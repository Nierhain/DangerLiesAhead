package de.nierhain.dangerliesahead.network;

import de.nierhain.dangerliesahead.capabilities.ILevel;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

import static de.nierhain.dangerliesahead.capabilities.CapabilityLevel.LEVEL;

public class PacketLevel {

    private final int level;
    private final int xp;

    public PacketLevel(PacketBuffer buf){
        level = buf.readInt();
        xp = buf.readInt();
    }

    public void toBytes(PacketBuffer buf){
        buf.writeInt(level);
        buf.writeInt(xp);
    }

    public PacketLevel(int level, int xp){
        this.level = level;
        this.xp = xp;
    }

    public void handle(Supplier<NetworkEvent.Context> ctx){
        ctx.get().enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();
            ILevel levelObj = mc.player.getCapability(LEVEL, null).orElse(null);
            levelObj.setLevel(level);
            levelObj.setXP(xp);
        });
        ctx.get().setPacketHandled(true);
    }
}
