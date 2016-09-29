package edu.training.first.action;

import edu.training.first.entity.Point;
import edu.training.first.entity.Triangle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Dell on 29.09.2016.
 */
public class Demo {
    private static final Logger LOGGER = LogManager.getLogger(Demo.class);

    public static void main(String... args){
        try {
            Point pointFirst = new Point(0,0);
            Point pointSecond = new Point(0,6);
            Point pointThird = new Point(4,0);
            Triangle triangle = new Triangle(pointFirst, pointSecond, pointThird);
            TriangleAction triangleAction = new TriangleAction();
            double square = triangleAction.calculateSquare(triangle);
        }
        catch (Exception ex){
            LOGGER.debug("It's not triagle. " + ex.getMessage());
        }
    }
}