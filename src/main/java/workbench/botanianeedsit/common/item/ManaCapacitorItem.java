/**
 * This class was created by Workbench
 * File created at [Jan 10, 2019, 17:11 UTC + 7]
 */
package workbench.botanianeedsit.common.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import vazkii.botania.api.item.IManaDissolvable;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.mana.IManaPool;
import workbench.botanianeedsit.BotaniaNeedsTheseThings;
import workbench.botanianeedsit.client.ModelHandler;
import workbench.botanianeedsit.common.lexicon.LexiconIntegration;

public class ManaCapacitorItem extends Item implements IManaDissolvable, ILexiconable, ModelHandler.IModelRegister {
    private final int capacity;

    public ManaCapacitorItem(int capacity) {
        setHasSubtypes(true);
        this.capacity = capacity;
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (isInCreativeTab(tab)) {
            items.add(new ItemStack(this, 1, 0));
            items.add(new ItemStack(this, 1, 1));
        }
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return stack.getMetadata() == 0;
    }

    @Override
    public void onDissolveTick(IManaPool pool, ItemStack stack, EntityItem item) {
        if (!item.world.isRemote && !item.isDead) {
            boolean charged = stack.getMetadata() == 0;
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

    public void processCharge(IManaPool pool, ItemStack stack, EntityItem item, boolean charged) {
        pool.recieveMana(capacity * (charged ? 1 : -1));
        stack.shrink(1);
        InventoryHelper.spawnItemStack(item.world, item.posX, item.posY, item.posZ, new ItemStack(this, 1, charged ? 1 : 0));
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        return stack.getMetadata() == 0 ? super.getTranslationKey(stack) + "_charged" : super.getTranslationKey(stack);
    }

    @Override
    public void registerModels() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(new ResourceLocation(BotaniaNeedsTheseThings.MOD_ID, getRegistryName().getPath() + "_charged"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(this, 1, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public LexiconEntry getEntry(World world, BlockPos pos, EntityPlayer player, ItemStack lexicon) {
        return LexiconIntegration.capacitorEntry;
    }
}
