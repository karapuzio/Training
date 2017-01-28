package edu.training.project.command.view;

import edu.training.project.command.AbstractCommand;
import edu.training.project.command.exception.ServiceException;
import edu.training.project.controller.ConfigurationManager;
import edu.training.project.dao.SongDAO;
import edu.training.project.dao.exception.DAOException;
import edu.training.project.entity.Song;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dell on 25.01.2017.
 */
public class ViewSongCommand extends AbstractCommand{
    private static final Logger LOGGER = LogManager.getLogger(ViewSongCommand.class);
    private static final String PARAM_NAME_SONG_ID = "songId";

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {

        int songId = Integer.parseInt(request.getParameter(PARAM_NAME_SONG_ID));
        try {
            SongDAO songDAO = new SongDAO();
            Song song = songDAO.getSongById(songId);

            LOGGER.log(Level.DEBUG, "Song get by id " + song);

            request.setAttribute("selectedSong", song);
        } catch (DAOException e) {
            throw new ServiceException("Service error: registration is failed", e);
        }
        return ConfigurationManager.getProperty("path.page.song");
    }
}
