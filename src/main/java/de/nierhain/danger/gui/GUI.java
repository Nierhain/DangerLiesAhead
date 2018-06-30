package de.nierhain.danger.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

import static de.nierhain.danger.capabilities.skills.ProviderSkills.CAPABILITY_SKILL;

public class GUI extends Gui {
    public GUI(Minecraft mc) {
        ScaledResolution scaled = new ScaledResolution(mc);
        int width = scaled.getScaledWidth();
        int height = scaled.getScaledHeight();

        String text = "Current maxHealth: " + Float.toString(mc.player.getMaxHealth()) + "\n Current Skillpoints: " + mc.player.getCapability(CAPABILITY_SKILL, null).getSkillpoints();

        drawCenteredString(mc.fontRenderer, text, width / 2, (height / 2) - 4, Integer.parseInt("FFAA00", 16));
    }
}
