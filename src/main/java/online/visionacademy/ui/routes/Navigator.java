package online.visionacademy.ui.routes;

import javax.swing.*;
import java.util.Stack;

public class Navigator {

    private final Stack<JPanel> stack = new Stack<>();

    private static Navigator instance;

    private Navigator(){

    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }

    public void push(JPanel view){
        stack.push(view);
    }

    public JPanel top(){
        if (stack.isEmpty())
            return null;
        return stack.peek();
    }

    public JPanel pop(){
        if (stack.isEmpty())
            return null;
        return stack.pop();
    }

    public int size(){
        return stack.size();
    }

    public String[] getNavigationPath(){

        String [] elements = new String[size()];

        for (int i = 0; i < stack.size(); i++) {
            JPanel panel =  stack.get(i);
            elements[i] = panel.getName();
        }

        return elements;
    }

    public static Navigator getInstance(){

        if (instance == null)
            instance = new Navigator();
        return instance;
    }

}
