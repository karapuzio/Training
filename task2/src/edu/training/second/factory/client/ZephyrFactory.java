package edu.training.second.factory.client;

import edu.training.second.entity.Zephyr;
import edu.training.second.type.TypeCovering;
import edu.training.second.type.TypeTaste;

/**
 * Created by Dell on 25.09.2016.
 */
public class ZephyrFactory extends AbstractSweetnessFactory{
    @Override
    public Zephyr createSweetness(String[] paramenters) {
        try {
            int weight = Integer.parseInt(paramenters[1]);
            int sugary = Integer.parseInt(paramenters[2]);
            int calorific = Integer.parseInt(paramenters[3]);
            TypeCovering typeCovering = TypeCovering.valueOf(paramenters[4].toUpperCase());
            TypeTaste typeTaste = TypeTaste.valueOf(paramenters[5].toUpperCase());
            return new Zephyr(weight, sugary, calorific, typeCovering, typeTaste);
        }
        catch (IndexOutOfBoundsException | IllegalArgumentException ex){
            return new Zephyr();
        }
    }
}
