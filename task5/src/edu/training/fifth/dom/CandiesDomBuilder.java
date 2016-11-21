package edu.training.fifth.dom;

import edu.training.fifth.entity.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dell on 20.11.2016.
 */
public class CandiesDomBuilder {
    private static final Logger LOGGER = LogManager.getLogger(CandiesDomBuilder.class);
    private Set<Candy> candies;
    private DocumentBuilder documentBuilder;

    public CandiesDomBuilder() {
        this.candies = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            documentBuilder = factory.newDocumentBuilder();
        }
        catch (ParserConfigurationException e){
            LOGGER.log(Level.ERROR, e, e);
        }
    }

    public Set<Candy> getCandies() {
        return candies;
    }

    public void buildSetCandies(String fileName){
        Document doc = null;
        try{
            doc = documentBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList candyList = root.getElementsByTagName("candy");
            for (int i = 0; i < candyList.getLength(); i++){
                Element candyElement = (Element)candyList.item(i);
                Candy candy = buildCandy(candyElement);
                candies.add(candy);
            }
        }
        catch (IOException e){
            LOGGER.log(Level.ERROR, e, e);
        }
        catch (SAXException e){
            LOGGER.log(Level.ERROR, e, e);
        }
    }

    private Candy buildCandy(Element candyElement){
        Candy candy = new Candy();
        candy.setId(candyElement.getAttribute("id"));
        if (candyElement.hasAttribute("name")) {
            candy.setName(candyElement.getAttribute("name"));
        }
        Integer energy = Integer.parseInt(getElementTextContent(candyElement, "energy"));
        candy.setEnergy(energy);
        Type type = Type.valueOf(getElementTextContent(candyElement, "type"));
        candy.setType(type);
        Ingredients ingredients = new Ingredients();
        Element ingredientsElement = (Element) candyElement.getElementsByTagName("ingredient_list").item(0);
        NodeList nodeIngredientList = ingredientsElement.getElementsByTagName("ingredient");
        for (int i = 0; i < nodeIngredientList.getLength(); i++){
            Node node = nodeIngredientList.item(i);
            Ingredient ingredient = Ingredient.valueOf(node.getTextContent());
            ingredients.getIngredients().add(ingredient);
        }
        candy.setIngredients(ingredients);
        Value value = new Value();
        Element valueElement = (Element) candyElement.getElementsByTagName("value").item(0);
        Integer proteins = Integer.parseInt(getElementTextContent(valueElement, "proteins"));
        value.setProteins(proteins);
        Integer fats = Integer.parseInt(getElementTextContent(valueElement, "fats"));
        value.setFats(fats);
        Integer carbohydrates = Integer.parseInt(getElementTextContent(valueElement, "carbohydrates"));
        value.setCarbohydrates(carbohydrates);
        candy.setValue(value);
        String production = getElementTextContent(candyElement, "production");
        candy.setProduction(production);
        return candy;
    }

    private static String getElementTextContent(Element element, String elementName){
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        String text = node.getTextContent();
        return text;
    }
}
