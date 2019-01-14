/**
 * This class was created by Workbench
 * File created at [Jan 11, 2019, 23:14 UTC + 7]
 */
package workbench.botanianeedsit.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import workbench.botanianeedsit.ModBlocks;
import workbench.botanianeedsit.common.tile.TileManaCharger;

public class RendererManaCharger extends TileEntitySpecialRenderer<TileManaCharger> {
    @Override
    public void render(TileManaCharger charger, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        if(!charger.getWorld().isBlockLoaded(charger.getPos(), false)
                || charger.getWorld().getBlockState(charger.getPos()).getBlock() != ModBlocks.blockManaCharger)
            return;

        ItemStack stack = charger.getItemHandler().getStackInSlot(0);
        if (!stack.isEmpty()) {
            GlStateManager.pushMatrix();
            {
                GlStateManager.translate(x, y, z);
                GlStateManager.rotate(90, 1, 0, 0);
                GlStateManager.scale(0.5f, 0.5f, 0.5f);
                GlStateManager.translate(1, 1, -0.4f);
                GlStateManager.rotate(charger._rotation, 0, 0, 1);
                Minecraft.getMinecraft().getRenderItem().renderItem(charger._stackIn, ItemCameraTransforms.TransformType.FIXED);
            }
            GlStateManager.popMatrix();
        }
    }
}
