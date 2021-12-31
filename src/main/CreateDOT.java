//$Id$
package main;

import java.io.FileWriter;

public class CreateDOT {
	
	/**
	 * To create the DOT file */
	public static void createDOTFile() throws Exception {
		
		if(Main.familyTree == null) {
			System.out.println("Data is not loaded");
			return;
		}
		
		FileWriter fWriter = new FileWriter("familytree.dot");
		
		fWriter.write("digraph familyTree {\n");
		fWriter.write("rankdir=LR;\n");
		//fWriter.write("size=\"10,20\"\n");
		fWriter.write("node [shape = rectangle] [color=black] [width=2];\n");
		
		fWriter.write(Main.familyTree.getGraphContent());
		
		fWriter.write("}");
		fWriter.close();
		
		System.out.println("DOT file created");
	}

}
