package edu.training.fourth.parse;

import edu.training.fourth.composite.TextComponent;
import edu.training.fourth.composite.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dell on 10.11.2016.
 */
public class SentenceParser extends AbstractHandler{
    public static final String SENTENCE_REGEX = "(\\w|\\+|-|,|\\++|\\*|/|\\(|\\)| |')+[.!?]";

    public SentenceParser(){
    }

    @Override
    public TextComponent handleRequest(String s) {
        Pattern pattern = Pattern.compile(SENTENCE_REGEX);
        Matcher matcher = pattern.matcher(s);
        TextComponent paragraph = new TextComposite();
        while (matcher.find()){
            TextComponent sentence = successor.chain(matcher.group());
            paragraph.add(sentence);
        }
        return paragraph;
    }
}
