package edu.training.fourth.interpreter;

/**
 * Created by Dell on 14.11.2016.
 */
public class TerminalExpressionMinusMinus extends AbstractMathExpression{

    @Override
    public void interpret(Context context) {
        context.pushValue(context.popValue() - 1);
    }
}
