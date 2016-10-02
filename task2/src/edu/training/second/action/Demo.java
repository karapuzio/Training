package edu.training.second.action;

import edu.training.second.entity.ChristmasPresent;
import edu.training.second.entity.Sweetness;
import edu.training.second.exception.ParametrException;
import org.apache.logging.log4j.Logger;

/**
 * Created by Dell on 02.10.2016.
 */
public class Demo {
    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(Demo.class);

    public static void main(String... args){
        ChristmasPresent christmasPresent = new ChristmasPresent("test/test1.txt");
        System.out.println(christmasPresent.sizePresent());
        PresentAction presentAction = new PresentAction();
        int presentWeight = presentAction.calculateWeight(christmasPresent);
        try{
            Sweetness candy = presentAction.findCandy(christmasPresent, 10, 60);
        }
        catch (ParametrException ex){
            LOGGER.debug(ex.getMessage());
        }
        presentAction.sortBySugary(christmasPresent);
    }
}
