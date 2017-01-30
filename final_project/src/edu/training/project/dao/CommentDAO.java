package edu.training.project.dao;

import edu.training.project.dao.exception.DAOException;
import edu.training.project.dao.pool.ConnectionPool;
import edu.training.project.entity.Comment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 05.01.2017.
 */
public class CommentDAO {
    private static final Logger LOGGER = LogManager.getLogger(CommentDAO.class);
    private static final String SQL_ADD_COMMENT = "INSERT INTO comments (comment, user_id, song_id, date) VALUES (?,?,?,?)";
    private static final String SQL_GET_ALL_COMMENTS = "SELECT * FROM comments";
    private static final String SQL_GET_COMMENT_BY_ID = "SELECT * FROM comments WHERE id = ?";
    private static final String SQL_GET_COMMENTS_BY_SONG_ID = "SELECT * FROM comments WHERE song_id = ?";
    private static final String SQL_DELETE_COMMENT_BY_ID = "DELETE FROM comments WHERE id = ?";

    public void addComment(Comment comment) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_ADD_COMMENT);
            statement.setString(1, comment.getContent());
            statement.setInt(2, comment.getUserId());
            statement.setInt(3, comment.getSongId());
            statement.setDate(4, new Date(comment.getDate().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : add comment.");
        } finally {
            pool.closeConnection(connection);
        }
    }

    public List<Comment> getAllComments() throws DAOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        List<Comment> allComments = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_GET_ALL_COMMENTS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Comment comment = new Comment();
                comment.setId(resultSet.getInt(1));
                comment.setContent(resultSet.getString(2));
                comment.setUserId(Integer.parseInt(resultSet.getString(3)));
                comment.setSongId(Integer.parseInt(resultSet.getString(4)));
                comment.setDate(resultSet.getDate(5));
                allComments.add(comment);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : get all comments.");
        } finally {
            pool.closeConnection(connection);
        }
        return allComments;
    }

    public List<Comment> getCommentBySongId(int songId) throws DAOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        List<Comment> songComments = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_GET_COMMENTS_BY_SONG_ID);
            statement.setInt(1, songId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Comment comment = new Comment();
                comment.setId(resultSet.getInt(1));
                comment.setContent(resultSet.getString(2));
                comment.setUserId(Integer.parseInt(resultSet.getString(3)));
                comment.setSongId(Integer.parseInt(resultSet.getString(4)));
                comment.setDate(resultSet.getDate(5));
                songComments.add(comment);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : get comments by song id.");
        } finally {
            pool.closeConnection(connection);
        }
        return songComments;
    }

    public void deleteCommentById(int id) throws DAOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_COMMENT_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : delete comment by id.");
        } finally {
            pool.closeConnection(connection);
        }
    }
}
