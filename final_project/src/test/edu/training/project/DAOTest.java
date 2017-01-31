package test.edu.training.project;

import edu.training.project.dao.OrderDAO;
import edu.training.project.dao.UserDAO;
import edu.training.project.dao.exception.DAOException;
import edu.training.project.entity.Order;
import edu.training.project.entity.User;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Dell on 29.01.2017.
 */
public class DAOTest {

    @Test
    public void addRegistrationTest() throws DAOException{
        User user = new User();
        user.setLogin("Bambo");
        user.setPassword("123456");
        user.setEmail("bamby@gmail.com");
        user.setRole("user");
        UserDAO userDAO = new UserDAO();
        userDAO.addUser(user);
        User checkUser = userDAO.getUserByLogin("Bambo");
        boolean actual = checkUser != null ? true : false;
        Assert.assertTrue(actual);
    }

    @Test(expected = DAOException.class)
    public void checkUserTest() throws DAOException{
        User user = new User();
        user.setLogin("Bamby");
        user.setPassword("123456");
        user.setEmail("bamby@gmail.com");
        user.setRole("user");
        UserDAO userDAO = new UserDAO();
        userDAO.addUser(user);
    }

    @Test
    public void getOrderByIdTest() throws DAOException {
        OrderDAO orderDAO = new OrderDAO();
        Order order = orderDAO.getOrderById(-1);
        Assert.assertNull(order);
    }
}
