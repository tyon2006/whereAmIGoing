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



public class WaigRareSpawnHandler {

	public static Map<String, String> mobAttMap = new HashMap<String, String>();
	
	/*
	@SubscribeEvent
	public void postPlayerRender( final net.minecraftforge.client.event.RenderLivingEvent<EntityLivingBase> e )
	{
		//e.getRenderer().prepareScale(entitylivingbaseIn, partialTicks);
	}
	*/
	
	/*
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onMobJoinDoBiome(EntityJoinWorldEvent event) {
		

		if (event.getEntity() instanceof EntityLiving) {	
			Entity entity = event.getEntity();
			Biome mobInBiome = entity.getEntityWorld().getBiome(entity.getPosition());
			String biomeName = mobInBiome.getBiomeName();
			EntityLiving entityLiving = (EntityLiving) entity;
			NBTTagCompound entityNBT = entity.getEntityData();
			NBTTagCompound biomeTag = new NBTTagCompound();
			if (entityNBT.hasKey("waigBiomeLevelChecked")){
				System.out.println("this sumbitch already been biomed");
				return;
			}
			
			if (tier1BiomesArrayList.contains(biomeName)) {
				entityNBT.setBoolean("waigBiomeLevelChecked", true); //tag the mob so it doesn't get modified later
				//entityLiving.writeToNBT(biomeTag);	//i think this is bad
				IAttributeInstance entityHealth = entityLiving.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
				double maxHealth = 0;
				maxHealth = entityHealth.getBaseValue() + 100;
				entityHealth.setBaseValue(maxHealth);
				entityLiving.setCustomNameTag(entity.getName() + " " + biomeName + " " + maxHealth + " " + entity.getEntityData().toString());// + "HEALTH:" + String.valueOf(maxHealth) + " ID:" + event.getEntity().getEntityId() );
				entity.setAlwaysRenderNameTag(true);
			}
		} 
		return;
	}
	*/
		
	@SubscribeEvent (priority = EventPriority.LOWEST)
	@SideOnly (Side.SERVER)
	public void onMobJoinDoRarespawn(EntityJoinWorldEvent event) {
		
		if (event.getEntity() instanceof EntityLiving) {	
			
			Entity entity = event.getEntity();
			EntityLiving entityLiving = (EntityLiving) entity;
			NBTTagCompound entityNBT = entity.getEntityData();
			NBTTagCompound healthtag = new NBTTagCompound();
			
			if (entityNBT.hasKey("waigRareSpawnChecked")){
				System.out.println("this muthafucka already bonused");
				return;
			} 
			if (ConfigManager.rareSpawnMap.containsKey(entity.getName().toLowerCase()))
			{
				//GlStateManager.generateTexture()color(10, 10, 10);
				healthtag.setBoolean("waighealth", true); //tag the mob so it doesn't get modified later
				
				
				System.out.println("MAP OUTPUTFOR " + entity.getName().toLowerCase());
				System.out.println(ConfigManager.rareSpawnMap.get(entity.getName().toLowerCase())); 
				//BossInfoServer bossInfo = new BossInfoServer(entity.getDisplayName(), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_10);
				mobAttMap.putAll(ConfigManager.rareSpawnMap.get(entity.getName().toLowerCase()));
				System.out.println("FOUND A THING NAMED " + entity.getName().toLowerCase());
				System.out.println(ConfigManager.rareSpawnMap.toString()); 
				System.out.println(ConfigManager.rareSpawnMap.get(entity.getName().toLowerCase())); 
				System.out.println(ConfigManager.rareSpawnMap.get(entity.getName().toLowerCase()).get("spawnchance"));
				System.out.println(mobAttMap.toString()); 
				System.out.println(mobAttMap.get("spawnchance")); 
				
				System.out.println("adding health to: " + entity.getName() + " " + event.getEntity().getEntityId());
				IAttributeInstance entityHealth = entityLiving.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
				Random rand = new Random();
				int randy = rand.nextInt(100);
				double maxHealth = 0;
				entityLiving.writeToNBT(healthtag);	//i think this is bad //no, it isnt
				/*
				maxHealth = entityHealth.getBaseValue() + 10;
				
				//entityLiving.writeToNBT(healthtag);	//i think this is bad
				//entityLiving.setHealth((float) maxHealth); //remove this see what happens
				
				entityHealth.setBaseValue(maxHealth); //is this what actually increases the health?
				entityLiving.setCustomNameTag("HEALTH:" + String.valueOf(maxHealth)+ " ID:" + event.getEntity().getEntityId() );
				*/
				
				if (randy > 5) {
		            EntityLivingBase mob = entityLiving;
					System.out.println("WINNER WINNER " + entity.getClass() + " " + entity.getEntityId() + " DINNER. RAND - " + randy);
					maxHealth = entityHealth.getBaseValue() + 10;
					entityHealth.setBaseValue(maxHealth);//is this what actually increases the health?
					entityLiving.setHealth((float)maxHealth);
					
					
					
					healthtag.setBoolean("waigIsRare", true);
					//entityLiving.writeToNBT(healthtag);
					//entity.writeToNBT(healthtag);
					//entityNBT.setBoolean("waigIsRare", true);
					entityNBT.setTag("waigIsRare", healthtag);
					
					entityLiving.setCustomNameTag(ConfigManager.rareSpawnMap.get(entity.getName().toLowerCase()).get("spawnname"));// + "HEALTH:" + " ID:" + event.getEntity().getEntityId() );
					entity.setAlwaysRenderNameTag(true);
		            mob.setGlowing(true);
		            //System.out.println("WINNER WINNER " + entity.readFromNBT();
		            
		            mob.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 1000));
		            //addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 200));
		            randy = 0;
		            mobAttMap.clear();
				}
			}
		} 
		else {
				return;
			}
		}
	
	@SubscribeEvent
    public void dropXP(LivingExperienceDropEvent e){
		
		if(!(e.getEntityLiving() instanceof EntityLiving))return;
		NBTTagCompound entityNBT = e.getEntity().getEntityData();
		//System.out.println(entityNBT.toString());
		if(!(e.getEntityLiving().getEntityData().hasKey("waigIsRare"))) {
			if (ConfigManager.enableDebug == true) System.out.println("NO TAG FOUND SKIPPING");
			return;
		}
			
		if(entityNBT.getBoolean("waigIsRare"));
		{
			System.out.println("DROP TIME");
			e.setDroppedExperience(e.getDroppedExperience()*5);
			return;
		} 
	}

}
