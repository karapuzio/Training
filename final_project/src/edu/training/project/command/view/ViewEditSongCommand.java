package edu.training.project.command.view;

import edu.training.project.command.AbstractCommand;
import edu.training.project.command.exception.ServiceException;
import edu.training.project.controller.ConfigurationManager;
import edu.training.project.dao.MusicalPerformanceDAO;
import edu.training.project.dao.SongDAO;
import edu.training.project.dao.exception.DAOException;
import edu.training.project.entity.MusicalPerformance;
import edu.training.project.entity.Song;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dell on 31.01.2017.
 */
public class ViewEditSongCommand extends AbstractCommand{
    private static final String PARAM_NAME_SONG_ID = "songId";

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        int songId = Integer.parseInt(request.getParameter(PARAM_NAME_SONG_ID));
        try {
            SongDAO songDAO = new SongDAO();
            Song song = songDAO.getSongById(songId);
            MusicalPerformanceDAO musicalPerformanceDAO = new MusicalPerformanceDAO();
            MusicalPerformance musicalPerformance = musicalPerformanceDAO.getPerformanceById(song.getPerformanceId());
            song.setPerformance(musicalPerformance);
            request.setAttribute("selectedSong", song);
            page = ConfigurationManager.getProperty("path.page.editSong");
        } catch (DAOException e) {
            throw new ServiceException("Service error: view edit song is failed", e);
        }
        return page;
    }
}
