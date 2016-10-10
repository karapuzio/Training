package edu.training.second.factory.client;

import edu.training.second.entity.Zephyr;
import edu.training.second.exception.ParameterException;
import edu.training.second.type.TypeCovering;
import edu.training.second.type.TypeTaste;

/**
 * Created by Dell on 25.09.2016.
 */
public class ZephyrFactory extends AbstractSweetnessFactory{
    @Override
    public Zephyr createSweetness(String... parameters) throws ParameterException {
        Zephyr zephyr;
        try {
            int weight = Integer.parseInt(parameters[weightParameter]);
            int sugary = Integer.parseInt(parameters[sugaryParameter]);
            int calorific = Integer.parseInt(parameters[coveringParameter]);
            TypeTaste typeTaste = TypeTaste.valueOf(parameters[tasteParameter].toUpperCase());
            TypeCovering typeCovering = TypeCovering.valueOf(parameters[coveringParameter].toUpperCase());
            zephyr = new Zephyr(weight, sugary, calorific, typeCovering, typeTaste);
        }
        catch (IndexOutOfBoundsException | IllegalArgumentException ex){
            throw new ParameterException("Not correct zephyr.");
        }
        return zephyr;
    }
}
