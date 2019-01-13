/**
 * This class was created by Workbench
 * File created at [Jan 11, 2019, 1:52 UTC + 7]
 */
package workbench.botanianeedsit.common.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class TileSimpleInventory extends TileBase {
    protected ItemStackHandler itemHandler = createItemstackHandler();

    public IItemHandlerModifiable getItemHandler() {
        return itemHandler;
    }

    public abstract int getInventorySize();

    public ItemStackHandler createItemstackHandler() {
        return new ItemstackHandler(this, true);
    }

    public void onContentChanged() {
        this.markDirty();
    }

    public void onBlockBreak() {}

    @Override
    public void writePacketNBT(NBTTagCompound compound) {
        super.writePacketNBT(compound);
        compound.merge(itemHandler.serializeNBT());
    }

    @Override
    public void readPacketNBT(NBTTagCompound compound) {
        super.readPacketNBT(compound);
        this.itemHandler = createItemstackHandler();
        itemHandler.deserializeNBT(compound);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ?
                CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemHandler) :
                super.getCapability(capability, facing);
    }

    public static class ItemstackHandler extends ItemStackHandler {
        public final boolean _allowWrite;
        public final TileSimpleInventory _tile;

        public ItemstackHandler(TileSimpleInventory tile, boolean allowWrite) {
            super(tile.getInventorySize());
            this._tile = tile;
            this._allowWrite = allowWrite;
        }

        @Nonnull
        @Override
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
            if (_allowWrite) {
                return super.insertItem(slot, stack, simulate);
            } else return stack;
        }

        @Nonnull
        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            if (_allowWrite) {
                return super.extractItem(slot, amount, simulate);
            } else return ItemStack.EMPTY;
        }

        @Override
        protected void onContentsChanged(int slot) {
            _tile.onContentChanged();
        }
    }
}
