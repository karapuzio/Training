package edu.training.project.command.view;

import edu.training.project.command.AbstractCommand;
import edu.training.project.command.exception.ServiceException;
import edu.training.project.controller.ConfigurationManager;
import edu.training.project.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Dell on 25.01.2017.
 */
public class ViewHomeCommand extends AbstractCommand{

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("currentUser");
        String page = user.getRole().equalsIgnoreCase("admin") ? ConfigurationManager.getProperty("path.page.admin") : ConfigurationManager.getProperty("path.page.home");
        return page;
    }
}
