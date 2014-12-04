package de.hawhamburg.gka.lab02;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;

import de.hawhamburg.gka.common.CustomEdge;

public class GraphGenerator {
	private
	Random r;
	
	public
	GraphGenerator (int seed) {
		this.r = new Random (seed);
	}
	
	public
	Graph<String, CustomEdge> generateDirected (int verticesCount, int edgeCount, int maxWeight, int minWeight) {
		if (edgeCount > (verticesCount * (verticesCount - 1) / 2)) {
			throw new RuntimeException ("To many edges!");
		}
		
		Graph<String, CustomEdge> graph = 
			new DefaultDirectedGraph<String, CustomEdge> (CustomEdge.class);
		
		List<String> vertices = new LinkedList<String> ();		
		for (int i = 0; i < verticesCount; ++i) {
			vertices.add ("v" + i);
		}
		
		for (String vertex : vertices) {
			graph.addVertex (vertex);
		}
		
		List<CustomEdge> edges = new LinkedList<CustomEdge> ();
		for (int i = 0; i < edgeCount; ++i) {
			edges.add (new CustomEdge ("path" + i, r.nextInt (maxWeight) + minWeight));
		}
		while (! edges.isEmpty ()) {
			String v1 = vertices.get (r.nextInt (verticesCount));
			String v2 = vertices.get (r.nextInt (verticesCount));

			// zweiten knoten im falle von kollision aufsteigen ermitteln	
			while (v1.equals (v2) ||
				   graph.containsEdge (v1, v2) ||
				   graph.containsEdge (v2, v1)) {
				v2 = vertices.get (r.nextInt (verticesCount));
			} 
			
			graph.addEdge (v1, v2, edges.remove (0));
		}
		
		return graph;
	}
	
	public
	Graph<String, CustomEdge> generateNet (int verticesCount, int edgeCount, int maxWeight, int minWeight) {
		Graph<String, CustomEdge> graph = generateDirected (verticesCount, edgeCount, maxWeight, minWeight);
		
		List<String> vertecies = new ArrayList<> (graph.vertexSet ());
		List<CustomEdge> edges = new ArrayList<> (graph.edgeSet ());
		
		String entry = vertecies.get (r.nextInt (verticesCount));
		
		graph.addVertex ("q");
		graph.addVertex ("s");
		
		graph.addEdge ("q", entry, new CustomEdge ("entry", maxWeight));
		
		CustomEdge exitEdge = edges.get (r.nextInt (edgeCount));
		graph.addEdge (exitEdge.getTarget (), "s", new CustomEdge ("exit", maxWeight));
		
		return graph;
	}
}
