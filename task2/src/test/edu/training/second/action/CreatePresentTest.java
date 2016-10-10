package test.edu.training.second.action;

import edu.training.second.entity.ChristmasPresent;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Dell on 10.10.2016.
 */
public class CreatePresentTest {
    @Test
    public void createPresent(){
        String fileName = "test/test1.txt";
        ChristmasPresent christmasPresent = new ChristmasPresent(fileName);
        int actual = christmasPresent.sizePresent();
        int expected = 3;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void createEmptyPresent(){
        String fileName = "test/test5.txt";
        ChristmasPresent christmasPresent = new ChristmasPresent(fileName);
        int actual = christmasPresent.sizePresent();
        int expected = 1;
        Assert.assertNotSame(actual, expected);
    }
}
