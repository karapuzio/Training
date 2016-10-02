package edu.training.second.entity;

import edu.training.second.comparator.SweetnessComparator;
import edu.training.second.factory.client.CandiesFactory;
import edu.training.second.factory.client.Client;
import edu.training.second.factory.client.CookiesFactory;
import edu.training.second.factory.client.ZephyrFactory;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 25.09.2016.
 */
public class ChristmasPresent {
    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(ChristmasPresent.class);
    private List<Sweetness>  present;

    public ChristmasPresent(String fileName){
        SweetnessComparator comparator = new SweetnessComparator();
        present = new ArrayList<>();
        Client client = new Client();
        CandiesFactory candiesFactory = new CandiesFactory();
        CookiesFactory cookiesFactory = new CookiesFactory();
        ZephyrFactory zephyrFactory = new ZephyrFactory();

        List<String> list = read(fileName);
        for (String s : list) {
            String[] listOfParamentrs = s.split("\\s");
            String id = listOfParamentrs[0];
            switch (id) {
                case "candy":
                    Candies candy = (Candies) client.createSweetnes(candiesFactory, listOfParamentrs);
                    present.add(candy);
                    break;
                case "cooke":
                    Cookies cooke = (Cookies) client.createSweetnes(cookiesFactory, listOfParamentrs);
                    present.add(cooke);
                    break;
                case "zephyr":
                    Zephyr zephyr = (Zephyr) client.createSweetnes(zephyrFactory, listOfParamentrs);
                    present.add(zephyr);
                    break;
                default:
                    LOGGER.debug("Not correct input data: " + s);
            }
        }
    }

    private List<String> read(String fileName){
       // String fileName = "test/test1.txt";
        //Stream<String> str = Files.lines(Paths.get(fileName));
        List<String> listOfSweetness = null;
        try {
            listOfSweetness = Files.readAllLines(Paths.get(fileName));
        }
        catch (IOException ex){
            LOGGER.debug("Not correct file: " + fileName);
        }
        return listOfSweetness;
    }

    public List<Sweetness> getPresent() {
        return present;
    }

    public void setPresent(List<Sweetness> present) {
        this.present = present;
    }

    public int sizePresent(){
        return present.size();
    }
}
