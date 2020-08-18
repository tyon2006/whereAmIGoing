package com.tyon2006.whereAmIGoing.events;

import java.util.Set;

import org.apache.commons.lang3.text.WordUtils;

import com.tyon2006.whereAmIGoing.config.ConfigManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WaigBiomeMobAttributeHandler {
	
	/*
	@SubscribeEvent 
	public void onEnhanceMobDamaged(LivingHurtEvent e) {
		//remove mobs and players
		if(e.getEntity() instanceof EntityPlayerMP) return;
		System.out.println(e.getEntityLiving().getHealth());
	}
	*/
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	//@SideOnly(Side.CLIENT)
	public void onMobJoinDoBiome(EntityJoinWorldEvent event) {

		if ((event.getEntity().dimension) != 0) return;
		if (event.getEntity() instanceof EntityPlayerMP) return;
		if ( !(event.getEntity() instanceof EntityLiving) ) return;
		if (event.getEntity().getEntityData().hasKey("waigBiomeAttributeChecked")){
			if (ConfigManager.enableDebug) System.out.println("found " 
			+ event.getEntity().getName() 
			+ " attributes already enhanced for " 
			+ event.getEntity().getEntityWorld().getBiome(event.getEntity().getPosition()));
			return;
		}
		
		Entity entity = event.getEntity();
		Biome mobInBiome = entity.getEntityWorld().getBiome(entity.getPosition());
		String biomeName = mobInBiome.getRegistryName().toString();
		biomeName = biomeName.substring(biomeName.lastIndexOf(':')+1);
		biomeName = biomeName.replace('_', ' ');
		biomeName = WordUtils.capitalize(biomeName);
		biomeName = biomeName.replaceAll("Mutated", " ");
		biomeName = biomeName.replaceAll("Smaller", " ");
		biomeName = biomeName.replaceAll("With", " ");
		biomeName = biomeName.replaceAll("Trees", " ");
		biomeName = biomeName.trim();

		EntityLiving entityLiving = (EntityLiving) entity;
		NBTTagCompound entityNBT = event.getEntity().getEntityData();
		entityNBT.setString("waigBiome", biomeName); 
		if (entityLiving.isCreatureType(EnumCreatureType.AMBIENT, false)) {
			entityNBT.setBoolean("waigBiomeAttributeChecked", true);
			return;
		}
		
		Set<Type> biomeTypesSet = BiomeDictionary.getTypes(entity.getEntityWorld().getBiome(entity.getPosition()));

		if(ConfigManager.enableDebug) {
			System.out.println(biomeTypesSet.toString());
		}
		
		double attArmor = 2;
		double attToughness = 2;
		double attHealth = 10;
		double attAttackSpeed = 10;
		double attDamage = 2;
		double attFollowRange = 16;
		double attKnockbackResist = 0.1;
		double attSpeed = 10;
		
		double mobArmor = entityLiving.getEntityAttribute(SharedMonsterAttributes.ARMOR).getAttributeValue();
		double mobArmorToughness =  entityLiving.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue();
		double mobHealth = entityLiving.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getAttributeValue();
		double mobAttackSpeed = 0;
		try {mobAttackSpeed =  entityLiving.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).getAttributeValue();}
		catch(Exception e) {}
		double mobDamage = 0;
		try {mobDamage = entityLiving.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();}
		catch(Exception e) {}
		double mobFollowRange = 0;
		try {mobFollowRange =  entityLiving.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).getAttributeValue();}
		catch(Exception e) {}
		double mobKnockbackResist = entityLiving.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getAttributeValue();
		double mobSpeed = 0;
		try { mobSpeed = entityLiving.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();}
		catch(Exception e) {}
		
		EntityAITasks tasks = ((EntityLiving)entity).tasks;
		
		if(ConfigManager.enableDebug) {
		}

		
		if(!(entityNBT.hasKey("waigIsRare")))
		{
			entity.setCustomNameTag(biomeName + " " + entity.getName());
		}
		
		if (biomeTypesSet.contains(Type.FOREST)) {
			try {tasks.addTask(1, new net.minecraft.entity.ai.EntityAIFleeSun((EntityCreature) entity, 1.0D));}
			catch(Exception e){};
			((EntityLiving)entity).addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 10000, 1, true, false));
			mobArmor = mobArmor + attArmor;
			mobSpeed = mobSpeed * (1 - (attSpeed / 100));
		}
		if (biomeTypesSet.contains(Type.COLD)) {
			mobArmor = mobArmor + attArmor;
			mobSpeed = mobSpeed * (1 - (attSpeed / 100));
		}
		if (biomeTypesSet.contains(Type.MOUNTAIN)) {
			mobKnockbackResist = mobKnockbackResist + attKnockbackResist;
			mobAttackSpeed = mobAttackSpeed * (1 - (attAttackSpeed / 100));
		}
		if (biomeTypesSet.contains(Type.JUNGLE)) {
			try { tasks.addTask(1, new EntityAILeapAtTarget((EntityLiving) entity, 8));}
			catch(Exception e){}
			mobAttackSpeed = mobAttackSpeed * (1 + (attAttackSpeed / 100));
			mobDamage = mobDamage - attDamage;
		}
		if (biomeTypesSet.contains(Type.SANDY)) {
			mobAttackSpeed = mobAttackSpeed * (1 + (attSpeed / 100));
			mobArmorToughness = mobArmorToughness - attToughness;
		}
		if (biomeTypesSet.contains(Type.SPOOKY)) {
			mobFollowRange = mobFollowRange + attFollowRange;
			try { tasks.addTask(1, new EntityAIOpenDoor((EntityCreature) entity, false));}
			catch(Exception e) {}
			try { tasks.addTask(2, new EntityAIMoveIndoors((EntityCreature) entity));}
			catch(Exception e) {}
			try { tasks.addTask(3, new EntityAIBreakDoor((EntityLiving) entity));}
			catch(Exception e) {}
		}
		if (biomeTypesSet.contains(Type.SWAMP)) {
			mobHealth = mobHealth * (1 + (attHealth / 100));
			mobKnockbackResist = mobKnockbackResist - attKnockbackResist;
		}
		if (biomeTypesSet.contains(Type.MESA)) {
			mobSpeed = mobSpeed * (1 + (attSpeed / 100));
			mobArmor = mobArmor - attArmor;
		}
		if (biomeTypesSet.contains(Type.PLAINS)) {
			mobSpeed = mobSpeed * (1 + (attSpeed / 100));
			mobHealth = (mobHealth * (1 - (attHealth / 100)));
		}
		
		//cleanup
		if(mobDamage < 1) mobDamage = 1;
		if(mobArmor > 30) mobArmor = 30;
		if(mobArmor < 0) mobArmor = 0;
		if(mobArmorToughness < 0) mobArmorToughness = 0;
		if(mobArmorToughness > 20) mobArmorToughness = 20;
		if(mobKnockbackResist < 0) mobKnockbackResist= 0;
		if(mobKnockbackResist > 1) mobKnockbackResist= 1;
		mobHealth = Math.round(mobHealth);
		
		//cold = armor = 1 Speed -5%
		//mountain = knockback 5%  Attack speed = -5%
		//jungle = attack speed = 5% attack Damage = -5% can you give mobs poison attacks?
		//sandy = attack damage = 5% Armor toughness = -1
		//spooky = follow range = 16 blocks ???
		//swamp = Speed = -5% fireproof
		//mesa = speed 5% Attack damage = -5%
		//plains = speed 2% Health -5%
		
		//grab all the values
		IAttributeInstance mobAttributeArmor = entityLiving.getEntityAttribute(SharedMonsterAttributes.ARMOR);
		mobAttributeArmor.setBaseValue(mobArmor);
		IAttributeInstance mobAttributeArmorToughness = entityLiving.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS);
		mobAttributeArmorToughness.setBaseValue(mobArmorToughness);
		IAttributeInstance mobAttributeHealth = entityLiving.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
		mobAttributeHealth.setBaseValue(mobHealth);
		entityLiving.setHealth((float) mobHealth);
		entityLiving.heal((float) mobHealth);
		try {
		IAttributeInstance mobAttributeAttackSpeed = entityLiving.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED);
		mobAttributeAttackSpeed.setBaseValue(mobAttackSpeed);
		}
		catch(Exception e) {}
		try {
		IAttributeInstance mobAttributeDamage = entityLiving.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
		mobAttributeDamage.setBaseValue(mobDamage);
		}
		catch(Exception e) {}
		try {
		IAttributeInstance mobAttributeFollow = entityLiving.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
		mobAttributeFollow.setBaseValue(mobFollowRange);
		}
		catch(Exception e) {}
		IAttributeInstance mobAttributeKnockback = entityLiving.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
		mobAttributeKnockback.setBaseValue(mobKnockbackResist);
		try {
		IAttributeInstance mobAttributeSpeed = entityLiving.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
		mobAttributeSpeed.setBaseValue(mobSpeed);
		}
		catch(Exception e) {}
		
		if (ConfigManager.enableDebug) {
			entityLiving.setCustomNameTag(entityLiving.getName() + " " + mobHealth + " " + mobDamage + " " + mobArmor + " " + mobArmorToughness + " " + mobKnockbackResist + " " + mobFollowRange);
		}
		
		entityNBT.setInteger("waigBiomeID", Biome.getIdForBiome(mobInBiome));
		entityNBT.setBoolean("waigBiomeAttributeChecked", true);

	}	
}
