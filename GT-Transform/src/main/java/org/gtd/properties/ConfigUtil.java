package org.gtd.properties;

import java.io.File;

import org.gtd.properties.model.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 * Created by vm033450 on 12/18/17.
 */
public class ConfigUtil {
    private static final String SOURCE_CONFIG_PATH = "extract.yaml";

    public static void main(String[] args) {
        try {
            getConfig();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Config getConfig() throws Exception {
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            File file = new File(Config.class.getClassLoader().getResource(SOURCE_CONFIG_PATH).getFile());
            Config config = mapper.readValue(file, Config.class);
            return config;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception(ex);
        }
    }
}
