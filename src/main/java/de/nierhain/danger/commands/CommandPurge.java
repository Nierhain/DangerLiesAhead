package de.nierhain.danger.commands;

import de.nierhain.danger.capabilities.attributes.IAttributes;
import de.nierhain.danger.capabilities.level.ILevel;
import de.nierhain.danger.handler.AttributesHandler;
import de.nierhain.danger.network.PacketAttributesToClient;
import de.nierhain.danger.network.PacketHandler;
import de.nierhain.danger.network.PacketLevelToClient;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

import static de.nierhain.danger.capabilities.attributes.ProviderAttributes.CAPABILITY_ATTRIBUTES;
import static de.nierhain.danger.capabilities.level.ProviderLevel.CAPABILITY_LEVEL;


public class CommandPurge extends CommandBase {

    private EntityPlayer player;

    @Override
    public String getName() {
        return "purge";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "resets players level and attributes to zero \n uses sender if no player specified \n /purge <player>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if(!checkPermission(server, sender)) return;
        if(args.length > 0 && args[0].contains("help")){
            sender.sendMessage(new TextComponentString(this.getUsage(sender)));
            return;
        }
        player = args.length > 0 ? server.getPlayerList().getPlayerByUsername(args[0]) :  getCommandSenderAsPlayer(sender);

        IAttributes attr = player.getCapability(CAPABILITY_ATTRIBUTES, null);
        ILevel level = player.getCapability(CAPABILITY_LEVEL, null);

        level.reset();
        attr.reset();
        AttributesHandler.purge(player);

        PacketHandler.INSTANCE.sendTo(new PacketLevelToClient(level.getLevel(), level.getXP()), (EntityPlayerMP) player);
        PacketHandler.INSTANCE.sendTo(new PacketAttributesToClient(attr.getAllAttributes(), attr.getSkillpoints()), (EntityPlayerMP) player);
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 4;
    }
}
