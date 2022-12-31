// Name: Phuc Huu Lam
// NetID: plam6
// I do not collaborate with anyone else. 

import java.io.*;
import java.util.*;

public class StreetMap {
    public static ArrayList<String> shortestPath;
    static Graph graph;
    static Draw dm;
    
    public static void main(String[] args) throws FileNotFoundException {
        String fileDir = args[0];
        Graph graph = new Graph(fileDir);
        boolean hasShow = false;
        boolean hasDirect = false;
        String start = "", end = "";
        for (int i = 0; i < args.length; i++){
            if (args[i].equals("--show"))
                hasShow = true;
            if (args[i].equals("--directions")){
                hasDirect = true;
                start = args[i+1];
                end = args[i+2];
            }
        }
        if (hasDirect){
            shortestPath = findShortestPath(graph, start, end);
        }
        if (hasShow) {
            dm = new Draw(graph);
        }
    }

    //Using Dijkstra algorithm
    public static ArrayList<String> findShortestPath(Graph graph, String start, String end){
        HashMap<Double,Integer> dict1 = new HashMap<>();
        HashMap<String,Integer> dict = new HashMap<>();
        String[] findValue = new String[graph.n()];
        double[] path = new double[graph.n()];
        boolean[] mark = new boolean[graph.n()];
        int[] prev = new int[graph.n()];
        ArrayList<String> shortestPath = new ArrayList<>();
        int j = 0;
        
        for (String key : graph.findNode.keySet()){
            dict.put(key, j);
            path[j] = Double.MAX_VALUE;
            findValue[j] = key;
            mark[j] = false;
            j++;
        }
        
        // Check if two input locations are correct
        if (!dict.containsKey(start) || !dict.containsKey(end)){
            System.out.println("Locations not found.");
            return null;
        }
        else {
            prev[dict.get(start)] = -1;
            path[dict.get(start)] = 0;
            dict1.put(0.0, dict.get(start));
            PriorityQueue<Double> minHeap = new PriorityQueue<>();
            minHeap.add(0.0);
            while (!mark[dict.get(end)]) {
                while (minHeap.size() > 0 && mark[dict1.get(minHeap.peek())]){
                    minHeap.remove();
                }
                
                //Check if the destination is reachable
                if (minHeap.size() == 0){
                    System.out.println("Destination unreachable from given starting point.");
                    return null;
                }
                
                int choose = dict1.get(minHeap.remove());
                mark[choose] = true;
                ArrayList<Edge> edges = graph.findNode.get(findValue[choose]).Edges();
                for (int i = 0; i < edges.size(); i++) {
                    String destination = edges.get(i).inter2ID();
                    if ((!mark[dict.get(destination)]) && ((path[choose] + edges.get(i).dist()) < path[dict.get(destination)])) {
                        path[dict.get(destination)] = path[choose] + edges.get(i).dist();
                        prev[dict.get(destination)] = choose;
                        minHeap.add(path[dict.get(destination)]);
                        dict1.put(path[dict.get(destination)],dict.get(destination));
                    }
                }
            }
            String s = end;
            while (prev[dict.get(s)] != -1) {
                shortestPath.add(0, s);
                s = findValue[prev[dict.get(s)]];
            }
            shortestPath.add(0, start);
            System.out.println("Total distance: " + path[dict.get(end)]);
            System.out.print("Shortest path from " + start + " to " + end + " : ");
            for (int i = 0; i < shortestPath.size(); i++) {
                System.out.print(shortestPath.get(i) + " ");
            }
            System.out.println();
            return shortestPath;
        }
    }
}