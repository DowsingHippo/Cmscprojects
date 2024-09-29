/*
In this project you will implement an arithmetic expression tree using bridges.base.BinTreeElement<E> as the node. 
(For more information, check out this tutorial - http://bridgesuncc.github.io/tutorials/BinTree.html) To do so, you will accomplish the following tasks:

Build a parse tree from a fully parenthesized mathematical expression.
The method accepts a mathematical expression as a string parameter and returns the root of the parse tree.
Each token in the mathematical expression is separated by a white-space character, for example, “( ( 7 + 3 ) * ( 5 – 2 ) )”
The header for this method must be:    public static bridges.base.BinTreeElement<String> buildParseTree(String expression)
Evaluate the expression stored in a parse tree.
This method evaluates a parse tree by recursively evaluating each subtree.
The header for this method must be:    public static double evaluate(bridges.base.BinTreeElement<String> tree)
Recover the original mathematical expression from a parse tree.
The method accepts the root of the parse tree parameter and returns a mathematical expression as a string.
The header for this method must be:    public static String getEquation(bridges.base.BinTreeElement<String> tree)
The first step in building a parse tree is to break up the expression string into a list of tokens. 
There are four different kinds of tokens to consider: left parentheses, right parentheses, operators, and operands. Consider the following:

When we read a left parenthesis, we are starting a new expression, and hence we should create a new tree or subtree to correspond to that expression. 
Whenever we read a right parenthesis, we have finished an expression or part of an expression. 
Operands are leaf nodes and children of their operators. 
Every operator is going to have both a left and a right child.
Using the information given above we can define four rules as follows:

If the current token is a '(', add a new node as the left child of the current node, and descend to the left child.
If the current token is in the operator list {'+', '-', '/', '*'}, set the root value of the current node to the operator represented by the current token.
Add a new node as the right child of the current node and descend to the right child.
If the current token is a number, set the root value of the current node to the number and return to the parent.
If the current token is a ')', go to the parent of the current node.
To evaluate a parse tree, we can write an algorithm that evaluates a parse tree by recursively evaluating each subtree. 

Begin the design for the recursive evaluation function by identifying the base case. A natural base case for recursive algorithms that operate on trees is to check for a leaf node. 
In a parse tree, the leaf nodes will always be operands. 
The recursive step that moves the function toward the base case is to call evaluate on both the left and the right children of the current node. 
To put the results of the two recursive calls together, apply the operator stored in the parent node to the results returned from evaluating both children. 
An inorder traversal is needed get the original equation back from the parse tree, remember to add opening and closing parenthesis to retain the meaning of the equation.
*/
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
