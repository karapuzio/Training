package edu.training.fifth.entity;

/**
 * Created by Dell on 20.11.2016.
 */
public class Sweetness {
    private int energy;
    private Type type;
    private Ingredients ingredients;
    private Value value;
    private String production;

    public Sweetness(){
        Value value = new Value();
        this.value = value;
        Ingredients ingredients = new Ingredients();
        this.ingredients = ingredients;
    }

    public Sweetness(int energy, Type type, Ingredients ingredients, Value value, String production) {
        this.energy = energy;
        this.type = type;
        this.ingredients = ingredients;
        this.value = value;
        this.production = production;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    @Override
    public String toString() {
        return "Sweetness{" +
                "energy=" + energy +
                ", type=" + type +
                ", ingredients=" + ingredients +
                ", value=" + value +
                ", production='" + production + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object){
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }
        Sweetness sweetness = (Sweetness) object;
        if (energy != sweetness.energy){
            return false;
        }
        if (type != sweetness.type){
            return false;
        }
        if (ingredients != null ? !ingredients.equals(sweetness.ingredients) : sweetness.ingredients != null) {
            return false;
        }
        if (value != null ? !value.equals(sweetness.value) : sweetness.value != null){
            return false;
        }
        return !(production != null ? !production.equals(sweetness.production) : sweetness.production != null);

    }

    @Override
    public int hashCode() {
        int result = energy;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (ingredients != null ? ingredients.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (production != null ? production.hashCode() : 0);
        return result;
    }
}
