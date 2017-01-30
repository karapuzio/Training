package edu.training.project.command.view;

import edu.training.project.command.AbstractCommand;
import edu.training.project.command.exception.ServiceException;
import edu.training.project.controller.ConfigurationManager;
import edu.training.project.dao.MusicalPerformanceDAO;
import edu.training.project.dao.OrderDAO;
import edu.training.project.dao.SongDAO;
import edu.training.project.dao.exception.DAOException;
import edu.training.project.entity.MusicalPerformance;
import edu.training.project.entity.Order;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Dell on 25.01.2017.
 */
public class ViewBasketCommand extends AbstractCommand{
    private static final Logger LOGGER = LogManager.getLogger(ViewBasketCommand.class);
    private static final String PARAM_NAME_USER_ID = "userId";
    private static final String SESSION_USER = "currentUser";

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        int userId = Integer.parseInt(request.getParameter(PARAM_NAME_USER_ID));
        try {
            OrderDAO orderDAO = new OrderDAO();
            List<Order> orders = orderDAO.getOrdersByUserId(userId);
            SongDAO songDAO = new SongDAO();
            MusicalPerformanceDAO musicalPerformanceDAO = new MusicalPerformanceDAO();
            for (Order order : orders){
                order.setSong(songDAO.getSongById(order.getSongId()));
                MusicalPerformance performance = musicalPerformanceDAO.getPerformanceById(order.getSong().getPerformanceId());
                order.getSong().setPerformance(performance);
            }
            LOGGER.log(Level.DEBUG, "Order get by user id " + orders);
            request.setAttribute("userOrders", orders);
        } catch (DAOException e) {
            throw new ServiceException("Service error: registration is failed", e);
        }
        return ConfigurationManager.getProperty("path.page.basket");
    }
}
