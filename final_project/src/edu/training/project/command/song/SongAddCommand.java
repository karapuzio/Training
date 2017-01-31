package edu.training.project.command.song;

import edu.training.project.command.AbstractCommand;
import edu.training.project.command.exception.ServiceException;
import edu.training.project.controller.ConfigurationManager;
import edu.training.project.dao.MusicGenreDAO;
import edu.training.project.dao.MusicalPerformanceDAO;
import edu.training.project.dao.SongDAO;
import edu.training.project.dao.exception.DAOException;
import edu.training.project.entity.MusicGenre;
import edu.training.project.entity.MusicalPerformance;
import edu.training.project.entity.Song;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Class is used to add new song.
 *
 * @author Skidan Olya
 * @version 1.0
 */
public class SongAddCommand extends AbstractCommand {
    private static final Logger LOGGER = LogManager.getLogger(SongAddCommand.class);
    private static final String PARAM_NAME_SONG_PERFORMANCE = "performance";
    private static final String PARAM_NAME_SONG_NAME = "name";
    private static final String PARAM_NAME_GENRE = "genre";
    private static final String PARAM_NAME_DEMO_PATH = "pathToDemo";
    private static final String PARAM_NAME_COST = "cost";
    private static final int MAX_NAME_LENGTH = 45;

    @Override
    public String execute(HttpServletRequest request) throws ServiceException{
        String page = null;
        LOGGER.log(Level.DEBUG, "Begin execute song add command");
        // extract from request parameters
        String performance = request.getParameter(PARAM_NAME_SONG_PERFORMANCE);
        String name = request.getParameter(PARAM_NAME_SONG_NAME);
        String genre = request.getParameter(PARAM_NAME_GENRE);
        String pathToDemo = request.getParameter(PARAM_NAME_DEMO_PATH);
        double cost = Double.parseDouble(request.getParameter(PARAM_NAME_COST));
        if (name.isEmpty() || name.length() > MAX_NAME_LENGTH) {
            throw new ServiceException("Service error : not correct parameters for add song.");
        }
        try {
            SongDAO songDAO = new SongDAO();
            Song song = new Song();
            MusicalPerformanceDAO musicalPerformanceDAO = new MusicalPerformanceDAO();
            MusicalPerformance musicalPerformance = musicalPerformanceDAO.getPerformanceByName(performance);
            if (musicalPerformance == null){
                MusicalPerformance newPerformance = new MusicalPerformance();
                newPerformance.setName(performance);
                musicalPerformanceDAO.addPerformance(newPerformance);
                musicalPerformance = musicalPerformanceDAO.getPerformanceByName(performance);
            }
            MusicGenreDAO musicGenreDAO = new MusicGenreDAO();
            MusicGenre musicGenre = musicGenreDAO.getGenreByName(genre);
            if (musicGenre == null){
                MusicGenre newGenre = new MusicGenre();
                newGenre.setGenre(genre);
                musicGenreDAO.addGenre(newGenre);
                musicGenre = musicGenreDAO.getGenreByName(genre);
            }
            song.setName(name);
            song.setPathToDemo(pathToDemo);
            song.setCost(cost);
            song.setPerformanceId(musicalPerformance.getId());
            song.setGenreId(musicGenre.getId());

            songDAO.addSong(song);

            page = ConfigurationManager.getProperty("path.page.admin");
        } catch (DAOException e) {
            throw new ServiceException("Service error: song add is failed", e);
        }
        return page;
    }
}
