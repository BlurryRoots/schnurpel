package de.hawhamburg.gka.lab03;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.jgrapht.Graph;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.Matrix;

public
class EdmondsKarpFlow {
	
	private
	Graph<String, CustomEdge> graph;
	private
	Matrix<Integer> residualGraph;
	private
	List<String> vertecies;
	private
	int parent[];
	private
	int maxFlow;
	
	public
	EdmondsKarpFlow (Graph<String, CustomEdge> graph) {
		this.graph = graph;
	}
	
	private
	void init () {
	    // Create a residual graph and fill the residual graph with
//	    // given capacities in the original graph as residual capacities
//	    // in residual graph
//	    int rGraph[V][V]; // Residual graph where rGraph[i][j] indicates 
//	                     // residual capacity of edge from i to j (if there
//	                     // is an edge. If rGraph[i][j] is 0, then there is not)
		this.vertecies = new ArrayList<> (graph.vertexSet ());
		this.residualGraph = new Matrix<Integer> (vertecies.size (), 0);
//	    for (u = 0; u < V; u++)
//	        for (v = 0; v < V; v++)
//	             rGraph[u][v] = graph[u][v];
		for (CustomEdge edge : graph.edgeSet ()) {
			int row = vertecies.indexOf (edge.getSource ());
			int column = vertecies.indexOf (edge.getTarget ());
			this.residualGraph.insert (row, column, edge.getCost ());
		}
//
//	    int parent[V];  // This array is filled by BFS and to store path
		this.parent = new int[vertecies.size ()];
//
//	    int max_flow = 0;  // There is no flow initially
		this.maxFlow = 0;	
	}
	
//
//	/* Returns true if there is a path from source 's' to sink 't' in
//	  residual graph. Also fills parent[] to store the path */
//	bool bfs(int rGraph[V][V], int s, int t, int parent[])
	private
	boolean hasPath (String source, String target) {
//	{
//	    // Create a visited array and mark all vertices as not visited
//	    bool visited[V];
		boolean visited[] = new boolean[this.vertecies.size ()];
//	    memset(visited, 0, sizeof(visited));
		for (int i = 0; i < this.vertecies.size (); ++i) {
			visited[i] = false;
		}
//
//	    // Create a queue, enqueue source vertex and mark source vertex
//	    // as visited
//	    queue <int> q;
		Queue<Integer> queue = new ConcurrentLinkedQueue<> ();
		int sourceIndex = this.vertecies.indexOf (source); 
		int targetIndex = this.vertecies.indexOf (target);
		int vertexCount = this.vertecies.size ();
//	    q.push(s);
		queue.offer (sourceIndex);
//	    visited[s] = true;
		visited[sourceIndex] = true;
//	    parent[s] = -1;
		this.parent[sourceIndex] = -1;
//
//	    // Standard BFS Loop
//	    while (!q.empty())
		while (! queue.isEmpty ()) {
//	    {
//	        int u = q.front();
//	        q.pop();
			int u = queue.poll ();
//
//	        for (int v=0; v<V; v++)
			for (int v = 0; v < vertexCount; ++v) {
//	        {
//	            if (visited[v]==false && rGraph[u][v] > 0)
				if (! visited[v] && this.residualGraph.retrieve (u, v) > 0) {
//	            {
//	                q.push(v);
					queue.offer (v);
//	                parent[v] = u;
					this.parent[v] = u;
//	                visited[v] = true;
					visited[v] = true;
//	            }
				}
//	        }
			}
//	    }
		}
//
//	    // If we reached sink in BFS starting from source, then return
//	    // true, else false
//	    return (visited[t] == true);
//	}
		return visited[targetIndex];
	}
//
//	// Returns tne maximum flow from s to t in the given graph
//	int fordFulkerson(int graph[V][V], int s, int t)
	public
	int maxFlow (String source, String target) {
//
		this.init ();
		
		int sourceIndex = this.vertecies.indexOf (source);
		int targetIndex = this.vertecies.indexOf (target);
		
//	    // Augment the flow while tere is path from source to sink
//	    while (bfs(rGraph, s, t, parent))
		while (this.hasPath (source, target)) {
//	    {
//	        // Find minimum residual capacity of the edhes along the
//	        // path filled by BFS. Or we can say find the maximum flow
//	        // through the path found.
//	        int path_flow = INT_MAX;
			int pathFlow = Integer.MAX_VALUE;
			int u, v;
			
			for (v = targetIndex; v != sourceIndex; v=this.parent[v]) {
//	        for (v=t; v!=s; v=parent[v])
//	        {
//	            u = parent[v];
				u = parent[v];
//	            path_flow = min(path_flow, rGraph[u][v]);
				pathFlow = Math.min (pathFlow, this.residualGraph.retrieve (u, v));
//	        }
			}
//
//	        // update residual capacities of the edges and reverse edges
//	        // along the path
//	        for (v=t; v != s; v=parent[v])
			for (v = targetIndex; v != sourceIndex; v = parent[v]) {
//	        {
//	            u = parent[v];
				u = parent[v];
//	            rGraph[u][v] -= path_flow;
				this.residualGraph.insert (u, v, this.residualGraph.retrieve (u, v) - pathFlow);
//	            rGraph[v][u] += path_flow;
				this.residualGraph.insert (v, u, this.residualGraph.retrieve (v, u) + pathFlow);
//	        }
			}
//
//	        // Add path flow to overall flow
//	        max_flow += path_flow;
			this.maxFlow += pathFlow;
//	    }
		}
//
//	    // Return the overall flow
//	    return max_flow;
		return this.maxFlow;
//	}
	}
}
