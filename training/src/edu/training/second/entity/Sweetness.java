package edu.training.second.entity;

/**
 * Created by Dell on 25.09.2016.
 */

public abstract class Sweetness {
    private int weightPerUnit;
    private int sugary;
    private int calorific;
    
    public Sweetness(int weightPerUnit, int sugary, int calorific) {
        this.weightPerUnit = weightPerUnit;
        this.sugary = sugary;
        this.calorific = calorific;
    }

    public int getWeightPerUnit() {
        return weightPerUnit;
    }

    public void setWeightPerUnit(int weightPerUnit) {
        this.weightPerUnit = weightPerUnit;
    }

    public int getSugary() {
        return sugary;
    }

    public void setSugary(int sugary) {
        this.sugary = sugary;
    }

    public int getCalorific() {
        return calorific;
    }

    public void setCalorific(int calorific) {
        this.calorific = calorific;
    }

    @Override
    public String toString() {
        return "Candies{" +
                "weightPerUnit=" + weightPerUnit +
                ", sugary=" + sugary +
                ", calorific=" + calorific +
                '}';
    }
}
