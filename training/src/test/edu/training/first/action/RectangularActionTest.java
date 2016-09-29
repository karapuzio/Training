package test.edu.training.first.action;

import edu.training.first.action.Checker;
import edu.training.first.entity.Point;
import edu.training.first.entity.Triangle;
import edu.training.first.exception.IncorrectTriagleException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Dell on 26.09.2016.
 */
public class RectangularActionTest {
    @Test
    public void checkRectangularTest(){
        Point pointFirst = new Point(0, 0);
        Point pointSecond = new Point(0, 3);
        Point pointThird = new Point(4, 0);
        Triangle triangle = new Triangle(pointFirst, pointSecond, pointThird);
        try{
            boolean actual = Checker.checkRectangular(triangle);
            Assert.assertTrue(actual);
        }
        catch (IncorrectTriagleException ex) {
            Assert.fail("Test for rectangular triangle should not have IncorrectTriangleException");
        }
    }

    @Test( expected = IncorrectTriagleException.class)
    public void checkRectangularTestException() throws IncorrectTriagleException{
        Point pointFirst = new Point(1,1);
        Point pointSecond = new Point(4,1);
        Point pointThird = new Point(1,1);
        Triangle triangle = new Triangle(pointFirst, pointSecond, pointThird);
        boolean actual = Checker.checkRectangular(triangle);
        Assert.assertFalse(actual);
    }

    @Test
    public void checkRectangularTestFalse(){
        Point pointFirst = new Point(1,1);
        Point pointSecond = new Point(4,1);
        Point pointThird = new Point(2,3);
        Triangle triangle = new Triangle(pointFirst, pointSecond, pointThird);
        try{
            boolean actual = Checker.checkRectangular(triangle);
            Assert.assertFalse(actual);
        }
        catch (IncorrectTriagleException ex) {
            Assert.fail("Test for rectangular test should not have IncorrectTriagleException");
        }
    }
}
