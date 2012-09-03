package mods.dinocraft.common.genlayer;

import mods.dinocraft.common.biomes.DCBiomeBase;
import net.minecraft.src.GenLayer;
import net.minecraft.src.IntCache;

public class GenLayerDCClearMinorFeatures extends GenLayer {

	private static final int SCAN = 3;
	private static final int DSCAN = 6;

	public GenLayerDCClearMinorFeatures(long par1, GenLayer par3GenLayer) {
		super(par1);
		this.parent = par3GenLayer;
	}

	public int[] getInts(int x, int z, int width, int depth) {
		int[] in = this.parent.getInts(x - 3, z - 3, width + 6, depth + 6);
		int[] out = IntCache.getIntCache(width * depth);

		for (int dz = 0; dz < depth; dz++) {
			for (int dx = 0; dx < width; dx++) {
				this.initChunkSeed(dx + x, dz + z);
				int inputBiome = in[(dx + 3 + (dz + 3) * (width + 6))];

				if ((inputBiome > 0) && (inputBiome < DCBiomeBase.majorFeature.biomeID)) {
					boolean valid = true;

					for (int sz = 0; sz <= 6; sz++) {
						for (int sx = 0; sx <= 6; sx++) {
							int scanBiome = in[(dx + sx + (dz + sz)
									* (width + 6))];
							if ((scanBiome >= inputBiome)
									&& ((sz != 3) || (sx != 3))) {
								valid = false;
							}
						}
					}

					out[(dx + dz * width)] = (valid ? inputBiome : 0);
					in[(dx + 3 + (dz + 3) * (width + 6))] = out[(dx + dz * width)];
				} else {
					out[(dx + dz * width)] = inputBiome;
				}
			}
		}

		return out;
	}
}
