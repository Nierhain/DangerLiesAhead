package de.nierhain.dangerliesahead.items;

import de.nierhain.dangerliesahead.DangerLiesAhead;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCreatureCompound extends Item {
    public ItemCreatureCompound(){
        setTranslationKey(DangerLiesAhead.MODID + ".creature_compound");
        setRegistryName("creature_compound");
        setCreativeTab(DangerLiesAhead.tabDangerLiesAhead);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
