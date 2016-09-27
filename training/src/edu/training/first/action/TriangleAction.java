package edu.training.first.action;

import edu.training.first.Exception.IncorrectTriagleException;
import edu.training.first.entity.Point;
import edu.training.first.entity.Triangle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Dell on 21.09.2016.
 */
public class TriangleAction {
    private static final Logger LOGGER = LogManager.getLogger(TriangleAction.class);

    public static double calculateSide(Point a, Point b){
        return Math.hypot(a.getX() - b.getX(), a.getY() - b.getY());
    }

    public static double calculatePerimetr(Triangle triangle) throws IncorrectTriagleException{
        if (!Checker.checkTriangle(triangle)){
            throw new IncorrectTriagleException(triangle + " is not correct");
        }
        double sideFirst = calculateSide(triangle.getPointFirst(), triangle.getPointSecond());
        double sideSecond = calculateSide(triangle.getPointFirst(), triangle.getPointThird());
        double sideThird = calculateSide(triangle.getPointThird(), triangle.getPointSecond());
        double periment = sideFirst + sideSecond + sideThird;
        return periment;
    }

    public static double calculateSquare(Triangle triangle) throws IncorrectTriagleException{
        if (!Checker.checkTriangle(triangle)){
            throw new IncorrectTriagleException(triangle + " is not correct");
        }
        double sideFirst = calculateSide(triangle.getPointFirst(), triangle.getPointSecond());
        double sideSecond = calculateSide(triangle.getPointFirst(), triangle.getPointThird());
        double sideThird = calculateSide(triangle.getPointThird(), triangle.getPointSecond());
        double semiPeriment = calculatePerimetr(triangle) / 2;
        double square = Math.sqrt(semiPeriment * (semiPeriment - sideFirst) *
                (semiPeriment - sideSecond) * (semiPeriment - sideThird));
        return square;
    }

    public static void readData(){
        String fileName = "test/test1.txt";
        List<String> listOfPoint = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))){
            stream.forEach(listOfPoint::add);
        }
        catch (IOException ex){
            LOGGER.debug("Not correct file.");
        }
    }
}
