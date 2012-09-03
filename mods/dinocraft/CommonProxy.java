package mods.dinocraft;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler{

	public static final String BLOCKS_PNG = "/mods/dinocraft/res/blocks.png";
	public static final String ITEMS_PNG = "/mods/dinocraft/res/items.png";
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	//Client stuff
	public void registerRenderInformation() {
		
	}
	
}
