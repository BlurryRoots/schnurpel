package de.hawhamburg.gka.lab02;

import java.io.File;
import java.io.IOException;

import org.jgrapht.Graph;

import de.hawhamburg.gka.common.CustomEdge;

public
class BigGraphWriter {
	private static
	void writeGraphToFile (Graph<String, CustomEdge> graph, String outfile) {
		try {
			GraphFileWriter writer = new GraphFileWriter (new File (outfile));
			writer.write (graph);
			writer.close ();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static
	void main (String args[]) {
		GraphGenerator g = new GraphGenerator (4242);
		
		writeGraphToFile (g.generateDirected (800, 300000, 1337, 42), "/tmp/800.gka");
		writeGraphToFile (g.generateDirected (2500, 2000000, 1337, 42), "/tmp/2500.gka");
	}
}
