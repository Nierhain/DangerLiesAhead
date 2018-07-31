package de.nierhain.danger.gui;


import de.nierhain.danger.Danger;
import de.nierhain.danger.capabilities.attributes.IAttributes;
import de.nierhain.danger.capabilities.level.ILevel;
import de.nierhain.danger.capabilities.level.MapLevels;
import de.nierhain.danger.enums.Attribute;
import de.nierhain.danger.network.PacketAttributeToServer;
import de.nierhain.danger.network.PacketHandler;
import de.nierhain.danger.util.DevEnv;
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


public class GuiSkill extends GuiScreen {

    private final ResourceLocation skills = new ResourceLocation(Danger.MODID, "textures/gui/skills.png");
    private final ResourceLocation skillButtonTexture = new ResourceLocation(Danger.MODID, "textures/gui/skill_button.png");
    private final ResourceLocation infoTexture = new ResourceLocation(Danger.MODID, "textures/gui/info.png");

    private int fontColor = 0xFFFFFF;

    private FontRenderer fontRenderer;
    private IAttributes skillsObj;
    private ILevel levelObj;

    private int titleOffset;
    private int titleSecondLineOffset;
    private int skillStringOffset = 20;
    private int skillButtonOffset;

    private int skillWidth = 78;
    private int skillHeight = 120;
    private int allSkillsWidth = 78 * 5;


    private int centerX;
    private int centerY;
    
    private int[] attributeX = new int[5];

    private int[] centerOnAttribute = new int[5];

    private GuiButtonClose close;
    private GuiButtonSkill skill_health,
    skill_luck,
    skill_movement_speed,
    skill_attack_damage,
    skill_attack_speed;

    private final int BUTTON_CLOSE = 0,
    BUTTON_SKILL_HEALTH = 1,
    BUTTON_SKILL_LUCK = 2,
    BUTTON_SKILL_MOVEMENT_SPEED = 3,
    BUTTON_SKILL_ATTACK_DAMAGE = 4,
    BUTTON_SKILL_ATTACK_SPEED = 5;
  
    public GuiSkill() {
        this.initGui();
    }

    @Override
    public void initGui() {
        this.setVariables();
        this.setButtons();
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        fontRenderer = mc.getRenderManager().getFontRenderer();
        skillsObj = mc.player.getCapability(CAPABILITY_ATTRIBUTES, null);
        levelObj = mc.player.getCapability(CAPABILITY_LEVEL, null);


        this.drawSkillTextures();
        this.drawSkillTitles();
        this.drawSkillOverview();
        this.drawSkillpointsAvailable();
        this.drawPlayerOverview();
        this.updateButtons();

        super.drawScreen(mouseX, mouseY, partialTicks);
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
        super.actionPerformed(button);
    }

    @Override
    public void updateScreen() {
        this.updateButtons();
        super.updateScreen();
    }

