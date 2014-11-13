package de.hawhamburg.gka.lab02;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.jgrapht.Graph;
import org.jgrapht.graph.GraphPathImpl;
import org.jgrapht.graph.SimpleGraph;

import de.hawhamburg.gka.common.CustomEdge;

public class BigGraphGenerator {
	public static
	Graph<String, CustomEdge> generate (int verticesCount, int edgeCount) {
		Random r = new Random (1337);		
		Graph<String, CustomEdge> graph = 
			new SimpleGraph<String, CustomEdge> (CustomEdge.class);
		
		List<String> vertices = new LinkedList<String> ();		
		for (int i = 0; i < verticesCount; ++i) {
			vertices.add ("v" + i);
		}
		
		for (String vertex : vertices) {
			graph.addVertex (vertex);
		}
		
		List<CustomEdge> edges = new LinkedList<CustomEdge> ();
		for (int i = 0; i < edgeCount; ++i) {
			edges.add (new CustomEdge ("path" + i, r.nextInt (42) + 1));
		}
		for (CustomEdge edge : edges) {
			int indexOne = r.nextInt (verticesCount);
			int indexTwo = r.nextInt (verticesCount);
			
			graph.addEdge (vertices.get (indexTwo), vertices.get (indexTwo), edge);
		}
		
		return graph;
	}
}
