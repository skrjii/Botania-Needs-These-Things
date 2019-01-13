/**
 * This class was created by Workbench
 * File created at [Jan 10, 2019, 17:11 UTC + 7]
 */
package workbench.botanianeedsit.common.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import vazkii.botania.api.item.IManaDissolvable;
import vazkii.botania.api.mana.IManaPool;
import workbench.botanianeedsit.lib.Lib;

public class ItemManaCapacitor extends ItemBase implements IManaDissolvable {
    public final CapacitorType type;

    public ItemManaCapacitor(String name, CapacitorType type, boolean registerCreativeTab) {
        super(name, registerCreativeTab);
        setHasSubtypes(true);
        this.type = type;
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
        if (!item.world.isRemote) {
            if (!item.isDead) {
                boolean charged = stack.getMetadata() == 0;
                int mult = charged ? 1 : -1;
                if (pool.isOutputtingPower()) {
                    if (!charged) {
                        if (pool.getCurrentMana() - this.type.mana >= 0) {
                            processCharge(pool, stack, item, charged, mult);
                        }

                    }
                } else {
                    if (charged) {
                        if (!pool.isFull()) {
                            processCharge(pool, stack, item, charged, mult);
                        }
                    }
                }
            }
        }
    }

    public void processCharge(IManaPool pool, ItemStack stack, EntityItem item, boolean charged, int mult) {
        pool.recieveMana(this.type.mana * mult);
        stack.shrink(1);
        item.world.spawnEntity(new EntityItem(item.world,
                item.posX,
                item.posY,
                item.posZ,
                new ItemStack(this, 1, charged ? 1 : 0)));
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        return stack.getMetadata() == 0 ? super.getTranslationKey(stack) + "Charged" : super.getTranslationKey(stack);
    }

    @Override
    public void registerModels() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(new ResourceLocation(Lib.General.MOD_ID, getRegistryName().getPath() + "charged"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(this, 1, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    public enum CapacitorType {
        MANA_STEEL(1000),
        ELEMENTIUM(10000),
        TERRA_STEEL(100000);
        public final int mana;

        CapacitorType(int mana) {
            this.mana = mana;
        }
    }
}
