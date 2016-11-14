package edu.training.fourth.reader;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Dell on 03.11.2016.
 */
public class TextReader {
    private static final Logger LOGGER = LogManager.getLogger(TextReader.class);

    public static String textReader(String fileName){
        List<String> strings;
        try{
            strings = Files.readAllLines(Paths.get(fileName));
        }
        catch (IOException e){
            LOGGER.log(Level.FATAL, "Not correct file.", e);
            throw new RuntimeException("Not correct file.");
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (String s : strings) {
            stringBuffer.append(s + "\n");
        }
        return new String(stringBuffer);
    }
}
