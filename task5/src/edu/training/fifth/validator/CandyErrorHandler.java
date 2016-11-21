package edu.training.fifth.validator;

import jdk.internal.org.xml.sax.SAXParseException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;

/**
 * Created by Dell on 20.11.2016.
 */
public class CandyErrorHandler extends DefaultHandler{
    private static final Logger LOGGER = LogManager.getLogger(CandyErrorHandler.class);

    public CandyErrorHandler() throws IOException{
    }

    public void warning(SAXParseException e){
        LOGGER.log(Level.WARN, e,e);
    }

    public void error(SAXParseException e){
        LOGGER.log(Level.ERROR, e,e);
    }

    public void fatalError(SAXParseException e){
        LOGGER.log(Level.FATAL, e,e);
    }

    public String getLineAdress(SAXParseException e){
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }
}
