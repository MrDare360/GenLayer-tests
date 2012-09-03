package mods.dinocraft.common.biomes;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;

public class DCBiomeDecorator extends BiomeDecorator{

	public DCBiomeDecorator(BiomeGenBase par1BiomeGenBase) {
		super(par1BiomeGenBase);
	}

	public void setTreesPerChunk(int par1) {
		this.treesPerChunk = par1;
	}
	
	public void setGrassPerChunk(int par1) {
		this.grassPerChunk = par1;
	}
}
