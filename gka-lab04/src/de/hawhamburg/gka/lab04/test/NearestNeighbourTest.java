package de.hawhamburg.gka.lab04.test;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.lab04.NearestNeighbour;

public
class NearestNeighbourTest {
	private
	Graph<String, CustomEdge> graph;

	@Before public
	void setUp ()
	throws Exception {
		this.graph = 
			new SimpleGraph<String, CustomEdge> (CustomEdge.class);
		
		this.graph.addVertex ("A");
		this.graph.addVertex ("B");
		this.graph.addVertex ("C");
		this.graph.addVertex ("D");
		this.graph.addVertex ("E");
		
		this.graph.addEdge ("A", "B", new CustomEdge (11));
		this.graph.addEdge ("A", "C", new CustomEdge (10));
		this.graph.addEdge ("A", "D", new CustomEdge (9));
		this.graph.addEdge ("A", "E", new CustomEdge (8));
		this.graph.addEdge ("B", "C", new CustomEdge (7));
		this.graph.addEdge ("B", "D", new CustomEdge (6));
		this.graph.addEdge ("B", "E", new CustomEdge (11));
		this.graph.addEdge ("C", "D", new CustomEdge (10));
		this.graph.addEdge ("C", "E", new CustomEdge (9));
		this.graph.addEdge ("D", "E", new CustomEdge (8));
	}

	@After public
	void tearDown ()
	throws Exception {
	}

	@Test public
	void testGetTour () {
		Graph<String, CustomEdge> expectedTour = 
			new SimpleGraph<String, CustomEdge> (CustomEdge.class);
		expectedTour.addVertex ("A");
		expectedTour.addVertex ("E");
		expectedTour.addVertex ("D");
		expectedTour.addVertex ("B");
		expectedTour.addVertex ("C");
		expectedTour.addEdge ("A", "E", this.graph.getEdge ("A", "E"));
		expectedTour.addEdge ("E", "D", this.graph.getEdge ("E", "D"));
		expectedTour.addEdge ("D", "B", this.graph.getEdge ("D", "B"));
		expectedTour.addEdge ("B", "C", this.graph.getEdge ("B", "C"));
		expectedTour.addEdge ("C", "A", this.graph.getEdge ("A", "C"));
		
		NearestNeighbour nn = new NearestNeighbour (graph);
		
		Graph<String, CustomEdge> result = nn.getTour ("A");
		assertNotNull (result);
		assertEquals (expectedTour, result);
	}
}
