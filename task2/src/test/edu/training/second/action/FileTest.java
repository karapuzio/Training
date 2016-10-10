package test.edu.training.second.action;

import edu.training.second.entity.ChristmasPresent;
import org.junit.Test;
import org.junit.Assert;

/**
 * Created by Dell on 09.10.2016.
 */

public class FileTest {
    @Test(expected = RuntimeException.class)
    public void openNotCorrectFileTest(){
        String fileName = "test/test10.txt";
        ChristmasPresent christmasPresent = new ChristmasPresent(fileName);
        Assert.fail("Test for not correct file should have Runtime Exception.");
    }
}
