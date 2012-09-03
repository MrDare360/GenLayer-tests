package mods.dinocraft.common.genlayer;

import mods.dinocraft.common.biomes.DCBiomeBase;
import net.minecraft.src.GenLayer;
import net.minecraft.src.IntCache;

public class GenLayerDCReinsertFeatures extends GenLayer {

	private GenLayer withoutLayer;
	  private GenLayer withLayer;

	  public GenLayerDCReinsertFeatures(long par1, GenLayer par3GenLayer, GenLayer par4GenLayer)
	  {
	    super(par1);
	    this.withoutLayer = par3GenLayer;
	    this.withLayer = par4GenLayer;
	  }

	  public void a(long par1)
	  {
	    this.withoutLayer.initWorldGenSeed(par1);
	    this.withLayer.initWorldGenSeed(par1);
	    super.initWorldGenSeed(par1);
	  }

	  public int[] getInts(int mapX, int mapZ, int mapWidth, int mapDepth)
	  {
	    int[] without = this.withoutLayer.getInts(mapX, mapZ, mapWidth, mapDepth);
	    int[] with = this.withLayer.getInts(mapX, mapZ, mapWidth, mapDepth);
	    int[] dest = IntCache.getIntCache(mapWidth * mapDepth);

	    for (int i = 0; i < mapWidth * mapDepth; i++)
	    {
	      if (with[i] > 0)
	      {
	        dest[i] = (with[i] == DCBiomeBase.majorFeature.biomeID ? with[i] : DCBiomeBase.minorFeature.biomeID);
	      }
	      else
	      {
	        dest[i] = without[i];
	      }
	    }

	    return dest;
	  }
}
