package edu.training.fifth.sax;

import edu.training.fifth.entity.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dell on 20.11.2016.
 */
public class CandyHandler extends DefaultHandler {
    private static final Logger LOGGER = LogManager.getLogger(CandyHandler.class);
    private Set<Candy> candies;
    private Candy current = null;
    private CandyEnum currentEnum = null;
    private EnumSet<CandyEnum> withText;

    public CandyHandler(){
        candies = new HashSet<>();
        withText = EnumSet.range(CandyEnum.ENERGY, CandyEnum.PRODUCTION);
    }

    public Set<Candy> getCandies() {
        return candies;
    }

    public void setCandies(Set<Candy> candies) {
        this.candies = candies;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("candy".equals(localName)){
            current= new Candy();
            current.setId(attributes.getValue(0));
            if (attributes.getLength() == 2){
                current.setName(attributes.getValue(1));
            }
        }
        else{
            CandyEnum temp = CandyEnum.valueOf(localName.toUpperCase());
            if (withText.contains(temp)){
                currentEnum = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("candy".equals(localName)){
            candies.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null){
            switch (currentEnum) {
                case ENERGY:
                    current.setEnergy(Integer.parseInt(s));
                    break;
                case TYPE:
                    current.setType(Type.valueOf(s.toUpperCase()));
                    break;
                case INGREDIENT:
                    current.getIngredients().add(Ingredient.valueOf(s.toUpperCase()));
                    break;
                case PROTEINS:
                    current.getValue().setProteins(Integer.parseInt(s));
                    break;
                case FATS:
                    current.getValue().setFats(Integer.parseInt(s));
                    break;
                case CARBOHYDRATES:
                    current.getValue().setCarbohydrates(Integer.parseInt(s));
                    break;
                case PRODUCTION:
                    current.setProduction(s);
                    break;
                default:
                    LOGGER.log(Level.ERROR, "Enum constant not present.");
            }
        }
        currentEnum = null;
    }
}
