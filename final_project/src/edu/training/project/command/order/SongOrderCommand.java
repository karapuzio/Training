package edu.training.project.command.order;

import edu.training.project.command.AbstractCommand;
import edu.training.project.command.exception.ServiceException;
import edu.training.project.controller.ConfigurationManager;
import edu.training.project.dao.MusicalPerformanceDAO;
import edu.training.project.dao.OrderDAO;
import edu.training.project.dao.SongDAO;
import edu.training.project.dao.UserDAO;
import edu.training.project.dao.exception.DAOException;
import edu.training.project.entity.MusicalPerformance;
import edu.training.project.entity.Order;
import edu.training.project.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 20.01.2017.
 */
public class SongOrderCommand extends AbstractCommand{
    private static final Logger LOGGER = LogManager.getLogger(SongOrderCommand.class);
    private static final String PARAM_NAME_ORDER_SONG = "orderSong";
    private static final int PARAM_NAME_PAY = 1;

    @Override
    public String execute(HttpServletRequest request) throws ServiceException{
        String page = null;
        try{
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("currentUser");
            OrderDAO orderDAO = new OrderDAO();
            String[] orderSong = request.getParameterValues(PARAM_NAME_ORDER_SONG);
            SongDAO songDAO = new SongDAO();
            double cost = 0;
            LOGGER.log(Level.DEBUG, "Order make " + orderSong);
            for (String id : orderSong){
                LOGGER.log(Level.DEBUG, "Value id " + id);
                Order order = orderDAO.getOrderById(Integer.parseInt(id));
                order.setSong(songDAO.getSongById(order.getSongId()));
                cost += order.getSong().getCost();
            }
            LOGGER.log(Level.DEBUG, "Order cost " + cost);
            cost *= (100.0 - user.getDiscount()) / 100.0;
            if (cost > user.getCash()){
                return null;
            }
            int count = 0;
            for (String id : orderSong){
                Order order = orderDAO.getOrderById(Integer.parseInt(id));
                order.setIsPayed(PARAM_NAME_PAY);
                order.setDateOfOrder(new Date());
                orderDAO.editOrderById(order);
                count++;
            }
            LOGGER.log(Level.DEBUG, "Order make cost " + cost);
            page = ConfigurationManager.getProperty("path.page.basket");
            user.setCash(user.getCash() - cost);
            user.setNumberOfOrders(user.getNumberOfOrders() + count);
            user.setDiscount(discounting(user));
            UserDAO userDAO = new UserDAO();
            userDAO.editUserById(user);
            List< Order> orders = orderDAO.getOrdersByUserId(user.getId());
            MusicalPerformanceDAO musicalPerformanceDAO = new MusicalPerformanceDAO();
            for (Order order : orders){
                order.setSong(songDAO.getSongById(order.getSongId()));
                MusicalPerformance performance = musicalPerformanceDAO.getPerformanceById(order.getSong().getPerformanceId());
                order.getSong().setPerformance(performance);
            }
            request.setAttribute("userOrders", orders);

        } catch (DAOException e) {
            throw new ServiceException("Service error: song ordered is failed", e);
        }
        return page;
    }

    private int discounting(User user){
        int numberOfOrders = user.getNumberOfOrders();
        if (numberOfOrders < 5){
            return 0;
        }
        if (numberOfOrders < 10){
            return 2;
        }
        if (numberOfOrders < 20){
            return 5;
        }
        return 10;
    }
}
