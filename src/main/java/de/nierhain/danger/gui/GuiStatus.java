package de.nierhain.danger.gui;

import de.nierhain.danger.Danger;
import de.nierhain.danger.capabilities.attributes.IAttributes;
import de.nierhain.danger.capabilities.level.ILevel;
import de.nierhain.danger.capabilities.level.MapLevels;
import de.nierhain.danger.enums.Attribute;
import de.nierhain.danger.network.PacketAttributeToServer;
import de.nierhain.danger.network.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

import static de.nierhain.danger.capabilities.attributes.ProviderAttributes.CAPABILITY_ATTRIBUTES;
import static de.nierhain.danger.capabilities.level.ProviderLevel.CAPABILITY_LEVEL;
import static de.nierhain.danger.config.ConfigHandler.PLAYER_MAX_LVL;

public class GuiStatus extends GuiScreen {

    private int centerX,
                centerY;

    private IAttributes skillsObj;
    private ILevel levelObj;

    private Minecraft mc;
    private FontRenderer fontRenderer;

    private  String playerName,
                    level,
                    remainingPoints,
                    experience;

    private ItemStack itemStack;

    private GuiButton SKILL_HEALTH,
            SKILL_LUCK,
            SKILL_MOVEMENT_SPEED,
            SKILL_ATTACK_DAMAGE,
            SKILL_ATTACK_SPEED;


    private final int BUTTON_CLOSE = 0,
            BUTTON_SKILL_HEALTH = 1,
            BUTTON_SKILL_LUCK = 2,
            BUTTON_SKILL_MOVEMENT_SPEED = 3,
            BUTTON_SKILL_ATTACK_DAMAGE = 4,
            BUTTON_SKILL_ATTACK_SPEED = 5;

    public GuiStatus() {
        this.initGui();
    }

    @Override
    public void initGui() {
        mc = Minecraft.getMinecraft();
        fontRenderer = mc.fontRenderer;
        skillsObj = mc.player.getCapability(CAPABILITY_ATTRIBUTES, null);
        levelObj = mc.player.getCapability(CAPABILITY_LEVEL, null);

        centerX = width / 2;
        centerY = height / 2;

        setupStrings();

        this.buttonList.add(new GuiButton(BUTTON_CLOSE, width - 80, height - 40, fontRenderer.getStringWidth(I18n.format("danger.button.close")) + 20, 20, I18n.format("danger.button.close")));

        int SKILLBUTTONWIDTH = (int) Math.ceil(fontRenderer.getStringWidth(" + ") * 0.8);
        this.buttonList.add(SKILL_HEALTH = new GuiButton(BUTTON_SKILL_HEALTH, centerX + 60, 108, SKILLBUTTONWIDTH, SKILLBUTTONWIDTH, " + "));
        this.buttonList.add(SKILL_LUCK =  new GuiButton(BUTTON_SKILL_LUCK, centerX + 60, 123, SKILLBUTTONWIDTH, SKILLBUTTONWIDTH, " + "));
        this.buttonList.add(SKILL_MOVEMENT_SPEED = new GuiButton(BUTTON_SKILL_MOVEMENT_SPEED, centerX + 60, 138, SKILLBUTTONWIDTH, SKILLBUTTONWIDTH, " + "));
        this.buttonList.add(SKILL_ATTACK_DAMAGE = new GuiButton(BUTTON_SKILL_ATTACK_DAMAGE, centerX + 60, 153, SKILLBUTTONWIDTH, SKILLBUTTONWIDTH, " + "));
        this.buttonList.add(SKILL_ATTACK_SPEED = new GuiButton(BUTTON_SKILL_ATTACK_SPEED, centerX + 60, 168, SKILLBUTTONWIDTH , SKILLBUTTONWIDTH, " + "));


        super.initGui();
    }

    @Override
    public void updateScreen() {
        setupStrings();
        super.updateScreen();
    }

