package edu.training.project.dao;

import edu.training.project.dao.exception.DAOException;
import edu.training.project.dao.pool.ConnectionPool;
import edu.training.project.entity.Song;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 05.01.2017.
 */
public class SongDAO {
    private static final Logger LOGGER = LogManager.getLogger(SongDAO.class);
    private static final String SQL_ADD_SONG = "INSERT INTO songs (name, number_of_orders, path_to_demo, " +
            "discount_for_song, song_cost, musical_genre_id, performance_id) VALUES (?,?,?,?,?,?,?)";
    private static final String SQL_GET_ALL_SONG = "SELECT * FROM songs";
    private static final String SQL_GET_RANDOM_SONG = "SELECT * FROM songs ORDER BY RAND() LIMIT 12";
    private static final String SQL_GET_SONG_BY_ID = "SELECT * FROM songs WHERE id = ?";
    private static final String SQL_DELETE_SONG_BY_ID = "DELETE FROM songs WHERE id = ?";
    private static final String SQL_UPDATE_SONG = "UPDATE songs " +
            "SET name = ?, number_of_orders = ?, path_to_demo = ?, discount_for_song = ?, " +
            "song_cost = ?, musical_genre_id = ?, performance_id = ? WHERE id = ?";

    public void addSong(Song song) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        LOGGER.log(Level.DEBUG, "Get Connection" + " " + connection);
        try {
            statement = connection.prepareStatement(SQL_ADD_SONG);
            statement.setString(1, song.getName());
            statement.setInt(2, song.getNumberOfOrders());
            statement.setString(3, song.getPathToDemo());
            statement.setInt(4, song.getDiscountForSong());
            statement.setDouble(5, song.getCost());
            statement.setInt(6, song.getGenreId());
            statement.setInt(7, song.getPerformanceId());
            LOGGER.log(Level.DEBUG, "Get statement" + " " + statement);
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
                song.setNumberOfOrders(resultSet.getInt(3));
                song.setPathToDemo(resultSet.getString(4));
                song.setDiscountForSong(resultSet.getInt(5));
                song.setCost(resultSet.getDouble(6));
                song.setGenreId(resultSet.getInt(7));
                song.setPerformanceId(resultSet.getInt(8));
                allSongs.add(song);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : get all song.");
        } finally {
            pool.closeConnection(connection);
        }
        return allSongs;
    }

    public List<Song> getRandomSong() throws DAOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        List<Song> randomSongs = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_GET_RANDOM_SONG);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Song song = new Song();
                song.setId(resultSet.getInt(1));
                song.setName(resultSet.getString(2));
                song.setNumberOfOrders(resultSet.getInt(3));
                song.setPathToDemo(resultSet.getString(4));
                song.setDiscountForSong(resultSet.getInt(5));
                song.setCost(resultSet.getDouble(6));
                song.setGenreId(resultSet.getInt(7));
                song.setPerformanceId(resultSet.getInt(8));
                randomSongs.add(song);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : get random song.");
        } finally {
            pool.closeConnection(connection);
        }
        return randomSongs;
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
                song.setNumberOfOrders(resultSet.getInt(3));
                song.setPathToDemo(resultSet.getString(4));
                song.setDiscountForSong(resultSet.getInt(5));
                song.setCost(resultSet.getDouble(6));
                song.setGenreId(resultSet.getInt(7));
                song.setPerformanceId(resultSet.getInt(8));
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

    public void editSongById(Song song) throws DAOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {statement = connection.prepareStatement(SQL_UPDATE_SONG);
            statement.setString(1, song.getName());
            statement.setInt(2, song.getNumberOfOrders());
            statement.setString(3, song.getPathToDemo());
            statement.setInt(4, song.getDiscountForSong());
            statement.setDouble(5, song.getCost());
            statement.setInt(6, song.getGenreId());
            statement.setInt(7, song.getPerformanceId());
            statement.setInt(8, song.getId());
            LOGGER.log(Level.DEBUG, "Get statement" + " " + statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : get song by id.");
        } finally {
            pool.closeConnection(connection);
        }
    }
}
