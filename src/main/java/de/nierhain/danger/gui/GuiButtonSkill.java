package de.nierhain.danger.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class GuiButtonSkill extends GuiButton {

    private GuiSkill parent;

    private static int buttonWidth = 48,
    buttonHeight = 48;

    public GuiButtonSkill(GuiSkill parent, int id, int x, int y){
        super(id, x -12, y - buttonHeight / 2, buttonWidth, buttonHeight, "");
        this.parent = parent;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partial) {

        if(this.visible){
            mc.renderEngine.bindTexture(parent.getSkillButtonTexture());
            this.drawTexturedModalRect(x-12, y - 24, 0,0,48,48);
        }

    }
}
