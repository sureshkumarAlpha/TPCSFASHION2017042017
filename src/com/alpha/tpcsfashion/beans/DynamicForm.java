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
public class DynamicForm implements  Serializable{

		@XmlElement
	    private int formId; 
		@XmlElement
	    private int tableId;	
		@XmlElement
	    private String tableName;	
		@XmlElement
	    private int numOfSections;
		@XmlElement
	    private String alignment;	    
		@XmlElementWrapper(name="javascripts")
		@XmlElement(name="javascript")
	    private List<DynamicJavaScript> formScript = new ArrayList<DynamicJavaScript>();
	    
	    @XmlElementWrapper(name="fields")
	    @XmlElement(name="field") 
	    private List<DynamicField> structureList = new ArrayList<DynamicField>();

		public int getFormId() {
			return formId;
		}

		public void setFormId(int formId) {
			this.formId = formId;
		}

		public int getTableId() {
			return tableId;
		}

		public void setTableId(int tableId) {
			this.tableId = tableId;
		}
		
		public String getTableName() {
			return tableName;
		}

		public void setTableName(String tableName) {
			this.tableName = tableName;
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

		public List<DynamicField> getStructureList() {
			return structureList;
		}

		public void setStructureList(List<DynamicField> structureList) {
			this.structureList = structureList;
		}
		
		

}
