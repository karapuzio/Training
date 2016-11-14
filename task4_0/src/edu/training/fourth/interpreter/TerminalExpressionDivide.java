package edu.training.fourth.interpreter;

/**
 * Created by Dell on 10.11.2016.
 */
public class TerminalExpressionDivide extends AbstractMathExpression{

    @Override
    public void interpret(Context context) {
        int a = context.popValue();
        int b = context.popValue();
        context.pushValue(b / a);
    }
}
