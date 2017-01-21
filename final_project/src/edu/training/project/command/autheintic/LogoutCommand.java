package edu.training.project.command.autheintic;

import edu.training.project.command.AbstractCommand;
import edu.training.project.controller.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dell on 20.01.2017.
 */
public class LogoutCommand extends AbstractCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.index");
        // destroy session
        request.getSession().invalidate();
        return page;
    }
}
