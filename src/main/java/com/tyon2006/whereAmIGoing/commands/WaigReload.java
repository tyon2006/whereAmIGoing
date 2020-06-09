package com.tyon2006.whereAmIGoing.commands;

import com.tyon2006.whereAmIGoing.config.ConfigManager;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class WaigReload extends CommandBase {

	@Override
	public String getName() {
		
		return "waigreload";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		
		return "Reloads the config file settings. Remember to click save!";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		ConfigManager.load();
		String message = "WAIG Config file reloaded succesfully!";
        TextComponentString text = new TextComponentString(message);
        text.getStyle().setColor(TextFormatting.GREEN);
		sender.sendMessage(text);
	}
}
