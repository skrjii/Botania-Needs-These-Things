/**
 * This class was created by Workbench
 * File created at [Jan 10, 2019, 17:03 UTC + 7]
 */
package workbench.botanianeedsit.common.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import workbench.botanianeedsit.ModCreativeTab;
import workbench.botanianeedsit.client.ModelHandler;
import workbench.botanianeedsit.lib.Lib;

public class ItemBase extends Item implements ModelHandler.IModelRegister {
    public ItemBase(String name, boolean registerCreativeTab) {
        setTranslationKey(name);
        setRegistryName(new ResourceLocation(Lib.General.MOD_ID, name));
        if (registerCreativeTab) {
            setCreativeTab(ModCreativeTab.INSTACE);
        }
    }

    @Override
    public void registerModels() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
