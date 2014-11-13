package de.hawhamburg.gka.lab02;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.GraphPathImpl;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.IPathfinder;
import de.hawhamburg.gka.common.WeightedVertex;

public
class DijkstraAlternative
implements IPathfinder {
	
	private int accessCount;

	@Override
	public GraphPath<String, CustomEdge> getPath (
			Graph<String, CustomEdge> graph, String source, String target) {
		
		List<String> unvisited = new LinkedList<String> ();
		Map<String, WeightedVertex> visitMap = new HashMap<String, WeightedVertex> ();

		visitMap.put (source, new WeightedVertex (source, null, 0));
		unvisited.add (source);

		while (! unvisited.isEmpty ()) {
			String current = unvisited.remove (0);
			WeightedVertex node = visitMap.get (current);
			Set<CustomEdge> edges = graph.edgesOf (current); 

			for (CustomEdge e : edges) {
				++this.accessCount;
				
				String to = e.getTarget ();
				if (to.equals (current)) {
					to = e.getSource ();
				}

				int updatedCost = node.cost + e.getCost ();
				if (! visitMap.containsKey (to)) {
					System.out.println ("Adding to map " + to);
					visitMap.remove (to);
					visitMap.put (to,
							new WeightedVertex (to, node, updatedCost));
					unvisited.add (to);
				}
				else {
					WeightedVertex wv = visitMap.get (to);
					if (wv.cost > updatedCost) {
						visitMap.remove (to);
						visitMap.put (to, new WeightedVertex (to, node,
								updatedCost));
					}

				}
			}
		}

		List<CustomEdge> edges = new LinkedList<CustomEdge> ();

		if (! visitMap.containsKey (target)) {
			System.out.println ("not good :(");
			return new GraphPathImpl<String, CustomEdge> (graph, source,
					target, edges, 1);
		}

		List<String> path = new LinkedList<String> ();
		WeightedVertex current = visitMap.get (target);
		while (null != current) {
			path.add (current.name);
			current = visitMap.get (current.parent);
		}
		Collections.reverse (path);

		for (int i = 0; i < path.size () - 1; ++i) {
			++this.accessCount;
			edges.add (graph.getEdge (path.get (i), path.get (i + 1)));
		}

		return new GraphPathImpl<String, CustomEdge> (graph, source, target,
				edges, 1);
	}
}