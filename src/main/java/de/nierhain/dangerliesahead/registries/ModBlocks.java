package de.nierhain.dangerliesahead.registries;

import de.nierhain.dangerliesahead.blocks.BlockSafeBeacon;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {
    @GameRegistry.ObjectHolder("dangerliesahead:safe_beacon")
    public static BlockSafeBeacon blockSafeBeacon;

    @SideOnly(Side.CLIENT)
    public static void initModels(){
        blockSafeBeacon.initModel();
    }
}
