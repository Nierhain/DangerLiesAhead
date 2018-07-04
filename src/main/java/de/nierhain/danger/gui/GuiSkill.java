package de.nierhain.danger.gui;

import de.nierhain.danger.utils.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class GuiSkill extends GuiScreen {

    final ResourceLocation texture = new ResourceLocation(Reference.MODID, "textures/gui/texture.png");
    int textureWidth = 256;
    int textureHeight = 144;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        int centerX = (width / 2 ) - (textureWidth / 2);
        int centerY = (height / 2) - (textureHeight / 2);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        drawTexturedModalRect(0,0,0, 0, 256, 256);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }



    public GuiSkill() {
        this.initGui();
    }
}
