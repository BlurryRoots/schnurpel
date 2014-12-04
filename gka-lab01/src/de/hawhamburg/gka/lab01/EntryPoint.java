package de.hawhamburg.gka.lab01;

import de.hawhamburg.gka.common.GraphParser;
import de.hawhamburg.gka.common.ParserBuilder;
import de.hawhamburg.gka.lab01.gui.GraphView;

public
class EntryPoint {
	public static
	void main (String[] args) {
		//JGraphAdapterDemo demo = new JGraphAdapterDemo ();
		
//		Demo.start (args);
        

//		doMagic ("graph1.gka");
//		doMagic ("graph2.gka");
//		doMagic ("graph3.gka");
//		doMagic ("graph4.gka");
//		doMagic ("graph5.gka");
//		doMagic ("graph6.gka");
		doMagic ("graph7.gka");
//		doMagic ("graph8.gka");
//		doMagic ("graphRotz.gka");
	}

	private static
	void doMagic (String filename) {
		System.out.println ("---" + filename + "---");
		GraphParser parser = ParserBuilder.createFromResourceFile (filename);		
		System.out.println (parser.getGraph ().toString ());
		
//		FrameGUI w = new FrameGUI (parser.getGraph ());
//		w.setVisible (true);
		
		GraphView view = new GraphView (parser.getGraph ());
		view.show ();
		
//		Demo.start (parser.getGraph ());
//		JGraphAdapterDemo.start (parser.getGraph ());
//		HelloWorld.start (null);
	}
}
