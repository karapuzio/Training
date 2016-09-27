package edu.training.second.entity;

/**
 * Created by Dell on 25.09.2016.
 */
public class Candies extends Sweetness{
    private TypeCandy typeCandy;

    public Candies(){
        this(30, 10, 10, TypeCandy.CARAMEL);
    }

    public Candies(int weightPerUnit, int sugary, int calorific, TypeCandy typeCandy) {
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
        return "Candies{" +
                super.toString() +
                ", typeCandy=" + typeCandy +
                '}';
    }
}
