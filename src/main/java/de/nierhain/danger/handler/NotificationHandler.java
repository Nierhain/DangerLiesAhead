package de.nierhain.danger.handler;

import de.nierhain.danger.event.EventNotifyPlayer;
import de.nierhain.danger.gui.NotificationLevelUp;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class NotificationHandler {

    private boolean draw = false;
    private int time = 0;
    private final int MAX_TIME_PER_NOTIFICATION = 200;

    @SubscribeEvent
    public void onRenderGui(RenderGameOverlayEvent.Post event){
        if (event.getType() != RenderGameOverlayEvent.ElementType.TEXT || !draw) return;
        new NotificationLevelUp(Minecraft.getMinecraft());

    }

    @SubscribeEvent
    public void onLevelUp(EventNotifyPlayer event){
        this.draw = true;
    }

    @SubscribeEvent
    public void onTick(TickEvent.RenderTickEvent event){
        if(event.phase == TickEvent.Phase.START) return;
        if(draw)
            time++;
        if(time > MAX_TIME_PER_NOTIFICATION) {
            time = 0;
            draw = false;
        }

    }
}
