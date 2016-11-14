package edu.training.fourth.action;

import edu.training.fourth.composite.TextComponent;
import edu.training.fourth.composite.TextLeaf;

import java.util.ArrayList;

/**
 * Created by Dell on 14.11.2016.
 */
public class TextAction {

    public void printSentenceSortedByNumberOfLexeme(TextComponent text){
        ArrayList<TextComponent> sentenceList = new ArrayList<>();
        for (TextComponent paragraph : text.getTextComponents()){
            for (TextComponent sentence : paragraph.getTextComponents()){
                sentenceList.add(sentence);
            }
        }
        for (int i = 0; i < sentenceList.size(); i++){
            TextComponent tinySentence = sentenceList.get(i);
            for (int j = i+1; j < sentenceList.size(); j++){
                if (sentenceList.get(j).getTextComponents().size() < tinySentence.getTextComponents().size()){
                    sentenceList.set(i,sentenceList.get(j));
                    sentenceList.set(j, tinySentence);
                    tinySentence = sentenceList.get(i);
                }
            }
            TextLeaf leaf = (TextLeaf)tinySentence.getTextComponents().get(0);
            if (!"\n".equals(leaf.getTextLeaf())){
                System.out.println(tinySentence);
            }
        }
    }

    public void deleteLexeme(TextComponent text, int length, int ch){
        for (TextComponent paragraph : text.getTextComponents()){
            for (TextComponent sentence : paragraph.getTextComponents()){
                for (int i = 0; i < sentence.getTextComponents().size(); i++){
                    TextLeaf leaf = (TextLeaf) sentence.getTextComponents().get(i);
                    if (leaf.getTextLeaf().length() == length && leaf.getTextLeaf().charAt(0) == ch){
                        sentence.getTextComponents().remove(leaf);
                    }
                }
            }
        }
    }

    public void changeFirsAndLastLexeme(TextComponent text){
        for (TextComponent paragraph : text.getTextComponents()){
            for (TextComponent sentence : paragraph.getTextComponents()){
                TextComponent firstLexeme = sentence.getTextComponents().get(0);
                sentence.getTextComponents().set(0, sentence.getTextComponents().get(sentence.getTextComponents().size() - 1));
                sentence.getTextComponents().set(sentence.getTextComponents().size() - 1, firstLexeme);
            }
        }
    }
}
