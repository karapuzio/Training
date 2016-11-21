package edu.training.fifth.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by Dell on 20.11.2016.
 */
public class ValidatorSax {
    private static final Logger LOGGER = LogManager.getLogger(ValidatorSax.class);

    public static boolean validate(String fileName, String schemaName){
        Schema schema = null;
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        try{
            schema = factory.newSchema(new File(schemaName));
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setSchema(schema);
            SAXParser parser = saxParserFactory.newSAXParser();
            parser.parse(fileName, new CandyErrorHandler());
            LOGGER.log(Level.INFO, fileName + " is valid.");
            return true;
        }
        catch (ParserConfigurationException e){
            LOGGER.log(Level.ERROR, "Config error : ", e);
        }
        catch (SAXException e){
            LOGGER.log(Level.ERROR, "Sax error : ", e);
        }
        catch (IOException e){
            LOGGER.log(Level.ERROR, "I/O error : ", e);
        }
        return false;
    }
}
