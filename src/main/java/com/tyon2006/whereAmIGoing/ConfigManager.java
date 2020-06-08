package com.tyon2006.whereAmIGoing;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class ConfigManager {
	
	public static Configuration config;
	
	public static boolean disableCategories = false;
	public static boolean enableDebug = false;
	
	public static int timeFadeIn = 20;
	public static int displayTime = 10;
	public static int timeFadeOut = 20;
	
	public static String[] tier1BiomesArray = {"Beach", "Birch Forest", "Birch Forest Hills", "Birch Forest Hills M", "Birch Forest M", "Cold Beach", "Cold Taiga", "Cold Taiga Hills", "Cold Taiga M", "Deep Ocean", "Desert", "Desert M", "DesertHills", "Extreme Hills", "Extreme Hills Edge", "Extreme Hills M", "Extreme Hills+", "Extreme Hills+ M", "Flower Forest", "Forest", "ForestHills", "FrozenOcean", "FrozenRiver", "Ice Mountains", "Ice Plains", "Ice Plains Spikes", "Jungle", "Jungle M", "JungleEdge", "JungleEdge M", "JungleHills", "Mega Spruce Taiga", "Mega Taiga", "Mega Taiga Hills", "Mesa", "Mesa (Bryce)", "Mesa Plateau", "Mesa Plateau F", "Mesa Plateau F M", "Mesa Plateau M", "MushroomIsland", "MushroomIslandShore", "Ocean", "Plains", "Redwood Taiga Hills M", "River", "Roofed Forest", "Roofed Forest M", "Savanna", "Savanna M", "Savanna Plateau", "Savanna Plateau M", "Stone Beach", "Sunflower Plains", "Swampland", "Swampland M", "Taiga", "Taiga M", "TaigaHills"};
	public static String tier1Subtitle = "";

	public static String[] tier2BiomesArray = {""};
	public static String tier2Subtitle = "";
	
	public static String[] tier3BiomesArray = {""};
	public static String tier3Subtitle = "";
	
	public static String[] tier4BiomesArray = {"The Void", "The End"};
	public static String tier4Subtitle = "";
	
	public static String[] tier5BiomesArray = {""};
	public static String tier5Subtitle = "";
	
	public static String[] tier6BiomesArray = {"Hell"};
	public static String tier6Subtitle = "";
	
	public static String[] tier7BiomesArray = {""};
	public static String tier7Subtitle = "";
	
	public static String[] excludedBiomesArray = {"River", "Beach"};
	
	public static void init(File configFile) {
		// TODO Auto-generated method stub
		if(config == null)
		{
			config = new Configuration(configFile);
			load();
		}
	}
	
	public static void load()
	{	
		disableCategories = config.getBoolean("Disable Categories?", "0Main", false, "Setting this value to true will disable all biome checking, categorization, and subtitles (except ignored to help prevent biome spam). Instead all biome names will display as they are approached in aqua and with no subtitle. Nice for big modpacks with tons and tons of biomes you don't feel like configuring and you just want to know where you're going. See what I did there? :)");
		enableDebug = config.getBoolean("Enable Debug?", "0Main", false, "Enabling this will write some findings to the log to help with debugging config. Can be chatty, so use with care.");
		
		timeFadeIn = config.getInt("Fade in Time", "Title Display Timings", timeFadeIn, 0, 100, "Amount of time it takes for the title to fade in. 0 will appear instantly and higher numbers will fade in more slowly. Whole number value measured in ticks (20 per second)");
		displayTime = config.getInt("Display Time", "Title Display Timings", displayTime, 0, 100, "Amount of time the title displays at full opacity after fading in. 0 will instantly begin the fade out action and higher numbers will display for longer time. Whole number value measured in ticks (20 per second)");
		timeFadeOut = config.getInt("Fade out Time", "Title Display Timings", timeFadeOut, 0, 100, "Amount of time is takes for the title to fade out after displaying. 0 will instantly disappear after displaying and higher numbers will fade out more slowly. Whole number value measured in ticks (20 per second)");
		
		tier1Subtitle = config.getString("Category 1 Title", "Biome Category Names", tier1Subtitle, "Words that display at the subtitle of Category 1 biomes.");
		tier2Subtitle = config.getString("Category 2 Title", "Biome Category Names", tier2Subtitle, "Words that display at the subtitle of Category 2 biomes.");
		tier3Subtitle = config.getString("Category 3 Title", "Biome Category Names", tier3Subtitle, "Words that display at the subtitle of Category 3 biomes.");
		tier4Subtitle = config.getString("Category 4 Title", "Biome Category Names", tier4Subtitle, "Words that display at the subtitle of Category 4 biomes.");
		tier5Subtitle = config.getString("Category 5 Title", "Biome Category Names", tier5Subtitle, "Words that display at the subtitle of Category 5 biomes.");
		tier6Subtitle = config.getString("Category 6 Title", "Biome Category Names", tier6Subtitle, "Words that display at the subtitle of Category 6 biomes.");
		tier7Subtitle = config.getString("Category 7 Title", "Biome Category Names", tier7Subtitle, "Words that display at the subtitle of Category 7 biomes.");

		tier1BiomesArray = config.getStringList("Category 1 Biomes (aqua)", "Biome Sets", tier1BiomesArray, "Contains an array of biome name strings to display in category 1 biomes.");
		tier2BiomesArray = config.getStringList("Category 2 Biomes (green)", "Biome Sets", tier2BiomesArray, "Contains an array of biome name strings to display in category 2 biomes.");
		tier3BiomesArray = config.getStringList("Category 3 Biomes (yellow)", "Biome Sets", tier3BiomesArray, "Contains an array of biome name strings to display in category 3 biomes.");
		tier4BiomesArray = config.getStringList("Category 4 Biomes (gold)", "Biome Sets", tier4BiomesArray, "Contains an array of biome name strings to display in category 4 biomes.");
		tier5BiomesArray = config.getStringList("Category 5 Biomes (red)", "Biome Sets", tier5BiomesArray, "Contains an array of biome name strings to display in category 5 biomes.");
		tier6BiomesArray = config.getStringList("Category 6 Biomes (dark red)", "Biome Sets", tier6BiomesArray, "Contains an array of biome name strings to display in category 6 biomes.");
		tier7BiomesArray = config.getStringList("Category 7 Biomes (light purple)", "Biome Sets", tier7BiomesArray, "Contains an array of biome name strings to display in category 7 biomes.");
		excludedBiomesArray = config.getStringList("Ignored Biomes", "Biome Sets", excludedBiomesArray, "Contains an array of biome name strings that will be ignored by this mod's features." );
		
		if (config.hasChanged())
		{
			config.save();
		}
	}
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if(event.getModID().equalsIgnoreCase("whereamigoing"))
		{
			load();
		}
	}
}
