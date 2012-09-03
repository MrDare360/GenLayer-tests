package mods.dinocraft.common.biomes;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.WorldGenTallGrass;
import net.minecraft.src.WorldGenerator;

public class DCBiomeClearing extends DCBiomeBase {

	public DCBiomeClearing(int i) {
		super(i);

		this.temperature = 0.8F;
		this.rainfall = 0.4F;

		this.minHeight = 0.01F;
		this.maxHeight = 0.0F;
		
		getDCBiomeDecorator().setTreesPerChunk(-999);
		getDCBiomeDecorator().setGrassPerChunk(10);
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random) {
		return new WorldGenTallGrass(Block.tallGrass.blockID, 1);
	}

}
