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
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
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
import net.minecraft.entity.ai.*;

public class WaigBiomeMobAttributeHandler {
	
	@SubscribeEvent 
	public void onEnhanceMobDamaged(LivingHurtEvent e) {
		//remove mobs and players
		if(e.getEntity() instanceof EntityPlayerMP) return;
		System.out.println(e.getEntityLiving().getHealth());
	}
	
	@SubscribeEvent(priority = EventPriority.HIGH)
	@SideOnly(Side.CLIENT)
	public void onMobJoinDoBiome(EntityJoinWorldEvent event) {

		if (!(event.getEntity() instanceof EntityLiving)) return;
		if (event.getEntity().getEntityData().hasKey("waigBiomeAttributeChecked")){
			if (ConfigManager.enableDebug) System.out.println("found " 
			+ event.getEntity().getName() 
			+ " attributes already enhanced for " 
			+ event.getEntity().getEntityWorld().getBiome(event.getEntity().getPosition()));
			return;
		}
		
		Entity entity = event.getEntity();
		Biome mobInBiome = entity.getEntityWorld().getBiome(entity.getPosition());
		String biomeName = mobInBiome.getBiomeName();
		EntityLiving entityLiving = (EntityLiving) entity;
		NBTTagCompound entityNBT = entity.getEntityData();
		if (entityLiving.isCreatureType(EnumCreatureType.AMBIENT, false)) {
			entityNBT.setBoolean("waigBiomeAttributeChecked", true);
			return;
		}
		
		Set<Type> biomeTypesSet = BiomeDictionary.getTypes(entity.getEntityWorld().getBiome(entity.getPosition()));
		System.out.println(biomeTypesSet.toString());
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
		
		if(ConfigManager.enableDebug) {
		}

		if(!(entityNBT.hasKey("waigIsRare")))
		{
			entity.setCustomNameTag(biomeName + " " + entity.getName());
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
			mobAttackSpeed = mobAttackSpeed * (1 + (attAttackSpeed / 100));
			mobDamage = mobDamage - attDamage;
		}
		if (biomeTypesSet.contains(Type.SANDY)) {
			mobAttackSpeed = mobAttackSpeed * (1 + (attSpeed / 100));
			mobArmorToughness = mobArmorToughness - attToughness;
		}
		if (biomeTypesSet.contains(Type.SPOOKY)) {
			mobFollowRange = mobFollowRange + attFollowRange;
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

		//apply all the values
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
			entity.setAlwaysRenderNameTag(true);
		}
		
		entityNBT.setBoolean("waigBiomeAttributeChecked", true);

	}	
}
