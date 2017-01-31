package edu.training.project.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

/**
 * Class is used to get resources.
 *
 * @author Skidan Olya
 * @version 1.0
 */
public class ConfigurationManager {
    private static final Logger LOGGER = LogManager.getLogger(ConfigurationManager.class);
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
    // class get information from file config.properties
    private ConfigurationManager() { }
    public static String getProperty(String key) {
        LOGGER.log(Level.DEBUG, "Resource " + resourceBundle);
        return resourceBundle.getString(key);
    }
}
