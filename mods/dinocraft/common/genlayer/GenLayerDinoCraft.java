package mods.dinocraft.common.genlayer;

import net.minecraft.src.GenLayer;
import net.minecraft.src.GenLayerAddIsland;
import net.minecraft.src.GenLayerAddMushroomIsland;
import net.minecraft.src.GenLayerAddSnow;
import net.minecraft.src.GenLayerFuzzyZoom;
import net.minecraft.src.GenLayerHills;
import net.minecraft.src.GenLayerIsland;
import net.minecraft.src.GenLayerRiver;
import net.minecraft.src.GenLayerRiverInit;
import net.minecraft.src.GenLayerRiverMix;
import net.minecraft.src.GenLayerShore;
import net.minecraft.src.GenLayerSmooth;
import net.minecraft.src.GenLayerSwampRivers;
import net.minecraft.src.GenLayerVoronoiZoom;
import net.minecraft.src.GenLayerZoom;
import net.minecraft.src.WorldType;

public class GenLayerDinoCraft extends GenLayer{

	public GenLayerDinoCraft(long par1) {
		super(par1);
	}

	@Override
	public int[] getInts(int var1, int var2, int var3, int var4) {
		int[] array = {var1, var2, var3, var4};
		
		return array;
	}
	
	public static GenLayer[] makeWorld(long l)
    {
		byte zoomFactor = 4;

	    GenLayer biomes = new GenLayerDCBiomes(1L);
	    GenLayer features = new GenLayerDCMajorFeatures(1L);

	    biomes = new GenLayerZoom(1000L, biomes);
	    biomes = new GenLayerZoom(1001L, biomes);

	    biomes = new GenLayerDCBiomeBorders(500L, biomes);

	    biomes = new GenLayerZoom(1002L, biomes);
	    biomes = new GenLayerZoom(1003L, biomes);

	    features = GenLayerDCFeatureZoom.multipleZoom(1000L, features, 4);

	    features = new GenLayerDCMinorFeatures(700, biomes, features);
	    features = new GenLayerDCClearMajorFeatures(700L, features);
	    features = new GenLayerDCClearMinorFeatures(701L, features);

	    biomes = new GenLayerZoom(1004L, biomes);
	    biomes = new GenLayerZoom(1005L, biomes);

	    GenLayer riverLayer = new GenLayerDCStream(1L, biomes);
	    riverLayer = new GenLayerSmooth(7000L, riverLayer);
	    biomes = new GenLayerRiverMix(100L, biomes, riverLayer);

	    GenLayer genlayervoronoizoom = new GenLayerVoronoiZoom(10L, biomes);

	    features = GenLayerDCFeatureZoom.multipleZoom(1004L, features, 4);

	    genlayervoronoizoom = new GenLayerDCReinsertFeatures(100L, genlayervoronoizoom, features);

	    biomes.initWorldGenSeed(l);
	    genlayervoronoizoom.initWorldGenSeed(l);
	    features.initWorldGenSeed(l);

	    return new GenLayer[] { biomes, genlayervoronoizoom, features };
    }
	
}
