package edu.training.project.dao;

import edu.training.project.dao.exception.DAOException;
import edu.training.project.dao.pool.ConnectionPool;
import edu.training.project.entity.MusicGenre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Dell on 05.01.2017.
 */
public class MusicGenreDAO {
    private static final String SQL_ADD_GENRE = "INSERT INTO musical_genres (id, genre) VALUES (?,?)";

    public void addGenre(MusicGenre genre) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_ADD_GENRE);
            statement.setInt(1, genre.getId());
            statement.setString(2, genre.getGenre());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : add genre.");
        } finally {
            pool.closeConnection(connection);
        }
    }
}
