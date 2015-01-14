package de.hawhamburg.gka.lab04;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.alg.ConnectivityInspector;

import de.hawhamburg.gka.common.CustomEdge;

public class MinSpanTree {

	//create the minimum spanning tree
	public 
	UndirectedGraph<String, CustomEdge> minSpanTree(UndirectedGraph<String, CustomEdge> graph){
	
		UndirectedGraph<String, CustomEdge> minSpanTreeGraph = new SimpleGraph<String, CustomEdge>(CustomEdge.class);
		
		ConnectivityInspector<String,CustomEdge> newGraph = new ConnectivityInspector<String, CustomEdge>(minSpanTreeGraph);
				
		while(!(newGraph.isGraphConnected())){
		Set<CustomEdge> edgesSet = new HashSet<CustomEdge>(graph.edgeSet()); 
			
			CustomEdge edge = getEdgeWithMinimumCost(graph, edgesSet);
			
			edgesSet.remove(edge);
					
			if((!addingEdgeToGraphCreatesCycle(graph, minSpanTreeGraph, edge)))
				minSpanTreeGraph.addVertex(graph.getEdgeSource(edge));
				minSpanTreeGraph.addVertex(graph.getEdgeTarget(edge));
				minSpanTreeGraph.addEdge(graph.getEdgeSource(edge), graph.getEdgeTarget(edge), edge);
		}
	
		return minSpanTreeGraph;
}	
	
	private 
	boolean addingEdgeToGraphCreatesCycle(
			UndirectedGraph<String, CustomEdge> graph,
			UndirectedGraph<String, CustomEdge> minSpanTreeGraph,
			CustomEdge edge) {

		String source = graph.getEdgeSource(edge);
		String target = graph.getEdgeTarget(edge);

		if(minSpanTreeGraph.containsVertex(source) && minSpanTreeGraph.containsVertex(target))
			return true;
				
		return false;
	}

	//return the edge with the lowest value out of a set of edges
	private 
	CustomEdge getEdgeWithMinimumCost(UndirectedGraph<String, CustomEdge> graph, Set<CustomEdge> edges){
	
		double weight;
		double minWeight = Double.MAX_VALUE;
		CustomEdge edge;
		CustomEdge minEdge = null;
		
		Iterator<CustomEdge> it = edges.iterator();
		
		//CustomEdge[] test = edges.toArray(new CustomEdge[edges.size()]);
		
		while(it.hasNext()){

			edge =  it.next();
			weight = graph.getEdgeWeight(edge);
			
			if(weight < minWeight){
				minEdge = edge;
				minWeight = weight;
			}
		}
		
		return minEdge;
	}
}