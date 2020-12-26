package com.tyon2006.whereAmIGoing.config;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConfigManager {
	
	public static Configuration config;
		
	public static boolean disableCategories = false;
	public static boolean enableBiomeNav = true;
	public static boolean enableDebugBiomeHUD = false;
	public static boolean enableDebug = false;
	public static boolean enableDebugDifficultyHandler = false;
	public static boolean enableRarespawn = true;
	public static boolean enableRarespawnDebug = true;
	public static boolean enableBiomeDifficulty = true;
	public static boolean enableBiomeDifficultyDebug = false;
	public static boolean enableBiomeAttributeTweaks = true;
	public static boolean enableBiomeAttributeTweaksDebug = false;

	public static int timeFadeIn = 20;
	public static int displayTime = 10;
	public static int timeFadeOut = 20;
	public static int displayWait = 40;
	
	public static ArrayList<ResourceLocation> tier1BiomeResLoc = new ArrayList<ResourceLocation>();
	public static ArrayList<ResourceLocation> tier2BiomeResLoc = new ArrayList<ResourceLocation>();
	public static ArrayList<ResourceLocation> tier3BiomeResLoc = new ArrayList<ResourceLocation>();
	public static ArrayList<ResourceLocation> tier4BiomeResLoc = new ArrayList<ResourceLocation>();
	public static ArrayList<ResourceLocation> tier5BiomeResLoc = new ArrayList<ResourceLocation>();
	public static ArrayList<ResourceLocation> tier6BiomeResLoc = new ArrayList<ResourceLocation>();
	public static ArrayList<ResourceLocation> tier7BiomeResLoc = new ArrayList<ResourceLocation>();
	public static ArrayList<ResourceLocation> tier8BiomeResLoc = new ArrayList<ResourceLocation>();
	public static ArrayList<ResourceLocation> tier9BiomeResLoc = new ArrayList<ResourceLocation>();
	public static ArrayList<ResourceLocation> tier10BiomeResLoc = new ArrayList<ResourceLocation>();
	public static ArrayList<ResourceLocation> tier11BiomeResLoc = new ArrayList<ResourceLocation>();
	public static ArrayList<ResourceLocation> tier12BiomeResLoc = new ArrayList<ResourceLocation>();
	public static ArrayList<ResourceLocation> tier13BiomeResLoc = new ArrayList<ResourceLocation>();
	public static ArrayList<ResourceLocation> tier14BiomeResLoc = new ArrayList<ResourceLocation>();
	public static ArrayList<ResourceLocation> tier15BiomeResLoc = new ArrayList<ResourceLocation>();
	public static ArrayList<ResourceLocation> tier16BiomeResLoc = new ArrayList<ResourceLocation>();
	public static ArrayList<ResourceLocation> excludedBiomeResLoc = new ArrayList<ResourceLocation>();
	
	public static String[] tier1BiomesArray = {"minecraft:birch_forest", "biomesoplenty:boreal_forest", 
			"biomesoplenty:brushland", "biomesoplenty:dead_forest", "minecraft:desert", "minecraft:mutated_forest", 
			"minecraft:forest", "biomesoplenty:grassland", "biomesoplenty:gravel_beach", "biomesoplenty:grove", 
			"biomesoplenty:highland", "minecraft:jungle", "minecraft:mutated_jungle", "biomesoplenty:marsh", "minecraft:mutated_redwood_taiga", 
			"minecraft:mutated_mesa", "biomesoplenty:mountain", "minecraft:ocean", "biomesoplenty:orchard", "biomesoplenty:origin_beach", 
			"biomesoplenty:origin_island", "minecraft:plains", "minecraft:savanna", "minecraft:stone_beach", "minecraft:swampland", 
			"minecraft:taiga", "minecraft:mutated_taiga", "biomesoplenty:white_beach"};
	public static String tier1Subtitle = "Level 1 Biome";
	//add health, add damage, add armor, add armor toughness, add knockback resist, magic resist, follow range, xp bonus
	public static String[] tier1BiomesDifficultyArray = {"0", "0", "0", "0", "0", "0", "0", "1"};

	public static String[] tier2BiomesArray = {"minecraft:birch_forest_hills", "minecraft:mutated_birch_forest", "biomesoplenty:cold_desert", 
			"beasts:dried_reef", "minecraft:extreme_hills", "minecraft:smaller_extreme_hills", "biomesoplenty:fen", "minecraft:forest_hills", 
			"minecraft:frozen_ocean", "minecraft:jungle_edge", "biomesoplenty:lavender_fields", "biomesoplenty:meadow", "minecraft:mushroom_island", 
			"minecraft:mushroom_island_shore", "biomesoplenty:mystic_grove", "biomesoplenty:pasture", "biomesoplenty:prairie",
			"biomesoplenty:rainforest", "biomesoplenty:sacred_springs", "minecraft:mutated_savanna", "minecraft:mutated_savanna_rock",
			"biomesoplenty:shrubland", "biomesoplenty:snowy_coniferous_forest", "minecraft:mutated_swampland", "minecraft:taiga_hills", 
			"beasts:the_abyss", "biomesoplenty:wetland"};
	public static String tier2Subtitle = "Level 2 Biome";
	public static String[] tier2BiomesDifficultyArray = {"10", "2", "3", "2", "0.1", "0.1", "8", "1.25"};
	
	public static String[] tier3BiomesArray = {"biomesoplenty:bog", "biomesoplenty:cherry_blossom_grove", "minecraft:taiga_cold",
			"biomesoplenty:coniferous_forest", "biomesoplenty:coral_reef", "biomesoplenty:eucalyptus_forest", "minecraft:mutated_extreme_hills",
			"minecraft:extreme_hills_with_trees", "biomesoplenty:flower_field", "minecraft:ice_flats", "minecraft:jungle_hills",
			"biomesoplenty:kelp_forest", "biomesoplenty:land_of_lakes", "biomesoplenty:lush_desert", "biomesoplenty:lush_swamp", 
			"thaumcraft:magical_forest", "biomesoplenty:maple_woods", "minecraft:redwood_taiga_hills", "minecraft:mutated_mesa_rock",
			"minecraft:mutated_mesa_clear_rock", "biomesoplenty:mountain_foothills", "biomesoplenty:outback", "biomesoplenty:seasonal_forest",
			"biomesoplenty:steppe", "minecraft:mutated_plains", "biomesoplenty:temperate_rainforest", "biomesoplenty:volcanic_island"};
	public static String tier3Subtitle = "Level 3 Biome";
	public static String[] tier3BiomesDifficultyArray = {"20", "5", "6", "4", "0.3", "0.2", "16", "1.5"};
	
	public static String[] tier4BiomesArray = {"biomesoplenty:alps", "biomesoplenty:alps_foothills", "netherex:arctic_abyss", "biomesoplenty:bamboo_forest",
			"biomesoplenty:bayou", "biomesoplenty:chaparral", "minecraft:taiga_cold_hills", "minecraft:mutated_taiga_cold", "biomesoplenty:corrupted_sands",
			"biomesoplenty:crag", "minecraft:mutated_extreme_hills_with_trees", "biomesoplenty:flower_island", "netherex:fungi_forest",
			"biomesoplenty:fungi_forest", "minecraft:hell", "minecraft:mutated_jungle_edge", "biomesoplenty:mangrove", "minecraft:redwood_taiga",
			"minecraft:mesa", "minecraft:mesa_clear_rock", "biomesoplenty:moor", "biomesoplenty:oasis", "biomesoplenty:phantasmagoric_inferno",
			"biomesoplenty:redwood_forest_edge", "minecraft:roofed_forest", "netherex:ruthless_sands", "biomesoplenty:shield", "biomesoplenty:snowy_forest",
			"netherex:torrid_wasteland", "fossil:treasure", "biomesoplenty:tropical_rainforest", "biomesoplenty:undergarden", "biomesoplenty:visceral_heap",
			"biomesoplenty:woodland", "biomesoplenty:xeric_shrubland"};
	public static String tier4Subtitle = "Level 4 Biome";
	public static String[] tier4BiomesDifficultyArray = {"30", "9", "9", "6", "0.6", "0.3", "24", "1.75"};
	
	public static String[] tier5BiomesArray = {"biomesoplenty:dead_swamp", "minecraft:deep_ocean", "minecraft:mutated_desert", "minecraft:desert_hills",
			"thaumcraft:eerie", "biomesoplenty:glacier", "minecraft:ice_mountains", "minecraft:mutated_ice_flats", "minecraft:mesa_rock",
			"biomesoplenty:ominous_woods", "biomesoplenty:overgrown_cliffs", "biomesoplenty:quagmire", "minecraft:mutated_redwood_taiga_hills",
			"biomesoplenty:redwood_forest", "minecraft:mutated_roofed_forest", "minecraft:savanna_rock", "biomesoplenty:snowy_tundra", "minecraft:sky",
			"minecraft:void", "biomesoplenty:tropical_island", "biomesoplenty:tundra", "biomesoplenty:wasteland"};
	public static String tier5Subtitle = "Level 5 Biome";
	public static String[] tier5BiomesDifficultyArray = {"40", "12", "12", "8", "0.8", "0.4", "32", "2.0"};
	
	public static String[] tier6BiomesArray = {"aether:aether_void", "aether:aether_arctic_peaks", "midnight:black_ridge", "midnight:crystal_spires",
			"midnight:deceitful_bog", "erebus:elysian_fields", "erebus:fields_sub_forest", "aether:aether_forgotten_highlands", "erebus:fungal_forest",
			"midnight:fungi_forest", "aether:aether_highlands", "midnight:hilly_fungi_forest", "midnight:hilly_vigilant_forest", "aether:instanced_zone",
			"aether:aether_irradiated_forests", "aether:aether_magnetic_hills", "midnight:night_plains", "midnight:obscured_peaks",
			"midnight:obscured_plateau", "erebus:petrified_forest", "midnight:phantasmal_valley", "midnight:runebush_grove", "erebus:submerged_swamp",
			"erebus:subterranean_savannah", "erebus:ulterior_outback", "erebus:underground_jungle", "midnight:vigilant_forest", "erebus:volcanic_desert",
			"midnight:warped_fields"};
	public static String tier6Subtitle = "Level 6 Biome";
	public static String[] tier6BiomesDifficultyArray = {"50", "15", "30", "20", "0.9", "0.5", "40", "2.25"};
	
	public static String[] tier7BiomesArray = {"Dwarven Capital", "Dragon shrine", "Human Citadel"};
	public static String tier7Subtitle = "Sanctuary";
	public static String[] tier7BiomesDifficultyArray = {"0", "0", "0", "0", "0", "0", "0", "0"};
	
	public static String[] tier8BiomesArray = {""};
	public static String tier8Subtitle = "";
	public static String[] tier8BiomesDifficultyArray = {"0", "0", "0", "0", "0", "0", "0", "0"};
	
	public static String[] tier9BiomesArray = {""};
	public static String tier9Subtitle = "";
	public static String[] tier9BiomesDifficultyArray = {"0", "0", "0", "0", "0", "0", "0", "0"};
	
	public static String[] tier10BiomesArray = {""};
	public static String tier10Subtitle = "";
	public static String[] tier10BiomesDifficultyArray = {"0", "0", "0", "0", "0", "0", "0", "0"};
	
	public static String[] tier11BiomesArray = {""};
	public static String tier11Subtitle = "";
	public static String[] tier11BiomesDifficultyArray = {"0", "0", "0", "0", "0", "0", "0", "0"};
	
	public static String[] tier12BiomesArray = {""};
	public static String tier12Subtitle = "";
	public static String[] tier12BiomesDifficultyArray = {"0", "0", "0", "0", "0", "0", "0", "0"};
	
	public static String[] tier13BiomesArray = {""};
	public static String tier13Subtitle = "";
	public static String[] tier13BiomesDifficultyArray = {"0", "0", "0", "0", "0", "0", "0", "0"};
	
	public static String[] tier14BiomesArray = {""};
	public static String tier14Subtitle = "";
	public static String[] tier14BiomesDifficultyArray = {"0", "0", "0", "0", "0", "0", "0", "0"};
	
	public static String[] tier15BiomesArray = {""};
	public static String tier15Subtitle = "";	
	public static String[] tier15BiomesDifficultyArray = {"0", "0", "0", "0", "0", "0", "0", "0"};
	
	public static String[] tier16BiomesArray = {""};
	public static String tier16Subtitle = "";	
	public static String[] tier16BiomesDifficultyArray = {"0", "0", "0", "0", "0", "0", "0", "0"};
	
	public static String[] excludedBiomesArray = {"minecraft:river", "minecraft:cold_beach", "minecraft:beaches", "minecraft:frozen_river"};
	public static float[] magicResistArray = new float[16];
	
	public static File configFileTemp;

	public static float rareSpawnXPboost = 2;
	public static String[] rareSpawnArrayRaw = {
			"zombie|Poque the Dreadful|100|rareSpawnBiome|minecraft:taiga|addhealth|20|drops|minecraft:grass|potion|regeneration", 
			"enderman|Slim|100|rareSpawnBiome|minecraft:taiga|addhealth|20|drops|minecraft:ender_pearl|potion|strength"};
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
		enableBiomeDifficulty= config.getBoolean("Enable Biome Difficulty Module?", "0Main", true, "Enables Biome Difficulty module. Note, requires configuration below.");
		enableRarespawn = config.getBoolean("Enable Rarespawn Module?", "0Main", true, "Enables rarespawn module. Note, requires configuration below.");
		disableCategories = config.getBoolean("Disable Categories?", "0Main", false, "Setting this value to true will disable all biome checking, categorization, and subtitles (except ignored to help prevent biome spam). Instead all biome names will display as they are approached in aqua and with no subtitle. Nice for big modpacks with tons and tons of biomes you don't feel like configuring and you just want to know where you're going. See what I did there? :)");
		enableDebug = config.getBoolean("Enable Debug?", "0Main", false, "Enabling this will write some findings to the log to help with debugging config. Can be chatty, so use with care.");
		enableBiomeNav = config.getBoolean("Enable Biome Navigation Display?", "0Main", true, "Enabling this turn on the displaying of biome titles as you transition between them.");
		enableRarespawnDebug = config.getBoolean("Enable Debug for Rarespawn logic?", "0Main", true, "Enabling this will write some findings to the log to help with debugging config. Can be chatty, so use with care.");
		enableDebugBiomeHUD = config.getBoolean("Enable Debug for biome hud?", "0Main", false, "Enabling this will write some findings to the log to help with debugging config. Can be chatty, so use with care.");
		enableBiomeDifficultyDebug = config.getBoolean("Enable Debug for Biome Difficulty?", "0Main", false, "Enabling this will write some findings to the log to help with debugging config. Can be chatty, so use with care.");
		
		timeFadeIn = config.getInt("Fade in Time", "Title Display Timings", timeFadeIn, 0, 100, "Amount of time it takes for the title to fade in. 0 will appear instantly and higher numbers will fade in more slowly. Whole number value measured in ticks (20 per second)");
		displayTime = config.getInt("Display Time", "Title Display Timings", displayTime, 0, 100, "Amount of time the title displays at full opacity after fading in. 0 will instantly begin the fade out action and higher numbers will display for longer time. Whole number value measured in ticks (20 per second)");
		timeFadeOut = config.getInt("Fade out Time", "Title Display Timings", timeFadeOut, 0, 100, "Amount of time is takes for the title to fade out after displaying. 0 will instantly disappear after displaying and higher numbers will fade out more slowly. Whole number value measured in ticks (20 per second)");
		displayWait = config.getInt("Time between Updates", "Title Display Timings", displayWait, 20, 1200, "Amount of time to wait until displaying the title again. Lower numbers will display updates faster/more often with higher numbers displaying less often. Whole number value measured in ticks (20 per second)");
		
		rareSpawnArrayRaw = config.getStringList("Rarespawn Entries", "Rarespawn", rareSpawnArrayRaw, "Enter 1 line per mob starting with the name of the mob, then name, then the percent chance of spawning, followed by attritbute name and value pairs. Can also handle rareSpawnBiome if you only want the rarespawn to appear in a single biome type");
		rareSpawnXPboost = config.getFloat("Rarespawn XP Booster", "Rarespawn", rareSpawnXPboost, 1f, 1000.0f, "When a Rarespawn is killed, it will drop its default XP value times the number provided here. For example, entering 2 gives you double (times 2) the amount of XP Accepts float values.");
		
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
		tier16Subtitle = config.getString("Category 16 Title", "Biome Category Names", tier16Subtitle, "Words that display at the subtitle of Category 16 biomes.");

		tier1BiomesArray = config.getStringList("Category 1 Biomes (aqua)", "Biome Sets", tier1BiomesArray, "Contains an array of biome name strings to display in this category.");
		tier2BiomesArray = config.getStringList("Category 2 Biomes (green)", "Biome Sets", tier2BiomesArray, "Contains an array of biome name strings to display in this category.");
		tier3BiomesArray = config.getStringList("Category 3 Biomes (yellow)", "Biome Sets", tier3BiomesArray, "Contains an array of biome name strings to display in this category.");
		tier4BiomesArray = config.getStringList("Category 4 Biomes (gold)", "Biome Sets", tier4BiomesArray, "Contains an array of biome name strings to display in this category.");
		tier5BiomesArray = config.getStringList("Category 5 Biomes (red)", "Biome Sets", tier5BiomesArray, "Contains an array of biome name strings to display in this category.");
		tier6BiomesArray = config.getStringList("Category 6 Biomes (dark red)", "Biome Sets", tier6BiomesArray, "Contains an array of biome name strings to display in this category.");
		tier7BiomesArray = config.getStringList("Category 7 Biomes (light purple)", "Biome Sets", tier7BiomesArray, "Contains an array of biome name strings to display in this category.");
		tier8BiomesArray = config.getStringList("Category 8 Biomes (dark_green)", "Biome Sets", tier8BiomesArray, "Contains an array of biome name strings to display in this category.");
		tier9BiomesArray = config.getStringList("Category 9 Biomes (dark_aqua)", "Biome Sets", tier9BiomesArray, "Contains an array of biome name strings to display in this category.");
		tier10BiomesArray = config.getStringList("Category 10 Biomes (dark_blue)", "Biome Sets", tier10BiomesArray, "Contains an array of biome name strings to display in this category.");
		tier11BiomesArray = config.getStringList("Category 11 Biomes (dark_purple)", "Biome Sets", tier11BiomesArray, "Contains an array of biome name strings to display in this category.");
		tier12BiomesArray = config.getStringList("Category 12 Biomes (white)", "Biome Sets", tier12BiomesArray, "Contains an array of biome name strings to display in this category.");
		tier13BiomesArray = config.getStringList("Category 13 Biomes (gray)", "Biome Sets", tier13BiomesArray, "Contains an array of biome name strings to display in this category.");
		tier14BiomesArray = config.getStringList("Category 14 Biomes (dark_grey)", "Biome Sets", tier14BiomesArray, "Contains an array of biome name strings to display in this category.");
		tier15BiomesArray = config.getStringList("Category 15 Biomes (black)", "Biome Sets", tier15BiomesArray, "Contains an array of biome name strings to display in this category.");
		tier16BiomesArray = config.getStringList("Category 16 Biomes (blue)", "Biome Sets", tier16BiomesArray, "Contains an array of biome name strings to display in this category.");
		
		tier1BiomesDifficultyArray = config.getStringList("Category 1 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier1BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add armor toughness, add knockback resist, add magic resist, add follow range] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		tier2BiomesDifficultyArray = config.getStringList("Category 2 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier2BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add armor toughness, add knockback resist, add magic resist, add follow range] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		tier3BiomesDifficultyArray = config.getStringList("Category 3 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier3BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add armor toughness, add knockback resist, add magic resist, add follow range] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		tier4BiomesDifficultyArray = config.getStringList("Category 4 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier4BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add armor toughness, add knockback resist, add magic resist, add follow range] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		tier5BiomesDifficultyArray = config.getStringList("Category 5 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier5BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add armor toughness, add knockback resist, add magic resist, add follow range] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		tier6BiomesDifficultyArray = config.getStringList("Category 6 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier6BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add armor toughness, add knockback resist, add magic resist, add follow range] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		tier7BiomesDifficultyArray = config.getStringList("Category 7 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier7BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add armor toughness, add knockback resist, add magic resist, add follow range] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		tier8BiomesDifficultyArray = config.getStringList("Category 8 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier8BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add armor toughness, add knockback resist, add magic resist, add follow range] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		tier9BiomesDifficultyArray = config.getStringList("Category 9 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier9BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add armor toughness, add knockback resist, add magic resist, add follow range] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		tier10BiomesDifficultyArray = config.getStringList("Category 10 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier10BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add armor toughness, add knockback resist, add magic resist, add follow range] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		tier11BiomesDifficultyArray = config.getStringList("Category 11 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier11BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add armor toughness, add knockback resist, add magic resist, add follow range] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		tier12BiomesDifficultyArray = config.getStringList("Category 12 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier12BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add armor toughness, add knockback resist, add magic resist, add follow range] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		tier13BiomesDifficultyArray = config.getStringList("Category 13 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier13BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add armor toughness, add knockback resist, add magic resist, add follow range] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		tier14BiomesDifficultyArray = config.getStringList("Category 14 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier14BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add armor toughness, add knockback resist, add magic resist, add follow range] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		tier15BiomesDifficultyArray = config.getStringList("Category 15 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier15BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add armor toughness, add knockback resist, add magic resist, add follow range] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
		tier16BiomesDifficultyArray = config.getStringList("Category 16 Biome Mob Difficulty Modifiers", "Biome Mob Difficulty", tier16BiomesDifficultyArray, "Contains an array of floats structured as [add health, add damage, add armor, add armor toughness, add knockback resist, add magic resist, add follow range] (must be in that order). Use 0 for skipping the entry, do not leave blank. Value read as a float, so decimals are cool.");
				
		excludedBiomesArray = config.getStringList("Ignored Biomes", "Biome Sets", excludedBiomesArray, "Contains an array of biome name strings that will be ignored by this mod's features." );
				
		if (config.hasChanged()) config.save();
		if (enableRarespawn) parseRarespawnStringToMap();
		if (enableBiomeDifficulty) parseMagicResistValues();
		parseBiomeResLoc();
		
	}

	public static void parseBiomeResLoc() {	
		for (String resLoc : tier1BiomesArray) {
			tier1BiomeResLoc.add(new ResourceLocation(resLoc));
		}
		System.out.println("WAIG loaded biomes:" + tier2BiomeResLoc.toString());
		for (String resLoc : tier2BiomesArray) {
			tier2BiomeResLoc.add(new ResourceLocation(resLoc));
		}
		System.out.println("WAIG loaded biomes:" + tier3BiomeResLoc.toString());
		for (String resLoc : tier3BiomesArray) {
			tier3BiomeResLoc.add(new ResourceLocation(resLoc));
		}
		System.out.println("WAIG loaded biomes:" + tier4BiomeResLoc.toString());
		for (String resLoc : tier4BiomesArray) {
			tier4BiomeResLoc.add(new ResourceLocation(resLoc));
		}
		System.out.println("WAIG loaded biomes:" + tier5BiomeResLoc.toString());
		for (String resLoc : tier5BiomesArray) {
			tier5BiomeResLoc.add(new ResourceLocation(resLoc));
		}
		System.out.println("WAIG loaded biomes:" + tier6BiomeResLoc.toString());
		for (String resLoc : tier6BiomesArray) {
			tier6BiomeResLoc.add(new ResourceLocation(resLoc));
		}
		System.out.println("WAIG loaded biomes:" + tier7BiomeResLoc.toString());
		for (String resLoc : tier7BiomesArray) {
			tier7BiomeResLoc.add(new ResourceLocation(resLoc));
		}
		System.out.println("WAIG loaded biomes:" + tier8BiomeResLoc.toString());
		for (String resLoc : tier8BiomesArray) {
			tier8BiomeResLoc.add(new ResourceLocation(resLoc));
		}
		System.out.println("WAIG loaded biomes:" + tier9BiomeResLoc.toString());
		for (String resLoc : tier9BiomesArray) {
			tier9BiomeResLoc.add(new ResourceLocation(resLoc));
		}
		System.out.println("WAIG loaded biomes:" + tier10BiomeResLoc.toString());
		for (String resLoc : tier10BiomesArray) {
			tier10BiomeResLoc.add(new ResourceLocation(resLoc));
		}
		System.out.println("WAIG loaded biomes:" + tier11BiomeResLoc.toString());
		for (String resLoc : tier11BiomesArray) {
			tier11BiomeResLoc.add(new ResourceLocation(resLoc));
		}
		System.out.println("WAIG loaded biomes:" + tier12BiomeResLoc.toString());
		for (String resLoc : tier12BiomesArray) {
			tier12BiomeResLoc.add(new ResourceLocation(resLoc));
		}
		System.out.println("WAIG loaded biomes:" + tier13BiomeResLoc.toString());
		for (String resLoc : tier13BiomesArray) {
			tier13BiomeResLoc.add(new ResourceLocation(resLoc));
		}
		System.out.println("WAIG loaded biomes:" + tier14BiomeResLoc.toString());
		for (String resLoc : tier14BiomesArray) {
			tier14BiomeResLoc.add(new ResourceLocation(resLoc));
		}
		System.out.println("WAIG loaded biomes:" + tier15BiomeResLoc.toString());
		for (String resLoc : tier15BiomesArray) {
			tier15BiomeResLoc.add(new ResourceLocation(resLoc));
		}
		System.out.println("WAIG loaded biomes:" + tier16BiomeResLoc.toString());
		for (String resLoc : tier16BiomesArray) {
			tier16BiomeResLoc.add(new ResourceLocation(resLoc));
		}
		System.out.println("WAIG loaded biomes:" + tier1BiomeResLoc.toString());
	}
	public static void parseMagicResistValues() {
		
		magicResistArray[0] = Float.parseFloat(tier1BiomesDifficultyArray[5]);
		magicResistArray[0] = Float.parseFloat(tier2BiomesDifficultyArray[5]);
		magicResistArray[0] = Float.parseFloat(tier3BiomesDifficultyArray[5]);
		magicResistArray[0] = Float.parseFloat(tier4BiomesDifficultyArray[5]);
		magicResistArray[0] = Float.parseFloat(tier5BiomesDifficultyArray[5]);
		magicResistArray[0] = Float.parseFloat(tier6BiomesDifficultyArray[5]);
		magicResistArray[0] = Float.parseFloat(tier7BiomesDifficultyArray[5]);
		magicResistArray[0] = Float.parseFloat(tier8BiomesDifficultyArray[5]);
		magicResistArray[0] = Float.parseFloat(tier9BiomesDifficultyArray[5]);
		magicResistArray[0] = Float.parseFloat(tier10BiomesDifficultyArray[5]);
		magicResistArray[0] = Float.parseFloat(tier11BiomesDifficultyArray[5]);
		magicResistArray[0] = Float.parseFloat(tier12BiomesDifficultyArray[5]);
		magicResistArray[0] = Float.parseFloat(tier13BiomesDifficultyArray[5]);
		magicResistArray[0] = Float.parseFloat(tier14BiomesDifficultyArray[5]);
		magicResistArray[0] = Float.parseFloat(tier15BiomesDifficultyArray[5]);
		magicResistArray[0] = Float.parseFloat(tier16BiomesDifficultyArray[5]);
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
			if (enableDebug == true) {
				System.out.println(mobKey + " - FORMOB");
			}
			tokenCursor++;
			String mapKey;
			String mapValue;
					
			//get the second entry and assign it as spawnname key
			mapKey = "spawnname";
			mapValue = token[tokenCursor];
			tokenCursor++;			

			mapSet.put(mapKey, mapValue);
			if (enableDebug == true) {
				System.out.println(mapKey + " - namekey");
				System.out.println(mapValue + " - value");
				System.out.println(mapSet.toString());
			}

			//get the third entry and assign it with the spawn% key
			mapKey = "spawnchance";
			mapValue = token[tokenCursor];
			tokenCursor++;

			mapSet.put(mapKey, mapValue);
			mapSet.toString();
			
			if (enableDebug == true) {
				System.out.println(mapKey + " - chancekey");
				System.out.println(mapValue + " - value");
				System.out.println(mapSet.toString());
			}
			
			//count and loop through all the remaining values
			while(tokenCursor < token.length) {
				mapKey = token[tokenCursor];
				tokenCursor++;
				mapValue = token[tokenCursor];
				tokenCursor++;
				
				if (enableDebug == true) {
					System.out.println(mapKey + " - key");
					System.out.println(mapValue + " - value");
					System.out.println(mapSet.toString());
				}
				mapSet.put(mapKey, mapValue);
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
