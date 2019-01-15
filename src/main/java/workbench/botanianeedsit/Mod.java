/**
 * This class was created by Workbench
 * File created at [Jan 10, 2019, 14:51 UTC + 7]
 */
package workbench.botanianeedsit;

import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import workbench.botanianeedsit.common.core.proxy.IProxy;
import workbench.botanianeedsit.common.lexicon.LexiconIntegration;
import workbench.botanianeedsit.lib.Lib;

@net.minecraftforge.fml.common.Mod(modid = Lib.General.MOD_ID, name = Lib.General.MOD_NAME, dependencies = Lib.General.DEPENDENCIES)
public class Mod {
    @net.minecraftforge.fml.common.Mod.Instance
    public static Mod instance;

    @SidedProxy(serverSide = Lib.General.PROXY_SERVER, clientSide = Lib.General.PROXY_CLIENT)
    public static IProxy proxy;

    public static final Logger logger = LogManager.getLogger(Lib.General.MOD_NAME);

    @net.minecraftforge.fml.common.Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @net.minecraftforge.fml.common.Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        proxy.init(event);
        LexiconIntegration.init();
    }

    @net.minecraftforge.fml.common.Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
