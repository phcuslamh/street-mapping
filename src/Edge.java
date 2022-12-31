// Name: Phuc Huu Lam
// NetID: plam6
// I do not collaborate with anyone else. 

public class Edge {
    public final static double RADIUS = 6378.0;
    double dist;
    String ID;
    String inter1ID;
    String inter2ID;
    
    //Constructor
    public Edge(String ID, String inter1ID, String inter2ID){
        this.ID = ID;
        this.inter1ID = inter1ID;
        this.inter2ID = inter2ID;
        double lat1 = Graph.findNode.get(inter1ID).Lat();
        double lat2 = Graph.findNode.get(inter2ID).Lat();
        double long1 = Graph.findNode.get(inter1ID).Long();
        double long2 = Graph.findNode.get(inter2ID).Long();
        dist = 2*RADIUS*Math.asin(Math.sqrt(Math.pow(Math.sin(0.5*(lat2 - lat1)),2.0) + Math.cos(lat1)*Math.cos(lat2)*Math.pow(Math.sin(0.5*(long2 - long1)),2.0)));
    }

    //Return intersections
    public String inter1ID() { return inter1ID;}
    public String inter2ID() { return inter2ID;}
    
    //Return edge ID
    public String RoadID() { return ID; }
    
    //Return distance
    public double dist() {
        return dist;
    }
}