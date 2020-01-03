/**
 * This class was created by Workbench
 * File created at [Jan 10, 2019, 17:11 UTC + 7]
 */
package workbench.botanianeedsit.common.item;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import vazkii.botania.api.item.IManaDissolvable;
import vazkii.botania.api.mana.IManaPool;
import workbench.botanianeedsit.BotaniaNeedsTheseThings;

import static workbench.botanianeedsit.BotaniaNeedsTheseThings.MOD_ID;

public class ManaCapacitorItem extends Item implements IManaDissolvable {
    public static final String TAG_CHARGED = MOD_ID + ":charged";
    private final int capacity;

    public ManaCapacitorItem(int capacity) {
        super(new Item.Properties().group(BotaniaNeedsTheseThings.itemGroup));
        addPropertyOverride(new ResourceLocation(MOD_ID, "charged"), (stack, world, living) -> isCharged(stack) ? 1 : 0);
        this.capacity = capacity;
    }

    @Override
    public void onDissolveTick(IManaPool pool, ItemStack stack, ItemEntity item) {
        if (!item.world.isRemote && item.isAlive()) {
            boolean charged = isCharged(stack);
            if (pool.isOutputtingPower()) {
                if (!charged) {
                    if (pool.getCurrentMana() - capacity >= 0) {
                        processCharge(pool, stack, item, false);
                    }

                }
            } else {
                if (charged) {
                    if (!pool.isFull()) {
                        processCharge(pool, stack, item, true);
                    }
                }
            }
        }
    }

    public void processCharge(IManaPool pool, ItemStack stack, ItemEntity item, boolean charged) {
        pool.recieveMana(capacity * (charged ? 1 : -1));
        stack.shrink(1);
        ItemStack stackToSpawn = new ItemStack(() -> this, 1);
        if (!charged) {
            stackToSpawn.getOrCreateTag().putBoolean(TAG_CHARGED, true);
        }
        InventoryHelper.spawnItemStack(item.world, item.posX, item.posY, item.posZ, stackToSpawn);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return isCharged(stack);
    }

    protected boolean isCharged(ItemStack stack) {
        return stack.hasTag() && stack.getTag().getBoolean(TAG_CHARGED);
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        return new TranslationTextComponent(getTranslationKey(stack) + (isCharged(stack) ? ".charged" : ""));
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (isInGroup(group)) {
            items.add(new ItemStack(this));
            ItemStack charged = new ItemStack(this);
            charged.getOrCreateTag().putBoolean(TAG_CHARGED, true);
            items.add(charged);
        }
    }
}
