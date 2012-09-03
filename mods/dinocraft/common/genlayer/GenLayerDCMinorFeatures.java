package mods.dinocraft.common.genlayer;

import mods.dinocraft.common.biomes.DCBiomeBase;
import net.minecraft.src.GenLayer;
import net.minecraft.src.IntCache;

public class GenLayerDCMinorFeatures extends GenLayer {

	protected GenLayer biomeLayer;

	public GenLayerDCMinorFeatures(int par1, GenLayer biomes, GenLayer features) {
		super(par1);
		this.parent = features;
		this.biomeLayer = biomes;
	}

	public int[] getInts(int x, int z, int width, int depth) {
		int nwidth = width + 2;

		int[] biomes = this.biomeLayer.getInts(x - 1, z - 1, nwidth, depth + 2);
		int[] parentFeatures = this.parent.getInts(x, z, width, depth);
		int[] out = IntCache.getIntCache(width * depth);

		for (int dz = 0; dz < depth; dz++) {
			for (int dx = 0; dx < width; dx++) {
				this.initChunkSeed(dx + x, dz + z);

				int parentFeature = parentFeatures[(dx + dz * width)];
				int inputBiome = biomes[(dx + 1 + (dz + 1) * nwidth)];

				if (DCBiomeBase.isFeature(parentFeature)) {
					out[(dx + dz * width)] = parentFeature;
				} else if (this.nextInt(2) == 0) {
					int up = biomes[(dx + 1 + (dz + 0) * nwidth)];
					int right = biomes[(dx + 2 + (dz + 1) * nwidth)];
					int left = biomes[(dx + 0 + (dz + 1) * nwidth)];
					int down = biomes[(dx + 1 + (dz + 2) * nwidth)];
					int ul = biomes[(dx + 0 + (dz + 0) * nwidth)];
					int dr = biomes[(dx + 2 + (dz + 2) * nwidth)];
					int ur = biomes[(dx + 2 + (dz + 0) * nwidth)];
					int dl = biomes[(dx + 0 + (dz + 2) * nwidth)];

					if ((ul == inputBiome) && (dr == inputBiome) && (ur == inputBiome) && (dl == inputBiome) && (up == inputBiome) && (right == inputBiome) && (left == inputBiome) && (down == inputBiome)) {
						out[(dx + dz * width)] = nextInt(DCBiomeBase.majorFeature.biomeID);
					} else {
						out[(dx + dz * width)] = 0;
					}
				} else {
					out[(dx + dz * width)] = 0;
				}
			}

		}

		return out;
	}

}
