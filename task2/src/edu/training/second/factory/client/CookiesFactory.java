package edu.training.second.factory.client;

import edu.training.second.entity.Cookies;
import edu.training.second.type.TypeCovering;
import edu.training.second.type.TypeDough;

/**
 * Created by Dell on 25.09.2016.
 */
public class CookiesFactory extends AbstractSweetnessFactory{
    @Override
    public Cookies createSweetness(String[] paramenters) {
        try {
            int weight = Integer.parseInt(paramenters[1]);
            int sugary = Integer.parseInt(paramenters[2]);
            int calorific = Integer.parseInt(paramenters[3]);
            TypeDough typeDough = TypeDough.valueOf(paramenters[4].toUpperCase());
            TypeCovering typeCovering = TypeCovering.valueOf(paramenters[5].toUpperCase());
            return new Cookies(weight, sugary, calorific, typeDough, typeCovering);
        }
        catch (IndexOutOfBoundsException | IllegalArgumentException ex){
            return new Cookies();
        }
    }
}
