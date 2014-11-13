package de.hawhamburg.gka.common;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;

public
interface IPathfinder {
	GraphPath<String, CustomEdge> getPath (Graph<String, CustomEdge> graph, String source, String target);
}
