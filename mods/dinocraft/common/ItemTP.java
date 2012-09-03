package mods.dinocraft.common;

import net.minecraft.src.ItemTeleporterBase;
import net.minecraft.src.Teleporter;
import net.minecraft.src.WorldProviderBase;

public class ItemTP extends ItemTeleporterBase{

	public ItemTP(int i) {
		super(i);
	}

	@Override
	public WorldProviderBase getDimension() {
		return new WorldProviderDinoCraft();
	}

	@Override
	public Teleporter getTeleporter() {
		return new TeleporterDinoCraft();
	}

	
	
}
