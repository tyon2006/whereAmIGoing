package com.tyon2006.whereAmIGoing.events;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyon2006.whereAmIGoing.config.ConfigManager;

import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedSpawnerEntity;
import java.util.Random;
import net.minecraft.init.MobEffects;
import net.minecraft.world.BossInfo;

public class WaigEventHandler {


	public String subTitle = "";
	public String lastBiome = "";
	public String lastLastBiome = "";
	
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
	
	public static List<String> excludedBiomesArrayList = new ArrayList <String>(Arrays.asList(ConfigManager.excludedBiomesArray));

	int ticksExisted = 100;
	int nextTrigger = ticksExisted + ConfigManager.displayWait;

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void checkBiomeOnClientTick(ClientTickEvent event) {

		if(Minecraft.getMinecraft().player == null) return; //it will crash on title screen without this line.
	 	
		BlockPos playerPosition = Minecraft.getMinecraft().player.getPosition(); //get the position of the character
		Biome currentBiome = Minecraft.getMinecraft().player.getEntityWorld().getBiomeForCoordsBody(playerPosition); //get the biome from the character position
		
		String biomeNameString = currentBiome.getBiomeName(); 
		String colorizedBiomeNameString = biomeNameString;

		String currentPhase = event.phase.toString();
		ticksExisted = Minecraft.getMinecraft().player.ticksExisted;
		
		if ((nextTrigger < ticksExisted) 
				&& (currentPhase == "END"
				//&& event.side.isClient() == true) 
				&& biomeNameString != lastBiome)
				&& biomeNameString != lastLastBiome){	

			//if checking is disabled
			if(ConfigManager.disableCategories == true) {
				
				subTitle = "";
				
				if (excludedBiomesArrayList.contains(biomeNameString)) {
					System.out.println("Skipping biome: " + biomeNameString);
					nextTrigger = ticksExisted + ConfigManager.displayWait;
					return;
				} 
				
				colorizedBiomeNameString = TextFormatting.AQUA + biomeNameString;
				Minecraft.getMinecraft().ingameGUI.displayTitle(null, subTitle, 0, 0, 0);
				Minecraft.getMinecraft().ingameGUI.displayTitle(colorizedBiomeNameString, subTitle, ConfigManager.timeFadeIn, ConfigManager.displayTime, ConfigManager.timeFadeOut); //with TextFormatting

				if (ConfigManager.enableDebug == true) {
					System.out.println("Detected biome: " + biomeNameString);
					System.out.println("Found Biome change on Tick: " + ticksExisted); 			 
				}

				lastBiome = biomeNameString;
				biomeNameString = "";
				nextTrigger = ticksExisted + ConfigManager.displayWait;
				return;
			}
			
			//if checking is enabled
			if (excludedBiomesArrayList.contains(biomeNameString)) {
				System.out.println("Skipping biome: " + biomeNameString);
				nextTrigger = ticksExisted + ConfigManager.displayWait;
				return;
			} 
			else if (tier1BiomesArrayList.contains(biomeNameString)) {
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
			else if (tier8BiomesArrayList.contains(biomeNameString)) {
				colorizedBiomeNameString = TextFormatting.DARK_GREEN + biomeNameString;
				subTitle = ConfigManager.tier8Subtitle;
			} 
			else if (tier9BiomesArrayList.contains(biomeNameString)) {
				colorizedBiomeNameString = TextFormatting.DARK_AQUA + biomeNameString;
				subTitle = ConfigManager.tier9Subtitle;
			} 
			else if (tier10BiomesArrayList.contains(biomeNameString)) {
				colorizedBiomeNameString = TextFormatting.DARK_BLUE + biomeNameString;
				subTitle = ConfigManager.tier10Subtitle;
			} 
			else if (tier11BiomesArrayList.contains(biomeNameString)) {
				colorizedBiomeNameString = TextFormatting.DARK_PURPLE + biomeNameString;
				subTitle = ConfigManager.tier11Subtitle;
			} 
			else if (tier12BiomesArrayList.contains(biomeNameString)) {
				colorizedBiomeNameString = TextFormatting.WHITE + biomeNameString;
				subTitle = ConfigManager.tier12Subtitle;
			} 
			else if (tier13BiomesArrayList.contains(biomeNameString)) {
				colorizedBiomeNameString = TextFormatting.GRAY + biomeNameString;
				subTitle = ConfigManager.tier13Subtitle;
			}  
			else if (tier14BiomesArrayList.contains(biomeNameString)) {
				colorizedBiomeNameString = TextFormatting.DARK_GRAY + biomeNameString;
				subTitle = ConfigManager.tier14Subtitle;
			} 
			else if (tier15BiomesArrayList.contains(biomeNameString)) {
				colorizedBiomeNameString = TextFormatting.BLACK + biomeNameString;
				subTitle = ConfigManager.tier15Subtitle;
			} 
			else if (tier16BiomesArrayList.contains(biomeNameString)) {
				colorizedBiomeNameString = TextFormatting.BLUE + biomeNameString;
				subTitle = ConfigManager.tier16Subtitle;
			} 
			else {
				colorizedBiomeNameString = TextFormatting.OBFUSCATED + biomeNameString;
				subTitle = "Unknown Biome";
			}
			
			//The sub ends up being blank if I don't structure it like this. 
			Minecraft.getMinecraft().ingameGUI.displayTitle(null, subTitle, 0, 0, 0);
			Minecraft.getMinecraft().ingameGUI.displayTitle(colorizedBiomeNameString, subTitle, ConfigManager.timeFadeIn, ConfigManager.displayTime, ConfigManager.timeFadeOut); //with TextFormatting
			
			//logs
			if (ConfigManager.enableDebug == true) {
				System.out.println("Last biome: " + lastBiome); 
				System.out.println("Last last biome: " + lastLastBiome); 
				System.out.println("Current biome: " + biomeNameString); 
			}

			//cleanup
			lastLastBiome = lastBiome;
			lastBiome = biomeNameString;
			biomeNameString = "";
			subTitle = "";		
			nextTrigger = ticksExisted + ConfigManager.displayWait;
		}
	}
}
