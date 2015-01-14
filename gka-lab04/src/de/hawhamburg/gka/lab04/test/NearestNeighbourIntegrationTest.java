package de.hawhamburg.gka.lab04.test;

import static org.junit.Assert.*;

import java.util.Set;

import org.jgrapht.Graph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.Generator;
import de.hawhamburg.gka.lab04.NearestNeighbour;

public
class NearestNeighbourIntegrationTest {

	@Before public
	void setUp ()
	throws Exception {
	}

	@After public
	void tearDown ()
	throws Exception {
	}

	@Test public
	void test () {
		final int SEED = 1337;
		final int WEIGHT = 123;
		final int VCOUNT = 24;
		final String START = "v1";
		
		Graph<String, CustomEdge> problem = new Generator ()
			.setSeed (SEED)
			.setMaximumWeight (WEIGHT)
			.setVerticesCount (VCOUNT)
			.build ();
		NearestNeighbour nn = new NearestNeighbour (problem);
		
		Graph<String, CustomEdge> tour = nn.getTour (START);
		assertNotNull (tour);
		assertEquals (VCOUNT, tour.vertexSet ().size ());
		assertEquals (VCOUNT, tour.edgeSet ().size ());
		
		Set<CustomEdge> startEdges = tour.edgesOf (START);

		// TODO: calc min span tree weight
		// TODO: calc double min span tree weight
		// TODO: calc total weight of tour
		// TODO: calc ration between result and best/worst case
	}

	public
	String walkToNextNode (String from, Graph<String, CustomEdge> in) {
		Set<CustomEdge> edges = in.edgesOf (from);
		assertNotNull (edges);
		CustomEdge edge = edges.iterator ().next ();
		assertNotNull (edge);
		
		return (from.equals (edge.getSource ()))
			? edge.getTarget ()
			: edge.getSource ();
	}
}