/**
 * This class was created by Workbench
 * File created at [Jan 13, 2019, 17:16 UTC + 7]
 */
package workbench.botanianeedsit;

import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@net.minecraftforge.common.config.Config(modid = BotaniaNeedsTheseThings.MOD_ID)
@Mod.EventBusSubscriber
public class Config {
    @net.minecraftforge.common.config.Config.RequiresMcRestart
    @net.minecraftforge.common.config.Config.LangKey("config.allowCapacitors.name")
    @net.minecraftforge.common.config.Config.Comment("Allow you to craft Manacapacitors")
    public static boolean allowCapacitors = true;

    @net.minecraftforge.common.config.Config.RequiresMcRestart
    @net.minecraftforge.common.config.Config.LangKey("config.manasteelManaCapacitorVolume.name")
    @net.minecraftforge.common.config.Config.RangeInt(min = 0, max = 1000000)
    public static int manasteelManaCapacitorVolume = 1000;

    @net.minecraftforge.common.config.Config.RequiresMcRestart
    @net.minecraftforge.common.config.Config.LangKey("config.elementiumManaCapacitorVolume.name")
    @net.minecraftforge.common.config.Config.RangeInt(min = 0, max = 1000000)
    public static int elementiumManaCapacitorVolume = 10_000;

    @net.minecraftforge.common.config.Config.RequiresMcRestart
    @net.minecraftforge.common.config.Config.LangKey("config.terrasteelManacapacitorVolume.name")
    @net.minecraftforge.common.config.Config.RangeInt(min = 0, max = 1000000)
    public static int terrasteelManacapacitorVolume = 100_000;

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent event) {
        ConfigManager.sync(BotaniaNeedsTheseThings.MOD_ID, net.minecraftforge.common.config.Config.Type.INSTANCE);
    }
}
