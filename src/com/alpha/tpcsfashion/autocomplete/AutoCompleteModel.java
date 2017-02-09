package com.alpha.tpcsfashion.autocomplete;


import java.sql.Connection;
import java.sql.SQLException;

import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

/**
 * This class will be used to handle all auto complete features of all modules.
 *  - Insert description here.
 *
 * @author Narayana Swamy
 * @creation Oct 6, 2013
 *
 */
public class AutoCompleteModel
{

 
  public String getGroups(String query,int createNew,TPCSUser ui){
    Connection con=null;
    String groups = null;
    try{
      con = connection.getConnection(ui);      
      groups = objAutoCompleteDAO.getGroups(con, query,ui,createNew);
    }catch(Exception sqe){
      sqe.printStackTrace();     
    }finally{
      try{
        if(con!=null)
           con.close();
      }catch(SQLException se){
        se.printStackTrace();
      }
    }
    return groups;
  }
  
  public String getTempGroups(String query,int createNew,TPCSUser ui){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getTempGroups(con, query,ui,createNew);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return groups;
	  }
  
  public String getUOM(String query,int createNew,TPCSUser ui){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getUOM(con, query,ui,createNew);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return groups;
	  }
  
  public String getIndentType(String query, int createNew, TPCSUser ui){
	  Connection con = null;
	  String str = null;
	  try{
		  con = connection.getConnection(ui);
		  str = objAutoCompleteDAO.getIndentType(con,query,ui,createNew);
	  }
	  catch(Exception sqe){
		  sqe.printStackTrace();
	  }
	  finally{
		  try{
			  if(con!=null)
				  con.close();
		  }
		  catch(SQLException se){
			  se.printStackTrace();
		  }
	  }
	  
	  
	  return str;
  }
  
  public String getCustomer(String query,int createNew,TPCSUser ui){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getCustomer(con, query,ui,createNew);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return groups;
	  }
  public String getAgent(String query,int createNew,TPCSUser ui){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getAgent(con, query,ui,createNew);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return groups;
	  }

public String getSeason(String query,int createNew,TPCSUser ui){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getSeason(con, query,ui,createNew);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return groups;
	  }

  public String getSupplier(String query,int createNew,TPCSUser ui){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getSupplier(con, query,ui,createNew);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return groups;
	  }
  
  public String getInventoryAccount(String query,TPCSUser ui,int createNew){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getInventoryAccount(con, query,ui,createNew);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return groups;
	  }
  
  public String getPurchaseAccount(String query,TPCSUser ui,int createNew){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getPurchaseAccount(con, query,ui,createNew);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return groups;
	  }
  public String getMaterial(String query,int createNew,TPCSUser ui,int groupId){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getMaterial(con, query,ui,createNew,groupId);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return groups;
	  }
  public String getMaterialFG(String query,int createNew,TPCSUser ui,int groupId){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getMaterialFG(con, query,ui,createNew,groupId);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return groups;
	  }
  
  public String getMaterialComponent(String query,int createNew,TPCSUser ui,int trId){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getMaterialComponent(con, query,ui,createNew,trId);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return groups;
	  }


public String getAltMaterial(String query,int createNew,TPCSUser ui,int matId,int compId){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getAltMaterial(con, query,ui,createNew,matId,compId);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return groups;
	  }



public String getBOMComponents(String query,int createNew,TPCSUser ui,int detId){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getBOMComponents(con, query,ui,createNew,detId);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return groups;
	  }

public String getBOM(String query,int createNew,TPCSUser ui){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getBOM(con, query,ui,createNew);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return groups;
	  }




public String getFGMaterial(String query,int createNew,TPCSUser ui){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getFGMaterial(con, query,ui,createNew);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return groups;
	  }
  
  public String getMaterialForInvoice(String query,int createNew,TPCSUser ui,int soId,int poId,int invId,int enqId,int quoteId,String trTag){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getMaterialForInvoice(con, query,ui,createNew,soId,poId,invId,enqId,quoteId,trTag);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return groups;
	  }
 
  public String getAccountGroup(String query,TPCSUser ui){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getAccountGroup(con, query,ui);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return groups;
	  }
  
  public String getCurrency(String query,TPCSUser ui){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getCurrency(con, query,ui);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return groups;
	  }
  
