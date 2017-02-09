package com.alpha.tpcsfashion.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alpha.tpcsfashion.beans.HomePage;
import com.alpha.tpcsfashion.beans.TPCSUser;
import com.alpha.tpcsfashion.database.connection.DatabaseConnection;

public class HomePageDAO {

	public HomePage getHomePageData(Connection con, TPCSUser ui, HomePage homePage) throws SQLException {
		
		PreparedStatement pst=null;
		CallableStatement cst=null;
		ResultSet rs=null;
		try{
			homePage.setOrderValue("0");
			homePage.setInvoiceValue("0");
			homePage.setCashBankValue("0");
			homePage.setReceivableValue("0");
			homePage.setPayableValue("0");
			
			pst=con.prepareStatement(" select sum(credit_fcy)-sum(debit_fcy) from fin_tr ft "
					+ " inner join fin_tr_details ftd with(nolock) on ft.fin_tr_id =ftd.fin_tr_id and ft.fin_tr_type in('INV','RET')"
					+ " inner join account acc with(nolock) on ftd.account_id=acc.account_id"
					+ " inner join account_group ag with(nolock) on acc.group_id=ag.group_id"
					+ " inner join account_group_type agt with(nolock) on ag.group_type=agt.group_type"
					+ " where agt.group_type like '%sales%' or agt.group_type like '%income%'"
					+ " and ft.company_id=? and ft.location_id=? and ft.year_id=?");
			pst.setInt(1,ui.getCompanyId());
			pst.setInt(2,ui.getLocationId());
			pst.setInt(3,ui.getYearId());
			rs= pst.executeQuery();
			if(rs.next()){
				homePage.setInvoiceValue(rs.getString(1));
			}
			
			pst=con.prepareStatement(" select sum(debit_fcy)-sum(credit_fcy) "
					+ " from fin_tr ft "
					+ " inner join fin_tr_details ftd with(nolock) on ft.fin_tr_id =ftd.fin_tr_id "
					+ " inner join account acc with(nolock) on ftd.account_id=acc.account_id"
					+ " inner join account_group ag with(nolock) on acc.group_id=ag.group_id"
					+ " inner join account_group_type agt with(nolock) on ag.group_type=agt.group_type"
					+ " where agt.group_type like '%cash%' or agt.group_type like '%bank%' "
					+ " and ft.company_id=? and ft.location_id=? and ft.year_id=? ");
			pst.setInt(1,ui.getCompanyId());
			pst.setInt(2,ui.getLocationId());
			pst.setInt(3,ui.getYearId());
			rs= pst.executeQuery();
			if(rs.next()){
				homePage.setCashBankValue(rs.getString(1));
			}
			
			pst=con.prepareStatement(" select sum(debit_fcy)-sum(credit_fcy) "
					+ " from fin_tr ft "
					+ " inner join fin_tr_details ftd with(nolock) on ft.fin_tr_id =ftd.fin_tr_id "
					+ " inner join account acc with(nolock) on ftd.account_id=acc.account_id"
					+ " inner join account_group ag with(nolock) on acc.group_id=ag.group_id"
					+ " inner join account_group_type agt with(nolock) on ag.group_type=agt.group_type"
					+ " where agt.group_type like '%sundry debtor%' "
					+ " and ft.company_id=? and ft.location_id=? and ft.year_id=? ");
			pst.setInt(1,ui.getCompanyId());
			pst.setInt(2,ui.getLocationId());
			pst.setInt(3,ui.getYearId());
			rs= pst.executeQuery();
			if(rs.next()){
				homePage.setReceivableValue(rs.getString(1));
			}
			
			pst=con.prepareStatement(" select sum(credit_fcy)-sum(debit_fcy) "
					+ " from fin_tr ft "
					+ " inner join fin_tr_details ftd with(nolock) on ft.fin_tr_id =ftd.fin_tr_id "
					+ " inner join account acc with(nolock) on ftd.account_id=acc.account_id"
					+ " inner join account_group ag with(nolock) on acc.group_id=ag.group_id"
					+ " inner join account_group_type agt with(nolock) on ag.group_type=agt.group_type"
					+ " where agt.group_type like '%sundry creditor%' "
					+ " and ft.company_id=? and ft.location_id=? and ft.year_id=? ");
			pst.setInt(1,ui.getCompanyId());
			pst.setInt(2,ui.getLocationId());
			pst.setInt(3,ui.getYearId());
			rs= pst.executeQuery();
			if(rs.next()){
				homePage.setPayableValue(rs.getString(1));
			}
			pst=con.prepareStatement(" select sum(value_before_tax_fcy) "
					+ " from sales_order so"
					+ " inner join sales_order_details sod with(nolock) on so.so_id=sod.so_id"
					+ " where so.company_id=? and so.location_id=? and so.year_id=? ");
			pst.setInt(1,ui.getCompanyId());
			pst.setInt(2,ui.getLocationId());
			pst.setInt(3,ui.getYearId());
			rs= pst.executeQuery();
			if(rs.next()){
				homePage.setOrderValue(rs.getString(1));
			}
			
			
			//Key Performance Indicator
			
			List<HomePage> kpiList=new ArrayList<HomePage>();
			
			cst=con.prepareCall("{ call pr_get_db_kpi(?,?,?) } ");
			cst.setInt(1,ui.getCompanyId());
			cst.setInt(2,ui.getLocationId());
			cst.setInt(3,ui.getYearId());
			rs= cst.executeQuery();
			while(rs.next()){
				HomePage kpi=new HomePage();
				kpi.setSlNo(rs.getInt(1));
				kpi.setIndicator(rs.getString(2));
				kpi.setAnalysisPeriod(rs.getString(3));
				kpi.setCurValue(rs.getString(4));
				kpi.setPrevValue(rs.getString(5));
				kpi.setDiffValue(rs.getString(6));
				kpi.setCumValue(rs.getString(7));
				kpiList.add(kpi);
			}
			homePage.setKeyPerIndList(kpiList);
			
			//Cash in and out
			
			List<HomePage> cashInOutList=new ArrayList<HomePage>();
			
			cst=con.prepareCall("{ call pr_get_db_cash_in_out(?,?,?) } ");
			cst.setInt(1,ui.getCompanyId());
			cst.setInt(2,ui.getLocationId());
			cst.setInt(3,ui.getYearId());
			rs= cst.executeQuery();
			while(rs.next()){
				HomePage cashInOut=new HomePage();
				cashInOut.setMonhtNumber(rs.getInt(1));
				cashInOut.setMonthName(rs.getString(2));
				cashInOut.setDebitFcy(rs.getString(3));
				cashInOut.setCreditFcy(rs.getString(4));
				cashInOut.setDisplayOrder(rs.getInt(5));
				cashInOutList.add(cashInOut);
			}
			
			homePage.setCashInOutList(cashInOutList);
			
			//Profit analysis
			
			List<HomePage> profitAnalysisList=new ArrayList<HomePage>();
			
			cst=con.prepareCall("{ call pr_get_db_profit_analysis(?,?,?) } ");
			cst.setInt(1,ui.getCompanyId());
			cst.setInt(2,ui.getLocationId());
			cst.setInt(3,ui.getYearId());
			rs= cst.executeQuery();
			while(rs.next()){
				HomePage profitAnalysis=new HomePage();
				profitAnalysis.setMonhtNumber(rs.getInt(1));
				profitAnalysis.setYear(rs.getInt(2));
				profitAnalysis.setMonthName(rs.getString(3));
				profitAnalysis.setYearMonth(rs.getString(4));
				profitAnalysis.setIncome(rs.getString(5));
				profitAnalysis.setExpense(rs.getString(6));
				profitAnalysis.setProfit(rs.getString(7));
				profitAnalysis.setDisplayOrder(rs.getInt(8));
				profitAnalysisList.add(profitAnalysis);
			}
			
			homePage.setProfitAnalysisList(profitAnalysisList);

		}
		
		finally{
			DatabaseConnection.closeAll(pst, rs);
		}
		return homePage;
	}

}
