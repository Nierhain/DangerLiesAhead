package de.nierhain.danger.items;

import de.nierhain.danger.Danger;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCreatureCompound extends Item {
    public ItemCreatureCompound(){
        setUnlocalizedName(Danger.MODID + ".creature_compound");
        setRegistryName("creature_compound");
        setCreativeTab(Danger.tabDangerLiesAhead);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
