package edu.training.project.controller;

import java.util.ResourceBundle;

/**
 * Created by Dell on 20.01.2017.
 */
public class MessageManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.messages");
    // class get information from file messages.properties
    private MessageManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
