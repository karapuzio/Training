package edu.training.project.command.autheintic;

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
 * Created by Dell on 20.01.2017.
 */
public class RegistrationCommand extends AbstractCommand {
    private static final Logger LOGGER = LogManager.getLogger(RegistrationCommand.class);
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_CONFIRM = "confirm";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final int MAX_LOGIN_LENGTH = 45;
    private static final int MAX_PASS_LENGTH = 20;
    private static final String JSP_ERROR = "/jsp/error.jsp";
    private static final String JSP_MAIN = "/jsp/home.jsp";
    private static final String SESSION_USER = "currentUser";

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        LOGGER.log(Level.DEBUG, "Begin execute registration command");
        // extract from request parameters
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        String confirmPass = request.getParameter(PARAM_NAME_CONFIRM);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        if (login.isEmpty() || pass.isEmpty() || confirmPass.isEmpty() || email.isEmpty() || login.length() > MAX_LOGIN_LENGTH
                || !pass.equals(confirmPass) || pass.length() > MAX_PASS_LENGTH) {
            throw new ServiceException("Service error : not correct parameters for login.");
        }
        LOGGER.log(Level.DEBUG, "Check is succeeded " + login + " " + pass + " " + confirmPass + " " + email);
        try {
            UserDAO userDAO = new UserDAO();
            User userCheck = userDAO.getUserByLogin(login);
            if (userCheck != null) {
                throw new ServiceException("Service error : not correct parameters for login.");
            }
            User user = new User();
            user.setLogin(login);
            user.setEmail(email);
            user.setPassword(pass);

            LOGGER.log(Level.DEBUG, "User to add " + user);

            userDAO.addUser(user);
            user = userDAO.getUserByLogin(user.getLogin());

            LOGGER.log(Level.DEBUG, "Added user " + user);
            //page = ConfigurationManager.getProperty("path.page.main");
            HttpSession session = request.getSession(true);
            session.setAttribute(SESSION_USER, user);
            page = ConfigurationManager.getProperty("path.page.home");
        } catch (DAOException e) {
            throw new ServiceException("Service error: registration is failed", e);
        }
        return page;
    }
}
