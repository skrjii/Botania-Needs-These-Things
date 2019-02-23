/**
 * This class was created by Workbench
 * File created at [Jan 10, 2019, 17:07 UTC + 7]
 */
package workbench.botanianeedsit;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import workbench.botanianeedsit.common.block.BlockManaCharger;
import workbench.botanianeedsit.common.item.ItemManaCapacitor;
import workbench.botanianeedsit.common.tile.TileManaCharger;
import workbench.botanianeedsit.lib.Lib;

@Mod.EventBusSubscriber
public class ModBlocks {
    public static final Block blockManaCharger = new BlockManaCharger(Lib.Names.blockManaCharger).setCreativeTab(ModCreativeTab.INSTACE);

    public static final Item manasteelCapacitor = new ItemManaCapacitor(ItemManaCapacitor.CapacitorType.MANA_STEEL).
            setRegistryName(Lib.Names.manasteelCapacitor).
            setTranslationKey(Lib.Names.manasteelCapacitor).
            setCreativeTab(ModCreativeTab.INSTACE);

    public static final Item elementiumCapacitor = new ItemManaCapacitor(ItemManaCapacitor.CapacitorType.ELEMENTIUM).
            setRegistryName(Lib.Names.elementiumCapacitor).
            setTranslationKey(Lib.Names.elementiumCapacitor).
            setCreativeTab(ModCreativeTab.INSTACE);

    public static final Item terrasteelCapacitor = new ItemManaCapacitor(ItemManaCapacitor.CapacitorType.TERRA_STEEL).
            setRegistryName(Lib.Names.terrasteelCapacitor).
            setTranslationKey(Lib.Names.terrasteelCapacitor).
            setCreativeTab(ModCreativeTab.INSTACE);

    @SubscribeEvent
    public static void init(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();

        registry.register(blockManaCharger);

        registerTileEntities();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        registry.register(manasteelCapacitor);
        registry.register(elementiumCapacitor);
        registry.register(terrasteelCapacitor);

        registry.register(new ItemBlock(blockManaCharger).
                setRegistryName(blockManaCharger.getRegistryName()));
    }

    public static void registerTileEntities() {
        GameRegistry.registerTileEntity(TileManaCharger.class, new ResourceLocation(Lib.General.MOD_ID, Lib.Names.tileManaCharger));
    }
}
