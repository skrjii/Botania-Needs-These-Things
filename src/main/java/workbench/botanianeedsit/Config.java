/**
 * This class was created by Workbench
 * File created at [Dec 29, 2019, 21:42 UTC + 7]
 */
package workbench.botanianeedsit;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    static ForgeConfigSpec.IntValue mana_capacitor_manasteel_capacity;
    static ForgeConfigSpec.IntValue mana_capacitor_elementium_capacity;
    static ForgeConfigSpec.IntValue mana_capacitor_terrasteel_capacity;
    static ForgeConfigSpec.Builder COMMON_BUILDER;
    static ForgeConfigSpec COMMON_CONFIG;


    static {
        COMMON_BUILDER = new ForgeConfigSpec.Builder();
        mana_capacitor_manasteel_capacity = COMMON_BUILDER.comment("Manasteel Manacapacitor capacity").
                defineInRange("mana_capacitor_manasteel_capacity", 1_000, 0, 1_000_000);
        mana_capacitor_elementium_capacity = COMMON_BUILDER.comment("Elementium Manacapacitor capacity").
                defineInRange("mana_capacitor_elementium_capacity", 10_000, 0, 1_000_000);
        mana_capacitor_terrasteel_capacity = COMMON_BUILDER.comment("Terrasteel Manacapacitor capacity").
                defineInRange("mana_capacitor_terrasteel_capacity", 100_000, 0, 1_000_000);

        COMMON_CONFIG = COMMON_BUILDER.build();
    }
}
