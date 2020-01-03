/**
 * This class was created by Workbench
 * File created at [Jan 10, 2019, 14:51 UTC + 7]
 */
package workbench.botanianeedsit;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import workbench.botanianeedsit.common.block.ManaChargerBlock;
import workbench.botanianeedsit.common.core.proxy.IProxy;
import workbench.botanianeedsit.common.item.ManaCapacitorItem;
import workbench.botanianeedsit.common.lexicon.LexiconIntegration;
import workbench.botanianeedsit.common.tile.ManaChargerTile;

@Mod.EventBusSubscriber(modid = BotaniaNeedsTheseThings.MOD_ID)
@Mod(modid = BotaniaNeedsTheseThings.MOD_ID, name = BotaniaNeedsTheseThings.MOD_NAME, dependencies = "required-after:botania@[r1.10,);")
public class BotaniaNeedsTheseThings {
    public static final String MOD_ID = "botanianeedsit";
    public static final String MOD_NAME = "Botania Needs These Things!";

    @SidedProxy(serverSide = "workbench.botanianeedsit.common.core.proxy.CommonProxy", clientSide = "workbench.botanianeedsit.client.core.proxy.ClientProxy")
    public static IProxy proxy;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        LexiconIntegration.init();
    }

    @SubscribeEvent
    public static void init(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(
                new ManaChargerBlock().
                        setRegistryName(MOD_ID, ModObjects.MANA_CHARGER_BLOCK_NAME).
                        setTranslationKey(ModObjects.MANA_CHARGER_BLOCK_NAME).
                        setCreativeTab(ModObjects.creativeTab)
        );

        registerTileEntities();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        System.out.println(ModObjects.MANA_CHARGER_BLOCK);
        event.getRegistry().registerAll(
                new ManaCapacitorItem(Config.manasteelManaCapacitorVolume).
                        setRegistryName(MOD_ID, ModObjects.MANA_CAPACITOR_MANASTEEL_NAME).
                        setTranslationKey(ModObjects.MANA_CAPACITOR_MANASTEEL_NAME).
                        setCreativeTab(ModObjects.creativeTab),
                new ManaCapacitorItem(Config.elementiumManaCapacitorVolume).
                        setRegistryName(MOD_ID, ModObjects.MANA_CAPACITOR_ELEMENTIUM_NAME).
                        setTranslationKey(ModObjects.MANA_CAPACITOR_MANASTEEL_NAME).
                        setCreativeTab(ModObjects.creativeTab),
                new ManaCapacitorItem(Config.terrasteelManacapacitorVolume).
                        setRegistryName(MOD_ID, ModObjects.MANA_CAPACITOR_TERRASTEEL_NAME).
                        setTranslationKey(ModObjects.MANA_CAPACITOR_TERRASTEEL_NAME).
                        setCreativeTab(ModObjects.creativeTab),

                new ItemBlock(ModObjects.MANA_CHARGER_BLOCK).
                        setRegistryName(ModObjects.MANA_CHARGER_BLOCK.getRegistryName()).
                        setTranslationKey(ModObjects.MANA_CHARGER_BLOCK_NAME).
                        setCreativeTab(ModObjects.creativeTab)
        );
    }

    public static void registerTileEntities() {
        GameRegistry.registerTileEntity(ManaChargerTile.class, new ResourceLocation(MOD_ID, ModObjects.MANA_CHARGER_BLOCK_NAME));
    }
}
