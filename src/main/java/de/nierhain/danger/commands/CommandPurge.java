package de.nierhain.danger.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

import static de.nierhain.danger.capabilities.level.ProviderLevel.CAPABILITY_LEVEL;

public class CommandPurge extends CommandBase {

    @Override
    public String getName() {
        return "purge";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "purge";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayer player = (EntityPlayer) sender;
        player.getCapability(CAPABILITY_LEVEL, null).reset();
    }
}
