package com.tyon2006.whereAmIGoing.config;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.List;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConfigManager {
	
	public static Configuration config;
	
	public static boolean disableCategories = false;
	public static boolean enableDebug = false;
	
	public static int timeFadeIn = 20;
	public static int displayTime = 10;
	public static int timeFadeOut = 20;
	public static int displayWait = 20;
	
	public static String[] tier1BiomesArray = {"Birch Forest", "Birch Forest Hills", "Birch Forest Hills M", "Birch Forest M", "Cold Beach", "Cold Taiga", "Cold Taiga Hills", "Cold Taiga M", "Deep Ocean", "Desert", "Desert M", "DesertHills", "Extreme Hills", "Extreme Hills Edge", "Extreme Hills M", "Extreme Hills+", "Extreme Hills+ M", "Flower Forest", "Forest", "ForestHills", "FrozenOcean", "Ice Mountains", "Ice Plains", "Ice Plains Spikes", "Jungle", "Jungle M", "JungleEdge", "JungleEdge M", "JungleHills", "Mega Spruce Taiga", "Mega Taiga", "Mega Taiga Hills", "Mesa", "Mesa (Bryce)", "Mesa Plateau", "Mesa Plateau F", "Mesa Plateau F M", "Mesa Plateau M", "MushroomIsland", "MushroomIslandShore", "Ocean", "Plains", "Redwood Taiga Hills M", "Roofed Forest", "Roofed Forest M", "Savanna", "Savanna M", "Savanna Plateau", "Savanna Plateau M", "Stone Beach", "Sunflower Plains", "Swampland", "Swampland M", "Taiga", "Taiga M", "TaigaHills"};
	public static String tier1Subtitle = "Minecraft Overworld";

	public static String[] tier2BiomesArray = {"Bog", "Fen", "Alps", "AlpsFoothills", "Bamboo Forest", "Bayou", "Boreal Forest", "Brushland", "Chaparral", "Cherry Blossom Grove", "Cold Desert", "Coniferous Forest", "Coral Reef", "Crag", "Dead Forest", "Dead Swamp", "Eucalyptus Forest", "Flower Field", "Flower Island", "Glacier", "Grassland", "Gravel Beach", "Grove", "Highland", "Kelp Forest", "Land of Lakes", "Lavender Fields", "Lush Desert", "Lush Swamp", "Mangrove", "Maple Woods", "Marsh", "Meadow", "Moor", "Mountain", "MountainFoothills", "Mystic Grove", "Oasis", "Ominous Woods", "Orchard", "Origin Beach", "Origin Island", "Outback", "Overgrown Cliffs", "Pasture", "Prairie", "Quagmire", "Rainforest", "RedwoodForest", "RedwoodForestEdge", "Sacred Springs", "Seasonal Forest", "Shield", "Shrubland", "Snowy Coniferous Forest", "Snowy Forest", "SnowyTundra", "Steppe", "Temperate Rainforest", "Tropical Island", "Tropical Rainforest", "Tundra", "Volcanic Island", "Wasteland", "Wetland", "White Beach", "Woodland", "Xeric Shrubland"};
	public static String tier2Subtitle = "Biomes O'Plenty Overworld";
	
	public static String[] tier3BiomesArray = {""};
	public static String tier3Subtitle = "";
	
	public static String[] tier4BiomesArray = {"The Void", "The End"};
	public static String tier4Subtitle = "The End";
	
	public static String[] tier5BiomesArray = {""};
	public static String tier5Subtitle = "";
	
	public static String[] tier6BiomesArray = {"Hell", "Fungi Forest", "Corrupted Sands","Phantasmagoric Inferno", "Undergarden", "Visceral Heap"};
	public static String tier6Subtitle = "The Nether";
	
	public static String[] tier7BiomesArray = {""};
	public static String tier7Subtitle = "";
	
	public static String[] tier8BiomesArray = {""};
	public static String tier8Subtitle = "";
	
	public static String[] tier9iomesArray = {""};
	public static String tier9Subtitle = "";
	
	public static String[] tier10iomesArray = {""};
	public static String tier10Subtitle = "";
	
	public static String[] tier11iomesArray = {""};
	public static String tier11Subtitle = "";
	
	public static String[] tier12iomesArray = {""};
	public static String tier12Subtitle = "";
	
	public static String[] tier13iomesArray = {""};
	public static String tier13Subtitle = "";
	
	public static String[] tier14iomesArray = {""};
	public static String tier14Subtitle = "";
	
	public static String[] tier15BiomesArray = {""};
	public static String tier15Subtitle = "";
	
	public static String[] tier16BiomesArray = {""};
	public static String tier16Subtitle = "";
	
	
	public static String[] excludedBiomesArray = {"River", "Beach", "Frozen River"};
	
	public static File configFileTemp;

	public static boolean enableRarespawn;
	public static String[] rareSpawnArrayRaw = {"zombie|Poque the Dreadful|50|addhealth|20"};
	public static Map<String, String> mapSet = new HashMap<String, String>();
	//public static ArrayList<Map<String, String>> keyToken = new ArrayList<Map<String, String>>();
	public static Map<String, Map<String, String>> rareSpawnMap = new HashMap<String, Map<String, String>>();
	
	public static void init(File configFile) {
		// TODO Auto-generated method stub
		if(config == null)
		{
			config = new Configuration(configFile);
			configFileTemp = configFile;
			load();
		}
	}
	
	public static void load()
	{	
		enableRarespawn = config.getBoolean("Enable Rarespawn Module?", "0Main", true, "Enables rarespawn module. Note, requires configuration below.");
		disableCategories = config.getBoolean("Disable Categories?", "0Main", false, "Setting this value to true will disable all biome checking, categorization, and subtitles (except ignored to help prevent biome spam). Instead all biome names will display as they are approached in aqua and with no subtitle. Nice for big modpacks with tons and tons of biomes you don't feel like configuring and you just want to know where you're going. See what I did there? :)");
		enableDebug = config.getBoolean("Enable Debug?", "0Main", false, "Enabling this will write some findings to the log to help with debugging config. Can be chatty, so use with care.");
		
		timeFadeIn = config.getInt("Fade in Time", "Title Display Timings", timeFadeIn, 0, 100, "Amount of time it takes for the title to fade in. 0 will appear instantly and higher numbers will fade in more slowly. Whole number value measured in ticks (20 per second)");
		displayTime = config.getInt("Display Time", "Title Display Timings", displayTime, 0, 100, "Amount of time the title displays at full opacity after fading in. 0 will instantly begin the fade out action and higher numbers will display for longer time. Whole number value measured in ticks (20 per second)");
		timeFadeOut = config.getInt("Fade out Time", "Title Display Timings", timeFadeOut, 0, 100, "Amount of time is takes for the title to fade out after displaying. 0 will instantly disappear after displaying and higher numbers will fade out more slowly. Whole number value measured in ticks (20 per second)");
		displayWait = config.getInt("Time between Updates", "Title Display Timings", displayWait, 20, 1200, "Amount of time to wait until displaying the title again. Lower numbers will display updates faster/more often with higher numbers displaying less often. Whole number value measured in ticks (20 per second)");
		
		rareSpawnArrayRaw = config.getStringList("Rarespawn Entries", "Rarespawn", rareSpawnArrayRaw, "Enter 1 line per mob starting with the name of the mob, then name, then the percent chance of spawning, followed by attritbute name and value pairs.");
		
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
		if (enableRarespawn) {
			parseRarespawnStringToMap();
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

	public static void sync() {
		// TODO Auto-generated method stub
		init(configFileTemp);
	}
	
	@SuppressWarnings("null")
	public static void parseRarespawnStringToMap() {
		int rawArrayLen = rareSpawnArrayRaw.length;
		int rawArrayCursor = 0;

		String[] token;
		int tokenCursor = 0;
		String mobKey;

		//loop for the length of the input array
		while(rawArrayCursor < rawArrayLen) {
			tokenCursor = 0;
			//get the first entry name and assign as the key of map entry
			token = rareSpawnArrayRaw[rawArrayCursor].split("\\|");
			mobKey = token[tokenCursor];
			System.out.println(mobKey + " - FORMOB");
			tokenCursor++;
			String mapKey;
			String mapValue;
					
			//get the second entry and assign it as spawnname key
			mapKey = "spawname";
			mapValue = token[tokenCursor];
			tokenCursor++;			
			System.out.println(mapKey + " - namekey");
			System.out.println(mapValue + " - value");
			mapSet.put(mapKey, mapValue);
			System.out.println(mapSet.toString());

			//get the third entry and assign it with the spawn% key
			mapKey = "spawnchance";
			mapValue = token[tokenCursor];
			tokenCursor++;
			System.out.println(mapKey + " - chancekey");
			System.out.println(mapValue + " - value");
			mapSet.put(mapKey, mapValue);
			mapSet.toString();
			System.out.println(mapSet.toString());

			//count and loop through all the remaining values
			
			while(tokenCursor < token.length) {
				mapKey = token[tokenCursor];
				tokenCursor++;
				mapValue = token[tokenCursor];
				tokenCursor++;
				System.out.println(mapKey + " - key");
				System.out.println(mapValue + " - value");
				mapSet.put(mapKey, mapValue);
				System.out.println(mapSet.toString());
			}
	
			rareSpawnMap.put(mobKey, mapSet);
			System.out.println(rareSpawnMap.toString());
			System.out.println(rareSpawnMap.get("zombie").get("spawnchance"));
			rawArrayCursor++;
			mapSet.clear();
			//decrement count
		}
	}
}
