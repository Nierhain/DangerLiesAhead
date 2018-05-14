package de.nierhain.danger.capabilities.level;

import de.nierhain.danger.util.Reference;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.Type;

@Config(modid = Reference.MODID)
public class DefaultLevelHandler implements ILevelHandler{

	private int level = 1;
	private int xp = 0;
	private boolean hadLevelUp = false;
	
	private final static float CONSTANT_A = 1 + 1/3;
	private final static float CONSTANT_B = 8 + 1/3;
	
	@Name("Needed XP per Level up")
	@Comment("Sets the needed experience points for a level up")
	public static int[] neededXP = {10,20,30,40,50,60,70,80,90,100};
	private static int MAX_LEVEL = neededXP.length;
	
	private static void setNeededXP() {
		for(int i = 0; i < neededXP.length; i++) {
			neededXP[i] =(int) (CONSTANT_A * i * i + CONSTANT_B * i);
		}
	}
	
	private boolean isLevelUp(int xp) {
		if (this.xp + xp >= neededXP[level-1])
			return true;
		return false;
	}
	
	private boolean isMaxLevel() {
		if(level >= MAX_LEVEL) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public int getXP() {
		return xp;
	}

	@Override
	public void addXP(int xp) {
		if(!isMaxLevel())
			if(isLevelUp(xp)) {
				this.xp = this.xp + xp - neededXP[level-1];
				level++;
				hadLevelUp = true;
			} else {
				this.xp += xp;
			}
	}

	@Override
	public void setXP(int xp) {
		this.xp = xp;
	}

	@Override
	public void setLevel(int level) {
		this.level = level;
	}
	
	@Override
	public int[] getNeededXP() {
		return neededXP;
	}
	
	@Override
	public void printMaxLevel() {
		System.out.println(MAX_LEVEL);
	}
	
	@Override
	public void purgeLevel() {
		this.level = 0;
		this.xp = 0;
	}

	@Override
	public boolean hadLevelUp() {
		return hadLevelUp;
	}

	@Override
	public void setHadLevelUp(boolean state) {
		this.hadLevelUp = state;
	}
}
