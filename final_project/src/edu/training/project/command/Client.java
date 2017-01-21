package edu.training.project.command;

import edu.training.project.command.autheintic.LoginCommand;
import edu.training.project.command.autheintic.LogoutCommand;
import edu.training.project.command.autheintic.RegistrationCommand;
import edu.training.project.command.song.SongAddCommand;
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
        LOGGER.log(Level.DEBUG, "Parse command.");
        switch (currentCommand) {
            case LOGIN:
                command = new LoginCommand();
                break;
            case LOGOUT:
                command = new LogoutCommand();
                break;
            case SONG_ADD:
                command = new SongAddCommand();
                break;
            case REGISTRATION:
                LOGGER.log(Level.DEBUG, "Choose registration command.");
                command = new RegistrationCommand();
                break;
        }
        return command;
    }
}
