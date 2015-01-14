package de.hawhamburg.gka.lab01.algorithm;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;

import de.hawhamburg.gka.common.ISearchStrategy;
import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.WeightedVertex;

public
class BreadthFirstStrategie
	implements ISearchStrategy {

	@Override
	public
	List<String> getPath (Graph<String,
						  CustomEdge> graph,
						  String source,
						  String target) {
//		 procedure BFS(G,v) is
//	      create a queue Q
//	      create a set V
//	      add v to V
//	      enqueue v onto Q
//	      while Q is not empty loop
//	         t ← Q.dequeue()
//	         
//	         if t is what we are looking for then
//	            return t
//	        end if
//	        
//	        for all edges e in G.adjacentEdges(t) loop
//	           u ← G.adjacentVertex(t,e)
//	           if u is not in V then
//	               add u to V
//	               enqueue u onto Q
//	           end if
//	        end loop
//	     end loop
//	     return none
//	 	end BFS
		
		
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
					WeightedVertex end = new WeightedVertex (
						(current.name.equals (edge.getTarget ()))
							? edge.getSource ()
							: edge.getTarget (),
						current,
						0
					);
					
					if (! set.contains (end)) {
						set.add (end);
						queue.add (end);
					}
				}
			}
		}
		
		return path;
	}
}
