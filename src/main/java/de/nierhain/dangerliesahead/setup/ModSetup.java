package de.nierhain.dangerliesahead.setup;

import de.nierhain.dangerliesahead.capabilities.CapabilityLevel;
import de.nierhain.dangerliesahead.capabilities.ILevel;
import de.nierhain.dangerliesahead.registry.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class ModSetup {


    public ItemGroup itemGroup = new ItemGroup("dangerliesahead") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.SAFEBEACON);
        }
    };

    public static void init() {
        CapabilityManager.INSTANCE.register(ILevel.class, new CapabilityLevel.LevelStorage(), CapabilityLevel::new);
    }
}
