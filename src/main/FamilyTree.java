//$Id$
package main;

import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/**
 * FamilyTree class to represents of the family*/
public class FamilyTree {
	
	/**
	 * To represent the family tree*/
	private Person root;
	
	/**
	 * To store the tempPersons object*/
	public static HashMap<String, Person> tempPersons = new HashMap<String, Person>();
	
	/**
	 * Constructor of FamilyTree class*/
	public FamilyTree(Person root) {
		this.root = root;
	}
	
	/**
	 * Setter method for root object
	 * @param root - root Person object*/
	public void setRoot(Person root) {
		this.root = root;
	}
	
	/**
	 * To get person for the tree
	 * @param personName - personName value of the string
	 * @return Person - Person object that matches the personName*/
	public Person getPerson(String personName) {
		if(personName == null) {
			return null;
		}
		return getPerson(personName, this.root);
	}
	
	/**
	 * To get person for the tree
	 * @param personName - personName value of the string
	 * @param currentPerson - Person object of the current person in the tree
	 * @return Person - person object to be returned that matches the search term personName*/
	public Person getPerson(String personName, Person currentPerson) {
		if(currentPerson == null) {
			return null;
		}
		
		if(personName.equals(currentPerson.getName())) {
			return currentPerson;
		}
		
		if(currentPerson.getPartner() != null) {
			if(personName.equals(currentPerson.getPartner().getName())) {
				return currentPerson.getPartner();
			}
		}
		
		if(currentPerson.getChildren() != null && currentPerson.getChildren().size() > 0) {
			for(int i=0; i<currentPerson.getChildren().size(); i++) {
				Person obj = getPerson(personName, currentPerson.getChildAtIndex(i));
				if(obj != null) {
					return obj;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * To add a person object to a tree
	 * @param firstname - firstName string value of a person
	 * @param relationship - relationship value for the firstPerson to secondPerson
	 * @param secondPerson - secondPerson String value of a person*/
	public void addPersonToTree(String firstPerson, String relationship, String secondPerson) {
		if("wife".equals(relationship) || "husband".equals(relationship)) {
			Person treeObject = getPerson(firstPerson);
			Person newObject = tempPersons.get(secondPerson);
			if(newObject != null) {
				treeObject.setPartner(newObject);
			}
		}else if("father".equals(relationship) || "mother".equals(relationship)) {
			Person treeObject = getPerson(secondPerson);
			
			Person tempObj = tempPersons.get(firstPerson);
			if(tempObj != null) {
				treeObject.addChild(tempObj);
			}
		}
	}
	
	/**
	 * Family names in sorted order*/
	public TreeSet<String> familyNames;
	
	/**
	 * To get all the names of all the persons in the family
	 * @return TreeSet<String> - TreeSet of all the names in the family*/
	public TreeSet<String> getAllNames(){
		familyNames = new TreeSet<String>();
		getNames(this.root);
		return familyNames;
	}
	
	/**
	 * To get all names of all the persons in the family
	 * Recursive method
	 * @param currentPerson - person object of the current element*/
	public void getNames(Person currentPerson){
		if(currentPerson == null) {
			return;
		}
		
		familyNames.add(currentPerson.getName());
		
		if(currentPerson.getPartner() != null) {
			familyNames.add(currentPerson.getPartner().getName());
		}
		
		if(currentPerson.getChildren() != null && currentPerson.getChildren().size() > 0) {
			for(int i=0; i<currentPerson.getChildren().size(); i++) {
				getNames(currentPerson.getChildAtIndex(i));
			}
		}
	}
	
	/**
	 * To get the depth of the personName in  the family tree
	 * @param personName - personName string value of the person to get the depth of the family tree
	 * @return int - depth of the family tree*/
	public int getPersonDepth(String personName) {
		return getPersonDepth(personName, 1, this.root);
	}
	
	/**
	 * To get the parent object of the passed in person
	 * @param childName - childName of the person to get the parent object value
	 * @return Person - parent Person object*/
	public Person getParent(String childName) {
		return getParent(childName, this.root);
	}
	
	/**
	 * To get the parent object of the passed in child name value
	 * @param childName - childName value of the string to which person object to be retrieved
	 * @param currentName - currentPerson object
	 * @return Person - parent Person object of the passed in child object*/
	public Person getParent(String childName, Person currentName) {
		if(currentName == null) {
			return null;
		}
		
		if(currentName.isChild(childName)) {
			return currentName;
		}
		
		if(currentName.getChildren() != null && currentName.getChildren().size() > 0) {
			for(int i=0; i<currentName.getChildren().size(); i++) {
				Person tempPerson = getParent(childName, currentName.getChildAtIndex(i));
				if(tempPerson != null) {
					return tempPerson;
				}
			}
		}
		return null;
	}
	
	/**
	 * To get depth of a person object
	 * @param personName - person name to be searched for
	 * @param depth - depth value for the current value
	 * @param currentPerson - Person object of the current object*/
	public int getPersonDepth(String personName, int depth, Person currentPerson) {
		if(currentPerson == null) {
			return -1;
		}
		
		if(personName.equals(currentPerson.getName())) {
			return depth;
		}
		
		if(currentPerson.getPartner() != null) {
			if(personName.equals(currentPerson.getPartner().getName())) {
				return depth;
			}
		}
		
		if(currentPerson.getChildren() != null && currentPerson.getChildren().size() > 0) {
			for(int i=0; i<currentPerson.getChildren().size(); i++) {
				int index = getPersonDepth(personName, depth+1, currentPerson.getChildAtIndex(i));
				if(index != -1) {
					return index;
				}
			}
		}
		
		return -1;
	}
	
	/**
	 * To get the graph content to build DOT file
	 * @return String - string value of the DOT value*/
	public String getGraphContent() {
		return getGraphContent(this.root);
	}
	
	/**
	 * To get the graph content to build DOT file in recursive value of the family tree
	 * @param currentPerson - Current Person object to build content
	 * @param String - String value to be returned*/
	public String getGraphContent(Person currentPerson) {
		if(currentPerson == null) {
			return "";
		}
		
		String value = "";
		
		List<Person> childs = currentPerson.getChildren();
		if(childs != null && childs.size() > 0) {
			for(int i=0; i< childs.size(); i++) {
				value += "\""+ currentPerson.getName() +"\" -> \""+ childs.get(i).getName() +"\" [label=\""+ (("man".equals(currentPerson.getGender())) ? "father" : "mother") +"\"] [width=10];\n";
			}
		}
		
		Person partner = currentPerson.getPartner();
		if(partner != null) {
			value += "\""+ currentPerson.getName() +"\" -> \""+ partner.getName() +"\" [label=\""+ (("man".equals(currentPerson.getGender())) ? "husband" : "wife") +"\"] [width=10];\n";
			List<Person> partnerChilds = partner.getChildren();
			if(partnerChilds != null && partnerChilds.size() > 0) {
				for(int i=0; i<partnerChilds.size(); i++) {
					value += "\""+ partner.getName() +"\" -> \""+ partnerChilds.get(i).getName() +"\" [label=\""+ (("man".equals(partner.getGender()))? "father" : "mother") +"\"] [width=10];\n";
				}
			}
		}
		
		
		childs = currentPerson.getChildren();
		if(childs != null && childs.size() > 0) {
			for(int i=0; i<childs.size(); i++) {
				value += getGraphContent(childs.get(i));
			}
		}
		
		return value;
	}
}
