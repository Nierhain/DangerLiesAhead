package de.nierhain.dangerliesahead.blocks;

import de.nierhain.dangerliesahead.DangerLiesAhead;
import de.nierhain.dangerliesahead.tile.SafeBeaconTileEntity;
import de.nierhain.dangerliesahead.worlddata.SafePoint;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class BlockSafeBeacon extends Block implements ITileEntityProvider {

    public static final int GUI_ID = 1;

    public BlockSafeBeacon() {
        super(Material.IRON);
        setRegistryName(new ResourceLocation(DangerLiesAhead.MODID, "safe_beacon"));
        setTranslationKey(DangerLiesAhead.MODID + ".safe_beacon");
        setCreativeTab(DangerLiesAhead.tabDangerLiesAhead);
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntity te = world.getTileEntity(pos);
        if(te instanceof SafeBeaconTileEntity) ((SafeBeaconTileEntity) te).onDestroy();
        super.breakBlock(world, pos, state);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(world.isRemote) {
            return true;
        }
        TileEntity te = world.getTileEntity(pos);
        if(!(te instanceof SafeBeaconTileEntity)){
            return false;
        }
        player.sendMessage(new TextComponentString(String.format("Energy: %d/%d @%dRF/tick",((SafeBeaconTileEntity) te).energyStorage.getEnergyStored(),((SafeBeaconTileEntity) te).energyStorage.getMaxEnergyStored(),((SafeBeaconTileEntity) te).RF_PER_TICK)));
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new SafeBeaconTileEntity();
    }
}