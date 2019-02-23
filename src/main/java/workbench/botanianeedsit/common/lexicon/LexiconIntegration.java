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
import workbench.botanianeedsit.lib.Lib;

public class LexiconIntegration {
    public static LexiconEntry capacitorEntry;
    public static LexiconEntry manaChargerEntry;

    public static void init() {
        if (Config.allowCapacitors) {
            capacitorEntry = new LexiconEntry(Lib.Lexicon.manaCapacitorsEntryName, BotaniaAPI.categoryMana);
            capacitorEntry.setLexiconPages(BotaniaAPI.internalHandler.textPage(Lib.Lexicon.manaCapacitorsEntryPage + "0"),
                    BotaniaAPI.internalHandler.craftingRecipePage(Lib.Lexicon.manaCapacitorsEntryPage + "1", new ResourceLocation(Lib.General.MOD_ID, "manasteel_manacapacitor")),
                    BotaniaAPI.internalHandler.craftingRecipePage(Lib.Lexicon.manaCapacitorsEntryPage + "2", new ResourceLocation(Lib.General.MOD_ID, "elementium_manacapacitor")),
                    BotaniaAPI.internalHandler.craftingRecipePage(Lib.Lexicon.manaCapacitorsEntryPage + "3", new ResourceLocation(Lib.General.MOD_ID, "terrasteel_manacapacitor"))).
                    setIcon(new ItemStack(ModBlocks.manasteelCapacitor));

            BotaniaAPI.addEntry(capacitorEntry, BotaniaAPI.categoryMana);
        }

        manaChargerEntry = new LexiconEntry(Lib.Lexicon.manaChargerEntryName, BotaniaAPI.categoryMana);
        manaChargerEntry.setLexiconPages(BotaniaAPI.internalHandler.textPage(Lib.Lexicon.manaChargerEntryPage + "0"),
                BotaniaAPI.internalHandler.craftingRecipePage(Lib.Lexicon.manaChargerEntryPage + "1", new ResourceLocation(Lib.General.MOD_ID, "mana_charger"))).
                setIcon(new ItemStack(ModBlocks.blockManaCharger));

        BotaniaAPI.addEntry(manaChargerEntry, BotaniaAPI.categoryMana);
    }
}
