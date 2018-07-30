package de.nierhain.danger.gui;

import de.nierhain.danger.event.EventLevelUp;
import de.nierhain.danger.event.EventNotifyPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NotificationLevelUp extends Gui {

    Minecraft mc;
    ScaledResolution sr;
    int width;
    int height;

    @SubscribeEvent
    public void onLevelUp(EventNotifyPlayer event){
        System.out.println("Level UP");
        this.mc = Minecraft.getMinecraft();
        this.sr = new ScaledResolution(mc);
        this.width = sr.getScaledWidth();
        this.height = sr.getScaledHeight();
        mc.fontRenderer.drawStringWithShadow(I18n.format("danger.levelup"), width / 2, height / 2, 0xFFFFFF);
    }
}
