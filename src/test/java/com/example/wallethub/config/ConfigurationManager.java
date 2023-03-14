package com.example.wallethub.config;

import org.aeonbits.owner.ConfigCache;

public final class ConfigurationManager {
    private ConfigurationManager() {}

    /**
     * @return an instance of Configuration class from an internal cache
     * @see <a href="http://owner.aeonbits.org/docs/singleton">reference</a>
     */
    public static Configuration configuration() {
        return ConfigCache.getOrCreate(Configuration.class);
    }
}