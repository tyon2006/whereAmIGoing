package com.tyon2006.whereAmIGoing.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.tyon2006.whereAmIGoing.config.ConfigManager;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class WaigBiomeDifficultyHandler {
	
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
	
	@SubscribeEvent 
	public void onEnhanceMobDamaged(LivingHurtEvent e) {
		//remove mobs and players
		if(e.getEntity() instanceof EntityPlayerMP) return;
		
		if (e.getSource() == DamageSource.MAGIC) {
			
			Entity entity = e.getEntity();
			Biome mobInBiome = entity.getEntityWorld().getBiome(entity.getPosition());
			String biomeName = mobInBiome.getBiomeName();
					
			if (tier1BiomesArrayList.contains(biomeName)) {
				if (ConfigManager.enableDebug) System.out.println("Found magic damage event to deal: " + e.getAmount());
				e.setAmount((float) (e.getAmount()*(1-(ConfigManager.magicResistArray[0]))));
				if (ConfigManager.enableDebug) System.out.println("Reduced magic damage to: " + e.getAmount());
			}
			if (tier2BiomesArrayList.contains(biomeName)) {
				if (ConfigManager.enableDebug) System.out.println("Found magic damage event to deal: " + e.getAmount());
				e.setAmount((float) (e.getAmount()*(1-(ConfigManager.magicResistArray[1]))));
				if (ConfigManager.enableDebug) System.out.println("Reduced magic damage to: " + e.getAmount());
			}
			if (tier3BiomesArrayList.contains(biomeName)) {
				if (ConfigManager.enableDebug) System.out.println("Found magic damage event to deal: " + e.getAmount());
				e.setAmount((float) (e.getAmount()*(1-(ConfigManager.magicResistArray[2]))));
				if (ConfigManager.enableDebug) System.out.println("Reduced magic damage to: " + e.getAmount());
			}
			if (tier4BiomesArrayList.contains(biomeName)) {
				if (ConfigManager.enableDebug) System.out.println("Found magic damage event to deal: " + e.getAmount());
				e.setAmount((float) (e.getAmount()*(1-(ConfigManager.magicResistArray[3]))));
				if (ConfigManager.enableDebug) System.out.println("Reduced magic damage to: " + e.getAmount());
			}
			if (tier5BiomesArrayList.contains(biomeName)) {
				if (ConfigManager.enableDebug) System.out.println("Found magic damage event to deal: " + e.getAmount());
				e.setAmount((float) (e.getAmount()*(1-(ConfigManager.magicResistArray[4]))));
				if (ConfigManager.enableDebug) System.out.println("Reduced magic damage to: " + e.getAmount());
			}
			if (tier6BiomesArrayList.contains(biomeName)) {
				if (ConfigManager.enableDebug) System.out.println("Found magic damage event to deal: " + e.getAmount());
				e.setAmount((float) (e.getAmount()*(1-(ConfigManager.magicResistArray[5]))));
				if (ConfigManager.enableDebug) System.out.println("Reduced magic damage to: " + e.getAmount());
			}
			if (tier7BiomesArrayList.contains(biomeName)) {
				if (ConfigManager.enableDebug) System.out.println("Found magic damage event to deal: " + e.getAmount());
				e.setAmount((float) (e.getAmount()*(1-(ConfigManager.magicResistArray[6]))));
				if (ConfigManager.enableDebug) System.out.println("Reduced magic damage to: " + e.getAmount());
			}
			if (tier8BiomesArrayList.contains(biomeName)) {
				if (ConfigManager.enableDebug) System.out.println("Found magic damage event to deal: " + e.getAmount());
				e.setAmount((float) (e.getAmount()*(1-(ConfigManager.magicResistArray[7]))));
				if (ConfigManager.enableDebug) System.out.println("Reduced magic damage to: " + e.getAmount());
			}
			if (tier9BiomesArrayList.contains(biomeName)) {
				if (ConfigManager.enableDebug) System.out.println("Found magic damage event to deal: " + e.getAmount());
				e.setAmount((float) (e.getAmount()*(1-(ConfigManager.magicResistArray[8]))));
				if (ConfigManager.enableDebug) System.out.println("Reduced magic damage to: " + e.getAmount());
			}
			if (tier10BiomesArrayList.contains(biomeName)) {
				if (ConfigManager.enableDebug) System.out.println("Found magic damage event to deal: " + e.getAmount());
				e.setAmount((float) (e.getAmount()*(1-(ConfigManager.magicResistArray[9]))));
				if (ConfigManager.enableDebug) System.out.println("Reduced magic damage to: " + e.getAmount());
			}
			if (tier11BiomesArrayList.contains(biomeName)) {
				if (ConfigManager.enableDebug) System.out.println("Found magic damage event to deal: " + e.getAmount());
				e.setAmount((float) (e.getAmount()*(1-(ConfigManager.magicResistArray[10]))));
				if (ConfigManager.enableDebug) System.out.println("Reduced magic damage to: " + e.getAmount());
			}
			if (tier12BiomesArrayList.contains(biomeName)) {
				if (ConfigManager.enableDebug) System.out.println("Found magic damage event to deal: " + e.getAmount());
				e.setAmount((float) (e.getAmount()*(1-(ConfigManager.magicResistArray[11]))));
				if (ConfigManager.enableDebug) System.out.println("Reduced magic damage to: " + e.getAmount());
			}
			if (tier13BiomesArrayList.contains(biomeName)) {
				if (ConfigManager.enableDebug) System.out.println("Found magic damage event to deal: " + e.getAmount());
				e.setAmount((float) (e.getAmount()*(1-(ConfigManager.magicResistArray[12]))));
				if (ConfigManager.enableDebug) System.out.println("Reduced magic damage to: " + e.getAmount());
			}
			if (tier14BiomesArrayList.contains(biomeName)) {
				if (ConfigManager.enableDebug) System.out.println("Found magic damage event to deal: " + e.getAmount());
				e.setAmount((float) (e.getAmount()*(1-(ConfigManager.magicResistArray[13]))));
				if (ConfigManager.enableDebug) System.out.println("Reduced magic damage to: " + e.getAmount());
			}
			if (tier15BiomesArrayList.contains(biomeName)) {
				if (ConfigManager.enableDebug) System.out.println("Found magic damage event to deal: " + e.getAmount());
				e.setAmount((float) (e.getAmount()*(1-(ConfigManager.magicResistArray[14]))));
				if (ConfigManager.enableDebug) System.out.println("Reduced magic damage to: " + e.getAmount());
			}
			if (tier16BiomesArrayList.contains(biomeName)) {
				if (ConfigManager.enableDebug) System.out.println("Found magic damage event to deal: " + e.getAmount());
				e.setAmount((float) (e.getAmount()*(1-(ConfigManager.magicResistArray[15]))));
				if (ConfigManager.enableDebug) System.out.println("Reduced magic damage to: " + e.getAmount());
			}

		}
		
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	@SideOnly(Side.CLIENT)
	public void onMobJoinDoBiome(EntityJoinWorldEvent event) {

		if (event.getEntity() instanceof EntityLiving) {	
			
			Entity entity = event.getEntity();
			Biome mobInBiome = entity.getEntityWorld().getBiome(entity.getPosition());
			String biomeName = mobInBiome.getBiomeName();
			EntityLiving entityLiving = (EntityLiving) entity;
			NBTTagCompound entityNBT = entity.getEntityData();


			
			if (entityLiving.isCreatureType(EnumCreatureType.AMBIENT, false)) {
				entityNBT.setBoolean("waigBiomeLevelChecked", true);
				return;
			}

			if (entityNBT.hasKey("waigBiomeLevelChecked")){
				if(ConfigManager.enableDebug) System.out.println("found " + entity.getName() + " already marked in " + biomeName);
				return;
			}
			
			//add health, add damage, add armor, add armor toughness, add knockback resist, follow range
			double maxHealth = 0;
			double maxDamage = 0;
			double maxArmor = 0;
			double maxArmorToughness = 0;
			double maxKnockbackResist = 0;
			double maxFollowRange = 0;
			String[] difficultyValues = {"0", "0", "0", "0", "0", "0"};  

			if (tier1BiomesArrayList.contains(biomeName)) {
				difficultyValues = ConfigManager.tier1BiomesDifficultyArray;
			}
			else if (tier2BiomesArrayList.contains(biomeName)) {
				difficultyValues = ConfigManager.tier2BiomesDifficultyArray;
			}
			else if (tier3BiomesArrayList.contains(biomeName)) {
				difficultyValues = ConfigManager.tier3BiomesDifficultyArray;
			}
			else if (tier4BiomesArrayList.contains(biomeName)) {
				difficultyValues = ConfigManager.tier4BiomesDifficultyArray;
			}
			else if (tier5BiomesArrayList.contains(biomeName)) {
				difficultyValues = ConfigManager.tier5BiomesDifficultyArray;
			}
			else if (tier6BiomesArrayList.contains(biomeName)) {
				difficultyValues = ConfigManager.tier6BiomesDifficultyArray;
			}
			else if (tier7BiomesArrayList.contains(biomeName)) {
				difficultyValues = ConfigManager.tier7BiomesDifficultyArray;
			}
			else if (tier8BiomesArrayList.contains(biomeName)) {
				difficultyValues = ConfigManager.tier8BiomesDifficultyArray;
			}
			else if (tier9BiomesArrayList.contains(biomeName)) {
				difficultyValues = ConfigManager.tier9BiomesDifficultyArray;
			}
			else if (tier10BiomesArrayList.contains(biomeName)) {
				difficultyValues = ConfigManager.tier10BiomesDifficultyArray;
			}
			else if (tier11BiomesArrayList.contains(biomeName)) {
				difficultyValues = ConfigManager.tier11BiomesDifficultyArray;
			}
			else if (tier12BiomesArrayList.contains(biomeName)) {
				difficultyValues = ConfigManager.tier12BiomesDifficultyArray;
			}
			else if (tier13BiomesArrayList.contains(biomeName)) {
				difficultyValues = ConfigManager.tier13BiomesDifficultyArray;
			}
			else if (tier14BiomesArrayList.contains(biomeName)) {
				difficultyValues = ConfigManager.tier14BiomesDifficultyArray;
			}
			else if (tier15BiomesArrayList.contains(biomeName)) {
				difficultyValues = ConfigManager.tier15BiomesDifficultyArray;
			}
			else if (tier16BiomesArrayList.contains(biomeName)) {
				difficultyValues = ConfigManager.tier16BiomesDifficultyArray;
			}
			else return;
			
			if(ConfigManager.enableDebug) {
				System.out.println(difficultyValues[0]);
				System.out.println(difficultyValues[1]);
				System.out.println(difficultyValues[2]);
				System.out.println(difficultyValues[3]);
				System.out.println(difficultyValues[4]);
				System.out.println(difficultyValues[5]); //is this even used?
				System.out.println(difficultyValues[6]);
			}
			

			maxHealth = Double.parseDouble(difficultyValues[0]) + entityLiving.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getAttributeValue();
			try {maxDamage = Double.parseDouble(difficultyValues[1]) + entityLiving.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();}
			catch(Exception e) {}
			maxArmor = Double.parseDouble(difficultyValues[2]) + entityLiving.getEntityAttribute(SharedMonsterAttributes.ARMOR).getAttributeValue();
			maxArmorToughness = Double.parseDouble(difficultyValues[3]) + entityLiving.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue();
			maxKnockbackResist = Double.parseDouble(difficultyValues[4]) + entityLiving.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getAttributeValue();
			try {maxFollowRange= Double.parseDouble(difficultyValues[6]) + entityLiving.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).getAttributeValue();}
			catch(Exception e) {}
			
			//apply all the values
			
			IAttributeInstance mobAttributeHealth = entityLiving.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
			mobAttributeHealth.setBaseValue(maxHealth);
			entityLiving.setHealth((float) maxHealth);
			entityLiving.heal((float) maxHealth);
			try {
			IAttributeInstance mobAttributeDamage = entityLiving.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			mobAttributeDamage.setBaseValue(maxDamage);
			}
			catch(Exception e) {}
			IAttributeInstance mobAttributeArmor = entityLiving.getEntityAttribute(SharedMonsterAttributes.ARMOR);
			mobAttributeArmor.setBaseValue(maxArmor);
			IAttributeInstance mobAttributeArmorToughness = entityLiving.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS);
			mobAttributeArmorToughness.setBaseValue(maxArmorToughness);
			IAttributeInstance mobAttributeKnockback = entityLiving.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
			mobAttributeKnockback.setBaseValue(maxKnockbackResist);
			try {
			IAttributeInstance mobAttributeFollow = entityLiving.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
			mobAttributeFollow.setBaseValue(maxFollowRange);
			}
			catch(Exception e) {}
			
			if (ConfigManager.enableDebug) {
				entityLiving.setCustomNameTag(entityLiving.getName() + " " + maxHealth + " " + maxDamage + " " + maxArmor + " " + maxArmorToughness + " " + maxKnockbackResist + " " + maxFollowRange);
				//entity.setAlwaysRenderNameTag(true);
			}
			
			if (ConfigManager.enableDebug) {
				
			}
			entityNBT.setBoolean("waigBiomeLevelChecked", true);
		}
	}	
}
