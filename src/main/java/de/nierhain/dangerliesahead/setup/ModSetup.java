package de.nierhain.dangerliesahead.setup;

import de.nierhain.dangerliesahead.registry.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModSetup {


    public ItemGroup itemGroup = new ItemGroup("dangerliesahead") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.SAFEBEACON);
        }
    };

    public void init() {

    }
}
