package de.hawhamburg.gka.lab02.test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.SimpleGraph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.GraphParser;
import de.hawhamburg.gka.lab02.DijkstraFinal;

public class DijkstraTest {
	
	private final
	String testGraphSource = 
		"Hanshausen -- Karlstadt (A42) : 42;\n" +
		"Hanshausen -- Ottofeld (B1337) : 200;";
	
	private final
	Graph<String, CustomEdge> expectedGraph =
		new SimpleGraph<String, CustomEdge> (CustomEdge.class);

	@Before
	public void setUp () throws Exception {		
		expectedGraph.addVertex ("Hanshausen");
		expectedGraph.addVertex ("Karlstadt");
		expectedGraph.addVertex ("Ottofeld");
		
		expectedGraph.addEdge ("Hanshausen", "Karlstadt", new CustomEdge ("A42", 42));
		expectedGraph.addEdge ("Hanshausen", "Ottofeld", new CustomEdge ("B1337", 200));
	}

	@After
	public void tearDown () throws Exception {
	}

	@Test
	public void testGetPath () {
		DijkstraFinal dijkstra = new DijkstraFinal ();		
		GraphParser parser = new GraphParser (testGraphSource);
		
		assertEquals (expectedGraph, parser.getGraph ());
		
		GraphPath<String, CustomEdge> path =
			dijkstra.getPath (parser.getGraph (), "Ottofeld", "Karlstadt");

		assertEquals (expectedGraph, path);
	}

}
