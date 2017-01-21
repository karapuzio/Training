package edu.training.project.controller;

import java.util.ResourceBundle;

/**
 * Created by Dell on 20.01.2017.
 */
public class ConfigurationManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.config");
    // class get information from file config.properties
    private ConfigurationManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
