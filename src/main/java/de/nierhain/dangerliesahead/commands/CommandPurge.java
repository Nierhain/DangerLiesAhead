package de.nierhain.dangerliesahead.commands;

import de.nierhain.dangerliesahead.capabilities.attributes.IAttributes;
import de.nierhain.dangerliesahead.capabilities.level.ILevel;
import de.nierhain.dangerliesahead.handler.AttributesHandler;
import de.nierhain.dangerliesahead.network.PacketAttributesToClient;
import de.nierhain.dangerliesahead.network.PacketHandler;
import de.nierhain.dangerliesahead.network.PacketLevelToClient;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

import static de.nierhain.dangerliesahead.capabilities.attributes.ProviderAttributes.CAPABILITY_ATTRIBUTES;
import static de.nierhain.dangerliesahead.capabilities.level.ProviderLevel.CAPABILITY_LEVEL;


public class CommandPurge extends CommandBase {

    @Override
    public String getName() {
        return "purge";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "resets players level and attributes to zero";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayer player = (EntityPlayer) sender;
        IAttributes attr = player.getCapability(CAPABILITY_ATTRIBUTES, null);
        ILevel level = player.getCapability(CAPABILITY_LEVEL, null);

        level.reset();
        attr.reset();
        AttributesHandler.purge(player);

        PacketHandler.INSTANCE.sendTo(new PacketLevelToClient(level.getLevel(), level.getXP()), (EntityPlayerMP) player);
        PacketHandler.INSTANCE.sendTo(new PacketAttributesToClient(attr.getAllAttributes(), attr.getSkillpoints()), (EntityPlayerMP) player);
    }
}
