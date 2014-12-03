package de.hawhamburg.gka.lab02.test;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.jgrapht.Graph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.lab02.GraphGenerator;

public class GraphGeneratorTest {
	private
	boolean do800 = false;
	private
	boolean do2500 = false;
	
	private
	long t;
	
	@Before
	public void setUp () throws Exception {
	}

	@After
	public void tearDown () throws Exception {
	}

	@Test
	public void test800 () {
		if (do800) {
			final int vc = 800;
	//		final int ec = vc * (vc - 1);
			final int ec = 300000;
			Graph<String, CustomEdge> graph = GraphGenerator.generateDirected (vc, ec, 42, 1);
		}
		
		assertTrue (true);
	}
	
	@Test
	public void testKeks () {
		this.startTimer ();
		GraphGenerator.generateDirected (800, 100000, 42, 1);
		System.out.println ("time: " + this.stopTimer ());
	}

	@Test
	public void test2500 () {
		if (do2500) {
			final int vc = 2500;
			final int ec = 2000000;
			Graph<String, CustomEdge> graph = GraphGenerator.generateDirected (vc, ec, 1337, 42);
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
