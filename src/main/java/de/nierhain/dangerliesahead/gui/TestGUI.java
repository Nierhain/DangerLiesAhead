package de.nierhain.dangerliesahead.gui;

import de.nierhain.dangerliesahead.capabilities.ILevel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static de.nierhain.dangerliesahead.capabilities.CapabilityLevel.LEVEL;

public class TestGUI extends Screen {

    PlayerEntity player;
    ILevel levelObj;

    public TestGUI() {
        super(new StringTextComponent("Test this stuff"));
    }

    @Override
    protected void init() {
        player = Minecraft.getInstance().player;
        levelObj = player.getCapability(LEVEL, null).orElse(LEVEL.getDefaultInstance());

    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        levelObj = player.getCapability(LEVEL, null).orElse(LEVEL.getDefaultInstance());
        String level = "LEVEL: " + levelObj.getLevel();
        String xp = "XP: " + levelObj.getXP();
        drawCenteredString(Minecraft.getInstance().fontRenderer, level,0,0,0);
        drawCenteredString(Minecraft.getInstance().fontRenderer, xp, 10,10,0);
        super.render(p_render_1_, p_render_2_, p_render_3_);
    }
}
