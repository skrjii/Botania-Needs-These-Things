/**
 * This class was created by Workbench
 * File created at [Jan 13, 2019, 17:34 UTC + 7]
 */
package workbench.botanianeedsit.common.crafting;

import com.google.gson.JsonObject;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;
import workbench.botanianeedsit.Config;

import java.util.function.BooleanSupplier;

public class ManacapacitorConditionFactory implements IConditionFactory {
    @Override
    public BooleanSupplier parse(JsonContext context, JsonObject json) {
        return () -> Config.allowCapacitors;
    }
}
