package edu.training.project.command.song;

import edu.training.project.command.AbstractCommand;
import edu.training.project.command.exception.ServiceException;
import edu.training.project.controller.ConfigurationManager;
import edu.training.project.dao.SongDAO;
import edu.training.project.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dell on 28.01.2017.
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
        }
        catch (DAOException e){
            throw new ServiceException("Service error : fall delete song operation.", e);
        }
        return page;
    }
}
