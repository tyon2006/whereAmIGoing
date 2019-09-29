package com.tyon2006.whereAmIGoing;

import java.util.ArrayList;
import java.util.Arrays;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WhereAmIGoingEventHandler {

	public String lastBiome = "";
	
	/*
	 * For now, example data only. Will fill in later.
	 */
	
	public String[] tier1BiomesArray = {"Forest"};
	public ArrayList<String> tier1BiomesArrayList = new ArrayList <String>(Arrays.asList(tier1BiomesArray));
	
	public String[] tier2BiomesArray = {"Roofed Forest"};
	public ArrayList<String> tier2BiomesArrayList = new ArrayList <String>(Arrays.asList(tier2BiomesArray));
	
	public String[] tier3BiomesArray = {"Birch Forest"};
	public ArrayList<String> tier3BiomesArrayList = new ArrayList <String>(Arrays.asList(tier3BiomesArray));
	
	public String[] tier4BiomesArray = {"Plains"};
	public ArrayList<String> tier4BiomesArrayList = new ArrayList <String>(Arrays.asList(tier4BiomesArray));
	
	public String[] tier5BiomesArray = {"Extreme Hills"};
	public ArrayList<String> tier5BiomesArrayList = new ArrayList <String>(Arrays.asList(tier5BiomesArray));
	
	public String[] tier6BiomesArray = {"Extreme Hills+"};
	public ArrayList<String> tier6BiomesArrayList = new ArrayList <String>(Arrays.asList(tier6BiomesArray));
	
	public String[] excludedBiomesArray = {"River"};
	public ArrayList<String> excludedBiomesArrayList = new ArrayList <String>(Arrays.asList(excludedBiomesArray));
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	
	public void checkBiomeOnClientTick(ClientTickEvent event) {
	 	if(Minecraft.getMinecraft().player == null) return; //it will crash on title screen without this line.
	 	
		BlockPos playerPosition = Minecraft.getMinecraft().player.getPosition(); //get the position of the character
		Biome currentBiome = Minecraft.getMinecraft().player.getEntityWorld().getBiomeForCoordsBody(playerPosition); //get the biome from the character position
		String biomeNameString = currentBiome.getBiomeName(); //get the biome name from the biome object
		
		String currentPhase = event.phase.toString(); //get the phase, only fire on end phases
		boolean currentSide = event.side.isClient(); //get the side, only fire on clientside ticks
		int ticksExisted = Minecraft.getMinecraft().player.ticksExisted; //get the number of ticks the player has existed for modulus
		
		if (ticksExisted % 100 == 0 && currentPhase == "END" && currentSide == true && biomeNameString != lastBiome){ //mod 100, 20 ticks per second, one check per 5 seconds.	

			String colorCodeString = "§a"; //tier 2 by default
			char biomeTierChar = 1;
			
			if (tier1BiomesArrayList.contains(biomeNameString)) {
				biomeTierChar = 1;
				colorCodeString = "§b";
			} 
			else if (tier2BiomesArrayList.contains(biomeNameString)) {
				biomeTierChar = 2;
			} 
			else if (tier3BiomesArrayList.contains(biomeNameString)) {
				biomeTierChar = 3;
				colorCodeString = "§e";
			} 
			else if (tier4BiomesArrayList.contains(biomeNameString)) {
				biomeTierChar = 4;
				colorCodeString = "§6";
			} 
			else if (tier5BiomesArrayList.contains(biomeNameString)) {
				biomeTierChar = 5;
				colorCodeString = "§c";
			} 
			else if (tier6BiomesArrayList.contains(biomeNameString)) {
				biomeTierChar = 6;
				colorCodeString = "§4";
			} 
			else if (excludedBiomesArrayList.contains(biomeNameString)) {
				System.out.println("Skipping biome: " + biomeNameString);
				return;
			}

			Minecraft.getMinecraft().ingameGUI.displayTitle(null, "subtitle", 0, 0, 0);
			Minecraft.getMinecraft().ingameGUI.displayTitle(colorCodeString + biomeNameString, null, 30, 20, 25);
			
			System.out.println("Detected tier " + biomeTierChar + " biome: " + biomeNameString);
			System.out.println("Found Biome change on Tick: " + ticksExisted); //logs			
			System.out.println("Last biome: " + lastBiome); //logs
			System.out.println("Current biome: " + biomeNameString); //logs		
			lastBiome = biomeNameString; //change criteria from if statement above
			
		}
	}
}
	
