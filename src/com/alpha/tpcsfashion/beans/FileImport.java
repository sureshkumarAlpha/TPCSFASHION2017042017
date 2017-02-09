package com.alpha.tpcsfashion.beans;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileImport {

	private String 				filePath;
	private String 				folderPath;
	private String 				className;
	private String 				methodName;
	private List 				fileItems;
//	private List<String>		fileList;
	private List<File>			fileList;
	private int 				colCountRow;
	private int 				headerRowCount;
	private List<Integer> mandatoryCells;
	private List<Integer> headerRows;
	private List<List<String>> 	valuList;
	private List<List<String>> 	nullList;
	
	
	
	
	public int getHeaderRowCount() {
		return headerRowCount;
	}
	public void setHeaderRowCount(int headerRowCount) {
		this.headerRowCount = headerRowCount;
	}
	public List<List<String>> getValuList() {
		return valuList;
	}
	public void setValuList(List<List<String>> valuList) {
		this.valuList = valuList;
	}
	public List<List<String>> getNullList() {
		return nullList;
	}
	public void setNullList(List<List<String>> nullList) {
		this.nullList = nullList;
	}
	public List<Integer> getMandatoryCells() {
		return mandatoryCells;
	}
	public void setMandatoryCells(List<Integer> mandatoryCells) {
		this.mandatoryCells = mandatoryCells;
	}
	public List<Integer> getHeaderRows() {
		return headerRows;
	}
	public void setHeaderRows(List<Integer> headerRows) {
		this.headerRows = headerRows;
	}
	public int getColCountRow() {
		return colCountRow;
	}
	public void setColCountRow(int colCountRow) {
		this.colCountRow = colCountRow;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public List getFileItems() {
		return fileItems;
	}
	public void setFileItems(List fileItems) {
		this.fileItems = fileItems;
	}
	public String getFolderPath() {
		return folderPath;
	}
	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}
	public List<File> getFileList() {
		return fileList;
	}
	public void setFileList(List<File> fileList) {
		this.fileList = fileList;
	}
	


}
