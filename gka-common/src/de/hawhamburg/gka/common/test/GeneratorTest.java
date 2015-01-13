package de.hawhamburg.gka.common.test;

import static org.junit.Assert.*;

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
		Generator g = new Generator (1337);
		final int NV = 2400;
		
		Graph<String, CustomEdge> graph = g.generateComplete (NV, 12, 42);
		assertNotNull (graph);
		assertEquals (this.calculateEdgeCount (NV), graph.edgeSet ().size ());
	}

}
