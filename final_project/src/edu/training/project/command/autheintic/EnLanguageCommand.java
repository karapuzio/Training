package edu.training.project.command.autheintic;

import edu.training.project.command.AbstractCommand;
import edu.training.project.command.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dell on 22.01.2017.
 */
public class EnLanguageCommand extends AbstractCommand{
    private static final String ENGLISH = "EN";
    private static final String NETHERLANDISH = "NL";

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        return null;
    }
}
