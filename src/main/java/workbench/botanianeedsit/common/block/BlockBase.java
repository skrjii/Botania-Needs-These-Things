/**
 * This class was created by Workbench
 * File created at [Jan 10, 2019, 15:24 UTC + 7]
 */
package workbench.botanianeedsit.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import workbench.botanianeedsit.ModCreativeTab;
import workbench.botanianeedsit.client.ModelHandler;
import workbench.botanianeedsit.lib.Lib;

public class BlockBase extends Block implements ModelHandler.IModelRegister {
    public BlockBase(String name, Material blockMaterialIn, MapColor blockMapColorIn) {
        super(blockMaterialIn, blockMapColorIn);
        setRegistryName(new ResourceLocation(Lib.General.MOD_ID, name));
        setTranslationKey(name);
    }

    public BlockBase(String name, Material materialIn) {
        this(name, materialIn, materialIn.getMaterialMapColor());
    }

    @Override
    public void registerModels() {
        if (Item.getItemFromBlock(this) != Items.AIR)
            ModelLoader.setCustomModelResourceLocation(
                    Item.getItemFromBlock(this),
                    0,
                    new ModelResourceLocation(getRegistryName(), "normal")
            );
    }
}
