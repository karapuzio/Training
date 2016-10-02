package edu.training.second.factory.client;

import edu.training.second.entity.Sweetness;

/**
 * Created by Dell on 25.09.2016.
 */
public abstract class AbstractSweetnessFactory {
    public abstract Sweetness createSweetness(String[] paramenters);
}
