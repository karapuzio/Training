package edu.training.project.command.song;

import edu.training.project.command.AbstractCommand;
import edu.training.project.command.exception.ServiceException;
import edu.training.project.dao.SongDAO;
import edu.training.project.dao.exception.DAOException;
import edu.training.project.entity.Song;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 22.01.2017.
 */
public class SearchCommand extends AbstractCommand{
    private static final Logger LOGGER = LogManager.getLogger(SearchCommand.class);
    private static final String PARAM_NAME_SEARCH = "search";
    private static final String JSP_ERROR = "/jsp/error.jsp";
    private static final String JSP_MAIN = "/jsp/home.jsp";
    private static final double PERCENTAGE_MATCH = 0.75;

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        // extract search parameters from request
        String search = request.getParameter(PARAM_NAME_SEARCH);
        try {
            SongDAO songDAO = new SongDAO();
            LOGGER.log(Level.DEBUG, "Begin execute search command" + " " + search);
            List<Song> allSong = songDAO.getAllSongs();
            LOGGER.log(Level.DEBUG, "Get all songs " + allSong);

            List<Song> suitableSong = new ArrayList<>();
            for (Song song : allSong){
                if (howManyMatches(song.getName(), search) > PERCENTAGE_MATCH){
                    suitableSong.add(song);
                }
            }
            request.setAttribute("songs", suitableSong);
            page = JSP_MAIN;
            LOGGER.log(Level.DEBUG, "Page " + page);
        /*    HttpSession session = request.getSession(true);
            session.setAttribute(USER_ID_SESSION_ATTRIBUTE, user.getId());
            session.setAttribute(USER_STATUS_SESSION_ATTRIBUTE, user.getStatus());
            session.setAttribute(LANGUAGE_ID_SESSION_ATTRIBUTE, user.getLanguageId()); */
        }
        catch (DAOException e){
            throw new ServiceException("Service error : fall login operation.", e);
        }
        return page;
    }

    private double howManyMatches(String first, String second){
        return 0.8;
    }
}
