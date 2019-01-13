/**
 * This class was created by Workbench
 * File created at [Jan 13, 2019, 17:16 UTC + 7]
 */
package workbench.botanianeedsit;

import workbench.botanianeedsit.lib.Lib;

@net.minecraftforge.common.config.Config(modid = Lib.General.MOD_ID)
public class Config {
    @net.minecraftforge.common.config.Config.RequiresMcRestart
    public static boolean allowCapacitors = true;
}
