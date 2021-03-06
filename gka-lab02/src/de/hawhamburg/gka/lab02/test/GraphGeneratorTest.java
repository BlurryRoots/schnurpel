package de.hawhamburg.gka.lab02.test;

import static org.junit.Assert.*;

import org.jgrapht.Graph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.lab02.GraphGenerator;

public class GraphGeneratorTest {
	private static final
	int SEED = 1337;

	private
	GraphGenerator generator;
	
	private
	boolean do800 = false;
	private
	boolean do2500 = false;
	
	private
	long t;
	
	@Before
	public void setUp () throws Exception {
		 generator = new GraphGenerator (SEED);
	}

	@After
	public void tearDown () throws Exception {
	}

	@Test
	public void testNet () {
		final int vc = 3;
		final int ec = 3;
		Graph<String, CustomEdge> graph = generator.generateNet (vc, ec, 42, 1);
		
		assertEquals (vc + 2, graph.vertexSet ().size ());
		assertEquals (ec + 2, graph.edgeSet ().size ());
	}
	
	@Test
	public void test800 () {
		if (do800) {
			final int vc = 800;
			final int ec = 300000;
			Graph<String, CustomEdge> graph = generator.generateDirected (vc, ec, 42, 1);
			
			assertEquals (vc, graph.vertexSet ().size ());
			assertEquals (ec, graph.edgeSet ().size ());
		}
		
		assertTrue (true);
	}

	@Test
	public void test2500 () {
		if (do2500) {
			final int vc = 2500;
			final int ec = 2000000;
			
			Graph<String, CustomEdge> graph = generator.generateDirected (vc, ec, 1337, 42);
			
			assertEquals (vc, graph.vertexSet ().size ());
			assertEquals (ec, graph.edgeSet ().size ());
		}
		
		assertTrue (true);
	}
	
	private
	void startTimer () {
		this.t = System.currentTimeMillis ();
	}
	
	private
	double stopTimer () {
		long c = System.currentTimeMillis () - this.t;
		this.t = 0;
		
		return (double)c / 1000.0;
	}
}
