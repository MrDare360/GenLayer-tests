package mods.dinocraft.common.genlayer;

import mods.dinocraft.common.biomes.DCBiomeBase;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.GenLayer;
import net.minecraft.src.IntCache;

public class GenLayerDCBiomes extends GenLayer {

	protected BiomeGenBase[] commonBiomes = { DCBiomeBase.dinoBiome};

	protected BiomeGenBase[] rareBiomes = { DCBiomeBase.dinoForest};

	public GenLayerDCBiomes(long l, GenLayer genlayer) {
		super(l);
		this.parent = genlayer;
	}

	public GenLayerDCBiomes(long l) {
		super(l);
	}

	public int[] getInts(int x, int z, int width, int depth) {
		int[] dest = IntCache.getIntCache(width * depth);
		for (int dz = 0; dz < depth; dz++) {
			for (int dx = 0; dx < width; dx++) {
				this.initChunkSeed(dx + x, dz + z);
				if (this.nextInt(15) == 0) {
					dest[(dx + dz * width)] = this.rareBiomes[nextInt(this.rareBiomes.length)].biomeID;
				} else {
					dest[(dx + dz * width)] = this.commonBiomes[nextInt(this.commonBiomes.length)].biomeID;
				}

			}

		}

		return dest;
	}
}
