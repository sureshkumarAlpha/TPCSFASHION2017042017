package com.alpha.tpcsfashion.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD) 
public class DynamicTable implements  Serializable{
	@XmlElement
    private int tableId;
	
	@XmlElementWrapper(name="fields")
    @XmlElement(name="field") 
    private List<DynamicField> structureList = new ArrayList<DynamicField>();

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public List<DynamicField> getStructureList() {
		return structureList;
	}

	public void setStructureList(List<DynamicField> structureList) {
		this.structureList = structureList;
	}
	
	

}
