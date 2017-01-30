package edu.training.project.command.comment;

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
import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 23.01.2017.
 */
public class AddCommentCommand extends AbstractCommand{
    private static final Logger LOGGER = LogManager.getLogger(AddCommentCommand.class);
    private static final String PARAM_NAME_COMMENT = "comment";
    private static final String PARAM_NAME_SONG_ID = "songId";
    private static final String PARAM_NAME_USER_ID = "userId";
    private static final String JSP_ERROR = "/jsp/error.jsp";
    private static final String JSP_SONG = "/jsp/song.jsp";
    private static final int MAX_COMMENT_LENGTH = 255;

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        LOGGER.log(Level.DEBUG, "Begin execute add commentContent command");
        // extract from request parameters
        String commentContent = request.getParameter(PARAM_NAME_COMMENT);
        int userId = Integer.parseInt(request.getParameter(PARAM_NAME_USER_ID));
        int songId = Integer.parseInt(request.getParameter(PARAM_NAME_SONG_ID));
        if (commentContent.isEmpty() || commentContent.length() > MAX_COMMENT_LENGTH) {
            throw new ServiceException("Service error : not correct parameters for add commentContent.");
        }
        LOGGER.log(Level.DEBUG, "Param " + commentContent + " " + userId + " " + songId);
        try {
            CommentDAO commentDAO = new CommentDAO();
            Comment comment = new Comment();
            comment.setContent(commentContent);
            comment.setSongId(songId);
            comment.setUserId(userId);
            comment.setDate(new Date());

            LOGGER.log(Level.DEBUG, "Comment to add " + comment);

            commentDAO.addComment(comment);
            List<Comment> comments = commentDAO.getCommentBySongId(songId);
            UserDAO userDAO = new UserDAO();
            for (Comment commentary : comments){
                User user = userDAO.getUserById(commentary.getUserId());
                commentary.setUser(user);
            }
            request.setAttribute("comments", comments);

            SongDAO songDAO = new SongDAO();
            Song song = songDAO.getSongById(songId);
            MusicalPerformanceDAO musicalPerformanceDAO = new MusicalPerformanceDAO();
            MusicalPerformance musicalPerformance = musicalPerformanceDAO.getPerformanceById(song.getPerformanceId());
            song.setPerformance(musicalPerformance);
            request.setAttribute("selectedSong", song);

            page = ConfigurationManager.getProperty("path.page.song");;
        } catch (DAOException e) {
            throw new ServiceException("Service error: registration is failed", e);
        }
        return page;
    }
}
