//$Id$
package main;

import java.util.Scanner;

public class FindRelationship {
	
	/**
	 * Basic method to findRelationship method
	 * @param scan - scanner object to read the data
	 * The method prints the relationship if exist*/
	public static void findRelationShip(Scanner scan) {
		
		if(Main.familyTree == null) {
			System.out.println("Data is not loaded");
			return;
		}
		
		String firstPerson, secondPerson;
		System.out.println("Enter first person value : ");
		firstPerson = scan.nextLine();
		System.out.println("Enter second person value : ");
		secondPerson = scan.nextLine();
		
		if(isFather(firstPerson, secondPerson)) {
			System.out.println("Father");
		}else if(isMother(firstPerson, secondPerson)) {
			System.out.println("Mother");
		}else if(isSon(firstPerson, secondPerson)){
			System.out.println("Son");
		}else if(isDaughter(firstPerson, secondPerson)){
			System.out.println("Daughter");
		}else if(isHusband(firstPerson, secondPerson)){
			System.out.println("Husband");
		}else if(isWife(firstPerson, secondPerson)){
			System.out.println("Wife");
		}else if(isBrother(firstPerson, secondPerson)){
			System.out.println("Brother");
		}else if(isSister(firstPerson, secondPerson)){
			System.out.println("Sister");
		}else if(isCousins(firstPerson, secondPerson)){
			System.out.println("Cousins");
		}else if(isGrandFather(firstPerson, secondPerson)){
			System.out.println("Grand father");
		}else if(isGrandMother(firstPerson, secondPerson)){
			System.out.println("Grand mother");
		}else if(isGrandSon(firstPerson, secondPerson)){
			System.out.println("Grand son");
		}else if(isGrandDaughter(firstPerson, secondPerson)){
			System.out.println("Grand daughter");
		}else if(isNephew(firstPerson, secondPerson)){
			System.out.println("Nephew");
		}else if(isNiece(firstPerson, secondPerson)){
			System.out.println("Niece");
		}else if(isUncle(firstPerson, secondPerson)){
			System.out.println("Uncle");
		}else if(isAunt(firstPerson, secondPerson)){
			System.out.println("Aunt");
		}else {
			System.out.println("They are not related");
		}
	}
	
	/**
	 * To check if the secondName is father of the firstName
	 * @param firstName - firstName value
	 * @param secondName - secondName value
	 * @return boolean - true if the secondName value is the father of the firstName*/
	public static boolean isFather(String firstName, String secondName) {
		Person secondPersonObj = Main.familyTree.getPerson(secondName);
		
		if(secondPersonObj != null && "man".equals(secondPersonObj.getGender())) {
			return secondPersonObj.isChild(firstName);
		}
		return false;
	}
	
	/**
	 * To check if the secondName is mother of the firstName
	 * @param firstName - firstName value
	 * @param secondName - secondName value
	 * @return boolean - true if the secondName value is the mother of the firstName*/
	public static boolean isMother(String firstName, String secondName) {
		Person secondPersonObj = Main.familyTree.getPerson(secondName);
		
		if(secondPersonObj != null && "woman".equals(secondPersonObj.getGender())) {
			return secondPersonObj.isChild(firstName);
		}
		return false;
	}
	
	/**
	 * To check if the secondName is son of the firstName
	 * @param firstName - firstName value
	 * @param secondName - secondName value
	 * @return boolean - true if the secondName value is the son of the firstName*/
	public static boolean isSon(String firstName, String secondName) {
		Person firstPersonObj = Main.familyTree.getPerson(firstName);
		
		if(firstPersonObj != null && firstPersonObj.isChild(secondName)) {
			Person secondPersonObj = Main.familyTree.getPerson(secondName);
			if(secondPersonObj != null) {
				return "man".equals(secondPersonObj.getGender());
			}
		}
		return false;
	}
	
	/**
	 * To check if the secondName is daughter of the firstName
	 * @param firstName - firstName value
	 * @param secondName - secondName value
	 * @return boolean - true if the secondName value is the daughter of the firstName*/
	public static boolean isDaughter(String firstName, String secondName) {
		Person firstPersonObj = Main.familyTree.getPerson(firstName);
		
		if(firstPersonObj != null && firstPersonObj.isChild(secondName)) {
			Person secondPersonObj = Main.familyTree.getPerson(secondName);
			if(secondPersonObj != null) {
				return "woman".equals(secondPersonObj.getGender());
			}
		}
		return false;
	}
	
