package de.hawhamburg.gka.lab04.test;

import static org.junit.Assert.assertEquals;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.Multigraph;
import org.jgrapht.graph.SimpleGraph;
import org.junit.Before;
import org.junit.Test;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.lab04.FleurysAlgorithm;

public class FleurysAlgorithmTest {
	private
	Graph<String, CustomEdge> expectedResult;
	
	@Before public
	void setUp ()
	throws Exception {
		expectedResult = 
			new SimpleGraph<String, CustomEdge> (CustomEdge.class);
		expectedResult.addVertex ("A");
		expectedResult.addVertex ("B");
		expectedResult.addVertex ("C");
		expectedResult.addVertex ("D");
		expectedResult.addVertex ("E");
	}
	
	@Test public
	void test () {
		FleurysAlgorithm fa = new FleurysAlgorithm ();		
	}

	@Test public
	void testAnotherGraph () {
		UndirectedGraph<String, CustomEdge> graph = 
			new Multigraph<String, CustomEdge> (CustomEdge.class);
		
		graph.addVertex ("A");
		graph.addVertex ("B");
		graph.addVertex ("C");
		graph.addVertex ("D");
		graph.addVertex ("E");
		
		graph.addEdge ("A", "E", new CustomEdge (8));
		graph.addEdge ("A", "E", new CustomEdge (8));
		graph.addEdge ("E", "D", new CustomEdge (8));
		graph.addEdge ("E", "D", new CustomEdge (8));
		graph.addEdge ("D", "B", new CustomEdge (6));
		graph.addEdge ("D", "B", new CustomEdge (6));
		graph.addEdge ("B", "C", new CustomEdge (7));
		graph.addEdge ("B", "C", new CustomEdge (7));
		
		FleurysAlgorithm fa = new FleurysAlgorithm ();	
		assertEquals (expectedResult, fa.fleurysAlgorithm (graph, "A"));
	}
}
