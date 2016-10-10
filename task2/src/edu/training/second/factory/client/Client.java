package edu.training.second.factory.client;

import edu.training.second.entity.Sweetness;
import edu.training.second.exception.ParameterException;

/**
 * Created by Dell on 25.09.2016.
 */
public class Client {
    public Sweetness createSweetness(AbstractSweetnessFactory factory, String... parameters) throws ParameterException {
        Sweetness sweetness = factory.createSweetness(parameters);
        return sweetness;
    }
}
