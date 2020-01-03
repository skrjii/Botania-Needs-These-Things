/**
 * This class was created by Workbench
 * File created at [Jan 11, 2019, 1:51 UTC + 7]
 */
package workbench.botanianeedsit.common.tile;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.mana.IManaPool;
import workbench.botanianeedsit.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class ManaChargerTile extends TileEntity implements ITickableTileEntity {
    private static final int RATE = 1000;
    private static final Random rand = new Random();
    public int _rotation = rand.nextInt(360);
    private ItemHandler itemHandler;

    public ManaChargerTile() {
        super(Objects.MANA_CHARGER_TILE);
    }

    @Override
    public void tick() {
        if (world.isRemote) return;

        ItemStack stack = getItemHandler().getStackInSlot(0);
        if (stack.isEmpty()) return;

        Item item = stack.getItem();
        if (item instanceof IManaItem) {
            IManaItem manaItem = (IManaItem) item;
            TileEntity tilePool = world.getTileEntity(pos.down());

            if (tilePool instanceof IManaPool) {
                IManaPool pool = (IManaPool) tilePool;

                if (pool.isOutputtingPower()) {
                    if (manaItem.canReceiveManaFromPool(stack, tilePool) && manaItem.getMana(stack) != manaItem.getMaxMana(stack)  && pool.getCurrentMana() > 0) {
                        int mana = Math.min(manaItem.getMaxMana(stack) - manaItem.getMana(stack), RATE);
                        mana = Math.min(pool.getCurrentMana(), mana);
                        pool.recieveMana(-mana);
                        manaItem.addMana(stack, mana);
                        markDirty();
                    }
                } else {
                    if (manaItem.canExportManaToPool(stack, tilePool)) {
                        int currentManaInStack = manaItem.getMana(stack);
                        if (!pool.isFull() && currentManaInStack > 0) {
                            int mana = Math.min(currentManaInStack, RATE);
                            pool.recieveMana(mana);
                            manaItem.addMana(stack, -mana);
                            markDirty();
                        }
                    }
                }

            }
        }
    }

    public boolean handleClick(PlayerEntity playerIn, Hand hand) {
        if (playerIn.isSneaking()) return false;

        ItemStack heldItem = playerIn.getHeldItem(hand);
        IItemHandler itemHandler = getItemHandler();
        if (!heldItem.isEmpty() && heldItem.getItem() instanceof IManaItem) {
            playerIn.setHeldItem(hand, itemHandler.insertItem(0, heldItem, false));
            return true;
        } else if (heldItem.isEmpty()) {
            ItemHandlerHelper.giveItemToPlayer(playerIn, itemHandler.extractItem(0, 1, false));
            return true;
        } else return false;
    }

    public int getComparatorOutput() {
        ItemStack stack = getItemHandler().getStackInSlot(0);
        if (stack.isEmpty())
            return 0;

        IManaItem manaItem = (IManaItem) stack.getItem();
        int currentMana = manaItem.getMana(stack);
        int maxMana = manaItem.getMaxMana(stack);

        if (maxMana < 1 || currentMana < 1) return 1;

        return 1 + (int) ((currentMana / (float) maxMana) * 14) + 1;
    }

    public ItemHandler getItemHandler() {
        if (itemHandler == null) {
            itemHandler = new ItemHandler(this);
        }
        return itemHandler;
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return write(new CompoundNBT());
    }

    @Override
    public void handleUpdateTag(CompoundNBT tag) {
        read(tag);
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(pos, -999, write(new CompoundNBT()));
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        read(pkt.getNbtCompound());
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        return getItemHandler().write(super.write(compound));
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        getItemHandler().read(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return LazyOptional.of(() -> (T) getItemHandler());
        return super.getCapability(cap, side);
    }

    public static class ItemHandler implements IItemHandler {
        private final ManaChargerTile tile;
        private ItemStack item = ItemStack.EMPTY;

        public ItemHandler(ManaChargerTile tile) {
            this.tile = tile;
        }

        @Override
        public int getSlots() {
            return 1;
        }

        @Nonnull
        @Override
        public ItemStack getStackInSlot(int slot) {
            return item;
        }

        @Nonnull
        @Override
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
            if (isItemValid(slot, stack)) {
                if (!item.isEmpty())
                    return stack;
                else {
                    ItemStack ret = stack.copy();
                    ret.shrink(1);
                    if (!simulate) {
                        ItemStack toInsert = stack.copy();
                        toInsert.setCount(1);
                        item = toInsert;
                        onContentChanged();
                    }
                    return ret;
                }
            }
            return stack;
        }

        @Nonnull
        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            if (item.isEmpty())
                return ItemStack.EMPTY;
            else {
                ItemStack ret = item;
                if (!simulate) {
                    item = ItemStack.EMPTY;
                    onContentChanged();
                }
                return ret;
            }
        }

        @Override
        public int getSlotLimit(int slot) {
            return 1;
        }

        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            return stack.getItem() instanceof IManaItem;
        }

        private CompoundNBT write(CompoundNBT nbt) {
            nbt.put("Item", item.serializeNBT());
            System.out.println("write " + nbt);
            return nbt;
        }

        private void read(CompoundNBT nbt) {
            item = ItemStack.read(nbt.getCompound("Item"));
            System.out.println("read " + nbt + ' ' + item);
        }

        private void onContentChanged() {
            tile.markDirty();
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(tile);
        }
    }
}
