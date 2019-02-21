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
import workbench.botanianeedsit.common.tile.TileManaCharger;
import workbench.botanianeedsit.lib.Lib;

@Mod.EventBusSubscriber
public class ModBlocks {
    public static final Block blockManaCharger = new BlockManaCharger(Lib.Names.blockManaCharger_UnlocName).setCreativeTab(ModCreativeTab.INSTACE);

    @SubscribeEvent
    public static void init(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();

        registry.register(blockManaCharger);

        registerTileEntities();
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(blockManaCharger).
                setRegistryName(blockManaCharger.getRegistryName()));
    }

    public static void registerTileEntities() {
        GameRegistry.registerTileEntity(TileManaCharger.class, new ResourceLocation(Lib.General.MOD_ID, Lib.Names.tileManaCharger_Name));
    }
}
