package edu.training.project.command.order;

import edu.training.project.command.AbstractCommand;
import edu.training.project.command.exception.ServiceException;
import edu.training.project.controller.ConfigurationManager;
import edu.training.project.dao.UserDAO;
import edu.training.project.dao.exception.DAOException;
import edu.training.project.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Dell on 30.01.2017.
 */
public class AddFundsCommand extends AbstractCommand{
    private static final Logger LOGGER = LogManager.getLogger(AddFundsCommand.class);
    private static final String PARAM_NAME_CARD = "card";
    private static final String PARAM_NAME_CVV = "cvv";
    private static final String PARAM_NAME_CASH = "cash";
    private static final int CARD_LENGTH = 8;
    private static final int CVV_LENGTH = 3;

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        // extract from request parameters
        String card = request.getParameter(PARAM_NAME_CARD);
        String cvv = request.getParameter(PARAM_NAME_CVV);
        double cash = Double.parseDouble(request.getParameter(PARAM_NAME_CASH));
        if (card.isEmpty() || card.length() != CARD_LENGTH || cvv.isEmpty() || cvv.length() != CVV_LENGTH) {
            throw new ServiceException("Service error : not correct parameters for add funds.");
        }
        LOGGER.log(Level.DEBUG, "Begin add funds" + " " + card + " " + cvv + " " + cash);
        try {
            UserDAO userDAO = new UserDAO();
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("currentUser");
            LOGGER.log(Level.DEBUG, "User : " + user);
            user.setCash(user.getCash() + cash);

            userDAO.editUserById(user);

            page = ConfigurationManager.getProperty("path.page.user");
        } catch (DAOException e) {
            throw new ServiceException("Service error: registration is failed", e);
        }
        return page;
    }
}
