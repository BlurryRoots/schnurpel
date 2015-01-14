package de.hawhamburg.gka.lab04.test;

import static org.junit.Assert.*;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.SimpleGraph;
import org.junit.Before;
import org.junit.Test;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.GraphParser;
import de.hawhamburg.gka.lab04.MinSpanTree;
import de.hawhamburg.gka.lab04.MinSpanTreeHeuristic;

public class MinSpanTreeTest {

	private final
	String testGraphSource = 
			"v0 -- v1 (A) : 27;\n" +
			"v0 -- v2 (B) : 14;\n" +
			"v0 -- v5 (C) : 16;\n" +
			"v0 -- v6 (D) : 33;\n" +
			"v1 -- v4 (E) : 10;\n" +
			"v1 -- v6 (F) : 4;\n" +
			"v2 -- v3 (G) : 20;\n" +
			"v2 -- v5 (H) : 6;\n" +
			"v2 -- v6 (I) : 22;\n" +
			"v3 -- v4 (J) : 40;\n" +
			"v3 -- v5 (K) : 23;\n" +
			"v3 -- v6 (L) : 30;\n" +
			"v4 -- v6 (M) : 13;\n";
	
	private
	Graph<String, CustomEdge> expectedGraph;
	
	@Before
	public void setUp () throws Exception {
		expectedGraph =
				new SimpleGraph<String, CustomEdge> (CustomEdge.class);

		expectedGraph.addVertex ("v0");
		expectedGraph.addVertex ("v1");
		expectedGraph.addVertex ("v2");
		expectedGraph.addVertex ("v3");
		expectedGraph.addVertex ("v4");
		expectedGraph.addVertex ("v5");
		expectedGraph.addVertex ("v6");

		expectedGraph.addEdge("v0", "v2", new CustomEdge ("B", 14));
		expectedGraph.addEdge("v1", "v4", new CustomEdge ("E", 10));
		expectedGraph.addEdge("v1", "v6", new CustomEdge ("F", 4));
		expectedGraph.addEdge("v2", "v3", new CustomEdge ("G", 20));
		expectedGraph.addEdge("v2", "v5", new CustomEdge ("H", 6));
		expectedGraph.addEdge("v2", "v6", new CustomEdge ("I", 22));
	}
		
	@Test
	public void testMinSpanTree() {
		MinSpanTree mst = new MinSpanTree();
		
		GraphParser parser = new GraphParser (testGraphSource);
		UndirectedGraph<String, CustomEdge> graph = (UndirectedGraph<String, CustomEdge>) parser.getGraph();
		
		
		assertEquals(expectedGraph, mst.minSpanTree(graph));
		
	}

}