	/**
	 * To check if the secondName is brother of the firstName
	 * @param firstName - firstName value
	 * @param secondName - secondName value
	 * @return boolean - true if the secondName value is the brother of the firstName*/
	public static boolean isBrother(String firstName, String secondName) {
		Person secondPersonObj = Main.familyTree.getPerson(secondName);
		if(secondPersonObj != null && "man".equals(secondPersonObj.getGender())) {
			Person parentObj = Main.familyTree.getParent(secondPersonObj.getName());
			if(parentObj != null) {
				return parentObj.isChild(firstName);
			}
		}
		return false;
	}
	
	/**
	 * To check if the secondName is sister of the firstName
	 * @param firstName - firstName value
	 * @param secondName - secondName value
	 * @return boolean - true if the secondName value is the sister of the firstName*/
	public static boolean isSister(String firstName, String secondName) {
		Person secondPersonObj = Main.familyTree.getPerson(secondName);
		if(secondPersonObj != null && "woman".equals(secondPersonObj.getGender())) {
			Person parentObj = Main.familyTree.getParent(secondPersonObj.getName());
			if(parentObj != null) {
				return parentObj.isChild(firstName);
			}
		}
		return false;
	}
	
	/**
	 * To check if the secondName is cousin of the firstName
	 * @param firstName - firstName value
	 * @param secondName - secondName value
	 * @return boolean - true if the secondName value is the cousin of the firstName*/
	public static boolean isCousins(String firstName, String secondName) {
		Person firstPersonObj = Main.familyTree.getPerson(firstName);
		Person secondPersonObj = Main.familyTree.getPerson(secondName);
		
		if(firstPersonObj != null && secondPersonObj != null) {
			Person firstPerParent = Main.familyTree.getParent(firstPersonObj.getName());
			Person secondPerParent = Main.familyTree.getParent(secondPersonObj.getName());
			if(firstPerParent != null && secondPerParent != null) {
				return isBrother(firstPerParent.getName(), secondPerParent.getName()) || isSister(firstPerParent.getName(), secondPerParent.getName());
			}
		}
		
		return false;
	}
	
