package edu.training.project.command.performer;

import edu.training.project.command.AbstractCommand;
import edu.training.project.command.exception.ServiceException;
import edu.training.project.controller.ConfigurationManager;
import edu.training.project.dao.MusicalPerformanceDAO;
import edu.training.project.dao.exception.DAOException;
import edu.training.project.entity.MusicalPerformance;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dell on 24.01.2017.
 */
public class PerformerAddCommand extends AbstractCommand{
    private static final Logger LOGGER = LogManager.getLogger(PerformerAddCommand.class);
    private static final String PARAM_NAME_PERFORMER = "performer";
    private static final int MAX_PERFORMER_LENGTH = 255;

    private static final String JSP_ERROR = "/jsp/error.jsp";
    private static final String JSP_MAIN = "/jsp/home.jsp";
    private static final String JSP_ADMIN = "/jsp/admin.jsp";

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        LOGGER.log(Level.DEBUG, "Begin execute performer add command");
        // extract from request parameters
        String performer = request.getParameter(PARAM_NAME_PERFORMER);
        if (performer.isEmpty() || performer.length() > MAX_PERFORMER_LENGTH) {
            throw new ServiceException("Service error : not correct parameters for add song.");
        }
        LOGGER.log(Level.DEBUG, "Performer parse " + performer);
        try {
            MusicalPerformanceDAO musicalPerformanceDAO = new MusicalPerformanceDAO();
            MusicalPerformance musicalPerformance = new MusicalPerformance();
            musicalPerformance.setName(performer);

            LOGGER.log(Level.DEBUG, "Performance to add " + musicalPerformance);

            musicalPerformanceDAO.addPerformance(musicalPerformance);

            page = ConfigurationManager.getProperty("path.page.admin");
        } catch (DAOException e) {
            throw new ServiceException("Service error: registration is failed", e);
        }
        return page;
    }
}
