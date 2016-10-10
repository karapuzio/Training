package edu.training.second.entity;

import edu.training.second.type.TypeCandy;

/**
 * Created by Dell on 25.09.2016.
 */
public class Candy extends Sweetness{
    private TypeCandy typeCandy;

    public Candy(int weightPerUnit, int sugary, int calorific, TypeCandy typeCandy) {
        super(weightPerUnit, sugary, calorific);
        this.typeCandy = typeCandy;
    }

    public TypeCandy getTypeCandy() {
        return typeCandy;
    }

    public void setTypeCandy(TypeCandy typeCandy) {
        this.typeCandy = typeCandy;
    }

    @Override
    public String toString() {
        return "Candy{" +
                super.toString() +
                ", typeCandy=" + typeCandy +
                '}';
    }
}
