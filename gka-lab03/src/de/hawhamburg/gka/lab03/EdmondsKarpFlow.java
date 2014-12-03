package de.hawhamburg.gka.lab03;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.jgrapht.Graph;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.Matrix;

public
class EdmondsKarpFlow {
	
	private
	Graph<String, CustomEdge> graph;
	private
	Matrix<Integer> residualGraph;
	private
	List<String> vertecies;
	private
	int parent[];
	private
	int maxFlow;
	
	public
	EdmondsKarpFlow (Graph<String, CustomEdge> graph) {
		this.graph = graph;
	}
	
	private
	void init () {
		this.vertecies = new ArrayList<> (graph.vertexSet ());
	    // Create a residual graph and fill it 0.
		this.residualGraph = new Matrix<Integer> (vertecies.size (), 0);
		// Fill matrix with values from original graph.
		for (CustomEdge edge : graph.edgeSet ()) {
			int row = vertecies.indexOf (edge.getSource ());
			int column = vertecies.indexOf (edge.getTarget ());
			this.residualGraph.insert (row, column, edge.getCost ());
		}

	    // This is used to keep track of the parents a vertex can have.
		this.parent = new int[vertecies.size ()];

	    // Initialze the flow to zero.
		this.maxFlow = 0;	
	}
	

	// Returns true if there is a path from source to sink in
	// residual graph. Also fills parent[] to
	private
	boolean hasPath (String source, String target) {
	    // Create a visited array and mark all vertices as not visited
		boolean visited[] = new boolean[this.vertecies.size ()];
		for (int i = 0; i < this.vertecies.size (); ++i) {
			visited[i] = false;
		}

	    // Create a queue, enqueue source vertex and mark source vertex
	    // as visited
		Queue<Integer> queue = new ConcurrentLinkedQueue<> ();
		int sourceIndex = this.vertecies.indexOf (source); 
		int targetIndex = this.vertecies.indexOf (target);
		int vertexCount = this.vertecies.size ();

		queue.offer (sourceIndex);

		visited[sourceIndex] = true;

		this.parent[sourceIndex] = -1;

	    // Do a breadth first search
		while (! queue.isEmpty ()) {
			// dequeue element
			int u = queue.poll ();

			for (int v = 0; v < vertexCount; ++v) {
				if (! visited[v] && this.residualGraph.retrieve (u, v) > 0) {
					queue.offer (v);

					this.parent[v] = u;
					visited[v] = true;
				}
			}
		}

	    // if we can reach the sink from given source
		// return true, else false
		return visited[targetIndex];
	}

	// Returns tne maximum flow from s to t in the given graph
	public
	int maxFlow (String source, String target) {
		this.init ();
		
		int sourceIndex = this.vertecies.indexOf (source);
		int targetIndex = this.vertecies.indexOf (target);
		
	    // Augment the flow while tere is path from source to sink
		while (this.hasPath (source, target)) {
	        // Find minimum residual capacity of the edhes along the
	        // path filled by BFS. Or we can say find the maximum flow
	        // through the path found.
			int pathFlow = Integer.MAX_VALUE;
			int u, v;
			
			for (v = targetIndex; v != sourceIndex; v=this.parent[v]) {
				u = parent[v];
				pathFlow = Math.min (pathFlow, this.residualGraph.retrieve (u, v));
			}

	        // update residual capacities of the edges and reverse edges
	        // along the path
			for (v = targetIndex; v != sourceIndex; v = parent[v]) {
				// store parent in u
				u = parent[v];
				// reverse paths
				this.residualGraph.insert (u, v, this.residualGraph.retrieve (u, v) - pathFlow);
				this.residualGraph.insert (v, u, this.residualGraph.retrieve (v, u) + pathFlow);
			}
			
	        // Add path flow to overall flow
			this.maxFlow += pathFlow;
		}

	    // Return the overall flow
		return this.maxFlow;
	}
}
