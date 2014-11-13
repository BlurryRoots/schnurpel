package de.hawhamburg.gka.common;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.SimpleGraph;

public
class GraphParser {
	private
	String source;	
	private
	List<ExpressionParser> expressions;
	private
	Graph<String, CustomEdge> graph;
	
	public
	GraphParser (String source) {
		this.source = source;
		this.expressions = new ArrayList<ExpressionParser> ();
		
		this.parseExpressions ();
		this.interpretExpressions ();
	}
	
	private
	void parseExpressions () {
		int e = 0;
		StringBuilder exprBuilder = new StringBuilder ();
		
		for (int i = 0; i < this.source.length (); ++i) {
			char c = this.source.charAt (i);
			if (';' == c) {
				++e;
				
				ExpressionParser p = new ExpressionParser (
						exprBuilder.toString ().trim ()
				);
				this.expressions.add (p);
				
				exprBuilder = new StringBuilder ();
			}
			else {
				exprBuilder.append (c);
			}
		}
	}
	
	private
	void interpretExpressions () {
		if (this.expressions.isEmpty ()) {
			this.graph = null;
			
			return;
		}
		
		boolean isDirected = false;
		for (ExpressionParser p : this.expressions) {
			isDirected = p.hasDirection ();
			if (isDirected) {
				break;
			}
		}
		
		if (isDirected) {
			this.graph = new DefaultDirectedGraph<String, CustomEdge> (
				CustomEdge.class
			);			
		}
		else {
			this.graph = new SimpleGraph<String, CustomEdge>(
				CustomEdge.class
			);
		}
		
		for (ExpressionParser p : this.expressions) {
			if (! this.graph.containsVertex (p.getSource ())) {
				this.graph.addVertex (p.getSource ());
			}
			
			if (p.hasTarget ()) {
				if (! this.graph.containsVertex (p.getTarget ())) {
					this.graph.addVertex (p.getTarget ());
				}
				
				CustomEdge edge;				
				if (p.hasEdgeName () && p.hasEdgeWeight ()) {
					edge = new CustomEdge (
						p.getEdgeName (),
						p.getEdgeWeight ()
					);
				}
				else if (p.hasEdgeWeight ()) {
					edge = new CustomEdge (
						p.getEdgeWeight ()
					);
				}
				else if (p.hasEdgeName ()) {
					edge = new CustomEdge (
						p.getEdgeName ()
					);
				}
				else {
					edge = new CustomEdge ();
				}
				
				this.graph.addEdge (
					p.getSource (), 
					p.getTarget (),
					edge
				);
				
			}
		}
	}
	
	public
	Graph<String, CustomEdge> getGraph () {
		return this.graph;
	}
	
	/**
	 * a listenable directed multigraph that allows loops and parallel edges.
	 */
	private static class ListenableDirectedMultigraph<V, E>
		extends DefaultListenableGraph<V, E>
		implements DirectedGraph<V, E> {

		ListenableDirectedMultigraph(Class<E> edgeClass) {
			super(new DirectedMultigraph<V, E>(edgeClass));
		}
	}
}
