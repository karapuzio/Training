package edu.training.first.action;

import edu.training.first.Exception.IncorrectTriagleException;
import edu.training.first.entity.Triangle;

/**
 * Created by Dell on 26.09.2016.
 */
public class Checker {
       public static boolean checkTriangle(Triangle triangle){
        if (triangle.getPointFirst().equals(triangle.getPointSecond()) ||
                triangle.getPointFirst().equals(triangle.getPointThird()) ||
                triangle.getPointSecond().equals(triangle.getPointThird())){
            return false;
        }
        if ((triangle.getPointFirst().getX()-triangle.getPointSecond().getX()) /
                (triangle.getPointThird().getX()-triangle.getPointSecond().getX()) ==
                (triangle.getPointFirst().getY()-triangle.getPointSecond().getY()) /
                        (triangle.getPointThird().getY()-triangle.getPointSecond().getY())){
            return false;
        }
        return true;
    }

    public static boolean checkRectangular(Triangle triangle) throws IncorrectTriagleException{
        if (!Checker.checkTriangle(triangle)){
            throw new IncorrectTriagleException(triangle + " is not correct.");
        }
        double sideFirst = TriangleAction.calculateSide(triangle.getPointFirst(), triangle.getPointSecond());
        double sideSecond = TriangleAction.calculateSide(triangle.getPointFirst(), triangle.getPointThird());
        double sideThird = TriangleAction.calculateSide(triangle.getPointThird(), triangle.getPointSecond());
        if (sideFirst * sideFirst + sideSecond * sideSecond == sideThird * sideThird ||
                sideFirst * sideFirst + sideThird * sideThird == sideSecond * sideSecond ||
                sideThird * sideThird + sideSecond * sideSecond == sideFirst * sideFirst) {
            return true;
        }
        return false;
    }
}
