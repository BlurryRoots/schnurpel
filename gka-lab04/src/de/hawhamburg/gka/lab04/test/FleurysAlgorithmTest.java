package de.hawhamburg.gka.lab04.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.junit.Before;
import org.junit.Test;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.GraphParser;
import de.hawhamburg.gka.lab04.FleurysAlgorithm;
import de.hawhamburg.gka.lab04.MinSpanTree;

public class FleurysAlgorithmTest {

	private final
	String testeulertour =
			"v1 -- v2 (A) : 2;\n" +
			"v1 -- v4 (A) : 4;\n" +
			"v2 -- v3 (A) : 5;\n" +
			"v2 -- v5 (A) : 2;\n" +
			"v2 -- v5 (A) : 3;\n" +
			"v3 -- v4 (A) : 6;\n" +
			"v3 -- v5 (A) : 7;\n" +
			"v3 -- v6 (A) : 1;\n" +
			"v4 -- v5 (A) : 2;\n" +
			"v4 -- v6 (A) : 4;\n";
	
	private
	List<String> expectedResult = new ArrayList<String>();;
	
	@Before
	public void setUp () throws Exception {
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
	
	@Test
	public void test() {

		FleurysAlgorithm fa = new FleurysAlgorithm();
		
		GraphParser parser = new GraphParser (testeulertour);
		UndirectedGraph<String, CustomEdge> graph = (UndirectedGraph<String, CustomEdge>) parser.getGraph();
		
		assertEquals(expectedResult, fa.fleurysAlgorithm(graph, "v1"));
		
	}

}
