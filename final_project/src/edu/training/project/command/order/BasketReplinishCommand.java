package edu.training.project.command.order;

import edu.training.project.command.AbstractCommand;
import edu.training.project.command.exception.ServiceException;
import edu.training.project.controller.ConfigurationManager;
import edu.training.project.dao.OrderDAO;
import edu.training.project.dao.exception.DAOException;
import edu.training.project.entity.Order;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Dell on 26.01.2017.
 */
public class BasketReplinishCommand extends AbstractCommand{
    private static final Logger LOGGER = LogManager.getLogger(BasketReplinishCommand.class);
    private static final String PARAM_NAME_SONG_ID = "songId";
    private static final String PARAM_NAME_USER_ID = "userId";
    private static final String JSP_ERROR = "/jsp/error.jsp";
    private static final String JSP_HOME = "/jsp/home.jsp";
    private static final String JSP_ADMIN = "/jsp/admin.jsp";
    private static final String SESSION_USER = "currentUser";

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        // extract song and user id from request
        LOGGER.log(Level.DEBUG, "Begin execute add to basket command");
        int songId = Integer.parseInt(request.getParameter(PARAM_NAME_SONG_ID));
        LOGGER.log(Level.DEBUG, "Begin execute add to basket command" + " " + songId);
        //HttpSession session = request.getSession(true);
//        Object userId = session.getAttribute(PARAM_NAME_USER_ID);
        int userId = Integer.parseInt(request.getParameter(PARAM_NAME_USER_ID));
        LOGGER.log(Level.DEBUG, "Begin execute add to basket command" + " " + songId + " " + userId);
        if (songId == 0 || userId == 0){
            throw new ServiceException("Service error : not correct parameters for add song to basket.");
        }
        try {
            OrderDAO orderDAO = new OrderDAO();
            Order correctOrder = orderDAO.getOrderByUserIdAndSongId(userId, songId);
            if (correctOrder != null){
                return JSP_HOME;
            }
            LOGGER.log(Level.DEBUG, "Begin execute add to basket command" + " " + songId);
            Order order = new Order();
            order.setUserId(userId);
            order.setSongId(songId);
            order.setDateOfOrder(new Date());

            LOGGER.log(Level.DEBUG, "Order to add " + order);

            orderDAO.addOrder(order);
            page = ConfigurationManager.getProperty("path.page.home");
        }
        catch (DAOException e){
            throw new ServiceException("Service error : fall login operation.", e);
        }
        return page;
    }
}
