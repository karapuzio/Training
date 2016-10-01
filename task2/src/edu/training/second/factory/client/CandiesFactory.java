package edu.training.second.factory.client;

import edu.training.second.entity.Candies;

/**
 * Created by Dell on 25.09.2016.
 */
public class CandiesFactory extends AbstractSweetnessFactory{
    @Override
    public Candies createSweetness() {
        return new Candies();
    }
}
