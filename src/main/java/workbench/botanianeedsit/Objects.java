/**
 * This class was created by Workbench
 * File created at [Dec 29, 2019, 13:30 UTC + 7]
 */
package workbench.botanianeedsit;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;
import workbench.botanianeedsit.common.item.ManaCapacitorItem;
import workbench.botanianeedsit.common.tile.ManaChargerTile;

import static workbench.botanianeedsit.BotaniaNeedsTheseThings.MOD_ID;

public class Objects {
    public static final String MANA_CHARGER_BLOCK_NAME = "mana_charger";
    public static final String MANA_CAPACITOR_MANASTEEL_NAME = "mana_capacitor_manasteel";
    public static final String MANA_CAPACITOR_ELEMENTIUM_NAME = "mana_capacitor_elementium";
    public static final String MANA_CAPACITOR_TERRASTEEL_NAME = "mana_capacitor_terrasteel";

    @ObjectHolder(MOD_ID + ':' + MANA_CHARGER_BLOCK_NAME)
    public static final Block MANA_CHARGER_BLOCK = null;

    @ObjectHolder(MOD_ID + ':' + MANA_CHARGER_BLOCK_NAME)
    public static final TileEntityType<ManaChargerTile> MANA_CHARGER_TILE = null;

    @ObjectHolder(MOD_ID + ':' + MANA_CAPACITOR_MANASTEEL_NAME)
    public static final ManaCapacitorItem MANA_CAPACITOR_MANASTEEL = null;
    @ObjectHolder(MOD_ID + ':' + MANA_CAPACITOR_ELEMENTIUM_NAME)
    public static final ManaCapacitorItem MANA_CAPACITOR_ELEMENTIUM = null;
    @ObjectHolder(MOD_ID + ':' + MANA_CAPACITOR_TERRASTEEL_NAME)
    public static final ManaCapacitorItem MANA_CAPACITOR_TERRASTEEL = null;
}
