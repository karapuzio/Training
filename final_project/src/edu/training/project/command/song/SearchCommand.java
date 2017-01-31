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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class is used to search similar by title song.
 *
 * @author Skidan Olya
 * @version 1.0
 */
public class SearchCommand extends AbstractCommand{
    private static final Logger LOGGER = LogManager.getLogger(SearchCommand.class);
    private static final String PARAM_NAME_SEARCH = "search";
    private static final String PARAM_NAME_USER_ROLE = "role";

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        // extract search parameters from request
        String search = request.getParameter(PARAM_NAME_SEARCH);
        String role = request.getParameter(PARAM_NAME_USER_ROLE);
        try {
            SongDAO songDAO = new SongDAO();
            List<Song> allSong = songDAO.getAllSongs();
            MusicalPerformanceDAO musicalPerformanceDAO = new MusicalPerformanceDAO();
            for (Song song : allSong){
                MusicalPerformance performance = musicalPerformanceDAO.getPerformanceById(song.getPerformanceId());
                song.setPerformance(performance);
            }
            //Have list of all songs with performance
            //Search the suitable songs
            List<Song> suitableSong = new ArrayList<>();
            for (Song song : allSong){
                song.setCoeffJakkara(howManyMatches(song.getPerformance().getName() + " " + song.getName(), search));
            }
            SongComparator comparator = new SongComparator();
            Collections.sort(allSong, comparator);
            for (int i = 0; i < allSong.size() && i < 10; i++){
                suitableSong.add(allSong.get(i));
            }
            HttpSession session = request.getSession(true);
            session.setAttribute("songs", suitableSong);
            page = role.equalsIgnoreCase("admin") ? ConfigurationManager.getProperty("path.page.admin") : ConfigurationManager.getProperty("path.page.home");
        }
        catch (DAOException e){
            throw new ServiceException("Service error : fall search operation.", e);
        }
        return page;
    }

    /**
     *It finds the similarity coefficient between the lines
     *
     * @param first performance and song name
     * @param second search query
     * @return similarity coefficient between 0 and 1
     */
    private double howManyMatches(String first, String second){
        first = first.toUpperCase();
        second = second.toUpperCase();
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
