package assignment1;
import java.util.*;

// Class representing an edge in the graph
public class Edge {
	    String from;  // Starting node of the edge
	    String to;    // Ending node of the edge
	    double weight;  // Weight or cost of the edge

	    // Constructor to initialize the edge with its properties
	    public Edge(String from, String to, double weight) {
	        this.from = from;
	        this.to = to;
	        this.weight = weight;
	    }
	}

