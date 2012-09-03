package mods.dinocraft.common.genlayer;

import net.minecraft.src.GenLayer;
import net.minecraft.src.IntCache;

public class GenLayerDCFeatureZoom extends GenLayer {

	public boolean shiftCenter;

	public GenLayerDCFeatureZoom(long l, GenLayer genlayer, boolean sc) {
		super(l);
		this.parent = genlayer;
		this.shiftCenter = sc;
	}

	public int[] getInts(int x, int z, int width, int depth) {
		int sx = x >> 1;
		int sz = z >> 1;
		int swidth = (width >> 1) + 3;
		int sdepth = (depth >> 1) + 3;
		int[] src = this.parent.getInts(sx, sz, swidth, sdepth);
		int[] dest = IntCache.getIntCache(swidth * 2 * (sdepth * 2));
		int doubleWidth = swidth << 1;
		for (int dz = 0; dz < sdepth - 1; dz++) {
			for (int dx = 0; dx < swidth - 1; dx++) {
				dest[(dx * 2 + 0 + (dz * 2 + 0) * doubleWidth)] = src[(dx + dz
						* swidth)];
				dest[(dx * 2 + 1 + (dz * 2 + 0) * doubleWidth)] = 0;
				dest[(dx * 2 + 0 + (dz * 2 + 1) * doubleWidth)] = 0;
				dest[(dx * 2 + 1 + (dz * 2 + 1) * doubleWidth)] = 0;
			}
		}

		int[] output = IntCache.getIntCache(width * depth);
		for (int copyZ = 0; copyZ < depth; copyZ++) {
			System.arraycopy(dest, (copyZ + (z & 0x1)) * (swidth << 1)
					+ (x & 0x1), output, copyZ * width, width);
		}

		return output;
	}

	public static GenLayer multipleZoom(long l, GenLayer genlayer, int i) {
		Object obj = genlayer;
		for (int j = 0; j < i; j++) {
			obj = new GenLayerDCFeatureZoom(l + j, (GenLayer) (GenLayer) obj, false);
		}

		return (GenLayer) (GenLayer) obj;
	}

}
