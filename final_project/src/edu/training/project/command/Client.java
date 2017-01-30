package edu.training.project.command;

import edu.training.project.command.autheintic.LanguageChangeCommand;
import edu.training.project.command.autheintic.LoginCommand;
import edu.training.project.command.autheintic.LogoutCommand;
import edu.training.project.command.autheintic.RegistrationCommand;
import edu.training.project.command.comment.AddCommentCommand;
import edu.training.project.command.comment.DeleteCommentCommand;
import edu.training.project.command.genre.GenreAddCommand;
import edu.training.project.command.order.AddFundsCommand;
import edu.training.project.command.order.BasketReplinishCommand;
import edu.training.project.command.order.SongOrderCommand;
import edu.training.project.command.performer.PerformerAddCommand;
import edu.training.project.command.song.SearchCommand;
import edu.training.project.command.song.SongAddCommand;
import edu.training.project.command.song.SongDeleteCommand;
import edu.training.project.command.song.SongEditCommand;
import edu.training.project.command.view.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Dell on 20.01.2017.
 */
public class Client {
    private static final Logger LOGGER = LogManager.getLogger(Client.class);
    private static final String COMMAND = "command";
    private static Client instance;
    private static AtomicBoolean instanceCreated = new AtomicBoolean();

    private Client(){
        //TO DO
    }

    public static Client getInstance(){
        if (!instanceCreated.getAndSet(true)){
            instance = new Client();
        }
        return instance;
    }

    public AbstractCommand defineCommand(HttpServletRequest request){
        LOGGER.log(Level.DEBUG, "Define command. ");
        AbstractCommand command = null;
        String cmd = request.getParameter(COMMAND);
        if (cmd == null){
            return command;
        }
        TypeCommand currentCommand = null;
        try {
            currentCommand = TypeCommand.valueOf(cmd.toUpperCase());
        } catch (IllegalArgumentException e) {
            //request.setAttribute("wrongAction", command
                //    + MessageManager.getProperty("message.wrongaction"));
        }
        LOGGER.log(Level.DEBUG, "Parse command. " + currentCommand);
        switch (currentCommand) {
            case LOGIN:
                command = new LoginCommand();
                break;
            case LOGOUT:
                command = new LogoutCommand();
                break;
            case ADD_SONG:
                command = new SongAddCommand();
                break;
            case REGISTRATION:
                LOGGER.log(Level.DEBUG, "Choose registration command.");
                command = new RegistrationCommand();
                break;
            case SEARCH:
                command = new SearchCommand();
                break;
            case LANGUAGE_CHANGE:
                command = new LanguageChangeCommand();
                break;
            case ADD_COMMENT:
                command = new AddCommentCommand();
                break;
            case ADD_GENRE:
                command = new GenreAddCommand();
                break;
            case ADD_PERFORMER:
                command = new PerformerAddCommand();
                break;
            case VIEW_BASKET:
                command = new ViewBasketCommand();
                break;
            case VIEW_CONTACT:
                command = new ViewContactCommand();
                break;
            case VIEW_USER:
                command = new ViewUserCommand();
                break;
            case VIEW_SONG:
                command = new ViewSongCommand();
                break;
            case VIEW_HOME:
                LOGGER.log(Level.DEBUG, "Choose view home command.");
                command = new ViewHomeCommand();
                break;
            case ADD_TO_BASKET:
                command = new BasketReplinishCommand();
                break;
            case SONG_ORDER:
                command = new SongOrderCommand();
                break;
            case EDIT_SONG:
                command = new SongEditCommand();
                break;
            case DELETE_SONG:
                command = new SongDeleteCommand();
                break;
            case VIEW_LOGIN:
                command = new ViewLoginCommand();
                break;
            case VIEW_REGISTRATION:
                command = new ViewRegistrationCommand();
                break;
            case DELETE_COMMENT:
                command = new DeleteCommentCommand();
                break;
            case ADD_FUNDS:
                command = new AddFundsCommand();
                break;
        }
        return command;
    }
}
