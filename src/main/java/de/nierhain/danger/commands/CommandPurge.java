package de.nierhain.danger.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

import static de.nierhain.danger.capabilities.level.ProviderLevel.CAPABILITY_LEVEL;
<<<<<<< HEAD
import static de.nierhain.danger.capabilities.attributes.ProviderAttributes.CAPABILITY_SKILL;
=======
import static de.nierhain.danger.capabilities.skills.ProviderAttributes.CAPABILITY_SKILL;
>>>>>>> master

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
        player.getCapability(CAPABILITY_SKILL, null).reset();
        player.experience = 0;
        player.experienceLevel = 0;
    }
}
