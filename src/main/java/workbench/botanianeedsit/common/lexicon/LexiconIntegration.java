/**
 * This class was created by Workbench
 * File created at [Jan 15, 2019, 10:29 UTC + 7]
 */
package workbench.botanianeedsit.common.lexicon;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.lexicon.page.PageCraftingRecipe;
import vazkii.botania.common.lexicon.page.PageText;
import workbench.botanianeedsit.ModBlocks;
import workbench.botanianeedsit.ModItems;
import workbench.botanianeedsit.lib.Lib;

public class LexiconIntegration {
    public static LexiconEntry capacitorEntry;
    public static LexiconEntry manaChargerEntry;

    public static void init() {
        capacitorEntry = new LexiconEntry(Lib.Lexicon.capacitorsEntryName, BotaniaAPI.categoryMana);
        capacitorEntry.setLexiconPages(new PageText(Lib.Lexicon.capacitorsEntryPage + "0"),
                new PageCraftingRecipe(Lib.Lexicon.capacitorsEntryPage + "1", new ResourceLocation(Lib.General.MOD_ID, "manasteel_manacapacitor")),
                new PageCraftingRecipe(Lib.Lexicon.capacitorsEntryPage + "2", new ResourceLocation(Lib.General.MOD_ID, "elementium_manacapacitor")),
                new PageCraftingRecipe(Lib.Lexicon.capacitorsEntryPage + "3", new ResourceLocation(Lib.General.MOD_ID, "terrasteel_manacapacitor"))).
                setIcon(new ItemStack(ModItems.manaSteelManaCapacitor));

        manaChargerEntry = new LexiconEntry(Lib.Lexicon.manaChargerEntryName, BotaniaAPI.categoryMana);
        manaChargerEntry.setLexiconPages(new PageText(Lib.Lexicon.manaChargerEntryPage + "0"),
                new PageCraftingRecipe(Lib.Lexicon.manaChargerEntryPage + "1", new ResourceLocation(Lib.General.MOD_ID, "mana_charger"))).
                setIcon(new ItemStack(ModBlocks.blockManaCharger));

        BotaniaAPI.addEntry(capacitorEntry, BotaniaAPI.categoryMana);
        BotaniaAPI.addEntry(manaChargerEntry, BotaniaAPI.categoryMana);
    }
}
