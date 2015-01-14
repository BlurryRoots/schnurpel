package de.hawhamburg.gka.lab04;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.alg.ConnectivityInspector;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.ISearchStrategy;
import de.hawhamburg.gka.common.Pathfinder;
import de.hawhamburg.gka.lab01.algorithm.BreadthFirstStrategie;

public
class MinSpanTree {
	private
	Graph<String, CustomEdge> graph;

	public
	MinSpanTree (UndirectedGraph<String, CustomEdge> graph_) {
		this.graph = graph_;
	}
	
	//create the minimum spanning tree
	public 
	UndirectedGraph<String, CustomEdge> getMinimumSpanTree () {
		UndirectedGraph<String, CustomEdge> minSpanTree =
			new SimpleGraph<String, CustomEdge>(CustomEdge.class);
		
		Set<String> vertecies = graph.vertexSet();		
		for (String v : this.graph.vertexSet ()) {
			minSpanTree.addVertex (v);
		}
		
		ConnectivityInspector<String, CustomEdge> inspector =
			new ConnectivityInspector<String, CustomEdge> (minSpanTree);

		Set<CustomEdge> edgesSet = new HashSet<CustomEdge> (this.graph.edgeSet()); 
		
		while (! inspector.isGraphConnected() && ! edgesSet.isEmpty ()) {
			CustomEdge minEdge = getEdgeWithMinimumCost (graph, edgesSet);
			Pathfinder finder = new Pathfinder (minSpanTree, new BreadthFirstStrategie ());
			
			if (null == minEdge) {
				throw new RuntimeException ("NO NO NO!");
			}
			
			String s = minEdge.getSource ();
			String t = minEdge.getTarget ();
			List<String> path = finder.find (s, t);
			if (path.isEmpty ()) {
				// means there is no cycle yet
				minSpanTree.addEdge(s, t, minEdge);
			}
			
			edgesSet.remove (minEdge);
		}
		
		return minSpanTree;
}

	//return the edge with the lowest value out of a set of edges
	private 
	CustomEdge getEdgeWithMinimumCost(Graph<String, CustomEdge> graph, Set<CustomEdge> edgesSet){
	
		int weight;
		int minWeight = Integer.MAX_VALUE;
		CustomEdge edge;
		CustomEdge minEdge = null;
		
		Iterator<CustomEdge> it = edgesSet.iterator();
		
		while(it.hasNext()){

			edge =  it.next();
			weight = edge.getCost();
			
			if(weight < minWeight){
				minEdge = edge;
				minWeight = weight;
			}
		}
		
		return minEdge;
	}
}