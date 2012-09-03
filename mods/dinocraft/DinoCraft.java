package mods.dinocraft;

import java.util.logging.Level;

import mods.dinocraft.common.ItemTP;
import mods.dinocraft.common.WorldProviderDinoCraft;
import net.minecraft.src.Block;
import net.minecraft.src.DimensionAPI;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(name="DinoCraft", modid="DinoCraft", version="0.1a")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class DinoCraft {

	@Instance
	public static DinoCraft instance;
	
	@SidedProxy(clientSide="mods.dinocraft.client.ClientProxy", serverSide="mods.dinocraft.CommonProxy")
	public static CommonProxy proxy;
	
	public static int worldType;
	public static int dimensionID;
	
	//Biome ID's
	public static int idBiomeMajorFeature;
	public static int idBiomeMinorFeature;
	public static int idBiomeLake;
	public static int idBiomeLakeBorder;
	public static int idBiomeClearing;
	public static int idBiomeClearingBorder;
	public static int idBiomeStream;
	public static int idDinoForest;
	public static int idDinoBiome;
	
	public static Item tp;
	
	@Init
	public void init(FMLInitializationEvent evt) {
		
		tp = new ItemTP(3400);
		
	}
	
	@PreInit
	public void preInit(FMLPreInitializationEvent evt) {
		
		Configuration cfg = new Configuration(evt.getSuggestedConfigurationFile());
		
		try {
			cfg.load();
			
			worldType = cfg.getOrCreateIntProperty("World Type ID", cfg.CATEGORY_GENERAL, 10).getInt();
			dimensionID = cfg.getOrCreateIntProperty("Dimension ID", cfg.CATEGORY_GENERAL, 12).getInt();
			
			idBiomeMajorFeature = cfg.getOrCreateIntProperty("Biome MF ID", cfg.CATEGORY_GENERAL, 50).getInt();
			idBiomeMinorFeature = cfg.getOrCreateIntProperty("Biome MFS ID", cfg.CATEGORY_GENERAL, 51).getInt();
			idBiomeLake = cfg.getOrCreateIntProperty("Biome Lake ID", cfg.CATEGORY_GENERAL, 52).getInt();
			idBiomeLakeBorder = cfg.getOrCreateIntProperty("Biome LakeB ID", cfg.CATEGORY_GENERAL, 53).getInt();
			idBiomeClearing = cfg.getOrCreateIntProperty("Biome Clearing ID", cfg.CATEGORY_GENERAL, 54).getInt();
			idBiomeClearingBorder = cfg.getOrCreateIntProperty("Biome ClearingB ID", cfg.CATEGORY_GENERAL, 55).getInt();
			idBiomeStream = cfg.getOrCreateIntProperty("Biome Stream ID", cfg.CATEGORY_GENERAL, 56).getInt();
			idDinoForest = cfg.getOrCreateIntProperty("Biome Forest ID", cfg.CATEGORY_GENERAL, 57).getInt();
			idDinoBiome = cfg.getOrCreateIntProperty("Biome bland ID", cfg.CATEGORY_GENERAL, 58).getInt();
			
		} catch(Exception e) {
			FMLLog.log(Level.SEVERE, e, "Dinocraft has had a problem loading it's confguration file!");
		} finally {
			cfg.save();
		}
		
		registerContent();
		
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent evt) {
		
		createRecipes();
		
	}
	
	private void createRecipes() {
		
		GameRegistry.addShapelessRecipe(new ItemStack(tp, 64), new Object[] {
			Block.dirt
		});
		
	}
	
	private void registerContent() {
		
		DimensionAPI.registerDimension(new WorldProviderDinoCraft());
		
	}
}
