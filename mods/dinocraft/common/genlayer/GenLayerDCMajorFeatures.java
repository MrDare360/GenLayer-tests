package mods.dinocraft.common.genlayer;

import mods.dinocraft.common.biomes.DCBiomeBase;
import net.minecraft.src.GenLayer;
import net.minecraft.src.IntCache;

public class GenLayerDCMajorFeatures extends GenLayer {

	 public GenLayerDCMajorFeatures(long par1) {
		super(par1);
	}

	public int[] getInts(int x, int z, int width, int depth)
	  {
	    int[] out = IntCache.getIntCache(width * depth);
	    for (int i = 0; i < out.length; i++) {
	      out[i] = DCBiomeBase.majorFeature.biomeID;
	    }
	    return out;
	  }

}
