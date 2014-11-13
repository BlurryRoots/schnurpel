package de.hawhamburg.gka.common;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;

import de.hawhamburg.gka.common.*;

public
class Pathfinder {
	private
	Graph<String, CustomEdge> graph;
	private
	ISearchStrategy strategy;
	
	public
	Pathfinder (Graph<String, CustomEdge> graph, ISearchStrategy strategy) {
		this.graph = graph;
		this.strategy = strategy;
	}
	
	public
	void changeStrategy (ISearchStrategy strategy) {
		this.strategy = strategy;
	}
	
	public
	List<String> find (String source, String target) {
		boolean isInGraph =
			this.graph.containsVertex (source) &&
			this.graph.containsVertex (target);
		
		if (! isInGraph) {
			return new ArrayList<String> ();
		}
		
		return this.strategy.getPath (this.graph, source, target);
	}
}
