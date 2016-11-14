package edu.training.fourth.composite;

import java.util.ArrayList;

/**
 * Created by Dell on 03.11.2016.
 */
public class TextComposite implements TextComponent{
    private ArrayList<TextComponent> textComponents = new ArrayList<>();

    public void add(TextComponent textComponent){
        textComponents.add(textComponent);
    }

    public void remove(TextComponent textComponent){
        textComponents.remove(textComponent);
    }

    public Object getChild(int index){
        return textComponents.get(index);
    }

    public ArrayList<TextComponent> getTextComponents() {
        return textComponents;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (TextComponent component : textComponents){
            stringBuilder.append(component.toString());
        }
        return stringBuilder.toString();
    }
}
