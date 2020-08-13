package com.tyon2006.whereAmIGoing.proxy;

import com.tyon2006.whereAmIGoing.config.ConfigManager;
import com.tyon2006.whereAmIGoing.events.WaigEventHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy {

	@Override
	@SideOnly(Side.CLIENT)
	public void init(FMLInitializationEvent event) {
		if(ConfigManager.enableBiomeNav==true) MinecraftForge.EVENT_BUS.register(new WaigEventHandler());
		super.init(event);
	}	
}
