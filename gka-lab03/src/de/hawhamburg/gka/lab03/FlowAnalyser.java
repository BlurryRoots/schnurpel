package de.hawhamburg.gka.lab03;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.Matrix;

public abstract
class FlowAnalyser {
	
	private
	Graph<String, CustomEdge> graph;
	
	public
	FlowAnalyser (Graph<String, CustomEdge> graph) {
		this.graph = graph;
	}
	
	private
	Matrix<Integer> initResidualGraph (List<String> vertecies, Set<CustomEdge> edges) {
	    // Create a residual graph and fill it 0.
		Matrix<Integer> residualGraph = new Matrix<Integer> (vertecies.size (), 0);
		// Fill matrix with values from original graph.
		for (CustomEdge edge : graph.edgeSet ()) {
			int row = vertecies.indexOf (edge.getSource ());
			int column = vertecies.indexOf (edge.getTarget ());
			residualGraph.insert (row, column, edge.getCost ());
		}
		
		return residualGraph;
	}
	

	// Returns true if there is a path from source to sink in
	// residual graph. Also fills parent[] to
	protected abstract
	boolean hasPath (String source, String target, Matrix<Integer> residualGraph, List<String> vertecies, int parent[]);
	
	// Returns tne maximum flow from s to t in the given graph
	public
	int maxFlow (String source, String target) {
		List<String> vertecies = new ArrayList<> (graph.vertexSet ());
	    // This is used to keep track of the parents a vertex can have.
		int parent[] = new int[vertecies.size ()];
		Matrix<Integer> residualGraph = this.initResidualGraph (vertecies, this.graph.edgeSet ());
		
		int sourceIndex = vertecies.indexOf (source);
		int targetIndex = vertecies.indexOf (target);
		
		int maxFlow = 0;
		
	    // Augment the flow while tere is path from source to sink
		while (this.hasPath (source, target, residualGraph, vertecies, parent)) {
	        // Find minimum residual capacity of the edhes along the
	        // path filled by BFS. Or we can say find the maximum flow
	        // through the path found.
			int pathFlow = Integer.MAX_VALUE;
			int u, v;
			
			for (v = targetIndex; v != sourceIndex; v = parent[v]) {
				u = parent[v];
				pathFlow = Math.min (pathFlow, residualGraph.retrieve (u, v));
			}

	        // update residual capacities of the edges and reverse edges
	        // along the path
			for (v = targetIndex; v != sourceIndex; v = parent[v]) {
				// store parent in u
				u = parent[v];
				// reverse paths
				residualGraph.insert (u, v, residualGraph.retrieve (u, v) - pathFlow);
				residualGraph.insert (v, u, residualGraph.retrieve (v, u) + pathFlow);
			}
			
	        // Add path flow to overall flow
			maxFlow += pathFlow;
		}

	    // Return the overall flow
		return maxFlow;
	}
}
