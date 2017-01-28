package edu.training.project.command.song;

import edu.training.project.command.AbstractCommand;
import edu.training.project.command.exception.ServiceException;
import edu.training.project.dao.MusicalPerformanceDAO;
import edu.training.project.dao.SongDAO;
import edu.training.project.dao.exception.DAOException;
import edu.training.project.entity.MusicalPerformance;
import edu.training.project.entity.Song;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Dell on 22.01.2017.
 */
public class SearchCommand extends AbstractCommand{
    private static final Logger LOGGER = LogManager.getLogger(SearchCommand.class);
    private static final String PARAM_NAME_SEARCH = "search";
    private static final String JSP_ERROR = "/jsp/error.jsp";
    private static final String JSP_MAIN = "/jsp/home.jsp";
    private static final double PERCENTAGE_MATCH = 0.5;

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
            MusicalPerformanceDAO musicalPerformanceDAO = new MusicalPerformanceDAO();
            for (Song song : allSong){
                LOGGER.log(Level.DEBUG, "Get perfomance by Id " + song.getPerformanceId());
                MusicalPerformance performance = musicalPerformanceDAO.getPerformanceById(song.getPerformanceId());
                LOGGER.log(Level.DEBUG, "Get perfomance by Id " + performance);
                song.setPerformance(performance);
            }
            //Have list of all songs with performance
            //Search the suitable songs
            List<Song> suitableSong = new ArrayList<>();
            for (Song song : allSong){
//                if (howManyMatches(song.getPerformance().getName() + " " + song.getName(), search) > PERCENTAGE_MATCH){
//                    suitableSong.add(song);
//                }
                song.setCoeffJakkara(howManyMatches(song.getPerformance().getName() + " " + song.getName(), search));
            }
            SongComparator comparator = new SongComparator();
            Collections.sort(allSong, comparator);
            LOGGER.log(Level.DEBUG, "Get all sorted " + allSong);
            for (int i = 0; i < allSong.size() && i < 10; i++){
                suitableSong.add(allSong.get(i));
            }
            HttpSession session = request.getSession(true);
            session.setAttribute("songs", suitableSong);
            page = JSP_MAIN;
            LOGGER.log(Level.DEBUG, "Page " + page);
        }
        catch (DAOException e){
            throw new ServiceException("Service error : fall login operation.", e);
        }
        return page;
    }

    private double howManyMatches(String first, String second){
        List<Character> firstList = new ArrayList<>();
        List<Character> secondList = new ArrayList<>();
        for (int i = 0; i < first.length(); i++){
            firstList.add(first.charAt(i));
        }
        for (int i = 0; i < second.length(); i++){
            secondList.add(second.charAt(i));
        }
        Collections.sort(firstList);
        Collections.sort(secondList);
        int j = 0;
        int i = 0;
        double countOfSameCharacter = 0;
        while (j < secondList.size() && i < firstList.size()){
            if (firstList.get(i).compareTo(secondList.get(j)) > 0 ){
                j++;
            }
            else{
                if (firstList.get(i).compareTo(secondList.get(j)) < 0){
                    i++;
                }
                else{
                    i++;
                    j++;
                    countOfSameCharacter++;
                }
            }
        }
        double coefJakkara = countOfSameCharacter/(firstList.size() + secondList.size() - countOfSameCharacter);
        return coefJakkara;
    }
}
