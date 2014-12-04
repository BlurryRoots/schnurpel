package de.hawhamburg.gka.lab03;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.jgrapht.Graph;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.Matrix;

public
class EdmondKarpFlowAnalyser
extends FlowAnalyser {
	
	public
	EdmondKarpFlowAnalyser (Graph<String, CustomEdge> graph) {
		super (graph);
	}
	
	@Override protected
	boolean hasPath (String source, String target, Matrix<Integer> residualGraph, List<String> vertecies, int parent[]) {
	    // Create a visited array and mark all vertices as not visited
		boolean visited[] = new boolean[vertecies.size ()];
		for (int i = 0; i < vertecies.size (); ++i) {
			visited[i] = false;
		}

	    // Create a queue, enqueue source vertex and mark source vertex
	    // as visited
		Queue<Integer> queue = new ConcurrentLinkedQueue<> ();
		int sourceIndex = vertecies.indexOf (source); 
		int targetIndex = vertecies.indexOf (target);
		int vertexCount = vertecies.size ();

		queue.offer (sourceIndex);

		visited[sourceIndex] = true;

		parent[sourceIndex] = -1;

	    // Do a breadth first search
		while (! queue.isEmpty ()) {
			// dequeue element
			int u = queue.poll ();

			for (int v = 0; v < vertexCount; ++v) {
				if (! visited[v] && residualGraph.retrieve (u, v) > 0) {
					queue.offer (v);

					parent[v] = u;
					visited[v] = true;
				}
			}
		}

	    // if we can reach the sink from given source
		// return true, else false
		return visited[targetIndex];
	}

}
