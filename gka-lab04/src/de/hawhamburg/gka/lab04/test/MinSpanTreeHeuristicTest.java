package de.hawhamburg.gka.lab04.test;

import static org.junit.Assert.*;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.SimpleGraph;
import org.junit.Before;
import org.junit.Test;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.GraphParser;
import de.hawhamburg.gka.lab04.*;

public class MinSpanTreeHeuristicTest {

	private final
	String testGraphSourceDisconnected = 
			"Ottofeld -- Gotham (A) : 1;\n" +
			"Frankenthal -- Karlstadt (J) : 1;";
	private final
	String testGraphSourceConnected = 
			"Ottofeld -- Gotham (A) : 1;\n" +
			"Gotham -- Karlstadt (J) : 1;";
	
	private final
	String testMinSpanTree =
			"v1 -- v2 (A) : 22;\n" +
			"v2 -- v3 (B) : 40;\n" +
			"v3 -- v4 (C) : 23;\n" +
			"v3 -- v5 (D) : 30;\n";
	
	
	
	
	private
	Graph<String, CustomEdge> expectedGraph;	
	
	@Before
	public void setUp () throws Exception {
		expectedGraph =
				new SimpleGraph<String, CustomEdge> (CustomEdge.class);
		expectedGraph.addVertex("v1");
		expectedGraph.addVertex("v2");
		expectedGraph.addVertex("v3");
		expectedGraph.addVertex("v4");
		expectedGraph.addVertex("v5");

		expectedGraph.addEdge("v1", "v2", new CustomEdge ("A", 22));
		expectedGraph.addEdge("v2", "v1", new CustomEdge ("NONAME", 22));
		expectedGraph.addEdge("v2", "v3", new CustomEdge ("B", 40));
		expectedGraph.addEdge("v3", "v2", new CustomEdge ("NONAME", 40));
		expectedGraph.addEdge("v3", "v4", new CustomEdge ("C", 23));
		expectedGraph.addEdge("v4", "v3", new CustomEdge ("NONAME", 23));
		expectedGraph.addEdge("v3", "v5", new CustomEdge ("D", 30));
		expectedGraph.addEdge("v5", "v3", new CustomEdge ("NONAME", 30));
	}
	
	@Test
	public void testeulscherGraph() {
		MinSpanTreeHeuristic msth = new MinSpanTreeHeuristic();
		
		GraphParser parser = new GraphParser (testMinSpanTree);
		UndirectedGraph<String, CustomEdge> graph = (UndirectedGraph<String, CustomEdge>) parser.getGraph();
		
		assertEquals(expectedGraph, MinSpanTreeHeuristic.createEulerGraph(graph));

	}
	
	@Test
	public void testConnectivityInspector() {
		
		MinSpanTreeHeuristic msth = new MinSpanTreeHeuristic();

		GraphParser parserdc = new GraphParser (testGraphSourceDisconnected);
		UndirectedGraph<String, CustomEdge> dcGraph = (UndirectedGraph<String, CustomEdge>) parserdc.getGraph();

		assertEquals(false, msth.testInspector(dcGraph));
		
		GraphParser parserc = new GraphParser (testGraphSourceConnected);
		UndirectedGraph<String, CustomEdge> cGraph = (UndirectedGraph<String, CustomEdge>) parserc.getGraph();

		assertEquals(true, msth.testInspector(cGraph));		

		UndirectedGraph<String, CustomEdge> empty = new SimpleGraph<String, CustomEdge>(CustomEdge.class);
		
		assertEquals(false, msth.testInspector(empty));
		
	}

}
