package com.tyon2006.whereAmIGoing.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.AttributeMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class WaigThreatHandler {

	/**
	 * add nbt to a player - different nbt value for each type of threat?
	 * listen for the player healing, dealing, buffing, eating, etc
	 * increment threat value
	 * add mob AI call that makes them prefer targets with higher NBT values. 
	 * decay the value over a configurable number of seconds
	 * add a configurable search range
	 * add different mob behaviors (target locking, disrupters, healers, potions, leaders, ranged)
	 * 
	 * check out the code below, trace through the getClosestPlayertoEntity implementation and do that. Just get more local players and count em up
	 * probably need to keep the numbers small to stop from blowing the ass out the server. 
	 */
	
	//reduce the threat by a set value on a set schedule, say by 1 mod 10 player ticks
	@SubscribeEvent
	public void onPlayerTickReduceThreat(TickEvent.PlayerTickEvent e) {
		//get the player
		EntityPlayer player = e.player;
		//mod and return if not yet time
		//get their threat level
		//if <=0 set to 0 and return
		//reduce threat by value (maybe make this configurable?
		}
	
	@SubscribeEvent
	public void onPlayerDealDamageChangeThreat(LivingHurtEvent e) {
		if(!(e.getEntity() instanceof EntityPlayerMP)) return;
			//get the player
			Entity entity = e.getEntity();
			//get their tag, create if doesnt exist
			//get damage amount
			//add damage to player's tag as threat score
		}
	
	/*
	 * onPlayerHealChangeThreat
	 */
	
	/*
	 * onMobTickUpdateTarget
	 * perhaps each mob carries a map of players, and it does a short check around itself each time it updates targets within a very short range.
	 * This would allow for clusters of fights to happen in a large space, instead of every nearby mob changing to the highest damaging target all the time
	 * a tank could swoop around and their presence would slowly pick up all the packs, if desired
	 * Should have some kind of combat check so the server isn't making mobs scan all around them all the time
	 * 	has target? can see player? don't know possible conditions... 
	 * Check only so often so the server doesn't explode
	 */
	
		/*
	    private void addAngryAction(AttributeMap map) {
        if (map.get(ACTION_ANGRY)) {
            actions.add(event -> {
                EntityZombie helper = event.getZombieHelper();
                EntityPlayer player = event.getWorld().getClosestPlayerToEntity(helper, 50);
                if (player != null) {
                    helper.setAttackTarget(player);
                }
            });
        	}
    		}
		 */
}