package com.example.wallethub.config;

import org.aeonbits.owner.ConfigCache;

public final class ConfigurationManager {
    private ConfigurationManager() {}
    
    public static Configuration configuration() {
        return ConfigCache.getOrCreate(Configuration.class);
    }
}