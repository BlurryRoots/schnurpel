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
	Generator setSeed (long seed) {
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
		
		final int lowerBound = (int) Math.ceil (this.maxWeight / 2.0);
		final int upperBound = maxWeight;

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
					// create random weight from maxWeight / 2 to maxWeight - 1
					int cost = this.random.nextInt (upperBound - lowerBound) + lowerBound;
					// create new edge
					graph.addEdge (v, o, new CustomEdge (cost));
				}
			}
		}

		return graph;
	}
	
	private
	void reset () {
		this.random = null;
		this.maxWeight = 0;
		this.verticesCount = 0;		
	}
}
