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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Class is used to delete comment.
 *
 * @author Skidan Olya
 * @version 1.0
 */
public class DeleteCommentCommand extends AbstractCommand{
    private static final String PARAM_NAME_COMMENT_ID = "commentId";
    private static final String PARAM_NAME_SONG_ID = "songId";

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        // extract search parameters from request
        int commentId = Integer.parseInt(request.getParameter(PARAM_NAME_COMMENT_ID));
        int songId = Integer.parseInt(request.getParameter(PARAM_NAME_SONG_ID));
        try {
            CommentDAO commentDAO = new CommentDAO();
            commentDAO.deleteCommentById(commentId);
            SongDAO songDAO = new SongDAO();
            Song song = songDAO.getSongById(songId);
            MusicalPerformanceDAO musicalPerformanceDAO = new MusicalPerformanceDAO();
            MusicalPerformance musicalPerformance = musicalPerformanceDAO.getPerformanceById(song.getPerformanceId());
            song.setPerformance(musicalPerformance);
            List<Comment> comments = commentDAO.getCommentBySongId(song.getId());
            UserDAO userDAO = new UserDAO();
            for (Comment comment : comments){
                User user = userDAO.getUserById(comment.getUserId());
                comment.setUser(user);
            }
            request.setAttribute("comments", comments);
            request.setAttribute("selectedSong", song);
            page = ConfigurationManager.getProperty("path.page.songAdmin");
        }
        catch (DAOException e){
            throw new ServiceException("Service error : fall delete comment operation.", e);
        }
        return page;
    }
}
