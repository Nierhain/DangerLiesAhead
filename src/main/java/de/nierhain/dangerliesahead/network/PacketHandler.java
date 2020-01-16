package de.nierhain.dangerliesahead.network;

import de.nierhain.dangerliesahead.DangerLiesAhead;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.lwjgl.system.windows.MSG;

public class PacketHandler {

    private static final String PROTOCOL_VERSION = "1";
    public static SimpleChannel INSTANCE;
    private static int ID = 0;

    public static int nextID(){
        return ID++;
    }

    public static void registerMessages() {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(DangerLiesAhead.MODID, "main"),
                () -> PROTOCOL_VERSION,
                PROTOCOL_VERSION::equals,
                PROTOCOL_VERSION::equals
                );

        INSTANCE.registerMessage(nextID(),
                PacketLevel.class,
                PacketLevel::toBytes,
                PacketLevel::new,
                PacketLevel::handle
                );

    }

}
