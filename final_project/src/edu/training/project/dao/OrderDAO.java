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
 * Provides a DAO-logic for the Order entity for the MySQL Database.
 *
 * @author Skidan Olya
 * @version 1.0
 */
public class OrderDAO {
    private static final Logger LOGGER = LogManager.getLogger(OrderDAO.class);
    private static final String SQL_ADD_ORDER = "INSERT INTO orders (user_id, song_id, is_payed, data_of_order) VALUES (?,?,?,?)";
    private static final String SQL_GET_ALL_ORDERS = "SELECT * FROM orders";
    private static final String SQL_GET_ORDER_BY_ID = "SELECT * FROM orders WHERE id = ?";
    private static final String SQL_GET_ORDERS_BY_USER_ID = "SELECT * FROM orders WHERE user_id = ?";
    private static final String SQL_GET_ORDERS_BY_USER_ID_AND_SONG_ID = "SELECT * FROM orders WHERE user_id = ? AND song_id = ?";
    private static final String SQL_DELETE_ORDER_BY_ID = "DELETE FROM orders WHERE id = ?";
    private static final String SQL_UPDATE_ORDER = "UPDATE orders SET is_payed = ?, data_of_order = ? WHERE id = ?";

    /**
     * Add new order to database.
     *
     * @param order a Order object to added.
     * @throws DAOException
     */
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
            statement.executeUpdate();
            LOGGER.log(Level.DEBUG, "Add order by id in statement" + " " + statement);
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : add order.");
        } finally {
            pool.closeConnection(connection);
        }
    }

    /**
     * Gets all orders from database.
     *
     * @return all orders.
     * @throws DAOException
     */
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
            throw new DAOException("Exception in DAO : get all orders.");
        } finally {
            pool.closeConnection(connection);
        }
        return allOrders;
    }

    /**
     * Gets order with concrete id from database.
     *
     * @param id a order id.
     * @return a Order object with concrete id from database.
     * @throws DAOException
     */
    public Order getOrderById(int id) throws DAOException{
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
            throw new DAOException("Exception in DAO : get order by id.");
        } finally {
            pool.closeConnection(connection);
        }
        return order;
    }

    /**
     * Get order with concrete user's ans song's ids from database.
     *
     * @param userId a user id.
     * @param songId a song id.
     * @return a Order object with concrete user's and song's ids.
     * @throws DAOException
     */
    public Order getOrderByUserIdAndSongId(int userId, int songId) throws DAOException{
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
                LOGGER.log(Level.DEBUG, "Order with concrete user's and song's ids : " + order);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : get order by user's and song's ids.");
        } finally {
            pool.closeConnection(connection);
        }
        return order;
    }

    /**
     * Gets all orders to concrete user from database.
     *
     * @param userId a user id.
     * @return all orders from concrete user.
     * @throws DAOException
     */
    public List<Order> getOrdersByUserId(int userId) throws DAOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        List<Order> userOrders = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_GET_ORDERS_BY_USER_ID);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Order order = new Order();
                order.setId(resultSet.getInt(1));
                order.setUserId(resultSet.getInt(2));
                order.setSongId(resultSet.getInt(3));
                order.setIsPayed(resultSet.getInt(4));
                order.setDateOfOrder(resultSet.getDate(5));
                userOrders.add(order);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : get orders by user's id.");
        } finally {
            pool.closeConnection(connection);
        }
        return userOrders;
    }

    /**
     * Delete order by id in database.
     *
     * @param id a oreder id.
     * @throws DAOException
     */
    public void deleteOrderById(int id) throws DAOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_ORDER_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : delete order by id.");
        } finally {
            pool.closeConnection(connection);
        }
    }

    /**
     * Updates song by id in database.
     *
     * @param order a Order object to update.
     * @throws DAOException
     */
    public void editOrderById(Order order) throws DAOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_ORDER);
            statement.setInt(1, order.getIsPayed());
            statement.setDate(2, new Date(order.getDateOfOrder().getTime()));
            statement.setInt(3, order.getId());
            LOGGER.log(Level.DEBUG, "Statement" + " " + statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : update order by id.");
        } finally {
            pool.closeConnection(connection);
        }
    }
}
