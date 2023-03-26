package softwaredesign.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a generic framework, implementing the Inversion of Control design principle.
 * The class offers the functionality of storing a number of modules that are each identified by a name property,
 * and interface segregation is applied by having the modules implement the {@link Framework.Module} interface.
 * These items (effectively "framework modules") can be registered with the {@link Framework#register(Module)} method.
 *
 * @param <T> The type of the modules this framework is managing.
 *
 * @author Zain
 */
public class Framework<T extends Framework.Module> {
    protected final Map<String, T> modules = new HashMap<>();

    /**
     * Registers a module in this framework.
     * @param module The module to register.
     * @return the Framework instance, so method calls can be (optionally) chained.
     */
    public Framework<T> register (T module) {
        this.modules.put(module.getName(), module);
        return this;
    }

    protected T getModule(String name) {
        return this.modules.get(name);
    }

    /**
     * The interface that all framework modules need to implement.
     */
    public interface Module {
        /**
         * The name of this module.
         * Framework modules are accessed and uniquely identified by this property.
         */
        String getName();
    }
}
