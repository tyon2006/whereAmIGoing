package com.tyon2006.whereAmIGoing.events;

import java.util.HashMap;
import java.util.Map;

import com.tyon2006.whereAmIGoing.config.ConfigManager;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.biome.Biome;

import java.util.Random;
import net.minecraft.init.MobEffects;

public class WaigRareSpawnHandler {

	public static Map<String, String> mobAttMap = new HashMap<String, String>();
	
	@SubscribeEvent (priority = EventPriority.HIGHEST)
	//public void onMobJoinDoRarespawn(LivingSpawnEvent.CheckSpawn event) {	
	public void onMobJoinDoRarespawn(EntityJoinWorldEvent event) {	
		
		if(ConfigManager.enableRarespawnDebug == true) System.out.println("*CHECKING FOR RARE SPAWN*");
        //if (event.getWorld().isRemote) return;
		if (!(event.getEntity() instanceof EntityLiving)) {
			if(ConfigManager.enableRarespawnDebug == true) System.out.println("MOB NOT LIVING - SKIPPING");
			return;
		}
		if (event.getEntity() instanceof EntityPlayerMP){
			if(ConfigManager.enableRarespawnDebug == true) System.out.println("MOB IS A PLAYER - SKIPPING");
			return;
		}
        if (event.getEntity().isDead){
			if(ConfigManager.enableRarespawnDebug == true) System.out.println("MOB WAS FOUND DEAD - SKIPPING");
			return;
		}
        if (!(ConfigManager.rareSpawnMap.containsKey(event.getEntity().getName().toLowerCase()))){
			if(ConfigManager.enableRarespawnDebug == true) System.out.println(event.getEntity().getName().toLowerCase() + " NOT FOUND IN RARESPAWN MAP- SKIPPING");
			return;
		}
		if (event.getEntity().getEntityData().hasKey("waigRareSpawnChecked")){
			if(ConfigManager.enableRarespawnDebug == true) System.out.println("MOB ALREADY RARE - SKIPPING");
			return;
		}
        
		if(ConfigManager.enableRarespawnDebug == true) System.out.println("NO SKIPS - ATTEMPTING TO RARIFY MOB: " + event.getEntity().getName().toLowerCase());
		
		Entity entity = event.getEntity();
		
		EntityLiving entityLiving = (EntityLiving) entity;
		NBTTagCompound entityNBT = entity.getEntityData();
				
		mobAttMap.putAll(ConfigManager.rareSpawnMap.get(entity.getName().toLowerCase()));
		
		if(mobAttMap.containsKey("rareSpawnBiome")) {
			String checkBiomeName = mobAttMap.get("rareSpawnBiome");
			if(ConfigManager.enableRarespawnDebug == true) System.out.println("MOB WILL ONLY SPAWN IN: " + checkBiomeName);
			//String biomeName = (entity.getEntityWorld().getBiome(entity.getPosition())).getBiomeName(); //bad, old factor when i was too lazy to use full resource name for comparison
			String biomeName = entity.getEntityWorld().getBiome(entity.getPosition()).getRegistryName().toString();
			if(ConfigManager.enableRarespawnDebug == true) System.out.println("MOB FOUND IN: " + biomeName);
			if (!biomeName.equals(checkBiomeName)) {
				if(ConfigManager.enableRarespawnDebug == true) System.out.println("MOB NOT FOUND IN CORRECT BIOME - SKIPPING");
				return;
			}
		}
				
		if (ConfigManager.enableRarespawnDebug == true) {
			System.out.println("FOUND A MOB NAMED " + entity.getName().toLowerCase());
			System.out.println("MAP OUTPUTFOR " + entity.getName().toLowerCase());
			System.out.println(ConfigManager.rareSpawnMap.get(entity.getName().toLowerCase())); 
			System.out.println(ConfigManager.rareSpawnMap.get(entity.getName().toLowerCase()).get("spawnchance"));
			System.out.println("adding health to: " + entity.getName() + " " + event.getEntity().getEntityId());
		}
		
		int randy = new Random().nextInt(100);
		int spawnChance = Integer.parseInt(mobAttMap.get("spawnchance"));
		
		if (randy > spawnChance) {
			if(ConfigManager.enableRarespawnDebug == true) System.out.println("rolled a :" + randy + ". Not higher than spawnchance: " + spawnChance);
			return;
		}
		else if (randy <= spawnChance) {
            
			EntityLivingBase mob = entityLiving;
            if(ConfigManager.enableRarespawnDebug == true) System.out.println("WINNER WINNER " + entity.getEntityId() + " DINNER. RANDO - " + randy);

			entityNBT.setBoolean("waigIsRare", true); //this is the good one. thanks kindlich!
			entityNBT.setString("waigMobName", entity.getName().toLowerCase()); //add name back to the mob so it can be found for item drops
			
			entityLiving.setCustomNameTag(ConfigManager.rareSpawnMap.get(entity.getName().toLowerCase()).get("spawnname"));// + "HEALTH:" + " ID:" + event.getEntity().getEntityId() );
			entity.setAlwaysRenderNameTag(true);
            //mob.setGlowing(true);
            		
            //armor
            if(mobAttMap.containsKey("armor") 
            		&& mobAttMap.get("armor") != null) 
            {
            	IAttributeInstance entityArmor = entityLiving.getEntityAttribute(SharedMonsterAttributes.ARMOR);
            	double maxArmor = entityArmor.getAttributeValue() + Double.parseDouble(mobAttMap.get("armor"));
            	if(maxArmor > 30) maxArmor = 30;
            	if(maxArmor < 0) maxArmor = 0;
            	entityArmor.setBaseValue(maxArmor);       	
            }
            
            //armor_toughness
            if(mobAttMap.containsKey("armor_toughness") 
            		&& mobAttMap.get("armor_toughness") != null) 
            {
            	IAttributeInstance entityarmor_toughness = entityLiving.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS);
            	double maxarmor_toughness = entityarmor_toughness.getAttributeValue() + Double.parseDouble(mobAttMap.get("armor_toughness"));
            	if(maxarmor_toughness > 20) maxarmor_toughness = 20;
            	if(maxarmor_toughness < 0) maxarmor_toughness = 0;
            	entityarmor_toughness.setBaseValue(maxarmor_toughness);       	
            }
               
            //attack_damage
            if(mobAttMap.containsKey("attack_damage") 
            		&& mobAttMap.get("attack_damage") != null) 
            {
            	IAttributeInstance entityattack_damage = entityLiving.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
            	double maxattack_damage = entityattack_damage.getAttributeValue() + Double.parseDouble(mobAttMap.get("attack_damage"));
            	if(maxattack_damage > 200) maxattack_damage = 200;
            	if(maxattack_damage < 0) maxattack_damage = 0;
            	entityattack_damage.setBaseValue(maxattack_damage);       	
            }
            
            //attack_speed
            if(mobAttMap.containsKey("attack_speed") 
            		&& mobAttMap.get("attack_speed") != null) 
            {
            	IAttributeInstance entityattack_speed = entityLiving.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED);
            	double maxattack_speed = entityattack_speed.getAttributeValue() + Double.parseDouble(mobAttMap.get("attack_speed"));
            	if(maxattack_speed > 200) maxattack_speed = 200;
            	if(maxattack_speed < 0) maxattack_speed = 0;
            	entityattack_speed.setBaseValue(maxattack_speed);       	
            }
            
