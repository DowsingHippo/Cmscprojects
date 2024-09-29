/*
Logan Miller
CMSC 256
4/18/2022
Project 4
 */
package cmsc256;

import bridges.base.SLelement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.EmptyStackException;
import java.util.Scanner;

public class MyStack<E> implements StackInterface<E> {
    private static MyStack<String> MyStack = new MyStack<String>();
    SLelement<E> top;
    int size;
    public MyStack(){
        top = null;
        size = 0;
    }
    @Override
    public void push(E newEntry) {
        if(newEntry == null)
            throw new IllegalArgumentException();
        SLelement<E> element = new SLelement<E>(newEntry, top);
        top = element;
        size++;
    }

    @Override
    public E pop() {
        E value = null;
        if(isEmpty()){
            throw new EmptyStackException();
        }
        else{
            value = top.getValue();
            SLelement<E> temp = top;
            top = top.getNext();
            temp.setNext(null);
            size--;
        }
        return value;
    }

    @Override
    public E peek() {
        E value = null;
        if(isEmpty()){
            throw new EmptyStackException();
        }
        else
            value = top.getValue();
        return value;
    }

    @Override
    public boolean isEmpty() {
        boolean flag = top == null;
        return flag;
    }

    @Override
    public void clear() {
    top = null;
    size = 0;
    }
    public int getSize(){
        return size;
    }
    public static boolean isBalanced(File webpage) throws FileNotFoundException {
        Scanner scan = new Scanner(webpage);
        boolean flag = false;
        MyStack = new MyStack();
        String webString = "";
        while(scan.hasNext()){
            webString += scan.nextLine();
        }
        webString.replaceAll("\\s", "");
        webString.replaceAll("\\t", "");
        for(int i = 0; i < webString.length(); i++){
            if(webString.charAt(i) == '<'){
                int j = i;
                String word = "";
                while(j < webString.length()&&!(webString.charAt(j) == '>')){
                    j++;
                    if(webString.charAt(j) == '<')
                        return false;
                }
                word = webString.substring(i, j+1);
                MyStack.push(word);
                System.out.println(word);
            }
            if(!MyStack.isEmpty() && MyStack.peek().substring(0,2).equals("</")){
                String temp = MyStack.pop();
                if(!MyStack.isEmpty()){
                String temp2 = MyStack.pop();
                if(temp.substring(2).equals(temp2.substring(1)))
                    flag = true;
                else
                    flag = false;
            }
                else
                    flag = false;
        }}
        scan.close();
        if(!MyStack.isEmpty())
            return false;
        return flag;
    }
}
