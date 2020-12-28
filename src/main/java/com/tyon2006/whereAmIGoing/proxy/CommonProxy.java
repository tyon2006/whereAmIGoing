package com.tyon2006.whereAmIGoing.proxy;

import com.tyon2006.whereAmIGoing.config.ConfigManager;
import com.tyon2006.whereAmIGoing.events.WaigBiomeDifficultyHandler;
import com.tyon2006.whereAmIGoing.events.WaigBiomeMobAttributeHandler;
import com.tyon2006.whereAmIGoing.events.WaigRareSpawnHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class CommonProxy {

	public void init(FMLInitializationEvent event) {

		if(ConfigManager.enableBiomeAttributeTweaks==true) MinecraftForge.EVENT_BUS.register(new WaigBiomeMobAttributeHandler());
		if(ConfigManager.enableBiomeDifficulty==true) MinecraftForge.EVENT_BUS.register(new WaigBiomeDifficultyHandler());
		if(ConfigManager.enableRarespawn==true) MinecraftForge.EVENT_BUS.register(new WaigRareSpawnHandler());
		
	}	
}
