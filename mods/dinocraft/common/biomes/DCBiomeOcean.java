package mods.dinocraft.common.biomes;

public class DCBiomeOcean extends DCBiomeBase {

	public DCBiomeOcean(int i) {
		super(i);

		this.minHeight = -1.9F;
		this.maxHeight = 0.5F;

		this.temperature = 0.66F;
		this.rainfall = 1.0F;

		this.spawnableWaterCreatureList.clear();
	}

}
