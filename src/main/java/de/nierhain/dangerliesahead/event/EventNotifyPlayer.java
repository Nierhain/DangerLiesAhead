package de.nierhain.dangerliesahead.event;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.Event;

public class EventNotifyPlayer extends Event {

    Minecraft mc;

    public EventNotifyPlayer(){
        super();
        this.mc = Minecraft.getMinecraft();
    }

    public Minecraft getInstance(){
        return mc;
    }
}
