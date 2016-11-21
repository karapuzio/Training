package test.edu.training.fifth;

import edu.training.fifth.dom.CandiesDomBuilder;
import edu.training.fifth.sax.CandiesSaxBuilder;
import edu.training.fifth.stax.CandiesStAXBuilder;
import org.junit.Test;
import org.junit.Assert;

/**
 * Created by Dell on 21.11.2016.
 */
public class ParseTest {
    private static final String fileName = "Candy.xml";

    @Test
    public void checkSaxAndDomParse() {
        CandiesSaxBuilder saxBuilder = new CandiesSaxBuilder();
        saxBuilder.buildSetCandies(fileName);
        CandiesDomBuilder domBuilder = new CandiesDomBuilder();
        domBuilder.buildSetCandies(fileName);
        boolean actual = domBuilder.getCandies().equals(saxBuilder.getCandies());
        Assert.assertTrue(actual);
    }

    @Test
    public void checkSaxAndStAXParse() {
        CandiesStAXBuilder stAXBuilder = new CandiesStAXBuilder();
        stAXBuilder.buildSetCandies(fileName);
        CandiesSaxBuilder saxBuilder = new CandiesSaxBuilder();
        saxBuilder.buildSetCandies(fileName);
        boolean actual = saxBuilder.getCandies().equals(stAXBuilder.getCandies());
        Assert.assertTrue(actual);
    }

    @Test
    public void checkDomAndStAXParse() {
        CandiesStAXBuilder stAXBuilder = new CandiesStAXBuilder();
        stAXBuilder.buildSetCandies(fileName);
        CandiesDomBuilder domBuilder = new CandiesDomBuilder();
        domBuilder.buildSetCandies(fileName);
        boolean actual = domBuilder.getCandies().equals(stAXBuilder.getCandies());
        Assert.assertTrue(actual);
    }
}
