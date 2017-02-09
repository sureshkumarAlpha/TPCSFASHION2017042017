package com.alpha.tpcsfashion.beans;

import java.io.Serializable;

public class DynamicDataType implements Serializable {

	
	private int dataTypeId;
    private String dataTypeName;

    public int getDataTypeId() {
        return dataTypeId;
    }

    public void setDataTypeId(int dataTypeId) {
        this.dataTypeId = dataTypeId;
    }

    public String getDataTypeName() {
        return dataTypeName;
    }

    public void setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
    }
}
