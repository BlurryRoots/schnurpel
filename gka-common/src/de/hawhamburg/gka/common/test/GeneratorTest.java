package de.hawhamburg.gka.common.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.Generator;

public class GeneratorTest {

	private
	int calculateEdgeCount (int vertexCount) {
		return (vertexCount * (vertexCount - 1)) / 2;
	}
	
	@Before
	public void setUp () throws Exception {
	}

	@After
	public void tearDown () throws Exception {
	}

	@Test
	public void testGenerateComplete () {
		final int NUM_VERTICES = 4;
		final int WEIGHT = 42;
		final int SEED = 98723487;
		
		Generator g = new Generator ();
		g.setSeed (SEED);
		g.setMaximumWeight (WEIGHT);
		g.setVerticesCount (NUM_VERTICES);
		
		Graph<String, CustomEdge> graph = g.build ();
		assertNotNull (graph);
		assertEquals (this.calculateEdgeCount (NUM_VERTICES), graph.edgeSet ().size ());
		
		System.out.println (graph);
	}
}
