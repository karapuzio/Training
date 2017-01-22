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
 * Created by Dell on 05.01.2017.
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

    private void init() {
        LOGGER.log(Level.DEBUG, "Init connection pool" + " " + pool);
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
            //TO DO FATALL LOG
            throw new RuntimeException();
        }
        LOGGER.log(Level.DEBUG, "Init connection pool" + " " + pool);
    }

    public static ConnectionPool getInstance(){
        LOGGER.log(Level.DEBUG, "Get instance of connection pool" + " " + instanceCreated);
        if (!instanceCreated.getAndSet(true)){
            instance = new ConnectionPool();
        }
        LOGGER.log(Level.DEBUG, "Get instance of connection pool" + " " + instanceCreated);
        return instance;
    }

    public Connection getConnection(){
        Connection cn = null;
        try{
            cn = pool.take();
        }
        catch (InterruptedException e){
            // TO DO LOGGER
        }
        return cn;
    }

    public void closeConnection(Connection connection){
        pool.offer(connection);
    }
}
