package de.hawhamburg.gka.common;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;

public
class Generator {
	protected
	Random random;
	
	public
	Generator (int seed) {
		this.random = new Random (seed);
	}
	
	public
	Graph<String, CustomEdge> generateComplete (int verticesCount, int maxWeight, int minWeight) {
		if (0 >= verticesCount) {
			throw new RuntimeException ("Negative number of vertecies!?");
		}
		if (0 >= maxWeight || 0 >= minWeight) {
			throw new RuntimeException ("Negative weights!?");
		}
		
		Graph<String, CustomEdge> graph = new SimpleGraph<String, CustomEdge>(
			CustomEdge.class
		);
		
		for (int i = 0; i < verticesCount; ++i) {
			String name = "v" + String.valueOf (i);
			graph.addVertex (name);
		}
		
		Set<String> vertecies = graph.vertexSet (); 
		for (String v : vertecies) {
			for (String o : vertecies) {
				if (! v.equals (o) 
					&&	! (graph.containsEdge (v, o)
						  || graph.containsEdge (o, v))) {
					// initialize new edge with the maximal possible weight
					graph.addEdge (v, o, new CustomEdge (maxWeight));
				}
			}
		}
		
		// create a list of unweighted edges (Lue)
		List<CustomEdge> unweightedEdges = new LinkedList<CustomEdge> (graph.edgeSet ());
		
		// do as long as (Lue) is not empty
		while (! unweightedEdges.isEmpty ()) {
			// select random edge (E) and its vertices (Va & Vb) and assign a random weight (W)
			int index = 
			// remove (E) from (Lue)
			// now select all vertices which are not connected to this edge and put them into a list (L)
			// for each vertex (V) in this list (L)
				// select edge (E1) from current (V) to (Va) and assign w(E1) a random weight rand(W) + 1
				// remove (E1) from (Lue)
				// select edge (E2) from current (V) to (Vb) and assign w(E2) equal to W - w(E1)
				// reomve (E2) from (Lue)
		}
		
		return graph;
	}
}
