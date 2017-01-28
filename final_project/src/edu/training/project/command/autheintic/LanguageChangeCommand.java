package edu.training.project.command.autheintic;

import edu.training.project.command.AbstractCommand;
import edu.training.project.command.exception.ServiceException;
import edu.training.project.controller.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Dell on 22.01.2017.
 */
public class LanguageChangeCommand extends AbstractCommand{
    private static final String ENGLISH = "en";
    private static final String NETHERLANDISH = "nl";
    private static final String PARAM_NAME_LANGUAGE = "language";

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        // extract language from request
        HttpSession session = request.getSession(true);
        String language = request.getParameter(PARAM_NAME_LANGUAGE);
        String selectedLanguage = language.equalsIgnoreCase("NL") ? NETHERLANDISH : ENGLISH;
        session.setAttribute("language", selectedLanguage);
        page = ConfigurationManager.getProperty("path.page.home");
        return page;
    }
}
