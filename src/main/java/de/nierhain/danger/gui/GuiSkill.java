package de.nierhain.danger.gui;

import de.nierhain.danger.capabilities.skills.ISkills;
import de.nierhain.danger.enums.Ability;
import de.nierhain.danger.handler.SkillsHandler;
import de.nierhain.danger.network.PacketAbilitySkill;
import de.nierhain.danger.network.PacketGetAbilities;
import de.nierhain.danger.network.PacketHandler;
import de.nierhain.danger.utils.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

import static de.nierhain.danger.capabilities.skills.ProviderSkills.CAPABILITY_SKILL;

public class GuiSkill extends GuiScreen {

    private final ResourceLocation texture = new ResourceLocation(Reference.MODID, "textures/gui/texture.png");
    private final ResourceLocation skills = new ResourceLocation(Reference.MODID, "textures/gui/skills.png");

    private int fontColor = 0xFFFFFF;

    private FontRenderer fontRenderer;
    private EntityPlayer player;
    private ISkills skillsObj;

    private int titleOffset = 10;
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


    private GuiButton close,
    skill_health,
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
        player = mc.player;
        skillsObj = player.getCapability(CAPABILITY_SKILL, null);


        this.setVariables();
        this.drawSkillTextures();
        this.drawSkillTitles();
        this.drawSkillOverview();

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch(button.id){
            case BUTTON_CLOSE:
                mc.displayGuiScreen(null);
                break;
            case BUTTON_SKILL_HEALTH:
                PacketHandler.INSTANCE.sendToServer(new PacketAbilitySkill(Ability.HEALTH));
                break;
            case BUTTON_SKILL_LUCK:
                PacketHandler.INSTANCE.sendToServer(new PacketAbilitySkill(Ability.LUCK));
                break;
            case BUTTON_SKILL_MOVEMENT_SPEED:
                PacketHandler.INSTANCE.sendToServer(new PacketAbilitySkill(Ability.MOVEMENT_SPEED));
                break;
            case BUTTON_SKILL_ATTACK_DAMAGE:
                PacketHandler.INSTANCE.sendToServer(new PacketAbilitySkill(Ability.ATTACK_DAMAGE));
                break;
            case BUTTON_SKILL_ATTACK_SPEED:
                PacketHandler.INSTANCE.sendToServer(new PacketAbilitySkill(Ability.ATTACK_SPEED));
                break;
        }
        super.actionPerformed(button);
    }

    @Override
    public void updateScreen() {
        if(skillsObj.getSkillpoints() < 1){
            skill_health.enabled = false;
            skill_luck.enabled = false;
            skill_movement_speed.enabled = false;
            skill_attack_damage.enabled = false;
            skill_attack_speed.enabled = false;
        } else {
            skill_health.enabled = true;
            skill_luck.enabled = true;
            skill_movement_speed.enabled = true;
            skill_attack_damage.enabled = true;
            skill_attack_speed.enabled = true;
        }
        super.updateScreen();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    private void setVariables() {

        centerX = width / 2 - allSkillsWidth / 2;
        centerY = height / 2 - skillHeight / 2;

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
        drawCenteredString(fontRenderer, I18n.format("danger.health"), centerOnHealth, centerY + titleOffset, fontColor);
        drawCenteredString(fontRenderer, I18n.format("danger.luck"), centerOnLuck, centerY + titleOffset, fontColor);
        drawCenteredString(fontRenderer, I18n.format("danger.movement.speed"), centerOnMovement, centerY + titleOffset, fontColor);
        drawCenteredString(fontRenderer, I18n.format("danger.attack.damage"), centerOnAttackDamage, centerY + titleOffset, fontColor);
        drawCenteredString(fontRenderer, I18n.format("danger.attack.speed"), centerOnAttackSpeed, centerY + titleOffset, fontColor);
    }

    private void drawSkillOverview(){
        drawCenteredString(fontRenderer, Integer.toString(skillsObj.getHealth()), centerOnHealth, centerY + skillStringOffset, fontColor );
        drawCenteredString(fontRenderer, Integer.toString(skillsObj.getLuck()), centerOnLuck, centerY + skillStringOffset, fontColor );
        drawCenteredString(fontRenderer, Integer.toString(skillsObj.getMovementSpeed()), centerOnMovement, centerY + skillStringOffset, fontColor );
        drawCenteredString(fontRenderer, Integer.toString(skillsObj.getAttackDamage()), centerOnAttackDamage, centerY + skillStringOffset, fontColor );
        drawCenteredString(fontRenderer, Integer.toString(skillsObj.getAttackSpeed()), centerOnAttackSpeed, centerY + skillStringOffset, fontColor );

    }

    private void setButtons() {
        buttonList.clear();
        buttonList.add(close = new GuiButton(BUTTON_CLOSE, centerX, centerY, 100, 20, I18n.format("danger.button.close")));
        buttonList.add(skill_health = new GuiButton(BUTTON_SKILL_HEALTH, centerOnHealth, skillButtonOffset,100,20,I18n.format("danger.button.skill")));
        buttonList.add(skill_luck = new GuiButton(BUTTON_SKILL_LUCK, centerOnLuck, skillButtonOffset,100,20,I18n.format("danger.button.skill")));
        buttonList.add(skill_movement_speed = new GuiButton(BUTTON_SKILL_MOVEMENT_SPEED, centerOnMovement, skillButtonOffset,100,20,I18n.format("danger.button.skill")));
        buttonList.add(skill_attack_damage = new GuiButton(BUTTON_SKILL_ATTACK_DAMAGE, centerOnAttackDamage, skillButtonOffset,100,20,I18n.format("danger.button.skill")));
        buttonList.add(skill_attack_speed = new GuiButton(BUTTON_SKILL_ATTACK_SPEED, centerOnAttackSpeed, skillButtonOffset,100,20,I18n.format("danger.button.skill")));
    }

}
