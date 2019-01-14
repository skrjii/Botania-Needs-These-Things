/**
 * This class was created by Workbench
 * File created at [Jan 13, 2019, 17:16 UTC + 7]
 */
package workbench.botanianeedsit;

import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import workbench.botanianeedsit.lib.Lib;

@net.minecraftforge.common.config.Config(modid = Lib.General.MOD_ID)
@Mod.EventBusSubscriber
public class Config {
    @net.minecraftforge.common.config.Config.RequiresMcRestart
    @net.minecraftforge.common.config.Config.LangKey("config.allowCapacitors.name")
    @net.minecraftforge.common.config.Config.Comment("Allow you to craft Manacapacitors")
    public static boolean allowCapacitors = true;

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent event) {
        ConfigManager.sync(Lib.General.MOD_ID, net.minecraftforge.common.config.Config.Type.INSTANCE);
    }
}
