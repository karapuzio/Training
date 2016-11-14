package edu.training.fourth.interpreter;

import java.util.ArrayDeque;

/**
 * Created by Dell on 10.11.2016.
 */
public class Context {
    private ArrayDeque<Integer> contextValues = new ArrayDeque<>();
    public  Integer popValue() {
        return contextValues.pop();
    }
    public void pushValue(Integer value){
        contextValues.push(value);
    }
}
