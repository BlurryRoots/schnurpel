package de.hawhamburg.gka.lab02;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;

import de.hawhamburg.gka.common.CustomEdge;

public class GraphGenerator {
	public static
	Graph<String, CustomEdge> generateDirected (int verticesCount, int edgeCount, int maxWeight, int minWeight) {
		Random r = new Random (1337);
		
		Graph<String, CustomEdge> graph = 
			new DefaultDirectedGraph<String, CustomEdge> (CustomEdge.class);
		Graph<String, CustomEdge> completeGraph = 
				new DefaultDirectedGraph<String, CustomEdge> (CustomEdge.class);
		
		List<String> vertices = new LinkedList<String> ();		
		for (int i = 0; i < verticesCount; ++i) {
			vertices.add ("v" + i);
		}
		
		for (String vertex : vertices) {
			graph.addVertex (vertex);
			completeGraph.addVertex (vertex);
		}
		
		for (int itc = 0; itc < verticesCount; ++itc) {
			for (int vi = 0; vi < verticesCount; ++vi) {
				StringBuilder b = new StringBuilder ();
				b.append ("path").append (itc).append (vi);
				if (vi == itc) {
					continue;
				}
				completeGraph.addEdge (
					vertices.get (itc),
					vertices.get (vi),
					new CustomEdge (b.toString (), r.nextInt (maxWeight) + minWeight)
				);
			}
		}
		
//		List<CustomEdge> edges = new LinkedList<CustomEdge> ();
//		for (int i = 0; i < edgeCount; ++i) {
//			edges.add (new CustomEdge ("path" + i, r.nextInt (maxWeight) + minWeight));
//		}
//		for (CustomEdge edge : edges) {
//			String v1, v2;
//			do {
//				v1 = vertices.get (r.nextInt (verticesCount));
//				v2 = vertices.get (r.nextInt (verticesCount));
//			} while (v1.equals (v2) || graph.containsEdge (v1, v2));
//			
//			graph.addEdge (v1, v2, edge);
//		}

		final int maxEdgeCount = verticesCount * (verticesCount - 1) / 2;
		List<CustomEdge> possibleEdges = new ArrayList<> (completeGraph.edgeSet ());
		for (int i = 0; i < edgeCount; ++i) {
			int edgeIndex = r.nextInt (possibleEdges.size ());
			CustomEdge edge = possibleEdges.remove (edgeIndex);
			graph.addEdge (edge.getSource (), edge.getTarget (), edge);
		}
		
		return graph;
	}
}
