package edu.training.project.dao;

import edu.training.project.dao.exception.DAOException;
import edu.training.project.dao.pool.ConnectionPool;
import edu.training.project.entity.Order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Dell on 05.01.2017.
 */
public class OrderDAO {
    private static final String SQL_ADD_ORDER = "INSERT INTO orders (id, order_cost, data_of_order) VALUES (?,?,?)";

    public void addOrder(Order order) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_ADD_ORDER);
            statement.setInt(1, order.getId());
            statement.setDouble(2, order.getCost());
            statement.setDate(3, new Date(order.getDateOfOrder().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : add genre.");
        } finally {
            pool.closeConnection(connection);
        }
    }
}
