// Name: Phuc Huu Lam
// NetID: plam6
// I do not collaborate with anyone else. 

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class Draw extends JPanel {
    private Graph graph;
    private JFrame frame;
    public Draw (Graph graph){
        this.graph = graph;
        frame = new JFrame("Map");
        frame.setSize(1020, 750);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(this);
        frame.getContentPane().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Component c = (Component) e.getSource();
                repaint();
            }
        });
    }

    private double xCoord(double lat) {
        return ((lat - graph.minLat) * ((frame.getWidth()-20) / (graph.maxLat - graph.minLat)));
    }
    
    private double yCoord(double log) {
        return ((log - graph.minLong) * ((frame.getHeight()-50) / (graph.maxLong - graph.minLong)));
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.WHITE);
        g2d.setColor(Color.BLACK);

        HashMap<String,Boolean> visited = new HashMap<>();

        for (String key : graph.findNode.keySet()){
            visited.put(key,false);
        }
        for (String key : graph.findNode.keySet()){
            visited.put(key,true);
            Node node = graph.findNode.get(key);
            ArrayList<Edge> edges = node.Edges();
            if (edges != null) {
                for (int i = 0; i < edges.size(); i++) {
                    if (!visited.get(edges.get(i).inter2ID())){
                        if (StreetMap.shortestPath != null && StreetMap.shortestPath.contains(edges.get(i).inter2ID()) && StreetMap.shortestPath.contains(node.ID())){
                            g2d.setColor(Color.RED);
                            g2d.setStroke(new BasicStroke(3));
                            g2d.draw(new Line2D.Double(xCoord(node.Lat()), yCoord(node.Long()), 
                            		xCoord(graph.findNode.get(edges.get(i).inter2ID()).Lat()), yCoord(graph.findNode.get(edges.get(i).inter2ID()).Long())));
                            g2d.setColor(Color.BLACK);
                            g2d.setStroke(new BasicStroke(1));
                        }
                        g2d.draw(new Line2D.Double(xCoord(node.Lat()), yCoord(node.Long()), 
                        		xCoord(graph.findNode.get(edges.get(i).inter2ID()).Lat()), yCoord(graph.findNode.get(edges.get(i).inter2ID()).Long())));
                    }
                }
            }
        }
    }
}
