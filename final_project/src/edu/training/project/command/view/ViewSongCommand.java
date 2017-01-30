package edu.training.project.command.view;

import edu.training.project.command.AbstractCommand;
import edu.training.project.command.exception.ServiceException;
import edu.training.project.controller.ConfigurationManager;
import edu.training.project.dao.CommentDAO;
import edu.training.project.dao.MusicalPerformanceDAO;
import edu.training.project.dao.SongDAO;
import edu.training.project.dao.UserDAO;
import edu.training.project.dao.exception.DAOException;
import edu.training.project.entity.Comment;
import edu.training.project.entity.MusicalPerformance;
import edu.training.project.entity.Song;
import edu.training.project.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Dell on 25.01.2017.
 */
public class ViewSongCommand extends AbstractCommand{
    private static final Logger LOGGER = LogManager.getLogger(ViewSongCommand.class);
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
            CommentDAO commentDAO = new CommentDAO();
            List<Comment> comments = commentDAO.getCommentBySongId(song.getId());
            UserDAO userDAO = new UserDAO();
            for (Comment comment : comments){
                User user = userDAO.getUserById(comment.getUserId());
                comment.setUser(user);
            }
            LOGGER.log(Level.DEBUG, "Song get by id " + song);
            request.setAttribute("comments", comments);
            request.setAttribute("selectedSong", song);

            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("currentUser");
            page = user.getRole().equalsIgnoreCase("admin") ? ConfigurationManager.getProperty("path.page.songAdmin") : ConfigurationManager.getProperty("path.page.song");
        } catch (DAOException e) {
            throw new ServiceException("Service error: registration is failed", e);
        }
        return page;
    }
}
