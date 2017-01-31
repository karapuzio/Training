package edu.training.project.command;

import edu.training.project.command.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

/**
 * Abstract class is used to execute command.
 *
 * @author Skidan Olya
 * @version 1.0
 */
public abstract class AbstractCommand {
    abstract public String execute(HttpServletRequest request) throws ServiceException;
}
