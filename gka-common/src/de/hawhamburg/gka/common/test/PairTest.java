package de.hawhamburg.gka.common.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.hawhamburg.gka.common.Pair;

public class PairTest {

	private
	String one = "Hößstouhwer080234ljb#as+#asdf";
	private
	String two = "asdfjhwiiuhwef";
	private
	String three = "Hamburg";
	private
	String four = "Straßbourg";
	
	@Before
	public void setUp () throws Exception {
	}

	@After
	public void tearDown () throws Exception {
	}

	@Test
	public void testHashCode () {
		Pair a = new Pair (one, two);
		Pair b = new Pair (one, two);
		
		assertEquals (a.hashCode (), b.hashCode ());
	}
	
	@Test
	public void testHashCodeChanges () {
		Pair p = new Pair (four, three);
		
		for (int i = 0; i < 10000; ++i) {
			assertEquals (p.hashCode (), new Pair (four, three).hashCode ());			
		}		
	}
	
	@Test
	public void testHashCodeUnequal () {
		Pair a = new Pair (one, two);
		Pair b = new Pair (one, four);
		
		assertNotEquals (a.hashCode (), b.hashCode ());
	}

	@Test
	public void testEqualsObject () {
		Pair a = new Pair (four, two);
		Pair b = new Pair (four, two);
		
		assertTrue (a.equals (b));
	}

	@Test
	public void testNotEqualsObject () {
		Pair a = new Pair (four, two);
		Pair b = new Pair (four, one);
		
		assertFalse (a.equals (b));
	}
}
