package de.nierhain.danger.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Event;

public class EventLevelUp extends Event {

    EntityPlayer player;

    public EventLevelUp(EntityPlayer player) {
        super();
        this.player = player;
    }

    public EntityPlayer getPlayer(){
        return player;
    }
}
