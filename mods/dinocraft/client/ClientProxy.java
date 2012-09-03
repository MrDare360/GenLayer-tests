package mods.dinocraft.client;

import mods.dinocraft.CommonProxy;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy{

	@Override
	public void registerRenderInformation() {
		
		MinecraftForgeClient.preloadTexture(BLOCKS_PNG);
		MinecraftForgeClient.preloadTexture(ITEMS_PNG);
		
	}
}
