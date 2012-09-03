package mods.dinocraft.common.genlayer;

import mods.dinocraft.common.biomes.DCBiomeBase;
import net.minecraft.src.GenLayer;
import net.minecraft.src.IntCache;

public class GenLayerDCClearMajorFeatures extends GenLayer {

	private static final int SCAN = 4;
	  private static final int DSCAN = 9;

	  public GenLayerDCClearMajorFeatures(long par1, GenLayer par3GenLayer)
	  {
	    super(par1);
	    this.parent = par3GenLayer;
	  }

	  public int[] getInts(int x, int z, int width, int depth)
	  {
	    int[] in = this.parent.getInts(x - 4, z - 4, width + 9, depth + 9);
	    int[] out = IntCache.getIntCache(width * depth);

	    for (int dz = 0; dz < depth; dz++)
	    {
	      for (int dx = 0; dx < width; dx++)
	      {
	        this.initChunkSeed(dx + x, dz + z);
	        int inputBiome = in[(dx + 4 + (dz + 4) * (width + 9))];

	        if ((inputBiome > 0) && (inputBiome < DCBiomeBase.majorFeature.biomeID))
	        {
	          boolean valid = true;
	          for (int sz = 0; sz <= 8; sz++) {
	            for (int sx = 0; sx <= 8; sx++) {
	              int scanBiome = in[(dx + sx + (dz + sz) * (width + 9))];
	              if (scanBiome == DCBiomeBase.majorFeature.biomeID) {
	                valid = false;
	              }
	            }
	          }

	          out[(dx + dz * width)] = (valid ? inputBiome : 0);
	        }
	        else {
	          out[(dx + dz * width)] = inputBiome;
	        }
	      }
	    }

	    return out;
	  }

}
