package de.hawhamburg.gka.lab04;

import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;

import de.hawhamburg.gka.common.CustomEdge;

public
class NearestNeighbour {
	private
	Graph<String, CustomEdge> graph;
	
	public
	NearestNeighbour (Graph<String, CustomEdge> graph_) {
		this.graph = graph_;
	}
	
	public
	Graph<String, CustomEdge> getTour (String start) {
		List<String> vertices = new LinkedList<> (this.graph.vertexSet ());

		Graph<String, CustomEdge> tourGraph = 
			new SimpleGraph<String, CustomEdge> (CustomEdge.class);		
		for (String v : vertices) {
			tourGraph.addVertex (v);
		}
		
		String current = start;
		boolean done = false;
		while (! done) {
			vertices.remove (current);			
			CustomEdge minimum = this.getMinimumEdge (current, vertices);
			
			if (null == minimum) {
				done = true;
			}
			else {
				if (current.equals (minimum.getSource ())) {
					current = minimum.getTarget ();
					tourGraph.addEdge (minimum.getSource (), current, minimum);
				}
				else {
					current = minimum.getSource ();
					tourGraph.addEdge (minimum.getTarget (), current, minimum);
				}
			}
		}
		
		tourGraph.addEdge (current, start, this.graph.getEdge (current, start));
		
		return tourGraph;
	}
	
	private
	CustomEdge getMinimumEdge (String from, List<String> tos) {
		CustomEdge minimumEdge = null;		
		int min = Integer.MAX_VALUE;
		
		for (String to : tos) {
			CustomEdge edge = this.graph.getEdge (from, to);
			if (edge.getCost () < min) {
				min = edge.getCost ();
				minimumEdge	= edge;
			}
		}
		
		return minimumEdge;
	}
}
