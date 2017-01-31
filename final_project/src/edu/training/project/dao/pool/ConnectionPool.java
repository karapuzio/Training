package edu.training.project.dao.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Stores a limit number of connections to the MySQL Database.
 *
 * @author Skidan Olya
 * @version 1.0
 */
public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool instance;
    private static AtomicBoolean instanceCreated = new AtomicBoolean();
    private static final int POOL_SIZE = 5;
    private ArrayBlockingQueue<Connection> pool;

    private ConnectionPool(){
        init();
    }

    /**
     * Creates a limit number of connections with the MySQL Database.
     */
    private void init() {
        pool = new ArrayBlockingQueue<Connection>(POOL_SIZE);
        try {
            ConnectorDB connector = new ConnectorDB();
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            LOGGER.log(Level.DEBUG, "ConnectorDB" + " " + connector);
            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = null;
                connection = connector.getConnection();
                pool.offer(connection);
            }
        }catch (SQLException e){
            LOGGER.log(Level.FATAL, e, e);
            throw new RuntimeException();
        }
        LOGGER.log(Level.DEBUG, "Init connection pool" + " " + pool);
    }

    /**
     * Gets instance from ConnectionPool class.
     *
     * @return a ConnectionPool object.
     */
    public static ConnectionPool getInstance(){
        if (!instanceCreated.getAndSet(true)){
            instance = new ConnectionPool();
        }
        LOGGER.log(Level.DEBUG, "Get instance of connection pool" + " " + instanceCreated);
        return instance;
    }

    /**
     * Gets connection from connection pool.
     *
     * @return a Connection object.
     */
    public Connection getConnection(){
        Connection cn = null;
        try{
            cn = pool.take();
        }
        catch (InterruptedException e){
            LOGGER.log(Level.ERROR, "Exception in get connection layer.", e);
        }
        return cn;
    }

    /**
     * Closes connection.
     *
     * @param connection a Connection object to close.
     */
    public void closeConnection(Connection connection){
        pool.offer(connection);
    }
}
