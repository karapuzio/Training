package edu.training.project.tag;

import edu.training.project.dao.MusicalPerformanceDAO;
import edu.training.project.dao.SongDAO;
import edu.training.project.dao.exception.DAOException;
import edu.training.project.entity.MusicalPerformance;
import edu.training.project.entity.Song;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

/**
 * Created by Dell on 29.01.2017.
 */
public class RandomSongTag extends TagSupport{
    private static final Logger LOGGER = LogManager.getLogger(RandomSongTag.class);
    @Override
    public int doStartTag() throws JspException {
        try {
            SongDAO songDAO = new SongDAO();
            List<Song> randomSongs = songDAO.getRandomSong();
            MusicalPerformanceDAO musicalPerformanceDAO = new MusicalPerformanceDAO();
            for (Song song : randomSongs){
                MusicalPerformance performance = musicalPerformanceDAO.getPerformanceById(song.getPerformanceId());
                song.setPerformance(performance);
            }
            JspWriter out = pageContext.getOut();
            out.write("<div class=\"list-group\">");
            for (Song song : randomSongs) {
                out.write("<a href=\"controller?command=view_song&songId=" + song.getId() +"\" class=\"list-group-item\"> "
                        + song.getPerformance().getName() + " - " + song.getName() + "</a>\n");
            }
            out.write("</div>");
        } catch (DAOException e){
            LOGGER.log(Level.ERROR, "Error in random song tag with DAO layer.", e);
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "Error in random song tag.", e);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return  EVAL_PAGE;
    }
}
