package com.alpha.tpcsfashion.beans;

public class UserRights
{

  
  public int getAddPermission()
  {
    return addPermission;
  }
  public void setAddPermission(int addPermission)
  {
    this.addPermission = addPermission;
  }
  public int getEditPermission()
  {
    return editPermission;
  }
  public void setEditPermission(int editPermission)
  {
    this.editPermission = editPermission;
  }
  public int getDeletePermission()
  {
    return deletePermission;
  }
  public void setDeletePermission(int deletePermission)
  {
    this.deletePermission = deletePermission;
  }
  public int getAmendmentPermission()
  {
    return amendmentPermission;
  }
  public void setAmendmentPermission(int amendmentPermission)
  {
    this.amendmentPermission = amendmentPermission;
  }
  public int getViewPermission()
  {
    return viewPermission;
  }
  public void setViewPermission(int viewPermission)
  {
    this.viewPermission = viewPermission;
  }
  public int getExcelPermission()
  {
    return excelPermission;
  }
  public void setExcelPermission(int excelPermission)
  {
    this.excelPermission = excelPermission;
  }
  public int getPrintPermission()
  {
    return printPermission;
  }
  public void setPrintPermission(int printPermission)
  {
    this.printPermission = printPermission;
  }
  public int getApprovalPermission()
  {
    return approvalPermission;
  }
  public void setApprovalPermission(int approvalPermission)
  {
    this.approvalPermission = approvalPermission;
  }
  private int addPermission=0;
  private int editPermission=0;
  private int deletePermission=0;
  private int amendmentPermission=0;
  private int viewPermission=0;
  private int excelPermission=0;
  private int printPermission=0;
  private int approvalPermission=0;
}
