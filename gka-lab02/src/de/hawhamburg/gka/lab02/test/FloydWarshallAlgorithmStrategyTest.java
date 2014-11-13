package de.hawhamburg.gka.lab02.test;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.jgrapht.GraphPath;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.GraphParser;
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
		
		GraphPath<String, CustomEdge> path =
			fw.getPath (parser.getGraph (), "Ottofeld", "Karlstadt");

		assertEquals (path.getGraph ().vertexSet ().size (), 3);
		assertEquals (new String [] {"Hanshausen", "Karlstadt", "Ottofeld"}, path.getGraph ().vertexSet ().toArray (new String [0]));
		
		assertEquals (path.getGraph ().vertexSet ().size (), 3);
	}

}
