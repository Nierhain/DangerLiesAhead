package de.nierhain.danger.blocks;

import de.nierhain.danger.Danger;
import de.nierhain.danger.worlddata.SafePoint;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

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

    }

    @Override
    public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state) {
        super.onBlockDestroyedByPlayer(world, pos, state);
        removeSafePoint(world, pos);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    private void removeSafePoint(World world, BlockPos pos){
        SafePoint safePoint = SafePoint.get(world);
        safePoint.markDirty();
        safePoint.removeSafePoint(pos);
        TextComponentString dangerMessage = new TextComponentString(String.format("The danger creeps back to %d, %d, %d", pos.getX(), pos.getY(), pos.getZ()));
        sendMessage(dangerMessage, world.playerEntities);
    }

    private void sendMessage(TextComponentString msg, List<EntityPlayer> playerEntities){
        for (Entity player : playerEntities) {
            player.sendMessage(msg);
        }
    }
}