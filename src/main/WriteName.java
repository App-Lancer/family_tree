//$Id$
package main;

import java.io.FileWriter;
import java.util.TreeSet;

public class WriteName {

	/**
	 * To write the content of family names and gender value into the file*/
	public static void writeFileInLexicalOrder() throws Exception {
		if(Main.familyTree != null) {
			
			TreeSet<String> familyNames = Main.familyTree.getAllNames();
			String value = "";
			
			for(String name : familyNames) {
				Person person = Main.familyTree.getPerson(name);
				if(person != null) {
					value += name + " " + person.getGender() + "\n";
				}
			}
			
			FileWriter fWriter = new FileWriter("familynames.txt");
			fWriter.write(value);
			
			fWriter.close();
			System.out.println("File successfully written");
		}else {
			System.out.println("Data is not loaded");
		}
	}
}
