package com.tyon2006.whereAmIGoing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.tyon2006.whereAmIGoing.ConfigManager;

public class WaigEventHandler {

	public String subTitle = "";
	public String lastBiome = "";
	
	public static List<String> tier1BiomesArrayList = new ArrayList <String>(Arrays.asList(ConfigManager.tier1BiomesArray));
	public static List<String> tier2BiomesArrayList = new ArrayList <String>(Arrays.asList(ConfigManager.tier2BiomesArray));
	public static List<String> tier3BiomesArrayList = new ArrayList <String>(Arrays.asList(ConfigManager.tier3BiomesArray));
	public static List<String> tier4BiomesArrayList = new ArrayList <String>(Arrays.asList(ConfigManager.tier4BiomesArray));
	public static List<String> tier5BiomesArrayList = new ArrayList <String>(Arrays.asList(ConfigManager.tier5BiomesArray));
	public static List<String> tier6BiomesArrayList = new ArrayList <String>(Arrays.asList(ConfigManager.tier6BiomesArray));
	public static List<String> tier7BiomesArrayList = new ArrayList <String>(Arrays.asList(ConfigManager.tier7BiomesArray));
	public static List<String> excludedBiomesArrayList = new ArrayList <String>(Arrays.asList(ConfigManager.excludedBiomesArray));
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void checkBiomeOnClientTick(ClientTickEvent event) {
	 	if(Minecraft.getMinecraft().player == null) return; //it will crash on title screen without this line.
	 	
		BlockPos playerPosition = Minecraft.getMinecraft().player.getPosition(); //get the position of the character
		Biome currentBiome = Minecraft.getMinecraft().player.getEntityWorld().getBiomeForCoordsBody(playerPosition); //get the biome from the character position
		String biomeNameString = currentBiome. getBiomeName(); //get the biome name from the biome object
		String colorizedBiomeNameString = biomeNameString;

		String currentPhase = event.phase.toString(); //get the phase, only fire on end phases
		int ticksExisted = Minecraft.getMinecraft().player.ticksExisted; //get the number of ticks the player has existed for modulus
		
		if (ticksExisted > 100 && ticksExisted % 100 == 0 && currentPhase == "END" && event.side.isClient() == true && biomeNameString != lastBiome){ //mod 100, 20 ticks per second, one check per 5 seconds.	
			
			if (tier1BiomesArrayList.contains(biomeNameString)) {
				colorizedBiomeNameString = TextFormatting.AQUA + biomeNameString;
				subTitle = ConfigManager.tier1Subtitle;
			} 
			else if (tier2BiomesArrayList.contains(biomeNameString)) {
				colorizedBiomeNameString = TextFormatting.GREEN + biomeNameString;
				subTitle = ConfigManager.tier2Subtitle;
			} 
			else if (tier3BiomesArrayList.contains(biomeNameString)) {
				colorizedBiomeNameString = TextFormatting.YELLOW + biomeNameString;
				subTitle = ConfigManager.tier3Subtitle;
			} 
			else if (tier4BiomesArrayList.contains(biomeNameString)) {
				colorizedBiomeNameString = TextFormatting.GOLD + biomeNameString;
				subTitle = ConfigManager.tier4Subtitle;
			} 
			else if (tier5BiomesArrayList.contains(biomeNameString)) {
				colorizedBiomeNameString = TextFormatting.RED + biomeNameString;
				subTitle = ConfigManager.tier5Subtitle;
			} 
			else if (tier6BiomesArrayList.contains(biomeNameString)) {
				colorizedBiomeNameString = TextFormatting.DARK_RED + biomeNameString;
				subTitle = ConfigManager.tier6Subtitle;
			}  
			else if (tier7BiomesArrayList.contains(biomeNameString)) {
				colorizedBiomeNameString = TextFormatting.LIGHT_PURPLE + biomeNameString;
				subTitle = ConfigManager.tier7Subtitle;
			}  
			else if (excludedBiomesArrayList.contains(biomeNameString)) {
				System.out.println("Skipping biome: " + biomeNameString);
				return;
			} 
			else {
				colorizedBiomeNameString = TextFormatting.OBFUSCATED + biomeNameString;
				subTitle = "Unknown Biome";
			}
			
			//why the hell do I have to assign the subtitle before the title? The sub ends up being blank if I don't structure it like this. Cmon minecraft. 
			Minecraft.getMinecraft().ingameGUI.displayTitle(null, subTitle, 0, 0, 0);
			Minecraft.getMinecraft().ingameGUI.displayTitle(colorizedBiomeNameString, subTitle, ConfigManager.timeFadeIn, ConfigManager.displayTime, ConfigManager.timeFadeOut); //with TextFormatting
			
			//logs
			System.out.println("Detected biome: " + biomeNameString);
			System.out.println("With subtitle: " + subTitle);
			System.out.println("Found Biome change on Tick: " + ticksExisted); 			
			System.out.println("Last biome: " + lastBiome); 
			System.out.println("Current biome: " + biomeNameString); 
			
			//cleanup
			//lastLastBiome = lastBiome; 
			lastBiome = biomeNameString;
			biomeNameString = "";
			subTitle = "";		
		}
	}
}
