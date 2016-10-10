package edu.training.second.factory.client;

import edu.training.second.entity.Cookie;
import edu.training.second.exception.ParameterException;
import edu.training.second.type.TypeCovering;
import edu.training.second.type.TypeDough;

/**
 * Created by Dell on 25.09.2016.
 */
public class CookiesFactory extends AbstractSweetnessFactory{
    @Override
    public Cookie createSweetness(String... parameters) throws ParameterException {
        Cookie cookie;
        try {
            int weight = Integer.parseInt(parameters[weightParameter]);
            int sugary = Integer.parseInt(parameters[sugaryParameter]);
            int calorific = Integer.parseInt(parameters[calorificParameter]);
            TypeDough typeDough = TypeDough.valueOf(parameters[doughParameter].toUpperCase());
            TypeCovering typeCovering = TypeCovering.valueOf(parameters[coveringParameter].toUpperCase());
            cookie = new Cookie(weight, sugary, calorific, typeDough, typeCovering);
        }
        catch (IndexOutOfBoundsException | IllegalArgumentException ex){
            throw new ParameterException("Not correct cookie.");
        }
        return cookie;
    }
}
