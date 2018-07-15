package de.nierhain.danger.network;

import de.nierhain.danger.utils.Reference;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler{

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);

    private static int id = 0;

    private static int nextID(){
        return id++;
    }

    public static void registerMessages(){
        INSTANCE.registerMessage(PacketAbilitySkill.Handler.class, PacketAbilitySkill.class, nextID(), Side.SERVER);
        INSTANCE.registerMessage(PacketGetAbilities.Handler.class, PacketGetAbilities.class, nextID(), Side.CLIENT);
    }

}
