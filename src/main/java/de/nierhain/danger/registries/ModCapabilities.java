package de.nierhain.danger.registries;

import de.nierhain.danger.capabilities.level.DefaultLevel;
import de.nierhain.danger.capabilities.level.ILevel;
import de.nierhain.danger.capabilities.level.StorageLevel;
import de.nierhain.danger.capabilities.skills.DefaultSkills;
import de.nierhain.danger.capabilities.skills.ISkills;
import de.nierhain.danger.capabilities.skills.StorageSkills;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class ModCapabilities {

    public static void registerCapabilities() {
        CapabilityManager.INSTANCE.register(ILevel.class, new StorageLevel(), DefaultLevel::new);
        CapabilityManager.INSTANCE.register(ISkills.class, new StorageSkills(), DefaultSkills::new);
    }
}
