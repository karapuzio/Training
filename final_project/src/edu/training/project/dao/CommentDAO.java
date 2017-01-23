package edu.training.project.dao;

import edu.training.project.dao.exception.DAOException;
import edu.training.project.dao.pool.ConnectionPool;
import edu.training.project.entity.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 05.01.2017.
 */
public class CommentDAO {
    private static final String SQL_ADD_COMMENT = "INSERT INTO comments (id, comment) VALUES (?,?)";
    private static final String SQL_GET_ALL_COMMENTS = "SELECT * FROM comments";
    private static final String SQL_GET_COMMENT_BY_ID = "SELECT * FROM comments WHERE id = ?";
    private static final String SQL_COMENNT_USER_BY_ID = "DELETE FROM comments WHERE id = ?";

    public void addComment(Comment comment) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_ADD_COMMENT);
            statement.setInt(1, comment.getId());
            statement.setString(2, comment.getContent());
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
                allComments.add(comment);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : get all user.");
        } finally {
            pool.closeConnection(connection);
        }
        return allComments;
    }
}
