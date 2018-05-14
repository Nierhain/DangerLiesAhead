package de.nierhain.danger.handler;


import de.nierhain.danger.capabilities.level.ILevelHandler;
import de.nierhain.danger.capabilities.level.ProviderLevel;
import de.nierhain.danger.capabilities.skills.ISkillHandler;
import de.nierhain.danger.capabilities.skills.ProviderSkill;
import de.nierhain.danger.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilitiesHandler {
	
	
	public static ILevelHandler getLevelHandler(Entity entity) {
		if (entity.hasCapability(ProviderLevel.CAPABILITY_LEVEL, EnumFacing.DOWN))
			return entity.getCapability(ProviderLevel.CAPABILITY_LEVEL, EnumFacing.DOWN);
	    return null;
	}
	
	public static ISkillHandler getSkillHandler(Entity entity) {
		if (entity.hasCapability(ProviderSkill.CAPABILITY_SKILL, null))
			return entity.getCapability(ProviderSkill.CAPABILITY_SKILL, null);
		return null;
	}
	
	@SubscribeEvent
    public void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        
        if (event.getObject() instanceof EntityPlayer)
            event.addCapability(new ResourceLocation(Reference.MODID, "level"), new ProviderLevel());
        	event.addCapability(new ResourceLocation(Reference.MODID, "skill"), new ProviderSkill());
    }
	
	@SubscribeEvent
	public void clonePlayer(PlayerEvent.Clone event) {
		final ILevelHandler original = getLevelHandler(event.getOriginal());
		final ILevelHandler clone = getLevelHandler(event.getEntity());
		clone.setLevel(original.getLevel());
		clone.setXP(original.getXP());
		
		final ISkillHandler originalSkill = getSkillHandler(event.getOriginal());
		final ISkillHandler cloneSkill = getSkillHandler(event.getEntity());
		cloneSkill.setAvailableSkillPoints(originalSkill.getAvailableSkillPoints());
		cloneSkill.setUsedSkillPoints(originalSkill.getUsedSkillPoints());
	}
}
