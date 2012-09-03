package mods.dinocraft.common.biomes;

import net.minecraft.src.Block;

public class DCBiomeCenter extends DCBiomeBase {

	public DCBiomeCenter(int i) {
		super(i);

		this.topBlock = (byte) Block.grass.blockID;
		this.fillerBlock = (byte) Block.stone.blockID;
	}

}
