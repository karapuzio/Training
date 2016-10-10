package edu.training.second.factory.client;

import edu.training.second.entity.Candy;
import edu.training.second.entity.Cookie;
import edu.training.second.entity.Sweetness;
import edu.training.second.entity.Zephyr;
import edu.training.second.exception.ParameterException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 05.10.2016.
 */
public class SwetnessReader {
    private static final Logger LOGGER = LogManager.getLogger(SwetnessReader.class);
    private static final String splitString = "\\s";
    private static final int typeParameter = 0;

    public static List<Sweetness> readSweetness(String fileName){
        List<String> listOfSweetness;
        try {
            listOfSweetness = Files.readAllLines(Paths.get(fileName));
        }
        catch (IOException ex){
            LOGGER.log(Level.FATAL, "Not correct file.", ex);
            throw new RuntimeException(ex);
        }
        Client client = new Client();
        CandiesFactory candiesFactory = new CandiesFactory();
        CookiesFactory cookiesFactory = new CookiesFactory();
        ZephyrFactory zephyrFactory = new ZephyrFactory();
        List<Sweetness> present = new ArrayList<>();

        for (String s : listOfSweetness) {
            try {
                String[] listOfParameters = s.split(splitString);
                String typeName = listOfParameters[typeParameter];
                switch (typeName) {
                    case "candy":
                        Candy candy = (Candy) client.createSweetness(candiesFactory, listOfParameters);
                        present.add(candy);
                        break;
                    case "cooke":
                        Cookie cooke = (Cookie) client.createSweetness(cookiesFactory, listOfParameters);
                        present.add(cooke);
                        break;
                    case "zephyr":
                        Zephyr zephyr = (Zephyr) client.createSweetness(zephyrFactory, listOfParameters);
                        present.add(zephyr);
                        break;
                    default:
                        LOGGER.log(Level.ERROR, "Not correct type parametr. Do not add sweetness in present.");
                }
            }
            catch (ParameterException ex){
                LOGGER.log(Level.ERROR, "Do not add sweetness in present.", ex);
            }
        }
        return present;
    }
}
