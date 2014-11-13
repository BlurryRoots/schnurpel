package de.hawhamburg.gka.lab02;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.GraphPathImpl;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.IPathfinder;
import de.hawhamburg.gka.common.WeightedVertex;

public class DijkstraFinal
implements IPathfinder {
	
	private
	int accessCount;

	@Override
	public GraphPath<String, CustomEdge> getPath (
			Graph<String, CustomEdge> graph, String source, String target) {
		
		this.accessCount = 0;
		// path to be found
		List<String> path = new LinkedList<String> ();
		
		// create queue and list
		List<WeightedVertex> queue = new LinkedList<WeightedVertex> ();
		List<WeightedVertex> set = new LinkedList<WeightedVertex> ();
		
		// create start node
		WeightedVertex start = new WeightedVertex (source, null, 0);
		
		queue.add (start);
		set.add (start);
		
		boolean found = false;
		while (! queue.isEmpty () && ! found) {
			WeightedVertex current = queue.remove (0);
			
			if (current.name.equals (target)) {
				WeightedVertex n = current;
				while (null != n) {
					path.add (n.name);
					n = n.parent;
				}
				
				Collections.reverse (path);
				
				found = true;
			}
			else {
				for (CustomEdge edge : graph.edgesOf (current.name)) {
					++this.accessCount;
					
					WeightedVertex to = new WeightedVertex (
						edge.getTarget (),
						current,
						0
					);
					
					if (! set.contains (to)) {
						if (to.cost + edge.getCost () < to.cost) {
							WeightedVertex newTo = new WeightedVertex (to.name, current, to.cost + edge.getCost ());
							set.remove (to);
							queue.remove (to);
							
							set.add (newTo);
							queue.add (newTo);
						}
						else {
							set.add (to);
							queue.add (to);
						}
					}
				}
			}
		}
		
		List<CustomEdge> edges =
				new LinkedList<CustomEdge> ();
		for (int i = 0; i < path.size () - 1; ++i) {
			++this.accessCount;
			
			edges.add (graph.getEdge (
				path.get (i),
				path.get (i + 1)
			));
		}
		
		System.out.println ("Dijkstra needed " + this.accessCount + " access calls.");
		return new GraphPathImpl<String, CustomEdge> (graph, source, target, edges, 1);
	}

}
