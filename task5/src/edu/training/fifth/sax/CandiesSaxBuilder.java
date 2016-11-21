package edu.training.fifth.sax;

import edu.training.fifth.entity.Candy;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

/**
 * Created by Dell on 20.11.2016.
 */
public class CandiesSaxBuilder {
    private static final Logger LOGGER = LogManager.getLogger(CandiesSaxBuilder.class);
    private Set<Candy> candies;
    private CandyHandler candyHandler;
    private XMLReader reader;

    public CandiesSaxBuilder() {
        candyHandler = new CandyHandler();
        try{
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(candyHandler);
        }
        catch (SAXException e){
            LOGGER.log(Level.ERROR, e, e);
        }
    }

    public Set<Candy> getCandies() {
        return candies;
    }

    public void buildSetCandies(String fileName){
        try{
            reader.parse(fileName);
        }
        catch (SAXException e){
            LOGGER.log(Level.ERROR, e, e);
        }
        catch (IOException e){
            LOGGER.log(Level.ERROR, e, e);
        }
        candies = candyHandler.getCandies();
    }
}
