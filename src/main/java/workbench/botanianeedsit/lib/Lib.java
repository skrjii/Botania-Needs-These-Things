/**
 * This class was created by Workbench
 * File created at [Jan 10, 2019, 14:53 UTC + 7]
 */
package workbench.botanianeedsit.lib;

import java.util.Random;

public final class Lib {
    public static final Random RANDOM = new Random();

    public static final class General {
        public static final String MOD_ID = "botanianeedsit";
        public static final String MOD_NAME = "Botania Needs These Things!";
        public static final String DEPENDENCIES = "required-after:botania@[r1.10,);";
        public static final String PROXY_SERVER = "workbench.botanianeedsit.common.core.proxy.CommonProxy";
        public static final String PROXY_CLIENT = "workbench.botanianeedsit.client.core.proxy.ClientProxy";
    }

    public static final class Names {
        public static final String blockManaCharger = "blockManaCharger";
        public static final String tileManaCharger = "tileManaCharger";
        public static final String manasteelCapacitor = "manaSteelManaCapacitor";
        public static final String elementiumCapacitor = "elementiumManaCapacitor";
        public static final String terrasteelCapacitor = "terraSteelManaCapacitor";
    }

    public static final class Lexicon {
        public static final String manaChargerEntryName = "lexicon.manaChargerEntry.name";
        public static final String manaChargerEntryPage = "lexicon.manaChargerEntry.page";
        public static final String manaCapacitorsEntryName = "lexicon.manacapacitorsEntry.name";
        public static final String manaCapacitorsEntryPage = "lexicon.manacapacitorsEntry.page";
    }
}
