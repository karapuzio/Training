package edu.training.project.command.song;

import edu.training.project.command.AbstractCommand;
import edu.training.project.command.exception.ServiceException;
import edu.training.project.controller.ConfigurationManager;
import edu.training.project.dao.MusicalPerformanceDAO;
import edu.training.project.dao.SongDAO;
import edu.training.project.dao.exception.DAOException;
import edu.training.project.entity.MusicalPerformance;
import edu.training.project.entity.Song;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dell on 28.01.2017.
 */
public class SongEditCommand extends AbstractCommand{
    private static final Logger LOGGER = LogManager.getLogger(SongEditCommand.class);
    private static final String PARAM_NAME_SONG_PERFORMANCE = "performance";
    private static final String PARAM_NAME_SONG_NAME = "name";
    private static final String PARAM_NAME_DEMO_PATH = "pathToDemo";
    private static final String PARAM_NAME_COST = "cost";
    private static final String PARAM_NAME_EDIT_SONG_ID = "editSongId";
    private static final int MAX_NAME_LENGTH = 255;

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        LOGGER.log(Level.DEBUG, "Begin execute song edit command");
        // extract from request parameters
        String performance = request.getParameter(PARAM_NAME_SONG_PERFORMANCE);
        String name = request.getParameter(PARAM_NAME_SONG_NAME);
        String pathToDemo = request.getParameter(PARAM_NAME_DEMO_PATH);
        LOGGER.log(Level.DEBUG, "Song parse " + name + " " + " " + pathToDemo);
        String strCost = request.getParameter(PARAM_NAME_COST);
        double cost = 0;
        if (!strCost.isEmpty()) {
            cost = Double.parseDouble(request.getParameter(PARAM_NAME_COST));
        }
        int editSongId = Integer.parseInt(request.getParameter(PARAM_NAME_EDIT_SONG_ID));
        if (name.length() > MAX_NAME_LENGTH || performance.length() > MAX_NAME_LENGTH) {
            throw new ServiceException("Service error : not correct parameters for edit song.");
        }
        try {
            SongDAO songDAO = new SongDAO();
            Song song = songDAO.getSongById(editSongId);
            if (!performance.isEmpty()) {
                MusicalPerformanceDAO musicalPerformanceDAO = new MusicalPerformanceDAO();
                MusicalPerformance musicalPerformance = musicalPerformanceDAO.getPerformanceByName(performance);
                if (musicalPerformance == null) {
                    MusicalPerformance newPerformance = new MusicalPerformance();
                    newPerformance.setName(performance);
                    musicalPerformanceDAO.addPerformance(newPerformance);
                    musicalPerformance = musicalPerformanceDAO.getPerformanceByName(performance);
                }
                song.setPerformanceId(musicalPerformance.getId());
            }
            if (!name.isEmpty()){
                song.setName(name);
            }
            if (!pathToDemo.isEmpty()){
                song.setPathToDemo(pathToDemo);
            }
            if (!strCost.isEmpty()){
                song.setCost(cost);
            }

            LOGGER.log(Level.DEBUG, "Song to edit " + song);

            songDAO.editSongById(song);

            LOGGER.log(Level.DEBUG, "Added song " + song);
            page = ConfigurationManager.getProperty("path.page.admin");
        } catch (DAOException e) {
            throw new ServiceException("Service error: edit song is failed", e);
        }
        return page;
    }
}
