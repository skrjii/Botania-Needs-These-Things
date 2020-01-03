/**
 * This class was created by Workbench
 * File created at [Jan 10, 2019, 14:51 UTC + 7]
 */
package workbench.botanianeedsit;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.IForgeRegistry;
import workbench.botanianeedsit.client.render.ManaChargerRenderer;
import workbench.botanianeedsit.common.block.ManaChargerBlock;
import workbench.botanianeedsit.common.item.ManaCapacitorItem;
import workbench.botanianeedsit.common.tile.ManaChargerTile;

import java.util.function.Consumer;

@Mod("botanianeedsit")
public class BotaniaNeedsTheseThings {
    public static final String MOD_ID = "botanianeedsit";
    public static final String MOD_NAME = "Botania needs these things!";
    public static ItemGroup itemGroup = new ItemGroup(MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(() -> Objects.MANA_CAPACITOR_TERRASTEEL);
        }
    };

    public BotaniaNeedsTheseThings() {
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(Block.class, this::registerBlocks);
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(Item.class, this::registerItems);
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(TileEntityType.class, this::registerTiles);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
        final CommentedFileConfig configData = CommentedFileConfig.
                builder(FMLPaths.CONFIGDIR.get().resolve("botania-needs-these-things-common.toml")).
                sync().
                autosave().
                writingMode(WritingMode.REPLACE).
                build();

        configData.load();
        Config.COMMON_CONFIG.setConfig(configData);
    }

    private void registerBlocks(final RegistryEvent.Register<Block> event) {
        final IForgeRegistry<Block> registry = event.getRegistry();

        registry.register(new ManaChargerBlock().setRegistryName(MOD_ID, Objects.MANA_CHARGER_BLOCK_NAME));
    }

    private void registerItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                new BlockItem(Objects.MANA_CHARGER_BLOCK, new Item.Properties().group(itemGroup)).
                        setRegistryName(MOD_ID, Objects.MANA_CHARGER_BLOCK_NAME),
                new ManaCapacitorItem(Config.mana_capacitor_manasteel_capacity.get()).
                        setRegistryName(MOD_ID, Objects.MANA_CAPACITOR_MANASTEEL_NAME),
                new ManaCapacitorItem(Config.mana_capacitor_elementium_capacity.get()).
                        setRegistryName(MOD_ID, Objects.MANA_CAPACITOR_ELEMENTIUM_NAME),
                new ManaCapacitorItem(Config.mana_capacitor_terrasteel_capacity.get()).
                        setRegistryName(MOD_ID, Objects.MANA_CAPACITOR_TERRASTEEL_NAME)
        );
    }

    private void registerTiles(final RegistryEvent.Register<TileEntityType<?>> event) {
        event.getRegistry().registerAll(
                TileEntityType.Builder.create(ManaChargerTile::new, Objects.MANA_CHARGER_BLOCK).build(null).
                        setRegistryName(MOD_ID, Objects.MANA_CHARGER_BLOCK_NAME)
        );
    }
}
