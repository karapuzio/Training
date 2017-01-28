package edu.training.project.dao;

import edu.training.project.dao.exception.DAOException;
import edu.training.project.dao.pool.ConnectionPool;
import edu.training.project.entity.MusicalPerformance;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 05.01.2017.
 */
public class MusicalPerformanceDAO {
    private static final Logger LOGGER = LogManager.getLogger(MusicalPerformanceDAO.class);
    private static final String SQL_ADD_PERFORMANCE = "INSERT INTO musician_performers (name) VALUES (?)";
    private static final String SQL_GET_ALL_PERFORMANCES = "SELECT * FROM musician_performers";
    private static final String SQL_GET_PERFORMANCE_BY_ID = "SELECT * FROM musician_performers WHERE id = ?";
    private static final String SQL_GET_PERFORMANCE_BY_NAME = "SELECT * FROM musician_performers WHERE name = ?";
    private static final String SQL_DELETE_PERFORMANCE_BY_ID = "DELETE FROM musician_performers WHERE id = ?";

    public void addPerformance(MusicalPerformance performance) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_ADD_PERFORMANCE);
            statement.setString(1, performance.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : add performance.");
        } finally {
            pool.closeConnection(connection);
        }
    }

    public List<MusicalPerformance> getAllPerformances() throws DAOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        List<MusicalPerformance> allPerformances = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_GET_ALL_PERFORMANCES);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                MusicalPerformance performance = new MusicalPerformance();
                performance.setId(resultSet.getInt(1));
                performance.setName(resultSet.getString(2));
                allPerformances.add(performance);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : get all performances.");
        } finally {
            pool.closeConnection(connection);
        }
        return allPerformances;
    }

    public MusicalPerformance getPerformanceById(int id) throws DAOException{
        LOGGER.log(Level.DEBUG, "Get user by id in MusicalPerformanceDao" + " " + id);
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        MusicalPerformance performance = null;
        try {
            statement = connection.prepareStatement(SQL_GET_PERFORMANCE_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                performance = new MusicalPerformance();
                performance.setId(resultSet.getInt(1));
                performance.setName(resultSet.getString(2));
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : get performance by id.");
        } finally {
            pool.closeConnection(connection);
        }
        LOGGER.log(Level.DEBUG, "Performance ready" + " " + performance);
        return performance;
    }

    public MusicalPerformance getPerformanceByName(String name) throws DAOException{
        LOGGER.log(Level.DEBUG, "Get user by name in MusicalPerformanceDao" + " " + name);
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        MusicalPerformance performance = null;
        try {
            statement = connection.prepareStatement(SQL_GET_PERFORMANCE_BY_NAME);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                performance = new MusicalPerformance();
                performance.setId(resultSet.getInt(1));
                performance.setName(resultSet.getString(2));
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : get user by name.");
        } finally {
            pool.closeConnection(connection);
        }
        LOGGER.log(Level.DEBUG, "Performance ready" + " " + performance);
        return performance;
    }

    public void deletePerformanceById(int id) throws DAOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_PERFORMANCE_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : delete performance by id.");
        } finally {
            pool.closeConnection(connection);
        }
    }
}
