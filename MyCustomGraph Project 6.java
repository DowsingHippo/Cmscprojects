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