    private void setupStrings() {
        playerName = mc.player.getDisplayNameString();
        itemStack = new ItemStack(Items.SKULL, 1, 3);
        itemStack.setTagCompound(new NBTTagCompound());
        itemStack.getTagCompound().setTag("SkullOwner", new NBTTagString(playerName));

        level = I18n.format("danger.level") + " : " + Integer.toString(levelObj.getLevel());
        remainingPoints = I18n.format("danger.remainingpoints") + " : " + skillsObj.getSkillpoints();
        if(levelObj.getLevel() != PLAYER_MAX_LVL){
            experience = I18n.format("danger.experience") + " : " + Integer.toString(levelObj.getXP()) + " / " + Integer.toString(MapLevels.getNeededXP(levelObj.getLevel() + 1));
        } else {
            experience = I18n.format("danger.ismaxlvl");
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();

        mc.renderEngine.bindTexture(new ResourceLocation(Danger.MODID, "textures/gui/status.png"));
        drawTexturedModalRect(centerX - 128, 40,0, 2, 256, 159);
        drawTexturedModalRect(centerX -128, 20, 0, 171, 256, 60);
        drawPlayerOverview();
        drawSkillOverview();
        updateButtons();


        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void drawPlayerOverview(){
        Minecraft.getMinecraft().getRenderItem().renderItemAndEffectIntoGUI(itemStack, centerX - (fontRenderer.getStringWidth(playerName)), 21);
        drawCenteredString(fontRenderer, playerName, centerX, 27, -1);
        drawCenteredString(fontRenderer, level, centerX, 50, -1);
        drawCenteredString(fontRenderer, experience, centerX, 60, -1);
        drawCenteredString(fontRenderer, remainingPoints, centerX, 70, -1);

    }

    private void drawSkillOverview(){
        mc.renderEngine.bindTexture(new ResourceLocation(Danger.MODID, "textures/gui/status.png"));
        drawTexturedModalRect(centerX - 75, 105,35, 0, 150, 1);
        drawTexturedModalRect(centerX - 75, 120,35, 0, 150, 1);
        drawTexturedModalRect(centerX - 75, 135,35, 0, 150, 1);
        drawTexturedModalRect(centerX - 75, 150,35, 0, 150, 1);
        drawTexturedModalRect(centerX - 75, 165,35, 0, 150, 1);
        drawTexturedModalRect(centerX - 75, 180,35, 0, 150, 1);

        drawString(fontRenderer, I18n.format("danger.health"),centerX - 50, 110 , -1);
        drawString(fontRenderer, I18n.format("danger.luck"),centerX - 50, 125 , -1);
        drawString(fontRenderer, I18n.format("danger.movementspeed"),centerX - 50, 140 , -1);
        drawString(fontRenderer, I18n.format("danger.attackdamage"),centerX - 50, 155 , -1);
        drawString(fontRenderer, I18n.format("danger.attackspeed"),centerX - 50, 170 , -1);


        drawCenteredString(fontRenderer, Integer.toString(skillsObj.getAttribute(Attribute.HEALTH)), centerX + 50, 110, -1);
        drawCenteredString(fontRenderer, Integer.toString(skillsObj.getAttribute(Attribute.LUCK)), centerX + 50, 125,-1);
        drawCenteredString(fontRenderer, Integer.toString(skillsObj.getAttribute(Attribute.MOVEMENT_SPEED)), centerX + 50, 140, -1 );
        drawCenteredString(fontRenderer, Integer.toString(skillsObj.getAttribute(Attribute.ATTACK_DAMAGE)), centerX + 50, 155, -1 );
        drawCenteredString(fontRenderer, Integer.toString(skillsObj.getAttribute(Attribute.ATTACK_SPEED)), centerX + 50, 170, -1 );


    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch(button.id){
            case BUTTON_CLOSE:
                mc.displayGuiScreen(null);
                break;
            case BUTTON_SKILL_HEALTH:
                PacketHandler.INSTANCE.sendToServer(new PacketAttributeToServer(Attribute.HEALTH));
                break;
            case BUTTON_SKILL_LUCK:
                PacketHandler.INSTANCE.sendToServer(new PacketAttributeToServer(Attribute.LUCK));
                break;
            case BUTTON_SKILL_MOVEMENT_SPEED:
                PacketHandler.INSTANCE.sendToServer(new PacketAttributeToServer(Attribute.MOVEMENT_SPEED));
                break;
            case BUTTON_SKILL_ATTACK_DAMAGE:
                PacketHandler.INSTANCE.sendToServer(new PacketAttributeToServer(Attribute.ATTACK_DAMAGE));
                break;
            case BUTTON_SKILL_ATTACK_SPEED:
                PacketHandler.INSTANCE.sendToServer(new PacketAttributeToServer(Attribute.ATTACK_SPEED));
                break;
        }
    }

    private void updateButtons() {
        if(mc.player.getCapability(CAPABILITY_ATTRIBUTES, null).getSkillpoints() <= 0){
            SKILL_HEALTH.visible = false;
            SKILL_LUCK.visible = false;
            SKILL_MOVEMENT_SPEED.visible = false;
            SKILL_ATTACK_DAMAGE.visible = false;
            SKILL_ATTACK_SPEED.visible = false;
        } else {
            SKILL_HEALTH.visible = true;
            SKILL_LUCK.visible = true;
            SKILL_MOVEMENT_SPEED.visible = true;
            SKILL_ATTACK_DAMAGE.visible = true;
            SKILL_ATTACK_SPEED.visible = true;
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
