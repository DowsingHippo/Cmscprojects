/*
In this project, you’ll begin with starter code for a Graph interface, an Edge class, and a class that represents an unweighted graph, UnweightedGraph.java.  
You may clone the starter code from this GitHub repository, https://github.com/DebMDuke/Project6-Spring2022.gitLinks to an external site.

You will extend the UnweightedGraph class by implementing a class called MyCustomGraph. You must override all of the constructors in the UnweightedGraph class and implement the following methods:

Write a method that reads in graph data from a file and sets this MyCustomGraph instance according to the data in the file. The method signature must be public MyCustomGraph<V> readFile(String fileName)
Your method will accept a file name including the file extension, for example, txt, as a String parameter to create and return a MyCustomGraph object.
If the file is unable to be opened, the method should throw a FileNotFoundException
The first line in the file contains a number that indicates the number of vertices (n).
The vertices are labeled as 0, 1, …, n−1.
Each subsequent line, with the format u v1 v2 …, describes edges (u, v1), (u, v2), and so on.
Here are examples of two files for their corresponding graphs:

Sample Input Files.png

Add the method public boolean isComplete() that returns true if this graph is complete
Add the method public boolean areAdjacent(V v1, V v2) that returns true if v1 is adjacent to v2
Add a method called isConnected that returns true if this graph is connected and false otherwise. The header for this method is public boolean isConnected().  
Hint: Call the printEdges() to display all edges, and then call dfs() to obtain an instance tree of UnweightedGraph.SearchTree.
If tree.getNumberOfVerticesFound() is the same as the number of vertices in the graph, the graph is connected.
Add a method called getShortestPath for finding the shortest path between two vertices using a breadth-first search with the following header:  public List<V> getShortestPath(V begin, V end)
The method returns a List<V> that contains all the vertices in the shortest path from u to v in order.
Using the breadth-first search approach, you can obtain the shortest path from u to v.
If there isn’t a path from u to v, the method returns null.
Add a method called hasCycle() for determining whether there is a cycle in the graph with the following header:  public boolean hasCycle()
The method returns true if there is a cycle in this instance of MyCustomGraph.
Your file must be written in package – cmsc256 .Upload the MyCustomGraph<V> class to Gradescope for grading. Do not upload any other files.

Here are some sample input files for the two sample graphs: test2-1.txt Download test2-1.txtand test3.txt Download test3.txt
In addition to the two sample graphs provided for you, you'll need to test your class with additional sample input. You may test with JUnit tests or by using code in a main method.
*/
package cmsc256;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyCustomGraph<V> extends UnweightedGraph<V>{
    public MyCustomGraph(){
        super();
    }

    public MyCustomGraph(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    public MyCustomGraph(List<Edge> edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    public MyCustomGraph(List<V> vertices, List<Edge> edges) {
        super(vertices, edges);
    }

    public MyCustomGraph(V[] vertices, int[][] edges) {
        super(vertices, edges);
    }
    public MyCustomGraph<V> readFile(String fileName) throws FileNotFoundException {
        List<Edge> edges = new ArrayList<Edge>();
        int numVerts;
        try{
            File in = new File(fileName);
            Scanner scan = new Scanner(in);
            numVerts = scan.nextInt();
            while(scan.hasNext()){
                String[] temp = scan.nextLine().split(" ");
                    for(int i = 0; i < temp.length-1; i++){
                        edges.add(new Edge(Integer.parseInt(temp[0]), Integer.parseInt(temp[i+1])));
                    }
                }
        }
        catch(FileNotFoundException e){
            throw new FileNotFoundException();
        }
        MyCustomGraph<V> graph = new MyCustomGraph<V>(edges, numVerts);
        return graph;
    }
    public boolean isComplete(){
        printEdges();
        int edge = 0;
        for (int u = 0; u < neighbors.size(); u++) {
            for (Edge e: neighbors.get(u)) {
                edge++;
            }
        }
        if(edge == (vertices.size() *(vertices.size()-1))){
            return true;
        }
        return false;
    }
    public boolean areAdjacent(V v1, V v2){
        boolean flag = false;
        List<Integer> temp = getNeighbors(getIndex(v1));
        for(Integer i : temp){
            if(i == getIndex(v2)){
                flag = true;
            }
        }
        return flag;
    }
    public boolean isConnected(){
        printEdges();
        boolean flag = false;
        SearchTree temp = dfs(0);
        if(vertices.size() == 1)
            return false;
        if(temp.getNumberOfVerticesFound() == vertices.size()){
            flag = true;
        }
        return flag;
    }
    public List<V> getShortestPath(V begin, V end){
        List<V> path = new ArrayList<V>();
        if(begin == end){
            path.add(begin);
            return path;
        }
        SearchTree temp = bfs(getIndex(end));
        path = temp.getPath(getIndex(begin));

        if(path.size() == 1)
            return null;
        return path;
    }
    public boolean hasCycle(){
        if(vertices.size() == 1 || vertices.size() == 0)
            return false;
        if(neighbors.get(0).get(0).getDestinationVertex() == neighbors.get(1).get(0).getOriginVertex() && neighbors.get(1).get(0).getDestinationVertex() == neighbors.get(0).get(0).getOriginVertex())
            return true;
        printEdges();
        boolean flag = false;
        SearchTree temp = dfs(0);
        List<Integer> searchOrder = temp.getSearchOrder();
        for(int i = 0; i < searchOrder.size(); i++){
            int e = searchOrder.get(i);
            for(int j = i; j < searchOrder.size(); j++){
                if(e == searchOrder.get(j))
                    flag = true;
            }
        }
        return flag;
    }

}
