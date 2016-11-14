package edu.training.fourth.composite;

import java.util.ArrayList;

/**
 * Created by Dell on 03.11.2016.
 */
public interface TextComponent {
    void add(TextComponent textComponent);
    void remove(TextComponent textComponent);
    Object getChild(int index);
    public ArrayList<TextComponent> getTextComponents();
}
