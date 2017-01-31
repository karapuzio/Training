package edu.training.project.command.song;

import edu.training.project.command.AbstractCommand;
import edu.training.project.command.exception.ServiceException;
import edu.training.project.controller.ConfigurationManager;
import edu.training.project.dao.MusicalPerformanceDAO;
import edu.training.project.dao.SongDAO;
import edu.training.project.dao.exception.DAOException;
import edu.training.project.entity.MusicalPerformance;
import edu.training.project.entity.Song;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Class is used to edit song.
 *
 * @author Skidan Olya
 * @version 1.0
 */
public class SongEditCommand extends AbstractCommand{
    private static final Logger LOGGER = LogManager.getLogger(SongEditCommand.class);
    private static final String PARAM_NAME_SONG_PERFORMANCE = "performance";
    private static final String PARAM_NAME_SONG_NAME = "name";
    private static final String PARAM_NAME_DEMO_PATH = "pathToDemo";
    private static final String PARAM_NAME_COST = "cost";
    private static final String PARAM_NAME_EDIT_SONG_ID = "editSongId";
    private static final int MAX_NAME_LENGTH = 45;

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        // extract from request parameters
        String performance = request.getParameter(PARAM_NAME_SONG_PERFORMANCE);
        String name = request.getParameter(PARAM_NAME_SONG_NAME);
        String pathToDemo = request.getParameter(PARAM_NAME_DEMO_PATH);
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
            MusicalPerformanceDAO musicalPerformanceDAO = new MusicalPerformanceDAO();
            if (!performance.isEmpty()) {
                MusicalPerformance musicalPerformance = musicalPerformanceDAO.getPerformanceByName(performance);
                if (musicalPerformance == null) {
                    MusicalPerformance newPerformance = new MusicalPerformance();
                    newPerformance.setName(performance);
                    musicalPerformanceDAO.addPerformance(newPerformance);
                    musicalPerformance = musicalPerformanceDAO.getPerformanceByName(performance);
                }
                song.setPerformanceId(musicalPerformance.getId());
            }
            song.setPerformance(musicalPerformanceDAO.getPerformanceById(song.getPerformanceId()));
            if (!name.isEmpty()){
                song.setName(name);
            }
            if (!pathToDemo.isEmpty()){
                song.setPathToDemo(pathToDemo);
            }
            if (!strCost.isEmpty()){
                song.setCost(cost);
            }
            songDAO.editSongById(song);
            HttpSession session = request.getSession(true);
            List<Song> suitableSong = (List<Song>)session.getAttribute("songs");
            int ind = -1;
            if (suitableSong != null){
                for (int i = 0; i < suitableSong.size(); i++){
                    Song tempSong = suitableSong.get(i);
                    if (tempSong.getId() == editSongId){
                        ind = i;
                    }
                }
            }
            if (ind != -1){
                suitableSong.remove(ind);
                suitableSong.add(ind, song);
            }
            session.setAttribute("songs", suitableSong);
            page = ConfigurationManager.getProperty("path.page.admin");
        } catch (DAOException e) {
            throw new ServiceException("Service error: edit song is failed", e);
        }
        return page;
    }
}
