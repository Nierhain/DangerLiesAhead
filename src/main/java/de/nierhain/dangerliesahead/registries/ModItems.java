package de.nierhain.dangerliesahead.registries;

import de.nierhain.dangerliesahead.items.ItemCreatureCompound;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {
    @GameRegistry.ObjectHolder("dangerliesahead:creature_compound")
    public static ItemCreatureCompound creatureCompound;

    @SideOnly(Side.CLIENT)
    public static void initModels(){
        creatureCompound.initModel();
    }
}
