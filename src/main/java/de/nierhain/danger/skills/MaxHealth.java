package de.nierhain.danger.skills;

import javax.annotation.Nullable;

import de.nierhain.danger.api.level.IMaxHealth;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class MaxHealth implements IMaxHealth{
	
	protected static final String MODIFIER_NAME = "Bonus Max Health";
	
	protected static final float MIN_AMOUNT = 2.0f;
	
	private final EntityPlayer entity;
	
	private float bonusMaxHealth;
	
	public MaxHealth(@Nullable EntityPlayer entity) {
		this.entity = entity;
	}
	
	@Override
	public float getBonusMaxHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setBonusMaxHealth(float bonus) {
		// TODO Auto-generated method stub
		
	}

}
