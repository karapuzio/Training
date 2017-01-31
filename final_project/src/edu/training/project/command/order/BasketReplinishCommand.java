package edu.training.project.command.order;

import edu.training.project.command.AbstractCommand;
import edu.training.project.command.exception.ServiceException;
import edu.training.project.controller.ConfigurationManager;
import edu.training.project.dao.OrderDAO;
import edu.training.project.dao.exception.DAOException;
import edu.training.project.entity.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Class is used to replinish basket with new song.
 *
 * @author Skidan Olya
 * @version 1.0
 */
public class BasketReplinishCommand extends AbstractCommand{
    private static final Logger LOGGER = LogManager.getLogger(BasketReplinishCommand.class);
    private static final String PARAM_NAME_SONG_ID = "songId";
    private static final String PARAM_NAME_USER_ID = "userId";

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        // extract song and user id from request
        int songId = Integer.parseInt(request.getParameter(PARAM_NAME_SONG_ID));
        int userId = Integer.parseInt(request.getParameter(PARAM_NAME_USER_ID));
        if (songId == 0 || userId == 0){
            throw new ServiceException("Service error : not correct parameters for add song to basket.");
        }
        try {
            OrderDAO orderDAO = new OrderDAO();
            Order correctOrder = orderDAO.getOrderByUserIdAndSongId(userId, songId);
            if (correctOrder != null){
                return ConfigurationManager.getProperty("path.page.home");
            }
            Order order = new Order();
            order.setUserId(userId);
            order.setSongId(songId);
            order.setDateOfOrder(new Date());

            orderDAO.addOrder(order);
            page = ConfigurationManager.getProperty("path.page.home");
        }
        catch (DAOException e){
            throw new ServiceException("Service error : fall add song to basket operation.", e);
        }
        return page;
    }
}
