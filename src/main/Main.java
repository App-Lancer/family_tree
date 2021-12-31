//$Id$
package main;

import java.util.Scanner;

/**
 * Main driver class to run the program
 * It shows the menu of options available for the program*/
public class Main {
	
	public static FamilyTree familyTree;
	
	public static void main(String args[]) throws Exception {
		
		while(true) {
			System.out.println("---Main Menu---");
			System.out.println("1. Read file and load in memory");
			System.out.println("2. Write a file in lexical order of family");
			System.out.println("3. Find relationship");
			System.out.println("4. Create DOT file");
			System.out.println("5. Quit");
			
			System.out.println();
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter your option : ");
			int choosenOption = scan.nextInt();
			
			if(choosenOption == 1) {
				CSVFileRead.readCSVFileAndLoadTree(scan);
			}else if(choosenOption == 2) {
				WriteName.writeFileInLexicalOrder();
			}else if(choosenOption == 3) {
				scan.nextLine();
				FindRelationship.findRelationShip(scan);
			}else if(choosenOption == 4) {
				CreateDOT.createDOTFile();
			}else if(choosenOption == 5) {
				System.out.println("Quitting the game");
				break;
			}
		}
	}

}
