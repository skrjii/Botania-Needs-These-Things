/**
 * This class was created by Workbench
 * File created at [Jan 11, 2019, 1:51 UTC + 7]
 */
package workbench.botanianeedsit.common.tile;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.mana.IManaPool;
import workbench.botanianeedsit.ModBlocks;

public class TileManaCharger extends TileSimpleInventory implements ITickable {
    public static final int RATE = 1000;
    public EntityItem _itemEntity;

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
                    if (manaItem.canReceiveManaFromPool(stack, tilePool) && manaItem.getMana(stack) != manaItem.getMaxMana(stack)) {
                        int mana = Math.min(manaItem.getMaxMana(stack) - manaItem.getMana(stack), RATE);
                        mana = Math.min(pool.getCurrentMana(), mana);
                        pool.recieveMana(-mana);
                        manaItem.addMana(stack, mana);
                    }
                } else {
                    if (manaItem.canExportManaToPool(stack, tilePool)) {
                        int currentManaInStack = manaItem.getMana(stack);
                        if (!pool.isFull() && currentManaInStack > 0) {
                            int mana = Math.min(currentManaInStack, RATE);
                            pool.recieveMana(mana);
                            manaItem.addMana(stack, -mana);
                        }
                    }
                }

            }
        }
    }

    @Override
    public void onContentChanged() {
        if (!itemHandler.getStackInSlot(0).isEmpty())
            initItem();
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
            playerIn.setHeldItem(hand, itemHandler.getStackInSlot(0).copy());
            itemHandler.setStackInSlot(0, ItemStack.EMPTY);
            return true;
        } else return false;
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
        this._itemEntity = new EntityItem(world, 0, 0, 0, itemHandler.getStackInSlot(0).copy());
    }

    @Override
    public boolean hasFastRenderer() {
        return true;
    }
}
