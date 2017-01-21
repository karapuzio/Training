package edu.training.project.dao;

import edu.training.project.dao.exception.DAOException;
import edu.training.project.dao.pool.ConnectionPool;
import edu.training.project.entity.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Dell on 05.01.2017.
 */
public class CommentDAO {
    private static final String SQL_ADD_COMMENT = "INSERT INTO comments (id, comment) VALUES (?,?)";

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
}
