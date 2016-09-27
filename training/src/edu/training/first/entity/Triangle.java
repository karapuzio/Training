package edu.training.first.entity;

/**
 * Created by Dell on 21.09.2016.
 */
public class Triangle {
    private Point pointFirst;
    private Point pointSecond;
    private Point pointThird;

    public Triangle(Point pointFirst, Point pointSecond, Point pointThird) {
        this.pointFirst = pointFirst;
        this.pointSecond = pointSecond;
        this.pointThird = pointThird;
    }

    public Point getPointFirst() {
        return pointFirst;
    }

    public Point getPointSecond() {
        return pointSecond;
    }

    public Point getPointThird() {
        return pointThird;
    }

    public void setPointFirst(Point pointFirst) {
        this.pointFirst = pointFirst;
    }

    public void setPointSecond(Point pointSecond) {
        this.pointSecond = pointSecond;
    }

    public void setPointThird(Point pointThird) {
        this.pointThird = pointThird;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "pointFirst=" + pointFirst +
                ", pointSecond=" + pointSecond +
                ", pointThird=" + pointThird +
                '}';
    }
}