  public String getParentGroup(String query,TPCSUser ui){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getParentGroup(con, query,ui);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return groups;
	  }
  
  public String getAccountReceivedFrom(String query,TPCSUser ui,int createNew){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getAccountReceivedFrom(con, query,ui,createNew);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return groups;
	  }
  public String getAllAccount(String query,TPCSUser ui,int createNew){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getAllAccount(con, query,ui,createNew);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return groups;
	  }
  
  public String getOpeningAccount(String query,TPCSUser ui,int createNew){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getOpeningAccount(con, query,ui,createNew);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return groups;
	  }
  
  
  public String getAccountDepositTo(String query,TPCSUser ui,int createNew){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getAccountDepositTo(con, query,ui,createNew);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return groups;
	  }
  
  public String getBankAccount(String query,TPCSUser ui,int createNew){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getBankAccount(con, query,ui,createNew);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return groups;
	  }
  
  public String getCashAccount(String query,TPCSUser ui,int createNew){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getCashAccount(con, query,ui,createNew);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return groups;
	  }
  public String getAccountPaidTo(String query,TPCSUser ui,int createNew){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getAccountPaidTo(con, query,ui,createNew);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return groups;
	  }
  public String getJournalAccount(String query,TPCSUser ui, int createNew){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getJournalAccount(con, query,ui,createNew);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return groups;
	  }
  public String getGroupForAccount(String query,TPCSUser ui,int createNew){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getGroupForAccount(con, query,ui,createNew);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	      try{
	        if(con!=null)
	           con.close();
	      }catch(SQLException se){
	        se.printStackTrace();
	      }
	    }
	    return groups;
	  }
  
  public String getAccountOfDutiesAndTax(String query,int createNew,TPCSUser ui){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getAccountOfDutiesAndTax(con, query,createNew,ui);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	     DatabaseConnection.connectionClose(con);
	    }
	    return groups;
	  }
  public String getAccountOfSales(String query,int createNew,TPCSUser ui){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getAccountOfSales(con, query,createNew,ui);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	     DatabaseConnection.connectionClose(con);
	    }
	    return groups;
	  }
  public String getAccountOfPurchase(String query,int createNew,TPCSUser ui){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getAccountOfPurchase(con, query,createNew,ui);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	     DatabaseConnection.connectionClose(con);
	    }
	    return groups;
	  }
  public String getAccountOfExpenditureDirExpense(String query,int createNew,TPCSUser ui){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getAccountOfExpenditureDirExpense(con, query,createNew,ui);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	     DatabaseConnection.connectionClose(con);
	    }
	    return groups;
	  }
  public String getAccountOfExpenseLiability(String query,int createNew,TPCSUser ui){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getAccountOfExpenseLiability(con, query,createNew,ui);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	     DatabaseConnection.connectionClose(con);
	    }
	    return groups;
	  }
  public String getCostCenter(String query,int createNew,TPCSUser ui){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getCostCenter(con, query,createNew,ui);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	     DatabaseConnection.connectionClose(con);
	    }
	    return groups;
	  }
  public String getProfitCenter(String query,int createNew,TPCSUser ui){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getProfitCenter(con, query,createNew,ui);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	     DatabaseConnection.connectionClose(con);
	    }
	    return groups;
	  }
  public String getBSAccountGroupType(String query,int createNew,TPCSUser ui){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getBSAccountGroupType(con, query,createNew,ui);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	     DatabaseConnection.connectionClose(con);
	    }
	    return groups;
	  }
  
  public String getPLAccountGroupType(String query,int createNew,TPCSUser ui){
	    Connection con=null;
	    String groups = null;
	    try{
	      con = connection.getConnection(ui);      
	      groups = objAutoCompleteDAO.getPLAccountGroupType(con, query,createNew,ui);
	    }catch(Exception sqe){
	      sqe.printStackTrace();     
	    }finally{
	     DatabaseConnection.connectionClose(con);
	    }
	    return groups;
	  }
  public String getOperation(String query,int createNew,TPCSUser ui){
		Connection con=null;
		String str = null;
		try{
			
			con = connection.getConnection(ui);      
			str = objAutoCompleteDAO.getOperation(con, query,ui,createNew);
			
		}
		catch(Exception sqe){
			sqe.printStackTrace();     
		}
		finally{
			DatabaseConnection.connectionClose(con);
		}
		return str;
	}	
  public String getgrouptype(String query,int createNew,TPCSUser ui){
		Connection con=null;
		String str = null;
		try{
			
			con = connection.getConnection(ui);      
			str = objAutoCompleteDAO.getgrouptype(con, query,ui,createNew);
			
		}
		catch(Exception sqe){
			sqe.printStackTrace();     
		}
		finally{
			DatabaseConnection.connectionClose(con);
		}
		return str;
	}	
	 public String getparentgrouptype(String query,int createNew,TPCSUser ui){
		Connection con=null;
		String str = null;
		try{
			
			con = connection.getConnection(ui);      
			str = objAutoCompleteDAO.getparentgrouptype(con, query,ui,createNew);
			
		}
		catch(Exception sqe){
			sqe.printStackTrace();     
		}
		finally{
			DatabaseConnection.connectionClose(con);
		}
		return str;
	}
	 public String getSizeRange(String query,int createNew,TPCSUser ui){
			Connection con=null;
			String str = null;
			try{
				
				con = connection.getConnection(ui);      
				str = objAutoCompleteDAO.getSizeRange(con, query,ui,createNew);
				
			}
			catch(Exception sqe){
				sqe.printStackTrace();     
			}
			finally{
				DatabaseConnection.connectionClose(con);
			}
			return str;
		}	
	 
	 
	 public String getSizeRangeSize(String query,int createNew,TPCSUser ui,int dependId){
			Connection con=null;
			String str = null;
			try{
				
				con = connection.getConnection(ui);      
				str = objAutoCompleteDAO.getSizeRangeSize(con, query,ui,createNew,dependId);
				
			}
			catch(Exception sqe){
				sqe.printStackTrace();     
			}
			finally{
				DatabaseConnection.connectionClose(con);
			}
			return str;
		}	
	 public String getSize(String query,int createNew,TPCSUser ui){
			Connection con=null;
			String str = null;
			try{
				
				con = connection.getConnection(ui);      
				str = objAutoCompleteDAO.getSize(con, query,ui,createNew);
				
			}
			catch(Exception sqe){
				sqe.printStackTrace();     
			}
			finally{
				DatabaseConnection.connectionClose(con);
			}
			return str;
		}	
	 public String getColor(String query,int createNew,TPCSUser ui){
			Connection con=null;
			String str = null;
			try{
				
				con = connection.getConnection(ui);      
				str = objAutoCompleteDAO.getColor(con, query,ui,createNew);
				
			}
			catch(Exception sqe){
				sqe.printStackTrace();     
			}
			finally{
				DatabaseConnection.connectionClose(con);
			}
			return str;
		}
	 
	  public String getVariant(String query,int createNew,TPCSUser ui){
		    Connection con=null;
		    String groups = null;
		    try{
		      con = connection.getConnection(ui);      
		      groups = objAutoCompleteDAO.getVariant(con, query,ui,createNew);
		    }catch(Exception sqe){
		      sqe.printStackTrace();     
		    }finally{
		      try{
		        if(con!=null)
		           con.close();
		      }catch(SQLException se){
		        se.printStackTrace();
		      }
		    }
		    return groups;
		  }
	  
	  
	  public String getSizeShedule(String query,int createNew,TPCSUser ui){
		    Connection con=null;
		    String groups = null;
		    try{
		      con = connection.getConnection(ui);      
		      groups = objAutoCompleteDAO.getSizeShedule(con, query,ui,createNew);
		    }catch(Exception sqe){
		      sqe.printStackTrace();     
		    }finally{
		      try{
		        if(con!=null)
		           con.close();
		      }catch(SQLException se){
		        se.printStackTrace();
		      }
		    }
		    return groups;
		  }
	  
	  public String getAllReports(String query,int createNew,TPCSUser ui){
			Connection con=null;
			String groups = null;
			try{
				con = connection.getConnection(ui);      
				groups = objAutoCompleteDAO.getAllReports(con, query,createNew,ui);
			}catch(Exception sqe){
				sqe.printStackTrace();     
			}finally{
				DatabaseConnection.connectionClose(con);
			}
			return groups;
		}
	  
  public static AutoCompleteDAO objAutoCompleteDAO = new AutoCompleteDAO();
  public static DatabaseConnection connection = new DatabaseConnection();
}
