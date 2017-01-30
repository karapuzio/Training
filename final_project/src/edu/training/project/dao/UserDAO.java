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
 * Created by Dell on 05.01.2017.
 */
public class UserDAO {
    private static final Logger LOGGER = LogManager.getLogger(UserDAO.class);
    private static final String SQL_ADD_USER = "INSERT INTO users (login, password, email, role," +
            "number_of_orders, discount_for_order, cash) VALUES (?,?,?,?,?,?,?)";
//    private static final String SQL_UPDATE_USER = "UPDATE users " +
//            "SET login = ?, password = ?, email = ?, role = ?, number_of_orders = ?, discount_for_order = ?, " +
//            "cash = ? WHERE id = ?";
    private static final String SQL_UPDATE_USER = "UPDATE users SET cash = ? WHERE id = ?";
    private static final String SQL_GET_ALL_USERS = "SELECT * FROM users";
    private static final String SQL_GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SQL_GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM user WHERE id = ?";

    public void addUser(User user) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_ADD_USER);
            LOGGER.log(Level.DEBUG, "Statement " + statement);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            LOGGER.log(Level.DEBUG, "Statement " + statement);
            statement.setString(4, user.getRole());
            statement.setInt(5, user.getNumberOfOrders());
            statement.setInt(6, user.getDiscount());
            statement.setDouble(7, user.getCash());
            LOGGER.log(Level.DEBUG, "Statement " + statement);
            statement.executeUpdate();
            LOGGER.log(Level.DEBUG, "Statement execute");
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : add user.");
        } finally {
            pool.closeConnection(connection);
        }
    }

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

    public User getUserByLogin(String login) throws DAOException{
        LOGGER.log(Level.DEBUG, "Get user by id in UserDao" + " " + login);
        ConnectionPool pool = ConnectionPool.getInstance();
        LOGGER.log(Level.DEBUG, "Get ConnectionPool" + " " + pool);
        Connection connection = pool.getConnection();
        LOGGER.log(Level.DEBUG, "Get Connection" + " " + connection);
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
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : get user by login.");
        } finally {
            pool.closeConnection(connection);
        }
        LOGGER.log(Level.DEBUG, "User ready" + " " + user);
        return user;
    }

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

    public void editUserById(User user) throws DAOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_USER);
//            statement.setString(1, user.getLogin());
//            statement.setString(2, user.getPassword());
//            statement.setString(3, user.getEmail());
//            statement.setString(4, user.getRole());
//            statement.setInt(5, user.getNumberOfOrders());
//            statement.setInt(6, user.getDiscount());
//            statement.setDouble(7, user.getCash());
//            statement.setInt(8, user.getId());
            statement.setDouble(1, user.getCash());
            statement.setInt(2, user.getId());
            LOGGER.log(Level.DEBUG, "Statement" + " " + statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in DAO : update user by id.");
        } finally {
            pool.closeConnection(connection);
        }
    }
}
