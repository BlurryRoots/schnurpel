package de.hawhamburg.gka.lab02.test;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.GraphParser;
import de.hawhamburg.gka.lab02.Dijkstra;

public class DijkstraTest {
	
	private final
	String testGraphSource = 
		"Ottofeld -- Gotham (A) : 1;\n" +
		"Ottofeld -- Hanshausen (B) : 3;\n" +
		"Gotham -- Birdhöhle (C) : 5;\n" +
		"Gotham -- Frankenthal (D) : 3;\n" +
		"Gotham -- Hanshausen (E) : 2;\n" +
		"Hanshausen -- Birdhöhle (F) : 2;\n" +
		"Hanshausen -- Frankenthal (G) : 1;\n" +
		"Birdhöhle -- Frankenthal (H) : 2;\n" +
		"Birdhöhle -- Karlstadt (I) : 1;\n" +
		"Frankenthal -- Karlstadt (J) : 3;";
	
	private final
	Graph<String, CustomEdge> expectedGraph =
		new SimpleGraph<String, CustomEdge> (CustomEdge.class);

	@Before
	public void setUp () throws Exception {
		expectedGraph.addVertex ("Ottofeld");
		expectedGraph.addVertex ("Gotham");
		expectedGraph.addVertex ("Hanshausen");
		expectedGraph.addVertex ("Birdhöhle");
		expectedGraph.addVertex ("Frankenthal");
		expectedGraph.addVertex ("Karlstadt");
				
		expectedGraph.addEdge ("Ottofeld", "Gotham", new CustomEdge ("A", 1));
		expectedGraph.addEdge ("Ottofeld", "Hanshausen", new CustomEdge ("B", 3));
		expectedGraph.addEdge ("Gotham", "Birdhöhle", new CustomEdge ("C", 5));
		expectedGraph.addEdge ("Gotham", "Frankenthal", new CustomEdge ("D", 3));
		expectedGraph.addEdge ("Gotham", "Hanshausen", new CustomEdge ("E", 2));
		expectedGraph.addEdge ("Hanshausen", "Birdhöhle", new CustomEdge ("F", 2));
		expectedGraph.addEdge ("Hanshausen", "Frankenthal", new CustomEdge ("G", 1));
		expectedGraph.addEdge ("Birdhöhle", "Frankenthal", new CustomEdge ("H", 2));
		expectedGraph.addEdge ("Birdhöhle", "Karlstadt", new CustomEdge ("I", 1));
		expectedGraph.addEdge ("Frankenthal", "Karlstadt", new CustomEdge ("J", 3));
		}

	@After
	public void tearDown () throws Exception {
	}
	
	@Test
	public void testGetPath () {
		final String source = "Ottofeld";
		final String target = "Karlstadt";
		Dijkstra dijkstra = new Dijkstra ();		
		GraphParser parser = new GraphParser (testGraphSource);
		Graph<String, CustomEdge> graph = parser.getGraph ();
		
		assertEquals (expectedGraph, graph);
		
		List<String> expectedPath = new LinkedList<> ();
		expectedPath.add ("Ottofeld");
		expectedPath.add ("Hanshausen");
		expectedPath.add ("Birdhöhle");
		expectedPath.add ("Karlstadt");
		
		
		List<String> path = dijkstra.getPath (graph, source, target);
		assertNotNull (path);
		System.out.println (expectedPath.hashCode () + " : " + path.hashCode ());
		assertEquals (expectedPath, path);
	}

}
