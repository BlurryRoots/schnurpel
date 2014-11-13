package de.hawhamburg.gka.common.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.RegexGraphParser;

public class RegexGraphParserTest {

	
	@Before
	public void setUp () throws Exception {
		
	}

	@After
	public void tearDown () throws Exception {
	}

	@Test
	public void testGetGraphFromStringFail () {
		RegexGraphParser parser = new RegexGraphParser ();
		
		assertNull (parser.getGraphFromString ("asdf jklö"));
	}

	@Test
	public void testGetGraphFromString () {
		RegexGraphParser parser = new RegexGraphParser ();
		
		Graph<String, CustomEdge> resultGraph
			= new DefaultDirectedGraph<String, CustomEdge>(CustomEdge.class);;
		
		 String sourceSuccess = 
			"köln -> düsseldorf;\n" +
			"köln->kaiserslautern (lang);\n" +
			"kaiserslautern-> mainz : 1337;\n";
		
		resultGraph.addVertex ("köln");
		resultGraph.addVertex ("düsseldorf");
		resultGraph.addVertex ("kaiserslautern");
		resultGraph.addVertex ("mainz");
		resultGraph.addEdge (
			"köln",
			"düsseldorf",
			new CustomEdge ()
		);
		resultGraph.addEdge (
			"köln",
			"kaiserslautern",
			new CustomEdge ("lang")
		);
		resultGraph.addEdge (
			"kaiserslautern",
			"mainz",
			new CustomEdge (1337)
		);
		
		Graph<String, CustomEdge> graph = 
			parser.getGraphFromString (sourceSuccess);

		assertNotNull (graph);
		assertEquals(resultGraph.vertexSet(), graph.vertexSet());
		assertEquals(resultGraph.edgeSet(), graph.edgeSet());
	}
}
