package edu.training.project.controller;

import edu.training.project.command.AbstractCommand;
import edu.training.project.command.Client;
import edu.training.project.command.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class {@code Controller} is a servlet class used as a controller of the application.
 *
 * @author Skidan Olya
 * @see HttpServlet
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

    /**
     * Actions after taking request
     *
     * @param request  is servlet's request
     * @param response is servlet's response
     * @throws ServletException if there are servlet errors
     * @throws IOException      if there are input/output errors
     */
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
        LOGGER.log(Level.DEBUG, "Got define command : " + command);
        try {
            page = command.execute(request);
            // method return answer page
            LOGGER.log(Level.DEBUG, "Page " + page);
            if (page != null) {
                request.getRequestDispatcher(page).forward(request, response);
            } else {
                // install page with the error message
                request.getSession().setAttribute("nullPage", "Null pointer page");
                response.sendRedirect(request.getContextPath() + ConfigurationManager.getProperty("path.page.error"));
            }
        }
        catch (ServiceException e){
            LOGGER.log(Level.ERROR, e, e);
            response.sendRedirect(request.getContextPath() + ConfigurationManager.getProperty("path.page.error"));
        }
    }


}
