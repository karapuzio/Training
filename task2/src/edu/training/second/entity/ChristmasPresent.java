package edu.training.second.entity;

import edu.training.second.factory.client.CandiesFactory;
import edu.training.second.factory.client.Client;
import edu.training.second.factory.client.CookiesFactory;
import edu.training.second.factory.client.ZephyrFactory;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Dell on 25.09.2016.
 */
public class ChristmasPresent {
    private Set<Sweetness>  present;

    public ChristmasPresent(){
        SweetnessComparator comparator = new SweetnessComparator();
        present = new TreeSet(comparator);
        Client client = new Client();
        CandiesFactory candiesFactory = new CandiesFactory();
        CookiesFactory cookiesFactory = new CookiesFactory();
        ZephyrFactory zephyrFactory = new ZephyrFactory();

        //TO DO

        String id = "";
        switch(id){
            case "candy" :
                Candies candy = (Candies)client.createSweetnes(candiesFactory);
                present.add(candy);
                break;
            case "cooke" :
                Cookies cooke = (Cookies)client.createSweetnes(cookiesFactory);
                present.add(cooke);
                break;
            case "zephyr" :
                Zephyr zephyr = (Zephyr)client.createSweetnes(zephyrFactory);
                present.add(zephyr);
                break;
            default :
                break;
        }
    }

    public Set<Sweetness> getPresent() {
        return present;
    }

    public void setPresent(Set<Sweetness> present) {
        this.present = present;
    }

    public int sizePresent(){
        return present.size();
    }
}
