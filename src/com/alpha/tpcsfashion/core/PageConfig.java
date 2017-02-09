package com.alpha.tpcsfashion.core;

public class PageConfig {

	public int iEnd;
	public int iStart;
	public static  int iPageSize=15;
	public int pageSize;
	private int pageNo;
	private int pageCount;
	public PageConfig(String strPageNo) throws NumberFormatException {
	
		setPageNo((strPageNo != null && !strPageNo.isEmpty() ? Integer.parseInt(strPageNo): 1 ));
        iStart = ((getPageNo() - 1) * iPageSize) + 1;
		iEnd = iStart + (iPageSize - 1);
	}
	public PageConfig(String strPageNo,int pageSize) throws NumberFormatException {
		
		setPageNo((strPageNo != null && !strPageNo.isEmpty() ? Integer.parseInt(strPageNo): 1 ));
        iStart = ((getPageNo() - 1) * pageSize) + 1;
		iEnd = iStart + (pageSize - 1);
	}
	public void setPageConfig(String strPageNo) throws NumberFormatException
	{
		setPageNo((strPageNo != null && !strPageNo.isEmpty() ? Integer.parseInt(strPageNo): 1 ));
        iStart = ((getPageNo() - 1) * iPageSize) + 1;
		iEnd = iStart + (iPageSize - 1);
	}

	public void setPageCount(int pageCount) {
		this.pageCount =(pageCount / iPageSize) + ((pageCount % iPageSize) > 0 ? 1 : 0);
		 ;
	}
	public void setPageCount(String strPageNo,int pageCount,int pageSize) {
		this.pageCount =(pageCount / pageSize) + ((pageCount % pageSize) > 0 ? 1 : 0);
		this.pageSize =(pageCount / pageSize) + ((pageCount % pageSize) > 0 ? 1 : 0);
	}
	public void setPageCount(int pageCount,int pageSize) {
		this.pageCount =pageCount;
		this.pageSize =(pageCount / pageSize) + ((pageCount % pageSize) > 0 ? 1 : 0);
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageNo() {
		return pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getIEnd() {
		return iEnd;
	}
	public void setIEnd(int iEnd) {
		this.iEnd = iEnd;
	}
	

}
