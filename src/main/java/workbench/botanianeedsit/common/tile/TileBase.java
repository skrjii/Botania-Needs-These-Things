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
import javax.annotation.Nullable;

public class TileBase extends TileEntity {
    public NBTTagCompound writePacketNBT(NBTTagCompound compound) { return compound; }

    public void readPacketNBT(NBTTagCompound compound) {}

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        return writePacketNBT(super.writeToNBT(compound));
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        readPacketNBT(compound);
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return writePacketNBT(super.getUpdateTag());
    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag) {
        super.handleUpdateTag(tag);
        readPacketNBT(tag);
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, -999, writePacketNBT(new NBTTagCompound()));
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        super.onDataPacket(net, packet);
        readPacketNBT(packet.getNbtCompound());
    }
}
