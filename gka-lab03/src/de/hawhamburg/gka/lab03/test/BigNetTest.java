package de.hawhamburg.gka.lab03.test;

import org.jgrapht.Graph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.lab02.GraphGenerator;
import de.hawhamburg.gka.lab03.EdmondKarpFlowAnalyser;
import de.hawhamburg.gka.lab03.FlowAnalyser;

public class BigNetTest {
	private static final
	int INTERESTING_SEED = 143;
	
	@Before
	public void setUp () throws Exception {
	}

	@After
	public void tearDown () throws Exception {
	}

	@Test
	public void test () {
		int seed = INTERESTING_SEED;
		System.out.println ("with seed " + seed);
		GraphGenerator gen = new GraphGenerator (seed);
		Graph<String, CustomEdge> graph = gen.generateNet (10, 6, 1337, 42);
		
		System.out.println (graph);
		
		FlowAnalyser a = new EdmondKarpFlowAnalyser (graph);
		int flow = a.maxFlow ("q", "s");
		System.out.println ("flow is " + flow);
	}

}
