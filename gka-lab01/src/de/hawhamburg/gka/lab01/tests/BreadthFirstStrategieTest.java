package de.hawhamburg.gka.lab01.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.jgrapht.Graph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.Pathfinder;
import de.hawhamburg.gka.common.RegexGraphParser;
import de.hawhamburg.gka.lab01.algorithm.BreadthFirstStrategie;

public class BreadthFirstStrategieTest {

	private
	Pathfinder finder;
	
	@Before
	public
	void setUp () throws Exception {
		Graph<String, CustomEdge> graph = 
			new RegexGraphParser ().getGraphFromString (
				"xxx -> asdf;" +
				"asdf -> bla;" +
				"bla -> keks;" +
				"xxx -> hans;"
			);
		
		this.finder = new Pathfinder (
			graph,
			new BreadthFirstStrategie ()
		);
		
	}

	@After
	public void tearDown () throws Exception {
	}

	@Test
	public void testGetPath () {
		List<String> path = this.finder.find ("xxx", "keks");
		
		assertEquals (4, path.size ());
		assertArrayEquals (
			new String[] {"xxx", "asdf", "bla", "keks"},
			path.toArray (new String[0])
		);
	}

}
