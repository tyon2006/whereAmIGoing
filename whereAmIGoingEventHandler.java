package com.tyon2006.whereAmIGoing;
/*
 * This line is essential to turning off the annoying messages in the client.
 * /gamerule sendCommandFeedback false
 */

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

import com.mojang.realmsclient.dto.RealmsServer.WorldType;

import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.SPacketTitle;
import net.minecraft.network.play.server.SPacketTitle.Type;
import net.minecraft.network.rcon.IServer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketEntityStatus;

public class WhereAmIGoingEventHandler {

	public String lastBiome = "";
	
	/*
	 * For now, example data only. Will fill in once mod is working properly. 
	 */
	
	public String[] tier1BiomesArray = {"Forest"};
	public ArrayList<String> tier1BiomesArrayList = new ArrayList <String>(Arrays.asList(tier1BiomesArray));
	
	public String[] tier2BiomesArray = {"Roofed Forest"};
	public ArrayList<String> tier2BiomesArrayList = new ArrayList <String>(Arrays.asList(tier2BiomesArray));
	
	public String[] tier3BiomesArray = {"Birch Forest"};
	public ArrayList<String> tier3BiomesArrayList = new ArrayList <String>(Arrays.asList(tier3BiomesArray));
	
	public String[] tier4BiomesArray = {"River"};
	public ArrayList<String> tier4BiomesArrayList = new ArrayList <String>(Arrays.asList(tier4BiomesArray));
	
	public String[] tier5BiomesArray = {"Plains"};
	public ArrayList<String> tier5BiomesArrayList = new ArrayList <String>(Arrays.asList(tier5BiomesArray));
	
	public String[] tier6BiomesArray = {"Extreme Hills"};
	public ArrayList<String> tier6BiomesArrayList = new ArrayList <String>(Arrays.asList(tier6BiomesArray));
	
	public String[] excludeBiomesArray = {};
	public ArrayList<String> excludeBiomesArrayList = new ArrayList <String>(Arrays.asList(excludeBiomesArray)); //have not accounted for
	
	/*
	 * Need to develop event on join world to set game rule "sendCommandFeedback false"
	 * Confirmation messages write to player chat if rule is not disabled.
	@SubscribeEvent
	public void entityJoinWorld(EntityJoinWorldEvent event) {
		if (event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			//Minecraft.getMinecraft().player.sendChatMessage("/gamerule sendCommandFeedback false");  //this is bad, come back to it.
		}
	}
	*/
	
	@SuppressWarnings("null")
	@SubscribeEvent
	@SideOnly(Side.CLIENT) //this needs vetted. does it break crap?
	public void checkBiomeOnClientTick(ClientTickEvent event) {
	 	if(Minecraft.getMinecraft().player == null) return; //it will crash on title screen without this line.
	 	
	 	//net.minecraft.
	 	
	 	//getPlayerList().send
	 	
		BlockPos playerPosition = Minecraft.getMinecraft().player.getPosition(); //get the position of the character
		Biome currentBiome = Minecraft.getMinecraft().player.getEntityWorld().getBiomeForCoordsBody(playerPosition); //get the biome from the character position
		
		String biomeNameString = currentBiome.getBiomeName(); //get the biome name from the biome object
		String biomeNameJSON= "/title @s title {\"text\":\"" + biomeNameString + "\"}"; //concatenate the minecraft json with the biome name string
		
		String currentPhase = event.phase.toString(); //get the phase, only fire on end phases
		boolean currentSide = event.side.isClient(); //get the side, only fire on clientside ticks
		int ticksExisted = Minecraft.getMinecraft().player.ticksExisted; //get the number of ticks the player has existed for modulus
		
		if (ticksExisted % 100 == 0 && currentPhase == "END" && currentSide == true && biomeNameString != lastBiome){ //mod 100, 20 ticks per second, one check per 5 seconds.
			
		 	String commandString = "/time set 0";
		 	
		 	//ClientCommandHandler.instance.executeCommand(Minecraft.getMinecraft().player.getEntityId(), message);
			//ICommandSender playerSender = Minecraft.getMinecraft().player;
			//IServer serveryeah = Minecraft.getMinecraft().ser
			//net.minecraft.command.CommandTitle.execute(server, sender, command);
			//GameRules gamerules = this.getOverWorldGameRules(server);
						
			Object entityplayermp;
			ITextComponent itextcomponent;
			ICommandSender sender;
			SPacketTitle spackettitle1 = new SPacketTitle(spackettitle$type, TextComponentUtils.processComponent(sender, itextcomponent, entityplayermp));
			
			SPacketTitle testpack = new SPacketTitle();
			
            entityplayermp.connection.sendPacket(spackettitle1);
            //notifyCommandListener(sender, this, "commands.title.success", new Object[0]); //do not include, whole reason im refactoring.
            
			Minecraft.getMinecraft().player.sendChatMessage("/title @s times 30 20 25"); //set fade in, display, and fade out times.
			
			/*
			 * logic for setting subtitle contents and color
			 */
			
			if (tier1BiomesArrayList.contains(biomeNameString)) {
				System.out.println("Detected tier 1 biome: " + biomeNameString);
				Minecraft.getMinecraft().player.sendChatMessage("/title @s subtitle {\"text\":\"Perfectly Safe!\",\"color\":\"aqua\"}");
			} 
			else if (tier2BiomesArrayList.contains(biomeNameString)) {
				System.out.println("Detected tier 2 biome: " + biomeNameString);
				Minecraft.getMinecraft().player.sendChatMessage("/title @s subtitle {\"text\":\"Let the games begin!\",\"color\":\"green\"}");
			} 
			else if (tier3BiomesArrayList.contains(biomeNameString)) {
				System.out.println("Detected tier 3 biome: " + biomeNameString);
				Minecraft.getMinecraft().player.sendChatMessage("/title @s subtitle {\"text\":\"Training wheels are off, now!\",\"color\":\"yellow\"}");
			} 
			else if (tier4BiomesArrayList.contains(biomeNameString)) {
				System.out.println("Detected tier 4 biome: " + biomeNameString);
				Minecraft.getMinecraft().player.sendChatMessage("/title @s subtitle {\"text\":\"This place means business!\",\"color\":\"gold\"}");
			} 
			else if (tier5BiomesArrayList.contains(biomeNameString)) {
				System.out.println("Detected tier 5 biome: " + biomeNameString);
				Minecraft.getMinecraft().player.sendChatMessage("/title @s subtitle {\"text\":\"It's dangerous to go alone!\",\"color\":\"red\"}");
			} 
			else if (tier6BiomesArrayList.contains(biomeNameString)) {
				System.out.println("Detected tier 6 biome: " + biomeNameString);
				Minecraft.getMinecraft().player.sendChatMessage("/title @s subtitle {\"text\":\"Gird your loins!\",\"color\":\"dark_red\"}");
			} 

			// Minecraft.getMinecraft().player.sendChatMessage(message); //i dunno what this is?

			System.out.println("Found Biome change on Tick: " + ticksExisted); //logs
			
			Minecraft.getMinecraft().player.sendChatMessage(biomeNameJSON); //DISPLAY THE TITLE TO THE PLAYER
			
			System.out.println("Last biome: " + lastBiome); //logs
			lastBiome = biomeNameString; //logs
			System.out.println("New current biome: " + lastBiome); //logs		
			
		}
	}
}
	
