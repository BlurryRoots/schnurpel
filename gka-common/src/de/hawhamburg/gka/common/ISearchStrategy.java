package de.hawhamburg.gka.common;

import java.util.List;

import org.jgrapht.Graph;

public
interface ISearchStrategy {
	List<String> getPath (Graph<String, CustomEdge> graph,
						  String source,
						  String target);
}
