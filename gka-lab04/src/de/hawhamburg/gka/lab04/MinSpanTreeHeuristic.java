package de.hawhamburg.gka.lab04;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jgrapht.*;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.SimpleGraph;

import de.hawhamburg.gka.common.CustomEdge;

public class MinSpanTreeHeuristic {
	
	private MinSpanTree mst;

	private FleurysAlgorithm fleurys;
	
	public UndirectedGraph<String, CustomEdge> minSpanTreeHeuristic(UndirectedGraph<String, CustomEdge> graph, String startingpoint){
		
		UndirectedGraph<String, CustomEdge> copyGraph = graph;
		
		UndirectedGraph<String, CustomEdge> minSpanTree = mst.getMinimumSpanTree ();
		
		UndirectedGraph<String, CustomEdge> eulerscherGraph = createEulerGraph(minSpanTree);
		
		List<String> eulerCircuit = new ArrayList<String>(fleurys.fleurysAlgorithm(eulerscherGraph, startingpoint).vertexSet ());
				
		UndirectedGraph resultGraph = new SimpleGraph<String, CustomEdge>(CustomEdge.class);
		
		//add all Vertexes to resultgraph
		Set vertexSet = graph.vertexSet();
		Iterator vertIt = vertexSet.iterator();
		while(vertIt.hasNext()){
			
			resultGraph.addVertex(vertIt.next());
		}

		Iterator it = eulerCircuit.iterator();
		
		String currentVertex = it.next().toString();
		String nextVertex = null;
		
		
		while(it.hasNext()){
			
			nextVertex = it.next().toString();
			
			while(resultGraph.edgesOf(nextVertex).size() == 2)
				nextVertex = it.next().toString();
						
			CustomEdge edge = graph.getEdge(currentVertex, nextVertex);
			
			resultGraph.addEdge(currentVertex, nextVertex, edge);
			
			currentVertex = nextVertex;
			
		}
		
		return resultGraph;
	
	}
	
	
	//create eulscher graph
	public static
	UndirectedGraph<String, CustomEdge> createEulerGraph (UndirectedGraph<String, CustomEdge> graph){
		UndirectedGraph<String, CustomEdge> eulerGraph =
			new SimpleGraph<String, CustomEdge> (CustomEdge.class);
		for (String v : graph.vertexSet ()) {
			eulerGraph.addVertex (v);
		}
		
		for (CustomEdge edge : graph.edgeSet ()) {
			eulerGraph.addEdge(edge.getSource (), edge.getTarget (), edge);
			eulerGraph.addEdge(edge.getSource (), edge.getTarget (), edge);
		}
		
		return eulerGraph;
	}
	
	
	//testing
	public boolean testInspector(UndirectedGraph<String, CustomEdge> graph){
		
		ConnectivityInspector<String,CustomEdge> newGraph = new ConnectivityInspector<String, CustomEdge>(graph);
		
		return newGraph.isGraphConnected();
		
	}

}
