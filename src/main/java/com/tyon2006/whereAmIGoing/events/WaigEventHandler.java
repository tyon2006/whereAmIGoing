package com.tyon2006.whereAmIGoing.events;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.tyon2006.whereAmIGoing.config.ConfigManager;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.ResourceLocation;

public class WaigEventHandler {

	public String colorizedBiomeNameString = "";
	public String subTitle = "";
	public String lastBiome = "";
	public String lastLastBiome = "";
	
	/* //code refactor no longer needs these?
	public static List<String> tier1BiomesArrayList = new ArrayList <String>(Arrays.asList(ConfigManager.tier1BiomesArray));
	public static List<String> tier2BiomesArrayList = new ArrayList <String>(Arrays.asList(ConfigManager.tier2BiomesArray));
	public static List<String> tier3BiomesArrayList = new ArrayList <String>(Arrays.asList(ConfigManager.tier3BiomesArray));
	public static List<String> tier4BiomesArrayList = new ArrayList <String>(Arrays.asList(ConfigManager.tier4BiomesArray));
	public static List<String> tier5BiomesArrayList = new ArrayList <String>(Arrays.asList(ConfigManager.tier5BiomesArray));
	public static List<String> tier6BiomesArrayList = new ArrayList <String>(Arrays.asList(ConfigManager.tier6BiomesArray));
	public static List<String> tier7BiomesArrayList = new ArrayList <String>(Arrays.asList(ConfigManager.tier7BiomesArray));
	public static List<String> tier8BiomesArrayList = new ArrayList <String>(Arrays.asList(ConfigManager.tier8BiomesArray));
	public static List<String> tier9BiomesArrayList = new ArrayList <String>(Arrays.asList(ConfigManager.tier9BiomesArray));
	public static List<String> tier10BiomesArrayList = new ArrayList <String>(Arrays.asList(ConfigManager.tier10BiomesArray));
	public static List<String> tier11BiomesArrayList = new ArrayList <String>(Arrays.asList(ConfigManager.tier11BiomesArray));
	public static List<String> tier12BiomesArrayList = new ArrayList <String>(Arrays.asList(ConfigManager.tier12BiomesArray));
	public static List<String> tier13BiomesArrayList = new ArrayList <String>(Arrays.asList(ConfigManager.tier13BiomesArray));
	public static List<String> tier14BiomesArrayList = new ArrayList <String>(Arrays.asList(ConfigManager.tier14BiomesArray));
	public static List<String> tier15BiomesArrayList = new ArrayList <String>(Arrays.asList(ConfigManager.tier15BiomesArray));
	public static List<String> tier16BiomesArrayList = new ArrayList <String>(Arrays.asList(ConfigManager.tier16BiomesArray));
	*/
	
	public static List<String> excludedBiomesArrayList = new ArrayList <String>(Arrays.asList(ConfigManager.excludedBiomesArray));
	
	int ticksExisted = 100;
	int nextTrigger = ticksExisted + ConfigManager.displayWait;

	@SubscribeEvent
	public void checkBiomeOnClientTick(ClientTickEvent event) {

		if(Minecraft.getMinecraft().player == null) return; //it will crash on title screen without this line.
	 	
		BlockPos playerPosition = Minecraft.getMinecraft().player.getPosition(); //get the position of the character
		Biome currentBiome = Minecraft.getMinecraft().player.getEntityWorld().getBiomeForCoordsBody(playerPosition); //get the biome from the character position
		String biomeNameString = currentBiome.getBiomeName(); 
		
		ResourceLocation biomeResLoc = currentBiome.getRegistryName();
		//String currentPhase = event.phase.toString(); //deprecated? by event.phase == Phase.END
		ticksExisted = Minecraft.getMinecraft().player.ticksExisted; 
		
		if ((nextTrigger < ticksExisted) 
				&& event.phase == Phase.END //does this check work? if so delete the currentPhase line
				//&& event.side.isClient() == true) 
				&& biomeNameString != lastBiome
				&& biomeNameString != lastLastBiome) {	

			//if checking is disabled, set AQUA and return
			if(ConfigManager.disableCategories == true) {
				
				subTitle = "";
				
				if (excludedBiomesArrayList.contains(biomeNameString)) {
					if (ConfigManager.enableDebugBiomeHUD == true) System.out.println("Skipping biome: " + biomeNameString);
					nextTrigger = ticksExisted + ConfigManager.displayWait;
					return;
				} 
				
				colorizedBiomeNameString = TextFormatting.AQUA + biomeNameString;
				Minecraft.getMinecraft().ingameGUI.displayTitle(null, subTitle, 0, 0, 0);
				Minecraft.getMinecraft().ingameGUI.displayTitle(colorizedBiomeNameString, subTitle, ConfigManager.timeFadeIn, ConfigManager.displayTime, ConfigManager.timeFadeOut); //with TextFormatting

				if (ConfigManager.enableDebugBiomeHUD == true) {
					System.out.println("Detected biome: " + biomeNameString);
					System.out.println("Found Biome change on Tick: " + ticksExisted); 			 
				}

				lastBiome = biomeNameString;
				biomeNameString = "";
				nextTrigger = ticksExisted + ConfigManager.displayWait;
				return;
			}
			
			//if checking is enabled, colorize
			colorizeTextFromBiome(biomeResLoc, biomeNameString);

			//The sub ends up being blank if I don't structure the next 2 lines like this. 
			Minecraft.getMinecraft().ingameGUI.displayTitle(null, subTitle, 0, 0, 0);
			Minecraft.getMinecraft().ingameGUI.displayTitle(colorizedBiomeNameString, subTitle, ConfigManager.timeFadeIn, ConfigManager.displayTime, ConfigManager.timeFadeOut); //with TextFormatting
			
			//logs
			if (ConfigManager.enableDebugBiomeHUD == true) {
				System.out.println("Current biome: " + biomeNameString); 
				System.out.println("Last biome: " + lastBiome); 
				System.out.println("Last last biome: " + lastLastBiome); 
			}
			
			if(biomeNameString != lastBiome
					&& biomeNameString != lastLastBiome) {
				lastLastBiome = lastBiome;
				lastBiome = biomeNameString;
			}

			//cleanup
			biomeNameString = "";
			subTitle = "";		
			nextTrigger = ticksExisted + ConfigManager.displayWait;
		}
	}
	
