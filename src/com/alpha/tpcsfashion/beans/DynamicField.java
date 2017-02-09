package com.alpha.tpcsfashion.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class DynamicField implements Comparable<DynamicField>, Serializable{

		private static final long serialVersionUID = 1L;
	    private int fieldId;	   
	    private String fieldName;
	    private String labelName;
	    private String dbfieldName;
	    private String fieldValue;
	    private int dataTypeId;
	    private String dataType;
	    private int controlTypeId;
	    private String controlType;
	    private boolean required;
	    private float floatPosition;	    
	    private String length;
	    private boolean fixed;
	    private boolean active;
	    private String columnWidth;
	    private List<DynamicJavaScript> dynamicJavaScriptList = new ArrayList<DynamicJavaScript>();
	    private List<String> defaultValuesList = new ArrayList<String>();
	    
	    private String fieldAlignment;

	    public int getFieldId() {
	        return fieldId;
	    }
	    
	    
	    
	    
	    
	    
	    
	    public String getColumnWidth() {
			return columnWidth;
		}







		public void setColumnWidth(String columnWidth) {
			this.columnWidth = columnWidth;
		}







		@XmlAttribute
	    public void setFieldId(int fieldId) {
	        this.fieldId = fieldId;
	    }

	    public String getFieldName() {
	        return fieldName;
	    }

	    @XmlElement
	    public void setFieldName(String fieldName) {
	        this.fieldName = fieldName;
	    }

	    public String getFieldValue() {
	        return fieldValue;
	    }
	    
	    @XmlElement
	    public void setFieldValue(String fieldValue) {
	        this.fieldValue = fieldValue;
	    }    
	    
	    public String getLabelName() {
	        return labelName;
	    }
	    @XmlElement
	    public void setLabelName(String labelName) {
	        this.labelName = labelName;
	    }
	   
	    public int getDataTypeId() {
			return dataTypeId;
		}
	    @XmlElement
		public void setDataTypeId(int dataTypeId) {
			this.dataTypeId = dataTypeId;
		}
	  
		public int getControlTypeId() {
			return controlTypeId;
		}
	    @XmlElement
		public void setControlTypeId(int controlTypeId) {
			this.controlTypeId = controlTypeId;
		}
		
		public String getDataType() {
		        return dataType;
		 }		    
		@XmlElement
	    public void setDataType(String dataType) {
	        this.dataType = dataType;
	    }

	    public String getControlType() {
	        return controlType;
	    }
	    
	    @XmlElement
	    public void setControlType(String controlType) {
	        this.controlType = controlType;
	    }

	    public boolean isRequired() {
	        return required;
	    }

	    @XmlElement
	    public void setRequired(boolean required) {
	        this.required = required;
	    }    

		public float getFloatPosition() {
			return floatPosition;
		}

		@XmlElement(name="position")
		public void setFloatPosition(float floatPosition) {
			this.floatPosition = floatPosition;
		}

		public boolean isActive() {
			return active;
		}

		@XmlElement
		public void setActive(boolean active) {
			this.active = active;
		}	
		
		public String getDbfieldName() {
			return dbfieldName;
		}
		
		@XmlElement
		public void setDbfieldName(String dbfieldName) {
			this.dbfieldName = dbfieldName;
		}

		public String getLength() {
			return length;
		}

		@XmlElement
		public void setLength(String length) {
			this.length = length;
		}

		public boolean isFixed() {
			return fixed;
		}

		@XmlElement
		public void setFixed(boolean fixed) {
			this.fixed = fixed;
		}	
		public List<DynamicJavaScript> getDynamicJavaScriptList() {
			return dynamicJavaScriptList;
		}
		
		public String getFieldAlignment() {
			return fieldAlignment;
		}
		
		@XmlElement(name="alignment")
		public void setFieldAlignment(String fieldAlignment) {
			this.fieldAlignment = fieldAlignment;
		}

		@XmlElementWrapper(name="javascripts")
		@XmlElement(name="javascript")
		public void setDynamicJavaScriptList(
				List<DynamicJavaScript> dynamicJavaScriptList) {
			this.dynamicJavaScriptList = dynamicJavaScriptList;
		}		
		public List<String> getDefaultValuesList() {
			return defaultValuesList;
		}

		@XmlElementWrapper(name="defaultValues") 
		@XmlElement(name="defaultValue")
		public void setDefaultValuesList(List<String> defaultValuesList) {
			this.defaultValuesList = defaultValuesList;
		}

		@Override
		public int compareTo(DynamicField o) {
			return Float.compare(this.getFloatPosition(), o.getFloatPosition());
		}


}
