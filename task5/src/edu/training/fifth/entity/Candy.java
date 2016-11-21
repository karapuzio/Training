package edu.training.fifth.entity;

/**
 * Created by Dell on 20.11.2016.
 */
public class Candy extends Sweetness{
    private String id;
    private String name;

    public Candy() {
    }

    public Candy(int energy, Type type, Ingredients ingredients, Value value, String production, String name, String id) {
        super(energy, type, ingredients, value, production);
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Candy{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}' + super.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object){
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }
        if (!super.equals(object)){
            return false;
        }
        Candy candy = (Candy) object;
        if (id != null ? !id.equals(candy.id) : candy.id != null){
            return false;
        }
        return !(name != null ? !name.equals(candy.name) : candy.name != null);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
