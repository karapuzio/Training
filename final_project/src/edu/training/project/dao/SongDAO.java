package edu.training.project.dao;

import edu.training.project.dao.exception.DAOException;
import edu.training.project.dao.pool.ConnectionPool;
import edu.training.project.entity.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 05.01.2017.
 */
public class SongDAO {
    private static final String SQL_ADD_SONG = "INSERT INTO songs (id, name, release_date, number_of_orders, path_to_demo, " +
            "path_to_text,, discount_for_song, song_cost) VALUES (?,?,?,?,?,?,?,?)";
    private static final String SQL_GET_ALL_SONG = "SELECT * FROM songs";
    private static final String SQL_GET_SONG_BY_ID = "SELECT * FROM songs WHERE id = ?";
    private static final String SQL_DELETE_SONG_BY_ID = "DELETE FROM songs WHERE id = ?";


    public void addSong(Song song) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_ADD_SONG);
            statement.setInt(1, song.getId());
            statement.setString(2, song.getName());
            statement.setDate(3, new Date(song.getReleaseDate().getTime()));
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

    public List<Song> getAllSongs() throws DAOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        List<Song> allSongs = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_GET_ALL_SONG);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Song song = new Song();
                song.setId(resultSet.getInt(1));
                song.setName(resultSet.getString(2));
                song.setReleaseDate(resultSet.getDate(3));
                song.setNumberOfOrders(resultSet.getInt(4));
                song.setPathToDemo(resultSet.getString(5));
                song.setPathToText(resultSet.getString(6));
                song.setDiscountForSong(resultSet.getInt(7));
                song.setCost(resultSet.getDouble(8));
                allSongs.add(song);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : get all song.");
        } finally {
            pool.closeConnection(connection);
        }
        return allSongs;
    }

    public Song getSongById(int id) throws DAOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        Song song = null;
        try {
            statement = connection.prepareStatement(SQL_GET_SONG_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                song = new Song();
                song.setId(resultSet.getInt(1));
                song.setName(resultSet.getString(2));
                song.setReleaseDate(resultSet.getDate(3));
                song.setNumberOfOrders(resultSet.getInt(4));
                song.setPathToDemo(resultSet.getString(5));
                song.setPathToText(resultSet.getString(6));
                song.setDiscountForSong(resultSet.getInt(7));
                song.setCost(resultSet.getDouble(8));
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : get song by id.");
        } finally {
            pool.closeConnection(connection);
        }
        return song;
    }

    public void deleteSongById(int id) throws DAOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_SONG_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : delete song by id.");
        } finally {
            pool.closeConnection(connection);
        }
    }
}
