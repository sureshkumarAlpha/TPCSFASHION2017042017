package com.alpha.tpcsfashion.beans;


public class EntityRights
{

  public int getEntityId()
  {
    return EntityId;
  }
  public void setEntityId(int entityId)
  {
	  EntityId = entityId;
  }
  public int getDocumentId()
  {
    return DocumentId;
  }
  public void setDocumentId(int documentId)
  {
	  DocumentId = documentId;
  }
  public String getEntityName()
  {
    return EntityName;
  }
  public void setEntityName(String entityName)
  {
	  EntityName = entityName;
  }
  public String getDocumentName()
  {
    return DocumentName;
  }
  public void setDocumentName(String documentName)
  {
	  DocumentName = documentName;
  }
  public String getModuleName()
  {
    return ModuleName;
  }
  public void setModuleName(String moduleName)
  {
	  ModuleName = moduleName;
  }
  
  public int getModuleId()
  {
    return ModuleId;
  }
  public void setModuleId(int moduleId)
  {
	  ModuleId = moduleId;
  }
  
  
  public String getEntityDocuments()
  {
    return EntityDocuments;
  }
  public void setEntityDocuments(String entityDocuments)
  {
	  EntityDocuments = entityDocuments;
  }
  public boolean isSuccess()
  {
    return Success;
  }
  public void setSuccess(boolean success)
  {
    Success = success;
  }


  
  private int EntityId;
  private int DocumentId;
  private int ModuleId;
  private String EntityName;
  private String DocumentName;
  private String ModuleName;
  private String EntityDocuments;
  private boolean Success;
  
  
  
  

}
