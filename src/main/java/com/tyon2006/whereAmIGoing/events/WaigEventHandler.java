package com.tyon2006.whereAmIGoing.events;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.tyon2006.whereAmIGoing.config.ConfigManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.ResourceLocation;

public class WaigEventHandler {

	public String colorizedBiomeNameString = "";
	public String subTitle = "";
	public String lastBiome = "";
	public String lastLastBiome = "";

	public ResourceLocation lastBiomeResLoc;
	public ResourceLocation lastLastBiomeResLoc;
	public static List<String> excludedBiomesArrayList = new ArrayList<>(Arrays.asList(ConfigManager.excludedBiomesArray));

	@SubscribeEvent
	public void checkBiomeOnClientTick(TickEvent.PlayerTickEvent event) {

		if(event.player == null) return; //it will crash on title screen without this line.

		EntityPlayer player = event.player;
		BlockPos playerPosition = player.getPosition(); //get the position of the character
		Biome currentBiome = player.getEntityWorld().getBiomeForCoordsBody(playerPosition); //get the biome from the character position
		String biomeNameString = currentBiome.getBiomeName();
		ResourceLocation biomeResLoc = currentBiome.getRegistryName();

		if ((player.getEntityWorld().getWorldTime() % 20 == 0)
				&& event.phase == Phase.END
				&& biomeResLoc != lastBiomeResLoc
				&& biomeResLoc != lastLastBiomeResLoc
				&& !biomeNameString.equals(lastBiome)
				&& !biomeNameString.equals(lastLastBiome)
		) {

			//if checking is disabled, set AQUA and return
			if(ConfigManager.disableCategories) {

				subTitle = "";

				if (excludedBiomesArrayList.contains(biomeNameString)) {
					if (ConfigManager.enableDebugBiomeHUD) System.out.println("Skipping biome: " + biomeNameString);
					return;
				}

				colorizedBiomeNameString = TextFormatting.AQUA + biomeNameString;
				Minecraft.getMinecraft().ingameGUI.displayTitle(null, subTitle, 0, 0, 0);
				Minecraft.getMinecraft().ingameGUI.displayTitle(colorizedBiomeNameString, subTitle, ConfigManager.timeFadeIn, ConfigManager.displayTime, ConfigManager.timeFadeOut); //with TextFormatting

				if (ConfigManager.enableDebugBiomeHUD) {
					System.out.println("Detected biome: " + biomeNameString);
				}

				lastBiomeResLoc = biomeResLoc;

				lastBiome = biomeNameString;
				biomeNameString = "";
				return;
			}

			//if checking is enabled, colorize
			colorizeTextFromBiome(biomeResLoc, biomeNameString);

			//The sub ends up being blank if I don't structure the next 2 lines like this.
			Minecraft.getMinecraft().ingameGUI.displayTitle(null, subTitle, 0, 0, 0);
			Minecraft.getMinecraft().ingameGUI.displayTitle(colorizedBiomeNameString, subTitle, ConfigManager.timeFadeIn, ConfigManager.displayTime, ConfigManager.timeFadeOut); //with TextFormatting

			//logs
			if (ConfigManager.enableDebugBiomeHUD) {
				System.out.println("Current biome: " + biomeNameString);
				System.out.println("Last biome: " + lastBiome);
				System.out.println("Last last biome: " + lastLastBiome);
			}

			if(!biomeNameString.equals(lastBiome)
					&& !biomeNameString.equals(lastLastBiome)) {
				lastLastBiome = lastBiome;
				lastBiome = biomeNameString;
			}

			if(biomeResLoc != lastBiomeResLoc
					&& biomeResLoc != lastLastBiomeResLoc) {
				lastLastBiomeResLoc = lastBiomeResLoc;
				lastBiomeResLoc = biomeResLoc;
			}

			//cleanup
			biomeNameString = "";
			subTitle = "";
		}
	}

	public void colorizeTextFromBiome(ResourceLocation biomeResLoc, String biomeNameString) {

		//skip if excluded
		if (ConfigManager.excludedBiomeResLoc.contains(biomeResLoc)) {
			System.out.println("Skipping biome: " + biomeNameString);
			colorizedBiomeNameString = null;
			subTitle = null;
			return;
		}
		//colorize if not
		if (ConfigManager.tier1BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.AQUA + biomeNameString;
			subTitle = ConfigManager.tier1Subtitle;
		}
		else if (ConfigManager.tier2BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.GREEN + biomeNameString;
			subTitle = ConfigManager.tier2Subtitle;
		}
		else if (ConfigManager.tier3BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.YELLOW + biomeNameString;
			subTitle = ConfigManager.tier3Subtitle;
		}
		else if (ConfigManager.tier4BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.GOLD + biomeNameString;
			subTitle = ConfigManager.tier4Subtitle;
		}
		else if (ConfigManager.tier5BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.RED + biomeNameString;
			subTitle = ConfigManager.tier5Subtitle;
		}
		else if (ConfigManager.tier6BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.DARK_RED + biomeNameString;
			subTitle = ConfigManager.tier6Subtitle;
		}
		else if (ConfigManager.tier7BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.LIGHT_PURPLE + biomeNameString;
			subTitle = ConfigManager.tier7Subtitle;
		}
		else if (ConfigManager.tier8BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.DARK_GREEN + biomeNameString;
			subTitle = ConfigManager.tier8Subtitle;
		}
		else if (ConfigManager.tier9BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.DARK_AQUA + biomeNameString;
			subTitle = ConfigManager.tier9Subtitle;
		}
		else if (ConfigManager.tier10BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.DARK_BLUE + biomeNameString;
			subTitle = ConfigManager.tier10Subtitle;
		}
		else if (ConfigManager.tier11BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.DARK_PURPLE + biomeNameString;
			subTitle = ConfigManager.tier11Subtitle;
		}
		else if (ConfigManager.tier12BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.WHITE + biomeNameString;
			subTitle = ConfigManager.tier12Subtitle;
		}
		else if (ConfigManager.tier13BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.GRAY + biomeNameString;
			subTitle = ConfigManager.tier13Subtitle;
		}
		else if (ConfigManager.tier14BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.DARK_GRAY + biomeNameString;
			subTitle = ConfigManager.tier14Subtitle;
		}
		else if (ConfigManager.tier15BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.BLACK + biomeNameString;
			subTitle = ConfigManager.tier15Subtitle;
		}
		else if (ConfigManager.tier16BiomeResLoc.contains(biomeResLoc)) {
			colorizedBiomeNameString = TextFormatting.BLUE + biomeNameString;
			subTitle = ConfigManager.tier16Subtitle;
		}
		else {
			colorizedBiomeNameString = null;
			subTitle = null;
		}
	}
}