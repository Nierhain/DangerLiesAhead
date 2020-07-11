package de.nierhain.dangerliesahead.commands;

import de.nierhain.dangerliesahead.capabilities.attributes.IAttributes;
import de.nierhain.dangerliesahead.capabilities.level.ILevel;
import de.nierhain.dangerliesahead.handler.PlayerHandler;
import de.nierhain.dangerliesahead.network.PacketAttributesToClient;
import de.nierhain.dangerliesahead.network.PacketHandler;
import de.nierhain.dangerliesahead.network.PacketLevelToClient;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

import static de.nierhain.dangerliesahead.capabilities.attributes.ProviderAttributes.CAPABILITY_ATTRIBUTES;
import static de.nierhain.dangerliesahead.capabilities.level.ProviderLevel.CAPABILITY_LEVEL;


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
        PlayerHandler.purge(player);

        PacketHandler.INSTANCE.sendTo(new PacketLevelToClient(level.getLevel(), level.getXP()), (EntityPlayerMP) player);
        PacketHandler.INSTANCE.sendTo(new PacketAttributesToClient(attr.getAllAttributes(), attr.getSkillpoints()), (EntityPlayerMP) player);
        player.sendMessage(new TextComponentString("DangerLiesAhead: Your threat has been reset"));
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 4;
    }
}
