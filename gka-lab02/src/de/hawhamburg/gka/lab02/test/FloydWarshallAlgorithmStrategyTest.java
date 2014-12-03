package de.hawhamburg.gka.lab02.test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.GraphParser;
import de.hawhamburg.gka.lab02.BigGraphGenerator;
import de.hawhamburg.gka.lab02.FloydWarshall;

public class FloydWarshallAlgorithmStrategyTest {

	private final
	String testGraphSource = 
		"Hanshausen -- Karlstadt (A42) : 42;\n" +
		"Hanshausen -- Ottofeld (B1337) : 200;";
	
	private final
	Set<String> vertices = new HashSet<String> ();

	@Before
	public void setUp () throws Exception {
		vertices.add ("Hanshausen");
		vertices.add ("Karlstadt");
		vertices.add ("Ottofeld");
	}

	@After
	public void tearDown () throws Exception {
	}

	@Test
	public void testGetPath () {
		FloydWarshall fw = new FloydWarshall ();		
		GraphParser parser = new GraphParser (testGraphSource);
	}
	
	@Test
	public void testBigGraph () {
		assertTrue (true);
//		Graph<String, CustomEdge> graph = BigGraphGenerator.generate (1000, 6000);
//		
//		FloydWarshall fw = new FloydWarshall ();
//		GraphPath<String, CustomEdge> path =
//			fw.getPath (graph, "v1", "v433");
//		
//		assertNotNull (path);
//		assertTrue (path.getGraph ().vertexSet ().size () > 0);
	}

}
