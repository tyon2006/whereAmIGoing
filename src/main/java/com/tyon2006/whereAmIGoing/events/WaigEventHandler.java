package com.tyon2006.whereAmIGoing.events;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tyon2006.whereAmIGoing.config.ConfigManager;

import gnu.trove.impl.Constants;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedSpawnerEntity;
import java.util.Random;
import net.minecraft.init.MobEffects;

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

	int ticksExisted = 100;
	int nextTrigger = ticksExisted + ConfigManager.displayWait;
	public static Map<String, String> mobAttMap = new HashMap<String, String>();
	
	/*
	@SubscribeEvent
	public void onMobJoinSpawnCheck(LivingSpawnEvent event) {
		
		System.out.println("LivingSpawn We trying" + event.getEntity().getEntityId());

		if (event.getEntity() instanceof EntityLiving) {
			EntityLiving entityLiving = (EntityLiving) event.getEntity();
		
		if (entityLiving.isCreatureType(EnumCreatureType.MONSTER, false)) {
			IAttributeInstance entityAttribute = entityLiving.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
			double maxHealth = entityAttribute.getBaseValue() + 100;
			entityAttribute.setBaseValue(maxHealth);
			System.out.println("Living Spawn We did it");
			}
		}
	}
	*/
	
		
	@SubscribeEvent
	public void onMobJoinMobCheck(EntityJoinWorldEvent event) {
		
		if (event.getEntity() instanceof EntityLiving) {	
			Entity entity = event.getEntity();
			EntityLiving entityLiving = (EntityLiving) entity;
		
			NBTTagCompound entityNBT = entity.getEntityData();
			NBTTagCompound healthtag = new NBTTagCompound();
			
			System.out.println("FOUND A THING NAMED " + entity.getName().toLowerCase());
			System.out.println(ConfigManager.rareSpawnMap.get("zombie")); 
			System.out.println(ConfigManager.rareSpawnMap.get("zombie").get("spawnchance"));

			if (entity.isCreatureType(EnumCreatureType.MONSTER, true) && ConfigManager.rareSpawnMap.containsKey(entity.getName().toLowerCase()))
			{
				System.out.println("MAP OUTPUTFOR" + entity.getName().toLowerCase());
				System.out.println(ConfigManager.rareSpawnMap.toString()); 
				
				
				mobAttMap.putAll(ConfigManager.rareSpawnMap.get(entity.getName().toLowerCase()));
				
				
				
				System.out.println(mobAttMap.toString()); 
				System.out.println(mobAttMap.get("spawnchance")); 
			}
			
			
			if (entityNBT.hasKey("waighealth")){
				System.out.println("this muthafucka already bonused");
				return;
			}
			
			else {
				System.out.println("adding health to: " + entity.getName() + " " + event.getEntity().getEntityId());
				IAttributeInstance entityHealth = entityLiving.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
				entity.spawnRunningParticles();
								
				Random rand = new Random();
				int randy = rand.nextInt(100);
				double maxHealth = 0;
				maxHealth = entityHealth.getBaseValue() + 10;
				entityNBT.setBoolean("waighealth", true);
				
				//entityLiving.writeToNBT(healthtag);	//i think this is bad

				//entityLiving.setHealth((float) maxHealth); //remove this see what happens
				
				entityHealth.setBaseValue(maxHealth); //is this what actually increases the health?
				healthtag.setBoolean("waighealth", true); //tag the mob so it doesn't get modified later
				entityLiving.setCustomNameTag("HEALTH:" + String.valueOf(maxHealth)+ " ID:" + event.getEntity().getEntityId() );
				
				if (randy > 10) {
		            EntityLivingBase mob = entityLiving;
					System.out.println("WINNER WINNER " + entity.getClass() + " " + entity.getEntityId() + " DINNER. RAND - " + randy);
					maxHealth = entityHealth.getBaseValue() + 100;
					entityHealth.setBaseValue(maxHealth); //is this what actually increases the health?
					entityLiving.setCustomNameTag("Yogar, the Terrible");// + "HEALTH:" + String.valueOf(maxHealth) + " ID:" + event.getEntity().getEntityId() );

		            mob.setGlowing(true);
		            mob.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 1000));
		            //addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 200));
		            randy = 0;
		            mobAttMap.clear();
				}
			}
			}
		}
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void checkBiomeOnClientTick(ClientTickEvent event) {

		if(Minecraft.getMinecraft().player == null) return; //it will crash on title screen without this line.
	 	
		BlockPos playerPosition = Minecraft.getMinecraft().player.getPosition(); //get the position of the character
		Biome currentBiome = Minecraft.getMinecraft().player.getEntityWorld().getBiomeForCoordsBody(playerPosition); //get the biome from the character position
		
		String biomeNameString = currentBiome. getBiomeName(); 
		String colorizedBiomeNameString = biomeNameString;

		String currentPhase = event.phase.toString();
		ticksExisted = Minecraft.getMinecraft().player.ticksExisted;
		
		if ((nextTrigger < ticksExisted) && (currentPhase == "END" && event.side.isClient() == true) && (biomeNameString != lastBiome)){	

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
					System.out.println("Last biome: " + lastBiome); 
					System.out.println("Current biome: " + biomeNameString); 
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
			else {
				colorizedBiomeNameString = TextFormatting.OBFUSCATED + biomeNameString;
				subTitle = "Unknown Biome";
			}
			
			//why the hell do I have to assign the subtitle before the title? The sub ends up being blank if I don't structure it like this. Cmon minecraft. 
			Minecraft.getMinecraft().ingameGUI.displayTitle(null, subTitle, 0, 0, 0);
			Minecraft.getMinecraft().ingameGUI.displayTitle(colorizedBiomeNameString, subTitle, ConfigManager.timeFadeIn, ConfigManager.displayTime, ConfigManager.timeFadeOut); //with TextFormatting
			
			//logs
			if (ConfigManager.enableDebug == true) {
				System.out.println("Detected biome: " + biomeNameString);
				System.out.println("With subtitle: " + subTitle);
				System.out.println("Found Biome change on Tick: " + ticksExisted); 			
				System.out.println("Last biome: " + lastBiome); 
				System.out.println("Current biome: " + biomeNameString); 
			}

			//cleanup
			lastBiome = biomeNameString;
			biomeNameString = "";
			subTitle = "";		
			nextTrigger = ticksExisted + ConfigManager.displayWait;
		}
	}
}
