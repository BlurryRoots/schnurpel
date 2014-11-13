package de.hawhamburg.gka.lab01.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.ext.JGraphXAdapter;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;

import de.hawhamburg.gka.common.CustomEdge;

public
class GraphView
implements MouseListener {

	private
	JFrame frame;
	
	private
	Graph<String, CustomEdge> graph;
	
	private
	JGraphXAdapter<String, CustomEdge> jgxAdapter;
	
	private
	mxGraphComponent graphComponent;

	public
	GraphView (Graph<String, CustomEdge> graph) {
		this.graph = graph;
		
		this.frame = new JFrame ();
		this.frame.setSize (800, 600);
		
		//this.frame.getContentPane ().setLayout (new GridBagLayout ());
		this.frame.getContentPane ().setLayout (new FlowLayout ());
		//final JPanel gui = new JPanel(new GridLayout(2, 3, 12, 12));
		//final JPanel gui = new JPanel(new FlowLayout ());
		//final JPanel gui = new JPanel();
		//this.frame.getContentPane ().add (gui);

		JTextField sourceField = new JTextField ();
		sourceField.setName ("source");
		sourceField.setSize (200, 32);
		this.frame.getContentPane ().add (sourceField);
		sourceField.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) {
				System.out.println ("Action Man !!!");
			}			
		});
		
		JButton startButton = new JButton ("Find Path");
		this.frame.getContentPane ().add (startButton);
		startButton.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed (final ActionEvent e) {
				// STUFF !!!
			}
		});

		this.jgxAdapter = new JGraphXAdapter<String, CustomEdge> (graph);
		this.jgxAdapter.setCellsEditable (false);
		if (graph instanceof UndirectedGraph) {
			// remove arrows
			this.jgxAdapter.setCellStyles (mxConstants.STYLE_ENDARROW,
				mxConstants.NONE,
				this.jgxAdapter.getChildEdges (
					this.jgxAdapter.getDefaultParent ()
				)
			);
		}

		this.graphComponent = new mxGraphComponent (this.jgxAdapter);
		this.frame.getContentPane ().add (this.graphComponent);

		//this.frame.pack ();
	}

	public void show () {
		this.frame.setVisible (true);

		mxCircleLayout layout = new mxCircleLayout (this.jgxAdapter);
		layout.setRadius (this.frame.getWidth () * 0.5f);
		layout.execute (this.jgxAdapter.getDefaultParent ());
	}

	@Override
	public void mouseClicked (MouseEvent e) {
	}

	@Override
	public void mouseEntered (MouseEvent e) {
	}

	@Override
	public void mouseExited (MouseEvent e) {
	}

	@Override
	public void mousePressed (MouseEvent e) {
	}

	@Override
	public void mouseReleased (MouseEvent e) {
		Object cell = this.graphComponent.getCellAt (e.getX (), e.getY ());

		if (cell != null) {
			//System.out.println ("cell=" + this.graph.getLabel (cell));
			System.out.println ("cell=" + this.jgxAdapter.getLabel (cell));			
		}
	}
}
