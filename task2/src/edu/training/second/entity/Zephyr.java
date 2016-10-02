package edu.training.second.entity;

import edu.training.second.type.TypeCovering;
import edu.training.second.type.TypeTaste;

/**
 * Created by Dell on 25.09.2016.
 */

public class Zephyr extends Sweetness{
    private TypeCovering covering;
    private TypeTaste taste;

    public Zephyr(){
        this(50, 25, 20, TypeCovering.SEMIGLAZED, TypeTaste.CAPPUCCINO);
    }

    public Zephyr(int weightPerUnit, int sugary, int calorific, TypeCovering covering, TypeTaste taste) {
        super(weightPerUnit, sugary, calorific);
        this.covering = covering;
        this.taste = taste;
    }

    public TypeCovering getCovering() {
        return covering;
    }

    public void setCovering(TypeCovering covering) {
        this.covering = covering;
    }

    public TypeTaste getTaste() {
        return taste;
    }

    public void setTaste(TypeTaste taste) {
        this.taste = taste;
    }

    @Override
    public String toString() {
        return "Zephyr{" +
                super.toString() +
                ", covering=" + covering +
                ", taste=" + taste +
                '}';
    }
}
