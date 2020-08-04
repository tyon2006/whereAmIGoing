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
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
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
		
	@SubscribeEvent (priority = EventPriority.HIGH)
	//@SideOnly (Side.SERVER) //this breaks it
	//public void onMobJoinDoRarespawn(EntityJoinWorldEvent event) {	//is this even different from living spawn event?
	public void onMobJoinDoRarespawn(LivingSpawnEvent.CheckSpawn event) {	
		
        if (event.getWorld().isRemote) return;
		if (!(event.getEntity() instanceof EntityLiving)) return;
        if (event.getEntityLiving().isDead) return;
        if (!(ConfigManager.rareSpawnMap.containsKey(event.getEntity().getName().toLowerCase()))) return;
        
		if (event.getEntity().getEntityData().hasKey("waigRareSpawnChecked")){
			System.out.println("this muthafucka already bonused");
			return;
		} 
        
		Entity entity = event.getEntity();
		EntityLiving entityLiving = (EntityLiving) entity;
		NBTTagCompound entityNBT = entity.getEntityData();
		NBTTagCompound healthtag = new NBTTagCompound();
		NBTTagCompound tag = new NBTTagCompound();
		
		healthtag.setBoolean("waighealth", true); //tag the mob so it doesn't get modified later
		
		System.out.println("MAP OUTPUTFOR " + entity.getName().toLowerCase());
		System.out.println(ConfigManager.rareSpawnMap.get(entity.getName().toLowerCase())); 
		//BossInfoServer bossInfo = new BossInfoServer(entity.getDisplayName(), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_10);
		mobAttMap.putAll(ConfigManager.rareSpawnMap.get(entity.getName().toLowerCase()));
		System.out.println("FOUND A THING NAMED " + entity.getName().toLowerCase());
		System.out.println(ConfigManager.rareSpawnMap.toString()); 
		System.out.println(ConfigManager.rareSpawnMap.get(entity.getName().toLowerCase())); 
		System.out.println(ConfigManager.rareSpawnMap.get(entity.getName().toLowerCase()).get("spawnchance"));
		//System.out.println(mobAttMap.toString()); 
		//System.out.println(mobAttMap.get("spawnchance")); 
		
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
			
			//healthtag.setBoolean("waigIsRare", true);
			//entityNBT.setTag("waigIsRare", healthtag);
			entityNBT.setBoolean("waigIsRare", true); //this is the good one. thanks kindlich!
			entityNBT.setString("waigMobName", entity.getName().toLowerCase());
			
			entityLiving.setCustomNameTag(ConfigManager.rareSpawnMap.get(entity.getName().toLowerCase()).get("spawnname"));// + "HEALTH:" + " ID:" + event.getEntity().getEntityId() );
			entity.setAlwaysRenderNameTag(true);
            mob.setGlowing(true);
            
            mob.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 1000));
            
            String potname = "INVISIBILITY";
            //addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 200));
            randy = 0;;
            mobAttMap.clear();
			}
		}
	
	@SubscribeEvent
    public void rareDropXP(LivingExperienceDropEvent e){
		
		if(!(e.getEntityLiving() instanceof EntityLiving))return;
		NBTTagCompound entityNBT = e.getEntity().getEntityData();
		//System.out.println(entityNBT.toString());
		if(!(e.getEntityLiving().getEntityData().hasKey("waigIsRare"))) {
			if (ConfigManager.enableDebug == true) System.out.println("NO TAG FOUND SKIPPING");
			return;
		}
			
		if(entityNBT.getBoolean("waigIsRare"));
		{
			System.out.println("XP DROP TIME");
			e.setDroppedExperience(e.getDroppedExperience()*5);
			return;
		} 
	}
	
	@SubscribeEvent
	public void rareDropItem(LivingDropsEvent e) {
		if(!(e.getEntityLiving() instanceof EntityLiving))return;
		NBTTagCompound entityNBT = e.getEntity().getEntityData();
		//System.out.println(entityNBT.toString());
		if(!(e.getEntityLiving().getEntityData().hasKey("waigIsRare"))) {
			if (ConfigManager.enableDebug == true) System.out.println("NO TAG FOUND SKIPPING");
			return;
		}
		System.out.println(e.getEntity().getName().toLowerCase());
		System.out.println(e.getEntityLiving().getEntityData().getString("waigMobName"));
		mobAttMap.putAll(ConfigManager.rareSpawnMap.get(e.getEntityLiving().getEntityData().getString("waigMobName")));
		
		if(entityNBT.getBoolean("waigIsRare") 
				&& mobAttMap.containsKey("drops")
				&& mobAttMap.get("drops") != null);
		{	
			String dropsString = mobAttMap.get("drops");
			
			System.out.println(dropsString);
			String dropModIDString = dropsString.substring(0, dropsString.indexOf(':'));
			String dropItemIDString = dropsString.substring(0, dropsString.indexOf(".")-1);
			String dropNBTString = dropsString.substring(dropsString.indexOf("{"), dropsString.indexOf("}")-1);

			if (ConfigManager.enableDebug == true) {
				System.out.println("ITEM DROP TIME");
				System.out.println(dropModIDString);
				System.out.println(dropItemIDString);
				System.out.println(dropNBTString);
			}
			
			List<EntityItem> rareDropList = e.getDrops();
			rareDropList.clear();
			new Item();
			Item dropItem = Item.getByNameOrId(mobAttMap.get("drops"));
			NBTTagCompound dropNBT = null;
			ItemStack rareDrop = new ItemStack(dropItem, 1, 0, dropNBT);
			EntityItem entityItemDrop = new EntityItem(e.getEntity().world, e.getEntity().posX, e.getEntity().posY, e.getEntity().posZ, rareDrop);
			//rareDropList.add(mobAttMap.get("drops").to);
			e.getDrops().add(entityItemDrop);
			return;
		} 
	}

}
