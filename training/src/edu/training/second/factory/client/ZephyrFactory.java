package edu.training.second.factory.client;

import edu.training.second.entity.Zephyr;

/**
 * Created by Dell on 25.09.2016.
 */
public class ZephyrFactory extends AbstractSweetnessFactory{
    @Override
    public Zephyr createSweetness() {
        return new Zephyr();
    }
}
