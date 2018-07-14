package de.nierhain.danger.gui;

import de.nierhain.danger.capabilities.skills.ISkills;
import de.nierhain.danger.handler.SkillsHandler;
import de.nierhain.danger.utils.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiButtonExt;

import java.io.IOException;

import static de.nierhain.danger.capabilities.skills.ProviderSkills.CAPABILITY_SKILL;

public class GuiSkill extends GuiScreen {

    private final ResourceLocation skills = new ResourceLocation(Reference.MODID, "textures/gui/skills.png");
    private final ResourceLocation skillButtonTexture = new ResourceLocation(Reference.MODID, "textures/gui/skill_button.png");

    private int fontColor = 0xFFFFFF;

    private FontRenderer fontRenderer;
    private EntityPlayer player;
    private ISkills skillsObj;

    private int titleOffset;
    private int skillStringOffset = 20;
    private int skillButtonOffset;

    private int skillWidth = 78;
    private int skillHeight = 120;
    private int luckX = 78;
    private int movementX = 78 * 2;
    private int attackDamageX = 78 * 3;
    private int attackSpeedX = 78 * 4;

    private int allSkillsWidth = 78 * 5;


    private int centerX;
    private int centerY;

    private int centerOnHealth;
    private int centerOnLuck;
    private int centerOnMovement;
    private int centerOnAttackDamage;
    private int centerOnAttackSpeed;

    private GuiButtonExt close;
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

    private final String SKILL_STRING= "danger.button.skill";

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
        skillsObj = mc.player.getCapability(CAPABILITY_SKILL, null);

        this.setVariables();
        this.drawSkillTextures();
        this.drawSkillTitles();
        this.drawSkillOverview();

        super.drawScreen(mouseX, mouseY, partialTicks);
        skill_health.drawButton(mc, mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch(button.id){
            case BUTTON_CLOSE:
                mc.displayGuiScreen(null);
                break;
            case BUTTON_SKILL_HEALTH:
                SkillsHandler.skillHealth(mc.player);
                break;
            case BUTTON_SKILL_LUCK:
                SkillsHandler.skillLuck(mc.player);
                break;
            case BUTTON_SKILL_MOVEMENT_SPEED:
                SkillsHandler.skillMovementSpeed(mc.player);
                break;
            case BUTTON_SKILL_ATTACK_DAMAGE:
                SkillsHandler.skillAttackDamage(mc.player);
                break;
            case BUTTON_SKILL_ATTACK_SPEED:
                SkillsHandler.skillAttackSpeed(mc.player);
                break;
        }
        super.actionPerformed(button);
    }

    @Override
    public void updateScreen() {
        if(false){
            skill_health.visible = false;
        } else {
            skill_health.visible = true;
            skill_luck.enabled = true;
            skill_movement_speed.enabled = true;
            skill_attack_damage.enabled = true;
            skill_attack_speed.enabled = true;
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
        skillStringOffset = centerY + 20;
        skillButtonOffset = height / 2 + skillHeight / 4;

        centerOnHealth = centerX + skillWidth / 2;
        centerOnLuck = centerX + skillWidth / 2 + luckX;
        centerOnMovement = centerX + skillWidth / 2 + movementX;
        centerOnAttackDamage = centerX + skillWidth / 2 + attackDamageX;
        centerOnAttackSpeed = centerX + skillWidth / 2 + attackSpeedX;
    }

    private void drawSkillTextures() {
        Minecraft.getMinecraft().getTextureManager().bindTexture(skills);
        drawTexturedModalRect(centerX, centerY, 0, 0, skillWidth, skillHeight);
        drawTexturedModalRect(centerX + luckX, centerY, skillWidth, 0, skillWidth, skillHeight);
        drawTexturedModalRect(centerX + movementX, centerY, skillWidth + skillWidth, 0, skillWidth, skillHeight);
        drawTexturedModalRect(centerX + attackDamageX, centerY, 0, skillHeight, skillWidth, skillHeight);
        drawTexturedModalRect(centerX + attackSpeedX, centerY, skillWidth, skillHeight, skillWidth, skillHeight);
    }

    private void drawSkillTitles(){
        drawCenteredString(fontRenderer, I18n.format("danger.health"), centerOnHealth, titleOffset, fontColor);
        drawCenteredString(fontRenderer, I18n.format("danger.luck"), centerOnLuck,  titleOffset, fontColor);
        drawCenteredString(fontRenderer, I18n.format("danger.movement.speed"), centerOnMovement,  titleOffset, fontColor);
        drawCenteredString(fontRenderer, I18n.format("danger.attack.damage"), centerOnAttackDamage, titleOffset, fontColor);
        drawCenteredString(fontRenderer, I18n.format("danger.attack.speed"), centerOnAttackSpeed,  titleOffset, fontColor);
    }

    private void drawSkillOverview(){
        drawCenteredString(fontRenderer, Integer.toString(skillsObj.getHealth()), centerOnHealth, skillStringOffset, fontColor );
        drawCenteredString(fontRenderer, Integer.toString(skillsObj.getLuck()), centerOnLuck, skillStringOffset, fontColor );
        drawCenteredString(fontRenderer, Integer.toString(skillsObj.getMovementSpeed()), centerOnMovement, skillStringOffset, fontColor );
        drawCenteredString(fontRenderer, Integer.toString(skillsObj.getAttackDamage()), centerOnAttackDamage, skillStringOffset, fontColor );
        drawCenteredString(fontRenderer, Integer.toString(skillsObj.getAttackSpeed()), centerOnAttackSpeed, skillStringOffset, fontColor );

    }

    private void setButtons() {
        buttonList.clear();
        buttonList.add(close = new GuiButtonExt(BUTTON_CLOSE, width / 2 - 25, height - 40, 50, 20, I18n.format("danger.button.close")));
        buttonList.add(skill_health = new GuiButtonSkill(this, BUTTON_SKILL_HEALTH, centerOnHealth, skillButtonOffset));
        buttonList.add(skill_luck = new GuiButtonSkill(this, BUTTON_SKILL_LUCK, centerOnLuck, skillButtonOffset));
        buttonList.add(skill_movement_speed = new GuiButtonSkill(this, BUTTON_SKILL_MOVEMENT_SPEED, centerOnMovement, skillButtonOffset));
        buttonList.add(skill_attack_damage = new GuiButtonSkill(this, BUTTON_SKILL_ATTACK_DAMAGE, centerOnAttackDamage, skillButtonOffset));
        buttonList.add(skill_attack_speed = new GuiButtonSkill(this, BUTTON_SKILL_ATTACK_SPEED, centerOnAttackSpeed, skillButtonOffset));
    }

    public ResourceLocation getSkillButtonTexture(){
        return this.skillButtonTexture;
    }
}
