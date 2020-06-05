package com.tyon2006.whereAmIGoing;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class ConfigManager {
	
	public static Configuration config;
	public static int timeFadeIn = 30;
	public static int displayTime = 15;
	public static int timeFadeOut = 30;
	
	public static String[] tier1BiomesArray = {"Forest"};
	public static String tier1Subtitle = "Tier 1 Biome";

	public static String[] tier2BiomesArray = {"Roofed Forest"};
	public static String tier2Subtitle = "Tier 2 Biome";
	
	public static String[] tier3BiomesArray = {"Birch Forest"};
	public static String tier3Subtitle = "Tier 3 Biome";
	
	public static String[] tier4BiomesArray = {"Plains"};
	public static String tier4Subtitle = "Tier 4 Biome";
	
	public static String[] tier5BiomesArray = {"Extreme Hills"};
	public static String tier5Subtitle = "Tier 5 Biome";
	
	public static String[] tier6BiomesArray = {"Extreme Hills+"};
	public static String tier6Subtitle = "Tier 6 Biome";
	
	public static String[] tier7BiomesArray = {"Extreme Hills+"};
	public static String tier7Subtitle = "Tier 7 Biome";
	
	public static String[] excludedBiomesArray = {"River"};
	
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

		tier1BiomesArray = config.getStringList("Category 1 Biomes", "Biome Sets", tier1BiomesArray, "Contains an array of biome name strings to display in category 1 biomes.");
		tier2BiomesArray = config.getStringList("Category 2 Biomes", "Biome Sets", tier2BiomesArray, "Contains an array of biome name strings to display in category 2 biomes.");
		tier3BiomesArray = config.getStringList("Category 3 Biomes", "Biome Sets", tier3BiomesArray, "Contains an array of biome name strings to display in category 3 biomes.");
		tier4BiomesArray = config.getStringList("Category 4 Biomes", "Biome Sets", tier4BiomesArray, "Contains an array of biome name strings to display in category 4 biomes.");
		tier5BiomesArray = config.getStringList("Category 5 Biomes", "Biome Sets", tier5BiomesArray, "Contains an array of biome name strings to display in category 5 biomes.");
		tier6BiomesArray = config.getStringList("Category 6 Biomes", "Biome Sets", tier6BiomesArray, "Contains an array of biome name strings to display in category 6 biomes.");
		tier7BiomesArray = config.getStringList("Category 7 Biomes", "Biome Sets", tier7BiomesArray, "Contains an array of biome name strings to display in category 7 biomes.");
		excludedBiomesArray = config.getStringList("Ignored Biomes", "Biome Sets", excludedBiomesArray, "Contains an array of biome name strings that will be ignored by this mod's features." );
		
		if (config.hasChanged())
		{
			config.save();
		}

	}
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if(event.getModID().equalsIgnoreCase(WhereAmIGoing.MODID))
		{
			load();
		}
	}
}
