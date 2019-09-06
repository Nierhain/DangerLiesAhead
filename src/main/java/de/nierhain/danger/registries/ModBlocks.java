package de.nierhain.danger.registries;

import de.nierhain.danger.Danger;
import de.nierhain.danger.blocks.BeaconBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {
    @GameRegistry.ObjectHolder(Danger.MODID + ":beacon")
    public static BeaconBlock beaconBlock;
}
