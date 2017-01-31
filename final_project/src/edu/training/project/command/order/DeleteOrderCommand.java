package edu.training.project.command.order;

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
 * Created by Dell on 31.01.2017.
 */
public class DeleteOrderCommand extends AbstractCommand{
    private static final Logger LOGGER = LogManager.getLogger(DeleteOrderCommand.class);
    private static final String PARAM_NAME_ORDER_ID = "orderId";
    private static final String PARAM_NAME_USER_ID = "userId";

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        // extract search parameters from request
        LOGGER.log(Level.DEBUG, "Begin execute delete order command");
        int orderId = Integer.parseInt(request.getParameter(PARAM_NAME_ORDER_ID));
        int userId = Integer.parseInt(request.getParameter(PARAM_NAME_USER_ID));
        LOGGER.log(Level.DEBUG, "Param : " + orderId + " "  + userId);
        try {
            OrderDAO orderDAO = new OrderDAO();
            orderDAO.deleteOrderById(orderId);
            LOGGER.log(Level.DEBUG, "Delete : " + orderId + " "  + userId);
            List<Order> orders = orderDAO.getOrdersByUserId(userId);
            SongDAO songDAO = new SongDAO();
            MusicalPerformanceDAO musicalPerformanceDAO = new MusicalPerformanceDAO();
            for (Order order : orders){
                order.setSong(songDAO.getSongById(order.getSongId()));
                MusicalPerformance performance = musicalPerformanceDAO.getPerformanceById(order.getSong().getPerformanceId());
                order.getSong().setPerformance(performance);
            }
            request.setAttribute("userOrders", orders);
            page = ConfigurationManager.getProperty("path.page.basket");
        }
        catch (DAOException e){
            throw new ServiceException("Service error : fall delete order operation.", e);
        }
        return page;
    }
}
