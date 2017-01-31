package edu.training.project.dao;

import edu.training.project.dao.exception.DAOException;
import edu.training.project.dao.pool.ConnectionPool;
import edu.training.project.entity.MusicalPerformance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides a DAO-logic for the MusicalPerformance entity for the MySQL Database.
 *
 * @author Skidan Olya
 * @version 1.0
 */
public class MusicalPerformanceDAO {
    private static final Logger LOGGER = LogManager.getLogger(MusicalPerformanceDAO.class);
    private static final String SQL_ADD_PERFORMANCE = "INSERT INTO musician_performers (name) VALUES (?)";
    private static final String SQL_GET_ALL_PERFORMANCES = "SELECT * FROM musician_performers";
    private static final String SQL_GET_PERFORMANCE_BY_ID = "SELECT * FROM musician_performers WHERE id = ?";
    private static final String SQL_GET_PERFORMANCE_BY_NAME = "SELECT * FROM musician_performers WHERE name = ?";
    private static final String SQL_DELETE_PERFORMANCE_BY_ID = "DELETE FROM musician_performers WHERE id = ?";

    /**
     * Adds musical performance to database.
     *
     * @param performance a musical performance object.
     * @throws DAOException
     */
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

    /**
     * Gets all musical performance from database.
     *
     * @return all musical performance.
     * @throws DAOException
     */
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

    /**
     * Gets musical performance by id from database.
     *
     * @param id a musical performance id.
     * @return a MsicalPerformance object with concrete id from database.
     * @throws DAOException
     */
    public MusicalPerformance getPerformanceById(int id) throws DAOException{
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
        return performance;
    }

    /**
     * Gets musical performance by name from database.
     *
     * @param name a musical performance name.
     * @return a MusicalPerformance object with concrete name from database.
     * @throws DAOException
     */
    public MusicalPerformance getPerformanceByName(String name) throws DAOException{
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
        return performance;
    }

    /**
     * Deletes musical performance by id in database.
     *
     * @param id a musical performance id.
     * @throws DAOException
     */
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
