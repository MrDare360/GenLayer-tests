package mods.dinocraft.common.biomes;

import net.minecraft.src.Block;

public class DCBiomeCenter2 extends DCBiomeBase{

	public DCBiomeCenter2(int par1) {
		super(par1);
		
		this.topBlock = (byte)Block.grass.blockID;
		this.fillerBlock = (byte)Block.dirt.blockID;
		
	}
	
}
