package edu.training.fourth.parse;

import edu.training.fourth.composite.TextComponent;

/**
 * Created by Dell on 10.11.2016.
 */
public class TextParser {
    public static TextComponent parse(String text){
        ParagraphParser paragraphParser = new ParagraphParser();
        SentenceParser sentenceParser = new SentenceParser();
        LeafParser leafParser = new LeafParser();
        paragraphParser.setSuccessor(sentenceParser);
        sentenceParser.setSuccessor(leafParser);
        return paragraphParser.chain(text);
    }
}
