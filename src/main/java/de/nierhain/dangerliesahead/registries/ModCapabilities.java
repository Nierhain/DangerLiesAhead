package de.nierhain.dangerliesahead.registries;

import de.nierhain.dangerliesahead.capabilities.attributes.DefaultAttributes;
import de.nierhain.dangerliesahead.capabilities.attributes.IAttributes;
import de.nierhain.dangerliesahead.capabilities.attributes.StorageAttributes;
import de.nierhain.dangerliesahead.capabilities.level.DefaultLevel;
import de.nierhain.dangerliesahead.capabilities.level.ILevel;
import de.nierhain.dangerliesahead.capabilities.level.StorageLevel;
import de.nierhain.dangerliesahead.capabilities.spawned.DefaultSpawned;
import de.nierhain.dangerliesahead.capabilities.spawned.ISpawned;
import de.nierhain.dangerliesahead.capabilities.spawned.StorageSpawned;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class ModCapabilities {

    public static void registerCapabilities() {
        CapabilityManager.INSTANCE.register(ILevel.class, new StorageLevel(), DefaultLevel::new);
        CapabilityManager.INSTANCE.register(IAttributes.class, new StorageAttributes(), DefaultAttributes::new);
        CapabilityManager.INSTANCE.register(ISpawned.class, new StorageSpawned(), DefaultSpawned::new);
    }
}
