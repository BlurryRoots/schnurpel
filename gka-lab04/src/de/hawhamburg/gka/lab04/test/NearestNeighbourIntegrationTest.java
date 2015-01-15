package de.hawhamburg.gka.lab04.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.Generator;
import de.hawhamburg.gka.lab04.MinSpanTree;
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
//		final int SEED = 1337;
		final int SEED = (int)System.currentTimeMillis ();
		final int WEIGHT = new Random (SEED).nextInt (1337) + 10;
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
		MinSpanTree mst = new MinSpanTree ((UndirectedGraph<String, CustomEdge>) problem);
		Graph<String, CustomEdge> minSpanTree = mst.getMinimumSpanTree();
		
		Set<CustomEdge> edgesSpanTreeSet = minSpanTree.edgeSet();
		
		CustomEdge spanTreeEdge = null;
		int spanTreeWeight = 0;
		
		Iterator<CustomEdge> spanTreeIt = edgesSpanTreeSet.iterator();
		
		while(spanTreeIt.hasNext()){
		
			spanTreeEdge = spanTreeIt.next();
			spanTreeWeight += spanTreeEdge.getCost();
		}
	
		// TODO: calc double min span tree weight
			int doubleSpanTreeWeight = spanTreeWeight * 2;
			
		// TODO: calc total weight of tour
			Graph<String, CustomEdge> getTour = nn.getTour(START);
			
			Set<CustomEdge> edgesTourSet = getTour.edgeSet();
			
			Iterator<CustomEdge> tourIt = edgesTourSet.iterator();
			
			CustomEdge tourEdge = null;
			int tourWeight = 0;
			
			while(tourIt.hasNext()){
				
				tourEdge = tourIt.next();
				tourWeight += tourEdge.getCost();
			}

			System.out.println("spanTreeWeight: " + spanTreeWeight);
			System.out.println("doubleSpanTreeWeight: " + doubleSpanTreeWeight);
			System.out.println("tourWeight: " + tourWeight);

		// TODO: calc ration between result and best/worst case
			
			assertEquals(true, tourWeight > spanTreeWeight && tourWeight < doubleSpanTreeWeight );
			
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
