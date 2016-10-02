package edu.training.second.comparator;

import edu.training.second.entity.Sweetness;

import java.util.Comparator;

/**
 * Created by Dell on 25.09.2016.
 */
public class SweetnessComparator implements Comparator<Sweetness>{
    @Override
    public int compare(Sweetness sweetness1, Sweetness sweetness2) {
        return sweetness1.getSugary()-sweetness2.getSugary();
    }
}
