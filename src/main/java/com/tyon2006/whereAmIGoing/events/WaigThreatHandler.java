package com.tyon2006.whereAmIGoing.events;

public class WaigThreatHandler {

	/**
	 * add nbt to a player - different nbt value for each type of threat?
	 * listen for the player healing, dealing, buffing, eating, etc
	 * increment threat value
	 * add mob AI call that makes them prefer targets with higher NBT values. 
	 * decay the value over a configurable number of seconds
	 * add a configurable search range
	 * add diferent mob behaviors (target locking, disrupters, healers, potions, leaders, ranged)
	 * 
	 * check out the code below, trace through the getClosestPlayertoEntity implementation and do that. Just get more local players and count em up
	 * probably need to keep the numbers small to stop from blowing the ass out the server. 
	 */
	
	/*
	 *     private void addAngryAction(AttributeMap map) {
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
