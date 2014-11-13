package de.hawhamburg.gka.lab02;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.FloydWarshallShortestPaths;
import org.jgrapht.util.VertexPair;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.internal.Lists;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.GraphParser;
import de.hawhamburg.gka.common.ParserBuilder;

public
class EntryPoint {

	public static
	class Options {
	    @Parameter
	    public List<String> parameters = Lists.newArrayList();

	    @Parameter(names = { "-f", "--file" }, description = "Name of the graph file to load")
	    public String filename;

	    @Parameter(names = { "-s", "--source" }, description = "Start node")
	    public String source;
	    
	    @Parameter(names = { "-t", "--target" }, description = "Target node")
	    public String target;
	}
	
	public static
	void main (String[] args) {
		Options o = new Options ();
		JCommander commander = new JCommander (o, args);
		
		// first argument is file name
		// second argument is a pair of notes to analyze
		String filename = o.filename;
		System.out.println ("Analyzing path from " + filename);
		GraphParser parser = ParserBuilder.createFromFile (filename);
		Graph<String, CustomEdge> graph = parser.getGraph (); 
		System.out.println (graph);

		//
//		FloydWarshall s = new FloydWarshall ();
//		DijkstraAlternative s = new DijkstraAlternative ();
		DijkstraFinal s = new DijkstraFinal ();
		GraphPath<String, CustomEdge> spath = s.getPath (graph, o.source, o.target);
		System.out.println ("My path: " + spath);
	}

}
