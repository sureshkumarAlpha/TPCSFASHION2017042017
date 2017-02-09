package com.alpha.tpcsfashion.util;

import java.io.File;

public class ManageFile {

	public static void deleteFile(String filePath,String folderPath)
	{
		try{
			File file = new File(filePath);
			if(file.exists()){
			file.delete();	
			}
			File folder = new File(folderPath);
			if(folder.exists()){
				folder.delete();	
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void deleteFile(String filePath)
	{
		try{
			File file = new File(filePath);
			if(file.exists()){
			file.delete();	
			}
			File folder = new File(filePath+"_files");
			if(folder.exists()){
				folder.delete();	
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static boolean isfileExist(String fileFullPath){
		boolean isFileExist=false;
		try{
			File file = new File(fileFullPath);
			if(file.exists()){
				isFileExist= true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return isFileExist;
	}
	
	public static boolean isDirectoryExist(String directoryFullPath){
		boolean isDirectoryExist=false;
		try{
			File file = new File(directoryFullPath);
			if(file.isDirectory()){
				isDirectoryExist= true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return isDirectoryExist;
	}
	
}
