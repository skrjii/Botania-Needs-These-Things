/**
 * This class was created by Workbench
 * File created at [Jan 15, 2019, 10:29 UTC + 7]
 */
package workbench.botanianeedsit.common.lexicon;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconEntry;
import workbench.botanianeedsit.Config;
import workbench.botanianeedsit.ModBlocks;
import workbench.botanianeedsit.ModItems;
import workbench.botanianeedsit.lib.Lib;

public class LexiconIntegration {
    public static LexiconEntry capacitorEntry;
    public static LexiconEntry manaChargerEntry;

    public static void init() {
        if (Config.allowCapacitors) {
            capacitorEntry = new LexiconEntry(Lib.Lexicon.capacitorsEntryName, BotaniaAPI.categoryMana);
            capacitorEntry.setLexiconPages(BotaniaAPI.internalHandler.textPage(Lib.Lexicon.capacitorsEntryPage + "0"),
                    BotaniaAPI.internalHandler.craftingRecipePage(Lib.Lexicon.capacitorsEntryPage + "1", new ResourceLocation(Lib.General.MOD_ID, "manasteel_manacapacitor")),
                    BotaniaAPI.internalHandler.craftingRecipePage(Lib.Lexicon.capacitorsEntryPage + "2", new ResourceLocation(Lib.General.MOD_ID, "elementium_manacapacitor")),
                    BotaniaAPI.internalHandler.craftingRecipePage(Lib.Lexicon.capacitorsEntryPage + "3", new ResourceLocation(Lib.General.MOD_ID, "terrasteel_manacapacitor"))).
                    setIcon(new ItemStack(ModItems.manaSteelManaCapacitor));

            BotaniaAPI.addEntry(capacitorEntry, BotaniaAPI.categoryMana);
        }

        manaChargerEntry = new LexiconEntry(Lib.Lexicon.manaChargerEntryName, BotaniaAPI.categoryMana);
        manaChargerEntry.setLexiconPages(BotaniaAPI.internalHandler.textPage(Lib.Lexicon.manaChargerEntryPage + "0"),
                BotaniaAPI.internalHandler.craftingRecipePage(Lib.Lexicon.manaChargerEntryPage + "1", new ResourceLocation(Lib.General.MOD_ID, "mana_charger"))).
                setIcon(new ItemStack(ModBlocks.blockManaCharger));

        BotaniaAPI.addEntry(manaChargerEntry, BotaniaAPI.categoryMana);
    }
}
