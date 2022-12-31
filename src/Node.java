// Name: Phuc Huu Lam
// NetID: plam6
// I do not collaborate with anyone else. 

import java.util.*;

public class Node {
    String ID;
    double lat,lon;
    ArrayList<Edge> edges;
    
    //Constructor
    public Node(String ID, double lat, double lon){
        this.ID = ID;
        this.lat = lat;
        this.lon = lon;
        edges = new ArrayList<>();
    }

    //Return intersection ID
    public String ID(){
        return ID;
    }

    //Return latitude
    public double Lat(){
        return lat;
    }

    //Return longitude
    public double Long(){
        return lon;
    }

    //Return list of distance to its neighbors
    public ArrayList<Edge> Edges() { return edges; }

    //Add edge to list
    public void addEdge(Edge edge) { edges.add(edge);}

}
