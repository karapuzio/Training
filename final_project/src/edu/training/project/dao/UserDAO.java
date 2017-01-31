package edu.training.project.dao;

import edu.training.project.dao.exception.DAOException;
import edu.training.project.dao.pool.ConnectionPool;
import edu.training.project.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides a DAO-logic for the User entity for the MySQL Database.
 *
 * @author Skidan Olya
 * @version 1.0
 */
public class UserDAO {
    private static final Logger LOGGER = LogManager.getLogger(UserDAO.class);
    private static final String SQL_ADD_USER = "INSERT INTO users (login, password, email, role," +
            "number_of_orders, discount_for_order, cash) VALUES (?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_USER = "UPDATE users SET cash = ?, number_of_orders = ?, discount_for_order = ? WHERE id = ?";
    private static final String SQL_GET_ALL_USERS = "SELECT * FROM users";
    private static final String SQL_GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SQL_GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM user WHERE id = ?";

    /**
     * Adds new user to database.
     *
     * @param user a User object to add.
     * @throws DAOException
     */
    public void addUser(User user) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_ADD_USER);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getRole());
            statement.setInt(5, user.getNumberOfOrders());
            statement.setInt(6, user.getDiscount());
            statement.setDouble(7, user.getCash());
            statement.executeUpdate();
            LOGGER.log(Level.DEBUG, "Statement execute");
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : add user.");
        } finally {
            pool.closeConnection(connection);
        }
    }

    /**
     * Returns all users from database.
     *
     * @return all users from database.
     * @throws DAOException
     */
    public List<User> getAllUsers() throws DAOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        List<User> allUsers = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_GET_ALL_USERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setLogin(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setRole(resultSet.getString(5));
                user.setNumberOfOrders(resultSet.getInt(6));
                user.setDiscount(resultSet.getInt(7));
                user.setCash(resultSet.getDouble(8));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : get all user.");
        } finally {
            pool.closeConnection(connection);
        }
        return allUsers;
    }

    /**
     * Gets user with concrete id from database.
     *
     * @param id a user id.
     * @return a User object with concrete id.
     * @throws DAOException
     */
    public User getUserById(int id) throws DAOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        User user = null;
        try {
            statement = connection.prepareStatement(SQL_GET_USER_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt(1));
                user.setLogin(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setRole(resultSet.getString(5));
                user.setNumberOfOrders(resultSet.getInt(6));
                user.setDiscount(resultSet.getInt(7));
                user.setCash(resultSet.getDouble(8));
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : get user by id.");
        } finally {
            pool.closeConnection(connection);
        }
        return user;
    }

    /**
     * Gets user by login from database.
     *
     * @param login a user login.
     * @return a User object with concrete login.
     * @throws DAOException
     */
    public User getUserByLogin(String login) throws DAOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        User user = null;
        try {
            statement = connection.prepareStatement(SQL_GET_USER_BY_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt(1));
                user.setLogin(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setRole(resultSet.getString(5));
                user.setNumberOfOrders(resultSet.getInt(6));
                user.setDiscount(resultSet.getInt(7));
                user.setCash(resultSet.getDouble(8));
                LOGGER.log(Level.DEBUG, "Get user by login " + login +" : " + user);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : get user by login.");
        } finally {
            pool.closeConnection(connection);
        }
        return user;
    }

    /**
     * Delete user by id in database.
     *
     * @param id a user id.
     * @throws DAOException
     */
    public void deleteUserById(int id) throws DAOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_USER_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : delete user by id.");
        } finally {
            pool.closeConnection(connection);
        }
    }

    /**
     * Update user by id in database.
     *
     * @param user a User object to update.
     * @throws DAOException
     */
    public void editUserById(User user) throws DAOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_USER);
            statement.setDouble(1, user.getCash());
            statement.setInt(2, user.getNumberOfOrders());
           statement.setInt(3, user.getDiscount());
            statement.setInt(4, user.getId());
            LOGGER.log(Level.DEBUG, "Statement" + " " + statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : update user by id.");
        } finally {
            pool.closeConnection(connection);
        }
    }
}
