package de.nierhain.danger.registries;

import de.nierhain.danger.blocks.BlockSafeBeacon;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {
    @GameRegistry.ObjectHolder("danger:safe_beacon")
    public static BlockSafeBeacon blockSafeBeacon;

    @SideOnly(Side.CLIENT)
    public static void initModels(){
        blockSafeBeacon.initModel();
    }
}
