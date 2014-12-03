package de.hawhamburg.gka.lab03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.SimpleGraph;

import de.hawhamburg.gka.common.CustomEdge;

public
class FordFulkerson {
	
//	class Edge(object):
	private static
	class FlowEdge
	extends CustomEdge {
//	    def __init__(self, u, v, w):
//	        self.source = u
//	        self.sink = v  
//	        self.capacity = w
		private
		FlowEdge residualEdge;
		
		public
		FlowEdge () {
		}
		
		public
		void setResidualEdge (FlowEdge residualEdge) {
			this.residualEdge = residualEdge;
		}
		
		public
		FlowEdge getResidualEdge () {
			return this.residualEdge;
		}
		
		public
		int getCapacitiy () {
			return this.getCost ();
		}
	}
		
	private
	Graph<String, FlowEdge> graph;
	
	private
	Map<CustomEdge, Integer> flowMap;
	private
	Map<CustomEdge, Integer> reflowMap;
	
	public
	FordFulkerson (Graph<String, CustomEdge> graph) {
		if (graph instanceof DefaultDirectedGraph) {
			this.graph = new DefaultDirectedGraph<String, FlowEdge> (FlowEdge.class);
		}
		else {
			throw new RuntimeException ("Flow analytics can only be done on directed graphs!");
		}
		
		for (String vertex : graph.vertexSet ()) {
			this.graph.addVertex (vertex);
		}
		
		for (CustomEdge edge : graph.edgeSet ()) {
			FlowEdge flowEdge = new FlowEdge ();
			FlowEdge resudialFlowEdge = new FlowEdge ();
			
			flowEdge.setResidualEdge (resudialFlowEdge);
			this.graph.addEdge (edge.getSource (), edge.getTarget (), flowEdge);
			resudialFlowEdge.setResidualEdge (flowEdge);
			this.graph.addEdge (edge.getTarget (), edge.getSource (), resudialFlowEdge);
		}
	}
	
	private
	List<Integer> calculateResiduals (List<String> path) {
//        residuals = [edge.capacity - self.flow[edge] for edge in path]
//      residuals = [edge.capacity - self.flow[edge] for edge in path]
		List<String> queue = new LinkedList<> (path);
		return null;
	}
	
	private
	List<String> findPath (String source, String sink, List<String> path) {
//		def find_path(self, source, sink, path):
		List<String> result = null;
//	        if source == sink:
		if (source.equals (sink)) {
//            return path
			return path;
		}
//	        for edge in self.get_edges(source):
		for (CustomEdge edge : this.graph.edgesOf (source)) {
//	        residuals = [edge.capacity - self.flow[edge] for edge in path]
//	            residual = edge.capacity - self.flow[edge]
//	            if residual > 0 and edge not in path:
//	                result = self.find_path( edge.sink, sink, path + [edge]) 
//	                if result != None:
//	                    return result
		}
		
		return result;
	}
	
	public
	int getMaxFlow (String source, String sink) {
//		def max_flow(self, source, sink):
		
		// TODO: init flow map ??
		
		List<Integer> residuals = new ArrayList<> (); 
		List<String> path = new LinkedList<> ();
//        path = self.find_path(source, sink, [])
		path = this.findPath (source, sink, path);
//	        while path != None:
		while (null != path) {
//	            residuals = [edge.capacity - self.flow[edge] for edge in path]
//	            flow = min(residuals)
//	            for edge in path:
//	                self.flow[edge] += flow
//	                self.flow[edge.redge] -= flow
//	            path = self.find_path(source, sink, [])
		}
//	        return sum(self.flow[edge] for edge in self.get_edges(source))
		return 1;
	}
	
}
