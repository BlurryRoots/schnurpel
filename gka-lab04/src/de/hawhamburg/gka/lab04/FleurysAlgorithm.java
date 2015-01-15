package de.hawhamburg.gka.lab04;

import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.SimpleGraph;

import de.hawhamburg.gka.common.CustomEdge;

public class FleurysAlgorithm {
	
	public
	Graph<String, CustomEdge> fleurysAlgorithm (
		final UndirectedGraph<String, CustomEdge> graph, String startingpoint) {
		// create result graph
		Graph<String, CustomEdge> result = new SimpleGraph<> (CustomEdge.class);
		for (String v : graph.vertexSet ()) {
			result.addVertex (v);
		}
		
		// copy graph
		UndirectedGraph<String, CustomEdge> workingGraph =
			new SimpleGraph<String, CustomEdge> (CustomEdge.class);
		for (String v : graph.vertexSet ()) {
			workingGraph.addVertex (v);
		}
		for (CustomEdge e : graph.edgeSet ()) {
			workingGraph.addEdge (e.getSource (), e.getTarget (), e);
		}
		
		List<CustomEdge> unmarkedEdges = new LinkedList<> (workingGraph.edgeSet ());
		String currentVertex = startingpoint;
		
		while (! unmarkedEdges.isEmpty ()) {
			CustomEdge selectedEdge = null;
			List<CustomEdge> adjacedEdges =
				new LinkedList<> (workingGraph.edgesOf (currentVertex));
			while (! adjacedEdges.isEmpty ()) {
				CustomEdge e = adjacedEdges.remove (0);
				if (! unmarkedEdges.contains (e)) {
					continue;
				}
				
				// remove edge
				workingGraph.removeEdge (e);
				// and check if this disconnects the graph
				ConnectivityInspector<String,CustomEdge> inspector =
					new ConnectivityInspector<String, CustomEdge> (workingGraph);
				if (inspector.isGraphConnected ()) {
					// if graph is still in one piece
					selectedEdge = e;
					unmarkedEdges.remove (selectedEdge);
					break;
				}
				else {
					workingGraph.addEdge (e.getSource (), e.getTarget (), e);
				}
			}
			
			if (null == selectedEdge) {
				//throw new RuntimeException ("NO NO NO");
				break;
			}
			
			
			if (currentVertex.equals (selectedEdge.getSource ())) {
				// mache "nextVertex" zum n�chsten Knoten, der betrachtet werden soll, f�r den n�chsten durchlauf
				currentVertex = selectedEdge.getTarget ();
				// f�ge der Route den Anfangsknoten der Kante, die gelaufen werden soll, hinzu
				result.addEdge (currentVertex, selectedEdge.getSource (), selectedEdge);
			}
			else {
				// mache "nextVertex" zum n�chsten Knoten, der betrachtet werden soll, f�r den n�chsten durchlauf
				currentVertex = selectedEdge.getSource ();
				// f�ge der Route den Anfangsknoten der Kante, die gelaufen werden soll, hinzu
				result.addEdge (currentVertex, selectedEdge.getTarget (), selectedEdge);
			}			
		}
		
		return result;
	}
}
