package de.hawhamburg.gka.lab04;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.Multigraph;
import org.jgrapht.graph.SimpleGraph;

import de.hawhamburg.gka.common.CustomEdge;

public class FleurysAlgorithm {
	
	public
	Graph<String, CustomEdge> fleurysAlgorithm (
		final UndirectedGraph<String, CustomEdge> graph, String startingpoint) {
		// create result graph
		Graph<String, CustomEdge> result = new Multigraph<> (CustomEdge.class);
		for (String v : graph.vertexSet ()) {
			result.addVertex (v);
		}
		
		List<String> route = new LinkedList<> ();
		
		// copy graph
		UndirectedGraph<String, CustomEdge> workingGraph =
			new Multigraph<String, CustomEdge> (CustomEdge.class);
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
				// no non bridge edges had been found, choose the next possible edge
				selectedEdge = workingGraph.edgesOf (currentVertex).iterator ().next ();
			}
			
			//route.add (currentVertex);
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
	
	public
	List<String> fleurysAlgorithm3(UndirectedGraph<String, CustomEdge> graph, String startingpoint){
		
		List<String> verticiesRoute = new ArrayList<String>();  		//returnwert liste von vertexes, wie sie nacheinander abgelaufen werden
		String nextVertex = startingpoint;
		boolean checkForBridges = true;									//nachdem eine kante gegangen ist, wird diese danach gel�sch, es m�ssen danach denoch alle kanten ablaufbar bleiben

		CustomEdge edge = null;
		  
		while(graph.vertexSet().size() > 1){							//der Algorithmus ist beendet, sobald alle Kanten abgelaufen sind, ich l�sche im Algorithmus auch die Knoten, die keine Kanten mehr haben
																		//wenn also nur noch ein Knoten vorhanden ist, der Anfangs- / Zielknote, dann kann die Schleife verlassen werden.
			
			LinkedList<CustomEdge> edgesList = new LinkedList<CustomEdge>(graph.edgesOf(nextVertex)); //hole alle dem Knoten zugeh�rigen Kanten  
			
			//Choose an edge which does not disconnect the graph after it is removed
			while (true) {

				edge = (CustomEdge) edgesList.getFirst();				//nehme eine der Kanten aus der Liste		
				
				// remove edge
				graph.removeEdge (edge);
				// and check if this disconnects the graph
				ConnectivityInspector<String,CustomEdge> inspector =
					new ConnectivityInspector<String, CustomEdge> (graph);
				if (inspector.isGraphConnected ()) {
					// if graph is still in one piece
					break;
				}
				else {
					graph.addEdge (edge.getSource (), edge.getTarget (), edge);
				}
			}
			
			if (null == edge) {
				throw new RuntimeException ("OH NOES");
			}
			
			//---------------------------------------------------------------------
			//System.out.println(edge);			
			verticiesRoute.add(graph.getEdgeSource(edge).toString()); 	//f�ge der Route den Anfangsknoten der Kante, die gelaufen werden soll, hinzu
	
			nextVertex = graph.getEdgeTarget(edge).toString(); 			//mache "nextVertex" zum n�chsten Knoten, der betrachtet werden soll, f�r den n�chsten durchlauf
		
			Set<CustomEdge> numOfEdges = new HashSet<CustomEdge>();
						
			graph.removeEdge(edge);										//entferne die genutze Kante
		
			//has source vertex still edges, if not remove it
			if((numOfEdges).size() == 0) 	//Falls der verlassene Knoten keine Kante mehr haben sollte, l�sche auch diesen
				graph.removeVertex(graph.getEdgeSource(edge));
		
		}
		
		verticiesRoute.add(nextVertex);									//der letzte verbleibende Knoten wird der Liste hinzugef�gt
		
		return verticiesRoute;
	}
	
	public
	List<String> fleurysAlgorithm2(UndirectedGraph<String, CustomEdge> graph, String startingpoint){
		
		List<String> verticiesRoute = new ArrayList<String>();  		//returnwert liste von vertexes, wie sie nacheinander abgelaufen werden
		String nextVertex = startingpoint;
		boolean checkForBridges = true;									//nachdem eine kante gegangen ist, wird diese danach gel�sch, es m�ssen danach denoch alle kanten ablaufbar bleiben

		CustomEdge edge = new CustomEdge();
		  
		while(graph.vertexSet().size() > 1){							//der Algorithmus ist beendet, sobald alle Kanten abgelaufen sind, ich l�sche im Algorithmus auch die Knoten, die keine Kanten mehr haben
																		//wenn also nur noch ein Knoten vorhanden ist, der Anfangs- / Zielknote, dann kann die Schleife verlassen werden.
			
			LinkedList<CustomEdge> edgesList = new LinkedList<CustomEdge>(graph.edgesOf(nextVertex)); //hole alle dem Knoten zugeh�rigen Kanten  
			
			//Choose an edge which does not disconnect the graph after it is removed
			while(checkForBridges){

				edge = (CustomEdge) edgesList.getFirst();				//nehme eine der Kanten aus der Liste		
				
				if(checkForBridges = edgeIsBridgeEdge(graph, edge)) 	//falls es eine Br�ckenkante ist, entferne die Kante aus der Liste der Kanten 
					edgesList.removeFirst();
			}
			//---------------------------------------------------------------------
			//System.out.println(edge);			
			verticiesRoute.add(graph.getEdgeSource(edge).toString()); 	//f�ge der Route den Anfangsknoten der Kante, die gelaufen werden soll, hinzu
	
			nextVertex = graph.getEdgeTarget(edge).toString(); 			//mache "nextVertex" zum n�chsten Knoten, der betrachtet werden soll, f�r den n�chsten durchlauf
		
			Set<CustomEdge> numOfEdges = new HashSet<CustomEdge>();
						
			graph.removeEdge(edge);										//entferne die genutze Kante
		
			//has source vertex still edges, if not remove it
			if((numOfEdges).size() == 0) 	//Falls der verlassene Knoten keine Kante mehr haben sollte, l�sche auch diesen
				graph.removeVertex(graph.getEdgeSource(edge));
		
		}
		
		verticiesRoute.add(nextVertex);									//der letzte verbleibende Knoten wird der Liste hinzugef�gt
		
		return verticiesRoute;
	}

	private boolean edgeIsBridgeEdge(UndirectedGraph<String, CustomEdge> graph, CustomEdge edge) {
		
		Graph<String, CustomEdge> graphCopy = graph;
		
		graphCopy.removeEdge(edge);
		
		ConnectivityInspector<String,CustomEdge> newGraph = new ConnectivityInspector<String, CustomEdge>((UndirectedGraph<String, CustomEdge>) graphCopy);
	
		return newGraph.isGraphConnected();
	}
}
