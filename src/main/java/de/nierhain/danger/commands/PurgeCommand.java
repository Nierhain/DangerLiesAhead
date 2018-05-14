package de.nierhain.danger.commands;

import com.google.common.collect.Lists;

import de.nierhain.danger.capabilities.level.ILevelHandler;
import de.nierhain.danger.handler.CapabilitiesHandler;
import de.nierhain.danger.util.Reference;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class PurgeCommand extends CommandBase {


	@Override
	public String getName() {
		return "purge";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/purge <player> - purges the level of <player>";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(args.length < 1) {
			sender.sendMessage(new TextComponentString(TextFormatting.RED + "No arguments delivered."));
			sender.sendMessage(new TextComponentString(TextFormatting.RED + getUsage(sender)));
			return;
		}
		EntityPlayer player;
		try {
			player = server.getEntityWorld().getPlayerEntityByName(args[0]);
		} catch(ArrayIndexOutOfBoundsException e) {
			player = null;
		}
		
		if(player == null) {
			sender.sendMessage(new TextComponentString(TextFormatting.RED + args[0] + ": is not a player or player not found."));
			return;
		}
		
		ILevelHandler level = CapabilitiesHandler.getLevelHandler(player);
		level.purgeLevel();
		sender.sendMessage(new TextComponentString("Level purged."));
	}
}
