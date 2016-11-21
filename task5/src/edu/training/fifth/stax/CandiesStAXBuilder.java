package edu.training.fifth.stax;

import edu.training.fifth.entity.*;
import edu.training.fifth.sax.CandyEnum;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dell on 20.11.2016.
 */
public class CandiesStAXBuilder {
    private static final Logger LOGGER = LogManager.getLogger(CandiesStAXBuilder.class);
    private Set<Candy> candies = new HashSet<>();
    private XMLInputFactory inputFactory;

    public CandiesStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public Set<Candy> getCandies() {
        return candies;
    }

    public void buildSetCandies(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (CandyEnum.valueOf(name.toUpperCase()) == CandyEnum.CANDY) {
                        Candy candy = buildCandy(reader);
                        candies.add(candy);
                    }
                }
            }
        } catch (XMLStreamException e) {
            LOGGER.log(Level.ERROR, e, e);
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.ERROR, e, e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOGGER.log(Level.ERROR, e, e);
            }
        }
    }

    private Candy buildCandy(XMLStreamReader reader) throws XMLStreamException {
        Candy candy = new Candy();
        candy.setId(reader.getAttributeValue(null, CandyEnum.ID.getValue()));
        candy.setName(reader.getAttributeValue(null, CandyEnum.NAME.getValue())); // ???
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (CandyEnum.valueOf(name.toUpperCase())) {
                        case ENERGY:
                            name = getXMLText(reader);
                            candy.setEnergy(Integer.parseInt(name));
                            break;
                        case TYPE:
                            name = getXMLText(reader);
                            candy.setType(Type.valueOf(name.toUpperCase()));
                        case INGREDIENT_LIST:
                            candy.setIngredients(getXMLIngredients(reader));
                            break;
                        case VALUE:
                            candy.setValue(getXMLValue(reader));
                            break;
                        case PRODUCTION:
                            candy.setProduction(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (CandyEnum.valueOf(name.toUpperCase()) == CandyEnum.CANDY) {
                        return candy;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag candy.");
    }

    private Ingredients getXMLIngredients(XMLStreamReader reader) throws XMLStreamException{
        Ingredients ingredients = new Ingredients();
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type){
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    if (CandyEnum.valueOf(name.toUpperCase()) == CandyEnum.INGREDIENT) {
                        Ingredient ingredient = Ingredient.valueOf(getXMLText(reader));
                        ingredients.add(ingredient);
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (CandyEnum.valueOf(name.toUpperCase()) == CandyEnum.INGREDIENT_LIST) {
                        return ingredients;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag ingredient_list.");
    }

    private Value getXMLValue(XMLStreamReader reader) throws XMLStreamException{
        Value value = new Value();
        int type;
        String name;
        while (reader.hasNext()){
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (CandyEnum.valueOf(name.toUpperCase())) {
                        case PROTEINS:
                            name = getXMLText(reader);
                            value.setProteins(Integer.parseInt(name));
                            break;
                        case FATS:
                            name = getXMLText(reader);
                            value.setFats(Integer.parseInt(name));
                            break;
                        case CARBOHYDRATES:
                            name = getXMLText(reader);
                            value.setCarbohydrates(Integer.parseInt(name));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (CandyEnum.valueOf(name.toUpperCase()) == CandyEnum.VALUE) {
                        return value;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag value.");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException{
        String text = null;
        if (reader.hasNext()){
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}