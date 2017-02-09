package com.alpha.tpcsfashion.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class DynamicJavaScript implements Serializable {
	
	private int fieldId;
    private String javaScript;
    private String javaScriptEvent;
    private int javaScriptEventId;
    private String methodName;
    
    private String pageFieldName;
	
    
    public int getFieldId() {
		return fieldId;
	}
    @XmlAttribute
	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}
	public String getJavaScript() {
		return javaScript;
	}
	@XmlElement(name="script")
	public void setJavaScript(String javaScript) {
		this.javaScript = javaScript;
	}
	public String getJavaScriptEvent() {
		return javaScriptEvent;
	}
	@XmlElement
	public void setJavaScriptEvent(String javaScriptEvent) {
		this.javaScriptEvent = javaScriptEvent;
	}
	public int getJavaScriptEventId() {
		return javaScriptEventId;
	}
	@XmlElement
	public void setJavaScriptEventId(int javaScriptEventId) {
		this.javaScriptEventId = javaScriptEventId;
	}
		
	public String getMethodName() {
		return methodName;
	}
	@XmlElement
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getPageFieldName() {
		return pageFieldName;
	}
	@XmlElement
	public void setPageFieldName(String pageFieldName) {
		this.pageFieldName = pageFieldName;
	}
	

    
}
