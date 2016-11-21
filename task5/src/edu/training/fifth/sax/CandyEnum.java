package edu.training.fifth.sax;

/**
 * Created by Dell on 20.11.2016.
 */
public enum CandyEnum {
    CANDIES("candies"),
    CANDY("candy"),
    ID("id"),
    NAME("name"),
    ENERGY("energy"),
    TYPE("type"),
    INGREDIENT_LIST("ingredients"),
    INGREDIENT("ingredient"),
    VALUE("value"),
    PROTEINS("proteins"),
    FATS("fats"),
    CARBOHYDRATES("carbohydrates"),
    PRODUCTION("production");

    private String value;

    private CandyEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
