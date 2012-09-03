package mods.dinocraft.common.biomes;

import java.util.Random;

import mods.dinocraft.DinoCraft;
import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.WorldGenTallGrass;
import net.minecraft.src.WorldGenerator;

public class DCBiomeBase extends BiomeGenBase{

	public static BiomeGenBase[] list = new BiomeGenBase[256];
	
	public static final BiomeGenBase majorFeature = new DCBiomeCenter(DinoCraft.idBiomeMajorFeature).setColor(16711680).setBiomeName("Major Feature");
	public static final BiomeGenBase minorFeature = new DCBiomeCenter2(DinoCraft.idBiomeMinorFeature).setColor(11184640).setBiomeName("Minor Feature");
	public static final BiomeGenBase dcLake = new DCBiomeOcean(DinoCraft.idBiomeLake).setColor(255).setBiomeName("Dino Lake");
	public static final BiomeGenBase lakeBorder = new DCBiomeForest(DinoCraft.idBiomeLakeBorder).setColor(26163).setBiomeName("Lake Border");
	public static final BiomeGenBase clearing = new DCBiomeClearing(DinoCraft.idBiomeClearing).setColor(3447604).setBiomeName("Dino Clearing");
	public static final BiomeGenBase clearingBorder = new DCBiomeForest(DinoCraft.idBiomeClearingBorder).setColor(26112).setBiomeName("Clearing Border");
	public static final BiomeGenBase stream = new DCBiomeStream(DinoCraft.idBiomeStream).setColor(3298231).setBiomeName("Dino Stream");
	public static final BiomeGenBase dinoForest = new DCBiomeDinoForest(DinoCraft.idDinoForest).setColor(3298231).setBiomeName("Dino Forest");
	public static final BiomeGenBase dinoBiome = new DCBiomeDino(DinoCraft.idDinoBiome).setColor(3298231).setBiomeName("Dino Biome");
	
	public DCBiomeBase(int i) {
		super(i);
		
		list[0] = majorFeature;
		list[1] = minorFeature;
		list[2] = dcLake;
		list[3] = lakeBorder;
		list[4] = clearing;
		list[5] = clearingBorder;
		list[6] = stream;
		list[7] = dinoForest;
		list[8] = dinoBiome;
		

		this.spawnableCreatureList.clear();

		this.spawnableMonsterList.clear();

		this.spawnableWaterCreatureList.clear();

		getDCBiomeDecorator().setTreesPerChunk(10);
		getDCBiomeDecorator().setGrassPerChunk(2);
	}

	protected DCBiomeBase setColor(int par1) {
		return (DCBiomeBase) super.setColor(par1);
	}

	public float getSpawningChance() {
		return 0.12F;
	}

	
	protected BiomeDecorator createBiomeDecorator() {
		return new DCBiomeDecorator(this);
	}

	protected DCBiomeDecorator getDCBiomeDecorator() {
		return (DCBiomeDecorator) this.theBiomeDecorator;
	}

	public WorldGenerator getRandomWorldGenForTrees(Random random) {
		if (random.nextInt(5) == 0) {
			return this.worldGeneratorTrees;
		}
		if (random.nextInt(10) == 0) {
			return this.worldGeneratorBigTree;
		}

		return this.worldGeneratorTrees;
	}

	public WorldGenerator getRandomWorldGenForGrass(Random par1Random) {
		if (par1Random.nextInt(4) == 0) {
			return new WorldGenTallGrass(Block.tallGrass.blockID, 2);
		}

		return new WorldGenTallGrass(Block.tallGrass.blockID, 1);
	}

	public static boolean isFeature(int idToCheck) {
		return (idToCheck == majorFeature.biomeID) || (idToCheck == minorFeature.biomeID);
	}
}