	/**
	 * To check if the secondName is husband of the firstName
	 * @param firstName - firstName value
	 * @param secondName - secondName value
	 * @return boolean - true if the secondName value is the husband of the firstName*/
	public static boolean isHusband(String firstName, String secondName) {
		Person secondPersonObj = Main.familyTree.getPerson(secondName);
		if(secondPersonObj != null && secondPersonObj.isPartner(firstName) && "man".equals(secondPersonObj.getGender())) {
			Person firstPersonObj = Main.familyTree.getPerson(firstName);
			if(firstPersonObj != null && "woman".equals(firstPersonObj.getGender())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * To check if the secondName is wife of the firstName
	 * @param firstName - firstName value
	 * @param secondName - secondName value
	 * @return boolean - true if the secondName value is the wife of the firstName*/
	public static boolean isWife(String firstName, String secondName) {
		Person secondPersonObj = Main.familyTree.getPerson(secondName);
		if(secondPersonObj != null && secondPersonObj.isPartner(firstName) && "woman".equals(secondPersonObj.getGender())) {
			Person firstPersonObj = Main.familyTree.getPerson(firstName);
			if(firstPersonObj != null && "man".equals(firstPersonObj.getGender())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * To check if the secondName is Grand father of the firstName
	 * @param firstName - firstName value
	 * @param secondName - secondName value
	 * @return boolean - true if the secondName value is the Grand father of the firstName*/
	public static boolean isGrandFather(String firstName, String secondName) {
		int firstNameDepth = Main.familyTree.getPersonDepth(firstName);
		int secondNameDepth = Main.familyTree.getPersonDepth(secondName);
		Person person = Main.familyTree.getPerson(secondName);
		if(firstNameDepth != -1 && secondNameDepth != -1 && person != null) {
			if("man".equals(person.getGender())) {
				return ((firstNameDepth - secondNameDepth) == 2);	
			}
		}
		return false;
	}
	
	/**
	 * To check if the secondName is Grand mother of the firstName
	 * @param firstName - firstName value
	 * @param secondName - secondName value
	 * @return boolean - true if the secondName value is the Grand mother of the firstName*/
	public static boolean isGrandMother(String firstName, String secondName) {
		int firstNameDepth = Main.familyTree.getPersonDepth(firstName);
		int secondNameDepth = Main.familyTree.getPersonDepth(secondName);
		Person person = Main.familyTree.getPerson(secondName);
		if(firstNameDepth != -1 && secondNameDepth != -1 && person != null) {
			if("woman".equals(person.getGender())) {
				return ((firstNameDepth - secondNameDepth) == 2);	
			}
		}
		return false;
	}
	
	/**
	 * To check if the secondName is Grand son of the firstName
	 * @param firstName - firstName value
	 * @param secondName - secondName value
	 * @return boolean - true if the secondName value is the Grand son of the firstName*/
	public static boolean isGrandSon(String firstName, String secondName) {
		int firstNameDepth = Main.familyTree.getPersonDepth(firstName);
		int secondNameDepth = Main.familyTree.getPersonDepth(secondName);
		Person person = Main.familyTree.getPerson(secondName);
		if(firstNameDepth != -1 && secondNameDepth != -1 && person != null) {
			if("man".equals(person.getGender())) {
				return ((secondNameDepth - firstNameDepth) == 2);	
			}
		}
		return false;
	}
	
	/**
	 * To check if the secondName is Grand daughter of the firstName
	 * @param firstName - firstName value
	 * @param secondName - secondName value
	 * @return boolean - true if the secondName value is the Grand daughter of the firstName*/
	public static boolean isGrandDaughter(String firstName, String secondName) {
		int firstNameDepth = Main.familyTree.getPersonDepth(firstName);
		int secondNameDepth = Main.familyTree.getPersonDepth(secondName);
		Person person = Main.familyTree.getPerson(secondName);
		if(firstNameDepth != -1 && secondNameDepth != -1 && person != null) {
			if("woman".equals(person.getGender())) {
				return ((secondNameDepth - firstNameDepth) == 2);	
			}
		}
		return false;
	}
	
	/**
	 * To check if the secondName is nephew of the firstName
	 * @param firstName - firstName value
	 * @param secondName - secondName value
	 * @return boolean - true if the secondName value is the nephew of the firstName*/
	public static boolean isNephew(String firstName, String secondName) {
		int firstNameDepth = Main.familyTree.getPersonDepth(firstName);
		int secondNameDepth = Main.familyTree.getPersonDepth(secondName);
		Person person = Main.familyTree.getPerson(secondName);
		if(firstNameDepth != -1 && secondNameDepth != -1 && person != null) {
			if("man".equals(person.getGender())) {
				return ((secondNameDepth - firstNameDepth) == 1);	
			}
		}
		return false;
	}
	
	/**
	 * To check if the secondName is niece of the firstName
	 * @param firstName - firstName value
	 * @param secondName - secondName value
	 * @return boolean - true if the secondName value is the niece of the firstName*/
	public static boolean isNiece(String firstName, String secondName) {
		int firstNameDepth = Main.familyTree.getPersonDepth(firstName);
		int secondNameDepth = Main.familyTree.getPersonDepth(secondName);
		Person person = Main.familyTree.getPerson(secondName);
		if(firstNameDepth != -1 && secondNameDepth != -1 && person != null) {
			if("woman".equals(person.getGender())) {
				return ((secondNameDepth - firstNameDepth) == 1);	
			}
		}
		return false;
	}
	
	/**
	 * To check if the secondName is uncle of the firstName
	 * @param firstName - firstName value
	 * @param secondName - secondName value
	 * @return boolean - true if the secondName value is the uncle of the firstName*/
	public static boolean isUncle(String firstName, String secondName) {
		int firstNameDepth = Main.familyTree.getPersonDepth(firstName);
		int secondNameDepth = Main.familyTree.getPersonDepth(secondName);
		Person person = Main.familyTree.getPerson(secondName);
		if(firstNameDepth != -1 && secondNameDepth != -1 && person != null) {
			if("man".equals(person.getGender())) {
				return ((firstNameDepth - secondNameDepth) == 1);	
			}
		}
		return false;
	}
	
	/**
	 * To check if the secondName is aunt of the firstName
	 * @param firstName - firstName value
	 * @param secondName - secondName value
	 * @return boolean - true if the secondName value is the aunt of the firstName*/
	public static boolean isAunt(String firstName, String secondName) {
		int firstNameDepth = Main.familyTree.getPersonDepth(firstName);
		int secondNameDepth = Main.familyTree.getPersonDepth(secondName);
		Person person = Main.familyTree.getPerson(secondName);
		if(firstNameDepth != -1 && secondNameDepth != -1 && person != null) {
			if("woman".equals(person.getGender())) {
				return ((firstNameDepth - secondNameDepth) == 1);	
			}
		}
		return false;
	}
}
