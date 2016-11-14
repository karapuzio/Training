package edu.training.fourth.interpreter;

import edu.training.fourth.action.ReversePolishNotationAction;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Dell on 10.11.2016.
 */
public class MathExpressionClient {
    private ArrayList<AbstractMathExpression> listExpression;
    public static final String POLISH_REGEX = "\\p{Blank}+";

    public MathExpressionClient(String expression){
        listExpression = new ArrayList<>();
        parse(expression);
    }

    private void parse(String expression){
        expression = ReversePolishNotationAction.reversePolishNotation(expression);
        for (String lexeme : expression.split(POLISH_REGEX)){
            if (lexeme.isEmpty()) {
                continue;
            }
            char temp = lexeme.charAt(0);
            switch (temp){
                case '+' :
                    listExpression.add(new TerminalExpressionPlus());
                    break;
                case '-' :
                    listExpression.add(new TerminalExpressionMinus());
                    break;
                case '*' :
                    listExpression.add(new TerminalExpressionMultiply());
                    break;
                case '/' :
                    listExpression.add(new TerminalExpressionDivide());
                    break;
                case '#' :
                    listExpression.add(new TerminalExpressionPlusPlus());
                    break;
                case '$' :
                    listExpression.add(new TerminalExpressionMinusMinus());
                    break;
                default:
                    Scanner scan = new Scanner(lexeme);
                    if (scan.hasNextInt()){
                        listExpression.add(new NonTerminalExpressionNumber(scan.nextInt()));
                    }
            }
        }
    }

    public String calculate(){
        Context context = new Context();
        for (AbstractMathExpression terminal : listExpression){
            terminal.interpret(context);
        }
        return context.popValue() + " ";
    }
}
