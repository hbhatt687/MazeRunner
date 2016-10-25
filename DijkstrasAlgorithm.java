//Skeleton for the algorithm used to find the shortest path in a maze.
//Requires Graph and weight(array) to to be implemented
//psuedocode from Data Abstraction and Problem Solving with Java 3rd edition
//private MyGraph Graph;
//private int weight;
public class DijkstrasAlgorithm {
	
	public static void main(String[] args) {
	//	shortestPath(Graph, weight);
	}
	
	//heavy pseudocode
	public static void shortestPath(in theGraph: Graph, in weight:WeightArray) {
		// Finds the minimum-cost paths between an origin vertex
		// (vertex 0) and all other vertices in a weighted directed
		// graph theGraph. The array weight contains theGraph's
		// weights which are nonnegative.
		
		// Step 1: initialization
		Create a set vertexSet that contains only vertex 0
		n = number of vertices in theGraph;
		for (v = 0 through n - 1) {
			weight[v] = matrix[0][v]
		} // end for
		// Steps 2 through n
		// Invarient: For v not in vertexSet, weight[v] is the
		// smallest weight of all the paths from 0 to v that pass
		// For v in vertexSet, weight[v] is the smallest weight
		// of all paths from 0 to v (including paths outside
		// vertexSet), and the shortest path from 0 to v lies 
		// entirely in vertexSet.
		for (setp = 2 through n) {
			Find the smallest weight[v] such that v is not in
				vertexSet
			Add v to vertexSet
			
			// Check weight[u] for all u not in vertexSet
			for (all vertices u not in vertexSet) {
				if (weight[u] > weight[v] + matrix[v][u]) {
					weight[u] = weight[v] + matrix[v][u]
				} // end if
			} // end for
		} // end for
}
