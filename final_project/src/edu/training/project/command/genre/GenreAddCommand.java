package edu.training.project.command.genre;

import edu.training.project.command.AbstractCommand;
import edu.training.project.command.exception.ServiceException;
import edu.training.project.dao.MusicGenreDAO;
import edu.training.project.dao.exception.DAOException;
import edu.training.project.entity.MusicGenre;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dell on 24.01.2017.
 */
public class GenreAddCommand extends AbstractCommand{
    private static final Logger LOGGER = LogManager.getLogger(GenreAddCommand.class);
    private static final String PARAM_NAME_GENRE = "genre";
    private static final int MAX_GENRE_LENGTH = 255;

    private static final String JSP_ERROR = "/jsp/error.jsp";
    private static final String JSP_MAIN = "/jsp/home.jsp";
    private static final String JSP_ADMIN = "/jsp/admin.jsp";

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        LOGGER.log(Level.DEBUG, "Begin execute genre add command");
        // extract from request parameters
        String genre = request.getParameter(PARAM_NAME_GENRE);
        if (genre.isEmpty() || genre.length() > MAX_GENRE_LENGTH) {
            throw new ServiceException("Service error : not correct parameters for add song.");
        }
        LOGGER.log(Level.DEBUG, "Genre parse " + genre);
        try {
            MusicGenreDAO musicGenreDAO = new MusicGenreDAO();
            MusicGenre musicGenre = new MusicGenre();
            musicGenre.setGenre(genre);

            LOGGER.log(Level.DEBUG, "Genre to add " + genre);

            musicGenreDAO.addGenre(musicGenre);

            //page = ConfigurationManager.getProperty("path.page.main");
            page = JSP_ADMIN;
        } catch (DAOException e) {
            throw new ServiceException("Service error: registration is failed", e);
        }
        return page;
    }
}
