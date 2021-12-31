//$Id$
package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Basic person class to store the details of a person entity*/
public class Person {
	
	/**Name field of the person*/
	private String name;
	
	/**Gender field of the person*/
	private String gender;
	
	/**Partner field of the person*/
	private Person partner;
	
	/**Children field of the person*/
	private List<Person> children;
	
	/**Child names in hash set of the person*/
	private HashSet<String> childNames;
	
	/**
	 * Constructor of Person class
	 * @param name - Person name value of the Object
	 * @param gender - gender value of the person object*/
	public Person(String name, String gender) {
		this.name = name;
		this.gender = gender;
		this.partner = null;
		this.children = null;
		this.childNames = new HashSet<String>();
	}
	
	/**
	 * Getter method for name value in person
	 * @return String - name value of the person*/
	public String getName() {
		return this.name;
	}
	
	/**
	 * Getter method for gender value in person
	 * @return String - gender values of the person*/
	public String getGender() {
		return this.gender;
	}
	
	/**
	 * Setter method for partner field
	 * @param partner - Person object value*/
	public void setPartner(Person partner) {
		this.partner = partner;
		if(!(this.isPartnerPresent() && this.isPartner(partner.getName()))) {
			partner.setPartner(this);
			List<Person> newChilds = getMergedChildList(this.children, partner.getChildren());
			this.children = newChilds;
			partner.setChildren(newChilds);
		}
	}
	
	/**
	 * To get merged child array list of both the list
	 * To create a new array list with the unique values of both the list
	 * @param firsstPereChildren - list of first person children
	 * @param secondPerChildren - list of second person children*/
	private List<Person> getMergedChildList(List<Person> firstPerChildren, List<Person> secondPerChildren){
		ArrayList<Person> newChildrens = new ArrayList<Person>();
		HashSet<String> child = new HashSet<String>();
		
		for(int i=0; i<firstPerChildren.size(); i++) {
			Person temp = firstPerChildren.get(i);
			newChildrens.add(temp);
			child.add(temp.getName());
		}
		
		for(int i=0; i<secondPerChildren.size(); i++) {
			Person temp = secondPerChildren.get(i);
			if(!child.contains(temp.getName())) {
				newChildrens.add(temp);
				child.add(temp.getName());
			}
		}
		return newChildrens;
	}
	
	/**
	 * Getter method for partner object
	 * @return Person - Person object*/
	public Person getPartner() {
		return this.partner;
	}
	
	/**
	 * Getter method for children array
	 * @return List<Person> - person child array of the persons object*/
	public List<Person> getChildren(){
		return this.children;
	}
	
	/**
	 * Setter method for children array
	 * @param List<Person> - array list of Person object*/
	public void setChildren(List<Person> child) {
		this.children = child;
	}
	
	/**
	 * To get Person object by child object
	 * @param name - name string value
	 * @return Person - object value*/
	public Person getChildByName(String name) {
		if(name == null) {
			return null;
		}
		
		if(this.children != null) {
			for(int i=0; i<this.children.size(); i++) {
				if(name.equals(this.children.get(i).getName())) {
					return this.children.get(i);
				}
			}
		}
		return null;
	}
	
	/**
	 * To get child person object for the given index
	 * To return null value if the index is out of range
	 * @param int - index of element to get
	 * @return Person - Person object to be returned*/
	public Person getChildAtIndex(int i) {
		if(i>=0 && i <this.children.size()) {
			return this.children.get(i);
		}
		return null;
	}
	
	/**
	 * To check if the child name is a child of the person object
	 * @param name - person name value
	 * @return boolean - indicating if the element is present in the child array for this person*/
	public boolean isChild(String name) {
		Person childObj = getChildByName(name);
		
		return childObj != null ? true : false;
	}
	
	/**
	 * To check of the name passed is a partner of the person object
	 * @param name - string name value
	 * @return boolean - indicating if the string passed is a partner for the current value*/
	public boolean isPartner(String name) {
		if(this.partner != null) {
			return name.equals(this.partner.getName());
		}
		return false;
	}
	
	/**
	 * To check if the partner is present for the person object
	 * @return boolean - indicating partner value present*/
	public boolean isPartnerPresent() {
		return this.partner != null;
	}
	
	/**
	 * To add a new child object to the Person object
	 * if the children value is null a new ArrayList<Person> is initiated and the value is added to the list
	 * @param childObj - Person object*/
	public void addChild(Person childObj) {
		if(this.children == null) {
			this.children = new ArrayList<Person>();
			this.children.add(childObj);
			this.childNames.add(childObj.getName());
			if(this.partner != null) {
				this.partner.addChild(childObj);
			}
		}else {
			if(!this.childNames.contains(childObj.getName())) {
				this.children.add(childObj);
				this.childNames.add(childObj.getName());
				if(this.partner != null) {
					this.partner.addChild(childObj);
				}
			}
		}
	}

}
