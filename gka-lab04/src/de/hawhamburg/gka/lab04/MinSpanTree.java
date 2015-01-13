package de.hawhamburg.gka.lab04;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.jgrapht.*;
import org.jgrapht.alg.ConnectivityInspector;

import de.hawhamburg.gka.common.CustomEdge;

public class MinSpanTree {

	//create the minimum spanning tree
	public 
	Set minspantree(UndirectedGraph<String, CustomEdge> graph){
		
		UndirectedGraph<String, CustomEdge> minSpanTreeGraph = null;
		

		ConnectivityInspector<String,CustomEdge> newGraph = new ConnectivityInspector<String, CustomEdge>(minSpanTreeGraph);
				
		while(!(newGraph.isGraphConnected())){
		Set edges = new HashSet(); 
		edges.add(graph.edgeSet());
		
		CustomEdge edge = getEdgeWithMinimumCost(graph, edges);
		
		edges.remove(edge);
		
		if((!addingEdgeToGraphCreatesCycle(graph, minSpanTreeGraph, edge)))
			minSpanTreeGraph.addVertex(graph.getEdgeSource(edge));
			minSpanTreeGraph.addVertex(graph.getEdgeTarget(edge));
			minSpanTreeGraph.addEdge(graph.getEdgeSource(edge), graph.getEdgeTarget(edge), edge);
	}
		
		Set treeEdges = new HashSet(); 
		treeEdges.add(minSpanTreeGraph.edgeSet());	
		
		return treeEdges;
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
	CustomEdge getEdgeWithMinimumCost(UndirectedGraph<String, CustomEdge> graph, Set edges){
	
		double weight;
		double minWeight = Double.MAX_VALUE;
		CustomEdge edge;
		CustomEdge minEdge = null;
		
		Iterator it = edges.iterator();
		
		while(it.hasNext()){
			
			edge = (CustomEdge) it.next();
			weight = graph.getEdgeWeight(edge);
			
			if(weight < minWeight){
				minEdge = edge;
				minWeight = weight;
			}
		}
		
		return minEdge;
	}
}