package cmsc256;

import bridges.base.BinTreeElement;

import java.util.ArrayList;
import java.util.Stack;

public class Project5 {
    public static bridges.base.BinTreeElement<String> buildParseTree(String expression){
        Stack<BinTreeElement<String>> bTStack = new Stack<bridges.base.BinTreeElement<String>>();
        bridges.base.BinTreeElement<String> current = new bridges.base.BinTreeElement<String>();
         String[] expression1 = expression.split("\\s");
        for(int i = 0; i < expression1.length; i++){
            if(expression1[i].equals("(")){
                current.setLeft(new bridges.base.BinTreeElement<String>());
                bTStack.push(current);
                current = current.getLeft();
            }
            else if(expression1[i].equals("+") || expression1[i].equals("-") || expression1[i].equals("*") || expression1[i].equals("/")){
                current.setValue(expression1[i]);
                current.setRight(new bridges.base.BinTreeElement<String>());
                bTStack.push(current);
                current = current.getRight();
            }
            else if(expression1[i].equals(")")){
                if(!bTStack.isEmpty())
                    current = bTStack.pop();
            }
            else if(isNumeric(expression1[i])){
                current.setValue(expression1[i]);
                current = bTStack.pop();
            }
            else
                throw new IllegalArgumentException("Expression cannot be parsed. Current token is " + expression1[i]);
        }
        return current;
    }
    public static double evaluate(bridges.base.BinTreeElement<String> tree){
        if(tree == null)
            return Double.NaN;
        if(tree.getValue().equals("+")){
            return evaluate(tree.getLeft()) + evaluate(tree.getRight());
        }
        else if(tree.getValue().equals("-")){
            return evaluate(tree.getLeft()) - evaluate(tree.getRight());
        }
        else if(tree.getValue().equals("*")){
            return evaluate(tree.getLeft()) * evaluate(tree.getRight());
        }
        else if(tree.getValue().equals("/")){
            if(Double.parseDouble(tree.getRight().getValue()) == 0){
                throw new ArithmeticException("Cannot divide by 0");
            }
            return evaluate(tree.getLeft()) / evaluate(tree.getRight());
        }
        else
            return Double.parseDouble(tree.getValue());
    }
    public static String getEquation(bridges.base.BinTreeElement<String> tree){
        String equation = "";
        ArrayList<String> list = new ArrayList<String>();
        inorder(tree, list);
        for(String s : list){
        equation += s;
        }
        return equation.trim();

    }
    public static void inorder(bridges.base.BinTreeElement<String> tree, ArrayList<String> list) {
        if (tree != null) {
            if(tree.getValue().equals("+") || tree.getValue().equals("-") || tree.getValue().equals("*") || tree.getValue().equals("/"))
                    list.add("( ");
            inorder(tree.getLeft(), list);
                list.add(tree.getValue() + " ");
            inorder(tree.getRight(), list);
            if(tree.getValue().equals("+") || tree.getValue().equals("-") || tree.getValue().equals("*") || tree.getValue().equals("/"))
                list.add(") ");
        }
    }
    public static boolean isNumeric(String string) {
        double intValue;
        if(string == null || string.equals("")) {
            System.out.println("String is null or empty.");
            return false;
        }
        try {
            intValue = Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
