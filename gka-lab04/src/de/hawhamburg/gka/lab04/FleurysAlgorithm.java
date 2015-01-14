package de.hawhamburg.gka.lab04;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;

import de.hawhamburg.gka.common.CustomEdge;

public class FleurysAlgorithm {
	
	public List<String> fleurysAlgorithm(UndirectedGraph<String, CustomEdge> graph, String startingpoint){
		
		List<String> verticiesRoute = new ArrayList<String>();  		//returnwert liste von vertexes, wie sie nacheinander abgelaufen werden
		String nextVertex = startingpoint;
		boolean checkForBridges = true;									//nachdem eine kante gegangen ist, wird diese danach gelösch, es müssen danach denoch alle kanten ablaufbar bleiben

		CustomEdge edge = new CustomEdge();
		  
		while(graph.vertexSet().size() > 1){							//der Algorithmus ist beendet, sobald alle Kanten abgelaufen sind, ich lösche im Algorithmus auch die Knoten, die keine Kanten mehr haben
																		//wenn also nur noch ein Knoten vorhanden ist, der Anfangs- / Zielknote, dann kann die Schleife verlassen werden.
			
			LinkedList<CustomEdge> edgesList = new LinkedList<CustomEdge>(graph.edgesOf(nextVertex)); //hole alle dem Knoten zugehörigen Kanten  
			
			//Choose an edge which does not disconnect the graph after it is removed
			while(checkForBridges){

				edge = (CustomEdge) edgesList.getFirst();				//nehme eine der Kanten aus der Liste		
				
				if(checkForBridges = edgeIsBridgeEdge(graph, edge)) 	//falls es eine Brückenkante ist, entferne die Kante aus der Liste der Kanten 
					edgesList.removeFirst();
			}
			//---------------------------------------------------------------------
			//System.out.println(edge);			
			verticiesRoute.add(graph.getEdgeSource(edge).toString()); 	//füge der Route den Anfangsknoten der Kante, die gelaufen werden soll, hinzu
	
			nextVertex = graph.getEdgeTarget(edge).toString(); 			//mache "nextVertex" zum nächsten Knoten, der betrachtet werden soll, für den nächsten durchlauf
		
			Set<CustomEdge> numOfEdges = new HashSet<CustomEdge>();
						
			graph.removeEdge(edge);										//entferne die genutze Kante
		
			//has source vertex still edges, if not remove it
			if((numOfEdges).size() == 0) 	//Falls der verlassene Knoten keine Kante mehr haben sollte, lösche auch diesen
				graph.removeVertex(graph.getEdgeSource(edge));
		
		}
		
		verticiesRoute.add(nextVertex);									//der letzte verbleibende Knoten wird der Liste hinzugefügt
		
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
	
	private boolean edgeIsBridgeEdge(UndirectedGraph<String, CustomEdge> graph, CustomEdge edge) {
		
		Graph<String, CustomEdge> graphCopy = graph;
		
		graphCopy.removeEdge(edge);
		
		ConnectivityInspector<String,CustomEdge> newGraph = new ConnectivityInspector<String, CustomEdge>((UndirectedGraph<String, CustomEdge>) graphCopy);
	
		return newGraph.isGraphConnected();
	}
}
