package de.nierhain.dangerliesahead.handler;

import de.nierhain.dangerliesahead.capabilities.level.ILevel;
import de.nierhain.dangerliesahead.capabilities.level.MapLevels;
import de.nierhain.dangerliesahead.capabilities.level.ProviderLevel;
import de.nierhain.dangerliesahead.event.EventLevelUp;
import de.nierhain.dangerliesahead.network.PacketHandler;
import de.nierhain.dangerliesahead.network.PacketLevelToClient;
import de.nierhain.dangerliesahead.network.PacketLevelUpToClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static de.nierhain.dangerliesahead.capabilities.level.ProviderLevel.CAPABILITY_LEVEL;
import static de.nierhain.dangerliesahead.config.ConfigHandler.PLAYER_MAX_LVL;

public class LevelHandler {

    private static ILevel getHandler(Entity entity) {

        if (entity.hasCapability(CAPABILITY_LEVEL, EnumFacing.DOWN))
            return entity.getCapability(CAPABILITY_LEVEL, EnumFacing.DOWN);

        return null;
    }

    @SubscribeEvent
    public void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {

        if (event.getObject() instanceof EntityPlayer) {
            event.addCapability(new ResourceLocation("dangerliesahead", "level"), new ProviderLevel());
        }
    }

    @SubscribeEvent
    public void clonePlayer(PlayerEvent.Clone event) {

        final ILevel original = getHandler(event.getOriginal());
        final ILevel clone = getHandler(event.getEntity());

        clone.setXP(original.getXP());
        clone.setLevel(original.getLevel());
    }

    @SubscribeEvent
    public void onXPPickup(PlayerPickupXpEvent e){
        EntityPlayer player = e.getEntityPlayer();
        if(!e.isCanceled() && !player.getEntityWorld().isRemote){
            int xp = e.getOrb().getXpValue();
            xpPickUp(player, xp);
        }
    }

    private void xpPickUp(EntityPlayer player, int xp){
        ILevel cap = player.getCapability(CAPABILITY_LEVEL, null);
        cap.addXP(xp);

        int currentLevel = cap.getLevel();
        int nextLevel = currentLevel + 1;

        if(currentLevel == PLAYER_MAX_LVL){
            PacketHandler.INSTANCE.sendTo(new PacketLevelToClient(cap.getLevel(), cap.getXP()), (EntityPlayerMP) player);
            return;
        }

        if(MapLevels.isLevelUp(nextLevel, cap.getXP())) {
            cap.addLevel(1);
            cap.setXP(cap.getXP() - MapLevels.getNeededXP(nextLevel));
            MinecraftForge.EVENT_BUS.post(new EventLevelUp(player));
            PacketHandler.INSTANCE.sendTo(new PacketLevelUpToClient(), (EntityPlayerMP) player);
        }

        PacketHandler.INSTANCE.sendTo(new PacketLevelToClient(cap.getLevel(), cap.getXP()), (EntityPlayerMP) player);
    }
}
