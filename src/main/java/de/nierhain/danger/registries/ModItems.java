package de.nierhain.danger.registries;

import de.nierhain.danger.items.ItemCreatureCompound;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {
    @GameRegistry.ObjectHolder("danger:creature_compound")
    public static ItemCreatureCompound creatureCompound;

    @SideOnly(Side.CLIENT)
    public static void initModels(){
        creatureCompound.initModel();
    }
}
