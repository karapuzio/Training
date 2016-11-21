package edu.training.fifth.entity;

/**
 * Created by Dell on 20.11.2016.
 */
public class Value {
    private int proteins;
    private int fats;
    private int carbohydrates;

    public Value() {
    }

    public Value(int proteins, int fats, int carbohydrates) {
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }
        Value value = (Value) object;
        if (proteins != value.proteins){
            return false;
        }
        if (fats != value.fats){
            return false;
        }
        return carbohydrates == value.carbohydrates;

    }

    @Override
    public int hashCode() {
        int result = proteins;
        result = 31 * result + fats;
        result = 31 * result + carbohydrates;
        return result;
    }
}
