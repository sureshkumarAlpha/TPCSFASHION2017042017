package com.alpha.tpcsfashion.beans;

import java.io.Serializable;

public class DynamicFieldType implements Serializable {
	
	private int fieldTypeId;
    private String fieldTypeName;

    public int getFieldTypeId() {
        return fieldTypeId;
    }

    public void setFieldTypeId(int fieldTypeId) {
        this.fieldTypeId = fieldTypeId;
    }

    public String getFieldTypeName() {
        return fieldTypeName;
    }

    public void setFieldTypeName(String fieldTypeName) {
        this.fieldTypeName = fieldTypeName;
    }

    
}
