package edu.training.fourth.composite;

import java.util.ArrayList;

/**
 * Created by Dell on 03.11.2016.
 */
public class TextLeaf implements TextComponent{
    private String textLeaf;

    public TextLeaf(String textLeaf) {
        this.textLeaf = textLeaf;
    }

    public void add(TextComponent textComponent){
    }

    public void remove(TextComponent textComponent){
    }

    public Object getChild(int index){
        throw new UnsupportedOperationException();
    }

    public String getTextLeaf() {
        return textLeaf;
    }

    public void setTextLeaf(String textLeaf) {
        this.textLeaf = textLeaf;
    }

    @Override
    public ArrayList<TextComponent> getTextComponents(){
        ArrayList<TextComponent> leaf = new ArrayList<>();
        leaf.add(this);
        return leaf;
    }

    @Override
    public String toString() {
        return textLeaf;
    }
}
