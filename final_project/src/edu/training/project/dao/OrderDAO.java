package edu.training.project.dao;

import edu.training.project.dao.exception.DAOException;
import edu.training.project.dao.pool.ConnectionPool;
import edu.training.project.entity.Order;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 05.01.2017.
 */
public class OrderDAO {
    private static final Logger LOGGER = LogManager.getLogger(OrderDAO.class);
    private static final String SQL_ADD_ORDER = "INSERT INTO orders (user_id, song_id, is_payed, data_of_order) VALUES (?,?,?,?)";
    private static final String SQL_GET_ALL_ORDERS = "SELECT * FROM orders";
    private static final String SQL_GET_ORDER_BY_ID = "SELECT * FROM orders WHERE id = ?";
    private static final String SQL_GET_ORDERS_BY_USER_ID = "SELECT * FROM orders WHERE user_id = ?";
    private static final String SQL_GET_ORDERS_BY_USER_ID_AND_SONG_ID = "SELECT * FROM orders WHERE user_id = ? AND song_id = ?";
    private static final String SQL_DELETE_ORDER_BY_ID = "DELETE FROM orders WHERE id = ?";

    public void addOrder(Order order) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_ADD_ORDER);
            statement.setInt(1, order.getUserId());
            statement.setInt(2, order.getSongId());
            statement.setInt(3, order.getIsPayed());
            statement.setDate(4, new Date(order.getDateOfOrder().getTime()));
            LOGGER.log(Level.DEBUG, "Add order by id in statement" + " " + statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : add order.");
        } finally {
            pool.closeConnection(connection);
        }
    }

    public List<Order> getAllOrders() throws DAOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        List<Order> allOrders = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_GET_ALL_ORDERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Order order = new Order();
                order.setId(resultSet.getInt(1));
                order.setUserId(resultSet.getInt(2));
                order.setSongId(resultSet.getInt(3));
                order.setIsPayed(resultSet.getInt(4));
                order.setDateOfOrder(resultSet.getDate(5));
                allOrders.add(order);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : get all performances.");
        } finally {
            pool.closeConnection(connection);
        }
        return allOrders;
    }

    public Order getOrderById(int id) throws DAOException{
        LOGGER.log(Level.DEBUG, "Get order by id in OrderDao" + " " + id);
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        Order order = null;
        try {
            statement = connection.prepareStatement(SQL_GET_ORDER_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                order = new Order();
                order.setId(resultSet.getInt(1));
                order.setUserId(resultSet.getInt(2));
                order.setSongId(resultSet.getInt(3));
                order.setIsPayed(resultSet.getInt(4));
                order.setDateOfOrder(resultSet.getDate(5));
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : get performance by id.");
        } finally {
            pool.closeConnection(connection);
        }
        LOGGER.log(Level.DEBUG, "Order ready" + " " + order);
        return order;
    }

    public Order getOrderByUserIdAndSongId(int userId, int songId) throws DAOException{
        LOGGER.log(Level.DEBUG, "Get order by user and song id in OrderDao" + " " + userId + " " + songId);
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        Order order = null;
        try {
            statement = connection.prepareStatement(SQL_GET_ORDERS_BY_USER_ID_AND_SONG_ID);
            statement.setInt(1, userId);
            statement.setInt(2, songId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                order = new Order();
                order.setId(resultSet.getInt(1));
                order.setUserId(resultSet.getInt(2));
                order.setSongId(resultSet.getInt(3));
                order.setIsPayed(resultSet.getInt(4));
                order.setDateOfOrder(resultSet.getDate(5));
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : get performance by id.");
        } finally {
            pool.closeConnection(connection);
        }
        LOGGER.log(Level.DEBUG, "Order ready" + " " + order);
        return order;
    }

    public List<Order> getOrdersByUserId(int userId) throws DAOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        List<Order> userOrders = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_GET_ORDERS_BY_USER_ID);
            statement.setInt(1, userId);
            LOGGER.log(Level.DEBUG, "Get orders by user id in OrderDao, statement : " + " " + statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Order order = new Order();
                order.setId(resultSet.getInt(1));
                order.setUserId(resultSet.getInt(2));
                order.setSongId(resultSet.getInt(3));
                order.setIsPayed(resultSet.getInt(4));
                order.setDateOfOrder(resultSet.getDate(5));
                LOGGER.log(Level.DEBUG, "Get orders by user id in OrderDao" + " " + order);
                userOrders.add(order);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : get all performances.");
        } finally {
            pool.closeConnection(connection);
        }
        return userOrders;
    }

    public void deleteOrderById(int id) throws DAOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_ORDER_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : delete performance by id.");
        } finally {
            pool.closeConnection(connection);
        }
    }
}
