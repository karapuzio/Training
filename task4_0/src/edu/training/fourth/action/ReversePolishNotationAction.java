package edu.training.fourth.action;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dell on 03.11.2016.
 */
public class ReversePolishNotationAction {

    public static Map<Character, Integer> setOperationPriorities(){
        Map<Character, Integer> priorityOperation = new HashMap<>();
        priorityOperation.put('(', 0);
        priorityOperation.put(')', 0);
        priorityOperation.put('+', 1);
        priorityOperation.put('-', 1);
        priorityOperation.put('#', 2);
        priorityOperation.put('$', 2);
        priorityOperation.put('*', 2);
        priorityOperation.put('/', 2);
        return priorityOperation;
    }

    public static String reversePolishNotation(String expression){
        Map<Character, Integer> priorityOperation = setOperationPriorities();
        ArrayDeque<Character> operation = new ArrayDeque<>();
        StringBuilder polishNotation = new StringBuilder();
        expression = expression.replace("++", "#");
        expression = expression.replace("--", "$");
        expression = expression.replace("(-", "(0-");
        for (int i = 0; i < expression.length(); i++){
            char ch = expression.charAt(i);
            if (Character.isDigit(ch)){
                polishNotation.append(ch);
                if (i < expression.length() && !Character.isDigit(expression.charAt(i+1))){
                    polishNotation.append(" ");
                }
            }
            switch (ch){
                case '(' :
                    operation.push(ch);
                    break;
                case ')' :
                    while (!operation.isEmpty() && operation.getFirst() != '('){
                        polishNotation.append(operation.pop() + " ");
                    }
                    if (!operation.isEmpty() && operation.getFirst() == '('){
                        operation.pop();
                    }
                    break;
                case '#' :
                    while (!operation.isEmpty() && priorityOperation.get('#') <= priorityOperation.get(operation.getFirst())){
                        polishNotation.append(operation.pop() + " ");
                    }
                    operation.push(ch);
                    break;
                case '$' :
                    while (!operation.isEmpty() && priorityOperation.get('$') <= priorityOperation.get(operation.getFirst())){
                        polishNotation.append(operation.pop() + " ");
                    }
                    operation.push(ch);
                    break;
                case '+' :
                    while (!operation.isEmpty() && priorityOperation.get('+') <= priorityOperation.get(operation.getFirst())){
                        polishNotation.append(operation.pop() + " ");
                    }
                    operation.push(ch);
                    break;
                case '-' :
                    while (!operation.isEmpty() && priorityOperation.get('-') <= priorityOperation.get(operation.getFirst())){
                        polishNotation.append(operation.pop() + " ");
                    }
                    operation.push(ch);
                    break;
                case '*' :
                    while (!operation.isEmpty() && priorityOperation.get('*') <= priorityOperation.get(operation.getFirst())){
                        polishNotation.append(operation.pop() + " ");
                    }
                    operation.push(ch);
                    break;
                case '/' :
                    while (!operation.isEmpty() && priorityOperation.get('/') <= priorityOperation.get(operation.getFirst())){
                        polishNotation.append(operation.pop() + " ");
                    }
                    operation.push(ch);
                    break;
            }
        }
        while (!operation.isEmpty()){
            polishNotation.append(operation.pop());
        }
        return polishNotation.toString();
    }
}
