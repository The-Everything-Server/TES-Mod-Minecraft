package com.thesaltynewfie.tesmod.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesaltynewfie.tesmod.global.global;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class ConfigHelper {
    public static Config config;
    private static String data = "";
    private static ObjectMapper mapper = new ObjectMapper();

    public static void GetConfig() {
        try {
            File conf = new File("tes_config.json");
            Scanner reader = new Scanner(conf);

            while(reader.hasNextLine()) {
                data = data + reader.nextLine();
            }

            reader.close();

            config = mapper.readValue(data, Config.class);
        } catch(Exception e) {
            global.LOGGER.error(e.toString());
        }
    }

    public static void CreateConfig() {
        try {
            FileWriter writer = new FileWriter("tes_config.json");

            writer.write(mapper.writeValueAsString(config));
        } catch (Exception e) {
            global.LOGGER.error(e.toString());
        }
    }
}
