//$Id$
package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class CSVFileRead {

	/**
	 * To read the input CSV file and create the Family tree
	 * @param scan - Scanner object to read content*/
	public static void readCSVFileAndLoadTree(Scanner scan) throws Exception {
		System.out.println("Enter file name : ");
		String fileName = scan.next();
		
		if(!fileName.endsWith(".csv")) {
			System.out.println("invalid file format");
			return;
		}
		
		File fileObj = new File(fileName);
		
		if(fileObj.exists()) {
			String value = "";
			
			BufferedReader bufReader = new BufferedReader(new FileReader(fileName));
			int i=0;
			while((value = bufReader.readLine()) != null) {
				String row[] = value.split(",");
				
				if(row.length == 2) {
					Person newPerson = new Person(row[0], row[1]);
					
					if(i==0) {
						Main.familyTree = new FamilyTree(newPerson);
					}else {
						FamilyTree.tempPersons.put(newPerson.getName(), newPerson);
						System.out.println(FamilyTree.tempPersons.size() + " " + newPerson.getName());
					}
				}else if(row.length == 3) {
					Main.familyTree.addPersonToTree(row[0], row[1], row[2]);
				}
				i++;
			}
			
			bufReader.close();
			
			System.out.println("Input file successfully loaded");
		}else {
			System.out.println("File not present");
		}
	}
}
