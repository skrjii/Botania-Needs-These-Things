/**
 * This class was created by Workbench
 * File created at [Jan 15, 2019, 10:29 UTC + 7]
 */
package workbench.botanianeedsit.common.lexicon;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconEntry;
import workbench.botanianeedsit.BotaniaNeedsTheseThings;
import workbench.botanianeedsit.Config;
import workbench.botanianeedsit.ModObjects;
public class LexiconIntegration {
    public static LexiconEntry capacitorEntry;
    public static LexiconEntry manaChargerEntry;

    public static void init() {
        if (Config.allowCapacitors) {
            capacitorEntry = new LexiconEntry("lexicon.manacapacitorsEntry.name", BotaniaAPI.categoryMana);
            capacitorEntry.setLexiconPages(BotaniaAPI.internalHandler.textPage("lexicon.manacapacitorsEntry.page0"),
                    BotaniaAPI.internalHandler.craftingRecipePage("lexicon.manacapacitorsEntry.page1", new ResourceLocation(BotaniaNeedsTheseThings.MOD_ID, ModObjects.MANA_CAPACITOR_MANASTEEL_NAME)),
                    BotaniaAPI.internalHandler.craftingRecipePage("lexicon.manacapacitorsEntry.page2", new ResourceLocation(BotaniaNeedsTheseThings.MOD_ID, ModObjects.MANA_CAPACITOR_ELEMENTIUM_NAME)),
                    BotaniaAPI.internalHandler.craftingRecipePage("lexicon.manacapacitorsEntry.page3", new ResourceLocation(BotaniaNeedsTheseThings.MOD_ID, ModObjects.MANA_CAPACITOR_TERRASTEEL_NAME))).
                    setIcon(new ItemStack(ModObjects.MANA_CAPACITOR_MANASTEEL));

            BotaniaAPI.addEntry(capacitorEntry, BotaniaAPI.categoryMana);
        }

        manaChargerEntry = new LexiconEntry("lexicon.manaChargerEntry.name", BotaniaAPI.categoryMana);
        manaChargerEntry.setLexiconPages(BotaniaAPI.internalHandler.textPage("lexicon.manaChargerEntry.page0"),
                BotaniaAPI.internalHandler.craftingRecipePage("lexicon.manaChargerEntry.page1", new ResourceLocation(BotaniaNeedsTheseThings.MOD_ID, ModObjects.MANA_CHARGER_BLOCK_NAME))).
                setIcon(new ItemStack(ModObjects.MANA_CHARGER_BLOCK));

        BotaniaAPI.addEntry(manaChargerEntry, BotaniaAPI.categoryMana);
    }
}
