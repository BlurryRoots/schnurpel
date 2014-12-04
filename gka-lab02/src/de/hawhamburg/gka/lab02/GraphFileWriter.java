package de.hawhamburg.gka.lab02;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;

import de.hawhamburg.gka.common.CustomEdge;

public
class GraphFileWriter {
	
	private
	FileWriter fwriter;
	
	public
	GraphFileWriter (File outfile)
		throws IOException {
		this.fwriter = new FileWriter (outfile);
	}
	
	public
	void write (Graph<String, CustomEdge> graph)
		throws IOException {
		
		boolean directed = graph instanceof DefaultDirectedGraph;
		
		for (CustomEdge edge : graph.edgeSet ()) {			
			StringBuilder builder = new StringBuilder ();
			
			builder.append (edge.getSource ());
			
			if (directed) {
				builder.append (" -> ");
			}
			else {
				builder.append (" -- ");
			}
			
			if (null != edge.getTarget ()) {
				builder.append (edge.getTarget ());
			}
			
			if (! "NONAME".equals (edge.getName ())) {
				builder.append (" (");
				builder.append (edge.getName ());
				builder.append (") ");
			}
			
			builder.append (" : ");
			builder.append (edge.getCost ());
			builder.append (";");
			
			builder.append ("\n");
			this.fwriter.write (builder.toString ());
		}
	}
	
	public
	void close ()
		throws IOException {
		this.fwriter.close ();
	}
}
