package edu.training.second.factory.client;

import edu.training.second.entity.Candy;
import edu.training.second.exception.ParameterException;
import edu.training.second.type.TypeCandy;

/**
 * Created by Dell on 25.09.2016.
 */
public class CandiesFactory extends AbstractSweetnessFactory{
    @Override
    public Candy createSweetness(String... parameters) throws ParameterException {
        Candy candy;
        try {
            int weight = Integer.parseInt(parameters[weightParameter]);
            int sugary = Integer.parseInt(parameters[sugaryParameter]);
            int calorific = Integer.parseInt(parameters[calorificParameter]);
            TypeCandy typeCandy = TypeCandy.valueOf(parameters[typeCandyParameter].toUpperCase());
            candy = new Candy(weight, sugary, calorific, typeCandy);
        }
        catch (IndexOutOfBoundsException | IllegalArgumentException ex){
            throw new ParameterException("Not correct candy.");
        }
        return candy;
    }
}
