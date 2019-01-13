/**
 * This class was created by Workbench
 * File created at [Jan 10, 2019, 18:03 UTC + 7]
 */
package workbench.botanianeedsit.client.core.proxy;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import workbench.botanianeedsit.Mod;
import workbench.botanianeedsit.client.render.RendererManaCharger;
import workbench.botanianeedsit.common.core.proxy.CommonProxy;
import workbench.botanianeedsit.common.tile.TileManaCharger;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        bindTESRs();
    }

    public void bindTESRs() {
//        Mod.logger.error("bind TESRs");
        ClientRegistry.bindTileEntitySpecialRenderer(TileManaCharger.class, new RendererManaCharger());
    }
}
