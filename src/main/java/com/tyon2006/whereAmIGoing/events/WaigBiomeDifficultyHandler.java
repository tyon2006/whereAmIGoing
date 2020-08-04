package com.tyon2006.whereAmIGoing.events;

public class WaigBiomeDifficultyHandler {
	
	/*
	 * subscribe to mod check spawn event
	 * check the biome the mob spawned in
	 * check biome lists for contains biome
	 * get corresponding biome attribute modifier
	 * apply modifiers additionally to mob as it spawns
	 * 
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

}
