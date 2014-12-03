package de.hawhamburg.gka.lab02;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.GraphPathImpl;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.IPathfinder;

public class DijkstraFinal
implements IPathfinder {

	@Override public
	List<String> getPath (
			Graph<String, CustomEdge> graph, String source, String target) {
		
		int accessCount = 0;

		accessCount += 2;
		if (! graph.containsVertex (source) ||
			! graph.containsVertex (target)) {
			return null;
		}
		
		
		Set<String> vertexNames = graph.vertexSet ();
		int vertexCount = vertexNames.size ();
		Map<String, DijkstraNode> nodes = new HashMap<> (vertexCount);
		List<DijkstraNode> queue = new LinkedList<> ();
		
		// prefil node list
		for (String name : vertexNames) {
			DijkstraNode node = new DijkstraNode ();
			node.name = name;
			node.cost = Integer.MAX_VALUE;
			node.isVisited = false;
			node.parentName = null;
			
			nodes.put (name, node);
		}
		
		DijkstraNode sourceNode = nodes.get (source);
		sourceNode.cost = 0;
		sourceNode.parentName = source;
		queue.add (sourceNode);
		
		boolean running = true;
		while (running) {
			DijkstraNode currentNode = pollMinimum (queue);
			
			if (currentNode.name.equals (target)) {
				running = false;
			}
			else {
				Set<CustomEdge> edges = graph.edgesOf (currentNode.name);
				for (CustomEdge edge : edges) {
					++accessCount;
					DijkstraNode node;
					if (edge.getTarget ().equals (currentNode.name)) {
						node = nodes.get (edge.getSource ());
					}
					else {
						node = nodes.get (edge.getTarget ());
					}
					
					int costSum = currentNode.cost + edge.getCost ();
					
					if (costSum < node.cost) {
						node.cost = costSum;
						node.parentName = currentNode.name;
					}
					
					if (! node.isVisited) {
						queue.add (node);
					}
				}
				
				currentNode.isVisited = true;
			}
		}
		
		List<String> path = new LinkedList<> ();
		
		DijkstraNode targetNode = nodes.get (target);
		if (null == targetNode.parentName) {
			return path;
		}
		
		String runner = target;
		while (true) {
			path.add (runner);
			String parentName = nodes.get (runner).parentName;
			if (runner.equals (parentName)) {
				break;
			}
			
			runner = parentName;
		}
		Collections.reverse (path);
		
		
		System.out.println ("Dijkstra needed " + accessCount + " access calls.");
		
		return path;
	}
	
	private static
	DijkstraNode pollMinimum (List<DijkstraNode> queue) {
		int minIndex = 0;
		DijkstraNode min = queue.get (minIndex);
		int l = queue.size ();
		
		for (int i = 0; i < l; ++i) {
			DijkstraNode n = queue.get (i);
			if (n.cost < min.cost) {
				min = n;
				minIndex = i;
			}
		}
		
		return queue.remove (minIndex);
	}
	
	private static
	class DijkstraNode {
		public
		String name;
		
		public
		String parentName;
		
		public
		int cost;
		
		public
		boolean isCostMaxiumum;
		
		public
		boolean isVisited;
	}

}