    public void updateButtons() {
        if(mc.player.getCapability(CAPABILITY_ATTRIBUTES, null).getSkillpoints() <= 0){
            skill_health.visible = false;
            skill_luck.visible = false;
            skill_movement_speed.visible = false;
            skill_attack_damage.visible = false;
            skill_attack_speed.visible = false;
        } else {
            skill_health.visible = true;
            skill_luck.visible = true;
            skill_movement_speed.visible = true;
            skill_attack_damage.visible = true;
            skill_attack_speed.visible = true;

        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    private void setVariables() {

        centerX = width / 2 - allSkillsWidth / 2;
        centerY = height / 2 - skillHeight / 2;

        titleOffset = centerY + 10;
        titleSecondLineOffset = titleOffset + 10;
        skillStringOffset = titleSecondLineOffset + 15;
        skillButtonOffset = skillStringOffset + 64;

        for(int i = 0; i < attributeX.length; i++){
            attributeX[i] = centerX + skillWidth * i;
        }

        for(int i = 0; i < centerOnAttribute.length; i++){
            centerOnAttribute[i] = attributeX[i] + skillWidth / 2;
        }

    }

    private void drawSkillTextures() {
        Minecraft.getMinecraft().getTextureManager().bindTexture(skills);
        drawTexturedModalRect(attributeX[0], centerY, 0, 0, skillWidth, skillHeight);
        drawTexturedModalRect(attributeX[1], centerY, skillWidth, 0, skillWidth, skillHeight);
        drawTexturedModalRect(attributeX[2], centerY, skillWidth + skillWidth, 0, skillWidth, skillHeight);
        drawTexturedModalRect(attributeX[3], centerY, 0, skillHeight, skillWidth, skillHeight);
        drawTexturedModalRect(attributeX[4], centerY, skillWidth, skillHeight, skillWidth, skillHeight);
    }

    private void drawSkillTitles(){
        drawCenteredString(fontRenderer, I18n.format("danger.health"), centerOnAttribute[Attribute.HEALTH.getValue()], titleOffset, fontColor);
        drawCenteredString(fontRenderer, I18n.format("danger.luck"), centerOnAttribute[Attribute.LUCK.getValue()],  titleOffset, fontColor);

        drawCenteredString(fontRenderer, I18n.format("danger.movement"), centerOnAttribute[Attribute.MOVEMENT_SPEED.getValue()],  titleOffset, fontColor);
        drawCenteredString(fontRenderer, I18n.format("danger.speed"), centerOnAttribute[Attribute.MOVEMENT_SPEED.getValue()], titleSecondLineOffset, fontColor);

        drawCenteredString(fontRenderer, I18n.format("danger.attack"), centerOnAttribute[Attribute.ATTACK_DAMAGE.getValue()], titleOffset, fontColor);
        drawCenteredString(fontRenderer, I18n.format("danger.damage"), centerOnAttribute[Attribute.ATTACK_DAMAGE.getValue()], titleSecondLineOffset, fontColor);

        drawCenteredString(fontRenderer, I18n.format("danger.attack"), centerOnAttribute[Attribute.ATTACK_SPEED.getValue()],  titleOffset, fontColor);
        drawCenteredString(fontRenderer, I18n.format("danger.speed"), centerOnAttribute[Attribute.ATTACK_SPEED.getValue()],  titleSecondLineOffset, fontColor);
    }

    private void drawSkillOverview(){
        drawCenteredString(fontRenderer, Integer.toString(skillsObj.getAttribute(Attribute.HEALTH)), centerOnAttribute[Attribute.HEALTH.getValue()], skillStringOffset, fontColor );
        drawCenteredString(fontRenderer, Integer.toString(skillsObj.getAttribute(Attribute.LUCK)), centerOnAttribute[Attribute.LUCK.getValue()], skillStringOffset, fontColor );
        drawCenteredString(fontRenderer, Integer.toString(skillsObj.getAttribute(Attribute.MOVEMENT_SPEED)), centerOnAttribute[Attribute.MOVEMENT_SPEED.getValue()], skillStringOffset, fontColor );
        drawCenteredString(fontRenderer, Integer.toString(skillsObj.getAttribute(Attribute.ATTACK_DAMAGE)), centerOnAttribute[Attribute.ATTACK_DAMAGE.getValue()], skillStringOffset, fontColor );
        drawCenteredString(fontRenderer, Integer.toString(skillsObj.getAttribute(Attribute.ATTACK_SPEED)), centerOnAttribute[Attribute.ATTACK_SPEED.getValue()], skillStringOffset, fontColor );

    }

    private void drawPlayerOverview(){
        String playerName = mc.player.getDisplayNameString();
        ItemStack itemStack = new ItemStack(Items.SKULL, 1, 3);
        itemStack.setTagCompound(new NBTTagCompound());
        itemStack.getTagCompound().setTag("SkullOwner", new NBTTagString(playerName));

        Minecraft.getMinecraft().getTextureManager().bindTexture(infoTexture);
        drawTexturedModalRect(attributeX[0],  centerY - 40, 0,20,140,20);
        drawTexturedModalRect(attributeX[2], centerY - 40, 0, 0, 234, 20);

        GlStateManager.pushMatrix();
        GlStateManager.scale(1.5,1.5,1.5);
        Minecraft.getMinecraft().getRenderItem().renderItemAndEffectIntoGUI(itemStack, (int)(centerX / 1.5 + 1) , (int)(centerY / 1.5 - 28));
        GlStateManager.scale(1,1,1);
        GlStateManager.popMatrix();

        drawString(fontRenderer, playerName, attributeX[0] + 26, centerY - 35, fontColor);
        drawString(fontRenderer, I18n.format("danger.level") + " : " + Integer.toString(levelObj.getLevel()), attributeX[2] + 15, centerY - 35, fontColor);
        String str;
        if(levelObj.getLevel() != PLAYER_MAX_LVL){
            str = I18n.format("danger.experience") + " : " + Integer.toString(levelObj.getXP()) + " / " + Integer.toString(MapLevels.getNeededXP(levelObj.getLevel() + 1));
        } else {
            str = I18n.format("danger.ismaxlvl");
        }
        drawString(fontRenderer, str, attributeX[3] + 15, centerY - 35, fontColor);
    }

    private void drawSkillpointsAvailable(){
        Minecraft.getMinecraft().getTextureManager().bindTexture(infoTexture);
        drawTexturedModalRect(attributeX[0], centerY + 140, 0,0,234,20);
        drawString(fontRenderer, I18n.format("danger.availableSkillpoints"), attributeX[0] + 10, centerY + 146, fontColor);
        drawCenteredString(fontRenderer, Integer.toString(skillsObj.getSkillpoints()), attributeX[2] + 20, centerY + 146, fontColor);
    }

    private void setButtons() {
        buttonList.clear();
        buttonList.add(close = new GuiButtonClose(this, BUTTON_CLOSE, width - 120, height - 40, 100, 20, I18n.format("danger.button.close")));
        buttonList.add(skill_health = new GuiButtonSkill(this, BUTTON_SKILL_HEALTH, centerOnAttribute[Attribute.HEALTH.getValue()], skillButtonOffset));
        buttonList.add(skill_luck = new GuiButtonSkill(this, BUTTON_SKILL_LUCK, centerOnAttribute[Attribute.LUCK.getValue()], skillButtonOffset));
        buttonList.add(skill_movement_speed = new GuiButtonSkill(this, BUTTON_SKILL_MOVEMENT_SPEED, centerOnAttribute[Attribute.MOVEMENT_SPEED.getValue()], skillButtonOffset));
        buttonList.add(skill_attack_damage = new GuiButtonSkill(this, BUTTON_SKILL_ATTACK_DAMAGE, centerOnAttribute[Attribute.ATTACK_DAMAGE.getValue()], skillButtonOffset));
        buttonList.add(skill_attack_speed = new GuiButtonSkill(this, BUTTON_SKILL_ATTACK_SPEED, centerOnAttribute[Attribute.ATTACK_SPEED.getValue()], skillButtonOffset));
    }

    public ResourceLocation getSkillButtonTexture(){
        return this.skillButtonTexture;
    }
    public ResourceLocation getCloseButtonTexture() { return  this.infoTexture; }
}
