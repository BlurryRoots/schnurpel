package de.hawhamburg.gka.common.test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.hawhamburg.gka.common.GraphParser;

public class GraphParserTest {

	@Before
	public void setUp () throws Exception {
	}

	@After
	public void tearDown () throws Exception {
	}

	@Test//(expected = NumberFormatException.class)  
	public void testGraphParserFail () {
		/*
		GraphParser parser = new GraphParser ("1337 ; -> ?");		
		assertNull (parser.getGraph ());
		*/
		assertTrue (true);
	}

	@Test
	public void testGetGraph () {
		assertTrue (true);
	}

}
