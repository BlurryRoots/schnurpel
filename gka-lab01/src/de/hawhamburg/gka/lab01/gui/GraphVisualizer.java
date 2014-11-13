package de.hawhamburg.gka.lab01.gui;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DirectedMultigraph;

import de.hawhamburg.gka.common.CustomEdge;

public
class GraphVisualizer {
	private
	Graph <String, CustomEdge> graph;
	
	public
	GraphVisualizer (Graph <String, CustomEdge> graph) {
		this.graph = graph;
	}
	
	/**
	 * a listenable directed multigraph that allows loops and parallel edges.
	 */
//	private static class ListenableUndirectedMultigraph<V, E>
//		extends DefaultListenableGraph<V, E>
//		implements UndirectedGraph<V, E>
//	{
//		private static final long serialVersionUID = 1L;
//
//		ListenableUndirectedMultigraph(Class<E> edgeClass)
//		{
//			super(new UndirectedMultigraph<V, E>(edgeClass));
//			org.jgrapht.graph.ListenableUndirectedWeightedGraph<V, E>
//		}
//	}
	
	/**
	 * a listenable directed multigraph that allows loops and parallel edges.
	 */
	private static class ListenableDirectedMultigraph<V, E>
		extends DefaultListenableGraph<V, E>
		implements DirectedGraph<V, E>
	{
		private static final long serialVersionUID = 1L;

		ListenableDirectedMultigraph(Class<E> edgeClass)
		{
			super(new DirectedMultigraph<V, E>(edgeClass));
		}
	}
}
