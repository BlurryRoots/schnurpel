package de.hawhamburg.gka.lab04.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.SimpleGraph;
import org.junit.Before;
import org.junit.Test;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.GraphParser;
import de.hawhamburg.gka.lab04.FleurysAlgorithm;

public class FleurysAlgorithmTest {
	private
	List<String> expectedResult = new ArrayList<String>();
	
	@Before public
	void setUp () throws Exception {
		expectedResult.add("v1");
		expectedResult.add("v2");
		expectedResult.add("v3");
		expectedResult.add("v6");
		expectedResult.add("v4");
		expectedResult.add("v5");
		expectedResult.add("v2");
		expectedResult.add("v5");
		expectedResult.add("v3");
		expectedResult.add("v4");
		expectedResult.add("v1");		
	}
	
	@Test public
	void test () {
		UndirectedGraph<String, CustomEdge> graph = 
			new SimpleGraph<String, CustomEdge> (CustomEdge.class);
		graph.addVertex ("v1");
		graph.addVertex ("v2");
		graph.addVertex ("v3");
		graph.addVertex ("v4");
		graph.addVertex ("v5");
		graph.addVertex ("v6");		
//					   "v1 -- v2 (A) : 2;\n" +
		graph.addEdge ("v1", "v2", new CustomEdge (2));
//					   "v1 -- v4 (A) : 4;\n" +
		graph.addEdge ("v1", "v4", new CustomEdge (4));
//					   "v2 -- v3 (A) : 5;\n" +
		graph.addEdge ("v2", "v3", new CustomEdge (5));
//					   "v2 -- v5 (A) : 2;\n" +
		graph.addEdge ("v2", "v5", new CustomEdge (2));
//					   "v2 -- v5 (A) : 3;\n" +
		graph.addEdge ("v2", "v5", new CustomEdge (3));
//					   "v3 -- v4 (A) : 6;\n" +
		graph.addEdge ("v3", "v4", new CustomEdge (6));
//					   "v3 -- v5 (A) : 7;\n" +
		graph.addEdge ("v3", "v5", new CustomEdge (7));
//					   "v3 -- v6 (A) : 1;\n" +
		graph.addEdge ("v3", "v6", new CustomEdge (1));
//					   "v4 -- v5 (A) : 2;\n" +
		graph.addEdge ("v4", "v5", new CustomEdge (2));
//					   "v4 -- v6 (A) : 4;\n";
		graph.addEdge ("v4", "v6", new CustomEdge (4));
		
		FleurysAlgorithm fa = new FleurysAlgorithm ();		
		assertEquals (expectedResult, fa.fleurysAlgorithm (graph, "v1"));		
	}

}
