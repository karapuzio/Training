package edu.training.fourth.interpreter;

/**
 * Created by Dell on 10.11.2016.
 */
public class TerminalExpressionMinus extends AbstractMathExpression{

    @Override
    public void interpret(Context context) {
        context.pushValue(-context.popValue() + context.popValue());
    }
}
