package mods.dinocraft.common.genlayer;

import mods.dinocraft.common.biomes.DCBiomeBase;
import net.minecraft.src.GenLayer;
import net.minecraft.src.IntCache;

public class GenLayerDCBiomeBorders extends GenLayer {

	public GenLayerDCBiomeBorders(long l, GenLayer genlayer)
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
	        int right = input[(dx + 0 + (dz + 1) * nwidth)];
	        int left = input[(dx + 2 + (dz + 1) * nwidth)];
	        int up = input[(dx + 1 + (dz + 0) * nwidth)];
	        int down = input[(dx + 1 + (dz + 2) * nwidth)];
	        int center = input[(dx + 1 + (dz + 1) * nwidth)];
	        if (onBorder(DCBiomeBase.dcLake.biomeID, center, right, left, up, down))
	        {
	          output[(dx + dz * width)] = DCBiomeBase.lakeBorder.biomeID;
	        }
	        else if (onBorder(DCBiomeBase.clearing.biomeID, center, right, left, up, down))
	        {
	          output[(dx + dz * width)] = DCBiomeBase.clearingBorder.biomeID;
	        }
	        else
	        {
	          output[(dx + dz * width)] = center;
	        }
	      }

	    }

	    return output;
	  }

	  boolean onBorder(int biome, int center, int right, int left, int up, int down)
	  {
	    if (center != biome) {
	      return false;
	    }

	    if ((right != biome) && (!DCBiomeBase.isFeature(right))) {
	      return true;
	    }
	    if ((left != biome) && (!DCBiomeBase.isFeature(left))) {
	      return true;
	    }
	    if ((up != biome) && (!DCBiomeBase.isFeature(up))) {
	      return true;
	    }

	    return (down != biome) && (!DCBiomeBase.isFeature(down));
	  }
}
