package edu.training.fourth.parse;

import edu.training.fourth.composite.TextComponent;

/**
 * Created by Dell on 03.11.2016.
 */
public abstract class AbstractHandler {
    protected AbstractHandler successor;

    public AbstractHandler(AbstractHandler successor) {
        this.successor = successor;
    }

    public AbstractHandler() {
    }

    public void setSuccessor(AbstractHandler successor) {
        this.successor = successor;
    }

    abstract public TextComponent handleRequest(String s);

    public final TextComponent chain(String s) {
        return handleRequest(s);
    }
}
