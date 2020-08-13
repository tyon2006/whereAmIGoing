package com.tyon2006.whereAmIGoing;

import java.util.logging.Logger;

import com.tyon2006.whereAmIGoing.proxy.CommonProxy;
//import com.tyon2006.whereAmIGoing.commands.WaigReload;
import com.tyon2006.whereAmIGoing.config.ConfigManager;
import com.tyon2006.whereAmIGoing.events.WaigBiomeDifficultyHandler;
import com.tyon2006.whereAmIGoing.events.WaigEventHandler;
import com.tyon2006.whereAmIGoing.events.WaigRareSpawnHandler;
import com.tyon2006.whereAmIGoing.util.Reference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)

public class WhereAmIGoing {
	
	@Instance
	public static WhereAmIGoing instance;
	public static Logger logger;
	
	@SidedProxy(clientSide = "com.tyon2006.whereAmIGoing.proxy.ClientProxy", serverSide = "com.tyon2006.whereAmIGoing.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event)
	{
    	MinecraftForge.EVENT_BUS.register(new ConfigManager());
    	ConfigManager.init(event.getSuggestedConfigurationFile());    	
	}
	
	@EventHandler
	public static void Init(FMLInitializationEvent event)
	{
        proxy.init(event);
	}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event)
	{
        //proxy.init(event);
	}
	
    @EventHandler
    public void init(FMLServerStartingEvent event)
    {
      //logger.info("initialize FMLServerStartingEvent :" + Reference.NAME);
      //event.registerServerCommand(new WaigReload()); //apparently you can't get this to happen after the client loads. explains why so many mods have their own customer config files.
    }
	
}
