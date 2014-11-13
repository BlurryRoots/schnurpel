package de.hawhamburg.gka.lab01.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.ext.JGraphXAdapter;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;

import de.hawhamburg.gka.common.CustomEdge;
import de.hawhamburg.gka.common.Pathfinder;
import de.hawhamburg.gka.lab01.algorithm.BreadthFirstStrategie;

public
class FrameGUI
	extends JFrame {

	private final
	Graph<String, CustomEdge> graph;
	private 
	JGraphXAdapter<String, CustomEdge> jgxAdapter;
	private 
	String sourceVertex;
	private 
	String targetVertex;
	private
	JLabel status; 
	private
	JLabel sourceLabel;
	private
	JLabel targetLabel;

	public
	FrameGUI (Graph<String, CustomEdge> inGraph) {
		//super ("Super Path Finder 3000");
		
		this.graph = inGraph;
		
		this.jgxAdapter = new JGraphXAdapter<String, CustomEdge> (
			this.graph
		);
		this.jgxAdapter.setCellsEditable (false);
		if (this.graph instanceof UndirectedGraph) {
			// remove arrows
			this.jgxAdapter.setCellStyles (
				mxConstants.STYLE_ENDARROW,
				mxConstants.NONE,
				this.jgxAdapter.getChildEdges (
					this.jgxAdapter.getDefaultParent ()
				)
			);
		}		
		
		this.initComponents ();
	}

	public
	void initComponents () {
		//this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		this.setSize (1024, 768);

//		this.getContentPane ().setLayout (new FlowLayout ());
		this.getContentPane ().setLayout (new GridBagLayout ());

		final JPanel gui = new JPanel(new GridLayout(2, 3, 12, 12));
//		final JPanel gui = new JPanel(new BorderLayout(4,4));
		
		gui.setBorder (new TitledBorder("BorderLayout(5,5)"));
//		gui.setSize (1024, 768);
		
		mxGraphComponent component = new mxGraphComponent (this.jgxAdapter);
//		component.setSize (800, 600);
//		gui.add (component);
		this.getContentPane ().add (component, FlowLayout.LEFT);
		mxCircleLayout layout = new mxCircleLayout (this.jgxAdapter);		
		
		JButton startButton = new JButton ("Choose Source");
		startButton.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed (final ActionEvent e) {
				setSource ();
			}
		});
//		this.getContentPane ().add (startButton);
		gui.add (startButton, BorderLayout.SOUTH);
		
		this.sourceLabel = new JLabel ();
		this.sourceLabel.setText ("No source.");
		gui.add (this.sourceLabel);

		JButton endButton = new JButton ("Choose Target");
		endButton.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed (final ActionEvent e) {
				setTarget ();
			}
		});
//		this.getContentPane ().add (endButton);
		gui.add (endButton, BorderLayout.SOUTH);
		
		this.targetLabel = new JLabel ();
		this.targetLabel.setText ("No target.");
		gui.add (this.targetLabel);

		JButton runButton = new JButton ("Search Path");
		runButton.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed (final ActionEvent e) {
				search ();
			}
		});
//		this.getContentPane ().add (runButton);
		gui.add (runButton, BorderLayout.SOUTH);
		
		this.status = new JLabel ();
		this.status.setText ("Waiting for input ...");
		gui.add (this.status);

		JButton clearButton = new JButton ("Clear");
		runButton.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed (final ActionEvent e) {
				clear ();
			}
		});
//		this.getContentPane ().add (runButton);
		gui.add (clearButton, BorderLayout.SOUTH);
		
		this.getContentPane ().add (gui);
//		this.pack ();
		layout.execute (this.jgxAdapter.getDefaultParent ());
	}

	private
	void setSource () {
		if (jgxAdapter.getSelectionCell () != null) {
			mxCell cell = (mxCell) jgxAdapter.getSelectionCell ();
			if (cell.isEdge ()) {
				this.showErrorMessage ("Whoopsie", "Please choose a vertex!");
			}
			else {
				this.sourceVertex = (String) cell.getValue ();
				System.out.println ("Setting " + this.sourceVertex + " as start!");	
				this.sourceLabel.setText (this.sourceVertex);
			}
		}
	}

	private
	void setTarget () {
		if (jgxAdapter.getSelectionCell () != null) {
			mxCell cell = (mxCell) jgxAdapter.getSelectionCell ();
			if (cell.isEdge ()) {
				this.showErrorMessage ("Whoopsie", "Please choose a vertex!");
			}
			else {
				this.targetVertex = (String) cell.getValue ();
				System.out.println ("Setting " + this.targetVertex + " as end!");
				this.targetLabel.setText (this.targetVertex);
			}
		}
	}

	private
	void search () {
		if (null == this.sourceVertex) {
			this.showErrorMessage ("Whoopsi!", "No Source specified!");
			return;
		}
		
		if (null == this.targetVertex) {
			this.showErrorMessage ("Whoopsi!", "No Target specified!");
			return;
		}
		
		Pathfinder finder = new Pathfinder (this.graph,
				new BreadthFirstStrategie ());

		List<String> path = finder.find (this.sourceVertex, this.targetVertex);
		if (path.size () == 0) {
			this.showErrorMessage (
				"Error",
				"No path found."
			);
			
			return;
		}

		for (int i = 0; i < path.size (); i++) {
			String start = path.get (i);
			setVertexColor (start);
			if (i + 1 < path.size ()) {
				String end = path.get (i + 1);
				setEdgeColor (start, end);
				System.out.println ("Color " + start + " to " + end);
			}
		}
		
		this.showInfoMessage (
			"Success",
			"Found path with" + (path.size () - 1) + " edges."
		);
	}
	
	private
	void clear () {
		this.resetColors ();
		this.sourceLabel.setText ("No source.");
		this.sourceVertex = null;
		this.targetLabel.setText ("No target.");
		this.targetVertex = null;
	}

	private
	void setEdgeColor (String source, String target) {
		this.jgxAdapter.setCellStyles (
			mxConstants.STYLE_STROKECOLOR,
			"#420023",
			new Object[] { 
				this.jgxAdapter.getEdgeToCellMap ().get (
					this.graph.getEdge (source, target)
				)
			}
		);
	}

	private
	void setVertexColor (String vertex) {
		this.jgxAdapter.setCellStyles (
			mxConstants.STYLE_STROKECOLOR,
			"#230042",
			new Object[] { 
				this.jgxAdapter.getVertexToCellMap ().get (vertex)
			}
		);
	}
	
	private
	void showInfoMessage (String title, String text) {
//		JOptionPane.showMessageDialog (
//			this, text, title,
//			JOptionPane.INFORMATION_MESSAGE
//		);
		this.status.setText (text);
	}
	
	private
	void showErrorMessage (String title, String text) {
//		JOptionPane.showMessageDialog (
//			this, text, title,
//			JOptionPane.ERROR_MESSAGE
//		);
		this.status.setText (text);
	}

	private
	void resetColors () {
	}
}