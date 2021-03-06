package edu.training.project.command.autheintic;

import edu.training.project.command.AbstractCommand;
import edu.training.project.controller.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


/**
 * Class is used to log out and destroy session.
 *
 * @author Skidan Olya
 * @version 1.0
 */
public class LogoutCommand extends AbstractCommand {
    private static final Logger LOGGER = LogManager.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.log(Level.DEBUG, "Logout begin :");
        String page = ConfigurationManager.getProperty("path.page.index");
        // destroy session
        LOGGER.log(Level.DEBUG, "Logout to :" + page);
        request.getSession().invalidate();
        return page;
    }
}