	public void colorizeTextFromBiome(ResourceLocation biomeResLoc, String biomeNameString) {
		
		//skip if excluded
		if (ConfigManager.excludedBiomeResLoc.contains(biomeResLoc)) {
			System.out.println("Skipping biome: " + biomeNameString);
			colorizedBiomeNameString = null;
			subTitle = null;
			nextTrigger = ticksExisted + ConfigManager.displayWait;
			return;
		}
		//colorize if not
		if (ConfigManager.tier1BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.AQUA + biomeNameString;
			subTitle = ConfigManager.tier1Subtitle;
			return;
		} 
		else if (ConfigManager.tier2BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.GREEN + biomeNameString;
			subTitle = ConfigManager.tier2Subtitle;
			return;
		} 
		else if (ConfigManager.tier3BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.YELLOW + biomeNameString;
			subTitle = ConfigManager.tier3Subtitle;
			return;
		} 
		else if (ConfigManager.tier4BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.GOLD + biomeNameString;
			subTitle = ConfigManager.tier4Subtitle;
			return;
		} 
		else if (ConfigManager.tier5BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.RED + biomeNameString;
			subTitle = ConfigManager.tier5Subtitle;
			return;
		} 
		else if (ConfigManager.tier6BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.DARK_RED + biomeNameString;
			subTitle = ConfigManager.tier6Subtitle;
			return;
		}  
		else if (ConfigManager.tier7BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.LIGHT_PURPLE + biomeNameString;
			subTitle = ConfigManager.tier7Subtitle;
			return;
		}  
		else if (ConfigManager.tier8BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.DARK_GREEN + biomeNameString;
			subTitle = ConfigManager.tier8Subtitle;
			return;
		} 
		else if (ConfigManager.tier9BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.DARK_AQUA + biomeNameString;
			subTitle = ConfigManager.tier9Subtitle;
			return;
		} 
		else if (ConfigManager.tier10BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.DARK_BLUE + biomeNameString;
			subTitle = ConfigManager.tier10Subtitle;
			return;
		} 
		else if (ConfigManager.tier11BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.DARK_PURPLE + biomeNameString;
			subTitle = ConfigManager.tier11Subtitle;
			return;
		} 
		else if (ConfigManager.tier12BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.WHITE + biomeNameString;
			subTitle = ConfigManager.tier12Subtitle;
			return;
		} 
		else if (ConfigManager.tier13BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.GRAY + biomeNameString;
			subTitle = ConfigManager.tier13Subtitle;
			return;
		}  
		else if (ConfigManager.tier14BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.DARK_GRAY + biomeNameString;
			subTitle = ConfigManager.tier14Subtitle;
			return;
		} 
		else if (ConfigManager.tier15BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.BLACK + biomeNameString;
			subTitle = ConfigManager.tier15Subtitle;
			return;
		} 
		else if (ConfigManager.tier16BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.BLUE + biomeNameString;
			subTitle = ConfigManager.tier16Subtitle;
			return;
		} 
		else {
			colorizedBiomeNameString = null;
			subTitle = null;
			nextTrigger = ticksExisted + ConfigManager.displayWait;
			return;
		}
	}
}
