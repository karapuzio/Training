package edu.training.second.action;

import edu.training.second.comparator.SweetnessComparator;
import edu.training.second.entity.ChristmasPresent;
import edu.training.second.entity.Sweetness;
import edu.training.second.exception.ParameterException;

import java.util.Collections;

/**
 * Created by Dell on 25.09.2016.
 */
public class PresentAction {
    public int calculateWeight(ChristmasPresent christmasPresent){
        int weight = 0;
        for (Sweetness sweetness : christmasPresent.getPresent()){
            weight += sweetness.getWeightPerUnit();
        }
        return weight;
    }

    public Sweetness findCandy(ChristmasPresent christmasPresent, int lowerLevel, int topLevel) throws ParameterException {
        for (Sweetness sweetness : christmasPresent.getPresent()){
            if (sweetness.getSugary() >= lowerLevel && sweetness.getSugary() <= topLevel){
                return sweetness;
            }
        }
        throw new ParameterException("Do not find candy. Not correct bounds!");
    }

    public void sortBySugary(ChristmasPresent christmasPresent){
        SweetnessComparator comparator = new SweetnessComparator();
        Collections.sort(christmasPresent.getPresent(), comparator);
    }
}
