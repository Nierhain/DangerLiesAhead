package de.nierhain.danger.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class GUI extends Gui {
    public GUI(Minecraft mc) {
        ScaledResolution scaled = new ScaledResolution(mc);
        int width = scaled.getScaledWidth();
        int height = scaled.getScaledHeight();

        String text = "Current maxHealth: " + Float.toString(mc.player.getMaxHealth());

        drawCenteredString(mc.fontRenderer, text, width / 2, (height / 2) - 4, Integer.parseInt("FFAA00", 16));
    }
}
