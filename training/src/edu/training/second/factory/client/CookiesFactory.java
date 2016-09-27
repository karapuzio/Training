package edu.training.second.factory.client;

import edu.training.second.entity.Cookies;

/**
 * Created by Dell on 25.09.2016.
 */
public class CookiesFactory extends AbstractSweetnessFactory{
    @Override
    public Cookies createSweetness() {
        return new Cookies();
    }
}
