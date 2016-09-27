package edu.training.second.factory.client;

import edu.training.second.entity.Sweetness;

/**
 * Created by Dell on 25.09.2016.
 */
public class Client {
    public Sweetness createSweetnes(AbstractSweetnessFactory factory){
        Sweetness sweetness = factory.createSweetness();
        return sweetness;
    }
}
