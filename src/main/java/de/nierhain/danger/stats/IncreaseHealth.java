package de.nierhain.danger.stats;

import java.util.UUID;

import de.nierhain.danger.util.Constants;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;

public class IncreaseHealth {
	
	
	final UUID MODIFIER_ID = UUID.fromString("Health");
	final String MODIFIER_NAME = "Increase Health";
	
	final float HEALTH_INCREMENT = 0.2f;
	
	final EntityPlayer entity;
	
	public IncreaseHealth(EntityPlayer entity) {
		this.entity = entity;
	}
	
	public void increaseHealth() {
		
		final IAttributeInstance entityMaxHealthAttribute = entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
		
		final float newAmount = entity.getMaxHealth() + HEALTH_INCREMENT;
		final float oldAmount;
		
		final AttributeModifier newModifier = new AttributeModifier(MODIFIER_ID, MODIFIER_NAME, newAmount , Constants.ATTRIBUTE_MODIFIER_OPERATION_ADD);
		final AttributeModifier oldModifier = entityMaxHealthAttribute.getModifier(MODIFIER_ID);
		
		if(oldModifier != null) {
			entityMaxHealthAttribute.removeModifier(oldModifier);
			
			oldAmount = (float) oldModifier.getAmount();
			
		} else {
			oldAmount = 2.0f;
		}
		
		
		entityMaxHealthAttribute.applyModifier(newModifier);
		
		final float amountToHeal = newAmount - oldAmount;
		if (amountToHeal > 0) {
			entity.heal(amountToHeal);
		}
	}
}
	
	
	
