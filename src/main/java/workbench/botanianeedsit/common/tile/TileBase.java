/**
 * This class was created by Workbench
 * File created at [Jan 11, 2019, 1:49 UTC + 7]
 */
package workbench.botanianeedsit.common.tile;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class TileBase extends TileEntity {

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, @Nonnull IBlockState oldState, @Nonnull IBlockState newState) {
        return oldState.getBlock() != newState.getBlock();
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagCompound ret = super.writeToNBT(compound);
        writePacketNBT(ret);
        return ret;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        readPacketNBT(compound);
    }

    @Nonnull
    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound cmp = super.getUpdateTag();
        writePacketNBT(cmp);
        return cmp;
    }

    public void writePacketNBT(NBTTagCompound compound) {}

    public void readPacketNBT(NBTTagCompound compound) {}

//     @Override
//     public void handleUpdateTag(NBTTagCompound tag) {
//        super.handleUpdateTag(tag);
//        readPacketNBT(tag);
//     }

    @Override
    public final SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound tag = getUpdateTag();
        return new SPacketUpdateTileEntity(pos, -999, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        readPacketNBT(packet.getNbtCompound());
    }
}
