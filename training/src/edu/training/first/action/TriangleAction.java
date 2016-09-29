package edu.training.first.action;

import edu.training.first.entity.Point;
import edu.training.first.entity.Triangle;
import edu.training.first.exception.IncorrectTriagleException;

/**
 * Created by Dell on 21.09.2016.
 */
public class TriangleAction {
    public double calculateSide(Point a, Point b){
        return Math.hypot(a.getX() - b.getX(), a.getY() - b.getY());
    }

    public double calculatePerimetr(Triangle triangle) throws IncorrectTriagleException{
        if (!Checker.checkTriangle(triangle)){
            throw new IncorrectTriagleException(triangle + " is not correct");
        }
        double sideFirst = calculateSide(triangle.getPointFirst(), triangle.getPointSecond());
        double sideSecond = calculateSide(triangle.getPointFirst(), triangle.getPointThird());
        double sideThird = calculateSide(triangle.getPointThird(), triangle.getPointSecond());
        return sideFirst + sideSecond + sideThird;
    }

    public double calculateSquare(Triangle triangle) throws IncorrectTriagleException{
        if (!Checker.checkTriangle(triangle)){
            throw new IncorrectTriagleException(triangle + " is not correct");
        }
        double sideFirst = calculateSide(triangle.getPointFirst(), triangle.getPointSecond());
        double sideSecond = calculateSide(triangle.getPointFirst(), triangle.getPointThird());
        double sideThird = calculateSide(triangle.getPointThird(), triangle.getPointSecond());
        double semiPerimetr = calculatePerimetr(triangle) / 2;
        double square = Math.sqrt(semiPerimetr * (semiPerimetr - sideFirst) *
                (semiPerimetr - sideSecond) * (semiPerimetr - sideThird));
        return square;
    }
}
