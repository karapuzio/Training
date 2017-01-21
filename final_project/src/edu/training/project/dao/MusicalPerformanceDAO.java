package edu.training.project.dao;

import edu.training.project.dao.exception.DAOException;
import edu.training.project.dao.pool.ConnectionPool;
import edu.training.project.entity.MusicalPerformance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Dell on 05.01.2017.
 */
public class MusicalPerformanceDAO {
    private static final String SQL_ADD_PERMORMANCE = "INSERT INTO musician_performers (id, name) VALUES (?,?)";

    public void addPermormance(MusicalPerformance performance) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_ADD_PERMORMANCE);
            statement.setInt(1, performance.getId());
            statement.setString(2, performance.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : add performance.");
        } finally {
            pool.closeConnection(connection);
        }
    }
}
