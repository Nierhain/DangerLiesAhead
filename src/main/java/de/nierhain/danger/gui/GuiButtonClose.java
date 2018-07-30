package de.nierhain.danger.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class GuiButtonClose extends GuiButton {

    private GuiSkill parent;

    public GuiButtonClose(GuiSkill parent,int id, int x, int y, int width, int height, String buttonText) {
        super(id, x, y, buttonText);
        this.parent = parent;
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.displayString = buttonText;
    }

    public GuiButtonClose(int buttonId, int x, int y, String buttonText) {
        super(buttonId, x, y, buttonText);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if(this.visible){
            mc.renderEngine.bindTexture(parent.getCloseButtonTexture());
            this.drawTexturedModalRect(x, y, 0,20,this.width,20);
            drawCenteredString(mc.fontRenderer, this.displayString, this.x + width / 2, this.y + 5, 0xFFFFFF);
        }
    }
}
