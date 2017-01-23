package edu.training.project.command.comment;

import edu.training.project.command.AbstractCommand;
import edu.training.project.command.exception.ServiceException;
import edu.training.project.dao.CommentDAO;
import edu.training.project.dao.exception.DAOException;
import edu.training.project.entity.Comment;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Dell on 23.01.2017.
 */
public class AddCommentCommand extends AbstractCommand{
    private static final Logger LOGGER = LogManager.getLogger(AddCommentCommand.class);
    private static final String PARAM_NAME_COMMENT = "comment";
    private static final String JSP_ERROR = "/jsp/error.jsp";
    private static final String JSP_SONG = "/jsp/song.jsp";
    private static final int MAX_COMMENT_LENGTH = 255;

    @Override
    public String execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        LOGGER.log(Level.DEBUG, "Begin execute add commentContent command");
        // extract from request parameters
        String commentContent = request.getParameter(PARAM_NAME_COMMENT);
        if (commentContent.isEmpty() || commentContent.length() > MAX_COMMENT_LENGTH) {
            throw new ServiceException("Service error : not correct parameters for add commentContent.");
        }
        try {
            CommentDAO commentDAO = new CommentDAO();
            Comment comment = new Comment();
            comment.setContent(commentContent);

            LOGGER.log(Level.DEBUG, "Comment to add " + comment);

            commentDAO.addComment(comment);

            List<Comment> allComment = commentDAO.getAllComments();
            request.setAttribute("comments", allComment);

            page = JSP_SONG;
        } catch (DAOException e) {
            throw new ServiceException("Service error: registration is failed", e);
        }
        return page;
    }
}
