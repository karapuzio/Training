package edu.training.fourth.demo;

import edu.training.fourth.action.TextAction;
import edu.training.fourth.composite.TextComponent;
import edu.training.fourth.parse.TextParser;
import edu.training.fourth.reader.TextReader;

/**
 * Created by Dell on 03.11.2016.
 */
public class Demo {
    public static final String FILE_NAME = "files/input.txt";

    public static void main(String... args) {
        TextComponent text = TextParser.parse(TextReader.textReader(FILE_NAME));
        TextAction textAction = new TextAction();
        System.out.println(text);
        textAction.changeFirsAndLastLexeme(text);
        System.out.println(text);
        textAction.printSentenceSortedByNumberOfLexeme(text);
        textAction.deleteLexeme(text, 4, 'h');
        textAction.deleteLexeme(text, 5, 'w');
        System.out.println(text);
    }
}
