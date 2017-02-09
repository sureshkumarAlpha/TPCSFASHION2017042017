package com.alpha.tpcsfashion.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "screen")
@XmlAccessorType(XmlAccessType.FIELD) 
public class DynamicFormScreens implements  Serializable{

		@XmlElement
	    private int screenId; 		
		@XmlElement
	    private int numOfSections;
		@XmlElement
	    private String alignment;	    
		@XmlElementWrapper(name="javascripts")
		@XmlElement(name="javascript")
	    private List<DynamicJavaScript> formScript = new ArrayList<DynamicJavaScript>();
		
		@XmlElementWrapper(name="tables")
		@XmlElement(name="table")
	    private List<DynamicTable> tables = new ArrayList<DynamicTable>();
		
		public int getScreenId() {
			return screenId;
		}

		public void setScreenId(int formId) {
			this.screenId = formId;
		}
		public int getNumOfSections() {
			return numOfSections;
		}

		public void setNumOfSections(int numOfSections) {
			this.numOfSections = numOfSections;
		}

		public String getAlignment() {
			return alignment;
		}

		public void setAlignment(String alignment) {
			this.alignment = alignment;
		}
		public List<DynamicJavaScript> getFormScript() {
			return formScript;
		}

		public void setFormScript(List<DynamicJavaScript> formScript) {
			this.formScript = formScript;
		}

		public List<DynamicTable> getTables() {
			return tables;
		}

		public void setTables(List<DynamicTable> tables) {
			this.tables = tables;
		}


}
