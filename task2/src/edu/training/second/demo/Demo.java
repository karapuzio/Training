package edu.training.second.demo;

import edu.training.second.action.PresentAction;
import edu.training.second.entity.ChristmasPresent;
import edu.training.second.entity.Sweetness;
import edu.training.second.exception.ParameterException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

/**
 * Created by Dell on 02.10.2016.
 */
public class Demo {
    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(Demo.class);

    public static void main(String... args){
        String fileName = "test/test1.txt";
        ChristmasPresent christmasPresent = new ChristmasPresent(fileName);
        System.out.println(christmasPresent.sizePresent());
        PresentAction presentAction = new PresentAction();
        int presentWeight = presentAction.calculateWeight(christmasPresent);
        try{
            int lowBound = 10;
            int topBound = 60;
            Sweetness candy = presentAction.findCandy(christmasPresent, lowBound, topBound);
            LOGGER.log(Level.DEBUG, "Found candy : " + candy);
        }
        catch (ParameterException ex){
            LOGGER.log(Level.ERROR, ex);
        }
        presentAction.sortBySugary(christmasPresent);
    }
}
