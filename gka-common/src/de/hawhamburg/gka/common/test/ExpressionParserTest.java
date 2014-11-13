package de.hawhamburg.gka.common.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.hawhamburg.gka.common.ExpressionParser;

public class ExpressionParserTest {
	
	private
	ExpressionParser parser;	
	
	@Before
	public
	void setUp () throws Exception {
		this.parser = new ExpressionParser ("hans -> wurst");
	}

	@After
	public
	void tearDown () throws Exception {
	}

	@Test
	public void testGetSource () {
		assertEquals ("hans", this.parser.getSource ());
	}

	@Test
	public void testHasTarget () {
		assertEquals (true, this.parser.hasTarget ());
	}

	@Test
	public void testGetTarget () {
		assertEquals ("wurst", this.parser.getTarget ());
	}

	@Test
	public void testHasEdgeName () {
		assertEquals (false, this.parser.hasEdgeName ());
	}

	@Test
	public void testGetEdgeName () {
		assertEquals ("", this.parser.getEdgeName ());
	}

	@Test
	public void testHasEdgeWeight () {
		assertEquals (false, this.parser.hasEdgeWeight ());
	}

	@Test
	public void testGetEdgeWeight () {
		assertEquals (0, this.parser.getEdgeWeight ());
	}

	@Test
	public void testHasDirection () {
		assertEquals (true, this.parser.hasDirection ());
	}

}
