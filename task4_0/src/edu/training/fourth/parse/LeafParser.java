package edu.training.fourth.parse;

import edu.training.fourth.composite.TextComponent;
import edu.training.fourth.composite.TextComposite;
import edu.training.fourth.composite.TextLeaf;
import edu.training.fourth.interpreter.MathExpressionClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dell on 10.11.2016.
 */
public class LeafParser extends AbstractHandler{
    public static final String LEAF_REGEX = "(\\w|\\+|-|\\+|\\*|/|\\(|\\)|'|,|:)+[. ]";
    public static final String ARIFM_REGEX = "(\\d|\\+|-|\\+|\\*|/|\\(|\\))+[ ]";

    public LeafParser() {
    }

    @Override
    public TextComponent handleRequest(String s) {
        Pattern pattern = Pattern.compile(LEAF_REGEX);
        Matcher matcher = pattern.matcher(s);
        TextComponent sentence = new TextComposite();
        while (matcher.find()){
            String leaf = matcher.group();
            Pattern arifmPattern = Pattern.compile(ARIFM_REGEX);
            Matcher arifmMatcher = arifmPattern.matcher(leaf);
            boolean isArifmetic = arifmMatcher.matches();
            if (isArifmetic) {
                leaf = new MathExpressionClient(leaf).calculate();
            }
            TextComponent textLeaf = new TextLeaf(leaf);
            sentence.add(textLeaf);
        }
        return sentence;
    }
}
