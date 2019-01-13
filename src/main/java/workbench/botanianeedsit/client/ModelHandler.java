/**
 * This class was created by Workbench
 * File created at [Jan 10, 2019, 21:20 UTC + 7]
 */
package workbench.botanianeedsit.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import workbench.botanianeedsit.lib.Lib;

import java.util.function.IntFunction;

@Mod.EventBusSubscriber
public class ModelHandler {
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        Item.REGISTRY.forEach(item -> {
            if (item instanceof IModelRegister) ((IModelRegister) item).registerModels();
        });

        Block.REGISTRY.forEach(block -> {
            if (block instanceof IModelRegister) ((IModelRegister) block).registerModels();
        });
    }

    public static void registerItemMetas(Item item, int maxExclusive, IntFunction<String> metaToName) {
        for (int i = 0; i < maxExclusive; i++) {
            ModelLoader.setCustomModelResourceLocation(
                    item, i,
                    new ModelResourceLocation(Lib.General.MOD_PREFIX + metaToName.apply(i), "inventory")
            );
        }
    }

    public interface IModelRegister {
        void registerModels();
    }
}
