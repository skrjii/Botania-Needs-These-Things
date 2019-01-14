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
        public static final String MOD_PREFIX = MOD_ID + ":";
        public static final String MOD_NAME = "Botania Needs It";
        public static final String DEPENDENCIES = "required-after:botania@[r1.10,);";
//        public static final String DEPENDENCIES = "";
        public static final String PROXY_SERVER = "workbench.botanianeedsit.common.core.proxy.CommonProxy";
        public static final String PROXY_CLIENT = "workbench.botanianeedsit.client.core.proxy.ClientProxy";
    }

    public static final class Names {
        public static final String blockManaCharger_UnlocName = "blockManaCharger";

        public static final String itemBlockManaCharger_UnlocName = "itemBlockManaCharger";
        public static final String itemManaSteelManaCapacitor_UnlocName = "manaSteelManaCapacitor";
        public static final String itemElemetiumManaCapacitor_UnlocName = "elementiumManaCapacitor";
        public static final String itemTerraSteelManaCapacitor_UnlocName = "terraSteelManaCapacitor";

        public static final String tileManaCharger_Name = "tileManaCharger";
    }
}
