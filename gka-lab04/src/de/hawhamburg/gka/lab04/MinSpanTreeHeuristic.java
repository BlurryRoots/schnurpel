package de.hawhamburg.gka.lab04;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jgrapht.*;

import de.hawhamburg.gka.common.CustomEdge;

public class MinSpanTreeHeuristic {
	
	private MinSpanTree mst;

	private FleurysAlgorithm fleurys;
	
	public UndirectedGraph<String, CustomEdge> minSpanTreeHeuristic(UndirectedGraph<String, CustomEdge> graph, String startingpoint){
		
		UndirectedGraph<String, CustomEdge> minSpanTree = mst.minSpanTree(graph);
		
		UndirectedGraph<String, CustomEdge> eulerscherGraph = eulerscherGraph(minSpanTree);
		
		List<String> eulerCircuit =	new ArrayList<String>(fleurys.fleurysAlgorithm(eulerscherGraph, startingpoint));
		
		/*
		eulerCircuit durchgehen, bis ein Vertex erreicht ist, der bereits aufgetaucht ist. 
		Dann vom Vertex davor und den Nachfolgenden, von der Dopplung ausgehend, eine direkte Kante einfügen.
		
		Mit welcher gewichtung? braucht man doch den ganzen graphen und nicht nur den minimum spanning tree?
		
		*/
		
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

}
