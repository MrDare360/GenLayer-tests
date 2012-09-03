package mods.dinocraft.common.genlayer;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.GenLayer;
import net.minecraft.src.IntCache;
import net.minecraft.src.WorldType;

public class GenLayerAddBiomes extends GenLayerDinoCraft {
	BiomeGenBase[] biomes = { BiomeGenBase.desert, BiomeGenBase.extremeHills };

	public GenLayerAddBiomes(long l, GenLayer par) {
		super(l);
		this.parent = par;
	}

	@Override
	public int[] getInts(int par1, int par2, int par3, int par4) {
		int[] var5 = this.parent.getInts(par1, par2, par3, par4);
		int[] var6 = IntCache.getIntCache(par3 * par4);

		for (int var7 = 0; var7 < par4; ++var7) {
			for (int var8 = 0; var8 < par3; ++var8) {
				this.initChunkSeed((long) (var8 + par1), (long) (var7 + par2));
				int var9 = var5[var8 + var7 * par3];

				if (var9 == 0) {
					var6[var8 + var7 * par3] = 0;
				} else if (var9 == 1) {
					var6[var8 + var7 * par3] = this.biomes[this.nextInt(this.biomes.length)].biomeID;
				} else {
					int var10 = this.biomes[this.nextInt(this.biomes.length)].biomeID;
				}
			}
		}

		return var6;
	}
}