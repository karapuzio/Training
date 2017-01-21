package edu.training.project.controller;

import edu.training.project.command.AbstractCommand;
import edu.training.project.command.Client;
import edu.training.project.command.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dell on 20.01.2017.
 */
@WebServlet("/controller")
public class Controller extends HttpServlet{
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {

        LOGGER.log(Level.DEBUG, "Enter to process Request");
        String page = null;
        // define commands, coming from JSP
        AbstractCommand command = Client.getInstance().defineCommand(request);
        /*
        * call realize method execute() and send parameters
        * for defined command class
         */
        LOGGER.log(Level.DEBUG, "Got define command");
        try {
            page = command.execute(request);
            // method return answer page
            // page = null;
            if (page != null) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                // call answer page on request
                dispatcher.forward(request, response);
            } else {
                // install page with the error message
                page = ConfigurationManager.getProperty("path.page.index");
                request.getSession().setAttribute("nullPage",
                        MessageManager.getProperty("message.nullpage"));
                response.sendRedirect(request.getContextPath() + page);
            }
        }
        catch (ServiceException e){
            // TO DO
        }
    }


}
