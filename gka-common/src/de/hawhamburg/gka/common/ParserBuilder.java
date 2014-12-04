package de.hawhamburg.gka.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ParserBuilder {	
	public static
	GraphParser createFromFile (String filename) {
		GraphParser parser = null;
		
		File fileDir = new File (filename);
		
		try {			 
			BufferedReader in = new BufferedReader (
				new InputStreamReader (
					new FileInputStream (fileDir),
					"UTF8"
				)
			);
	 
			String lineBuffer;
			StringBuilder builder;			
			boolean run;
			
			builder = new StringBuilder ();
			run = true;
			while (null != (lineBuffer = in.readLine())) {
				builder.append (lineBuffer);
				builder.append (" ");
			}
 
            in.close ();

            parser = new GraphParser (builder.toString ());
	    } 
	    catch (IOException  e) {
			System.err.println (e.getMessage ());
	    }
	    catch (Exception e)
	    {
	    	System.err.println ("Something wicked happend!");
			System.err.println (e.getMessage ());
	    }
		
		return parser;
	}
	
	public static
	GraphParser createFromResourceFile (String filename) {		
		URL fileUrl = ParserBuilder.class.getResource("/resources/graphs/" + filename);
		
		return createFromFile (fileUrl.getFile ());
	}
}
