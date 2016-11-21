package test.edu.training.fifth;

import edu.training.fifth.validator.ValidatorSax;
import org.junit.Test;
import org.junit.Assert;

/**
 * Created by Dell on 21.11.2016.
 */
public class ValidateTest {
    private static final String fileName = "Candy.xml";
    private static final String schemaName = "Candy.xsd";

    @Test
    public void validateTest(){
        boolean actual = ValidatorSax.validate(fileName, schemaName);
        Assert.assertEquals(actual, true);
    }
}
