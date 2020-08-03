package com.tyon2006.whereAmIGoing.config;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ConfigManager {
	
	public static Configuration config;
	
	public static boolean disableCategories = false;
	public static boolean enableBiomeNav = true;
	public static boolean enableRarespawn = true;
	public static boolean enableBiomeDifficulty = true;
	public static boolean enableDebug = false;
	
	public static int timeFadeIn = 20;
	public static int displayTime = 10;
	public static int timeFadeOut = 20;
	public static int displayWait = 20;
	
	public static String[] tier1BiomesArray = {"Birch Forest", "Birch Forest Hills", "Birch Forest Hills M", "Birch Forest M", "Cold Beach", "Cold Taiga", "Cold Taiga Hills", "Cold Taiga M", "Deep Ocean", "Desert", "Desert M", "DesertHills", "Extreme Hills", "Extreme Hills Edge", "Extreme Hills M", "Extreme Hills+", "Extreme Hills+ M", "Flower Forest", "Forest", "ForestHills", "FrozenOcean", "Ice Mountains", "Ice Plains", "Ice Plains Spikes", "Jungle", "Jungle M", "JungleEdge", "JungleEdge M", "JungleHills", "Mega Spruce Taiga", "Mega Taiga", "Mega Taiga Hills", "Mesa", "Mesa (Bryce)", "Mesa Plateau", "Mesa Plateau F", "Mesa Plateau F M", "Mesa Plateau M", "MushroomIsland", "MushroomIslandShore", "Ocean", "Plains", "Redwood Taiga Hills M", "Roofed Forest", "Roofed Forest M", "Savanna", "Savanna M", "Savanna Plateau", "Savanna Plateau M", "Stone Beach", "Sunflower Plains", "Swampland", "Swampland M", "Taiga", "Taiga M", "TaigaHills"};
	public static String tier1Subtitle = "Minecraft Overworld";
	public static String[] tier1BiomesDifficultyArray = {"0", "0", "0", "0"};
	//add health, add damage, add armor, add knockback resist
			
	public static String[] tier2BiomesArray = {"Bog", "Fen", "Alps", "AlpsFoothills", "Bamboo Forest", "Bayou", "Boreal Forest", "Brushland", "Chaparral", "Cherry Blossom Grove", "Cold Desert", "Coniferous Forest", "Coral Reef", "Crag", "Dead Forest", "Dead Swamp", "Eucalyptus Forest", "Flower Field", "Flower Island", "Glacier", "Grassland", "Gravel Beach", "Grove", "Highland", "Kelp Forest", "Land of Lakes", "Lavender Fields", "Lush Desert", "Lush Swamp", "Mangrove", "Maple Woods", "Marsh", "Meadow", "Moor", "Mountain", "MountainFoothills", "Mystic Grove", "Oasis", "Ominous Woods", "Orchard", "Origin Beach", "Origin Island", "Outback", "Overgrown Cliffs", "Pasture", "Prairie", "Quagmire", "Rainforest", "RedwoodForest", "RedwoodForestEdge", "Sacred Springs", "Seasonal Forest", "Shield", "Shrubland", "Snowy Coniferous Forest", "Snowy Forest", "SnowyTundra", "Steppe", "Temperate Rainforest", "Tropical Island", "Tropical Rainforest", "Tundra", "Volcanic Island", "Wasteland", "Wetland", "White Beach", "Woodland", "Xeric Shrubland"};
	public static String tier2Subtitle = "Biomes O'Plenty Overworld";
	public static String[] tier2BiomesDifficultyArray = {"0", "0", "0", "0"};
	
	public static String[] tier3BiomesArray = {""};
	public static String tier3Subtitle = "";
	public static String[] tier3BiomesDifficultyArray = {"0", "0", "0", "0"};
	
	public static String[] tier4BiomesArray = {"The Void", "The End"};
	public static String tier4Subtitle = "The End";
	public static String[] tier4BiomesDifficultyArray = {"0", "0", "0", "0"};
	
	public static String[] tier5BiomesArray = {""};
	public static String tier5Subtitle = "";
	public static String[] tier5BiomesDifficultyArray = {"0", "0", "0", "0"};
	
	public static String[] tier6BiomesArray = {"Hell", "Fungi Forest", "Corrupted Sands","Phantasmagoric Inferno", "Undergarden", "Visceral Heap"};
	public static String tier6Subtitle = "The Nether";
	public static String[] tier6BiomesDifficultyArray = {"0", "0", "0", "0"};
	
	public static String[] tier7BiomesArray = {""};
	public static String tier7Subtitle = "";
	public static String[] tier7BiomesDifficultyArray = {"0", "0", "0", "0"};
	
	public static String[] tier8BiomesArray = {""};
	public static String tier8Subtitle = "";
	public static String[] tier8BiomesDifficultyArray = {"0", "0", "0", "0"};
	
	public static String[] tier9BiomesArray = {""};
	public static String tier9Subtitle = "";
	public static String[] tier9BiomesDifficultyArray = {"0", "0", "0", "0"};
	
	public static String[] tier10BiomesArray = {""};
	public static String tier10Subtitle = "";
	public static String[] tier10BiomesDifficultyArray = {"0", "0", "0", "0"};
	
	public static String[] tier11BiomesArray = {""};
	public static String tier11Subtitle = "";
	public static String[] tier11BiomesDifficultyArray = {"0", "0", "0", "0"};
	
	public static String[] tier12BiomesArray = {""};
	public static String tier12Subtitle = "";
	public static String[] tier12BiomesDifficultyArray = {"0", "0", "0", "0"};
	
	public static String[] tier13BiomesArray = {""};
	public static String tier13Subtitle = "";
	public static String[] tier13BiomesDifficultyArray = {"0", "0", "0", "0"};
	
	public static String[] tier14BiomesArray = {""};
	public static String tier14Subtitle = "";
	public static String[] tier14BiomesDifficultyArray = {"0", "0", "0", "0"};
	
	public static String[] tier15BiomesArray = {""};
	public static String tier15Subtitle = "";	
	public static String[] tier15BiomesDifficultyArray = {"0", "0", "0", "0"};
	
	public static String[] excludedBiomesArray = {"River", "Beach", "Frozen River"};
	
	public static File configFileTemp;


	public static float rareSpawnXPboost;
	public static String[] rareSpawnArrayRaw = {"zombie|Poque the Dreadful|50|addhealth|20"};
	public static Map<String, String> mapSet = new HashMap<String, String>();
	public static Map<String, String> mobAttSet = new HashMap<String, String>();
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
		enableBiomeDifficulty= config.getBoolean("Enable Rarespawn Module?", "0Main", true, "Enables rarespawn module. Note, requires configuration below.");
		enableRarespawn = config.getBoolean("Enable Rarespawn Module?", "0Main", true, "Enables rarespawn module. Note, requires configuration below.");
		disableCategories = config.getBoolean("Disable Categories?", "0Main", false, "Setting this value to true will disable all biome checking, categorization, and subtitles (except ignored to help prevent biome spam). Instead all biome names will display as they are approached in aqua and with no subtitle. Nice for big modpacks with tons and tons of biomes you don't feel like configuring and you just want to know where you're going. See what I did there? :)");
		enableDebug = config.getBoolean("Enable Debug?", "0Main", false, "Enabling this will write some findings to the log to help with debugging config. Can be chatty, so use with care.");
		enableBiomeNav = config.getBoolean("Enable Biome Navigation Display?", "0Main", true, "Enabling this turn on the displaying of biome titles as you transition between them.");
		
		timeFadeIn = config.getInt("Fade in Time", "Title Display Timings", timeFadeIn, 0, 100, "Amount of time it takes for the title to fade in. 0 will appear instantly and higher numbers will fade in more slowly. Whole number value measured in ticks (20 per second)");
		displayTime = config.getInt("Display Time", "Title Display Timings", displayTime, 0, 100, "Amount of time the title displays at full opacity after fading in. 0 will instantly begin the fade out action and higher numbers will display for longer time. Whole number value measured in ticks (20 per second)");
		timeFadeOut = config.getInt("Fade out Time", "Title Display Timings", timeFadeOut, 0, 100, "Amount of time is takes for the title to fade out after displaying. 0 will instantly disappear after displaying and higher numbers will fade out more slowly. Whole number value measured in ticks (20 per second)");
		displayWait = config.getInt("Time between Updates", "Title Display Timings", displayWait, 20, 1200, "Amount of time to wait until displaying the title again. Lower numbers will display updates faster/more often with higher numbers displaying less often. Whole number value measured in ticks (20 per second)");
		
		rareSpawnArrayRaw = config.getStringList("Rarespawn Entries", "Rarespawn", rareSpawnArrayRaw, "Enter 1 line per mob starting with the name of the mob, then name, then the percent chance of spawning, followed by attritbute name and value pairs.");
		rareSpawnXPboost = config.getFloat("Rarespawn XP Booster", "Rarespawn", rareSpawnXPboost, 1f, 1000.0f, "When a Rarespawn is killed, it will drop its default XP value times the number provided here. Accepts float values.");
		
		tier1Subtitle = config.getString("Category 1 Title", "Biome Category Names", tier1Subtitle, "Words that display at the subtitle of Category 1 biomes.");
		tier2Subtitle = config.getString("Category 2 Title", "Biome Category Names", tier2Subtitle, "Words that display at the subtitle of Category 2 biomes.");
		tier3Subtitle = config.getString("Category 3 Title", "Biome Category Names", tier3Subtitle, "Words that display at the subtitle of Category 3 biomes.");
		tier4Subtitle = config.getString("Category 4 Title", "Biome Category Names", tier4Subtitle, "Words that display at the subtitle of Category 4 biomes.");
		tier5Subtitle = config.getString("Category 5 Title", "Biome Category Names", tier5Subtitle, "Words that display at the subtitle of Category 5 biomes.");
		tier6Subtitle = config.getString("Category 6 Title", "Biome Category Names", tier6Subtitle, "Words that display at the subtitle of Category 6 biomes.");
		tier7Subtitle = config.getString("Category 7 Title", "Biome Category Names", tier7Subtitle, "Words that display at the subtitle of Category 7 biomes.");
		tier8Subtitle = config.getString("Category 8 Title", "Biome Category Names", tier8Subtitle, "Words that display at the subtitle of Category 8 biomes.");
		tier9Subtitle = config.getString("Category 9 Title", "Biome Category Names", tier9Subtitle, "Words that display at the subtitle of Category 9 biomes.");
		tier10Subtitle = config.getString("Category 10 Title", "Biome Category Names", tier10Subtitle, "Words that display at the subtitle of Category 10 biomes.");
		tier11Subtitle = config.getString("Category 11 Title", "Biome Category Names", tier11Subtitle, "Words that display at the subtitle of Category 11 biomes.");
		tier12Subtitle = config.getString("Category 12 Title", "Biome Category Names", tier12Subtitle, "Words that display at the subtitle of Category 12 biomes.");
		tier13Subtitle = config.getString("Category 13 Title", "Biome Category Names", tier13Subtitle, "Words that display at the subtitle of Category 13 biomes.");
		tier14Subtitle = config.getString("Category 14 Title", "Biome Category Names", tier14Subtitle, "Words that display at the subtitle of Category 14 biomes.");
		tier15Subtitle = config.getString("Category 15 Title", "Biome Category Names", tier15Subtitle, "Words that display at the subtitle of Category 15 biomes.");

		tier1BiomesArray = config.getStringList("Category 1 Biomes (aqua)", "Biome Sets", tier1BiomesArray, "Contains an array of biome name strings to display in category 1 biomes.");
		tier2BiomesArray = config.getStringList("Category 2 Biomes (green)", "Biome Sets", tier2BiomesArray, "Contains an array of biome name strings to display in category 2 biomes.");
		tier3BiomesArray = config.getStringList("Category 3 Biomes (yellow)", "Biome Sets", tier3BiomesArray, "Contains an array of biome name strings to display in category 3 biomes.");
		tier4BiomesArray = config.getStringList("Category 4 Biomes (gold)", "Biome Sets", tier4BiomesArray, "Contains an array of biome name strings to display in category 4 biomes.");
		tier5BiomesArray = config.getStringList("Category 5 Biomes (red)", "Biome Sets", tier5BiomesArray, "Contains an array of biome name strings to display in category 5 biomes.");
		tier6BiomesArray = config.getStringList("Category 6 Biomes (dark red)", "Biome Sets", tier6BiomesArray, "Contains an array of biome name strings to display in category 6 biomes.");
		tier7BiomesArray = config.getStringList("Category 7 Biomes (light purple)", "Biome Sets", tier7BiomesArray, "Contains an array of biome name strings to display in category 7 biomes.");
		tier8BiomesArray = config.getStringList("Category 8 Biomes (dark_green)", "Biome Sets", tier8BiomesArray, "Contains an array of biome name strings to display in category 1 biomes.");
		tier9BiomesArray = config.getStringList("Category 9 Biomes (dark_aqua)", "Biome Sets", tier9BiomesArray, "Contains an array of biome name strings to display in category 2 biomes.");
		tier10BiomesArray = config.getStringList("Category 10 Biomes (dark_blue)", "Biome Sets", tier10BiomesArray, "Contains an array of biome name strings to display in category 3 biomes.");
		tier11BiomesArray = config.getStringList("Category 11 Biomes (dark_purple)", "Biome Sets", tier11BiomesArray, "Contains an array of biome name strings to display in category 4 biomes.");
		tier12BiomesArray = config.getStringList("Category 12 Biomes (white)", "Biome Sets", tier12BiomesArray, "Contains an array of biome name strings to display in category 5 biomes.");
		tier13BiomesArray = config.getStringList("Category 13 Biomes (gray)", "Biome Sets", tier13BiomesArray, "Contains an array of biome name strings to display in category 6 biomes.");
		tier14BiomesArray = config.getStringList("Category 14 Biomes (dark_grey)", "Biome Sets", tier14BiomesArray, "Contains an array of biome name strings to display in category 5 biomes.");
		tier15BiomesArray = config.getStringList("Category 15 Biomes (black)", "Biome Sets", tier15BiomesArray, "Contains an array of biome name strings to display in category 6 biomes.");
		
		tier1BiomesDifficultyArray = config.getStringList("Category 1 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier1BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add knockback resist] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		tier2BiomesDifficultyArray = config.getStringList("Category 2 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier2BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add knockback resist] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		tier3BiomesDifficultyArray = config.getStringList("Category 3 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier3BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add knockback resist] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		tier4BiomesDifficultyArray = config.getStringList("Category 4 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier4BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add knockback resist] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		tier5BiomesDifficultyArray = config.getStringList("Category 5 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier5BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add knockback resist] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		tier6BiomesDifficultyArray = config.getStringList("Category 6 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier6BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add knockback resist] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		tier7BiomesDifficultyArray = config.getStringList("Category 7 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier7BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add knockback resist] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		tier8BiomesDifficultyArray = config.getStringList("Category 8 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier8BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add knockback resist] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		tier9BiomesDifficultyArray = config.getStringList("Category 9 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier9BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add knockback resist] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		tier10BiomesDifficultyArray = config.getStringList("Category 10 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier10BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add knockback resist] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		tier11BiomesDifficultyArray = config.getStringList("Category 11 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier11BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add knockback resist] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		tier12BiomesDifficultyArray = config.getStringList("Category 12 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier12BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add knockback resist] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		tier13BiomesDifficultyArray = config.getStringList("Category 13 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier13BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add knockback resist] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		tier14BiomesDifficultyArray = config.getStringList("Category 14 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier14BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add knockback resist] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		tier15BiomesDifficultyArray = config.getStringList("Category 15 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier15BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add knockback resist] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		
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
	
	public Map<String, String> getAttributesFromMob(String mobName) {
		mobAttSet.clear();
		mobAttSet.putAll(rareSpawnMap.get(mobName));
		return mobAttSet;
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
			mapSet.clear();
			tokenCursor = 0;
			//get the first entry name and assign as the key of map entry
			token = rareSpawnArrayRaw[rawArrayCursor].split("\\|");
			mobKey = token[tokenCursor];
			System.out.println(mobKey + " - FORMOB");
			tokenCursor++;
			String mapKey;
			String mapValue;
					
			//get the second entry and assign it as spawnname key
			mapKey = "spawnname";
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
	
			//rareSpawnMap.put(mobKey,mapSet);
			rareSpawnMap.put(mobKey, new HashMap<String, String>() {{putAll(mapSet);}});
						
			rawArrayCursor++;
			mapSet.clear();
			//decrement count
		}
		System.out.println(rareSpawnMap.toString());
	}
}
