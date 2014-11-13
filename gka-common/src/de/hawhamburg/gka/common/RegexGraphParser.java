package de.hawhamburg.gka.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.SimpleGraph;

public
class RegexGraphParser {
	
	public static final
	Pattern ExpressionPattern = Pattern.compile(
		"([a-zA-ZöäüÖÄÜß0-9]+)" +
		"(([ ]*-(>|-)[ ]*([a-zA-ZöäüÖÄÜß0-9]+)[ ]?" +
		"(\\(([a-zA-ZöäüÖÄÜß0-9]+)\\))?)?[ ]?" +
		"([ ]*:[ ]*([0-9]+))?)?;"
	);
	
	public
	RegexGraphParser () {
		
	}
	
	private
	void addEdge (Matcher matcher, Graph<String, CustomEdge> graph) {
		String source = matcher.group (1);		
		String edgeType = matcher.group(4);
		String target = matcher.group(5);
		String name = matcher.group(7);
		String weight = matcher.group(9);
		
		if (null == source) {
			throw new RuntimeException ("NoNo!");
		}
		
		if (! graph.containsVertex (source)) {
			graph.addVertex (source);
		}
		
		if (null != edgeType && null != target) {
			if (! graph.containsVertex (target)) {
				graph.addVertex (target);
			}

			CustomEdge edge;
			boolean hasName = null != name;
			boolean hasWeight = null != weight;
			
			if (hasName && hasWeight) {
				edge = new CustomEdge (
					name,
					Integer.valueOf (weight)
				);
			}
			else if (hasWeight) {
				edge = new CustomEdge (
					Integer.valueOf (weight)
				);
			}
			else if (hasName) {
				edge = new CustomEdge (name);
			}
			else {
				edge = new CustomEdge ();
			}
			
			graph.addEdge (
				source, 
				target,
				edge
			);
			
		}
	}
	
	public
	Graph<String, CustomEdge> getGraphFromString (String source) {
		Matcher matcher =
			RegexGraphParser.ExpressionPattern.matcher (source);
		
		boolean empty = true;
		while (matcher.find ()) {
			empty = false;
		}
		
		if (empty) {
			return null;
		}
		
		matcher.reset ();
		
		Graph<String, CustomEdge> graph = null;
		if (source.contains("->")) {
			graph = new DefaultDirectedGraph<String, CustomEdge> (
				CustomEdge.class
			);
		}
		else {
			graph = new SimpleGraph<String, CustomEdge>(
				CustomEdge.class
			);
		}		
		
		while (matcher.find ()) {
			if (0 == matcher.groupCount ()) {
				graph = null;
				break;
			}
			
			this.addEdge (matcher, graph);
		}
		
		return graph;
	}
	
}
