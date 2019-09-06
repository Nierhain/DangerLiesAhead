package de.nierhain.danger.items;

import de.nierhain.danger.Danger;
import net.minecraft.item.Item;

public class ItemCreatureCompound extends Item {
    public ItemCreatureCompound(){
        setRegistryName("creature_compound");
        setUnlocalizedName(Danger.MODID + ".creature_compound");
    }
}
