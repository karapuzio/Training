package edu.training.project.dao.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Dell on 05.01.2017.
 */
public class ConnectionPool {
    private static ConnectionPool instance;
    private static AtomicBoolean instanceCreated = new AtomicBoolean();
    private static final int POOL_SIZE = 10;
    private ArrayBlockingQueue<Connection> pool;

    private ConnectionPool(){
        init();
    }

    private void init() {
        pool = new ArrayBlockingQueue<Connection>(POOL_SIZE);
        ConnectorDB connector = new ConnectorDB();

        for (int i = 0; i < POOL_SIZE; i++){
            Connection connection = null;
            try{
                connection = connector.getConnection();
                pool.offer(connection);
            } catch (SQLException e) {
                //TO DO FATALL LOG
                throw new RuntimeException();
            }
        }
    }

    public static ConnectionPool getInstance(){
        if (!instanceCreated.getAndSet(true)){
            instance = new ConnectionPool();
        }
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
