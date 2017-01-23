package edu.training.project.command.autheintic;

import edu.training.project.command.AbstractCommand;
import edu.training.project.command.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dell on 22.01.2017.
 */
public class LanguageChangeCommand extends AbstractCommand{
    private static final String ENGLISH = "EN";
    private static final String NETHERLANDISH = "NL";
    private static final String PARAM_NAME_LANGUAGE = "language";

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        // extract language from request
        String language = request.getParameter(PARAM_NAME_LANGUAGE);

        return null;
    }
}
