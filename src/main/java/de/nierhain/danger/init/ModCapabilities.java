package de.nierhain.danger.init;

import de.nierhain.danger.capabilities.level.ILevel;
import de.nierhain.danger.capabilities.level.Level;
import de.nierhain.danger.capabilities.level.LevelProvider;
import de.nierhain.danger.capabilities.level.LevelStorage;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class ModCapabilities {
	
	public static void registerCapabilites() {
		CapabilityManager.INSTANCE.register(ILevel.class, new LevelStorage(), Level::new);
	}
}
