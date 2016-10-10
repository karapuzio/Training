package edu.training.second.entity;

import edu.training.second.type.TypeCovering;
import edu.training.second.type.TypeDough;

/**
 * Created by Dell on 25.09.2016.
 */

public class Cookie extends Sweetness{
    private TypeDough dough;
    private TypeCovering covering;

    public Cookie(int weightPerUnit, int sugary, int calorific, TypeDough dough, TypeCovering covering) {
        super(weightPerUnit, sugary, calorific);
        this.dough = dough;
        this.covering = covering;
    }

    public TypeDough getDough() {
        return dough;
    }

    public void setDough(TypeDough dough) {
        this.dough = dough;
    }

    public TypeCovering getCovering() {
        return covering;
    }

    public void setCovering(TypeCovering covering) {
        this.covering = covering;
    }

    @Override
    public String toString() {
        return "Cookie{" +
                super.toString() +
                ", dough=" + dough +
                ", covering=" + covering +
                '}';
    }
}
