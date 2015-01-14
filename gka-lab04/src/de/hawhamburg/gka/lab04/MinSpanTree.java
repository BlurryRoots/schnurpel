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

public class MinSpanTree {
	
	private BreadthFirstStrategie bfs;
	
	//create the minimum spanning tree
	public 
	UndirectedGraph<String, CustomEdge> minSpanTree(UndirectedGraph<String, CustomEdge> graph){
	
		UndirectedGraph<String, CustomEdge> minSpanTree = new SimpleGraph<String, CustomEdge>(CustomEdge.class);
		
		Set<String> vertecies = graph.vertexSet();
		Iterator<String> it = vertecies.iterator();
		
		while(it.hasNext()){
			
			minSpanTree.addVertex(it.next());
		}
		
		ConnectivityInspector<String,CustomEdge> newGraph = new ConnectivityInspector<String, CustomEdge>(minSpanTree);

		Set<CustomEdge> edgesSet = new HashSet<CustomEdge>(graph.edgeSet()); 
		
		while(!(newGraph.isGraphConnected())){


			CustomEdge minEdge = getEdgeWithMinimumCost(graph, edgesSet);
			
			System.out.println(minSpanTree);
			
			if((!addingEdgeToGraphCreatesCycle(minSpanTree, minEdge))){
				minSpanTree.addEdge(graph.getEdgeSource(minEdge), graph.getEdgeTarget(minEdge), minEdge);
			}
			
			edgesSet.remove(minEdge);

		}
		
		return minSpanTree;
}	
	
	private 
	boolean addingEdgeToGraphCreatesCycle(
			UndirectedGraph<String, CustomEdge> graph,
			CustomEdge edge) {

		String source = graph.getEdgeSource(edge);
		String target = graph.getEdgeTarget(edge);
		
		List<String> emptyList = new ArrayList();
		
		//there is allready a path from edge source to edge target
		if((bfs.getPath(graph, source, target) != emptyList))
			return true;
				
		return false;

	}

	//return the edge with the lowest value out of a set of edges
	private 
	CustomEdge getEdgeWithMinimumCost(UndirectedGraph<String, CustomEdge> graph, Set<CustomEdge> edgesSet){
	
		int weight;
		int minWeight = Integer.MAX_VALUE;
		CustomEdge edge;
		CustomEdge minEdge = new CustomEdge();
		
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