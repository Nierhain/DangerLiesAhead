package de.nierhain.danger.registries;

import de.nierhain.danger.capabilities.level.DefaultLevel;
import de.nierhain.danger.capabilities.level.ILevel;
import de.nierhain.danger.capabilities.level.StorageLevel;
import de.nierhain.danger.capabilities.attributes.DefaultAttributes;
import de.nierhain.danger.capabilities.attributes.IAttributes;
import de.nierhain.danger.capabilities.attributes.StorageAttributes;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class ModCapabilities {

    public static void registerCapabilities() {
        CapabilityManager.INSTANCE.register(ILevel.class, new StorageLevel(), DefaultLevel::new);
        CapabilityManager.INSTANCE.register(IAttributes.class, new StorageAttributes(), DefaultAttributes::new);
    }
}
