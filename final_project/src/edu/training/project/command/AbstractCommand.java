package edu.training.project.command;

import edu.training.project.command.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dell on 20.01.2017.
 */
public abstract class AbstractCommand {
    abstract public String execute(HttpServletRequest request) throws ServiceException;
}
