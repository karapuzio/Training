package edu.training.project.dao.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Dell on 05.01.2017.
 */

class ConnectorDB {
    private static final String url = "jdbc:mysql://localhost:3306/music_center";
    private static Properties prop = new Properties();

    static {
        prop.put("user", "root");
        prop.put("password", "333666999oo");
        prop.put("autoReconnect", "true");
        prop.put("characterEncoding", "UTF-8");
        prop.put("useUnicode", "true");
        prop.put("useSSL", "false");
    }

    public Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, prop);
        return connection;
    }
}
