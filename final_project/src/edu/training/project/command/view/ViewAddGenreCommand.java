package edu.training.project.command.view;

import edu.training.project.command.AbstractCommand;
import edu.training.project.command.exception.ServiceException;
import edu.training.project.controller.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dell on 31.01.2017.
 */
public class ViewAddGenreCommand extends AbstractCommand{

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        return ConfigurationManager.getProperty("path.page.addGenre");
    }
}
