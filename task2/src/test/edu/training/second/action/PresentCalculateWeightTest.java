package test.edu.training.second.action;

import edu.training.second.action.PresentAction;
import edu.training.second.entity.ChristmasPresent;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by Dell on 03.10.2016.
 */
public class PresentCalculateWeightTest {
    private PresentAction presentAction;

    @Test
    public void checkPresentCalculateWeightTrue(){
        ChristmasPresent christmasPresent = new ChristmasPresent("test/test1.txt");
        presentAction = new PresentAction();
        int actual = presentAction.calculateWeight(christmasPresent);
        Assert.assertEquals(actual, 160);
    }


    @Test
    public void checkPresentCalculateWeightFalse(){
        ChristmasPresent christmasPresent = new ChristmasPresent("test/test2.txt");
        presentAction = new PresentAction();
        int actual = presentAction.calculateWeight(christmasPresent);
        Assert.assertNotSame(actual, 100);
    }
}
