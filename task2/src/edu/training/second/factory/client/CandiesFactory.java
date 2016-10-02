package edu.training.second.factory.client;

import edu.training.second.entity.Candies;
import edu.training.second.type.TypeCandy;

/**
 * Created by Dell on 25.09.2016.
 */
public class CandiesFactory extends AbstractSweetnessFactory{
    @Override
    public Candies createSweetness(String[] paramenters) {
        try {
            int weight = Integer.parseInt(paramenters[1]);
            int sugary = Integer.parseInt(paramenters[2]);
            int calorific = Integer.parseInt(paramenters[3]);
            TypeCandy typeCandy = TypeCandy.valueOf(paramenters[4].toUpperCase());
            return new Candies(weight, sugary, calorific, typeCandy);
        }
        catch (IndexOutOfBoundsException | IllegalArgumentException ex){
            return new Candies();
        }
    }
}
