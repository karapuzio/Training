package test.edu.training.first.action;

import edu.training.first.action.Checker;
import edu.training.first.entity.Point;
import edu.training.first.entity.Triangle;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Dell on 21.09.2016.
 */

public class TriangleActionTest {
    private Triangle triangle;

    @Test
    public void checkTriangleTest(){
        Point pointFirst = new Point(1,3);
        Point pointSecond = new Point(4,6);
        Point pointThird = new Point(6,7);
        triangle = new Triangle(pointFirst, pointSecond, pointThird);
        boolean actual = Checker.checkTriangle(triangle);
        Assert.assertTrue(actual);
    }

    @Test
    public void checkTriangleTestFalse(){
        Point pointFirst = new Point(1,1);
        Point pointSecond = new Point(1,1);
        Point pointThird = new Point(2,1);
        triangle = new Triangle(pointFirst, pointSecond, pointThird);
        boolean actual = Checker.checkTriangle(triangle);
        Assert.assertFalse(actual);
    }

    @Test
    public void checkTriangleTestLineFalse(){
        Point pointFirst = new Point(1,1);
        Point pointSecond = new Point(2,2);
        Point pointThird = new Point(3,3);
        triangle = new Triangle(pointFirst, pointSecond, pointThird);
        boolean actual = Checker.checkTriangle(triangle);
        Assert.assertFalse(actual);
    }
}
