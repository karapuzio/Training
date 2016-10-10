package test.edu.training.second.action;

import edu.training.second.action.PresentAction;
import edu.training.second.entity.ChristmasPresent;
import org.junit.Test;
import org.junit.Assert;

/**
 * Created by Dell on 03.10.2016.
 */
public class PresentCalculateWeightTest {
    private PresentAction presentAction;

    @Test
    public void checkPresentCalculateWeightTrue(){
        String fileName = "test/test1.txt";
        ChristmasPresent christmasPresent = new ChristmasPresent(fileName);
        presentAction = new PresentAction();
        int actual = presentAction.calculateWeight(christmasPresent);
        int expected = 90;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void checkPresentCalculateWeightFalse(){
        String fileName = "test/test1.txt";
        ChristmasPresent christmasPresent = new ChristmasPresent(fileName);
        presentAction = new PresentAction();
        int actual = presentAction.calculateWeight(christmasPresent);
        int expected = 100;
        Assert.assertNotSame(actual, expected);
    }
}
