package de.nierhain.danger.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class GuiButtonSkill extends GuiButton {

    private GuiSkill parent;

    private static int buttonWidth = 64,
    buttonHeight = 64;

    public GuiButtonSkill(GuiSkill parent, int id, int x, int y){
        super(id, x - buttonWidth / 2, y - 48, buttonWidth, buttonHeight, "");
        this.parent = parent;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partial) {

        if(this.visible){
            mc.renderEngine.bindTexture(parent.getSkillButtonTexture());
            this.drawTexturedModalRect(x, y, 0,0,64,64);
        }

    }
}
