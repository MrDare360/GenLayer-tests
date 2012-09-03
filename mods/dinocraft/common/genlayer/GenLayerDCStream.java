package mods.dinocraft.common.genlayer;

import mods.dinocraft.common.biomes.DCBiomeBase;
import net.minecraft.src.GenLayer;
import net.minecraft.src.IntCache;

public class GenLayerDCStream extends GenLayer {

	public GenLayerDCStream(long l, GenLayer genlayer)
	  {
	    super(l);
	    this.parent = genlayer;
	  }

	  public int[] getInts(int x, int z, int width, int depth)
	  {
	    int nx = x - 1;
	    int nz = z - 1;
	    int nwidth = width + 2;
	    int ndepth = depth + 2;
	    int[] input = this.parent.getInts(nx, nz, nwidth, ndepth);
	    int[] output = IntCache.getIntCache(width * depth);
	    for (int dz = 0; dz < depth; dz++)
	    {
	      for (int dx = 0; dx < width; dx++)
	      {
	        int left = input[(dx + 0 + (dz + 1) * nwidth)];
	        int right = input[(dx + 2 + (dz + 1) * nwidth)];
	        int down = input[(dx + 1 + (dz + 0) * nwidth)];
	        int up = input[(dx + 1 + (dz + 2) * nwidth)];
	        int mid = input[(dx + 1 + (dz + 1) * nwidth)];

	        if (shouldStream(mid, left, down, right, up))
	        {
	          output[(dx + dz * width)] = DCBiomeBase.stream.biomeID;
	        }
	        else {
	          output[(dx + dz * width)] = -1;
	        }
	      }

	    }

	    return output;
	  }

	  boolean shouldStream(int mid, int left, int down, int right, int up) {
	    if (shouldStream(mid, left)) {
	      return true;
	    }
	    if (shouldStream(mid, right)) {
	      return true;
	    }
	    if (shouldStream(mid, down)) {
	      return true;
	    }

	    return shouldStream(mid, up);
	  }

	  boolean shouldStream(int biome1, int biome2)
	  {
	    if (biome1 == biome2) {
	      return false;
	    }
	    if (biome1 == -biome2) {
	      return false;
	    }

	    if ((biome1 != DCBiomeBase.dinoForest.biomeID) && (biome2 != DCBiomeBase.dinoBiome.biomeID)) {
	      return false;
	    }

	    return (!DCBiomeBase.isFeature(biome1)) && (!DCBiomeBase.isFeature(biome2));
	  }
}
