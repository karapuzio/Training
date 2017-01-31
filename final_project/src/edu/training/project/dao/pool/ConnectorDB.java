package edu.training.project.dao.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Stores a properties to get connections and gets connection to the MySQL Database.
 *
 * @author Skidan Olya
 * @version 1.0
 */
class ConnectorDB {
    private static final String URL = "jdbc:mysql://localhost:3306/music_center";
    private static Properties prop = new Properties();

    static {
        prop.put("user", "root");
        prop.put("password", "333666999oo");
        prop.put("autoReconnect", "true");
        prop.put("characterEncoding", "UTF-8");
        prop.put("useUnicode", "true");
        prop.put("useSSL", "false");
    }

    /**
     * Gets connection to the MySQL Database.
     *
     * @return a Connection object.
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, prop);
        return connection;
    }
}
