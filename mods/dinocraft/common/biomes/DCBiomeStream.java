package mods.dinocraft.common.biomes;

public class DCBiomeStream extends DCBiomeBase {

	public DCBiomeStream(int i) {
		super(i);

		this.minHeight = -0.75F;
		this.maxHeight = -0.1F;

		this.temperature = 0.5F;
		this.rainfall = 1.0F;

		this.spawnableWaterCreatureList.clear();
	}

}
