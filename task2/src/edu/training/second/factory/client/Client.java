package edu.training.second.factory.client;

import edu.training.second.entity.Sweetness;
import org.apache.logging.log4j.Logger;

/**
 * Created by Dell on 25.09.2016.
 */
public class Client {
    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(Client.class);

    public Sweetness createSweetnes(AbstractSweetnessFactory factory, String[] paramenters){
        Sweetness sweetness = factory.createSweetness(paramenters);
        return sweetness;
    }
}
