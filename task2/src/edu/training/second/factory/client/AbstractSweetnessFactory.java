package edu.training.second.factory.client;

import edu.training.second.entity.Sweetness;
import edu.training.second.exception.ParameterException;

/**
 * Created by Dell on 25.09.2016.
 */
public abstract class AbstractSweetnessFactory{
    public static final int weightParameter = 1;
    public static final int sugaryParameter = 2;
    public static final int calorificParameter = 3;
    public static final int doughParameter = 4;
    public static final int typeCandyParameter = 4;
    public static final int tasteParameter = 4;
    public static final int coveringParameter = 5;

    public abstract Sweetness createSweetness (String... paramenters) throws ParameterException;
}
