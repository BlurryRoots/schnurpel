package de.hawhamburg.gka.lab02.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.GraphParser;
import de.hawhamburg.gka.common.ParserBuilder;
import de.hawhamburg.gka.lab02.GraphFileWriter;

public class GraphFileWriterTest {
	
	private final
	String TEMP_FILE_PATH = "/tmp/graph.gka";
	
	private final
	String testGraphSource = 
		"q -- v1 (A) : 5;\n" +
		"q -- v5 (A) : 1;\n" +
		"q -- v2 (A) : 4;\n" +
		"v1 -- v3 (A) : 1;\n" +
		"v1 -- s (A) : 3;\n" +
		"v1 -- v5 (A) : 1;\n" +
		"v2 -- v3 (A) : 2;\n" +
		"v3 -- s (A) : 3;\n" +
		"v5 -- s (A) : 3;\n";

	private
	Graph<String, CustomEdge> expectedGraph;
	
	@Before
	public void setUp () throws Exception {
		expectedGraph =
				new SimpleGraph<String, CustomEdge> (CustomEdge.class);
		
		expectedGraph.addVertex ("q");
		expectedGraph.addVertex ("v1");
		expectedGraph.addVertex ("v2");
		expectedGraph.addVertex ("v3");
		expectedGraph.addVertex ("v5");
		expectedGraph.addVertex ("s");

		expectedGraph.addEdge ("q", "v1", new CustomEdge ("A", 5));
		expectedGraph.addEdge ("q", "v5", new CustomEdge ("A", 1));
		expectedGraph.addEdge ("q", "v2", new CustomEdge ("A", 4));
		expectedGraph.addEdge ("v1", "v3", new CustomEdge ("A", 1));
		expectedGraph.addEdge ("v1", "s", new CustomEdge ("A", 3));
		expectedGraph.addEdge ("v1", "v5", new CustomEdge ("A", 1));
		expectedGraph.addEdge ("v2", "v3", new CustomEdge ("A", 2));
		expectedGraph.addEdge ("v3", "s", new CustomEdge ("A", 3));
		expectedGraph.addEdge ("v5", "s", new CustomEdge ("A", 3));
	}

	@After
	public void tearDown () throws Exception {
	}

	@Test
	public void test () {
		try {
			GraphFileWriter writer = new GraphFileWriter (new File (TEMP_FILE_PATH));
			writer.write (expectedGraph);
			writer.close ();
			
			GraphParser parser = ParserBuilder.createFromFile (TEMP_FILE_PATH);
			Graph<String, CustomEdge> readGraph = parser.getGraph ();
			
			assertEquals (expectedGraph, readGraph);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
