package edu.training.project.dao;

import edu.training.project.dao.exception.DAOException;
import edu.training.project.dao.pool.ConnectionPool;
import edu.training.project.entity.MusicGenre;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dell on 05.01.2017.
 */
public class MusicGenreDAO {
    private static final Logger LOGGER = LogManager.getLogger(MusicGenre.class);
    private static final String SQL_ADD_GENRE = "INSERT INTO musical_genres (genre) VALUES (?)";
    private static final String SQL_GET_GENRE_BY_ID = "SELECT * FROM musical_genres WHERE id = ?";
    private static final String SQL_GET_GENRE_BY_NAME = "SELECT * FROM musical_genres WHERE genre = ?";
    private static final String SQL_DELETE_GENRE_BY_ID = "DELETE FROM musical_genres WHERE id = ?";

    public void addGenre(MusicGenre genre) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_ADD_GENRE);
            statement.setString(1, genre.getGenre());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : add genre.");
        } finally {
            pool.closeConnection(connection);
        }
    }

    public MusicGenre getPerformanceById(int id) throws DAOException{
        LOGGER.log(Level.DEBUG, "Get genre by id in MusicGenreDao" + " " + id);
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        MusicGenre musicGenre = null;
        try {
            statement = connection.prepareStatement(SQL_GET_GENRE_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                musicGenre = new MusicGenre();
                musicGenre.setId(resultSet.getInt(1));
                musicGenre.setGenre(resultSet.getString(2));
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : get genre by id.");
        } finally {
            pool.closeConnection(connection);
        }
        LOGGER.log(Level.DEBUG, "Music genre ready" + " " + musicGenre);
        return musicGenre;
    }

    public MusicGenre getGenreByName(String name) throws DAOException{
        LOGGER.log(Level.DEBUG, "Get genre by name in MusicGenreDao" + " " + name);
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        MusicGenre musicGenre = null;
        try {
            statement = connection.prepareStatement(SQL_GET_GENRE_BY_NAME);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                musicGenre = new MusicGenre();
                musicGenre.setId(resultSet.getInt(1));
                musicGenre.setGenre(resultSet.getString(2));
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : get genre by name.");
        } finally {
            pool.closeConnection(connection);
        }
        LOGGER.log(Level.DEBUG, "Music genre ready" + " " + musicGenre);
        return musicGenre;
    }

    public void deletePerformanceById(int id) throws DAOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_GENRE_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : delete genre by id.");
        } finally {
            pool.closeConnection(connection);
        }
    }
}
