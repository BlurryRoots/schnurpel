package de.hawhamburg.gka.lab02;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.IPathfinder;

public class Dijkstra implements IPathfinder {

	// inspired by
	// http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html
	
	private int accessCount;

	@Override
	public GraphPath<String, CustomEdge> getPath (
			Graph<String, CustomEdge> graph, String source, String target) {
		
		
//		Foreach node set distance[node] = HIGH
		Map<String, Integer> distance = new HashMap<String, Integer> ();
//		SettledNodes = empty
		List<String> visited = new LinkedList<String> ();
//		UnSettledNodes = empty
		List<String> unvisited = new LinkedList<String> ();
		
		for (String nodeName : graph.vertexSet ()) {
			unvisited.add (nodeName);
		}
		for (String nodeName : unvisited) {
			distance.put (nodeName, Integer.MAX_VALUE);
		}
//
//		Add sourceNode to UnSettledNodes
		unvisited.add (source);
//		distance[sourceNode]= 0
		distance.put (source, 0);
//
//		while (UnSettledNodes is not empty) {
		while (! unvisited.isEmpty ()) {
//		  evaluationNode = getNodeWithLowestDistance(UnSettledNodes)
			String node = this.findNodeWithLowestDistance (distance).getKey ();

//			remove evaluationNode from UnSettledNodes 
//			 add evaluationNode to SettledNodes
			unvisited.remove (node);
			visited.add (node);
			
			if (visited.contains (node)) {
				continue;
			}
			
//			  Foreach destinationNode which can be reached via an edge from evaluationNode AND which is not in SettledNodes {
			for (CustomEdge edge : graph.edgesOf (node)) {
				String dest = edge.getTarget ();
//				edgeDistance = getDistance(edge(evaluationNode, destinationNode))
//			    newDistance = distance[evaluationNode] + edgeDistance
				int updatedCost = distance.get (node) + edge.getCost ();

//				if (distance[destinationNode]  > newDistance) {
				if (distance.get (node) > updatedCost) {
//				   distance[destinationNode]  = newDistance 
					distance.remove (node);
					distance.put (node, updatedCost);
//				   add destinationNode to UnSettledNodes
					unvisited.add (dest);
//				}
				}
			}
//		    
//		  }
//		}
		}

		System.out.println ("un " + unvisited);
		System.out.println ("vi " + visited);
		System.out.println ("di " + distance);
		
//		List<String> queue = new LinkedList<String> ();
//		Map<String, WeightedVertex> visitMap = new HashMap<String, WeightedVertex> ();
//
//		visitMap.put (source, new WeightedVertex (source, null, 0));
//		queue.add (source);
//
//		while (! queue.isEmpty ()) {
//			String current = queue.remove (0);
//			WeightedVertex node = visitMap.get (current);
//			Set<CustomEdge> edges = graph.edgesOf (current); 
//
//			for (CustomEdge e : edges) {
//				++this.accessCount;
//				
//				String to = e.getTarget ();
//				if (to.equals (current)) {
//					to = e.getSource ();
//				}
//
//				int updatedCost = node.cost + e.getCost ();
//				if (! visitMap.containsKey (to)) {
//					System.out.println ("Adding to map " + to);
//					visitMap.remove (to);
//					visitMap.put (to,
//							new WeightedVertex (to, node, updatedCost));
//					queue.add (to);
//				}
//				else {
//					WeightedVertex wv = visitMap.get (to);
//					if (wv.cost > updatedCost) {
//						visitMap.remove (to);
//						visitMap.put (to, new WeightedVertex (to, node,
//								updatedCost));
//					}
//
//				}
//			}
//		}
//
//		List<CustomEdge> edges = new LinkedList<CustomEdge> ();
//
//		if (!visitMap.containsKey (target)) {
//			System.out.println ("FUUUK");
//			return new GraphPathImpl<String, CustomEdge> (graph, source,
//					target, edges, 1);
//		}
//
//		List<String> path = new LinkedList<String> ();
//		WeightedVertex current = visitMap.get (target);
//		while (null != current) {
//			path.add (current.name);
//			current = visitMap.get (current.parent);
//		}
//		Collections.reverse (path);
//
//		for (int i = 0; i < path.size () - 1; ++i) {
//			++this.accessCount;
//			edges.add (graph.getEdge (path.get (i), path.get (i + 1)));
//		}

//		return new GraphPathImpl<String, CustomEdge> (graph, source, target,
//				edges, 1);
		return null;
	}
	
//	getNodeWithLowestDistance(UnSettledNodes){
//	  find the node with the lowest distance in UnSettledNodes and return it 
//	}
	private
	Entry<String, Integer> findNodeWithLowestDistance (Map<String, Integer> distance) {
		Entry<String, Integer> lowest = null;
		for (Entry<String, Integer> entry : distance.entrySet ()) {
			if (null == lowest) {
				lowest = entry;
			}
			else {
				if (entry.getValue () < lowest.getValue ()) {
					lowest = entry;
				}
			}
		}
		
		return lowest;
	}
}