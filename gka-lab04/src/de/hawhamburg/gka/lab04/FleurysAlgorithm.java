package de.hawhamburg.gka.lab04;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;

import de.hawhamburg.gka.common.CustomEdge;

public class FleurysAlgorithm {
	
	public List<String> fleurysAlgorithm(UndirectedGraph<String, CustomEdge> graph, String startingpoint){
		
		List<String> verticiesRoute = new ArrayList<String>();
		
		Set edges = new HashSet(graph.edgesOf(startingpoint));
		
		CustomEdge edge = choseNextEdge(edges);
		String nextVertex;
		
		while(graph.vertexSet().size() != 1){
		
		while(edgeIsNoBridgeEdge(graph, edge)){
			
			edges.remove(edge);
			edge = choseNextEdge(edges);			
		}
			
		verticiesRoute.add(graph.getEdgeSource(edge).toString());

		nextVertex = graph.getEdgeTarget(edge).toString();
		
		graph.removeEdge(edge);
		
		if((graph.edgesOf(graph.getEdgeSource(edge))).size() == 0)
			graph.removeVertex(graph.getEdgeSource(edge));
		
		}
		
		return verticiesRoute;
	}
		
	private CustomEdge choseNextEdge(Set edges) {
	
		CustomEdge edge;
		
		Iterator it = edges.iterator();
		
		while(it.hasNext()){
			
			return edge =  (CustomEdge) it.next();
			
		}
		
		return null;
	}

	private boolean edgeIsNoBridgeEdge(UndirectedGraph graph, CustomEdge edge) {
		
		graph.removeEdge(edge);
		
		ConnectivityInspector<String,CustomEdge> newGraph = new ConnectivityInspector<String, CustomEdge>((UndirectedGraph<String, CustomEdge>) graph);
		
		if(newGraph.isGraphConnected())
			return true;
		
		return false;
	}
}
