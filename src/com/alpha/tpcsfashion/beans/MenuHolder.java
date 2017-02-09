package com.alpha.tpcsfashion.beans;

import java.util.List;
import java.util.Map;

public class MenuHolder
{
  
  private List<MenuItem> mainMenu;
  private Map<Integer, List<MenuItem>> documents;
  private Map<Integer, List<MenuItem>> subDocuments;
  public List<MenuItem> getMainMenu()
  {
    return mainMenu;
  }
  public void setMainMenu(List<MenuItem> mainMenu)
  {
    this.mainMenu = mainMenu;
  }
  public Map<Integer, List<MenuItem>> getDocuments()
  {
    return documents;
  }
  public void setDocuments(Map<Integer, List<MenuItem>> documents)
  {
    this.documents = documents;
  }
  public Map<Integer, List<MenuItem>> getSubDocuments()
  {
    return subDocuments;
  }
  public void setSubDocuments(Map<Integer, List<MenuItem>> subDocuments)
  {
    this.subDocuments = subDocuments;
  }
  
}
