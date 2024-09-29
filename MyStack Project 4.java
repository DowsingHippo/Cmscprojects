/*
Programming Project 4 – Custom Stack Implementation

In this project you will implement a linked node Stack using the Bridges SLelement<E>.

You will write a generic MyStack<E> class that implements the StackInterface<E> Download StackInterface<E>shown here:
package cmsc256;

public interface StackInterface<E> {

   /** Adds a new entry to the top of this stack.

       @param newEntry  an object to be added to the stack. 

   public void push(E newEntry);

 

   /** Removes and returns this stack's top entry.

       @return  the object at the top of the stack.

       @throws  java.util.EmptyStackException if the stack is empty. 

   public E pop();

 

   /** Retrieves this stack's top entry.

       @return  the object at the top of the stack.

       @throws  java.util.EmptyStackException if the stack is empty.  

   public E peek();

 

   /** Detects whether this stack is empty.

       @return  True if the stack is empty. 

   public boolean isEmpty();

 

   /** Removes all entries from this stack. 

   public void clear();

}

The class will use the BRIDGES SLelement<E> class to represent a node in the stack.
Delimiters called tags are used to format static html pages. These tags must be paired correctly but can be nested inside other tags. 
For example, an open paragraph tag, <p> must correspond to a close paragraph tag, </p>. In addition, pairs of tags must not intersect. 
Thus, an html web page can contain a sequence of tags along with nested tag, such as:
<html>
<body>

<h1>My First Heading</h1>
<p>My first paragraph.</p>

</body>
</html>

but not intersecting tags as shown here with the h1, p, and body tags.

<html>
<body>

<h1>My First Heading
<p></h1>My first paragraph.

</body></p>
</html>

For convenience, we will say that a balanced expression contains delimiters that are paired correctly or are balanced. To simplify the implementation, 
valid tags consist of a pair (no <br/> allowed here) The difference between the opening tag and the closing tag is that the closing tag has the additional character of ‘/’ immediately after the first angle bracket.

You are to write a static method in your MyStack class that uses your MyStack <E> to detect whether an html file is tag balanced. The method should have the following heading:

public static boolean isBalanced(File webpage) throws FileNotFoundException

Your MyStack  class must be written in a cmsc256 package and will be uploaded to Gradescope for grading.

Follow the Coding Style Guideline in Canvas and include a comment block that includes your name, the name of the project along with the file name and
a brief description of the purpose of the class at the top of the source code file that you submit.

Here are some sample html pages to use to test your program:

1goodeasypage.htmlDownload 1goodeasypage.html
2badeasypage.html Download 2badeasypage.html 
*/


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
