package edu.training.project.dao;

import edu.training.project.dao.exception.DAOException;
import edu.training.project.dao.pool.ConnectionPool;
import edu.training.project.entity.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Dell on 05.01.2017.
 */
public class SongDAO {
    private static final String SQL_ADD_SONG = "INSERT INTO songs (id, name, release_date, number_of_orders, path_to_demo, " +
            "path_to_text,, discount_for_song, song_cost) VALUES (?,?,?,?,?,?,?,?)";

    public void addSong(Song song) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_ADD_SONG);
            statement.setInt(1, song.getId());
            statement.setString(2, song.getName());
            statement.setDate(3, song.getReleaseDate());
            statement.setInt(4, song.getNumberOfOrders());
            statement.setString(5, song.getPathToDemo());
            statement.setString(6, song.getPathToText());
            statement.setInt(7, song.getDiscountForSong());
            statement.setDouble(8, song.getCost());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : add song.");
        } finally {
            pool.closeConnection(connection);
        }
    }
}
