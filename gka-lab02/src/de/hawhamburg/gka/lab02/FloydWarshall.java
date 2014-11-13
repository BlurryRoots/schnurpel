package de.hawhamburg.gka.lab02;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.GraphPathImpl;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.IPathfinder;
import de.hawhamburg.gka.common.Matrix;

public
class FloydWarshall
implements IPathfinder {
	
	private static final
	int NO_TRANSITION = -42;
	
	private
	int accessCount;
	
	public
	GraphPath<String, CustomEdge> getPath (Graph<String, CustomEdge> graph, String source, String target) {

		this.accessCount = 0;
		
		boolean inputValid = 
			null != graph && source != null && target != null &&
			graph.containsVertex (source) &&
			graph.containsVertex (target);

		if (! inputValid) {
			throw new RuntimeException ("Invalid input!");
		}

		Matrix<Integer> distances = this.createDistanceMatrix (graph);
		Matrix<Integer> transitions = this.createTransitionMatrix (graph);
		
		this.calculatePossiblePaths (graph, distances, transitions);
		
		List<CustomEdge> edges =
			this.createResult (graph, distances, transitions, source, target);
		GraphPathImpl<String, CustomEdge> pathGraph = 
				new GraphPathImpl<String, CustomEdge> (graph, source, target, edges, 1);
		
		System.out.println ("FloydWarshall had " + this.accessCount + " graph access calls.");
		
		return pathGraph;
	}
	
	private
	Matrix<Integer> createDistanceMatrix (Graph<String, CustomEdge> graph) {
		List<String> vertices = new ArrayList<String> (graph.vertexSet ());
		int c = vertices.size ();
		Matrix<Integer> m = new Matrix<Integer> (c, Integer.MAX_VALUE);
		
		// simulate a two dimensional matrix with x and y coordinates
		for (int x = 0; x < c; ++x) {
			String xName = vertices.get (x);
			
			for (int y = 0; y < c; ++y) {
				String yName = vertices.get(y);
				
				// initialize to max int
				int cost = Integer.MAX_VALUE;
				
				if (xName.equals(yName)) {
					cost = 0;
				}
				else {
					++this.accessCount;
					
					// either choose weight of edge, or reset it to max value if there
					// are no edges
					int ec = graph.getAllEdges(xName, vertices.get(y)).size();
					cost = 0 < ec
						? graph.getEdge(xName, vertices.get(y)).getCost()
						: cost;
				}
				
				m.insert (x, y, cost);
			}
		}
		
		return m;
	}
	
	private
	Matrix<Integer> createTransitionMatrix (Graph<String, CustomEdge> graph) {
		// initialize with no transition value
		return new Matrix<Integer> (graph.vertexSet ().size (), NO_TRANSITION);
	}
	
	private
	List<CustomEdge> createResult (
			Graph<String, CustomEdge> graph,
			Matrix<Integer> distances,
			Matrix<Integer> transitions,
			String source, String target) {

		List<String> vertices = new ArrayList<String> (graph.vertexSet ());
		
		List<CustomEdge> result =
			new LinkedList<CustomEdge> (); 
		
		if (NO_TRANSITION == transitions.retrieve (
				vertices.indexOf(source),
				vertices.indexOf(target)
			)) {
			return result;
		}

		List<String> stopovers = new ArrayList<String> ();
		stopovers.add (source);
		stopovers.add (target);
		
		boolean run = true;
		while (run) {
			int c = stopovers.size ();
			for (int i = 0; i < (c - 1); ++i) {
				String from = stopovers.get (i);
				String to = stopovers.get (i + 1);
				
				int viaIndex = transitions.retrieve (
					vertices.indexOf (from),
					vertices.indexOf (to)
				);
				if (NO_TRANSITION != viaIndex) {
					String via = vertices.get (viaIndex);
					stopovers.add (i + 1, via);
				}
			}
			
			run = c != stopovers.size ();
		}
		
		for (int i = 0; i < stopovers.size () - 1; ++i) {
			++this.accessCount;
			
			result.add (graph.getEdge (
				stopovers.get (i),
				stopovers.get (i + 1)
			));
		}
		
		return result;
	}
	
	private
	void calculatePossiblePaths (
			Graph<String, CustomEdge> graph,
			Matrix<Integer> distances,
			Matrix<Integer> transitions) {

		ArrayList<String> vertices = new ArrayList<String> (graph.vertexSet ());
		int c = vertices.size ();
		
		// from where
		for (int j = 0; j < c; ++j) {
			// to
			for (int i = 0; i < c; ++i) {
				if (i != j) {
					// via
					for (int k = 0; k < c; ++k) {
						if (k != j) {
							int dik = distances.retrieve(i, k);
							int dij = distances.retrieve(i, j);
							int djk = distances.retrieve(j, k);
							
							final int min = FloydWarshall.checkedAddedMinimum (dij, djk, dik);
							if (min < 0) {
								throw new RuntimeException ("Loop detected!");
							}
							
							if (dik != min) {
								distances.insert (i, k, min);									
								transitions.insert (i, k, j);
							}
							
						}
					}
							
				}	
			}		
		}
	}
	
	private static
	Integer checkedAddedMinimum (Integer a, Integer b, Integer comp) {
		if (Integer.MAX_VALUE == a ||
			Integer.MAX_VALUE == b) {
			return comp;
		}
		
		return Math.min (comp, (a + b));
	}
}
