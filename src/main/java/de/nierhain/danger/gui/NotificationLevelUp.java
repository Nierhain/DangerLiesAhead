package de.nierhain.danger.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.Sound;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;

import static de.nierhain.danger.capabilities.level.ProviderLevel.CAPABILITY_LEVEL;

public class NotificationLevelUp extends Gui {

    public NotificationLevelUp(Minecraft mc){
        ScaledResolution scaled = new ScaledResolution(mc);
        int width = scaled.getScaledWidth();
        int height = scaled.getScaledHeight();

        String str = I18n.format("danger.levelup", mc.player.getCapability(CAPABILITY_LEVEL, null).getLevel());
        int strWidth = mc.fontRenderer.getStringWidth(str);

        GlStateManager.pushMatrix();
        {
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.DST_ALPHA);
            GlStateManager.scale(1.4, 1.4, 1.4);
            mc.fontRenderer.drawString(str, (int) (width / (2 * 1.4) - strWidth / 2), (int) (height / (2 * 1.4) - 30), 0x00FF96);
            GlStateManager.scale(1, 1, 1);
            GlStateManager.disableBlend();
        }
        GlStateManager.popMatrix();
    }
}
