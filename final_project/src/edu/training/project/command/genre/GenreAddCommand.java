package edu.training.project.command.genre;

import edu.training.project.command.AbstractCommand;
import edu.training.project.command.exception.ServiceException;
import edu.training.project.controller.ConfigurationManager;
import edu.training.project.dao.MusicGenreDAO;
import edu.training.project.dao.exception.DAOException;
import edu.training.project.entity.MusicGenre;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Class is used to add new genre.
 *
 * @author Skidan Olya
 * @version 1.0
 */
public class GenreAddCommand extends AbstractCommand{
    private static final Logger LOGGER = LogManager.getLogger(GenreAddCommand.class);
    private static final String PARAM_NAME_GENRE = "genre";
    private static final int MAX_GENRE_LENGTH = 45;

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        // extract from request parameters
        String genre = request.getParameter(PARAM_NAME_GENRE);
        if (genre.isEmpty() || genre.length() > MAX_GENRE_LENGTH) {
            throw new ServiceException("Service error : not correct parameters for add genre.");
        }
        try {
            MusicGenreDAO musicGenreDAO = new MusicGenreDAO();
            MusicGenre musicGenre = new MusicGenre();
            musicGenre.setGenre(genre);

            musicGenreDAO.addGenre(musicGenre);

            page = ConfigurationManager.getProperty("path.page.admin");
        } catch (DAOException e) {
            throw new ServiceException("Service error: registration is failed", e);
        }
        return page;
    }
}
