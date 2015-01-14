package de.hawhamburg.gka.lab04;

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
		Graph<String, CustomEdge> tourGraph = 
			new SimpleGraph<String, CustomEdge> (CustomEdge.class);
		
		return tourGraph;
	}
}
