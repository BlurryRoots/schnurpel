package de.hawhamburg.gka.lab03.test;

import static org.junit.Assert.assertEquals;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.GraphParser;
import de.hawhamburg.gka.lab03.EdmondKarpFlowAnalyser;
import de.hawhamburg.gka.lab03.FlowAnalyser;
import de.hawhamburg.gka.lab03.FordFulkerson;

public class EdmondsKarpFlowTest {

	private final
	String testGraphSource = 
		"q -- v1 (A) : 5;\n" +
		"q -- v5 (A) : 1;\n" +
		"q -- v2 (A) : 4;\n" +
		"v1 -- v3 (A) : 1;\n" +
		"v1 -- s (A) : 3;\n" +
		"v1 -- v5 (A) : 1;\n" +
		"v2 -- v3 (A) : 2;\n" +
		"v3 -- s (A) : 3;\n" +
		"v5 -- s (A) : 3;\n";
	
	private final
	Graph<String, CustomEdge> expectedGraph =
		new SimpleGraph<String, CustomEdge> (CustomEdge.class);

	@Before
	public void setUp () throws Exception {
		expectedGraph.addVertex ("q");
		expectedGraph.addVertex ("v1");
		expectedGraph.addVertex ("v2");
		expectedGraph.addVertex ("v3");
		expectedGraph.addVertex ("v5");
		expectedGraph.addVertex ("s");

		expectedGraph.addEdge ("q", "v1", new CustomEdge ("A", 5));
		expectedGraph.addEdge ("q", "v5", new CustomEdge ("A", 1));
		expectedGraph.addEdge ("q", "v2", new CustomEdge ("A", 4));
		expectedGraph.addEdge ("v1", "v3", new CustomEdge ("A", 1));
		expectedGraph.addEdge ("v1", "s", new CustomEdge ("A", 3));
		expectedGraph.addEdge ("v1", "v5", new CustomEdge ("A", 1));
		expectedGraph.addEdge ("v2", "v3", new CustomEdge ("A", 2));
		expectedGraph.addEdge ("v3", "s", new CustomEdge ("A", 3));
		expectedGraph.addEdge ("v5", "s", new CustomEdge ("A", 3));
	}

	@After
	public void tearDown () throws Exception {
	}

	@Test
	public void test () {
		GraphParser parser = new GraphParser (testGraphSource);
		Graph<String, CustomEdge> graph = parser.getGraph ();
		
		assertEquals (expectedGraph, graph);
		
		FlowAnalyser ekf = new EdmondKarpFlowAnalyser (graph);
		assertEquals (8, ekf.maxFlow ("q", "s"));
	}

}
