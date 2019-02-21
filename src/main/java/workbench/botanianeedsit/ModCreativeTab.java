/**
 * This class was created by Workbench
 * File created at [Jan 10, 2019, 15:30 UTC + 7]
 */
package workbench.botanianeedsit;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import workbench.botanianeedsit.lib.Lib;

public class ModCreativeTab extends CreativeTabs {
    public static final ModCreativeTab INSTACE = new ModCreativeTab();

    public ModCreativeTab() {
        super(Lib.General.MOD_NAME);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ModBlocks.blockManaCharger);
    }
}
