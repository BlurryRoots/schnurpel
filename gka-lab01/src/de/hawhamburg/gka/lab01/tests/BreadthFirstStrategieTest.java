package de.hawhamburg.gka.lab01.tests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.Pathfinder;
import de.hawhamburg.gka.lab01.algorithm.BreadthFirstStrategie;

public class BreadthFirstStrategieTest {
	private
	Graph<String, CustomEdge> graph;
	private
	Pathfinder finder;
	
	@Before public
	void setUp ()
	throws Exception {
		this.graph =
			new SimpleGraph<String, CustomEdge> (CustomEdge.class);
		
		this.graph.addVertex ("A");
		this.graph.addVertex ("B");
		this.graph.addVertex ("C");
		this.graph.addVertex ("D");
		this.graph.addVertex ("X");
		this.graph.addVertex ("Y");
		this.graph.addVertex ("Z");
		
		this.graph.addEdge ("A", "D", new CustomEdge (1));
		this.graph.addEdge ("B", "D", new CustomEdge (1));
		this.graph.addEdge ("X", "B", new CustomEdge (1));
		this.graph.addEdge ("C", "D", new CustomEdge (1));
		this.graph.addEdge ("C", "X", new CustomEdge (1));
		this.graph.addEdge ("C", "Y", new CustomEdge (1));
		this.graph.addEdge ("X", "Z", new CustomEdge (1));
		
		this.finder = new Pathfinder (
			this.graph,
			new BreadthFirstStrategie ()
		);		
	}

	@After public
	void tearDown ()
	throws Exception {
	}

	@Test public
	void testGetPath () {
		List<String> expectedPath = new LinkedList <> ();
		expectedPath.add ("A");
		expectedPath.add ("D");
		expectedPath.add ("B");
		expectedPath.add ("X");
		expectedPath.add ("Z");
		
		System.out.println ("Expected path is:\n" + expectedPath);
		List<String> path = this.finder.find ("A", "Z");
		System.out.println ("Path is:\n" + path);
		
		assertEquals (expectedPath.size (), path.size ());
		assertEquals (expectedPath, path);
	}

}
