package org.example.utils;

import java.util.ResourceBundle;

public class ConfigReader {

    public static ResourceBundle config = ResourceBundle.getBundle("requestUserConfig");

    public static final String NAME = config.getString("name");

    public static final String JOB = config.getString("job");

    public static final String JOB_UPDATE = config.getString("jobUpdate");
}
