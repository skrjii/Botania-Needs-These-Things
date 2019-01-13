/**
 * This class was created by Workbench
 * File created at [Jan 11, 2019, 23:14 UTC + 7]
 */
package workbench.botanianeedsit.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.animation.FastTESR;
import workbench.botanianeedsit.ModBlocks;
import workbench.botanianeedsit.common.tile.TileManaCharger;

import javax.annotation.Nonnull;

public class RendererManaCharger extends FastTESR<TileManaCharger> {
    @Override
    public void renderTileEntityFast(@Nonnull TileManaCharger charger, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
        if(!charger.getWorld().isBlockLoaded(charger.getPos(), false)
                || charger.getWorld().getBlockState(charger.getPos()).getBlock() != ModBlocks.blockManaCharger)
            return;

        ItemStack stack = charger.getItemHandler().getStackInSlot(0);
        if (!stack.isEmpty()) {
            GlStateManager.pushMatrix();
            {
                GlStateManager.translate(x + 0.5, y, z + 0.5);
                Minecraft.getMinecraft().getRenderManager().
                        renderEntity(charger._itemEntity, 0, 0, 0,
                                0, 0, false);
            }
            GlStateManager.popMatrix();
        }
    }
}
