/**
 * This class was created by Workbench
 * File created at [Jan 03, 2020, 19:27 UTC + 7]
 */
package workbench.botanianeedsit;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import workbench.botanianeedsit.common.item.ManaCapacitorItem;

import static workbench.botanianeedsit.BotaniaNeedsTheseThings.MOD_ID;

public class ModObjects {
    public static CreativeTabs creativeTab = new CreativeTabs(MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModObjects.MANA_CAPACITOR_TERRASTEEL);
        }
    };

    public static final String MANA_CHARGER_BLOCK_NAME = "mana_charger";
    public static final String MANA_CAPACITOR_MANASTEEL_NAME = "mana_capacitor_manasteel";
    public static final String MANA_CAPACITOR_ELEMENTIUM_NAME = "mana_capacitor_elementium";
    public static final String MANA_CAPACITOR_TERRASTEEL_NAME = "mana_capacitor_terrasteel";

    @GameRegistry.ObjectHolder(MOD_ID + ':' + MANA_CHARGER_BLOCK_NAME)
    public static final Block MANA_CHARGER_BLOCK = null;

    @GameRegistry.ObjectHolder(MOD_ID + ':' + MANA_CAPACITOR_MANASTEEL_NAME)
    public static final ManaCapacitorItem MANA_CAPACITOR_MANASTEEL = null;
    @GameRegistry.ObjectHolder(MOD_ID + ':' + MANA_CAPACITOR_ELEMENTIUM_NAME)
    public static final ManaCapacitorItem MANA_CAPACITOR_ELEMENTIUM = null;
    @GameRegistry.ObjectHolder(MOD_ID + ':' + MANA_CAPACITOR_TERRASTEEL_NAME)
    public static final ManaCapacitorItem MANA_CAPACITOR_TERRASTEEL = null;
}
