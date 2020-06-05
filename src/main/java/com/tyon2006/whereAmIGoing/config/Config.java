package com.tyon2006.whereAmIGoing.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Config {
	
	private static Configuration config = null;
	
	public static void preInit() {
		File configFile = new File(Loader.instance().getConfigDir(), "WhereAmIGoing.cfg");
		config = new Configuration(configFile);
		syncFromFiles();
	}
	
	public static Configuration getConfig() {
		return config;
	}
	
	public static void clientpreInit() {
		
	}
	
	public static void syncFromFiles() {
		
	}
	
	public static void syncFromGUI() {
		
	}
	
	public static void syncFromFields() {
		
	}
	
	private static void syncConfig(boolean loadFromConfigFile, boolean readFieldsFromConfig) {
		
	}

}