            //follow_range
            if(mobAttMap.containsKey("follow_range") 
            		&& mobAttMap.get("follow_range") != null) 
            {
            	IAttributeInstance entityfollow_range = entityLiving.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
            	double maxfollow_range = entityfollow_range.getAttributeValue() + Double.parseDouble(mobAttMap.get("follow_range"));
            	if(maxfollow_range > 1024) maxfollow_range = 1024;
            	if(maxfollow_range < 0) maxfollow_range = 0;
            	entityfollow_range.setBaseValue(maxfollow_range);       	
            }
            
            //knockback_resistance
            if(mobAttMap.containsKey("knockback_resistance") 
            		&& mobAttMap.get("knockback_resistance") != null) 
            {
            	IAttributeInstance entityknockback_resistance = entityLiving.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
            	double maxknockback_resistance = entityknockback_resistance.getAttributeValue() + Double.parseDouble(mobAttMap.get("knockback_resistance"));
            	if(maxknockback_resistance > 1) maxknockback_resistance = 1;
            	if(maxknockback_resistance < 0) maxknockback_resistance = 0;
            	entityknockback_resistance.setBaseValue(maxknockback_resistance);       	
            }
            
            //max_health
            if(mobAttMap.containsKey("max_health") 
            		&& mobAttMap.get("max_health") != null) 
            {
            	IAttributeInstance entitymax_health = entityLiving.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
            	double maxmax_health = entitymax_health.getAttributeValue() + Double.parseDouble(mobAttMap.get("max_health"));
            	if(maxmax_health > 1024) maxmax_health = 1024;
            	if(maxmax_health < 0) maxmax_health = 0;
            	entitymax_health.setBaseValue(maxmax_health);       	
            }
           
            //movement_speed
            if(mobAttMap.containsKey("movement_speed") 
            		&& mobAttMap.get("movement_speed") != null) 
            {
            	IAttributeInstance entitymovement_speed = entityLiving.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
            	double maxmovement_speed = entitymovement_speed.getAttributeValue() + Double.parseDouble(mobAttMap.get("movement_speed"));
            	if(maxmovement_speed > 10) maxmovement_speed = 10;
            	if(maxmovement_speed < 0) maxmovement_speed = 0;
            	entitymovement_speed.setBaseValue(maxmovement_speed);       	
            }
            
