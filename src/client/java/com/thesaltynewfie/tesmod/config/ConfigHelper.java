package com.thesaltynewfie.tesmod.config;

import com.thesaltynewfie.tesmod.global.global;
import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;

import java.io.File;
import java.io.FileWriter;

public class ConfigHelper {
    private INIConfiguration config;
    private final File configFile;

    public ConfigHelper(File configFile) {
        this.configFile = configFile;
        loadConfig();
    }

    private void loadConfig() {
        Configurations configs = new Configurations();
        try {
            if (configFile.exists()) {
                config = configs.ini(configFile);
            } else {
                config = new INIConfiguration();
                saveConfig();
            }
        } catch (Exception e) {
            global.LOGGER.error(e.toString());
        }
    }

    public void saveConfig() {
        try {
            config.write(new FileWriter(configFile));
        } catch (Exception e) {
            global.LOGGER.error(e.toString());
        }
    }

    public void setValue(String section, String key,  String value) {
        config.setProperty(section + "." + key, value);
        saveConfig();
    }

    public String getValue(String section, String key, String defaultValue) {
        return config.getString(section + "." + key, defaultValue);
    }
}
