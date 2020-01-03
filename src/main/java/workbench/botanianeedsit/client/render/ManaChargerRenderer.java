/**
 * This class was created by Workbench
 * File created at [Jan 11, 2019, 23:14 UTC + 7]
 */
package workbench.botanianeedsit.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import workbench.botanianeedsit.BotaniaNeedsTheseThings;
import workbench.botanianeedsit.common.tile.ManaChargerTile;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = BotaniaNeedsTheseThings.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ManaChargerRenderer extends TileEntityRenderer<ManaChargerTile> {
    @Override
    public void render(ManaChargerTile charger, double x, double y, double z, float partialTicks, int destroyStage) {
        if (!charger.getWorld().isBlockLoaded(charger.getPos()))
            return;

        ItemStack stack = charger.getItemHandler().getStackInSlot(0);
        if (stack.isEmpty())
            return;

        GlStateManager.pushMatrix();
        {
            GlStateManager.translated(x, y, z);
            GlStateManager.rotatef(90, 1, 0, 0);
            GlStateManager.scalef(0.5f, 0.5f, 0.5f);
            GlStateManager.translatef(1, 1, -0.4f);
            GlStateManager.rotatef(charger._rotation, 0, 0, 1);
            Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.FIXED);
        }
        GlStateManager.popMatrix();
    }

    @SubscribeEvent
    static void bindTesr(FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntitySpecialRenderer(ManaChargerTile.class, new ManaChargerRenderer());
    }
}
