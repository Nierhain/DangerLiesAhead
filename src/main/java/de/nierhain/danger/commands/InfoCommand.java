package de.nierhain.danger.commands;

import de.nierhain.danger.capabilities.level.ILevelHandler;
import de.nierhain.danger.handler.CapabilitiesHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class InfoCommand extends CommandBase{

	@Override
	public String getName() {
		return "dangerinfo";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return null;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		EntityPlayer player = server.getEntityWorld().getPlayerEntityByName(sender.getName());
		
		ILevelHandler level = CapabilitiesHandler.getLevelHandler(player);
		sender.sendMessage(new TextComponentString("XP: " + level.getXP()));
		sender.sendMessage(new TextComponentString("Level: " + level.getLevel()));
	}
	
}
