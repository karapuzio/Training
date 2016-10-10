package edu.training.second.entity;

import edu.training.second.factory.client.SwetnessReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Created by Dell on 25.09.2016.
 */
public class ChristmasPresent {
    private static final Logger LOGGER = LogManager.getLogger(ChristmasPresent.class);
    private List<Sweetness>  present;

    public ChristmasPresent(String fileName){
        present = SwetnessReader.readSweetness(fileName);
    }

    public List<Sweetness> getPresent() {
        return present;
    }

    public void setPresent(List<Sweetness> present) {
        this.present = present;
    }

    public int sizePresent(){
        return present.size();
    }
}