            if(mobAttMap.containsKey("potion") && mobAttMap.get("potion") != null) {       
        
            	if(mobAttMap.get("potion") == "absorption") {
            		mob.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 10000, 2, true, true));
            	}
            	if(mobAttMap.get("potion") == "fire_resistance") {
            		mob.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 10000, 2, true, true));
            	}
            	if(mobAttMap.get("potion") == "haste") {
            		mob.addPotionEffect(new PotionEffect(MobEffects.HASTE, 10000, 2, true, true));
            	}
            	if(mobAttMap.get("potion") == "invisibility") {
            		mob.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 10000, 2, true, true));
            	}
            	if(mobAttMap.get("regneration") == "regeneration") {
            		mob.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 10000, 2, true, true));
            	}
            	if(mobAttMap.get("potion") == "resistance") {
            		mob.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 10000, 2, true, true));
            	}
            	if(mobAttMap.get("potion") == "speed") {
            		mob.addPotionEffect(new PotionEffect(MobEffects.SPEED, 10000, 2, true, true));;
            	}
            	if(mobAttMap.get("potion") == "strength") {
            		mob.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 10000, 2, true, true));
            	}
            	if(mobAttMap.get("potion") == "water_breathing") {
            		mob.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 10000, 2, true, true));
            	}
            }
            randy = 0;
            mobAttMap.clear();
			}
		}
	
	@SubscribeEvent
    public void rareDropXP(LivingExperienceDropEvent e){
		
		if(!(e.getEntityLiving() instanceof EntityLiving))return;
		NBTTagCompound entityNBT = e.getEntity().getEntityData();
		
		if(!(e.getEntityLiving().getEntityData().hasKey("waigIsRare"))) {
			if (ConfigManager.enableRarespawnDebug == true) System.out.println("NO TAG FOUND SKIPPING");
			return;
		}	
		
		if(entityNBT.getBoolean("waigIsRare"));
		{
			System.out.println("XP DROP TIME");
			e.setDroppedExperience(Math.round(e.getDroppedExperience()*ConfigManager.rareSpawnXPboost));
			return;
		} 
	}
	
	@SubscribeEvent
	public void rareDropItem(LivingDropsEvent e) {
		if(!(e.getEntityLiving() instanceof EntityLiving))return;

		if(!(e.getEntityLiving().getEntityData().hasKey("waigIsRare"))) {
			if (ConfigManager.enableRarespawnDebug == true) System.out.println("NO TAG FOUND SKIPPING");
			return;
		}
		
		NBTTagCompound entityNBT = e.getEntity().getEntityData();
		System.out.println(e.getEntity().getName().toLowerCase());
		System.out.println(e.getEntityLiving().getEntityData().getString("waigMobName"));
		mobAttMap.putAll(ConfigManager.rareSpawnMap.get(e.getEntityLiving().getEntityData().getString("waigMobName")));
		
		if(entityNBT.getBoolean("waigIsRare") 
				&& mobAttMap.containsKey("drops")
				&& mobAttMap.get("drops") != null);{	
					
			String dropsString = mobAttMap.get("drops");
			
			//don't forget to use single quotes for character unicode because strings dont work for some reason.
			System.out.println(dropsString);
			String dropModIDString = dropsString.substring(0, dropsString.indexOf(':'));
			String dropItemIDString = dropsString.substring(dropsString.indexOf(':'), dropsString.length());
			String dropNBTString = null;
			
			if(dropsString.contains(".")) {
				dropNBTString = dropsString.substring(dropsString.indexOf('{'), dropsString.indexOf('}')+1);
				System.out.println(dropNBTString);
			}

			if (ConfigManager.enableRarespawnDebug == true) {
				System.out.println("dropping item:");
				System.out.println(dropModIDString);
				System.out.println(dropItemIDString);
			}
			
			Item dropItem = Item.getByNameOrId(mobAttMap.get("drops"));
			NBTTagCompound dropNBT = null;
			if (!(dropNBTString == null)) {
				try {
					dropNBT = JsonToNBT.getTagFromJson(dropNBTString);
				} catch (NBTException e1) {}
			}
			
			ItemStack rareDrop = new ItemStack(dropItem, 1, 0, dropNBT);
			EntityItem entityItemDrop = new EntityItem(e.getEntity().world, e.getEntity().posX, e.getEntity().posY, e.getEntity().posZ, rareDrop);
			e.getDrops().add(entityItemDrop);
			mobAttMap.clear();
			return;
		} 
	}
}