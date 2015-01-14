package de.hawhamburg.gka.lab04;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;

import de.hawhamburg.gka.common.CustomEdge;

public class FleurysAlgorithm {
	
	public List<String> fleurysAlgorithm(UndirectedGraph<String, CustomEdge> graph, String startingpoint){
		
		List<String> verticiesRoute = new ArrayList<String>();
		String nextVertex = startingpoint;
		boolean checkForBridges = true;
		CustomEdge edge = null;
		  
		while(graph.vertexSet().size() > 1){	
			
			LinkedList edgesList = new LinkedList<CustomEdge>(graph.edgesOf(nextVertex));
			
			//Choose an edge which does not disconnect the graph after removed
			while(checkForBridges){
				
				edge = (CustomEdge) edgesList.getFirst();				
				
				if(checkForBridges = edgeIsBridgeEdge(graph, edge))
					edgesList.removeFirst();
				
			}

			
			verticiesRoute.add(graph.getEdgeSource(edge).toString());
	
			nextVertex = graph.getEdgeTarget(edge).toString();
			
			graph.removeEdge(edge);
		
			//has source vertex still edges, if not remove it
			if((graph.edgesOf(graph.getEdgeSource(edge))).size() == 0)
				graph.removeVertex(graph.getEdgeSource(edge));
		
		}
		
		verticiesRoute.add(nextVertex);
		
		return verticiesRoute;
	}
	
/*		
	private CustomEdge choseNextEdge(LinkedList<CustomEdge> edges) {
	
		CustomEdge edge;
		
		Iterator it = edges.iterator();
		
		while(it.hasNext()){
			
			return edge =  (CustomEdge) it.next();
			
		}
		
		return null;
	}
*/
	
	private boolean edgeIsBridgeEdge(UndirectedGraph graph, CustomEdge edge) {
		
		Graph graphCopy = graph;
		
		graphCopy.removeEdge(edge);
		
		ConnectivityInspector<String,CustomEdge> newGraph = new ConnectivityInspector<String, CustomEdge>((UndirectedGraph<String, CustomEdge>) graph);
		
		if(newGraph.isGraphConnected())
			return false;
		
		return true;
	}
}
