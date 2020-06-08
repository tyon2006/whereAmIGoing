package com.tyon2006.whereAmIGoing;

import com.tyon2006.whereAmIGoing.util.Reference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class WhereAmIGoing {
	    
	@Instance
	public static WhereAmIGoing instance;
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event)
	{
    	MinecraftForge.EVENT_BUS.register(new ConfigManager());
    	ConfigManager.init(event.getSuggestedConfigurationFile());
	}
	
	@EventHandler
	public static void Init(FMLInitializationEvent event)
	{

	}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new WaigEventHandler());
	}
	
}
