package de.nierhain.danger.blocks;

import de.nierhain.danger.Danger;
import de.nierhain.danger.worlddata.SafePoint;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSafeBeacon extends Block {

    public BlockSafeBeacon() {
        super(Material.IRON);
        setRegistryName(new ResourceLocation(Danger.MODID, "safe_beacon"));
        setUnlocalizedName(Danger.MODID + ".safe_beacon");
        setCreativeTab(Danger.tabDangerLiesAhead);
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        super.onBlockAdded(world, pos, state);
        SafePoint safePoint = SafePoint.get(world);
        safePoint.markDirty();
        safePoint.addSafePoint(pos);

        TextComponentString safeMessage = new TextComponentString(String.format("You feel safer around: %d, %d, %d", pos.getX(), pos.getY(), pos.getZ()));
        for (Entity player : world.playerEntities) {
            player.sendMessage(safeMessage);
        }
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}