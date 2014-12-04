package de.hawhamburg.gka.lab02.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.GraphParser;
import de.hawhamburg.gka.common.IPathfinder;
import de.hawhamburg.gka.lab02.Dijkstra;

public class DijkstraTest {
	
	private final
	String testGraphSource = 
		"Ottofeld -- Gotham (A) : 1;\n" +
		"Ottofeld -- Hanshausen (B) : 3;\n" +
		"Gotham -- Badhöhle (C) : 5;\n" +
		"Gotham -- Frankenthal (D) : 3;\n" +
		"Gotham -- Hanshausen (E) : 2;\n" +
		"Hanshausen -- Badhöhle (F) : 2;\n" +
		"Hanshausen -- Frankenthal (G) : 1;\n" +
		"Badhöhle -- Frankenthal (H) : 2;\n" +
		"Badhöhle -- Karlstadt (I) : 1;\n" +
		"Frankenthal -- Karlstadt (J) : 3;";

	
	private
	Graph<String, CustomEdge> expectedGraph;

	@Before
	public void setUp () throws Exception {
		expectedGraph =
				new SimpleGraph<String, CustomEdge> (CustomEdge.class);
		
		expectedGraph.addVertex ("Ottofeld");
		expectedGraph.addVertex ("Gotham");
		expectedGraph.addVertex ("Hanshausen");
		expectedGraph.addVertex ("Badhöhle");
		expectedGraph.addVertex ("Frankenthal");
		expectedGraph.addVertex ("Karlstadt");
				
		expectedGraph.addEdge ("Ottofeld", "Gotham", new CustomEdge ("A", 1));
		expectedGraph.addEdge ("Ottofeld", "Hanshausen", new CustomEdge ("B", 3));
		expectedGraph.addEdge ("Gotham", "Badhöhle", new CustomEdge ("C", 5));
		expectedGraph.addEdge ("Gotham", "Frankenthal", new CustomEdge ("D", 3));
		expectedGraph.addEdge ("Gotham", "Hanshausen", new CustomEdge ("E", 2));
		expectedGraph.addEdge ("Hanshausen", "Badhöhle", new CustomEdge ("F", 2));
		expectedGraph.addEdge ("Hanshausen", "Frankenthal", new CustomEdge ("G", 1));
		expectedGraph.addEdge ("Badhöhle", "Frankenthal", new CustomEdge ("H", 2));
		expectedGraph.addEdge ("Badhöhle", "Karlstadt", new CustomEdge ("I", 1));
		expectedGraph.addEdge ("Frankenthal", "Karlstadt", new CustomEdge ("J", 3));
		}

	@After
	public void tearDown () throws Exception {
	}
	
	@Test
	public void testGetPath () {
		final String source = "Ottofeld";
		final String target = "Karlstadt";
		IPathfinder finder = new Dijkstra ();		
		GraphParser parser = new GraphParser (testGraphSource);
		Graph<String, CustomEdge> graph = parser.getGraph ();
		
		assertEquals (expectedGraph, graph);
		
		List<String> expectedPath = new LinkedList<> ();
		expectedPath.add ("Ottofeld");
		expectedPath.add ("Hanshausen");
		expectedPath.add ("Badhöhle");
		expectedPath.add ("Karlstadt");

		List<String> path = finder.getPath (graph, source, target);
		assertNotNull (path);
		System.out.println (expectedPath.hashCode () + " : " + path.hashCode ());
		assertEquals (expectedPath, path);
	}

}
