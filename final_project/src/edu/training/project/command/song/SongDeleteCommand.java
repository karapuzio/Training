package edu.training.project.command.song;

import edu.training.project.command.AbstractCommand;
import edu.training.project.command.exception.ServiceException;
import edu.training.project.controller.ConfigurationManager;
import edu.training.project.dao.SongDAO;
import edu.training.project.dao.exception.DAOException;
import edu.training.project.entity.Song;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Class is used to delete song.
 *
 * @author Skidan Olya
 * @version 1.0
 */
public class SongDeleteCommand extends AbstractCommand{
    private static final Logger LOGGER = LogManager.getLogger(SongDeleteCommand.class);
    private static final String PARAM_NAME_SONG_ID = "songId";

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        // extract search parameters from request
        int songId = Integer.parseInt(request.getParameter(PARAM_NAME_SONG_ID));
        try {
            SongDAO songDAO = new SongDAO();
            songDAO.deleteSongById(songId);
            page = ConfigurationManager.getProperty("path.page.admin");
            HttpSession session = request.getSession(true);
            List<Song> suitableSong = (List<Song>)session.getAttribute("songs");
            int ind = -1;
            for (int i = 0; i < suitableSong.size(); i++){
                Song song = suitableSong.get(i);
                if (song.getId() == songId){
                    ind = i;
                }
            }
            if (ind != -1){
                suitableSong.remove(ind);
            }
            session.setAttribute("songs", suitableSong);
        }
        catch (DAOException e){
            throw new ServiceException("Service error : fall delete song operation.", e);
        }
        return page;
    }
}
