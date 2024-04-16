package assignment1;

import java.util.*;

//Main class containing the method to find the shortest route
public class ShortestRouteFinder {

	// Method to find the shortest route given a list of edges, start node, and end node	
	public static List<String> findShortestRoute(List<Edge> edges, String start, String end) {
		
		// Map is used to store distances and previous nodes during execution in the format of key-value pair
		Map<String, Double> distances = new HashMap<>();
		Map<String, String> previous = new HashMap<>();
		
		// here we have created the object of set interface and it is used to keep track of visited nodes
		Set<String> visited = new HashSet<>();
		
		// the Priority key object stores only string type of data
		// Priority queue is used to select the node with the smallest distance
		// the comparator is used to determine the order of element present in the queue
		// comparingDouble is the method of Comparator class	
		PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));

		// Initialize distances for all nodes as infinity except the start node as 0
		for (Edge edge : edges) {
			distances.put(edge.from, Double.MAX_VALUE);
			distances.put(edge.to, Double.MAX_VALUE);
		}
		distances.put(start, 0.0);

		// Add start node to the priority queue
		queue.add(start);

		// Dijkstra's algorithm loop
		while (!queue.isEmpty()) {
			// Extract the node with the smallest distance
			// it retrieves and removes the first element from the queue
			// current now holds the value of the first element retrieved from the queue.
			String current = queue.poll();
			
			// Skip if already visited
			// This checks if the visited collection contains the element stored in the variable current
			if (visited.contains(current))
				continue;
			
			// Mark the node as visited
			visited.add(current);
			
			// end is used to Break if reached the end node
			if (current.equals(end))
				break;

			// Iterate over all edges connected to the current node
			for (Edge edge : edges) {
				// If the edge starts from the current node
				if (edge.from.equals(current)) {
					// Calculate the new distance to the neighbor node via the current edge
					double newDistance = distances.get(current) + edge.weight;
					// Update the distance if it's shorter than the previous distance
					if (newDistance < distances.get(edge.to)) {
						distances.put(edge.to, newDistance); // put is used to insert the value
						previous.put(edge.to, current);
						queue.add(edge.to);
					}
				}
			}
		}

		// Reconstruct the shortest route by backtracking from the end node to the start node
		List<String> shortestRoute = new ArrayList<>(); // list interface only stores string type of value
		String current = end;
		while (previous.containsKey(current)) {
			shortestRoute.add(0, current);
			current = previous.get(current);
		}
		shortestRoute.add(0, start);

		// Return the shortest route
		return shortestRoute;
	}

	// Main method to test the ShortestRouteFinder class
	public static void main(String[] args) {
		// Example input in JSON format
		List<Edge> edges = new ArrayList<>();
		edges.add(new Edge("A", "B", 1));
		edges.add(new Edge("B", "C", 3));
		edges.add(new Edge("B", "E", 3.5));
		edges.add(new Edge("C", "E", 4));
		edges.add(new Edge("C", "D", 2.5));
		edges.add(new Edge("D", "G", 2.5));
		edges.add(new Edge("G", "F", 3.5));
		edges.add(new Edge("E", "F", 2));
		edges.add(new Edge("F", "H", 2.5));
		edges.add(new Edge("H", "I", 1));

		// Find shortest route from node C to node F
		// findShortestRoute is a method with 3 args(edges,start node,end node)
		List<String> shortestRoute = findShortestRoute(edges, "C", "F");
		// Print the shortest route
		System.out.println("Shortest root is"+shortestRoute);		
	}
}
