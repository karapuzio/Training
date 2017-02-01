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
 * Provides a DAO-logic for the Song entity for the MySQL Database.
 *
 * @author Skidan Olya
 * @version 1.0
 */
public class SongDAO {
    private static final Logger LOGGER = LogManager.getLogger(SongDAO.class);
    private static final String SQL_ADD_SONG = "INSERT INTO songs (name, path_to_demo, " +
            "song_cost, musical_genre_id, performance_id, is_deleted) VALUES (?,?,?,?,?,?)";
    private static final String SQL_GET_ALL_SONG = "SELECT * FROM songs";
    private static final String SQL_GET_RANDOM_SONG = "SELECT * FROM songs WHERE is_deleted = 0 ORDER BY RAND() LIMIT 12";
    private static final String SQL_GET_SONG_BY_ID = "SELECT * FROM songs WHERE id = ?";
    private static final String SQL_DELETE_SONG_BY_ID = "UPDATE songs SET is_deleted = ? WHERE id = ?";
    private static final String SQL_UPDATE_SONG = "UPDATE songs " +
            "SET name = ?, path_to_demo = ?, song_cost = ?, musical_genre_id = ?, performance_id = ? WHERE id = ?";

    /**
     * Adds new song to database.
     *
     * @param song a Song object to add.
     * @throws DAOException
     */
    public void addSong(Song song) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_ADD_SONG);
            statement.setString(1, song.getName());
            statement.setString(2, song.getPathToDemo());
            statement.setDouble(3, song.getCost());
            statement.setInt(4, song.getGenreId());
            statement.setInt(5, song.getPerformanceId());
            statement.setInt(6, song.getIsDeleted());
            statement.executeUpdate();
            LOGGER.log(Level.DEBUG, "Get statement" + " " + statement);
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : add song.");
        } finally {
            pool.closeConnection(connection);
        }
    }

    /**
     * Gets all songs from database.
     *
     * @return all song from database.
     * @throws DAOException
     */
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
                song.setPathToDemo(resultSet.getString(3));
                song.setCost(resultSet.getDouble(4));
                song.setGenreId(resultSet.getInt(5));
                song.setPerformanceId(resultSet.getInt(6));
                song.setIsDeleted(resultSet.getInt(7));
                if(song.getIsDeleted() == 0){
                    allSongs.add(song);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : get all song.");
        } finally {
            pool.closeConnection(connection);
        }
        return allSongs;
    }

    /**
     * Gets random songs from database.
     *
     * @return the random songs from database.
     * @throws DAOException
     */
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
                song.setPathToDemo(resultSet.getString(3));
                song.setCost(resultSet.getDouble(4));
                song.setGenreId(resultSet.getInt(5));
                song.setPerformanceId(resultSet.getInt(6));
                song.setIsDeleted(resultSet.getInt(7));
                if(song.getIsDeleted() == 0){
                    randomSongs.add(song);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : get random song.");
        } finally {
            pool.closeConnection(connection);
        }
        return randomSongs;
    }

    /**
     * Gets song with concrete id from database.
     *
     * @param id a song id.
     * @return a Song object with concrete id.
     * @throws DAOException
     */
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
                song.setPathToDemo(resultSet.getString(3));
                song.setCost(resultSet.getDouble(4));
                song.setGenreId(resultSet.getInt(5));
                song.setPerformanceId(resultSet.getInt(6));
                song.setIsDeleted(resultSet.getInt(7));
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : get song by id.");
        } finally {
            pool.closeConnection(connection);
        }
        return song;
    }

    /**
     * Deletes song by id in database.
     *
     * @param song a Song object to delete.
     * @throws DAOException
     */
    public void deleteSongById(Song song) throws DAOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_SONG_BY_ID);
            statement.setInt(1, song.getIsDeleted());
            statement.setInt(2, song.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : delete song by id.");
        } finally {
            pool.closeConnection(connection);
        }
    }

    /**
     * Update song with concrete id in database.
     *
     * @param song a Song object from update.
     * @throws DAOException
     */
    public void editSongById(Song song) throws DAOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_SONG);
            statement.setString(1, song.getName());
            statement.setString(2, song.getPathToDemo());
            statement.setDouble(3, song.getCost());
            statement.setInt(4, song.getGenreId());
            statement.setInt(5, song.getPerformanceId());
            statement.setInt(6, song.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : update song by id.");
        } finally {
            pool.closeConnection(connection);
        }
    }
}
