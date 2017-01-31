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
 * Class is used to log in.
 *
 * @author Skidan Olya
 * @version 1.0
 */
public class LoginCommand extends AbstractCommand {
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final int MAX_LOGIN_LENGTH = 45;
    private static final int MAX_PASS_LENGTH = 20;
    private static final String SESSION_USER = "currentUser";

    @Override
    public String execute(HttpServletRequest request) throws ServiceException{
        String page = null;
        // extract login and password from request
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        // ckeck login and password
        if (login.isEmpty() || pass.isEmpty() || login.length() > MAX_LOGIN_LENGTH || pass.length() > MAX_PASS_LENGTH){
            throw new ServiceException("Service error : not correct parameters for login.");
        }
        try {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserByLogin(login);
            LOGGER.log(Level.DEBUG, "Check login ans password" + user + " " + login + " " + pass);
            if (user == null) {
                page = ConfigurationManager.getProperty("path.page.login");
                request.setAttribute("isCorrect", 0);
                return page;
            }
            if (!user.getPassword().equals(pass)){
                page = ConfigurationManager.getProperty("path.page.login");
                request.setAttribute("isCorrect", 0);
                return page;
            }
            request.setAttribute("wrongParam", 1);
            String role = user.getRole();
            if (role.equalsIgnoreCase("user")){
                page = ConfigurationManager.getProperty("path.page.home");
            }
            else {
                page = ConfigurationManager.getProperty("path.page.admin");
            }
            HttpSession session = request.getSession(true);
            session.setAttribute(SESSION_USER, user);
        }
        catch (DAOException e){
            throw new ServiceException("Service error : fall login operation.", e);
        }
        return page;
    }
}
