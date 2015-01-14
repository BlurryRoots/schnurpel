package de.hawhamburg.gka.lab04.test;

import static org.junit.Assert.*;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.SimpleGraph;
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
	
	@Test
	public void testConnectivityInspector() {
		
		MinSpanTreeHeuristic msth = new MinSpanTreeHeuristic();

		GraphParser parserdc = new GraphParser (testGraphSourceDisconnected);
		UndirectedGraph<String, CustomEdge> dcGraph = (UndirectedGraph<String, CustomEdge>) parserdc.getGraph();

		assertEquals(false, msth.testInspector(dcGraph));
		
		GraphParser parserc = new GraphParser (testGraphSourceConnected);
		UndirectedGraph<String, CustomEdge> cGraph = (UndirectedGraph<String, CustomEdge>) parserc.getGraph();

		assertEquals(true, msth.testInspector(cGraph));		

	}

}
