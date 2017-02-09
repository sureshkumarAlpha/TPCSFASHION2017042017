package com.alpha.tpcsfashion.beans;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MailInfo {

	private String fileName;
	private String filePath;
	private String folderPath;
	private List<MailInfo> liMailInfo;
	private Map<String,String> attachMap=new LinkedHashMap<String, String>();
	

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFolderPath() {
		return folderPath;
	}

	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}

	public List<MailInfo> getLiMailInfo() {
		return liMailInfo;
	}

	public void setLiMailInfo(List<MailInfo> liMailInfo) {
		this.liMailInfo = liMailInfo;
	}

	public Map<String, String> getAttachMap() {
		return attachMap;
	}

	public void setAttachMap(Map<String, String> attachMap) {
		this.attachMap = attachMap;
	}
	
	
}
