package edu.training.fourth.parse;

import edu.training.fourth.composite.TextComponent;
import edu.training.fourth.composite.TextComposite;
import edu.training.fourth.composite.TextLeaf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dell on 10.11.2016.
 */
public class ParagraphParser extends AbstractHandler{
    public static final String PARAGRAPH_REGEX = ".+\n";

    public ParagraphParser(){
    }

    @Override
    public TextComponent handleRequest(String s) {
        Pattern pattern = Pattern.compile(PARAGRAPH_REGEX);
        Matcher matcher = pattern.matcher(s);
        TextComponent text = new TextComposite();
        TextComponent emptyParagraph = new TextComposite();
        emptyParagraph.getTextComponents().add(new TextComposite());
        emptyParagraph.getTextComponents().get(0).getTextComponents().add(new TextLeaf("\n"));
        while (matcher.find()){
            TextComponent paragraph = successor.chain(matcher.group());
            text.add(paragraph);
            text.add(emptyParagraph);
        }
        return text;
    }
}
