/* This program and the accompanying materials are dual-licensed under
 * either
 *
 * (a) the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation, or (at your option) any
 * later version.
 *
 * or (per the licensee's choosing)
 *
 * (b) the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation.
 */
package de.hawhamburg.gka.lab01.gui;

import java.awt.Dimension;

import javax.swing.JApplet;
import javax.swing.JFrame;

import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.view.mxGraph;

import de.hawhamburg.gka.common.CustomEdge;


/**
 * A demo applet that shows how to use JGraphX to visualize JGraphT graphs.
 * Applet based on JGraphAdapterDemo.
 *
 * @since July 9, 2013
 */
public class Demo
    extends JApplet
{
    

    private static final long serialVersionUID = 2202072534703043194L;
    private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);

    

    private JGraphXAdapter<String, CustomEdge> jgxAdapter;

    

    /**
     * An alternative starting point for this demo, to also allow running this
     * applet as an application.
     *
     * @param args ignored.
     */
    public static void start (Graph <String, CustomEdge> graph)
    {
        JGraphAdapterDemo applet = new JGraphAdapterDemo();
        applet.init (graph);

        JFrame frame = new JFrame();
        frame.getContentPane().add(applet);
        frame.setTitle("Hans im Glück");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    public void init (Graph <String, CustomEdge> graph)
    {
        // create a JGraphT graph
//        ListenableGraph<String, DefaultEdge> g =
//            new ListenableDirectedGraph<String, DefaultEdge>(
//                DefaultEdge.class);
//    	ListenableGraph<String, NamedWeightedEdge> g =
//              new ListenableDirectedGraph<String, NamedWeightedEdge>(
//                  NamedWeightedEdge.class);
    	
    	
        // create a visualization using JGraph, via an adapter
//        jgxAdapter = new JGraphXAdapter<String, DefaultEdge>(g);
//    	jgxAdapter = new JGraphXAdapter<String, NamedWeightedEdge> (graph);

//        getContentPane().add(new mxGraphComponent(jgxAdapter));
//        resize(DEFAULT_SIZE);

//        String v1 = "v1";
//        String v2 = "v2";
//        String v3 = "v3";
//        String v4 = "v4";

        // add some sample data (graph manipulated via JGraphX)
//        g.addVertex(v1);
//        g.addVertex(v2);
//        g.addVertex(v3);
//        g.addVertex(v4);

//        g.addEdge(v1, v2);
//        g.addEdge(v2, v3);
//        g.addEdge(v3, v1);
//        g.addEdge(v4, v3);

        // positioning via jgraphx layouts
//        mxCircleLayout layout = new mxCircleLayout(jgxAdapter);
//        mxCompactTreeLayout layout =  mxCompactTreeLayout (jgxAdapter);
    	mxGraph g = new JGraphXAdapter<String, CustomEdge> (graph);
//    	mxGraph g = new mxGraph ();
//        mxOrganicLayout layout = mxOrganicLayout (g);
    	
    	new mxCircleLayout(g).execute(g.getDefaultParent ());        
        new mxParallelEdgeLayout(g, 50).execute(g.getDefaultParent());

        // that's all there is to it!...
    }
}

//End JGraphXAdapterDemo.java
