package de.hawhamburg.gka.common;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;

public
class Generator {
	private
	Random random;
	private
	int maxWeight;
	private
	int verticesCount;
	
	public
	Generator () {
		this.reset ();
	}
	
	public
	Generator setSeed (int seed) {
		this.random = new Random (seed);
		
		return this;
	}
	
	public
	Generator setMaximumWeight (int weight) {
		if (0 >= weight) {
			throw new RuntimeException ("Negative weights!?");
		}
		this.maxWeight = weight;
		
		return this;
	}
	
	public
	Generator setVerticesCount (int count) {
		if (0 >= count) {
			throw new RuntimeException ("Number of vertices zero or negative?!");
		}
		this.verticesCount = count;
		
		return this;
	}
	
	public
	Graph<String, CustomEdge> build () {
		if (null == this.random
		 || 0 == this.maxWeight
		 || 0 == this.verticesCount) {
			throw new RuntimeException ("Please specifiy all neccessary parameters!");
		}
		
		Graph<String, CustomEdge> graph = new SimpleGraph<String, CustomEdge>(
			CustomEdge.class
		);
		
		for (int i = 0; i < verticesCount; ++i) {
			String name = "v" + String.valueOf (i);
			graph.addVertex (name);
		}
		
		Set<String> vertecies = graph.vertexSet (); 
		for (String v : vertecies) {
			for (String o : vertecies) {
				if (! v.equals (o) 
					&&	! (graph.containsEdge (v, o)
						  || graph.containsEdge (o, v))) {
					// initialize new edge with the maximal possible weight
					graph.addEdge (v, o, new CustomEdge (this.maxWeight));
				}
			}
		}
		
		// create a list of unweighted edges (Lue)
		List<CustomEdge> unweightedEdges = new LinkedList<> (graph.edgeSet ());
		
		initialSetup (graph, unweightedEdges);
		setRemainingWeights (graph, unweightedEdges);
		
		return graph;
	}
	
	private
	void initialSetup (Graph<String, CustomEdge> graph, List<CustomEdge> unweightedEdges) {
		// select random edge (E) and its vertices (Va & Vb)
		int index = this.random.nextInt (unweightedEdges.size ());
		// remove (E) from (Lue)
		CustomEdge edge = unweightedEdges.remove (index);
		// and assign a random weight (W)
		edge.setCost (this.randomCost (this.maxWeight));
		
		// now select all vertices which are not connected to this edge and put them into a list (L)
		List<String> remainingVertices = new LinkedList<> (graph.vertexSet ());
		remainingVertices.remove (edge.getSource ());
		remainingVertices.remove (edge.getTarget ());		
		// for each vertex (V) in this list (L)
		while (! remainingVertices.isEmpty ()) {
			String v = remainingVertices.remove (0);
			// select edge (E1) from current (V) to (Va)
			CustomEdge e1 = graph.getEdge (edge.getSource (), v);
			// and assign w(E1) a random weight rand(W) + 1
			e1.setCost (this.randomCost (edge.getCost ()));
			// remove (E1) from (Lue)
			unweightedEdges.remove (e1);
			
			// select edge (E2) from current (V) to (Vb)			
			CustomEdge e2 = graph.getEdge (edge.getTarget (), v);
			// and assign w(E2) equal to W - w(E1)
			e2.setCost (edge.getCost () - e1.getCost ());
			// remove (E2) from (Lue)
			unweightedEdges.remove (e2);
		}
	}
	
	private
	void setRemainingWeights (Graph<String, CustomEdge> graph, List<CustomEdge> unweightedEdges) {
		// for every remaining unweighted edge in (L)
		while (! unweightedEdges.isEmpty ()) {
			// select edge (E)
			// remove (EMin) from (L)
			CustomEdge edge = unweightedEdges.remove (0);
			// create list of vertices not connected to (E)
			// now select all vertices which are not connected to this edge and put them into a list (L)
			List<String> remainingVertices = new LinkedList<> (graph.vertexSet ());
			remainingVertices.remove (edge.getSource ());
			remainingVertices.remove (edge.getTarget ());
			// fetch vertices of current edge
			String a = edge.getSource ();
			String b = edge.getTarget ();

			// build a set of significant pairs
			// where significant means all legs are properly weighted
			List<EdgePair> pairs = new LinkedList<> ();			
			// for each vertex in list
			for (String v : remainingVertices) {
				// check if pair is fully set
				EdgePair pair = new EdgePair (graph.getEdge (a, v), graph.getEdge (b, v));
				if (! unweightedEdges.contains (pair.left)
				 && ! unweightedEdges.contains (pair.right)) {
					pairs.add (pair);					
				}
			}

			// set the initial value to be the maxium, so the first properly set
			// pair can be assign as minimum
			int min = maxWeight;
			// for each pair
			for (EdgePair pair : pairs) {
				// calculate minimum (Min) of pairs of edges connected to current vertex
				int costSum = pair.left.getCost () + pair.right.getCost ();
				// select this (EMin) 
				min = (costSum < min) ? costSum : min;
			}
			
			// calculate random weight with upper bound (Min) and assign it to (EMin)				
			edge.setCost (this.randomCost (min));
		}
	}
	
	private
	int randomCost (int max) {
		return this.random.nextInt (max) + 1;
	}
	
	private
	void reset () {
		this.random = null;
		this.maxWeight = 0;
		this.verticesCount = 0;		
	}
	
	private static
	class EdgePair {
		public
		CustomEdge left;
		public
		CustomEdge right;
		
		public
		EdgePair (CustomEdge left_, CustomEdge right_) {
			this.left = left_;
			this.right = right_;
		}
	}
}
