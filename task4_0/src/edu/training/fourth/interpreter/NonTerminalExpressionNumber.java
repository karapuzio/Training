package edu.training.fourth.interpreter;

/**
 * Created by Dell on 10.11.2016.
 */
public class NonTerminalExpressionNumber extends AbstractMathExpression{
    private int number;

    public NonTerminalExpressionNumber(int number){
        this.number = number;
    }

    @Override
    public void interpret(Context context) {
        context.pushValue(number);
    }
}
