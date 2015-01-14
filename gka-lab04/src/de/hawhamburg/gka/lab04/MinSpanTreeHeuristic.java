package de.hawhamburg.gka.lab04;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jgrapht.*;
import org.jgrapht.alg.ConnectivityInspector;

import de.hawhamburg.gka.common.CustomEdge;

public class MinSpanTreeHeuristic {
	
	private MinSpanTree mst;

	private FleurysAlgorithm fleurys;
	
	public UndirectedGraph<String, CustomEdge> minSpanTreeHeuristic(UndirectedGraph<String, CustomEdge> graph, String startingpoint){
		
		UndirectedGraph copyGraph = graph;
		
		UndirectedGraph<String, CustomEdge> minSpanTree = mst.minSpanTree(copyGraph);
		
		UndirectedGraph<String, CustomEdge> eulerscherGraph = eulerscherGraph(minSpanTree);
		
		List<String> eulerCircuit =	new ArrayList<String>(fleurys.fleurysAlgorithm(eulerscherGraph, startingpoint));
				
		UndirectedGraph resultGraph = null;
		
		//add all Vertexes to resultgraph
		Set vertexSet = graph.vertexSet();
		Iterator vertIt = vertexSet.iterator();
		while(vertIt.hasNext()){
			
			resultGraph.addVertex(vertIt.next());
		}

		Iterator it = eulerCircuit.iterator();
		
		while(it.hasNext()){
			
			String currentVertex = it.next().toString();
			
//			resultGraph.addEdge(graph.getEdgeSource(currentEdge), graph.getEdgeTarget(currentEdge), currentEdge);
			
		}
		
		
		
		return null;
	
	}
	
	
	//create eulscher graph
	public UndirectedGraph<String, CustomEdge> eulerscherGraph(UndirectedGraph<String, CustomEdge> graph){
		
		Set edges = new HashSet(); 
		edges.add(graph.edgeSet());
		
		CustomEdge edge;
		
		Iterator it = edges.iterator();
		
		while(it.hasNext()){
		
			edge = (CustomEdge) it.next();
			graph.addEdge(graph.getEdgeSource(edge), graph.getEdgeTarget(edge), edge);
		}
		
		return graph;
		
	}
	
	
	//testing
	public boolean testInspector(UndirectedGraph<String, CustomEdge> graph){
		
		ConnectivityInspector<String,CustomEdge> newGraph = new ConnectivityInspector<String, CustomEdge>(graph);
		
		return newGraph.isGraphConnected();
		
	}

}
