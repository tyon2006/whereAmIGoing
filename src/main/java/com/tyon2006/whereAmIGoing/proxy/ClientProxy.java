package com.tyon2006.whereAmIGoing.proxy;

import com.tyon2006.whereAmIGoing.config.ConfigManager;
import com.tyon2006.whereAmIGoing.events.WaigBiomeDifficultyHandler;
import com.tyon2006.whereAmIGoing.events.WaigBiomeMobAttributeHandler;
import com.tyon2006.whereAmIGoing.events.WaigEventHandler;
import com.tyon2006.whereAmIGoing.events.WaigRareSpawnHandler;
import com.tyon2006.whereAmIGoing.renderer.biomeColorRenderer;
import com.tyon2006.whereAmIGoing.renderer.biomeColorRendererNonliving;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy {

	@Override
	public void init(FMLInitializationEvent event) {
		if(ConfigManager.enableBiomeNav==true) MinecraftForge.EVENT_BUS.register(new WaigEventHandler());
		if(ConfigManager.enableBiomeRenderColor==true) MinecraftForge.EVENT_BUS.register(biomeColorRenderer.class);

		
		//if(ConfigManager.enableBiomeAttributeTweaks==true) MinecraftForge.EVENT_BUS.register(biomeColorRendererNonliving.class); //dont think i need this little guy
		super.init(event);
	}	
}
