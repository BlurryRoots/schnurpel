package de.hawhamburg.gka.lab03;

import java.util.List;

import org.jgrapht.Graph;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.Matrix;

public
class FordFulkersonFlowAnalyser
extends FlowAnalyser {

	public
	FordFulkersonFlowAnalyser (Graph<String, CustomEdge> graph) {
		super (graph);
	}

	@Override protected
	boolean hasPath (String source, String target,
		Matrix<Integer> residualGraph, List<String> vertecies, int[] parent) {
		
		// Mark all the vertices as not visited
		// Create a visited array and mark all vertices as not visited
		boolean visited[] = new boolean[vertecies.size ()];
		for (int i = 0; i < vertecies.size (); ++i) {
			visited[i] = false;
		}

	    // Call the recursive helper function to print DFS traversal
	    // starting from all vertices one by one
		for (String vertex : vertecies) {
			int index = vertecies.indexOf (vertex);
			if (! visited[index]) {
				this.expand (vertex, visited, parent, residualGraph, vertecies);
			}
		}
		
		return visited[vertecies.indexOf (target)];
	}

	private
	void expand (String vertex, boolean[] visited, int[] parent, Matrix<Integer> residualGraph, List<String> vertecies) {
		// Mark the current node as visited and print it
		int vertexIndex = vertecies.indexOf (vertex);
		visited[vertexIndex] = true;

	    // Recur for all the vertices adjacent to this vertex
		for (int u = 0; u < vertecies.size (); ++u) {
			int residual = residualGraph.retrieve (u, vertexIndex);
			boolean hasBeenVisited = visited[u];
			if (! hasBeenVisited && residual > 0) {
				parent[vertexIndex] = u;
				this.expand (vertecies.get (u), visited, parent, residualGraph, vertecies);
			}
		}

	}
}
