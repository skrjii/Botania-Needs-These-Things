/**
 * This class was created by Workbench
 * File created at [Jan 10, 2019, 17:07 UTC + 7]
 */
package workbench.botanianeedsit;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import workbench.botanianeedsit.common.item.ItemManaCapacitor;
import workbench.botanianeedsit.lib.Lib;

@Mod.EventBusSubscriber
public class ModItems {
    public static final Item manaSteelManaCapacitor = new ItemManaCapacitor(Lib.Names.itemManaSteelManaCapacitor_UnlocName, ItemManaCapacitor.CapacitorType.MANA_STEEL, true);
    public static final Item elementiumManaCapacitor = new ItemManaCapacitor(Lib.Names.itemElemetiumManaCapacitor_UnlocName, ItemManaCapacitor.CapacitorType.ELEMENTIUM, true);
    public static final Item terraSteelManaCapacitor = new ItemManaCapacitor(Lib.Names.itemTerraSteelManaCapacitor_UnlocName, ItemManaCapacitor.CapacitorType.TERRA_STEEL, true);

    @SubscribeEvent
    public static void init(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.register(manaSteelManaCapacitor);
        registry.register(elementiumManaCapacitor);
        registry.register(terraSteelManaCapacitor);
        ModBlocks.registerItemBlocks(registry);
    }
}
