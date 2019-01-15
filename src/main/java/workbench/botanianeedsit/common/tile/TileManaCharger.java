/**
 * This class was created by Workbench
 * File created at [Jan 11, 2019, 1:51 UTC + 7]
 */
package workbench.botanianeedsit.common.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.mana.IManaPool;
import workbench.botanianeedsit.ModBlocks;
import workbench.botanianeedsit.lib.Lib;

import javax.annotation.Nullable;

public class TileManaCharger extends TileSimpleInventory implements ITickable {
    public static final int RATE = 1000;
    public ItemStack _stackIn;
    public int _rotation;

    @Override
    public int getInventorySize() {
        return 1;
    }

    @Override
    public void update() {
        if (world.isRemote) return;

        ItemStack stack = itemHandler.getStackInSlot(0);
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

    @Override
    public ItemStackHandler createItemstackHandler() {
        return new ItemstackHandler(this, true){
            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }
        };
    }

    @Override
    public void onContentChanged() {
        if (!itemHandler.getStackInSlot(0).isEmpty())
            initItem();
        markDirty();
        world.notifyBlockUpdate(pos, ModBlocks.blockManaCharger.getDefaultState(), ModBlocks.blockManaCharger.getDefaultState(), 3);
    }

    public boolean handleClick(EntityPlayer playerIn, EnumHand hand) {
        if (playerIn.isSneaking()) return false;

        ItemStack heldItem = playerIn.getHeldItem(hand);
        if (!heldItem.isEmpty() && heldItem.getItem() instanceof IManaItem) {
            ItemStack chargerStack = itemHandler.getStackInSlot(0);
            if (chargerStack.isEmpty()) {
                ItemStack newStack = heldItem.copy();
                newStack.setCount(1);
                itemHandler.setStackInSlot(0, newStack);
                heldItem.shrink(1);
                return true;
            } else return false;
        } else if (heldItem.isEmpty() && !itemHandler.getStackInSlot(0).isEmpty()) {
            ItemHandlerHelper.giveItemToPlayer(playerIn, itemHandler.getStackInSlot(0).copy());
            itemHandler.setStackInSlot(0, ItemStack.EMPTY);
            return true;
        } else return false;
    }

    public int getComparatorOutput() {
        ItemStack stack = itemHandler.getStackInSlot(0).copy();
        if (stack.isEmpty())
            return 0;

        IManaItem manaItem = (IManaItem) stack.getItem();
        int currentMana = manaItem.getMana(stack);
        int maxMana = manaItem.getMaxMana(stack);

        if (maxMana < 1 || currentMana < 1) return 1;

        return 1 + (int) ((currentMana / (float) maxMana) * 14) + 1;
    }

    @Override
    public void onBlockBreak() {
        ItemStack stack = itemHandler.getStackInSlot(0);
        if (!stack.isEmpty()) {
            itemHandler.setStackInSlot(0, ItemStack.EMPTY);
            InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
        }
    }

    @Override
    public void readPacketNBT(NBTTagCompound compound) {
        super.readPacketNBT(compound);
        if (!itemHandler.getStackInSlot(0).isEmpty())
            initItem();
    }

    protected void initItem() {
        this._stackIn = itemHandler.getStackInSlot(0).copy();
        this._rotation = Lib.RANDOM.nextInt(360);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(getItemHandler()) :
                super.getCapability(capability, facing);
    }
}
