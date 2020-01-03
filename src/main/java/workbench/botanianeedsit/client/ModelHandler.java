/**
 * This class was created by Workbench
 * File created at [Jan 10, 2019, 21:20 UTC + 7]
 */
package workbench.botanianeedsit.client;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import workbench.botanianeedsit.BotaniaNeedsTheseThings;

@Mod.EventBusSubscriber(modid = BotaniaNeedsTheseThings.MOD_ID, value = Side.CLIENT)
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

    public interface IModelRegister {
        void registerModels();
    }
}
