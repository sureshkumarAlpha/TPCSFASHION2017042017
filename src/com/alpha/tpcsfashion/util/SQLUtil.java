package com.alpha.tpcsfashion.util;




public class SQLUtil {


	public static String GET_USER_VALID = "  SELECT ui.user_id ,ui.user_login_name,ci.database_name,ci.server_ip,ci.sql_username,ci.sql_password,ui.client_id,ci.datasource_name FROM users_info ui,clients_info ci WHERE ui.client_id=ci.client_id and ui.user_login_name = ? ";
	public static String GET_USER_QUESTION =" select question ,answer,user_id from password_change where user_id = ? and client_id=?";
	public static String GET_USER_PASSWORD_DETAIL =" select ui.user_id,ui.user_login_name,ci.email_id,ui.user_password from users_info ui,clients_info ci where ui.client_id = ci.client_id and ui.user_id = ? and ui.client_id=?";


	public static String VALIDATE_LOGIN = "SELECT ui.first_name,ui.last_name,ui.user_id,ci.datasource_name,ci.database_name,ci.server_ip,ci.sql_username,ci.sql_password,ui.client_id,ci.company_name,l.language_code,ci.property_filename FROM users_info ui WITH(NOLOCK),clients_info ci WITH(NOLOCK),language l WITH(NOLOCK) WHERE ui.client_id = ci.client_id AND ui.language_id =l.language_id AND ui.user_login_name=? AND ui.user_password=? AND ui.is_delete=0";

	public static String GET_USER_COMPANY_YEAR=" select company_id,year_id from  tpcs_user where tpcs_user_id=? ";
	public static String UPDATE_USER_COMPANY_YEAR="update tpcs_user set company_id=?,year_id=? where tpcs_user_id=?";

	public static String UPDATE_LOGIN_TIME = " UPDATE users_info set login_time=GETDATE() WHERE client_id=? AND user_id=? ";

	public static String INSERT_LOGIN_INFO = " INSERT INTO login_info(user_id,login_time) VALUES(?,GETDATE())";

	public static String GET_ENTITY_DETAILS = " SELECT entity_id,customer_id,factory_id  FROM tpcs_user WITH(NOLOCK) WHERE tpcs_user_id=? ";


	public static String GET_CLIENT_SERVER = "SELECT ci.email_id,ci.email_password,ci.mail_server,ci.port_number FROM users_info ui WITH(NOLOCK),clients_info ci WITH(NOLOCK) WHERE ui.client_id = ci.client_id AND ui.user_id=?";
	/*
	 public static String MAIN_MENU_ITEMS =  "SELECT DISTINCT m.module_id,m.module_name,m.url,m.display_order FROM module m WITH(NOLOCK),documents d WITH(NOLOCK),tpcs_user tu WITH(NOLOCK), "+
    										 "tpcs_user_rights tur WITH(NOLOCK) WHERE m.module_id=d.module_id AND "+ 
    										 "tur.tpcs_user_id=tu.tpcs_user_id AND tu.tpcs_user_id=? ORDER BY m.display_order";

	 public static String SUB_MENU_ITEMS =  "SELECT d.document_id,d.document_name,d.url FROM documents d WITH(NOLOCK),tpcs_user tu WITH(NOLOCK),tpcs_user_rights tur WITH(NOLOCK) "+
	 										"WHERE d.document_id=tur.document_id AND tur.tpcs_user_id=tu.tpcs_user_id AND d.module_id=? AND tu.tpcs_user_id=? ORDER BY d.display_order";
	 */

	//	  public static final String GET_MAIN_MENUS = "SELECT module_id,module_name,url,display_order FROM module WITH(NOLOCK) ORDER BY display_order";

	public static final String GET_MAIN_MENUS = "SELECT  DISTINCT m.module_id,m.module_name,m.url,m.display_order "
			+ " FROM  module m  "
			+ " inner JOIN documents d ON  m.module_id=d.module_id "
			+ " inner JOIN subdocuments sd ON d.document_id=sd.document_id and sd.active_tag=1 "
			+ " inner JOIN tpcs_user_rights tur ON  sd.subdocument_id = tur.subdocument_id "
			+ " inner JOIN tpcs_user tu ON tur.tpcs_user_id=tu.tpcs_user_id "
			+ " where tu.tpcs_user_id=?"
			+ " ORDER BY m.display_order";
	
	public static final String IS_SUBDOC_EXISTS_FOR_MODULE="select count(*) from tpcs_user_rights tu"
			+ " inner join subdocuments sub on tu.subdocument_id=sub.subdocument_id"
			+ " inner join documents doc on sub.document_id=doc.document_id "
			+ " where tu.subdocument_id in(select subdocument_id from subdocuments where document_id in (select document_id from documents where module_id=12))"
			+ " and tu.entry_permission=1 and tu.tpcs_user_id=? and doc.module_id=? ";
	
	public static final String IS_SUBDOC_EXISTS_FOR_DOCUMENT="select count(*) from tpcs_user_rights tu"
			+ " inner join subdocuments sub on tu.subdocument_id=sub.subdocument_id"
			+ " inner join documents doc on sub.document_id=doc.document_id "
			+ " where tu.subdocument_id in(select subdocument_id from subdocuments where document_id in (select document_id from documents where module_id=12))"
			+ " and tu.entry_permission=1 and tu.tpcs_user_id=? and doc.module_id=?  and doc.document_id=? ";

	public static final String GET_REPORT_NAMES= "SELECT  DISTINCT sd.url,sd.subdocument_name, sd.display_order "
			+ " FROM  module m  "
			+ " inner JOIN documents d ON  m.module_id=d.module_id "
			+ " inner JOIN subdocuments sd ON d.document_id=sd.document_id and sd.active_tag=1 "
			+ " inner JOIN tpcs_user_rights tur ON  sd.subdocument_id = tur.subdocument_id "
			+ " inner JOIN tpcs_user tu ON tur.tpcs_user_id=tu.tpcs_user_id "
			+ " where d.visibility=2 and tu.tpcs_user_id=? "
			+ " ORDER BY sd.display_order";
	
	public static final String GET_REPORT_MAIN_MENUS = "SELECT  DISTINCT m.module_id,m.module_name,m.url,m.display_order "
			+ " FROM  module m  "
			+ " inner JOIN documents d ON  m.module_id=d.module_id "
			+ " inner JOIN subdocuments sd ON d.document_id=sd.document_id and sd.active_tag=1 "
			+ " inner JOIN tpcs_user_rights tur ON  sd.subdocument_id = tur.subdocument_id "
			+ " inner JOIN tpcs_user tu ON tur.tpcs_user_id=tu.tpcs_user_id "
			+ " where d.visibility=2 and tu.tpcs_user_id=? "
			+ " ORDER BY m.display_order";

	//	  public static final String GET_MAIN_DOCUMENTS = "SELECT document_id,document_name,url,display_order FROM documents WITH(NOLOCK) WHERE module_id=? ORDER BY display_order";

	public static final String GET_MAIN_DOCUMENTS= "SELECT  DISTINCT d.document_id,d.document_name,d.url,d.display_order "
			+ " FROM  module m  "
			+ " inner JOIN documents d ON  m.module_id=d.module_id "
			+ " inner JOIN subdocuments sd ON d.document_id=sd.document_id and sd.active_tag=1 "
			+ " inner JOIN tpcs_user_rights tur ON  sd.subdocument_id = tur.subdocument_id "
			+ " inner JOIN tpcs_user tu ON tur.tpcs_user_id=tu.tpcs_user_id "
			+ " where d.visibility=1 and tu.tpcs_user_id=? and m.module_id=? "
			+ " ORDER BY d.display_order";

	public static final String GET_REPORT_DOCUMENTS= "SELECT  DISTINCT d.document_id,d.document_name,d.url,d.display_order "
			+ " FROM  module m  "
			+ " inner JOIN documents d ON  m.module_id=d.module_id "
			+ " inner JOIN subdocuments sd ON d.document_id=sd.document_id "
			+ " inner JOIN tpcs_user_rights tur ON  sd.subdocument_id = tur.subdocument_id "
			+ " inner JOIN tpcs_user tu ON tur.tpcs_user_id=tu.tpcs_user_id "
			+ " where d.visibility=2 and tu.tpcs_user_id=? and m.module_id=? "
			+ " ORDER BY d.display_order";
	//	  public static final String GET_MAIN_SUB_DOCUMENTS = " SELECT subdocument_id,subdocument_name,url,display_order,view_page_sm FROM subdocuments  WITH(NOLOCK) WHERE document_id=? ORDER BY display_order";

	public static final String GET_MAIN_SUB_DOCUMENTS= "SELECT  DISTINCT sd.subdocument_id,sd.subdocument_name,sd.url,sd.display_order,sd.view_page_sm "
			+ " FROM  module m  "
			+ " inner JOIN documents d ON  m.module_id=d.module_id "
			+ " inner JOIN subdocuments sd ON d.document_id=sd.document_id and sd.active_tag=1 "
			+ " inner JOIN tpcs_user_rights tur ON  sd.subdocument_id = tur.subdocument_id "
			+ " inner JOIN tpcs_user tu ON tur.tpcs_user_id=tu.tpcs_user_id "
			+ " where tu.tpcs_user_id=? and m.module_id=? and d.document_id=?"
			+ " ORDER BY sd.display_order";
	
	
	public static final String GET_NEW_TRANS_SUB_DOCUMENTS="SELECT  DISTINCT sd.subdocument_id,sd.subdocument_name,sd.url,sd.display_order,sd.view_page_sm "+ 
	 " FROM  module m  "+
	 " inner JOIN documents d ON  m.module_id=d.module_id "+ 
	 " inner JOIN subdocuments sd ON d.document_id=sd.document_id and sd.active_tag=1 "+ 
	 "inner JOIN tpcs_user_rights tur ON  sd.subdocument_id = tur.subdocument_id and tur.entry_permission=1 "+
	 " inner JOIN tpcs_user tu ON tur.tpcs_user_id=tu.tpcs_user_id "+
	 " where tu.tpcs_user_id=? and m.module_id=? and d.document_id=? "+
	 " ORDER BY sd.display_order ";
	 

	public static final String GET_REPORT_SUB_DOCUMENTS= "SELECT  DISTINCT sd.subdocument_id,sd.subdocument_name,sd.url,sd.display_order,sd.view_page_sm,sd.description,isnull(sd.parent_subdocument_id,0) "
			+ " FROM  module m  "
			+ " inner JOIN documents d ON  m.module_id=d.module_id "
			+ " inner JOIN subdocuments sd ON d.document_id=sd.document_id "
			+ " inner JOIN tpcs_user_rights tur ON  sd.subdocument_id = tur.subdocument_id "
			+ " inner JOIN tpcs_user tu ON tur.tpcs_user_id=tu.tpcs_user_id "
			+ " where d.visibility=2 and tu.tpcs_user_id=? and m.module_id=? and d.document_id=?"
			+ " ORDER BY sd.display_order";


	/*public static String MAIN_MENU_ITEMS = " SELECT DISTINCT m.module_id,m.module_name,m.url,m.display_order FROM module m WITH(NOLOCK),documents d WITH(NOLOCK),subdocuments sd,tpcs_user tu WITH(NOLOCK), "+
	" tpcs_user_rights tur WITH(NOLOCK) WHERE m.module_id=d.module_id AND d.document_id=sd.document_id AND tur.tpcs_user_id=tu.tpcs_user_id AND "+
	" sd.subdocument_id = tur.subdocument_id and tu.tpcs_user_id=? ORDER BY m.display_order ";*/

	public static String MAIN_MENU_ITEMS = " SELECT  DISTINCT m.module_id,m.module_name,m.url,m.display_order FROM  module m "+ 
			" inner JOIN documents d ON  m.module_id=d.module_id "+
			" inner JOIN subdocuments sd ON d.document_id=sd.document_id and sd.active_tag=1 "+
			" inner JOIN tpcs_user_rights tur ON  sd.subdocument_id = tur.subdocument_id "+
			" inner JOIN tpcs_user tu ON tur.tpcs_user_id=tu.tpcs_user_id AND tu.tpcs_user_id=? "+
			" ORDER BY m.display_order ";

	public static String REPORT_MAIN_MENU_ITEMS = " SELECT  DISTINCT m.module_id,m.module_name,m.url,m.display_order FROM  module m "+ 
			" inner JOIN documents d ON  m.module_id=d.module_id "+
			" inner JOIN subdocuments sd ON d.document_id=sd.document_id and sd.active_tag=1 "+
			" inner JOIN tpcs_user_rights tur ON  sd.subdocument_id = tur.subdocument_id "+
			" inner JOIN tpcs_user tu ON tur.tpcs_user_id=tu.tpcs_user_id WHERE d.visibility=2 and tu.tpcs_user_id=? "+
			" ORDER BY m.display_order ";

	public static String SUB_MENU_ITEMS = " SELECT distinct d.document_id,d.document_name,d.url ,d.display_order FROM documents d WITH(NOLOCK),tpcs_user tu WITH(NOLOCK)," +
			" tpcs_user_rights tur WITH(NOLOCK),subdocuments sd WITH(NOLOCK) WHERE d.document_id=sd.document_id AND sd.subdocument_id = tur.subdocument_id and tur.tpcs_user_id=tu.tpcs_user_id AND d.module_id=? AND tu.tpcs_user_id=? ORDER BY d.display_order ";

	public static String SUB_DOCUMENT_ITEMS = " SELECT sd.subdocument_id,sd.subdocument_name,sd.url,d.document_id FROM subdocuments sd WITH(NOLOCK),documents d WITH(NOLOCK),module m  WITH(NOLOCK) ,tpcs_user_rights tur WITH(NOLOCK),tpcs_user tu WITH(NOLOCK) WHERE sd.document_id = d.document_id and d.module_id = m.module_id and m.module_id =? and tur.subdocument_id = sd.subdocument_id and tur.tpcs_user_id = tu.tpcs_user_id and tu.tpcs_user_id = ? and doc_type=1 order by sd.display_order ";

	public static String CREATE_NEW_ITEMS = " select cn.module_id,sub.subdocument_name,sub.new_url from set_CreateNew  cn "+ 
			" inner join subdocuments  sub on sub.subdocument_id=cn.subdocument_id "+
			" inner join tpcs_user_rights tur on cn.subdocument_id=tur.subdocument_id and entry_permission=1 "+
			" where tur.tpcs_user_id=? "+
			" order by cn.module_id ";

	public static String QUICK_LINKS = " select ql.module_id,sub.subdocument_name,sub.url from set_quicklinks  ql "+ 
			" inner join subdocuments  sub on sub.subdocument_id=ql.subdocument_id "+
			" inner join tpcs_user_rights tur on ql.subdocument_id=tur.subdocument_id and entry_permission=1 "+
			" where tur.tpcs_user_id=? "+
			" order by ql.module_id ";

	public static String UPDATE_LOGOUT_TIME = " UPDATE users_info set logout_time=GETDATE() WHERE client_id=? AND user_id=? ";

	public static String INSERT_LOGOUT_INFO = " INSERT INTO login_info(user_id,logout_time) VALUES(?,GETDATE())";

	public static String GET_SUBDOCUMENTS=" select subdocument_id,subdocument_name from subdocuments ";


	public static String GET_CURRENCY_DATA="select currency_id,currency_name,symbol from currency_master where currency_id=? ";

	/* Item Group */
	public static String GROUP_COUNT = " select count(*) from groups WITH(NOLOCK)";

	public static String INSERT_INTO_GROUPS="INSERT INTO groups(Company_id,location_id,group_code,group_name,group_type_id,Parent_group_id,entered_by,Host_Name,entered_on,status) "+        
			" VALUES (?,?,?,?,?,?,?,host_name(),getdate(),?)  ";

	public static String GET_GROUP_ID=" SELECT TOP 1 group_id FROM groups ORDER BY group_id DESC  ";

	public static String INSERT_INTO_GROUPCONFIG="INSERT INTO groups_config(Company_id,Location_id,group_id,is_itrack_required,is_barcode_required,status)  VALUES(?,?,?,?,?,?)   ";

	public static String INSERT_GROUP = " insert into groups(group_name,group_code,short_name,grouptype_id,grouped_under,primary_group,group_level,Companyid,entered_by,entered_on,location_id) values(?,?,?,?,?,?,?,?,?,getdate(),?) ";
	//	public static String GET_SUB_GROUP = " select g.group_id,g.group_name,g.group_code,g.short_name,gt.group_type,gt.grouptype_id,g.group_level from groups g WITH(NOLOCK) ,group_type gt WITH(NOLOCK) where g.grouptype_id = ? and gt.grouptype_id = g.grouptype_id ";


	public static String GET_GROUP_INFO = " select g1.grouptype_id,g1.group_code,g1.group_name,g1.short_name,g1.grouped_under,g2.group_name as groupunder_name,g1.group_level,g1.group_id from groups g1 left outer join groups g2 on g1.grouped_under = g2.group_id where g1.group_id = ? ";

	public static String UPDATE_GROUP = " update groups set grouptype_id =?,group_code = ?,group_name =?,short_name=?,grouped_under=?,primary_group = ?,group_level=?,updated_by=?,updated_on=getdate() where group_id =? ";

	public static String DELETE_GROUP = " delete from groups where group_id = ? ";

	public static String GET_GROUP_TYPE_LIST = " SELECT * FROM GROUP_TYPE WITH(NOLOCK) ";
	public static String GET_REF3_LIST = " SELECT distinct ref3 FROM ref3_master WITH(NOLOCK) ";
	public static String INSERT_REF3 = " insert into ref3_master values(?)";
	public static String IS_REF3_EXIST = "select count(*) from ref3_master where ref3=? ";
	public static String IS_GROUP_EXIST = " select count(*) from groups where ( group_name=? or group_code =? ) and group_id !=? ";

	public static String EXIST_IN_GROUP_UNDER = " select count(*) from groups WITH(NOLOCK) where grouped_under = ? ";

	public static String EXIST_IN_MATERIAL = " select count(*) from material WITH(NOLOCK) where group_id = ? ";

	public static String GROUP_CODE_LENGTH = " select groupcode_len from matcode_setting WITH(NOLOCK)";

	public static String IS_MAT_EXIST = " select count(*) from material where Material_name=?  ";

	//	public static String INSERT_INTO_MATERIAL=" INSERT INTO material (Material_code,Material_name,spec1,spec2,spec3,spec4,spec5,spec6,spec7,sku,stock_location,bin_no,reorder_level,cost_price,selling_price,group_id,sales_desc,purchase_desc,Host_Name,Entered_by,Entered_On) "+                        
	//            " VALUES(?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,host_name(),?,getDate()) ";   

	public static String INSERT_INTO_MATERIAL=" insert into material (Material_code,Material_name,SKU,group_id,stock_location,bin_no,reorder_level,selling_price,    "
			+ " Cost_price,sales_desc,purchase_desc,Status,Track_inventory,Purchase_applicable,Sales_applicable,    "
			+ " Inventory_Account_id,COGS_Account_id,Host_Name,IP_Address,Entered_by,Entered_On)    "
			+ " values(?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,host_name(),?,?,getutcDate())";    

	public static String INSERT_INTO_UOM=" INSERT INTO uom_master (uom) values(?)";

	/* Company and year */
	public static String SELECT_YEAR = " SELECT YearId,CONVERT(varchar,StartDate,106) StartDate,CONVERT(varchar,EndDate,106) EndDate FROM YearTable WHERE Client_id=? order by yearid desc";
	public static String SELECT_USER_YEAR=" select yearid,convert(nvarchar,startdate,105),convert(nvarchar,enddate,105) from yeartable where yearid=? ";
	public static String SELECT_COMPANY = "SELECT * FROM company WHERE Client_id=?";
	public static String GET_COMPANY = " select company_id,isnull(company_name,''),isnull(address1,''),isnull(address2,''),isnull(address3,''),isnull(country,''),isnull(phone_no,''),isnull(fax_no,''),isnull(tax_reg_no1,''),isnull(tax_reg_no2,''),isnull(email_id,''),party_id,currency_id from company with(nolock) where company_id =? and client_id=? ";
	public static String GET_CURRENCY = "select currency_id,currency_name from currency_master where currency_id=?";

	/* Sales Order */

	/* SAve as SalesOrder From Quotation*/
	public static String INSERT_SALES_ORDER_HEADER="  INSERT INTO sales_order(company_id,year_id,location_id,so_no,so_date,version_no,customer_order_no,party_id,invoice_to_party_id, "+      
			" ship_to_party_id,tag,quote_id,currency_id,exchange_rate,executive_id,entered_by,entry_date,host_name,ip_address) "+
			" SELECT ?,?,?,?,quote_date,version_no,reference_no,party_id,invoice_to_party_id,ship_to_party_id,tag,quote_id,currency_id,exchange_rate,executive_id,?,getdate(),ip_address "+
			" FROM sales_quotation WHERE quote_id =? "; 

	public static String INSERT_SALES_ORDER_DETAIL="  INSERT INTO sales_order_details (company_id,year_id,location_id,so_id,so_date,material_id,uom,quantity,price_fcy,price_lcy,discount_percent,discount_fcy,value_before_tax_FCY,value_before_tax_LCY, "+
			" expected_date,remarks,variant_id,Ed_percentage,Ecess_percentage,ShEcess_percentage,VAT_percentage,CST_percentage,Service_tax_percentage,Ed_FCY,Ecess_FCY,ShEcess_FCY,VAT_FCY,CST_FCY,Service_tax_FCY,value_after_tax_FCY,value_after_tax_LCY) "+  
			" SELECT ?,?,?,?,quote_date,material_id,uom,quantity,price_fcy,price_lcy,discount_percent,discount_fcy,value_before_tax_FCY,value_before_tax_LCY, "+
			" required_date,remarks,variant_id,Ed_percentage,Ecess_percetage,ShEcess_percetage,VAT_percetage,CST_percetage,Service_tax_percetage,Ed_FCY,Ecess_FCY,ShEcess_FCY,VAT_FCY,CST_FCY,Service_tax_FCY,value_after_tax_FCY,value_after_tax_LCY "+
			" FROM sales_quotation_details WHERE quote_id =? ";

	public static String INSERT_SO_ATTACH_FROM_SQ=" INSERT INTO Sales_Order_URL(company_id,year_id,location_id,so_id,File_type,URL) "+
			" SELECT company_id,year_id,location_id,?,File_type,URL from Sales_Quotation_URL where quote_id=?";

	public static String GET_SALES_ORDER_ID="  SELECT quote_id FROM sales_quotation WITH(NOLOCK) WHERE quote_no=? ";

	public static String GET_REPORT_NAME_LIST = " SELECT sd.subdocument_id,sd.subdocument_name,sd.url,d.document_id FROM subdocuments sd WITH(NOLOCK),documents d WITH(NOLOCK),module m  WITH(NOLOCK) ,tpcs_user_rights tur WITH(NOLOCK),tpcs_user tu WITH(NOLOCK) WHERE sd.document_id = d.document_id and d.module_id = m.module_id and m.module_id =? and tur.subdocument_id = sd.subdocument_id and tur.tpcs_user_id = tu.tpcs_user_id and tu.tpcs_user_id = ? and d.document_id=?  order by sd.display_order ";


	public static String INSERT_SALES_ORDER_ATTACHMENT=" INSERT INTO Sales_Order_URL(company_id,year_id,location_id,so_id,File_type,URL) VALUES(?,?,?,?,?,?) ";

	public static String  INSERT_SO_USER=  " if not exists(select * from Sales_Order_followers where user_id=? and so_id=?)"
			+ " begin"
			+ " INSERT INTO Sales_Order_followers(company_id,year_id,location_id,so_id,User_id)values(?,?,?,?,?)"
			+ " end ";

	public static String  INSERT_DEF_SALES_ORDER_USER= " INSERT INTO Sales_Order_followers(company_id,year_id,location_id,so_id,User_id)values(?,?,?,?,?)";

	/*	public static String GET_SALES_ORDER = " SELECT  so.so_no,g.group_name,m.Material_code,m.Material_name,m.spec1 +' ' "+ 
		" +ISNULL(m.spec2,'')+' '+ISNULL(m.spec3,'')+' '+ISNULL(m.spec4,'')+' '+ "+
		" ISNULL(m.spec5,'')+' '+ISNULL(m.spec6,'')+' '+ISNULL(m.spec7,'') as 'desc', "+
		" sod.quantity,sod.uom,sod.price_fcy,ISNULL(CONVERT(nvarchar(10),sod.expected_date,105),'') 'req_date', "+
		" sod.quantity*sod.price_fcy as val_before_dis ,sod.discount_percent,ROUND(sod.discount_fcy,2) as discount_fcy,ROUND(sod.value_fcy,2) as value_fcy,sod.remarks,currency_name,so.so_id,sod.so_det_id  "+
		" FROM sales_order so INNER JOIN sales_order_details sod on so.company_id=sod.company_id AND "+
		" so.year_id=sod.year_id AND so.location_id=sod.location_id AND so.so_id=sod.so_id "+
		" INNER JOIN material m on sod.material_id=m.Material_id "+
		" INNER JOIN groups g on g.group_id=m.group_id " +
		" LEFT JOIN currency_master cm on  cm.currency_id=so.currency_id ";*/

	public static String GET_SALES_ORDER = " SELECT  so.prefix+convert(nvarchar,so.so_no) as so_no,convert(nvarchar,so.so_date,105) as so_date,g.group_name,p.party_name,so.Bill_to_address,so.Ship_to_address,so.Internal_Memo,so.email_sent_to,m.Material_code,m.Material_name,sod.material_description as 'desc', "+
			" sod.quantity,sod.sku,sod.price_fcy,ISNULL(CONVERT(nvarchar(10),sod.expected_date,105),'') 'req_date', "+
			" round(sod.value_before_tax_FCY,2) as value_before_tax_fcy ,round(sod.discount_percent,2) as discount_percent,ROUND(sod.discount_fcy,2) as discount_fcy,sod.remarks,cm.currency_name,v.variant_name,"+
			" round(sod.Ed_percentage,2) as ed_percentage,round(sod.Ecess_percentage,2) as ecess_percentage,round(sod.ShEcess_percentage,2) as shecess_percentage,round(sod.VAT_percentage,2) as vat_percentage,round(sod.CST_percentage,2) as cst_percentage,round(sod.GST_percentage,2) as gst_percentage,round(sod.Service_tax_percentage,2) as service_tax_percentage, "+
			" round(sod.ED_FCY,2) as ed_fcy,round(sod.Ecess_FCY,2) as ecess_fcy,round(sod.ShEcess_FCY,2) as shecess_fcy,round(sod.VAT_FCY,2) as vat_fcy,round(sod.CST_FCY,2) as cst_fcy,round(sod.GST_FCY,2) as gst_fcy,round(sod.Service_tax_FCY,2) as service_tax_fcy,round(sod.value_after_tax_FCY,2) as value_after_tax_fcy,"+
			" so.so_id,sod.so_det_id ,isnull(sq.prefix,'')+convert(nvarchar,sq.quote_no) as quote_no "+
			" FROM sales_order so INNER JOIN sales_order_details sod on so.company_id=sod.company_id AND "+
			" so.year_id=sod.year_id AND so.location_id=sod.location_id AND so.so_id=sod.so_id "+
			" left join sales_quotation sq with(nolock) on sq.quote_id=so.quote_id" +
			" INNER JOIN material m on sod.material_id=m.Material_id "+
			" INNER JOIN groups g on g.group_id=m.group_id " +
			" left join party p on so.party_id=p.party_id" +
			" left join variant_master v on v.variant_id=sod.variant_id "+
			/*	" inner join uom_master um with(nolock) on sod.uom=um.uom " +*/
			" LEFT JOIN currency_master cm on  cm.currency_id=so.currency_id "+
			" Left Join sales_order_dynamic sodf on so.so_id = sodf.so_id "+
			" Left Join sales_order_Details_Dynamic soddf on sod.so_det_id = soddf.so_det_id  ";

	public static String SELECT_SALES_ORDER_DETAIL_DYNAMIC_BY_ID = " Select sdf.*, sodf.* "
			+ " FROM  "
			+ " sales_order so (NOLOCK) "
			+ " Join sales_order_details sod on so.so_id = sod.so_id "
			+ " Left Join sales_order_dynamic sdf on so.so_id = sdf.so_id "
			+ " Left Join sales_order_details_dynamic sodf on sod.so_det_id = sodf.so_det_id "
			+ " Where so.so_id=? AND sod.so_det_id=? ";





	public static String SALES_ORDER_PAGE_COUNT = " SELECT  count(so.so_id) FROM sales_order so INNER JOIN sales_order_details sod on so.company_id=sod.company_id AND "+ 
			" so.year_id=sod.year_id AND so.location_id=sod.location_id AND so.so_id=sod.so_id "+ 
			" INNER JOIN material m on sod.material_id=m.Material_id "+
			" INNER JOIN groups g on g.group_id=m.group_id "+
			" left join party p on so.party_id=p.party_id" +
			" left join variant_master v on v.variant_id=sod.variant_id "+
			" LEFT JOIN currency_master cm on  cm.currency_id=so.currency_id "+
			" Left Join sales_order_dynamic sodf on so.so_id = sodf.so_id "+
			" Left Join sales_order_Details_Dynamic soddf on sod.so_det_id = soddf.so_det_id  ";

	public static String GET_ID_NAME = "select ?c1,?c2 from ?t ";

	public static String GET_SALES_ORDER_HEADER = " select  so.so_id,so.so_no,so.prefix,convert(nvarchar,so.so_date,105) as 'so date',so.customer_order_no,so.party_id,  "
			+ " p.party_name as 'customer',so.tag,so.currency_id,  "
			+ " so.exchange_rate,so.executive_id,e.employee_name,so.version_no,so.remarks ,so.quote_id,sq.prefix+convert(nvarchar,sq.quote_no) as  quote_no,"
			+ " isnull(so.email_sent_to,'') email_sent_to, isnull(so.bill_to_address,'') bill_to_address, isnull(so.ship_to_address,'') ship_to_address,isnull(so.internal_memo,'') internal_memo,cm.currency_name,so.charge_fcy1,so.charge_fcy2,so.charge_fcy3 "
			+ " ,so.charge_acc_id1,isnull(acc1.account_name,'') as charge1,so.charge_acc_id2 ,isnull(acc2.account_name,'') as charge2,so.charge_acc_id3 ,isnull(acc3.account_name,'') as charge3,so.payment_terms,so.credit_days "
			+ " from sales_order so "
			+ " left join sales_quotation sq with(nolock) on sq.quote_id=so.quote_id "
			+ " inner join party p on p.party_id=so.party_id "
			+ " LEFT JOIN currency_master cm on  cm.currency_id=so.currency_id "
			+ " left join employees e on e.employee_id=so.executive_id  "
			+ " left join account acc1 with(nolock) on so.charge_acc_id1=acc1.account_id"
			+ " left join account acc2 with(nolock) on so.charge_acc_id2=acc2.account_id"
			+ " left join account acc3 with(nolock) on so.charge_acc_id3=acc3.account_id"
			+ " where so.company_Id=? and so.year_id=? and so.location_id=? and so.so_id= ?  ";

	public static String SELECT_SALES_ORDER_HEADER_DYNAMIC = " Select sodf.* "
			+ " FROM  "
			+ " sales_order so (NOLOCK) "
			+ " Left Join sales_order_dynamic sodf on so.so_id = sodf.so_id "
			+ " Where so.so_id=? ";


	public static String GET_SALES_ORDER_DETAIL_FOR_GRID = " select g.group_id,g.group_name,m.Material_id,m.Material_name,sod.quantity,sod.sku,sod.price_fcy, " +
			" convert(nvarchar,sod.expected_date,105) as 'expected date',sod.quantity*sod.price_fcy as 'value before discount', " +
			" sod.discount_percent,sod.discount_fcy,sod.value_before_tax_FCY,sod.remarks,sod.so_det_id,var.variant_id,var.variant_name,sod.material_description ," +
			" sod.Ed_percentage,sod.Ecess_percentage,sod.ShEcess_percentage,sod.VAT_percentage,sod.CST_percentage,sod.Service_tax_percentage,sod.tax_group_id  "+
			" from sales_order_details sod " +
			" inner join material m on sod.material_id=m.Material_id " +
			" inner join groups g on g.group_id=m.group_id " +
			" left join variant_master var on sod.variant_id=var.variant_id " +
			/*	" inner join uom_master um with(nolock) on sod.uom=um.uom " +*/
			" where sod.so_id= ? order by sod.so_det_id ";


	public static String SELECT_SALES_ORDER_DETAIL_DYNAMIC = " Select soddf.* "
			+ " FROM  "
			+ " sales_order so (NOLOCK) "
			+ " Left Join sales_order_Details_Dynamic soddf on so.so_id = soddf.so_id "
			+ " Where so.so_id=? ";

	public static String SELECT_SALES_ORDER_ATTACHMENT=" SELECT so_id,File_type,isnull(URL,''),reverse(substring(reverse(URL), 1,charindex('/', reverse(URL))-1)) from Sales_Order_URL where so_id=? ";
	//PURCHASE ORDER
	
	public static String CHECK_PO_AUTO_NUMBER_EXISTENCE="select count(*) from po where po_no=? and po_prefix=? and company_id=? and location_id=? and year_id=?";

	public static String GET_PO_TRANSACTION_CREATED_PREFIX="select distinct po_prefix from ?header_table where company_id=? and location_id=? and year_id=? ";
	
	public static String GET_PURCHASE_ORDERS = "  SELECT  po.prefix+convert(nvarchar,po.Po_no) as Po_no,so.prefix+convert(nvarchar,so.so_no) as so_no,convert(nvarchar,po.Po_date,105) as Po_date,p.party_name ,po.email_sent_to,po.Bill_to_address,po.Ship_to_address,po.Internal_Memo,po.Reference_No,g.group_name,m.Material_code,m.Material_name,pod.material_description, "+    
			" pod.quantity,pod.sku,round(pod.price_fcy,3) as price_fcy,   "+
			" pod.quantity*pod.price_fcy as val_before_dis ,pod.discount_percent,ROUND(pod.discount_fcy,2) as discount_fcy,ROUND(pod.value_before_tax_FCY,2) as value_before_tax_fcy,pod.remarks,cm.currency_name,v.variant_name,   "+
			" round(pod.Ed_percentage,2) as ed_percentage,round(pod.Ecess_percentage,2) as ecess_percentage,round(pod.ShEcess_percentage,2) as shecess_percentage,round(pod.VAT_percentage,2) as vat_percentage,round(pod.cst_percentage,2) as cst_percentage,round(pod.gst_percentage,2) as gst_percentage,round(pod.Service_tax_percentage,2) as service_tax_percentage,   "+
			" round(pod.ED_FCY,2) as ed_fcy,round(pod.Ecess_FCY,2) as ecess_fcy,round(pod.ShEcess_FCY,2) as shecess_fcy,round(pod.VAT_FCY,2) as vat_fcy,round(pod.CST_FCY,2) as cst_fcy,round(pod.GST_FCY,2) as gst_fcy,round(pod.Service_tax_FCY,2) as service_tax_fcy,round(pod.value_after_tax_FCY,2) as value_after_tax_fcy , "+   
			" po.po_id,pod.po_det_id    "+
			" FROM po po INNER JOIN po_details pod on po.company_id=pod.company_id AND    "+
			" po.year_id=pod.year_id AND po.location_id=pod.location_id AND po.po_id=pod.po_id "+    
			" left JOIN sales_order so on so.so_id=po.so_id   "+
			" INNER JOIN material m on pod.Material_id=m.Material_id "+    
			" INNER JOIN groups g on g.group_id=m.group_id    "+
			" inner join party p on po.party_id=p.party_id and p.party_tag='Vendor' "+
			" left join variant_master v on v.variant_id=pod.variant_id   "+
			/*	  " inner join uom_master um with(nolock) on pod.uom=um.uom " +*/
			" LEFT JOIN currency_master cm on  cm.currency_id=po.currency_id "+   
			" Left Join Po_Dynamic podf on po.po_id = podf.po_id   "+
			" Left Join Po_Details_Dynamic poddf on pod.po_det_id = poddf.po_det_id ";  


	public static String PURCHASE_ORDERS_PAGE_COUNT = " SELECT  count(po.po_id) "+
			" FROM po po INNER JOIN po_details pod on po.company_id=pod.company_id AND    "+
			" po.year_id=pod.year_id AND po.location_id=pod.location_id AND po.po_id=pod.po_id "+    
			" LEFT JOIN sales_order so on so.so_id=po.so_id   "+
			" INNER JOIN material m on pod.Material_id=m.Material_id "+    
			" INNER JOIN groups g on g.group_id=m.group_id    "+
			" inner join party p on po.party_id=p.party_id and p.party_tag='Vendor'   "+
			" left join variant_master v on v.variant_id=pod.variant_id   "+
			" LEFT JOIN currency_master cm on  cm.currency_id=po.currency_id "+   
			" Left Join Po_Dynamic podf on po.po_id = podf.po_id   "+
			" Left Join Po_Details_Dynamic poddf on pod.po_det_id = poddf.po_det_id ";

	public static String GET_PURCHASE_ORDER_HEADER = " select  po.Po_id,po.prefix,po.Po_no,convert(nvarchar,po.Po_date,105) as 'po date',po.Reference_No,po.party_id,   "
			+ " p.party_name as 'supplier', po.currency_id,  "
			+ " po.exchange_rate,po.version_no,po.remarks, "
			+ " po.so_id,so.prefix+convert(nvarchar,so.so_no) as so_no,isnull(po.email_sent_to,'') email_sent_to, isnull(po.bill_to_address,'') bill_to_address, isnull(po.ship_to_address,'') ship_to_address,isnull(po.internal_memo,'') internal_memo,cm.currency_name,po.charge_fcy1,po.charge_fcy2,po.charge_fcy3 ,"
			+ " po.charge_acc_id1,isnull(acc1.account_name,'') as charge1,po.charge_acc_id2 ,isnull(acc2.account_name,'') as charge2,po.charge_acc_id3 ,isnull(acc3.account_name,'') as charge3,po.payment_terms,po.credit_days "
			+ " from po po  "
			+ " inner join party p on p.party_id=po.party_id and p.party_tag='Vendor' "
			+ " left join sales_order so on so.so_id=po.so_id "
			+ " inner join currency_master cm on po.currency_id=cm.currency_id"
			+ " left join account acc1 with(nolock) on po.charge_acc_id1=acc1.account_id"
			+ " left join account acc2 with(nolock) on po.charge_acc_id2=acc2.account_id"
			+ " left join account acc3 with(nolock) on po.charge_acc_id3=acc3.account_id"
			+ " where po.company_Id=? and po.year_id=? and po.location_id=? and po.Po_id= ?  ";

	public static String SELECT_PURCHASE_ORDER_HEADER_DYNAMIC = "  Select podf.* "+ 
			" FROM  "+
			" po po (NOLOCK) "+
			" Left Join Po_Dynamic podf on po.po_id = podf.po_id "+ 
			" Where po.po_id=?  ";

	public static String GET_PURCHASE_ORDER_DETAIL_FOR_GRID = "  select g.group_id,g.group_name,pod.Material_id,m.Material_name,pod.quantity,pod.sku,pod.price_fcy,convert(nvarchar,pod.expected_date,105) as expdate, "+  
			" pod.quantity*pod.price_fcy as 'value before discount',  "+
			" pod.discount_percent,pod.discount_fcy,pod.value_before_tax_FCY,pod.remarks,pod.Po_Det_Id,pod.variant_id,var.variant_name,pod.material_description ,  "+
			" pod.Ed_percentage,pod.Ecess_percentage,pod.ShEcess_percentage,pod.VAT_percentage,pod.CST_percentage,pod.Service_tax_percentage,pod.tax_group_id  "+
			" from po_details pod  "+
			" inner join po po on po.company_id=pod.company_id AND    "+
			" po.year_id=pod.year_id AND po.location_id=pod.location_id AND po.po_id=pod.po_id "+    
			" inner join material m on pod.Material_id=m.Material_id "+   
			" inner join groups g on g.group_id=m.group_id  "+
			" left join variant_master var on pod.variant_id=var.variant_id "+
			/*		" inner join uom_master um with(nolock) on pod.uom=um.uom "+*/
			" left join sales_order so on so.so_id=po.so_id "+   
			" where pod.po_id=? order by pod.Po_Det_Id desc ;";

	public static String SELECT_PURCHASE_ORDER_DETAIL_DYNAMIC = "   Select poddf.* "+ 
			" FROM  "+
			" po po (NOLOCK) "+
			" Left Join Po_Details_Dynamic poddf on po.po_id = poddf.po_id "+ 
			" Where po.po_id=?  ";

	public static String SELECT_PURCHASE_ORDER_ATTACHMENT=" SELECT po_id,File_type,isnull(URL,''),reverse(substring(reverse(URL), 1,charindex('/', reverse(URL))-1)) from PO_URL where po_id=? ";

	public static String   GET_NOT_SAVED_USER_IN_PO="  SELECT t.tpcs_user_id,t.tpcs_user_first_name,t.tpcs_user_last_name,tc.image_url,t.tpcs_user_login_name "+
			" FROM tpcs_user t WITH(NOLOCK)  left join tpcs_contact tc on t.tpcs_user_id = tc.user_id "+
			" WHERE t.tpcs_user_id NOT IN (SELECT user_id from po_Followers WHERE po_id=?) "; 


	public static String   GET_PO_SAVED_FOLLOWERS="  select t.tpcs_user_id,t.tpcs_user_first_name,t.tpcs_user_last_name, "+ 
			"	tc.image_url,t.tpcs_user_login_name,reverse(substring(reverse(image_url), 1,charindex('/', reverse(image_url))-1)) "+
			"	from po_Followers pof , "+
			"	tpcs_user t WITH(NOLOCK)  "+
			"	left join tpcs_contact tc on t.tpcs_user_id = tc.user_id "+ 
			"	WHERE pof.user_id=t.tpcs_user_id "+
			"	and pof.po_id=? and t.tpcs_user_id<>? "; 

	public static String DELETE_PO_ATTACH =" DELETE FROM PO_URL WHERE po_id=? and url=? ";


	public static String SELECT_PO_DETAIL_DYNAMIC_BY_ID = " Select pdf.*, pddf.* "
			+ " FROM  "
			+ " po po (NOLOCK) "
			+ " Join po_details pod on po.po_id = pod.po_id "
			+ " Left Join po_dynamic pdf on po.po_id = pdf.po_id "
			+ " Left Join po_details_dynamic pddf on pod.po_det_id = pddf.po_det_id "
			+ " Where po.po_id=? AND pod.po_det_id=? ";

	public static String GET_PURCHASE_ORDER_PRINT_HEADER=" SELECT distinct po.po_id,po.prefix+convert(nvarchar,po.po_no) as po_no,convert(nvarchar,po.po_date,105) as po_date , "
			+ " po.Reference_No,po.party_id,p.party_name,po.currency_id,cm.currency_name,po.exchange_rate,po.remarks,po.version_no ,   "
			+ " po.charge_fcy1,po.charge_fcy2,po.charge_fcy3 ,"
			+ " isnull(acc1.account_name,'') as charge1,isnull(acc2.account_name,'') as charge2,isnull(acc3.account_name,'') as charge3 ,po.payment_terms,po.credit_days "
			+ " FROM  po po    "
			+ " inner join party p with(nolock) on p.party_id=po.party_id and p.party_tag='Vendor'  "
			+ " left join currency_master cm with(nolock) on po.currency_id=cm.currency_id     "
			+ " left join account acc1 with(nolock) on po.charge_acc_id1=acc1.account_id"
			+ " left join account acc2 with(nolock) on po.charge_acc_id2=acc2.account_id"
			+ " left join account acc3 with(nolock) on po.charge_acc_id3=acc3.account_id"
			+ " where  po.company_Id=? and po.year_id=? and po.location_id=? and  po.po_id=? " ;

	public static String INSERT_PURCHASE_ORDER_ATTACHMENT=" INSERT INTO PO_URL(company_id,year_id,location_id,po_id,File_type,URL) VALUES(?,?,?,?,?,?) ";

	public static String  INSERT_PO_USER= " if not exists(select * from Po_followers where user_id=? and po_id=?)"
			+ " begin"
			+ " INSERT INTO Po_followers(company_id,year_id,location_id,po_id,User_id)values(?,?,?,?,?)"
			+ " end ";



	public static String GET_PODETAILS = " SELECT po.po_id,po.po_no,p.party_name,m.material_name FROM po po "+ 
			" LEFT JOIN po_details pod ON po.po_id=pod.po_id "+
			" LEFT JOIN party p On po.party_id=p.party_id "+
			" LEFT JOIN material m ON pod.Material_id=m.material_id WHERE po.po_id=? "; 

	public static String  GET_PO_FOLLOWERS=" SELECT distinct tu.tpcs_user_login_name  FROM tpcs_user tu  "+
			" LEFT JOIN po_Followers pof ON pof.User_id=tu.tpcs_user_id WHERE pof.po_id=? " ;


	//SALES INVOICE



	public static String GET_SALES_INVOICE = "select  si.prefix+convert(nvarchar,si.invoice_no) as invoice_no,si.draft_tag,si.email_sent_to,so.prefix+convert(nvarchar,so.so_no) as so_no,convert(nvarchar,si.invoice_date,105) as invoice_date,p.party_name ,si.Bill_to_address,si.Ship_to_address,si.Internal_Memo,si.customer_order_no,g.group_name,m.material_code,m.material_name,sid.material_description as 'desc', "+ 
			" abs(sid.quantity) quantity ,sid.sku,round(sid.price_fcy,3) as price_fcy, "+
			" abs(round(sid.value_before_tax_fcy,2)) as value_before_tax_fcy ,sid.discount_percent,abs(round(sid.discount_fcy,2)) as discount_fcy,sid.remarks,cm.currency_name,v.variant_name, "+
			" round(sid.ed_percentage,2) as ed_percentage,round(sid.ecess_percentage,2) as ecess_percentage,round(sid.shecess_percentage,2) as shecess_percentage,round(sid.vat_percentage,2) as vat_percentage,round(sid.cst_percentage,2) as cst_percentage,round(sid.gst_percentage,2) as gst_percentage,round(sid.service_tax_percentage,2) as service_tax_percentage, "+
			" abs(round(sid.ed_fcy,2)) as ed_fcy,abs(round(sid.ecess_fcy,2)) as ecess_fcy,abs(round(sid.shecess_fcy,2)) as shecess_fcy,abs(round(sid.vat_fcy,2)) as vat_fcy,abs(round(sid.cst_fcy,2)) as cst_fcy,abs(round(sid.gst_fcy,2)) as gst_fcy,abs(round(sid.service_tax_fcy,2)) as service_tax_fcy,abs(round(sid.value_after_tax_fcy,2)) as value_after_tax_fcy , "+
			" si.invoice_id,sid.invoice_det_id "+
			" from sales_invoice si "
			+ " inner join sales_invoice_details sid on si.company_id=sid.company_id and "+ 
			" si.year_id=sid.year_id and si.location_id=sid.location_id and si.invoice_id=sid.invoice_id "+ 
			" left join sales_order so on so.so_id=si.so_id "+
			" inner join material m on sid.material_id=m.material_id "+ 
			" inner join groups g on g.group_id=m.group_id  "+
			" inner join party p on si.party_id=p.party_id   and party_tag='customer' "+
			" left join variant_master v on v.variant_id=sid.variant_id "+
			/*	 " inner join uom_master um with(nolock) on sid.uom=um.uom " +*/
			" left join currency_master cm on  cm.currency_id=si.currency_id "+
			" left join sales_invoice_dynamic sidf on si.invoice_id = sidf.invoice_id "+
			" left join sales_invoice_details_dynamic siddf on sid.invoice_det_id = siddf.invoice_det_id ";
	
	public static String GET_SALES_RETURN = "select  si.prefix+convert(nvarchar,si.invoice_no) as invoice_no,si.draft_tag,refsi.prefix+convert(nvarchar,refsi.invoice_no) as ref_invoice_no,convert(nvarchar,si.invoice_date,105) as invoice_date,si.email_sent_to,p.party_name ,si.Bill_to_address,si.Ship_to_address,si.Internal_Memo,si.customer_order_no,g.group_name,m.material_code,m.material_name,sid.material_description as 'desc', "+ 
			" abs(sid.quantity) quantity ,sid.sku,round(sid.price_fcy,3) as price_fcy, "+
			" sid.discount_percent,abs(round(sid.discount_fcy,2)) as discount_fcy,abs(round(sid.value_before_tax_fcy,2)) as value_before_tax_fcy,sid.remarks,cm.currency_name,v.variant_name, "+
			" round(sid.ed_percentage,2) as ed_percentage,round(sid.ecess_percentage,2) as ecess_percentage,round(sid.shecess_percentage,2) as shecess_percentage,round(sid.vat_percentage,2) as vat_percentage,round(sid.cst_percentage,2) as cst_percentage,round(sid.gst_percentage,2) as gst_percentage,round(sid.service_tax_percentage,2) as service_tax_percentage, "+
			" abs(round(sid.ed_fcy,2)) as ed_fcy,abs(round(sid.ecess_fcy,2)) as ecess_fcy,abs(round(sid.shecess_fcy,2)) as shecess_fcy,abs(round(sid.vat_fcy,2)) as vat_fcy,abs(round(sid.cst_fcy,2)) as cst_fcy,abs(round(sid.gst_fcy,2)) as gst_fcy,abs(round(sid.service_tax_fcy,2)) as service_tax_fcy,abs(round(sid.value_after_tax_fcy,2)) as value_after_tax_fcy, "+
			" si.invoice_id,sid.invoice_det_id "+
			" from sales_invoice si "
			+ " inner join sales_invoice_details sid on si.company_id=sid.company_id and "+ 
			" si.year_id=sid.year_id and si.location_id=sid.location_id and si.invoice_id=sid.invoice_id "+ 
			" left join sales_invoice refsi on si.invoice_id_returned=refsi.invoice_id "+
			" inner join material m on sid.material_id=m.material_id "+ 
			" inner join groups g on g.group_id=m.group_id  "+
			" inner join party p on si.party_id=p.party_id   and party_tag='customer' "+
			" left join variant_master v on v.variant_id=sid.variant_id "+
			/*	 " inner join uom_master um with(nolock) on sid.uom=um.uom " +*/
			" left join currency_master cm on  cm.currency_id=si.currency_id "+
			" left join sales_invoice_dynamic sidf on si.invoice_id = sidf.invoice_id "+
			" left join sales_invoice_details_dynamic siddf on sid.invoice_det_id = siddf.invoice_det_id ";
	

	public static String INSERT_SALES_INVOICE_ATTACHMENT=" INSERT INTO Sales_invoice_URL(company_id,year_id,location_id,invoice_id,File_type,URL) VALUES(?,?,?,?,?,?) ";

	public static String  INSERT_SI_USER= " if not exists(select * from Sales_invoice_followers where user_id=? and invoice_id=?)"
			+ " begin"
			+ " INSERT INTO Sales_invoice_followers(company_id,year_id,location_id,invoice_id,User_id)values(?,?,?,?,?)"
			+ " end ";

	public static String GET_SIDETAILS = " SELECT si.invoice_id,si.invoice_no,p.party_name,m.material_name FROM Sales_invoice si "+ 
			" LEFT JOIN sales_invoice_details sid ON si.invoice_id=sid.invoice_id "+
			" LEFT JOIN party p On si.party_id=p.party_id "+
			" LEFT JOIN material m ON sid.Material_id=m.material_id WHERE si.invoice_id=? "; 

	public static String  GET_SI_FOLLOWERS=" SELECT distinct tu.tpcs_user_login_name  FROM tpcs_user tu  "+
			" LEFT JOIN Sales_Invoice_Followers sif ON sif.User_id=tu.tpcs_user_id WHERE sif.invoice_id=? " ;


	public static String GET_SALES_INVOICE_HEADER = "select  si.invoice_id,si.invoice_no,si.prefix,convert(nvarchar,si.invoice_date,105) as 'si date',si.customer_order_no,si.party_id, "
			+ " p.party_name as 'customer', si.tag,si.currency_id,  "
			+ " si.exchange_rate,si.executive_id,e.employee_name,si.version_no,si.remarks,si.so_id,so.prefix+convert(nvarchar,so.so_no) as so_no,isnull(si.email_sent_to,'') email_sent_to, isnull(si.bill_to_address,'') bill_to_address, isnull(si.ship_to_address,'') ship_to_address,isnull(si.internal_memo,'') internal_memo,cm.currency_name,"
			+ " abs(si.charge_fcy1) charge_fcy1,abs(si.charge_fcy2) charge_fcy2,abs(si.charge_fcy3) charge_fcy3 ,si.charge_acc_id1,isnull(acc1.account_name,'') as charge1,si.charge_acc_id2 ,isnull(acc2.account_name,'') as charge2,si.charge_acc_id3 ,isnull(acc3.account_name,'') as charge3,si.invoice_tag,si.draft_tag,si.payment_terms,si.credit_days ,si.charge_cc_id1,cc1.costcenter_name,si.charge_cc_id2,cc2.costcenter_name,si.charge_cc_id3,cc3.costcenter_name"
			+ " from sales_invoice si "
			+ " inner join party p on p.party_id=si.party_id  and party_tag='customer'"
			+ " left join employees e on e.employee_id=si.executive_id "
			+ " left join sales_order so on so.so_id=si.so_id "
			+ " inner join currency_master cm on si.currency_id=cm.currency_id"
			+ " left join account acc1 with(nolock) on si.charge_acc_id1=acc1.account_id"
			+ " left join account acc2 with(nolock) on si.charge_acc_id2=acc2.account_id"
			+ " left join account acc3 with(nolock) on si.charge_acc_id3=acc3.account_id"
			+ " left join costcenter cc1 with(nolock) on si.charge_cc_id1=cc1.costcenter_id"
			+ " left join costcenter cc2 with(nolock) on si.charge_cc_id2=cc2.costcenter_id"
			+ " left join costcenter cc3 with(nolock) on si.charge_cc_id3=cc3.costcenter_id"
			+ " where si.company_Id=? and si.year_id=? and si.location_id=? and si.invoice_id=?";
	
	public static String GET_SALES_RETURN_HEADER = " select  siret.invoice_id,siret.invoice_no,siret.prefix,convert(nvarchar,siret.invoice_date,105) as 'si date',siret.customer_order_no,siret.party_id, "+ 
				" p.party_name as 'customer', siret.tag,siret.currency_id,   siret.exchange_rate,siret.executive_id,e.employee_name,siret.version_no,siret.remarks, "+
				" isnull(siret.email_sent_to,'') email_sent_to, isnull(siret.bill_to_address,'') bill_to_address,  "+
				" isnull(siret.ship_to_address,'') ship_to_address,isnull(siret.internal_memo,'') internal_memo,cm.currency_name, abs(siret.charge_fcy1) charge_fcy1, "+
				" abs(siret.charge_fcy2) charge_fcy2,abs(siret.charge_fcy3) charge_fcy3 ,siret.charge_acc_id1,isnull(acc1.account_name,'') as charge1,siret.charge_acc_id2 , "+
				" isnull(acc2.account_name,'') as charge2,siret.charge_acc_id3 ,isnull(acc3.account_name,'') as charge3,siret.invoice_tag ,siret.invoice_id_returned, si.prefix+convert(nvarchar,si.invoice_no) as invoice_no,siret.draft_tag,siret.payment_terms,siret.credit_days ,siret.charge_cc_id1,cc1.costcenter_name,siret.charge_cc_id2,cc2.costcenter_name,siret.charge_cc_id3,cc3.costcenter_name"+
				" from sales_invoice siret "+  
				" inner join party p on p.party_id=siret.party_id  and party_tag='customer' "+ 
				" left join employees e on e.employee_id=siret.executive_id  "+
				" left join sales_invoice si on siret.invoice_id_returned=si.invoice_id "+  
				" inner join currency_master cm on siret.currency_id=cm.currency_id "+
				" left join account acc1 with(nolock) on siret.charge_acc_id1=acc1.account_id "+ 
				" left join account acc2 with(nolock) on siret.charge_acc_id2=acc2.account_id "+
				" left join account acc3 with(nolock) on siret.charge_acc_id3=acc3.account_id "+
				" left join costcenter cc1 with(nolock) on siret.charge_cc_id1=cc1.costcenter_id "+
				" left join costcenter cc2 with(nolock) on siret.charge_cc_id2=cc2.costcenter_id "+
				" left join costcenter cc3 with(nolock) on siret.charge_cc_id3=cc3.costcenter_id "+
				" where siret.company_Id=? and siret.year_id=? and siret.location_id=? and siret.invoice_id=? ";

	public static String SELECT_SALES_INVOICE_HEADER_DYNAMIC = " Select sidf.* "
			+ " FROM  "
			+ " sales_invoice si (NOLOCK) "
			+ " Left Join sales_invoice_dynamic sidf on si.invoice_id = sidf.invoice_id "
			+ " Where si.invoice_id=? ";
	
	
	public static String SELECT_SALES_RETURN_HEADER_DYNAMIC = " Select sidf.* "
			+ " FROM  "
			+ " sales_invoice si (NOLOCK) "
			+ " Left Join sales_return_dynamic sidf on si.invoice_id = sidf.invoice_id "
			+ " Where si.invoice_id=? ";
	

	public static String GET_SALES_INVOICE_DETAIL_FOR_GRID = " select g.group_id,g.group_name,m.Material_id,m.Material_name,abs(sid.quantity) quantity,sid.sku,sid.price_fcy,  "+
			" abs(sid.quantity*sid.price_fcy) as 'value before discount',  "+
			" sid.discount_percent,abs(sid.discount_fcy) discount_fcy,abs(sid.value_before_tax_FCY) value_before_tax_FCY,sid.remarks,sid.invoice_det_id,var.variant_id,var.variant_name,sid.material_description , "+ 
			" sid.Ed_percentage,sid.Ecess_percentage,sid.ShEcess_percentage,sid.VAT_percentage,sid.CST_percentage,sid.Service_tax_percentage,sid.tax_group_id "+//,so.so_no,so.so_id
			" from sales_invoice_details sid  "+
			" inner join material m on sid.Material_id=m.Material_id "+  
			" inner join groups g on g.group_id=m.group_id  "+
			" left join variant_master var on sid.variant_id=var.variant_id "+
			/*	" inner join uom_master um with(nolock) on sid.uom=um.uom "+*/
			//			" inner join sales_order so on so.so_id=sid.so_id "+  
			" where sid.invoice_id=? order by sid.invoice_det_id ";
	
	
	public static String GET_SALES_RETURN_DETAIL_FOR_GRID = " select g.group_id,g.group_name,m.Material_id,m.Material_name,abs(sid.quantity) quantity,sid.sku,sid.price_fcy,  "+
			" abs(sid.quantity*sid.price_fcy) as 'value before discount',  "+
			" sid.discount_percent,abs(sid.discount_fcy) discount_fcy,abs(sid.value_before_tax_FCY) value_before_tax_FCY,sid.remarks,sid.invoice_det_id,var.variant_id,var.variant_name,sid.material_description , "+ 
			" sid.Ed_percentage,sid.Ecess_percentage,sid.ShEcess_percentage,sid.VAT_percentage,sid.CST_percentage,sid.Service_tax_percentage,sid.tax_group_id "+//,so.so_no,so.so_id
			" from sales_invoice_details sid  "+
			" inner join material m on sid.Material_id=m.Material_id "+  
			" inner join groups g on g.group_id=m.group_id  "+
			" left join variant_master var on sid.variant_id=var.variant_id "+
			/*	" inner join uom_master um with(nolock) on sid.uom=um.uom "+*/
			//			" inner join sales_order so on so.so_id=sid.so_id "+  
			" where sid.invoice_id=? order by sid.invoice_det_id ";
	


	public static String SALES_INVOICE_PAGE_COUNT = " SELECT  count(si.invoice_id) "
			+ " from sales_invoice si "
			+ " inner join sales_invoice_details sid on si.company_id=sid.company_id and "+ 
			" si.year_id=sid.year_id and si.location_id=sid.location_id and si.invoice_id=sid.invoice_id "+ 
			" left join sales_order so on so.so_id=si.so_id "+
			" inner join material m on sid.material_id=m.material_id "+ 
			" inner join groups g on g.group_id=m.group_id  "+
			" inner join party p on si.party_id=p.party_id  and party_tag='customer' "+
			" left join variant_master v on v.variant_id=sid.variant_id "+
			//" inner join uom_master um with(nolock) on sid.uom=um.uom " +
			" left join currency_master cm on  cm.currency_id=si.currency_id "+
			" left join sales_invoice_dynamic sidf on si.invoice_id = sidf.invoice_id "+
			" left join sales_invoice_details_dynamic siddf on sid.invoice_det_id = siddf.invoice_det_id ";
	
	public static String SALES_RETURN_PAGE_COUNT = " SELECT  count(si.invoice_id) "
			+ " from sales_invoice si "
			+ " inner join sales_invoice_details sid on si.company_id=sid.company_id and "+ 
			" si.year_id=sid.year_id and si.location_id=sid.location_id and si.invoice_id=sid.invoice_id "+ 
			" left join sales_invoice refsi on refsi.invoice_id_returned=si.invoice_id "+
			" inner join material m on sid.material_id=m.material_id "+ 
			" inner join groups g on g.group_id=m.group_id  "+
			" inner join party p on si.party_id=p.party_id  and party_tag='customer' "+
			" left join variant_master v on v.variant_id=sid.variant_id "+
			//" inner join uom_master um with(nolock) on sid.uom=um.uom " +
			" left join currency_master cm on  cm.currency_id=si.currency_id "+
			" left join sales_invoice_dynamic sidf on si.invoice_id = sidf.invoice_id "+
			" left join sales_invoice_details_dynamic siddf on sid.invoice_det_id = siddf.invoice_det_id ";
	

	/*public static String GET_SO_NO_DET = " select  so.so_id,so.so_no,m.Material_name,sum(sod.quantity) as orderQty,sod.uom,round((sum(sod.price_fcy*sod.quantity)/sum(sod.quantity)),2) as price,convert(nvarchar,sod.expected_date,105) as 'expected date' "+
		 " from sales_order_details sod "+
		 " left join sales_order so on sod.so_id=so.so_id  "+
		 " inner join material m on sod.material_id=m.Material_id "+  
		 " inner join groups g on g.group_id=m.group_id   "+
		 " left join variant_master var on sod.variant_id=var.variant_id   "+
         " Group By so.so_id,so.so_no,m.Material_name,sod.uom,sod.expected_date "+
         " order by convert(float,so.so_no) desc";*/
	public static String SELECT_SALES_INVOICE_DETAIL_DYNAMIC_BY_ID = " Select sdf.*, sidf.* "
			+ " FROM  "
			+ " sales_invoice si (NOLOCK) "
			+ " Join sales_invoice_details sid on si.invoice_id = sid.invoice_id "
			+ " Left Join sales_invoice_dynamic sdf on si.invoice_id = sdf.invoice_id "
			+ " Left Join sales_invoice_details_dynamic sidf on sid.invoice_det_id = sidf.invoice_det_id "
			+ " Where si.invoice_id=? AND sid.invoice_det_id=? ";
	
	public static String SELECT_SALES_RETURN_DETAIL_DYNAMIC_BY_ID = " Select sdf.*, sidf.* "
			+ " FROM  "
			+ " sales_invoice si (NOLOCK) "
			+ " Join sales_invoice_details sid on si.invoice_id = sid.invoice_id "
			+ " Left Join sales_return_dynamic sdf on si.invoice_id = sdf.invoice_id "
			+ " Left Join sales_return_details_dynamic sidf on sid.invoice_det_id = sidf.invoice_det_id "
			+ " Where si.invoice_id=? AND sid.invoice_det_id=? ";
	
	
	public static String SELECT_SALES_INVOICE_DETAIL_DYNAMIC = " Select siddf.* "
			+ " FROM  "
			+ " sales_invoice si (NOLOCK) "
			+ " Left Join sales_invoice_details_dynamic siddf on si.invoice_id = siddf.invoice_id "
			+ " Where si.invoice_id=? ";
	
	public static String SELECT_SALES_RETURN_DETAIL_DYNAMIC = " Select siddf.* "
			+ " FROM  "
			+ " sales_invoice si (NOLOCK) "
			+ " Left Join sales_return_details_dynamic siddf on si.invoice_id = siddf.invoice_id "
			+ " Where si.invoice_id=? ";

	public static String SELECT_SALES_INVOICE_ATTACHMENT=" SELECT invoice_id,File_type,isnull(URL,''),reverse(substring(reverse(URL), 1,charindex('/', reverse(URL))-1)) from Sales_Invoice_URL where invoice_id=? ";

	public static String   GET_NOT_SAVED_USER_IN_SI="  SELECT t.tpcs_user_id,t.tpcs_user_first_name,t.tpcs_user_last_name,tc.image_url,t.tpcs_user_login_name "+
			" FROM tpcs_user t WITH(NOLOCK)  left join tpcs_contact tc on t.tpcs_user_id = tc.user_id "+
			" WHERE t.tpcs_user_id NOT IN (SELECT user_id from Sales_invoice_Followers WHERE invoice_id=?) "; 

	public static String   GET_SI_SAVED_FOLLOWERS="  select t.tpcs_user_id,t.tpcs_user_first_name,t.tpcs_user_last_name, "+ 
			"	tc.image_url,t.tpcs_user_login_name,reverse(substring(reverse(image_url), 1,charindex('/', reverse(image_url))-1)) "+
			"	from Sales_invoice_Followers sif , "+
			"	tpcs_user t WITH(NOLOCK)  "+
			"	left join tpcs_contact tc on t.tpcs_user_id = tc.user_id "+ 
			"	WHERE sif.user_id=t.tpcs_user_id "+
			"	and sif.invoice_id=? and t.tpcs_user_id<>? "; 

	public static String DELETE_SALES_INVOICE_ATTACH =" DELETE FROM Sales_invoice_URL WHERE invoice_id=? and url=? ";

	public static String SELECT_SALES_ENQUIRY_ATTACHMENT=" SELECT enq_id,File_type,isnull(URL,''),reverse(substring(reverse(URL), 1,charindex('/', reverse(URL))-1)) from Sales_Enquiry_URL where enq_id=? ";

	

	public static String GET_SODETAILS = " SELECT so.so_id,so.so_no,p.party_name,m.material_name FROM Sales_Order so "+ 
			" LEFT JOIN sales_order_details sod ON so.so_id=sod.so_id "+
			" LEFT JOIN party p On so.party_id=p.party_id "+
			" LEFT JOIN material m ON sod.material_id=m.material_id WHERE so.so_id=? "; 
	public static String  GET_SO_FOLLOWERS=" SELECT distinct tu.tpcs_user_login_name  FROM tpcs_user tu  "+
			" LEFT JOIN Sales_Order_Followers sof ON sof.User_id=tu.tpcs_user_id WHERE sof.so_id=? " ;


	public static String GET_SALES_INVOICE_PRINT_HEADER=" SELECT distinct si.invoice_id,si.prefix+convert(nvarchar,si.invoice_no) as invoice_no,convert(nvarchar,si.invoice_date,105) as inv_date , si.customer_order_no,si.party_id,p.party_name,si.currency_id,cm.currency_name,si.executive_id,e.employee_name,si.exchange_rate,si.remarks,si.version_no,si.tag,si.email_sent_to,si.bill_to_address,si.ship_to_address,si.internal_memo,"
			+ " si.charge_fcy1,si.charge_fcy2,si.charge_fcy3 ,"
			+ " isnull(acc1.account_name,'') as charge1,isnull(acc2.account_name,'') as charge2,isnull(acc3.account_name,'') as charge3,si.payment_terms,si.credit_days "
			+ " FROM  sales_invoice si "
			+ " inner join party p with(nolock) on p.party_id=si.party_id and p.party_tag='customer' "
			+ " left join employees e with(nolock) on e.employee_id=si.executive_id "
			+ " left join currency_master cm with(nolock) on si.currency_id=cm.currency_id "
			+ " left join account acc1 with(nolock) on si.charge_acc_id1=acc1.account_id"
			+ " left join account acc2 with(nolock) on si.charge_acc_id2=acc2.account_id"
			+ " left join account acc3 with(nolock) on si.charge_acc_id3=acc3.account_id"
			+ " where  si.company_Id=? and si.year_id=? and si.location_id=? and  si.invoice_id=? ";
	
	
	public static String GET_SALES_RETURN_PRINT_HEADER=" SELECT distinct si.invoice_id,si.prefix+convert(nvarchar,si.invoice_no) as invoice_no,convert(nvarchar,si.invoice_date,105) as inv_date , si.customer_order_no,si.party_id,p.party_name,si.currency_id,cm.currency_name,si.executive_id,e.employee_name,si.exchange_rate,si.remarks,si.version_no,si.tag,si.email_sent_to,si.bill_to_address,si.ship_to_address,si.internal_memo,"
			+ " abs(si.charge_fcy1) charge_fcy1,abs(si.charge_fcy2) charge_fcy2,abs(si.charge_fcy3) charge_fcy3 ,"
			+ " isnull(acc1.account_name,'') as charge1,isnull(acc2.account_name,'') as charge2,isnull(acc3.account_name,'') as charge3,si.payment_terms,si.credit_days  "
			+ " FROM  sales_invoice si "
			+ " inner join party p with(nolock) on p.party_id=si.party_id and p.party_tag='customer' "
			+ " left join employees e with(nolock) on e.employee_id=si.executive_id "
			+ " left join currency_master cm with(nolock) on si.currency_id=cm.currency_id "
			+ " left join account acc1 with(nolock) on si.charge_acc_id1=acc1.account_id"
			+ " left join account acc2 with(nolock) on si.charge_acc_id2=acc2.account_id"
			+ " left join account acc3 with(nolock) on si.charge_acc_id3=acc3.account_id"
			+ " where  si.company_Id=? and si.year_id=? and si.location_id=? and  si.invoice_id=? ";
	

	public static String   GET_ALL_SO_USER=" SELECT  t.tpcs_user_id,t.tpcs_user_first_name,t.tpcs_user_last_name FROM tpcs_user t ";

	public static String   GET_SO_USER="  select t.tpcs_user_id,t.tpcs_user_first_name,t.tpcs_user_last_name, "+
			" tc.image_url,t.tpcs_user_login_name from Sales_Order_Followers sof ,tpcs_user t WITH(NOLOCK) "+
			" left outer join tpcs_contact tc on t.tpcs_user_id = tc.user_id WHERE sof.user_id=t.tpcs_user_id "+ 
			" and sof.so_id=? AND t.tpcs_user_id!=? ";

	public static String DELETE_SALES_ORDER_ATTACH =" DELETE FROM Sales_Order_URL WHERE so_id=? and url=? ";



	public static String GET_SALES_ORDER_DETAIL = " select sod.so_det_id,m.material_id,m.short_name,g.group_id,g.group_name,sod.uom, "+
			" sod.quantity,sod.price_fcy,sod.quantity*sod.price_fcy as 'value',sod.discount_percent, "+
			" sod.discount_fcy,sod.value_fcy,sod.remarks,convert(nvarchar,sod.expected_date,105) "+
			" from sales_order_details sod inner join material m on sod.material_id=m.material_id "+
			" inner join groups g on g.group_id=m.group_id where sod.so_id=? and so_det_id=? "; 



	public static String GET_SALES_ORDER_PRINT_HEADER=" SELECT distinct so.so_id,so.prefix+convert(nvarchar,so.so_no) as so_no,convert(nvarchar,so.so_date,105) as so_date , so.customer_order_no,so.party_id,p.party_name,so.currency_id,cm.currency_name,so.executive_id,e.employee_name,so.exchange_rate,so.remarks,so.version_no,so.tag,"
			+ " so.charge_fcy1,so.charge_fcy2,so.charge_fcy3 ,"
			+ " isnull(acc1.account_name,'') as charge1,isnull(acc2.account_name,'') as charge2,isnull(acc3.account_name,'') as charge3,so.Payment_Terms,so.Credit_Days "
			+ " FROM  sales_order so "
			+ " left join party p with(nolock) on p.party_id=so.party_id and p.party_tag='customer' "
			+ " left join employees e with(nolock) on e.employee_id=so.executive_id "
			+ " left join currency_master cm with(nolock) on so.currency_id=cm.currency_id"
			+ " left join account acc1 with(nolock) on so.charge_acc_id1=acc1.account_id"
			+ " left join account acc2 with(nolock) on so.charge_acc_id2=acc2.account_id"
			+ " left join account acc3 with(nolock) on so.charge_acc_id3=acc3.account_id"
			+ " where  so.company_Id=? and so.year_id=? and so.location_id=? and  so.so_id=? ";



	public static String GET_SALES_FILE = " SELECT attachment_url FROM sales_order WITH(NOLOCK) WHERE so_id=?";

	public static String UPLOAD_SALES_FILE = " UPDATE sales_order SET attachment_url=? WHERE so_id=?";

	public static String GET_SALES_FILES = " SELECT attachment_url,attachment2_url,attachment3_url,attachment4_url FROM sales_order WITH(NOLOCK) WHERE so_id=? ";

	public static String UPLOAD_SALES_FILES = " UPDATE sales_order SET attachment_url=?,attachment2_url=?,attachment3_url=?,attachment4_url=? WHERE so_id=? ";

	//Get all currency 
	public static String GET_ALL_CURRENCY = "select currency_id,currency_name from currency_master";

	//Get all Tax 
	public static String GET_ALL_TAX = "SELECT tax_id,tax_display_name from tax_master order by display_order";
	
	//ed
	
	public static String GET_ED_TAX_IF_APPLICABLE="select tax_id,tax_display_name from tax_master where (tax_name like '%ED%' or tax_name like '%ECHESS%' or tax_name like '%SH Ecess%' )"
			+ " and 'yes'=(select top 1 config_option from set_location_config where config_code='EDapp' and location_id=?)"
			+ " order by display_order";

	//VAT 
	public static String GET_VAT_TAX_IF_APPLICABLE=" select tax_id,tax_display_name from tax_master where tax_name like '%VAT%' "
			+ " and 'yes'=(select top 1 config_option from set_location_config where config_code='VATapp' and location_id=?)"
			+ " order by display_order";
	
	//CST
	public static String GET_CST_TAX_IF_APPLICABLE=" select tax_id,tax_display_name from tax_master where tax_name like '%CST%' "
			+ " and 'yes'=(select top 1 config_option from set_location_config where config_code='CSTapp' and location_id=?)"
			+ " order by display_order ";
	
	//GST
	public static String GET_GST_TAX_IF_APPLICABLE=" select tax_id,tax_display_name from tax_master where tax_name like '%GST%' "
			+ " and 'yes'=(select top 1 config_option from set_location_config where config_code='GSTapp' and location_id=?)"
			+ " order by display_order ";
	
	//Service Tax
	public static String GET_SERVICE_TAX_IF_APPLICABLE=" select tax_id,tax_display_name from tax_master where tax_name like '%Service Tax%' "
			+ " and 'yes'=(select top 1 config_option from set_location_config where config_code='ServTaxapp' and location_id=?)"
			+ " order by display_order ";
	
	//Get Account 
	public static String GET_ACCOUNT = "select group_id,group_name from account_group where group_type like '%Liabilit%' or group_type like '%Other Expense%' or group_type like '%Expenditure%' order by group_name";

	public static String GET_ACCOUNT_HEAD="select acc.account_id,acc.account_name "
			+ " from account acc "
			+ " inner join account_group ag with(nolock) on acc.group_id=ag.group_id"
			+ " where ag.group_type like '%Expenditure%' or ag.group_type like '%Direct Expense%' or ag.group_type like '%Liabilit%'  or ag.group_type like '%Duties and taxes%' ";

	//Get all Tr. Type
	public static String GET_TR_TYPE = "select tr_type from tr_type";

	//Get all Tr. Category
	public static String GET_TR_CATEGORY = "select tr_category from tr_category";

	//Get Party
	public static String GET_PARTY = "select ?c1,?c2 from ?t ";

	//SalesQuotation

	public static String INSERT_SALES_QUOTATION_ATTACHMENT=" INSERT INTO Sales_Quotation_URL(company_id,year_id,location_id,quote_id,File_type,URL) VALUES(?,?,?,?,?,?) ";

	public static String INSERT_SALES_ENQUIRY_ATTACHMENT=" INSERT INTO Sales_Enquiry_URL(company_id,year_id,location_id,enq_id,File_type,URL) VALUES(?,?,?,?,?,?) ";

	public static String  INSERT_DEF_SALES_QUOTATION_USER= " INSERT INTO Sales_Quotation_Followers(company_id,year_id,location_id,quote_id,User_id)values(?,?,?,?,?)";
	public static String  GET_SQ_FOLLOWERS=" SELECT tu.tpcs_user_login_name  FROM tpcs_user tu  "+
			" LEFT JOIN Sales_Quotation_Followers sqf ON sqf.User_id=tu.tpcs_user_id WHERE sqf.quote_id=? " ;

	public static String  GET_SE_FOLLOWERS=" SELECT distinct tu.tpcs_user_login_name  FROM tpcs_user tu  "+
			" LEFT JOIN Sales_Enquiry_Followers sef ON sef.User_id=tu.tpcs_user_id WHERE sef.enq_id=? " ;

	public static String GET_SQDETAILS = " SELECT sq.quote_id,sq.quote_no,p.party_name,m.material_name FROM Sales_Quotation sq "+ 
			" LEFT JOIN Sales_Quotation_Details sqd ON sq.quote_id=sqd.quote_id "+
			" LEFT JOIN party p On sq.party_id=p.party_id "+
			" LEFT JOIN material m ON sqd.material_id=m.material_id WHERE sq.quote_id=? "; 


	public static String GET_SEDETAILS = " SELECT se.enq_id,se.enq_no,p.party_name,m.material_name FROM sales_enquiry se "+
			"  LEFT JOIN sales_enquiry_details sed ON se.enq_id=sed.enq_id  "+
			" LEFT JOIN party p On se.party_id=p.party_id  "+
			"  LEFT JOIN material m ON sed.material_id=m.material_id WHERE se.enq_id=? "; 


	public static String GET_USER_NAME = " SELECT tpcs_user_login_name,tpcs_user_first_name,tpcs_user_last_name  FROM tpcs_user  WITH(NOLOCK) WHERE tpcs_user_id=? ";
	public static String  INSERT_SQ_USER= " if not exists(select * from Sales_Quotation_Followers where user_id=? and quote_id=?)"
			+ " begin"
			+ " INSERT INTO Sales_Quotation_Followers(company_id,year_id,location_id,quote_id,User_id)values(?,?,?,?,?)"
			+ " end ";




	public static String  INSERT_SE_USER= " if not exists(select * from Sales_enquiry_followers where user_id=? and enq_id=?)"
			+ " begin"
			+ " INSERT INTO Sales_enquiry_followers(company_id,year_id,location_id,enq_id,User_id)values(?,?,?,?,?)"
			+ " end ";

	public static String  DELETE_DEF_SALES_QUOTATION_USER= "DELETE FROM Sales_Quotation_Followers WHERE user_id=?";

	public static String   GET_ALL_USER=" SELECT  t.tpcs_user_id,t.tpcs_user_first_name,t.tpcs_user_last_name FROM tpcs_user t where t.tpcs_user_id!=? ";

	public static String   GET_NOT_SAVED_USER_SQ="  SELECT t.tpcs_user_id,t.tpcs_user_first_name,t.tpcs_user_last_name,tc.image_url,t.tpcs_user_login_name "+
			" FROM tpcs_user t WITH(NOLOCK)  left join tpcs_contact tc on t.tpcs_user_id = tc.user_id "+
			" WHERE t.tpcs_user_id NOT IN (SELECT user_id from Sales_Quotation_Followers WHERE quote_id=?) "; 


	public static String   GET_NOT_SAVED_USER_SE="  SELECT t.tpcs_user_id,t.tpcs_user_first_name,t.tpcs_user_last_name,tc.image_url,t.tpcs_user_login_name "+
			" FROM tpcs_user t WITH(NOLOCK)  left join tpcs_contact tc on t.tpcs_user_id = tc.user_id "+
			" WHERE t.tpcs_user_id NOT IN (SELECT user_id from Sales_Enquiry_Followers WHERE enq_id=?) "; 


	public static String   GET_NOT_SAVED_USER_IN_SO="  SELECT t.tpcs_user_id,t.tpcs_user_first_name,t.tpcs_user_last_name,tc.image_url,t.tpcs_user_login_name "+
			" FROM tpcs_user t WITH(NOLOCK)  left join tpcs_contact tc on t.tpcs_user_id = tc.user_id "+
			" WHERE t.tpcs_user_id NOT IN (SELECT user_id from Sales_Order_Followers WHERE so_id=?) "; 


	public static String   GET_SQ_SAVED_FOLLOWERS="  select t.tpcs_user_id,t.tpcs_user_first_name,t.tpcs_user_last_name, "+ 
			"	tc.image_url,t.tpcs_user_login_name ,reverse(substring(reverse(image_url), 1,charindex('/', reverse(image_url))-1)) "+
			"	from Sales_Quotation_Followers sqf , "+
			"	tpcs_user t WITH(NOLOCK)  "+
			"	left join tpcs_contact tc on t.tpcs_user_id = tc.user_id "+ 
			"	WHERE sqf.user_id=t.tpcs_user_id "+
			"	and sqf.quote_id=? and t.tpcs_user_id<>? "; 

	public static String   GET_SE_SAVED_FOLLOWERS="  select t.tpcs_user_id,t.tpcs_user_first_name,t.tpcs_user_last_name, "+ 
			"	tc.image_url,t.tpcs_user_login_name ,reverse(substring(reverse(image_url), 1,charindex('/', reverse(image_url))-1))"+
			"	from Sales_Enquiry_Followers sef , "+
			"	tpcs_user t WITH(NOLOCK)  "+
			"	left join tpcs_contact tc on t.tpcs_user_id = tc.user_id "+ 
			"	WHERE sef.user_id=t.tpcs_user_id "+
			"	and sef.enq_id=? and t.tpcs_user_id<>? "; 

	public static String   GET_SO_SAVED_FOLLOWERS="  select t.tpcs_user_id,t.tpcs_user_first_name,t.tpcs_user_last_name, "+ 
			"	tc.image_url,t.tpcs_user_login_name ,reverse(substring(reverse(image_url), 1,charindex('/', reverse(image_url))-1)) "+
			"	from Sales_Order_Followers sof , "+
			"	tpcs_user t WITH(NOLOCK)  "+
			"	left join tpcs_contact tc on t.tpcs_user_id = tc.user_id "+ 
			"	WHERE sof.user_id=t.tpcs_user_id "+
			"	and sof.so_id=? and t.tpcs_user_id<>? "; 


	public static String SELECT_SALES_QUOTATION_ATTACHMENT=" SELECT quote_id,File_type,isnull(URL,''),reverse(substring(reverse(URL), 1,charindex('/', reverse(URL))-1)) from Sales_Quotation_URL where quote_id=? ";
	public static String DELETE_SALES_QUOTATION_ATTACH =" DELETE FROM Sales_Quotation_URL WHERE quote_id=? and url=? "; 


//	public static String GET_MAT_DATA=" SELECT  sales_desc,sku,selling_price FROM material WHERE material_id=? " ;
	public static String GET_MAT_DATA=" SELECT m.BOM_Uom,um.uom_id FROM item m inner join uom_master um with(nolock) on m.BOM_Uom=um.uom  WHERE Mat_Id=? " ;


	public static String GET_CUSTOMER_DATA="select p.party_id,p.party_name,isnull(p.contact_email,'') contact_email,isnull(p.bill_street,'') bill_street,isnull(p.bill_location,'') bill_location,isnull(p.bill_city,'') bill_city,isnull(p.bill_state,'') bill_state,isnull(p.bill_country,'') bill_country,isnull(p.bill_zip,'') bill_zip,isnull(p.ship_street,'') ship_street,isnull(p.ship_location,'') ship_location,isnull(p.ship_city,'') ship_city,isnull(p.ship_state,'') ship_state,isnull(p.ship_country,'') ship_country,isnull(p.ship_zip,'') ship_zip "
			+ " ,cm.currency_id,cm.currency_symbol,currency_name,p.payment_terms,p.credit_days "
			+ " from party p inner join currency_master cm on p.currency_id=cm.currency_id and p.company_id=cm.company_id and p.location_id=cm.location_id "
			+ " where p.party_tag='Customer' and p.party_id=? and p.company_id=? and p.location_id=?";
	
	public static String GET_SUPPLIER_DATA="select p.party_id,p.party_name,isnull(p.contact_email,'') contact_email,isnull(p.bill_street,'') bill_street,isnull(p.bill_location,'') bill_location,isnull(p.bill_city,'') bill_city,isnull(p.bill_state,'') bill_state,isnull(p.bill_country,'') bill_country,isnull(p.bill_zip,'') bill_zip,isnull(p.ship_street,'') ship_street,isnull(p.ship_location,'') ship_location,isnull(p.ship_city,'') ship_city,isnull(p.ship_state,'') ship_state,isnull(p.ship_country,'') ship_country,isnull(p.ship_zip,'') ship_zip "
			+ " ,cm.currency_id,cm.currency_symbol,currency_name,p.payment_terms,p.credit_days "
			+ " from party p inner join currency_master cm on p.currency_id=cm.currency_id and p.company_id=cm.company_id and p.location_id=cm.location_id "
			+ " where p.party_tag='Vendor' and p.party_id=? and p.company_id=? and p.location_id=?";


	//Get material for Sales Order Number
	public static String GET_MATERIAL_FOR_SO_NUM= " select material_id,material_code,material_name,sku,group_id from material ";

	public static String GET_PURCHASE_MAT_DATA=" SELECT  purchase_desc,sku,cost_price FROM material WHERE material_id=? " ;	

	public static String GET_SALES_QUOTATION_HEADER=" SELECT distinct sq.quote_id,sq.prefix,sq.quote_no,convert(nvarchar,sq.quote_date,105) as quoteDate , sq.reference_no,sq.party_id,p.party_name,sq.currency_id,sq.executive_id,e.employee_name,sq.exchange_rate,sq.remarks,sq.version_no,sq.tag,sq.enq_id,se.prefix+convert(nvarchar,se.enq_no) as enq_no , "
			+ " isnull(sq.email_sent_to,'') email_sent_to, isnull(sq.bill_to_address,'') bill_to_address, isnull(sq.ship_to_address,'') ship_to_address,isnull(sq.internal_memo,'') internal_memo,cm.currency_name,sq.charge_fcy1,sq.charge_fcy2,sq.charge_fcy3 ,sq.charge_acc_id1,isnull(acc1.account_name,'') as charge1,sq.charge_acc_id2 ,isnull(acc2.account_name,'') as charge2,"
			+ " sq.charge_acc_id3 ,isnull(acc3.account_name,'') as charge3 ,sq.payment_terms,sq.credit_days "
			+ " FROM  sales_quotation sq "
			+ " left join sales_enquiry se with(nolock) on se.enq_id=sq.enq_id "
			+ " inner join party p  on p.party_id=sq.party_id and party_tag='customer' "
			+ " left join employees e with(nolock) on e.employee_id=sq.executive_id "
			+ " inner join currency_master cm with(nolock) on sq.currency_id=cm.currency_id "
			+ " left join account acc1 with(nolock) on sq.charge_acc_id1=acc1.account_id"
			+ " left join account acc2 with(nolock) on sq.charge_acc_id2=acc2.account_id"
			+ " left join account acc3 with(nolock) on sq.charge_acc_id3=acc3.account_id"
			+ " where  sq.company_Id=? and sq.year_id=? and sq.location_id=? and  sq.quote_id=? "; 



	public static String SELECT_SALES_QUOTATION_HEADER_DYNAMIC = " Select sqdf.* "
			+ " FROM  "
			+ " sales_quotation sq (NOLOCK) "
			+ " Left Join sales_quotation_dynamic sqdf on sq.quote_id = sqdf.quote_id "
			+ " Where sq.quote_id=? ";

	public static String SELECT_SALES_QUOTATION_DETAIL_DYNAMIC = " Select sqddf.* "
			+ " FROM  "
			+ " sales_quotation sq (NOLOCK) "
			+ " Left Join Sales_Quotation_Details_Dynamic sqddf on sq.quote_id = sqddf.quote_id "
			+ " Where sq.quote_id=? ";
	public static String GET_SALES_QUOTATION_DET_LIST=" select  sqd.quote_id,sqd.quote_det_id,convert(nvarchar,sqd.quote_date,105) as quote_date,g.group_id,g.group_name,sqd.material_id,m.Material_name,sqd.sku,sqd.quantity," +
			" sqd.price_fcy,sqd.discount_percent,sqd.remarks,sqd.quantity*sqd.price_fcy as valBefDis,sqd.discount_fcy,sqd.value_before_tax_FCY,convert(nvarchar,sqd.required_date,105) as required_date,v.variant_id,v.variant_name," +
			" sqd.Ed_percentage,sqd.Ecess_percentage,sqd.ShEcess_percentage,sqd.VAT_percentage,sqd.CST_percentage,sqd.Service_tax_percentage,sqd.material_description,sqd.tax_group_id  "
			+ " from sales_quotation sq " +
			" inner join sales_quotation_details sqd with(nolock) on sq.quote_id=sqd.quote_id " +
			" inner join material m with(nolock) on sqd.material_id=m.Material_id" +
			" inner join groups g with(nolock) on m.group_id=g.group_id "+
			" left join variant_master v on v.variant_id=sqd.variant_id "+
			/*	" inner join uom_master um with(nolock) on sqd.uom=um.uom "+*/
			" where  sqd.company_Id=? and sqd.year_id=? and sqd.location_id=? and sqd.quote_id=? order by sqd.quote_det_id ";


	public static String GET_SALES_QUOTATION_LIST=" select sq.quote_id,isnull(sq.prefix,'')+convert(nvarchar,sq.quote_no) as quote_no,g.group_id,p.party_name,sq.Bill_to_address,sq.Ship_to_address,sq.Internal_Memo,g.group_name,m.Material_id,m.Material_code,m.Material_name,se.email_sent_to,sqd.material_description as 'desc',sqd.quantity,sqd.sku,sqd.price_fcy,ISNULL(convert(nvarchar,sqd.required_date,105),'') as required_date,round(sqd.discount_percent,2) as discount_percent,sqd.remarks,round(sqd.value_before_tax_fCY,2) as value_before_tax_fCY,sqd.discount_fcy,convert(nvarchar,sq.quote_date,105) as quote_date,sq.closed_tag,ISNULL(v.variant_name,'') as variant_name ,sqd.quote_det_id ," +
			" round(sqd.Ed_percentage,2) as ed_percentage,round(sqd.Ecess_percentage,2) as ecess_percentage,round(sqd.ShEcess_percentage,2) as shecess_percentage,round(sqd.vat_percentage,2) as vat_percentage,round(sqd.cst_percentage,2) as cst_percentage,round(sqd.gst_percentage,2) as gst_percentage,round(sqd.Service_tax_percentage,2) as service_tax_percentage, "+
			" round(sqd.ED_FCY,2) as ed_fcy,round(sqd.Ecess_FCY,2) as ecess_fcy,round(sqd.ShEcess_FCY,2) as shecess_fcy,round(sqd.VAT_FCY,2) as vat_fcy,round(sqd.CST_FCY,2) as cst_fcy,round(sqd.GST_FCY,2) as gst_fcy,round(sqd.Service_tax_FCY,2) as service_tax_fcy,round(sqd.value_after_tax_FCY,2) as value_after_tax_fCY,isnull(se.prefix,'')+convert(nvarchar,se.enq_no) as enq_no"+
			" from " +                     
			" sales_quotation sq" +
			" inner join sales_quotation_details sqd with(nolock) on sq.company_id=sqd.company_id and sq.year_id =sqd.year_id and sq.location_id=sqd.location_id and sq.quote_id=sqd.quote_id" +
			" left join sales_enquiry se with(nolock) on sq.enq_id=se.enq_id" +
			" inner join material m with(nolock) on sqd.material_id=m.Material_id" +
			" inner join groups g with(nolock) on m.group_id=g.group_id "+
			" left join variant_master v on v.variant_id=sqd.variant_id "+
			" left join party p on sq.party_id=p.party_id" +
			/*	" inner join uom_master um with(nolock) on sqd.uom=um.uom " + */
			" Left Join sales_quotation_dynamic sqdf on sq.quote_id = sqdf.quote_id "+
			" Left Join Sales_Quotation_Details_Dynamic sqddf on sqd.quote_det_id = sqddf.quote_det_id  ";

	public static String SELECT_QUOTATION_ATTACHMENT=" SELECT quote_id,File_type,url from Sales_Quotation_URL where quote_id=? ";

	public static String SELECT_SALES_QUOTATION_DETAIL_DYNAMIC_BY_ID = " Select sdf.*, sodf.* "
			+ " FROM  "
			+ " sales_quotation so (NOLOCK) "
			+ " Join sales_quotation_details sod on so.quote_id = sod.quote_id "
			+ " Left Join sales_quotation_dynamic sdf on so.quote_id = sdf.quote_id "
			+ " Left Join Sales_Quotation_Details_Dynamic sodf on sod.quote_det_id = sodf.quote_det_id "
			+ " Where so.quote_id=? AND sod.quote_det_id=? ";

	public static String GET_SALES_QUOTATION_COUNT=" select count(*) " +
			" from " +
			" sales_quotation sq" +
			" left join sales_quotation_details sqd with(nolock) on sq.company_id=sqd.company_id and sq.year_id =sqd.year_id and sq.location_id=sqd.location_id and sq.quote_id=sqd.quote_id" +
			" left join sales_enquiry se with(nolock) on sq.enq_id=se.enq_id" +
			" inner join material m with(nolock) on sqd.material_id=m.Material_id" +
			" inner join groups g with(nolock) on m.group_id=g.group_id "+
			" left join variant_master v on v.variant_id=sqd.variant_id "+
			" left join party p on sq.party_id=p.party_id ";
	public static String GET_SALES_QUOTATION_DET_INFO=" select sqd.quote_det_id,sqd.quote_id,g.group_id,g.group_name,m.material_id,m.short_name,sqd.quantity,sqd.uom,sqd.price_fcy,convert(nvarchar,sqd.quote_date,105) as quote_date,sqd.discount_percent,sqd.remarks,sqd.quantity*sqd.price_fcy as valBefDis,(sqd.quantity*sqd.price_fcy)*(sqd.discount_percent/100) as disval,(sqd.quantity*sqd.price_fcy)-((sqd.quantity*sqd.price_fcy)*(sqd.discount_percent/100)) as valAftDis,sqd.variant_id,v.variant_name,convert(nvarchar,sqd.required_date,105) as required_date" +
			" from " +
			" sales_quotation_details sqd " +
			" inner join material m with(nolock) on sqd.material_id=m.material_id" +
			" inner join groups g with(nolock) on m.group_id=g.group_id" +
			" left join variant_master v on v.variant_id=sqd.variant_id "+
			" where  sqd.company_Id=?" +
			" and sqd.year_id=?" +
			" and sqd.location_id=?" +
			" and sqd.quote_det_id=? ";
	public static String DELETE_SALES_QUOTATION_DETAIL="DELETE FROM sales_quotation_details  WHERE quote_det_id=?";

	public static String DELETE_SALES_QUOTATION_DETAIL_DYNAMIC = " DELETE FROM Sales_Quotation_Details_Dynamic WHERE quote_det_id=? ";

	public static String GET_SALES_PRINT_HEADER=" SELECT distinct sq.quote_id,sq.prefix+convert(nvarchar,sq.quote_no) as quote_no,convert(nvarchar,sq.quote_date,105) as quoteDate , sq.reference_no,sq.party_id,p.party_name,sq.currency_id,cm.currency_name,sq.executive_id,e.employee_name,sq.exchange_rate,sq.remarks,sq.version_no,enq_no,sq.tag ,"
			+ " sq.charge_fcy1,sq.charge_fcy2,sq.charge_fcy3 ,"
			+ " isnull(acc1.account_name,'') as charge1,isnull(acc2.account_name,'') as charge2,isnull(acc3.account_name,'') as charge3 ,sq.payment_terms,sq.credit_days  "
			+ " FROM  sales_quotation sq "
			+ " left join party it with(nolock) on it.party_id=sq.party_id and it.party_tag='customer'"
			+ " left join party st with(nolock) on st.party_id=sq.party_id and st.party_tag='customer' "
			+ " left join party p with(nolock) on p.party_id=sq.party_id and p.party_tag='customer' "
			+ " left join employees e with(nolock) on e.employee_id=sq.executive_id "
			+ " left join currency_master cm with(nolock) on sq.currency_id=cm.currency_id "
			+ " left join sales_enquiry en on sq.enq_id=en.enq_id"
			+ " left join account acc1 with(nolock) on sq.charge_acc_id1=acc1.account_id"
			+ " left join account acc2 with(nolock) on sq.charge_acc_id2=acc2.account_id"
			+ " left join account acc3 with(nolock) on sq.charge_acc_id3=acc3.account_id"
			+ " where  sq.company_Id=? and sq.year_id=? and sq.location_id=? and  sq.quote_id=? ";

	public static String GET_QUOTATION_IMAGE = " SELECT attachment_url FROM sales_quotation WITH(NOLOCK) WHERE quote_id=?";

	public static String UPLOAD_QUOTATION_IMAGE = " UPDATE sales_quotation SET attachment_url=? WHERE quote_id=?";

	public static String GET_QUOTATION_IMAGES = " SELECT attachment_url,attachment2_url,attachment3_url,attachment4_url FROM sales_quotation WITH(NOLOCK) WHERE quote_id=? ";

	public static String UPLOAD_QUOTATION_IMAGES = " UPDATE sales_quotation SET attachment_url=?,attachment2_url=?,attachment3_url=?,attachment4_url=? WHERE quote_id=? ";
	public static String SELECT_IMAGE_NAME = "select 1? from sales_quotation where quote_id=?";
	public static String UPDATE_IMAGE_NAME = "update sales_quotation set 1? =null where quote_id=?";
	//Approved Price List	
	public static String GET_PRICELIST_INFO = "select groups.group_name ,material.short_name,party.party_name,approved_pricelist.rate,"
			+ "approved_pricelist.lead_time,approved_pricelist.description from groups,approved_pricelist,"
			+ "party,material where groups.group_id in(select material.group_id from material where material.material_id=?)"
			+ " and approved_pricelist.party_id=? and approved_pricelist.material_id=? and party.party_id=? and material.material_id=?";
	public static String DELETE_APPROVED_PRICELIST = "DELETE FROM approved_pricelist WHERE party_id=? and material_id=?";
	public static String PRICELIST_COUNT = " SELECT COUNT(*) FROM approved_pricelist ap,party p,material m,groups g,group_type gt "
			+ " WHERE ap.party_id=p.party_id and ap.material_id=m.material_id  and m.group_id=g.group_id and g.group_type_id=gt.group_type_id";

	//Master Group
	public static String INSERT_GROUP_TYPE="insert into groups_type(group_type)values (?) ";
	public static String INSERT_GROUPS="insert into groups (group_code,group_name,short_name)values(?,?,?)";
	public static String INSERT_GROUPS_CONFIG="insert into groups_confif(is_itrack_required,is_barcode_required)values(?,?) ";
	public static String GET_ALL_GROUP="select group_type_id,group_type from group_type ";

	public static String GET_ALL_GROUPS=" SELECT group_id,group_name FROM groups WHERE company_id=? and location_id=? ";

	public static String GET_ALL_GROUP_TYPE="select Group_typeid,Group_Type FROM fa_group_type  ";

	public static String GET_ALL_COST_CNTR=" SELECT Costcenter_Id,Costcenter_name FROM Costcenter";

	/*	public static String GET_GROUP_MASTER=" select g.group_code,g.group_name,g.short_name,g.grouped_under,g.group_id, "+
			" gt.group_type from groups g inner join  group_type gt "
			+" on g.group_type_id=gt.group_type_id"; 

	public static String GET_GROUP_MASTER_INFO=" select g.group_code,g.group_name,g.short_name,g.grouped_under,g.group_id,gt.group_type,gt.group_type_id from groups g inner join group_type gt on g.group_type_id=gt.group_type_id where group_id=?";
	 */

	//Delivery Note

	public static String GET_DELIVERY_NOTE="select s.tr_no,convert(nvarchar,s.tr_date,105) as tr_date,s.issued_by,s.received_by,m.material_code,m.material_name as 'material',m.material_name as 'description',m.basic_unit,sid.qty," +
			" st.store_name,s.entered_by,s.entered_on,s.updated_by,s.updated_on,s.host_name "+
			"	from stock s inner join stock_itemdet sid on s.company_id=sid.company_id  and s.location_id=sid.location_id " +
			"   and s.tr_no=sid.tr_no and s.tr_id=sid.tr_id and s.tr_tag=sid.tr_tag inner join material m on sid.material_id=m.material_id "+
			"	inner join groups g on g.group_id=m.group_id" +
			//" inner join sales_order_details sod on sod.so_det_id=sid.Wo_Detail_Id " +
			//"--   inner join sales_order  so on sod.so_id=so.so_id and sod.company_id=so.company_id and sod.year_id=so.year_id and sod.location_id=so.location_id " +
			"   inner join stores st on sid.store_id=st.store_id ";

	public static String GET_DELIVERY_NOTE_COUNT="select count(*)  "+
			"	from stock s inner join stock_itemdet sid on s.company_id=sid.company_id  and s.location_id=sid.location_id " +
			"   and s.tr_no=sid.tr_no and s.tr_id=sid.tr_id and s.tr_tag=sid.tr_tag inner join material m on sid.material_id=m.material_id "+
			"	inner join groups g on g.group_id=m.group_id " +
			//"inner join sales_order_details sod on sod.so_det_id=sid.Wo_Detail_Id " +
			//"   inner join sales_order  so on sod.so_id=so.so_id and sod.company_id=so.company_id and sod.year_id=so.year_id and sod.location_id=so.location_id " +
			"   inner join stores st on sid.store_id=st.store_id ";

	//PENDING SALES ORDER
	//for value
	//sum(sod.price_lcy*round((isnull(sod.quantity,0)-isnull(st.qty,0)),2)) as value
	public static String GET_PENDING_SALES ="select  ROW_NUMBER() OVER (ORDER BY so.so_no) AS slno, p.party_name,so.so_no,convert(nvarchar,so.so_date,105) as so_date,so.customer_order_no,g.group_name,m.material_code,m.material_name,m.spec1,m.basic_unit,sum(sod.quantity) as sodquantity," +
			" sum(st.qty) as stockquantity,sum(round((isnull(sod.quantity,0)-isnull(st.qty,0)),2) ) as balance_qty,sum(sod.price_fcy) as pricefcy,c.currency_name ,sum(sod.price_fcy*round((isnull(sod.quantity,0)-isnull(st.qty,0)),2) ) as fcyvalue," +
			" sum(round((isnull(sod.quantity,0)-isnull(st.qty,0)),2)*so.exchange_rate) as value from sales_order so,sales_order_details sod,party p,material m ,groups g,currency_master c,stock_itemdet st " +
			" where so.company_id=sod.company_id and so.year_id=sod.year_id and so.location_id=sod.location_id " +
			" and so.so_id=sod.so_id and p.party_id=so.party_id and m.material_id=sod.material_id and m.group_id=g.group_id and so.currency_id=c.currency_id ";

	public static String GET_PENDING_SALES_COUNT=" select  distinct count (*) over() from sales_order so,sales_order_details sod,party p,material m ,groups g,currency_master c,stock_itemdet st " +
			" where so.company_id=sod.company_id and so.year_id=sod.year_id and so.location_id=sod.location_id " +
			" and so.so_id=sod.so_id and p.party_id=so.party_id and m.material_id=sod.material_id and m.group_id=g.group_id and so.currency_id=c.currency_id " ;


	public static String INSERT_COLUMN_PREFERENCES = "INSERT INTO column_preference_user_map(column_id,subdocument_id,tpcs_user_id,column_visibility_id) VALUES(?,?,?,?)";
	public static String DELETE_COLUMN_PREFERENCES = "DELETE FROM column_preference_user_map WHERE subdocument_id=? AND tpcs_user_id=?";

	//USER RIGHTS

	public static String GET_USER_RIGHTS = "SELECT entry_permission,edit_permission,delete_permission,amendment_permission,view_permission,excel_permission,print_permission,approval_permission FROM tpcs_user_rights WHERE tpcs_user_id=? AND subdocument_id=?";

	//SALES ENQUIRY

	public static String GET_SALES_ENQUIRY_HEADER=" SELECT distinct se.enq_id,se.prefix,se.enq_no,convert(nvarchar,se.enq_date,105) as enq_date , se.reference_no,se.party_id,p.party_name,se.currency_id, "+
			" se.executive_id,e.employee_name,se.exchange_rate,se.remarks,se.version_no,se.tag,isnull(email_sent_to,'') email_sent_to, "+
			" isnull(bill_to_address,'') bill_to_address, isnull(ship_to_address,'') ship_to_address,isnull(internal_memo,'') internal_memo,cm.currency_name "+
			" FROM  sales_enquiry se  "+
			" inner join party p with(nolock) on p.party_id=se.party_id "+  
			" left join employees e with(nolock) on e.employee_id=se.executive_id "+  
			" inner join currency_master cm with(nolock) on se.currency_id=cm.currency_id "+ 
			" where  se.company_Id=? and se.year_id=? and se.location_id=? and  se.enq_id=? ";

	public static String SELECT_SALES_ENQUIRY_HEADER_DYNAMIC = " Select sedf.* "
			+ " FROM  "
			+ " sales_enquiry se (NOLOCK) "
			+ " Left Join sales_enquiry_dynamic sedf on se.enq_id = sedf.enq_id "
			+ " Where se.enq_id=? ";



	public static String GET_SALES_ENQUIRY_DETAIL="select sed.enq_id,sed.enq_det_id,g.group_id,g.group_name,sed.material_id,m.short_name,sed.uom,sed.quantity,sed.price_fcy,convert(nvarchar,sed.required_date,105) as required_date, " +
			" sed.quantity*sed.price_fcy as valBefDis,sed.discount_percent,(sed.quantity*sed.price_fcy)*(sed.discount_percent/100) as disval,  " +
			" (sed.quantity*sed.price_fcy)-((sed.quantity*sed.price_fcy)*(sed.discount_percent/100)) as valAftDis,sed.remarks,sed.variant_id,v.variant_name from sales_enquiry_details sed  " +
			" inner join material m on m.material_id=sed.material_id inner join  groups g on g.group_id=m.group_id left join  variant_master v  on v.variant_id=sed.variant_id where sed.enq_det_id=? ";


	public static String GET_SALES_ENQUIRY_DETAIL_LIST=" select  sed.enq_id,sed.enq_det_id,convert(nvarchar,sed.enq_date,105) as enq_date,g.group_id,g.group_name,sed.material_id,m.Material_name,sed.sku,sed.quantity," +
			" sed.price_fcy,sed.remarks,sed.value_fcy as value_fcy,sed.value_lcy,convert(nvarchar,sed.required_date,105) as required_date,v.variant_id,v.variant_name,sed.material_description from" +
			" sales_enquiry_details sed" +
			" inner join material m with(nolock) on sed.material_id=m.Material_id" +
			" inner join groups g with(nolock) on m.group_id=g.group_id "+
			" left join variant_master v on v.variant_id=sed.variant_id "+
			/*	" inner join uom_master um with(nolock) on sed.uom=um.uom "+*/
			" where  sed.company_Id=? and sed.year_id=? and sed.location_id=? and  sed.enq_id=? order by sed.enq_det_id ";


	public static String SELECT_SALES_ENQUIRY_DETAIL_DYNAMIC = " Select seddf.* "
			+ " FROM  "
			+ " sales_enquiry se (NOLOCK) "
			+ " Left Join sales_enquiry_details_dynamic seddf on se.enq_id = seddf.enq_id "
			+ " Where se.enq_id=? ";


	public static String GET_SALES_ENQUIRY = " SELECT  se.prefix+convert(nvarchar,se.enq_no) as enq_no,convert(nvarchar,se.enq_date,105) as enq_date ,p.party_name,se.Bill_to_address,se.Ship_to_address,se.Internal_Memo,g.group_name,m.Material_code,m.Material_name,sed.material_description as 'desc', "+
			" sed.quantity,sed.sku,sed.price_fcy,ISNULL(CONVERT(nvarchar(10),sed.required_date,105),'') 'req_date',"+
			" sed.value_fcy as value_fcy,se.email_sent_to,sed.remarks,cm.currency_name,v.variant_name,"+
			" se.enq_id,sed.enq_det_id  "+
			" FROM sales_enquiry se INNER JOIN sales_enquiry_details sed on se.company_id=sed.company_id AND "+
			" se.year_id=sed.year_id AND se.location_id=sed.location_id AND se.enq_id=sed.enq_id "+
			" INNER JOIN material m on sed.material_id=m.Material_id "+
			" INNER JOIN groups g on g.group_id=m.group_id " +
			" left join party p on se.party_id=p.party_id" +
			" left join variant_master v on v.variant_id=sed.variant_id "+
			/*	" inner join uom_master um with(nolock) on sed.uom=um.uom " +*/
			" LEFT JOIN currency_master cm on  cm.currency_id=se.currency_id "+
			" Left Join sales_enquiry_dynamic sedf on se.enq_id = sedf.enq_id "+
			" Left Join sales_enquiry_details_dynamic seddf on sed.enq_det_id = seddf.enq_det_id  ";


	public static String SELECT_SALES_ENQUIRY_DETAIL_DYNAMIC_BY_ID = " Select sdf.*, sedf.* "
			+ " FROM  "
			+ " sales_enquiry se (NOLOCK) "
			+ " Join sales_enquiry_details sed on se.enq_id = sed.enq_id "
			+ " Left Join sales_enquiry_dynamic sdf on se.enq_id = sdf.enq_id "
			+ " Left Join sales_enquiry_details_dynamic sedf on sed.enq_det_id = sedf.enq_det_id "
			+ " Where se.enq_id=? AND sed.enq_det_id=? ";

	public static String GET_SALES_ENQUIRY_PRINT_HEADER=" SELECT distinct se.enq_id,se.prefix+convert(nvarchar,se.enq_no) as enq_no,convert(nvarchar,se.enq_date,105) as enq_date , se.reference_no,se.party_id,p.party_name,se.currency_id,cm.currency_name,se.executive_id,e.employee_name,se.exchange_rate,se.remarks,se.version_no,se.tag" +
			" FROM  sales_enquiry se " +
			" left join party p with(nolock) on p.party_id=se.party_id and p.party_tag='customer' " +
			" left join employees e with(nolock) on e.employee_id=se.executive_id " +
			" left join currency_master cm with(nolock) on se.currency_id=cm.currency_id " +
			" where  se.company_Id=? and se.year_id=? and se.location_id=? and  se.enq_id=? ";


	public static String GET_SALES_ENQUIRY_COUNT="select distinct count(*)  over()" +
			" FROM sales_enquiry se INNER JOIN sales_enquiry_details sed on se.company_id=sed.company_id AND "+
			" se.year_id=sed.year_id AND se.location_id=sed.location_id AND se.enq_id=sed.enq_id "+
			" INNER JOIN material m on sed.material_id=m.Material_id "+
			" INNER JOIN groups g on g.group_id=m.group_id " +
			" left join party p on se.party_id=p.party_id" +
			" left join variant_master v on v.variant_id=sed.variant_id "+
			" LEFT JOIN currency_master cm on  cm.currency_id=se.currency_id "+
			" Left Join sales_enquiry_dynamic sedf on se.enq_id = sedf.enq_id "+
			" Left Join sales_enquiry_details_dynamic seddf on sed.enq_det_id = seddf.enq_det_id  ";

	public static String DELETE_SALES_ENQUIRY_DETAIL="DELETE FROM sales_enquiry_details  WHERE enq_det_id=?";
	public static String DELETE_SALES_ENQUIRY_DETAIL_DYNAMIC = " DELETE FROM sales_enquiry_details_dynamic WHERE enq_det_id=? ";
	public static String DELETE_SALES_ENQUIRY_ATTACH =" DELETE FROM Sales_Enquiry_URL WHERE enq_id=? and url=? "; 

	public static String GROUP_MASTER_PAGE_COUNT=" select count(g1.group_id) as group_id "+
			" from groups g1 " +
			" left outer join groups g2 on g1.Parent_group_id = g2.group_id" +
			" left join group_type gt on g1.group_type_id=gt.group_type_id "+
			" left join groups_dynamic gdf on g1.group_id=gdf.group_id ";


	public static String GET_GROUP_MASTER=" select g1.group_id ,g1.group_code,g1.group_name," +
			" g1.Parent_group_id,g2.group_name as groupunder_name," +
			" g1.group_type_id,gt.group_type,case when g1.status=1 then 'Active' else case when g1.status=0 then 'Inactive' else '' end end as status" +
			" from groups g1 " +
			" left outer join groups g2 on g1.Parent_group_id = g2.group_id" +
			" left join group_type gt on g1.group_type_id=gt.group_type_id "+
			" left join groups_dynamic gdf on g1.group_id=gdf.group_id ";

	/*public static String GET_GROUP_MASTER_INFO=" select g1.group_name,g1.group_type_id,g1.group_code," +
		" g1.Parent_group_id,g2.group_name as groupunder_name," +
		" g1.group_id " +
		" from groups g1 " +
		" left outer join groups g2 on g1.Parent_group_id = g2.group_id " +
		" where g1.group_id = ? ";*/

	public static String GET_GROUP_MASTER_INFO=" SELECT top 1 g1.group_name,g1.group_type_id,g1.group_code, "+
			" g1.Parent_group_id,g2.group_name as groupunder_name, "+
			" g1.group_id ,gc.is_itrack_required,gc.is_barcode_required,g1.status,gt.group_type, "+
			" gd.Item_Tracking_Applicable,gd.Expiry_Applicable,gd.Allow_Negative_Stock,gd.Issue_With_IO,gd.Tax_Category,gd.Size_applicable,gd.Color_Applicable, "+
			" gd.Reservation_Applicable,gd.Inspection_Required,gd.Barcode_Required"+
			" FROM groups g1  "+
			" LEFT OUTER JOIN groups g2 ON g1.Parent_group_id = g2.group_id "+ 
			" left JOIN groups_config gc ON gc.group_id=g1.group_id "+
			" inner join group_type gt on g1.group_type_id=gt.group_type_id "+
			" left join group_details gd on g1.group_id=gd.group_id "+
			" WHERE g1.group_id = ? ";

	/*Group Dynamic */
	public static String SELECT_GROUP_HEADER_DYNAMIC = " Select gf.* "
			+ " FROM  "
			+ " groups g (NOLOCK) "
			+ " Left Join Groups_Dynamic gf on g.group_id = gf.group_id "
			+ " Where g.group_id=? ";

	public static String GROUP_CODE_EXIST="select count(*) from groups where group_code=? ";

	public static String GROUP_NAME_EXIST="select count(*) from groups where group_name=? ";

	//HOME

	public static String GET_DISPLAYDATA =  "SELECT s.material_name FROM group_detail gd WITH(NOLOCK),styles s WITH(NOLOCK) LEFT JOIN customer c WITH(NOLOCK) ON s.customer_id=c.customer_id WHERE s.group_id=gd.group_id ";

	public static String GET_NOTIFICATIONS_COUNT =  "SELECT COUNT(*) FROM notification_details nd WITH(NOLOCK),tpcs_user tu WITH(NOLOCK),notification_share ns WITH(NOLOCK)  WHERE ns.user_id=tu.tpcs_user_id AND nd.tpcs_user_id=tu.tpcs_user_id AND  nd.notification_detail_id=ns.notification_detail_id ";

	public static String GET_MY_NOTIFICATIONS =  " SELECT nd.notification_detail_id,nd.notification_type_id,nd.description,nd.display_data,nd.data_id,nd.js_function,tu.tpcs_user_first_name,tpcs_user_last_name,CONVERT(varchar,nd.display_date,105) as 'dispaly_date',tu.tpcs_user_id "+ 
			" FROM notification_details nd WITH(NOLOCK),tpcs_user tu WITH(NOLOCK),notification_share ns WITH(NOLOCK) WHERE "+ 
			" ns.user_id=tu.tpcs_user_id AND nd.tpcs_user_id=tu.tpcs_user_id AND nd.notification_detail_id=ns.notification_detail_id  ";

	public static String GET_CONTACT_IMAGE =  "SELECT image_url FROM tpcs_contact  WITH(NOLOCK) WHERE user_id=?";

	public static String GET_COMMENTS_COUNT =  "SELECT COUNT(*) FROM notification_comment_map  WITH(NOLOCK) WHERE notification_detail_id=?";


	public static String GET_NOTIFICATION_FOLLOWERS = " select u.tpcs_user_id,tpcs_user_login_name,tc.image_url from notification_share ns,tpcs_user u left outer join tpcs_contact tc on u.tpcs_user_id = tc.user_id where ns.notification_detail_id =? and ns.user_id = u.tpcs_user_id and ns.user_id <>? ";

	public static String GET_NOTIFICATIONS_ADVANCED_COUNT =  "  SELECT COUNT(distinct nd.notification_detail_id) "+ 	
			" FROM tpcs_user tu WITH(NOLOCK),notification_share ns WITH(NOLOCK) ,notification_details nd WITH(NOLOCK) "+
			" LEFT JOIN notification_comment_map ncm ON nd.notification_detail_id=ncm.notification_detail_id "+
			" WHERE   nd.tpcs_user_id=tu.tpcs_user_id AND nd.notification_detail_id=ns.notification_detail_id  ";
	public static String GET_COMMENTS =  "SELECT ncm.comment_id,ncm.comment,tu.tpcs_user_first_name,tu.tpcs_user_last_name,tc.image_url,CONVERT(VARCHAR(20),dateadd(mi,?,ncm.comment_date),100) as datetime,ncm.notification_attachment_url FROM notification_comment_map ncm WITH(NOLOCK),tpcs_user tu WITH(NOLOCK) LEFT JOIN tpcs_contact tc WITH(NOLOCK) ON tu.tpcs_user_id=tc.user_id WHERE ncm.tpcs_user_id=tu.tpcs_user_id AND ncm.notification_detail_id=?";



	//GL

	//FA GROUPS
	public static String GET_USER_FA_GROUPS=" SELECT group_id,Group_Name,parent_group_id ,group_level,'G' As Tag,group_code,Group_Type FROM Account_Group  ";

	public static String  GET_PARENT_GROUP="  SELECT group_id,Group_Name,parent_group_id,group_level  FROM Account_Group  ";

	public static String  GET_ACCOUNT_HEADS="  SELECT Account_id,Account_Name,Group_Id,'A' As Tag,Account_Code,Account_Description FROM account ";

	//Cost CENTERS

	public static String  GET_ALL_DIVISIONS=" SELECT Division_id,Division_name,Remarks,'D' As Tag FROM Costcenter_Division  ";
	public static String  GET_COST_CENTERS=" SELECT c.Division_id,c.Costcenter_id,c.Costcenter_Name,c.Costcenter_code,'C' As Tag,cd.Division_name  FROM Costcenter c "+
			" INNER JOIN Costcenter_Division cd ON cd.Division_id=c.Division_id ";
	public static String GET_GROUP_ITEMS="SELECT GroupId,Group_Code,Group_Name,Group_Type,parent_group_id,Group_Level FROM Account_Group WHERE Companyid=?  AND Locationid=? ";

	public static String INSERT_INTO_FA_GROUPS="INSERT INTO Account_Group(Group_Code,Group_Name,Group_Type,Display_Order,Is_Primary_Group,parent_group_id,Group_Level,Status,Company_id,Location_id ) "+
			" VALUES(?,?,?,?,?,?,?,?,?,?)"; 


	public static String UPDATE_FA_GROUPS="UPDATE Account_Group SET Group_Code=?,Group_Name=?,Group_Type=?,Display_Order=?,Is_Primary_Group=?,parent_group_id=?,Status=?,Company_id=?,Location_id=?"+
			" WHERE group_id=? ";
	public static String GET_FA_GROUP_INFO ="SELECT group_id,Group_Code,Group_Name,Group_Type,Display_Order,Is_Primary_Group,parent_group_id "+ 
			" FROM Account_Group WHERE Company_id=? AND Location_id=? AND group_id=? "; 



	//GL Accounts

	public static String INSERT_INTO_ACCOUNTS=" INSERT INTO Account(Account_Code,Account_Name,Group_Id,Bill_tracking,active,Standard_Narration,Account_Description,company_id,location_id) "+
			" VALUES(?,?,?,?,?,?,?,?,?) ";

	public static String UPDATE_ACCOUNTS=" UPDATE Account SET Account_Code=?,Account_Name=?,Group_Id=?,Bill_tracking=?,active=?,Standard_Narration=? ,"+
			" Account_Description=?,company_id=?,location_id=? "+
			" WHERE Account_id=? ";


	public static String GET_ACCOUNT_INFO =" SELECT Account_id,Account_Code,Account_Name,Group_Id,Bill_tracking,active,Standard_Narration, "+
			"Account_Description"+
			" FROM Account WHERE Company_id=? AND Location_id=? AND Account_id=? ";

	//Division
	public static String INSERT_INTO_FA_DIVISION="INSERT INTO Costcenter_Division(Division_name,Remarks,Company_id,location_id) VALUES(?,?,?,?)";

	public static String UPDATE_FA_DIVISION="UPDATE Costcenter_Division SET Division_name=?,Remarks=?,Company_id=?,location_id=? WHERE Division_id=? ";

	public static String GET_FA_DIVISION_INFO ="SELECT Division_id,Division_name,Remarks FROM Costcenter_Division WHERE Company_id=?  AND Division_id=? ";

	//Cost Cntr
	public static String INSERT_INTO_COST_CNTR="INSERT INTO Costcenter(Costcenter_name,Costcenter_code,Division_id,Company_id,Location_id) VALUES(?,?,?,?,?)";

	public static String UPDATE_COST_CNTR="UPDATE Costcenter SET Costcenter_name=?,Costcenter_code=?,Company_id=?,Location_id=? WHERE Costcenter_Id=? ";

	public static String GET_COST_CNTR ="SELECT Costcenter_Id,Costcenter_name,Costcenter_code FROM Costcenter WHERE Company_id=?  AND Costcenter_Id=? ";
	//Get Tax Category
	public static String GET_TAX_CATEGORY = " SELECT DISTINCT Tax_Category FROM Tax_Configuration ";

	public static String GET_TAX_DETAIL=" SELECT Tax_Display_Name FROM tax_configuration WHERE Tax_Category=?";

	public static String GET_DYNAMICFIELDS = "SELECT df.dynamicfieldId,df.labelname,df.pagefieldname,df.dbfieldname,df.tableid  FROM dynamicfields df "+
			" INNER JOIN DynamicRefScreenFields dr with(nolock) ON df.DynamicFieldId=dr.DynamicFieldId "+
			" WHERE  df.tableId in ";


	public static String  SELECT_MAX_TABLEID=" SELECT max(tableid) as tableId FROM DynamicRefTables ";
	public static String INSERT_DYNAMIC_REF_TABLES=" INSERT INTO DynamicRefTables VALUES(?,?) ";

	public static String INSERT_DYNAMIC_REF_SCREEN_TABLES=" INSERT INTO DynamicRefScreenRefTable VALUES(?,?) ";

	
	//UOM Master
	public static String IS_UOM_EXIST = " select count(*) from uom_master where uom=?  ";
	
	//Currency Master
		public static String IS_CURRENCY_EXIST = " select count(*) from currency_master where currency_name=?  ";
		public static String INSERT_INTO_CURRENCY=" INSERT INTO currency_master (Company_id,location_id,currency_name) values(?,?,?)";

		//Material Master


	public static String GET_MATERIAL_MASTER = " select i.item_Id,g.group_name,i.item_Code,i.item_Name,i.Standard_Cost,i.Standard_Cost_currency,i.Stock_Uom,i.BOM_Uom,i.Purchase_Uom,i.spec1,i.spec2,i.spec3,i.spec4,i.spec5,i.spec6,i.spec7,i.spec8,i.spec9,i.spec10,i.BOM_Number,case when i.is_active=1 then 'Active' else case when i.is_active=0 then 'Inactive' else '' end end as is_active," +
			"   ROUND(id.Min_Level,2) AS Min_Level,ROUND(id.Max_Level,2) AS Max_Level,ROUND(id.Reorder_Level,2) AS Reorder_Level,ROUND(id.Reorder_Qty,2) AS Reorder_Qty,id.Min_Order_Qty,id.Lead_Time,id.Default_Size_Range,id.Default_Leather_Id,id.Default_variant_Id,id.Buyer_Style_No,"+
            "   id.Sample_Style_No,id.Old_item_Code,id.ABC_Class,id.XYZ_Class,id.Route_Ref,case when id.Imported_Item=1 then 'Domestic' else case when id.Imported_Item=0 then 'Import' else '' end end as Imported_Item ,"+
            "   case when id.Item_Tracking_Applicable=1 then 'Yes' else case when id.Item_Tracking_Applicable=0 then 'No' else '' end end as Item_Tracking_Applicable,"+
            "   case when id.Expiry_Applicable=1 then 'Yes' else case when id.Expiry_Applicable=0 then 'No' else '' end end as Expiry_Applicable,ROUND(id.Excess_Allowance,2) AS Excess_Allowance,"+
            "   case when id.Allow_Negative_Stock=1 then 'Yes' else case when id.Allow_Negative_Stock=0 then 'No' else '' end end as Allow_Negative_Stock,"+
            "   id.Issue_With_IO,id.Tax_Category,c.Variant_Code"+
            "   from "+
            "   item i (NOLOCK) "+
            "   inner join item_details id on id.item_Id=i.item_Id "+
            "   inner join groups g with(nolock) on i.Group_Id=g.group_id " +
            "   inner join group_type  gt with(nolock) on gt.group_type_id=g.group_type_id " +
            "   left join variant_master c with(nolock) on c.Variant_Id=id.Default_variant_Id "  ;
	
	public static String SELECT_ITEM_DYNAMIC_BY_ID = " Select idf.* "+
			" FROM  "+
			" item i (NOLOCK)  "+
			" Left Join item_dynamic idf on i.item_Id = idf.item_Id "+ 
			" Where i.item_Id=? "; 
	


public static String MATERIAL_PAGE_COUNT = "SELECT DISTINCT COUNT(*) FROM   item i inner join groups g on g.group_id=i.group_id ";


public static String SELECT_MATERIAL_IMAGES=" SELECT item_id,Pic_Path from Item_image where item_id=?  ";




//public static String IS_MATERIAL_EXIST ="SELECT COUNT(*) FROM item WITH(NOLOCK) WHERE (Mat_Code=? or Mat_Name=? or spec1=? or spec2=? or spec3=? or spec4=? or spec5=? or spec6=? or spec7=? or spec8=? or spec9=? or spec10=?) ";
public static String IS_MATERIAL_EXIST ="SELECT COUNT(*) FROM item WITH(NOLOCK) WHERE (item_Code=? or item_Name=?) ";


public static String INSERT_ITEM_ATTACHMENT="INSERT INTO Item_image(Item_Id,Pic_Path) values(?,?)";





	//Material Master


public static String DELETE_ITEM= " DELETE FROM item WHERE item_Id=?";
	
	public static String DELETE_ITEM_DETAILS= " DELETE FROM item_details WHERE item_Id=?";

	public static String DELETE_ITEM_DYNAMIC = " DELETE FROM item_dynamic WHERE item_Id=? ";

public static String DELETE_MATERIAL_IMAGES =" DELETE FROM Item_image WHERE item_Id=? "; 



	public static String GET_MATERIAL_INFO = "select i.item_Id,g.group_name,i.item_Code,i.item_Name,i.Standard_Cost,i.Standard_Cost_currency,i.Stock_Uom,i.BOM_Uom,i.Purchase_Uom,i.spec1,i.spec2,i.spec3,i.spec4,i.spec5,i.spec6,i.spec7,i.spec8,i.spec9,i.spec10,i.BOM_Number,i.is_active," +
			 "   id.Min_Level,id.Max_Level,id.Reorder_Level,id.Reorder_Qty,id.Min_Order_Qty,id.Lead_Time,id.Default_Size_Range,id.Default_Leather_Id,id.Default_variant_Id,id.Buyer_Style_No,"+
             "   id.Sample_Style_No,id.ABC_Class,id.XYZ_Class,id.Route_Ref,id.Imported_Item,id.Item_Tracking_Applicable,id.Expiry_Applicable,id.Excess_Allowance,id.Allow_Negative_Stock,"+
             "   id.Issue_With_IO,id.Tax_Category,c.Variant_Name,g.group_id,gt.group_type,id.Size_applicable,id.Color_Applicable, "+
             "   id.Reservation_Applicable,id.Inspection_Required,id.Barcode_Required"+
             "   from "+
             "   item i (NOLOCK) "+
             "   inner join item_details id on id.item_Id=i.item_Id "+
             "   inner join groups g with(nolock) on i.Group_Id=g.group_id " +
             "   inner join group_type  gt with(nolock) on gt.group_type_id=g.group_type_id " +
             "   left join variant_master c with(nolock) on c.Variant_Id=id.Default_variant_Id " +
             //"   inner join Size_Range sr with(nolock) on sr.Size_Range_Id=id.Default_Size_Range " +
	         "   where i.item_Id=? " ;




	
	public static String SELECT_ITEM_DYNAMIC = " Select idf.* "
			+ " FROM  "
			+ " item  i (NOLOCK) "
			+ " Left Join item_dynamic idf on i.item_Id = idf.item_Id "
			+ " Where i.item_Id=? ";



	public static String DELETE_MAT_ATTACH =" DELETE FROM Item_image WHERE item_Id=? and Pic_Path=? "; 


	public static String DELETE_ITEM_ROW="DELETE FROM item WHERE item_Id=?";




	public static String GET_DYNAMIC_DBNAME = "SELECT dBfieldname FROM dynamicfields WITH(NOLOCK) WHERE dynamicfieldId=?";

	public static String GET_DYNAMIC_TABLEID = "SELECT tableId FROM dynamicfields WITH(NOLOCK) WHERE dynamicfieldId=?";

	//Entity

	public static String ENTITY_RIGHTS_COUNT = "SELECT count(*)"
			+ " from entity_rights er WITH(NOLOCK),"
			+ " entity e WITH(NOLOCK),"
			+ " documents d WITH(NOLOCK) ,"
			+ " subdocuments s"
			+ " WHERE er.entity_id=e.entity_id and er.subdocument_id=s.subdocument_id and s.document_id=d.document_id ";
	public static String SELECT_ENTITY_RIGHTS = "SELECT er.entity_id,e.entity_name,s.subdocument_id,s.subdocument_name "
			+ " from entity_rights er WITH(NOLOCK),"
			+ " entity e WITH(NOLOCK),"
			+ " documents d WITH(NOLOCK) ,"
			+ " subdocuments s"
			+ " WHERE er.entity_id=e.entity_id and er.subdocument_id=s.subdocument_id and s.document_id=d.document_id ";
	public static String GET_ENTITY = " SELECT entity_id,entity_name FROM entity WITH(NOLOCK)";
	public static String GET_MODULES = " SELECT DISTINCT module_id,module_name FROM module WITH(NOLOCK) ";
	public static String GET_DOCUMENTS = " SELECT document_id,document_name FROM documents  WITH(NOLOCK) "+
			"WHERE module_id=? ORDER BY document_id ASC";
	//public static String GET_SUB_DOCUMENTS = " SELECT subdocument_id,subdocument_name FROM subdocuments  WITH(NOLOCK) "+
	//		"WHERE document_id=? ORDER BY subdocument_id ASC";

	public static String GET_SUB_DOCUMENTS = " SELECT sd.subdocument_id,sd.subdocument_name,d.visibility "
			+ " FROM subdocuments  sd WITH(NOLOCK) "
			+ " inner join documents d with(nolock) on sd.document_id=d.document_id"
			+ " WHERE d.document_id=? ORDER BY sd.subdocument_id ASC ";

	public static String GET_DOCUMENT_IDS = " SELECT document_id FROM documents WITH(NOLOCK) WHERE module_id=?";
	public static String GET_SUB_DOCUMENT_IDS = " SELECT subdocument_id FROM subdocuments WITH(NOLOCK) WHERE document_id=?";
	public static String IS_ENTITY_RIGHT_EXIST = " SELECT COUNT(*) FROM entity_rights WITH(NOLOCK) WHERE entity_id=? and subdocument_id=?";
	public static String GET_ENTITY_SUB_DOCUMENTS = " SELECT subdocument_id FROM entity_rights  WITH(NOLOCK) "+
			" WHERE entity_id=? ORDER BY subdocument_id ASC ";
	public static String DELETE_ENTITY_RIGHTS = " DELETE  FROM entity_rights WHERE entity_id=? AND subdocument_id=?";

	public static String DELETE_FROM_PROFILE_DETAILS = " delete from profile_detail where subdocument_id in (select subdocument_id from entity_rights where entity_id = ? ) and profile_id in (select profile_id from  profile where entity_id = ?) ";
	public static String INSERT_INTO_PROFILE_DETAILS = " insert into profile_detail(profile_id,subdocument_id,entry_permission,edit_permission,delete_permission,view_permission,excel_permission,print_permission,approval_permission) "+
			" select p.profile_id,er.subdocument_id,er.accessible,er.accessible,er.accessible,er.accessible,er.accessible,er.accessible,er.accessible from entity_rights er,profile p where er.entity_id = p.entity_id and er.entity_id =? ";

	public static String DELETE_FROM_TPCS_USER_RIGHTS= " delete from tpcs_user_rights  where subdocument_id in (select subdocument_id from entity_rights where entity_id = ? ) and tpcs_user_id in (select tpcs_user_id from  tpcs_user where entity_id = ? ) ";
	public static String INSERT_INTO_TPCS_USER_RIGHTS= " insert into tpcs_user_rights(tpcs_user_id,subdocument_id,entry_permission,edit_permission,delete_permission,view_permission,excel_permission,print_permission,approval_permission) "+
			" select tu.tpcs_user_id,er.subdocument_id,er.accessible,er.accessible,er.accessible,er.accessible,er.accessible,er.accessible,er.accessible from entity_rights er,tpcs_user tu where tu.entity_id =  er.entity_id and  er.entity_id  = ? ";

	//Profile
	public static String PROFILE_COUNT = "SELECT count(*) "
			+ "	FROM profile p WITH(NOLOCK),"
			+ " profile_detail pd WITH(NOLOCK),"
			+ " entity e WITH(NOLOCK),"
			+ " subdocuments s WITH(NOLOCK),"
			+ " entity_rights er WITH(NOLOCK)  "
			+ " WHERE p.profile_id=pd.profile_id and p.entity_id=e.entity_id "
			+ " and pd.subdocument_id=s.subdocument_id and p.entity_id=er.entity_id "
			+ " and pd.subdocument_id=er.subdocument_id  ";
	public static String SELECT_PROFILES = " SELECT p.profile_id,p.profile_name,p.entity_id,e.entity_name,s.subdocument_name,pd.entry_permission,pd.edit_permission,pd.delete_permission,pd.view_permission,pd.excel_permission,pd.print_permission,pd.approval_permission  "
			+ " FROM profile p WITH(NOLOCK),"
			+ " profile_detail pd WITH(NOLOCK),"
			+ " entity e WITH(NOLOCK),"
			+ " subdocuments s WITH(NOLOCK),"
			+ " entity_rights er WITH(NOLOCK)  "
			+ " WHERE p.profile_id=pd.profile_id and p.entity_id=e.entity_id "
			+ " and pd.subdocument_id=s.subdocument_id and p.entity_id=er.entity_id "
			+ " and pd.subdocument_id=er.subdocument_id  ";
	public static String GET_ENTITY_SELECTED_DOCUMENTS = " select er.subdocument_id,s.subdocument_name "
			+ " from entity_rights er WITH(NOLOCK),"
			+ " subdocuments s WITH(NOLOCK)"
			+ " where  er.subdocument_id=s.subdocument_id and entity_id=? and s.document_id not in (select document_id from  documents where module_id=12) order by s.document_id,er.subdocument_id";
	public static String IS_PROFILE_EXIST = "SELECT COUNT(*) FROM profile WITH(NOLOCK) WHERE profile_name=? and entity_id=? ";

	public static String GET_ENTRY_SELECTED_DOCUMENTS = " SELECT subdocument_id FROM profile_detail WHERE profile_id=? and entry_permission IS NOT NULL";
	public static String GET_EDIT_SELECTED_DOCUMENTS = " SELECT subdocument_id FROM profile_detail WHERE profile_id=? and edit_permission IS NOT NULL";
	public static String GET_DELETE_SELECTED_DOCUMENTS = " SELECT subdocument_id FROM profile_detail WHERE profile_id=? and delete_permission IS NOT NULL";
	public static String GET_VIEW_SELECTED_DOCUMENTS = " SELECT subdocument_id FROM profile_detail WHERE profile_id=? and view_permission IS NOT NULL";
	public static String GET_EXCEL_SELECTED_DOCUMENTS = " SELECT subdocument_id FROM profile_detail WHERE profile_id=? and excel_permission IS NOT NULL";
	public static String GET_PRINT_SELECTED_DOCUMENTS = " SELECT subdocument_id FROM profile_detail WHERE profile_id=? and print_permission IS NOT NULL";
	public static String GET_APPR_SELECTED_DOCUMENTS = " SELECT subdocument_id FROM profile_detail WHERE profile_id=? and approval_permission IS NOT NULL";
	public static String SELECT_PROFILE_INFO="SELECT profile_id,profile_name,entity_id FROM profile WHERE profile_id=?";
	public static String IS_PROFILE_ENTITY_EXIST = "SELECT COUNT(*) FROM profile WITH(NOLOCK) WHERE profile_name=? and entity_id=? and profile_id!=? ";

	//Users
	public static String USERS_COUNT = " SELECT count(*) FROM tpcs_user WITH(NOLOCK) WHERE entity_id=? AND customer_id=? AND factory_id=? ";
	public static String GET_IS_SYS_ADMIN=" SELECT is_sys_admin FROM users_info WHERE  user_id=?  ";
	public static String COUNT_NO_OF_USER_INFO_ACTIVE_USERS="SELECT COUNT(*) FROM users_info WHERE client_id=? AND is_active=1 ";
	public static String GET_NO_OF_USERS=" SELECT no_of_users FROM clients_info WHERE client_id=? ";
	public static String GET_LANGUAGE = "SELECT language_id,display_name FROM language WITH(NOLOCK)";
	public static String GET_ENTITY_ROLES = " SELECT role_id,role_name FROM roles WITH(NOLOCK) WHERE entity_id=? AND customer_id=? AND factory_id=?";
	public static String GET_ENTITY_PROFILES = " SELECT profile_id,profile_name FROM profile WITH(NOLOCK) WHERE entity_id=?";
	public static String GET_PROFILE_DOCUMENTS = " select pd.subdocument_id,d.subdocument_name from profile_detail pd WITH(NOLOCK),subdocuments d WITH(NOLOCK),entity_rights er WITH(NOLOCK),profile p WITH(NOLOCK) where p.profile_id=pd.profile_id and p.entity_id=er.entity_id and pd.subdocument_id=d.subdocument_id and d.subdocument_id=er.subdocument_id and pd.profile_id=? and d.document_id not in (select document_id from  documents where module_id=12) ORDER BY d.document_id,er.subdocument_id";
	public static String GET_USER_ENTRY_SELECTED_DOCUMENTS = " SELECT subdocument_id FROM tpcs_user_rights WHERE tpcs_user_id=? and entry_permission IS NOT NULL";
	public static String GET_USER_EDIT_SELECTED_DOCUMENTS = " SELECT subdocument_id FROM tpcs_user_rights WHERE tpcs_user_id=? and edit_permission IS NOT NULL";
	public static String GET_USER_DELETE_SELECTED_DOCUMENTS = " SELECT subdocument_id FROM tpcs_user_rights WHERE tpcs_user_id=? and delete_permission IS NOT NULL";
	public static String GET_USER_VIEW_SELECTED_DOCUMENTS = " SELECT subdocument_id FROM tpcs_user_rights WHERE tpcs_user_id=? and view_permission IS NOT NULL";
	public static String GET_USER_EXCEL_SELECTED_DOCUMENTS = " SELECT subdocument_id FROM tpcs_user_rights WHERE tpcs_user_id=? and excel_permission IS NOT NULL";
	public static String GET_USER_PRINT_SELECTED_DOCUMENTS = " SELECT subdocument_id FROM tpcs_user_rights WHERE tpcs_user_id=? and print_permission IS NOT NULL";
	public static String GET_USER_APPR_SELECTED_DOCUMENTS = " SELECT subdocument_id FROM tpcs_user_rights WHERE tpcs_user_id=? and approval_permission IS NOT NULL";
	public static String GET_CLIENT_USER_COUNT = "SELECT COUNT(*) FROM users_info WITH(NOLOCK) WHERE client_id=? ";
	public static String GET_CLIENT_USER_SUBSCRIPTION_COUNT = "SELECT no_of_users FROM clients_info WITH(NOLOCK) WHERE client_id=? ";
	public static String IS_USER_EXIST = "SELECT COUNT(*) FROM users_info WITH(NOLOCK) WHERE user_login_name=? ";
	public static String GET_PASSWORD = "SELECT user_id,user_password  FROM users_info  WITH(NOLOCK) WHERE user_login_name=? ";
	public static String INSERT_OPTIONS=" insert into notification_config (notification_type_id,tpcs_user_id,is_notification_on) values (?,?,?) ";
	public static String GET_USER_INFO = " SELECT tpcs_user_id,tpcs_user_login_name,tpcs_user_first_name,tpcs_user_last_name,entity_id,customer_id,factory_id,role_id,profile_id,language_id FROM tpcs_user WITH(NOLOCK) WHERE tpcs_user_id=?";
	public static String COUNT_NO_OF_USERS_ACTIVE=" SELECT COUNT(*) FROM users_info WHERE client_id=? AND is_active=1  AND user_id<>? ";
	public static String GET_ACTIVE_USER_LIST=" SELECT DISTINCT user_id,isnull(is_active,0) FROM users_info WHERE client_id=? ";
	public static String GET_IS_ACTIVE="SELECT is_active From users_info where user_id=? ";
	public static String IS_ENTITY_USER_EXIST = "SELECT COUNT(*) FROM users_info WITH(NOLOCK) WHERE user_login_name=? AND user_id!=? AND client_id!=? ";

	public static String GET_CLIENT_EMAIL = "SELECT ci.email_id,ui.user_id  FROM users_info ui WITH(NOLOCK),clients_info ci WITH(NOLOCK) WHERE ui.client_id = ci.client_id AND ui.user_login_name=?";
	public static String GET_CLIENT_USER_INFO = "SELECT user_login_name,user_password  FROM users_info ui WITH(NOLOCK) WHERE ui.user_id=? AND ui.client_id=?";

	/* CUSTOMER MASTER */
	public static String GET_CUSTOMER_INFO = " SELECT p.party_id,p.party_code,p.party_name,p.Display_name,p.party_tag,p.Contact_salutation,p.Contact_Firstname,p.Contact_Lastname,p.Contact_Designation,p.Contact_Phone,p.Contact_Email, "+ 
			" p.Company_phone,p.Company_fax,p.Company_Email,p.Status,p.currency_id,p.Credit_Days,p.Bill_Street,p.Bill_Location,p.Bill_City,p.Bill_State,p.Bill_Country,p.Bill_Zip,p.Ship_Street, "+ 
			" p.Ship_Location,p.Ship_City,p.Ship_state,p.Ship_Country,p.Ship_Zip,p.Ship_Phone,p.PAN_NO,p.VAT_No,p.Servtax_No,p.Insurance_details,p.Account_group_id,p.Bill_tracking,p.Notes,ag.Group_Name,p.Payment_Terms,ag.group_type,c.currency_name "+ 
			" FROM party p "+
			" INNER JOIN  currency_master c ON c.currency_id=p.currency_id "+
			" LEFT JOIN account_group ag ON ag.group_id=p.account_group_id WHERE p.party_id=? ";
	
	public static String GET_CUSTOMER_INFO_IN_ACC = " SELECT p.party_id,p.party_code,p.party_name,p.Display_name,p.party_tag,p.Contact_salutation,p.Contact_Firstname,p.Contact_Lastname,p.Contact_Designation,p.Contact_Phone,p.Contact_Email, "+ 
			" p.Company_phone,p.Company_fax,p.Company_Email,p.Status,p.currency_id,p.Credit_Days,p.Bill_Street,p.Bill_Location,p.Bill_City,p.Bill_State,p.Bill_Country,p.Bill_Zip,p.Ship_Street, "+ 
			" p.Ship_Location,p.Ship_City,p.Ship_state,p.Ship_Country,p.Ship_Zip,p.Ship_Phone,p.PAN_NO,p.VAT_No,p.Servtax_No,p.Insurance_details,p.Account_group_id,p.Bill_tracking,p.Notes,ag.Group_Name,p.Payment_Terms,ag.group_type,cm.currency_name "+ 
			" FROM party p "
			+ " LEFT JOIN account_group ag ON ag.group_id=p.account_group_id "
			+ " INNER JOIN currency_master cm ON cm.currency_id=p.currency_id WHERE p.account_id=? ";


	public static String GET_ALL_CUSTOMERS = " SELECT p.party_id,p.party_code,p.party_name,p.Display_name,p.party_tag,p.Contact_salutation,p.Contact_Firstname,p.Contact_Lastname,p.Contact_Designation,p.Contact_Phone,p.Contact_Email, "+ 
			" p.Company_phone,p.Company_fax,p.Company_Email,case when p.status=1 then 'Active' else case when p.status=0 then 'Inactive' else '' end end as status,p.Credit_Days,p.Bill_Street,p.Bill_Location,p.Bill_City,p.Bill_State,p.Bill_Country,p.Bill_Zip,p.Ship_Street, "+
			" p.Ship_Location,p.Ship_City,p.Ship_state,p.Ship_Country,p.Ship_Zip,p.Ship_Phone,p.PAN_NO,p.VAT_No,p.Servtax_No,p.Insurance_details,p.Bill_tracking,p.Notes,ag.Group_Name,c.currency_name "+ 
			" FROM party p "+
			" LEFT JOIN account_group ag ON ag.group_id=p.account_group_id "+ 
			" LEFT JOIN currency_master c ON c.currency_id=p.currency_id ";

	public static String GET_CUSTOMERS_COUNT = " SELECT DISTINCT COUNT(*)  "+
			" FROM party p LEFT JOIN account_group ag ON ag.group_id=p.account_group_id "+ 
			" LEFT JOIN currency_master c ON c.currency_id=p.currency_id ";

	public static String DELETE_CUSTOMER="DELETE FROM party WHERE party_id=? ";

	public static String INSERT_INTO_CUSTOMER="INSERT INTO party (Company_id,location_id,entered_by,party_code,party_name,Display_name,party_tag,Contact_salutation,Contact_Firstname,Contact_Lastname,Contact_Designation,Contact_Phone, "+  
			" Contact_Email,Company_phone,Company_fax,Company_Email,Status,currency_id,Credit_Days,Bill_Street,Bill_Location,Bill_City,Bill_State,Bill_Country,Bill_Zip,   "+
			" Ship_Street,Ship_Location,Ship_City,Ship_state,Ship_Country,Ship_Zip,Ship_Phone,PAN_NO,VAT_No,Servtax_No,Insurance_details,Account_group_id,Bill_tracking,Notes,Payment_Terms, "+  
			" entered_on,Host_Name)                          "+
			" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,GETDATE(),HOST_NAME()) "; 


	public static String GET_SALES_ORDER_PROFORMA_HEADER=" SELECT distinct so.so_id,so.prefix+convert(nvarchar,so.so_no) as so_no,convert(nvarchar,so.so_date,105) as so_date , so.customer_order_no,so.party_id,"
			+ " p.party_name,so.currency_id,cm.currency_name,so.executive_id,e.employee_name,so.exchange_rate,so.remarks,so.version_no,so.tag,so.payment_terms,so.credit_days " +
			" FROM  sales_order so " +
			" left join party p with(nolock) on p.party_id=so.party_id and p.party_tag='customer' " +
			" left join employees e with(nolock) on e.employee_id=so.executive_id " +
			" left join currency_master cm with(nolock) on so.currency_id=cm.currency_id " +
			" where  so.company_Id=? and so.year_id=? and so.location_id=? and  so.so_id=? ";

	public static String GET_BANK_RECPT_HEADER="SELECT f.fin_tr_id,convert(nvarchar,f.fin_tr_date,105) as fin_tr_date,cm.currency_name,f.exchange_rate "
			+ " FROM Fin_tr f left join currency_master cm with(nolock) on f.currency_id=cm.currency_id "
			+ " WHERE  f.company_Id=? and f.year_id=? and f.location_id=? and  f.fin_tr_id=? ";

	
	/*public static String GET_ACCOUNT_BOOK=" select a.account_id,a.Account_name,v.Fin_Tr_no,convert(nvarchar,v.fin_tr_date,105) as fin_tr_date,t.towards_Account_id,P.Account_Name Particulars, "+ 
			" v.cheq_no ,convert(nvarchar,V.cheq_date,105) as cheq_date,t.Debit,T.credit, V.Reference "+ 
			" from account a, Fin_TR_details t, Fin_Tr V,Account p ";*/
	public static String GET_ACCOUNT_BOOK=" select acc.account_id,acc.Account_name,ft.prefix+convert(nvarchar,ft.Fin_Tr_no) as Fin_Tr_no,convert(nvarchar,ft.fin_tr_date,105) as fin_tr_date,ftd.towards_Account_id,a.Account_Name Particulars,"+  
				" ft.cheq_no ,convert(nvarchar,ft.cheq_date,105) as cheq_date,ftd.Debit_fcy,ftd.credit_fcy, ft.Reference "+ 
				" from  Fin_TR ft "+
				" inner join Fin_TR_details ftd on ft.fin_tr_type  = ftd.fin_tr_type  and ft.fin_tr_id = ftd.fin_tr_id and ft.company_id = ftd.company_id   and ft.location_id = ftd.location_id and ft.year_id = ftd.year_id "+  
				" inner join account acc on acc.account_id = ftd.Account_Id "+
				" inner join Account a on ftd.towards_Account_id = a.Account_Id "+
				" Inner join account_group ag on ag.group_id=acc.group_id "+
				" Inner join account_group_type agt on agt.Group_Type=ag.Group_Type ";

	public static String GET_ACCOUNT_BOOK_COUNT=" select distinct count(*) "+  
				" from  Fin_TR ft "+
				" inner join Fin_TR_details ftd on ft.fin_tr_type  = ftd.fin_tr_type  and ft.fin_tr_id = ftd.fin_tr_id and ft.company_id = ftd.company_id   and ft.location_id = ftd.location_id and ft.year_id = ftd.year_id "+  
				" inner join account acc on acc.account_id = ftd.Account_Id "+
				" inner join Account a on ftd.towards_Account_id = a.Account_Id "+
				" Inner join account_group ag on ag.group_id=acc.group_id "+
				" Inner join account_group_type agt on agt.Group_Type=ag.Group_Type ";
	
	
	public static String GET_DAY_BOOK=" select acc.account_id,acc.Account_name,ft.prefix+convert(nvarchar,ft.Fin_Tr_no) as Fin_Tr_no,convert(nvarchar,ft.fin_tr_date,105) as fin_tr_date,ft.fin_tr_type,ftd.Debit_fcy,ftd.credit_fcy,isnull(ft.Reference,'') Reference,ft.Remarks,ftd.fin_tr_det_id "+ 
					"	from  Fin_TR ft  "+
			        "   inner join Fin_TR_details ftd on ft.fin_tr_type  = ftd.fin_tr_type  and ft.fin_tr_id = ftd.fin_tr_id and ft.company_id = ftd.company_id   and ft.location_id = ftd.location_id and ft.year_id = ftd.year_id "+
					"	inner join account acc on acc.account_id = ftd.Account_Id ";
	
	public static String GET_DAY_BOOK_COUNT=" select distinct count(*)  "+ 
			"	from  Fin_TR ft  "+
	        "   inner join Fin_TR_details ftd on ft.fin_tr_type  = ftd.fin_tr_type  and ft.fin_tr_id = ftd.fin_tr_id and ft.company_id = ftd.company_id   and ft.location_id = ftd.location_id and ft.year_id = ftd.year_id "+
	        "	inner join account acc on acc.account_id = ftd.Account_Id ";
				

	//Purchase Invoice

	public static String GET_PURCHASE_INVOICE = "select  pi.prefix+convert(nvarchar,pi.invoice_no) as invoice_no,pi.draft_tag,po.prefix+convert(nvarchar,po.po_no) as po_no,convert(nvarchar,pi.invoice_date,105) as invoice_date,p.party_name ,pi.email_sent_to,pi.Bill_to_address,pi.Ship_to_address,pi.Internal_Memo,pi.reference_no,g.group_name,m.material_code,m.material_name,pid.material_description, "+ 
			" pid.quantity,pid.sku,round(pid.price_fcy,3) as price_fcy, "+
			" pid.discount_percent,round(pid.discount_fcy,2) as discount_fcy,round(pid.value_before_tax_fcy,2) as value_before_tax_fcy ,pid.remarks,cm.currency_name,v.variant_name, "+
			" round(pid.ed_percentage,2) as ed_percentage,round(pid.ecess_percentage,2) as ecess_percentage,round(pid.shecess_percentage,2) as shecess_percentage,round(pid.vat_percentage,2) as vat_percentage,round(pid.cst_percentage,2) as cst_percentage,round(pid.gst_percentage,2) as gst_percentage,round(pid.service_tax_percentage,2) as service_tax_percentage, "+
			" round(pid.ed_fcy,2) as ed_fcy,round(pid.ecess_fcy,2) as ecess_fcy,round(pid.shecess_fcy,2) as shecess_fcy,round(pid.vat_fcy,2) as vat_fcy,round(pid.cst_fcy,2) as cst_fcy,round(pid.gst_fcy,2) as gst_fcy,round(pid.service_tax_fcy,2) as service_tax_fcy,round(pid.value_after_tax_fcy,2) as value_after_tax_fcy , "+
			" pi.invoice_id,pid.invoice_det_id  "+
			" from purchase_invoice pi "
			+ " inner join purchase_invoice_details pid on pi.company_id=pid.company_id and "+ 
			" pi.year_id=pid.year_id and pi.location_id=pid.location_id and pi.invoice_id=pid.invoice_id "+ 
			" left join po po on po.po_id=pi.po_id "+
			" inner join material m on pid.material_id=m.material_id "+ 
			" inner join groups g on g.group_id=m.group_id  "+
			" inner join party p on pi.party_id=p.party_id and p.party_tag='vendor' "+
			" left join variant_master v on v.variant_id=pid.variant_id "+
			/*	 " inner join uom_master um with(nolock) on sid.uom=um.uom " +*/
			" left join currency_master cm on  cm.currency_id=pi.currency_id "+
			" left join purchase_invoice_dynamic pidf on pi.invoice_id = pidf.invoice_id "+
			" left join purchase_invoice_details_dynamic piddf on pid.invoice_det_id = piddf.invoice_det_id ";
	
	public static String GET_PURCHASE_RETURN = "select  pi.prefix+convert(nvarchar,pi.invoice_no) as return_no,isnull(pi.draft_tag,0) as draft_tag,piref.prefix+convert(nvarchar,piref.invoice_no) as invoice_no,convert(nvarchar,pi.invoice_date,105) as return_date,p.party_name ,pi.email_sent_to,pi.Bill_to_address,pi.Ship_to_address,pi.Internal_Memo,pi.reference_no,g.group_name,m.material_code,m.material_name,pid.material_description, "+ 
			" abs(pid.quantity) quantity,pid.sku,round(pid.price_fcy,3) as price_fcy, "+
			" pid.discount_percent,abs(round(pid.discount_fcy,2)) as discount_fcy, abs(round(pid.value_before_tax_fcy,2)) as value_before_tax_fcy ,pid.remarks,cm.currency_name,v.variant_name, "+
			" round(pid.ed_percentage,2) as ed_percentage,round(pid.ecess_percentage,2) as ecess_percentage,round(pid.shecess_percentage,2) as shecess_percentage,round(pid.vat_percentage,2) as vat_percentage,round(pid.cst_percentage,2) as cst_percentage,round(pid.gst_percentage,2) as gst_percentage,round(pid.service_tax_percentage,2) as service_tax_percentage, "+
			" abs(round(pid.ed_fcy,2)) as ed_fcy,abs(round(pid.ecess_fcy,2)) as ecess_fcy,abs(round(pid.shecess_fcy,2)) as shecess_fcy,abs(round(pid.vat_fcy,2)) as vat_fcy,abs(round(pid.cst_fcy,2)) as cst_fcy,abs(round(pid.gst_fcy,2)) as gst_fcy,abs(round(pid.service_tax_fcy,2)) as service_tax_fcy,abs(round(pid.value_after_tax_fcy,2)) as value_after_tax_fcy , "+
			" pi.invoice_id,pid.invoice_det_id  "+
			" from purchase_invoice pi "
			+ " inner join purchase_invoice_details pid on pi.company_id=pid.company_id and "+ 
			" pi.year_id=pid.year_id and pi.location_id=pid.location_id and pi.invoice_id=pid.invoice_id "+ 
			" left join purchase_invoice piref on piref.invoice_id=pi.invoice_id_returned "+
			" inner join material m on pid.material_id=m.material_id "+ 
			" inner join groups g on g.group_id=m.group_id  "+
			" inner join party p on pi.party_id=p.party_id and p.party_tag='vendor' "+
			" left join variant_master v on v.variant_id=pid.variant_id "+
			/*	 " inner join uom_master um with(nolock) on sid.uom=um.uom " +*/
			" left join currency_master cm on  cm.currency_id=pi.currency_id "+
			" left join purchase_invoice_dynamic pidf on pi.invoice_id = pidf.invoice_id "+
			" left join purchase_invoice_details_dynamic piddf on pid.invoice_det_id = piddf.invoice_det_id ";

	public static String PURCHASE_INVOICE_PAGE_COUNT = " SELECT  count(pi.invoice_id) "+
			" from purchase_invoice pi "+
			" inner join purchase_invoice_details pid on pi.company_id=pid.company_id and "+ 
			" pi.year_id=pid.year_id and pi.location_id=pid.location_id and pi.invoice_id=pid.invoice_id "+ 
			" left join po po on po.po_id=pi.po_id "+
			" inner join material m on pid.material_id=m.material_id "+ 
			" inner join groups g on g.group_id=m.group_id  "+
			" inner join party p on pi.party_id=p.party_id and p.party_tag='vendor' "+
			" left join variant_master v on v.variant_id=pid.variant_id "+
			/*	 " inner join uom_master um with(nolock) on sid.uom=um.uom " +*/
			" left join currency_master cm on  cm.currency_id=pi.currency_id "+
			" left join purchase_invoice_dynamic pidf on pi.invoice_id = pidf.invoice_id "+
			" left join purchase_invoice_details_dynamic piddf on pid.invoice_det_id = piddf.invoice_det_id ";
	
	public static String PURCHASE_RETURN_PAGE_COUNT = " SELECT  count(pi.invoice_id) "+
			" from purchase_invoice pi "+
			" inner join purchase_invoice_details pid on pi.company_id=pid.company_id and "+ 
			" pi.year_id=pid.year_id and pi.location_id=pid.location_id and pi.invoice_id=pid.invoice_id "+ 
			" left join purchase_invoice piref on piref.invoice_id_returned=pi.invoice_id "+
			" inner join material m on pid.material_id=m.material_id "+ 
			" inner join groups g on g.group_id=m.group_id  "+
			" inner join party p on pi.party_id=p.party_id and p.party_tag='vendor' "+
			" left join variant_master v on v.variant_id=pid.variant_id "+
			/*	 " inner join uom_master um with(nolock) on sid.uom=um.uom " +*/
			" left join currency_master cm on  cm.currency_id=pi.currency_id "+
			" left join purchase_invoice_dynamic pidf on pi.invoice_id = pidf.invoice_id "+
			" left join purchase_invoice_details_dynamic piddf on pid.invoice_det_id = piddf.invoice_det_id ";
	


	public static String   GET_NOT_SAVED_USER_IN_PI="  SELECT t.tpcs_user_id,t.tpcs_user_first_name,t.tpcs_user_last_name,tc.image_url,t.tpcs_user_login_name "+
			" FROM tpcs_user t WITH(NOLOCK)  left join tpcs_contact tc on t.tpcs_user_id = tc.user_id "+
			" WHERE t.tpcs_user_id NOT IN (SELECT user_id from purchase_invoice_Followers WHERE invoice_id=?) "; 

	public static String   GET_PI_SAVED_FOLLOWERS="  select t.tpcs_user_id,t.tpcs_user_first_name,t.tpcs_user_last_name, "+ 
			"	tc.image_url,t.tpcs_user_login_name "+
			"	from purchase_invoice_Followers pif , "+
			"	tpcs_user t WITH(NOLOCK)  "+
			"	left join tpcs_contact tc on t.tpcs_user_id = tc.user_id "+ 
			"	WHERE pif.user_id=t.tpcs_user_id "+
			"	and pif.invoice_id=? and t.tpcs_user_id<>? "; 

	public static String  INSERT_PI_USER= " if not exists(select * from purchase_invoice_followers where user_id=? and invoice_id=?)"
			+ " begin"
			+ " INSERT INTO purchase_invoice_followers(company_id,year_id,location_id,invoice_id,User_id)values(?,?,?,?,?)"
			+ " end ";

	public static String INSERT_PURCHASE_INVOICE_ATTACHMENT=" INSERT INTO Purchase_invoice_URL(company_id,year_id,location_id,invoice_id,File_type,URL) VALUES(?,?,?,?,?,?) ";

	public static String GET_PIDETAILS = " SELECT pi.invoice_id,pi.invoice_no,p.party_name,m.material_name FROM purchase_invoice pi "+ 
			" LEFT JOIN purchase_invoice_details pid ON pi.invoice_id=pid.invoice_id "+
			" LEFT JOIN party p On pi.party_id=p.party_id "+
			" LEFT JOIN material m ON pid.Material_id=m.material_id WHERE pi.invoice_id=? "; 

	public static String  GET_PI_FOLLOWERS=" SELECT tu.tpcs_user_login_name  FROM tpcs_user tu  "+
			" LEFT JOIN Purchase_Invoice_Followers pif ON pif.User_id=tu.tpcs_user_id WHERE pif.invoice_id=? " ;

	public static String GET_PURCHASE_INVOICE_HEADER = " select  pi.invoice_id,pi.prefix,pi.invoice_no,convert(nvarchar,pi.invoice_date,105) as 'si date',pi.reference_no,pi.party_id, "
			+ " p.party_name as 'customer', pi.currency_id,  "
			+ " pi.exchange_rate,pi.version_no,pi.remarks,pi.po_id,po.prefix+convert(nvarchar,po.po_no) as po_no,isnull(pi.email_sent_to,'') email_sent_to, isnull(pi.bill_to_address,'') bill_to_address, "
			+ " isnull(pi.ship_to_address,'') ship_to_address,isnull(pi.internal_memo,'') internal_memo,cm.currency_name,pi.charge_fcy1,pi.charge_fcy2,pi.charge_fcy3 ,"
			+ " pi.charge_acc_id1,isnull(acc1.account_name,'') as charge1,pi.charge_acc_id2 ,isnull(acc2.account_name,'') as charge2,pi.charge_acc_id3 ,isnull(acc3.account_name,'') as charge3 ,pi.draft_tag,pi.payment_terms,pi.credit_days ,pi.charge_cc_id1,cc1.costcenter_name,pi.charge_cc_id2,cc2.costcenter_name,pi.charge_cc_id3,cc3.costcenter_name"
			+ " from purchase_invoice pi "
			+ " inner join party p on p.party_id=pi.party_id  and party_tag='vendor'"
			+ " left join po po on po.po_id=pi.po_id "
			+ " inner join currency_master cm on pi.currency_id=cm.currency_id"
			+ " left join account acc1 with(nolock) on pi.charge_acc_id1=acc1.account_id"
			+ " left join account acc2 with(nolock) on pi.charge_acc_id2=acc2.account_id"
			+ " left join account acc3 with(nolock) on pi.charge_acc_id3=acc3.account_id"
			+ " left join costcenter cc1 with(nolock) on pi.charge_cc_id1=cc1.costcenter_id"
			+ " left join costcenter cc2 with(nolock) on pi.charge_cc_id2=cc2.costcenter_id"
			+ " left join costcenter cc3 with(nolock) on pi.charge_cc_id3=cc3.costcenter_id"
			+ " where pi.company_id=? and pi.year_id=? and pi.location_id=? and pi.invoice_id=?";
	
	public static String GET_PURCHASE_RETURN_HEADER = " select  prtn.invoice_id,prtn.prefix,prtn.invoice_no as returnNo,convert(nvarchar,prtn.invoice_date,105) as 'pi date',prtn.reference_no,prtn.party_id, "
			+ " p.party_name as 'customer', prtn.currency_id,  "
			+ " prtn.exchange_rate,prtn.version_no,prtn.remarks,prtn.invoice_id_returned,pi.prefix+convert(nvarchar,pi.invoice_no) as invoice_no,isnull(prtn.email_sent_to,'') email_sent_to, isnull(prtn.bill_to_address,'') bill_to_address, "
			+ " isnull(prtn.ship_to_address,'') ship_to_address,isnull(prtn.internal_memo,'') internal_memo,cm.currency_name,abs(prtn.charge_fcy1) charge_fcy1,abs(prtn.charge_fcy2) charge_fcy2,abs(prtn.charge_fcy3)  charge_fcy3,"
			+ " prtn.charge_acc_id1,isnull(acc1.account_name,'') as charge1,prtn.charge_acc_id2 ,isnull(acc2.account_name,'') as charge2,prtn.charge_acc_id3 ,isnull(acc3.account_name,'') as charge3 ,prtn.draft_tag,prtn.payment_terms,prtn.credit_days ,prtn.charge_cc_id1,cc1.costcenter_name,prtn.charge_cc_id2,cc2.costcenter_name,prtn.charge_cc_id3,cc3.costcenter_name"
			+ " from purchase_invoice prtn "
			+ " inner join party p on p.party_id=prtn.party_id  and party_tag='vendor'"
			+ " left join purchase_invoice pi on prtn.invoice_id_returned=pi.invoice_id "
			+ " inner join currency_master cm on prtn.currency_id=cm.currency_id"
			+ " left join account acc1 with(nolock) on prtn.charge_acc_id1=acc1.account_id"
			+ " left join account acc2 with(nolock) on prtn.charge_acc_id2=acc2.account_id"
			+ " left join account acc3 with(nolock) on prtn.charge_acc_id3=acc3.account_id"
			+ " left join costcenter cc1 with(nolock) on prtn.charge_cc_id1=cc1.costcenter_id"
			+ " left join costcenter cc2 with(nolock) on prtn.charge_cc_id2=cc2.costcenter_id"
			+ " left join costcenter cc3 with(nolock) on prtn.charge_cc_id3=cc3.costcenter_id"
			+ " where prtn.company_id=? and prtn.year_id=? and prtn.location_id=? and prtn.invoice_id=?";
	

	public static String SELECT_PURCHASE_INVOICE_HEADER_DYNAMIC = " Select pidf.* "
			+ " FROM  "
			+ " purchase_invoice pi (NOLOCK) "
			+ " Left Join purchase_invoice_dynamic pidf on pi.invoice_id = pidf.invoice_id "
			+ " Where pi.invoice_id=? ";
	
	public static String SELECT_PURCHASE_RETURN_HEADER_DYNAMIC = " Select pidf.* "
			+ " FROM  "
			+ " purchase_invoice pi (NOLOCK) "
			+ " Left Join purchase_return_dynamic pidf on pi.invoice_id = pidf.invoice_id "
			+ " Where pi.invoice_id=? ";
	

	public static String GET_PURCHASE_INVOICE_DETAIL_FOR_GRID = " select g.group_id,g.group_name,m.Material_id,m.Material_name,pid.quantity,pid.sku,pid.price_fcy,  "+
			" pid.discount_percent,pid.discount_fcy,(isnull(pid.quantity,0)*isnull(pid.price_fcy,0)) as amount,pid.remarks,pid.invoice_det_id,var.variant_id,var.variant_name,pid.material_description , "+ 
			" pid.Ed_percentage,pid.Ecess_percentage,pid.ShEcess_percentage,pid.VAT_percentage,pid.CST_percentage,pid.Service_tax_percentage,pid.tax_group_id "+
			" from purchase_invoice_details pid  "+
			" inner join material m on pid.Material_id=m.Material_id "+  
			" inner join groups g on g.group_id=m.group_id  "+
			" left join variant_master var on pid.variant_id=var.variant_id "+
			" where pid.invoice_id=? order by pid.invoice_det_id desc ";
	
	public static String GET_PURCHASE_RETURN_DETAIL_FOR_GRID = " select g.group_id,g.group_name,m.Material_id,m.Material_name,abs(pid.quantity) as quantity,pid.sku,pid.price_fcy,  "+
			" pid.discount_percent,pid.discount_fcy,(abs(pid.quantity)*pid.price_fcy) value_before_dis,pid.remarks,pid.invoice_det_id,var.variant_id,var.variant_name,pid.material_description , "+ 
			" pid.Ed_percentage,pid.Ecess_percentage,pid.ShEcess_percentage,pid.VAT_percentage,pid.CST_percentage,pid.Service_tax_percentage,pid.tax_group_id "+
			" from purchase_invoice_details pid  "+
			" inner join material m on pid.Material_id=m.Material_id "+  
			" inner join groups g on g.group_id=m.group_id  "+
			" left join variant_master var on pid.variant_id=var.variant_id "+
			" where pid.invoice_id=? order by pid.invoice_det_id desc ";
	
	
	public static String SELECT_PURCHASE_INVOICE_DETAIL_DYNAMIC = " Select piddf.* "
			+ " FROM  "
			+ " purchase_invoice pi (NOLOCK) "
			+ " Left Join purchase_invoice_details_dynamic piddf on pi.invoice_id = piddf.invoice_id "
			+ " Where pi.invoice_id=? ";
	
	public static String SELECT_PURCHASE_RETURN_DETAIL_DYNAMIC = " Select piddf.* "
			+ " FROM  "
			+ " purchase_invoice pi (NOLOCK) "
			+ " Left Join purchase_return_details_dynamic piddf on pi.invoice_id = piddf.invoice_id "
			+ " Where pi.invoice_id=? ";
	

	public static String SELECT_PURCHASE_INVOICE_ATTACHMENT=" SELECT invoice_id,File_type,isnull(URL,''),reverse(substring(reverse(URL), 1,charindex('/', reverse(URL))-1)) from purchase_Invoice_URL where invoice_id=? ";

	public static String DELETE_PURCHASE_INVOICE_ATTACH =" DELETE FROM Purchase_invoice_URL WHERE invoice_id=? and url=? ";

	public static String SELECT_PURCHASE_INVOICE_DETAIL_DYNAMIC_BY_ID = " Select pdf.*, pidf.* "
			+ " FROM  "
			+ " purchase_invoice pi (NOLOCK) "
			+ " Join purchase_invoice_details pid on pi.invoice_id = pid.invoice_id "
			+ " Left Join purchase_invoice_dynamic pdf on pi.invoice_id = pdf.invoice_id "
			+ " Left Join purchase_invoice_details_dynamic pidf on pid.invoice_det_id = pidf.invoice_det_id "
			+ " Where pi.invoice_id=? AND pid.invoice_det_id=? ";
	
	public static String SELECT_PURCHASE_RETURN_DETAIL_DYNAMIC_BY_ID = " Select pdf.*, pidf.* "
			+ " FROM  "
			+ " purchase_invoice pi (NOLOCK) "
			+ " Join purchase_invoice_details pid on pi.invoice_id = pid.invoice_id "
			+ " Left Join purchase_return_dynamic pdf on pi.invoice_id = pdf.invoice_id "
			+ " Left Join purchase_return_details_dynamic pidf on pid.invoice_det_id = pidf.invoice_det_id "
			+ " Where pi.invoice_id=? AND pid.invoice_det_id=? ";
	

	public static String GET_PURCHASE_INVOICE_PRINT_HEADER=" SELECT distinct pi.invoice_id,pi.prefix+convert(nvarchar,pi.invoice_no) as invoice_no,convert(nvarchar,pi.invoice_date,105) as inv_date , pi.reference_no,pi.party_id,p.party_name,pi.currency_id,cm.currency_name,pi.exchange_rate,pi.remarks,pi.version_no,pi.email_sent_to,pi.bill_to_address,pi.ship_to_address,pi.internal_memo,"
			+ " pi.charge_fcy1,pi.charge_fcy2,pi.charge_fcy3 ,"
			+ " isnull(acc1.account_name,'') as charge1,isnull(acc2.account_name,'') as charge2,isnull(acc3.account_name,'') as charge3 ,pi.payment_terms,pi.credit_days ,pi.payment_terms,pi.credit_days  "
			+ " FROM  purchase_invoice pi "
			+ " inner join party p with(nolock) on p.party_id=pi.party_id and p.party_tag='vendor' "
			+ " left join currency_master cm with(nolock) on pi.currency_id=cm.currency_id "
			+ " left join account acc1 with(nolock) on pi.charge_acc_id1=acc1.account_id"
			+ " left join account acc2 with(nolock) on pi.charge_acc_id2=acc2.account_id"
			+ " left join account acc3 with(nolock) on pi.charge_acc_id3=acc3.account_id"
			+ " where  pi.company_Id=? and pi.year_id=? and pi.location_id=? and  pi.invoice_id=? ";
	
	
	public static String GET_PURCHASE_RETURN_PRINT_HEADER=" SELECT distinct pi.invoice_id,pi.prefix+convert(nvarchar,pi.invoice_no) as invoice_no,convert(nvarchar,pi.invoice_date,105) as inv_date , pi.reference_no,pi.party_id,p.party_name,pi.currency_id,cm.currency_name,pi.exchange_rate,pi.remarks,pi.version_no,pi.email_sent_to,pi.bill_to_address,pi.ship_to_address,pi.internal_memo,"
			+ " abs(pi.charge_fcy1) charge_fcy1,abs(pi.charge_fcy2) charge_fcy2,abs(pi.charge_fcy3) charge_fcy3,"
			+ " isnull(acc1.account_name,'') as charge1,isnull(acc2.account_name,'') as charge2,isnull(acc3.account_name,'') as charge3,pi.payment_terms,pi.credit_days  "
			+ " FROM  purchase_invoice pi "
			+ " inner join party p with(nolock) on p.party_id=pi.party_id and p.party_tag='vendor' "
			+ " left join currency_master cm with(nolock) on pi.currency_id=cm.currency_id "
			+ " left join account acc1 with(nolock) on pi.charge_acc_id1=acc1.account_id"
			+ " left join account acc2 with(nolock) on pi.charge_acc_id2=acc2.account_id"
			+ " left join account acc3 with(nolock) on pi.charge_acc_id3=acc3.account_id"
			+ " where  pi.company_Id=? and pi.year_id=? and pi.location_id=? and  pi.invoice_id=? ";
	



	//For create account from page

	public static String SELECT_ACCOUNT_GROUP=" select group_id,group_name,parent_group_id from account_group ";

	//Bank Receipt

	public static String INSERT_FIN_TR_ATTACHMENT=" insert into fin_tr_url(company_id,year_id,location_id,fin_tr_type,fin_tr_id,file_type,url) values(?,?,?,?,?,?,?) ";
	public static String SELECT_FIN_TR_ATTACHMENT=" select fin_tr_id,file_type,isnull(url,''),reverse(substring(reverse(URL), 1,charindex('/', reverse(URL))-1)) from fin_tr_url where fin_tr_id=? ";
	public static String DELETE_FIN_TR_ATTACHMENT =" DELETE FROM fin_tr_url WHERE fin_tr_id=? and url=? ";

	public static String GET_BANK_RECEIPT_HEADER="select ft.fin_tr_type,ft.fin_tr_id,ft.prefix,ft.fin_tr_no,ft.prefix+convert(nvarchar,ft.fin_tr_no) as fin_tr_no,convert(nvarchar,ft.fin_tr_date,105) as fin_tr_date,cur.currency_id,cur.currency_name,"
			+ " ft.exchange_rate,ft.cheq_no,convert(nvarchar,ft.cheq_date,105) as cheq_date,ft.remarks,ft.internal_memo,ftd.account_id,acc.account_name,"
			+ " ftd.towards_account_id,tacc.account_name ,ftd.credit,ag.group_type,ftd.fin_tr_det_id,agt.category"
			+ " from fin_tr ft"
			+ " inner join fin_tr_details ftd on ft.fin_tr_id=ftd.fin_tr_id and ftd.account_id=ft.credit_to_account_id"
			+ " inner join currency_master cur on ft.currency_id=cur.currency_id"
			+ " inner join account acc on ftd.account_id=acc.account_id"
			+ " inner join account tacc on ftd.towards_account_id=tacc.account_id"
			+ " inner join account_group ag on ag.group_id=acc.group_id"
			+ " inner join account_group_type agt on ag.group_type=agt.group_type"
			+ " where ft.fin_tr_id=?";
	public static String GET_BANK_PAYMENT_HEADER="select ft.fin_tr_type,ft.fin_tr_id,ft.prefix,ft.fin_tr_no,ft.prefix+convert(nvarchar,ft.fin_tr_no) as fin_tr_no,convert(nvarchar,ft.fin_tr_date,105) as fin_tr_date,cur.currency_id,cur.currency_name,"
			+ " ft.exchange_rate,ft.cheq_no,convert(nvarchar,ft.cheq_date,105) as cheq_date,ft.remarks,ft.internal_memo,ftd.account_id,acc.account_name,"
			+ " ftd.towards_account_id,tacc.account_name ,ftd.debit,ag.group_type,ftd.fin_tr_det_id,agt.category"
			+ " from fin_tr ft"
			+ " inner join fin_tr_details ftd on ft.fin_tr_id=ftd.fin_tr_id and ftd.account_id=ft.debit_to_account_id"
			+ " inner join currency_master cur on ft.currency_id=cur.currency_id"
			+ " inner join account acc on ftd.account_id=acc.account_id"
			+ " inner join account tacc on ftd.towards_account_id=tacc.account_id"
			+ " inner join account_group ag on ag.group_id=acc.group_id"
			+ " inner join account_group_type agt with(nolock) on ag.group_type=agt.group_type"
			+ " where ft.fin_tr_id=?";
	public static String GET_BANK_RECEIPT_BALANCE_DUE="select sum(debit_fcy)-sum(credit_fcy) "
			+ " from fin_tr_details"
			+ " where account_id=? and not(fin_tr_id=? and fin_tr_type='REC') and company_id=? and location_id=?";
	public static String GET_BANK_PAYMENT_BALANCE_DUE="select round(sum(credit_fcy)-sum(debit_fcy),2) "
			+ " from fin_tr_details"
			+ " where account_id=? and not(fin_tr_id=? and fin_tr_type='PAY') and company_id=? and location_id=?";
	public static String GET_BANK_RECEIPT_BILLS_FOR_EDIT="select b.bill_reference,b.bill_reference_tr_type,isnull(convert(nvarchar,bn.due_date,105),'') as due_date,round(bn.debit_fcy,2) as original_amt,"
			+ " round(isnull(sum(b.debit_fcy)-sum(b.credit_fcy),0),2) as balance_amt,round(isnull(ftb.debit_fcy,0),2) as debit ,round(isnull(ftb.credit_fcy,0),2) as credit ,"
			+ " case when (round(isnull(ftb.debit_fcy,0),2)>0) then -round(isnull(ftb.debit_fcy,0),2) else round(isnull(ftb.credit_fcy,0),2) end pad_amt"
			+ " from fin_tr_bills b"
			+ " inner join fin_tr_bills bn on b.bill_reference=bn.bill_reference and b.bill_reference_tr_type=bn.bill_reference_tr_type and b.account_id=bn.account_id and b.company_id=bn.company_id and b.location_id=bn.location_id"
			+ " and (bN.adjustment_mode='NEW' or  bn.adjustment_mode='EXCESS' ) "
			+ " left join fin_tr_bills ftb on b.bill_reference=ftb.bill_reference and b.bill_reference_tr_type=ftb.bill_reference_tr_type and ftb.fin_tr_type='rec' and b.company_id=ftb.company_id and b.location_id=ftb.location_id"
			+ " where b.account_id=? and not(b.fin_tr_id=? and b.fin_tr_type='REC') and b.company_id=? and b.location_id=?"
			+ " group by b.bill_reference,b.bill_reference_tr_type,bn.due_date,bn.debit_fcy,ftb.debit_fcy,ftb.credit_fcy having round(sum(b.debit_fcy)-sum(b.credit_fcy),2)<>0  ";
	public static String GET_BANK_PAYMENT_BILLS_FOR_EDIT="select b.bill_reference,b.bill_reference_tr_type,isnull(convert(nvarchar,bn.due_date,105),'') as due_date,round(bn.credit_fcy,2) as original_amt,"
			+ " round(isnull(sum(b.credit_fcy)-sum(b.debit_fcy),0),2) as balance_amt,round(isnull(ftb.debit_fcy,0),2) as debit ,round(isnull(ftb.credit_fcy,0),2) as credit , "
			+ " case when(round(isnull(ftb.credit_fcy,0),2)>0) then -round(isnull(ftb.credit_fcy,0),2) else round(isnull(ftb.debit_fcy,0),2) end as paid_amt"
			+ " from fin_tr_bills b"
			+ " inner join fin_tr_bills bn on b.bill_reference=bn.bill_reference and b.bill_reference_tr_type=bn.bill_reference_tr_type and b.account_id=bn.account_id and b.company_id=bn.company_id and b.location_id=bn.location_id"
			+ " and (bN.adjustment_mode='NEW' or  bn.adjustment_mode='EXCESS' ) "
			+ " left join fin_tr_bills ftb on b.bill_reference=ftb.bill_reference and b.bill_reference_tr_type=ftb.bill_reference_tr_type and ftb.fin_tr_type='PAY' and b.company_id=ftb.company_id and b.location_id=ftb.location_id"
			+ " where b.account_id=? and not(b.fin_tr_id=? and b.fin_tr_type='PAY') and b.company_id=? and b.location_id=?"
			+ " group by b.bill_reference,b.bill_reference_tr_type,bn.due_date,bn.credit_fcy,ftb.debit_fcy,ftb.credit_fcy having round(sum(b.credit_fcy)-sum(b.debit_fcy),2)<>0";  

	public static String GET_BANK_RECEIPT_DEDUCTIONS_CNT_FOR_EDIT="select count(*) "
			+ " From fin_tr_details ftd"
			+ " inner join account acc on ftd.account_id=acc.account_id"
			+ " inner join account_group ag with(nolock) on acc.group_id=ag.group_id" 
			+ " inner join account_group_type agt with(nolock) on ag.group_type=agt.group_type"
			+ " where ftd.account_id not in(?,?) and ftd.fin_tr_id=? ";
	
	public static String GET_BANK_RECEIPT_DEDUCTIONS_FOR_EDIT="select ftd.fin_tr_id,ftd.fin_tr_det_id,ftd.account_id,ftd.towards_account_id,"
			+ " round(ftd.debit_fcy,2),round(ftd.credit_fcy,2),ftd.remarks,acc.account_name,ag.group_type,agt.category "
			+ " From fin_tr_details ftd"
			+ " inner join account acc on ftd.account_id=acc.account_id"
			+ " inner join account_group ag with(nolock) on acc.group_id=ag.group_id" 
			+ " inner join account_group_type agt with(nolock) on ag.group_type=agt.group_type"
			+ " where ftd.account_id not in(?,?) and ftd.fin_tr_id=? order by fin_tr_det_id ";
	
	public static String GET_BANK_PAYMENT_DEDUCTIONS_CNT_FOR_EDIT="  select count(*)"
			+ " From fin_tr_details ftd "
			+ " inner join account acc on ftd.account_id=acc.account_id"
			+ " inner join account_group ag with(nolock) on acc.group_id=ag.group_id" 
			+ " inner join account_group_type agt with(nolock) on ag.group_type=agt.group_type"
			+ " where ftd.account_id not in(?,?) and ftd.fin_tr_id=?";
	public static String GET_BANK_PAYMENT_DEDUCTIONS_FOR_EDIT="  select ftd.fin_tr_id,ftd.fin_tr_det_id,ftd.account_id,ftd.towards_account_id,"
			+ " round(ftd.debit_fcy,2),round(ftd.credit_fcy,2),ftd.remarks ,acc.account_name,ag.group_type,agt.category"
			+ " From fin_tr_details ftd "
			+ " inner join account acc on ftd.account_id=acc.account_id"
			+ " inner join account_group ag with(nolock) on acc.group_id=ag.group_id" 
			+ " inner join account_group_type agt with(nolock) on ag.group_type=agt.group_type"
			+ " where ftd.account_id not in(?,?) and ftd.fin_tr_id=? order by fin_tr_det_id ";
	
	public static String GET_COST_CENTER_COUNT_FOR_EDIT="select count(*) "
			+ " from fin_tr_cc ftcc"
			+ " inner join costcenter cc with(nolock) on ftcc.costcenter_id=cc.costcenter_id"
			+ " where ftcc.fin_tr_id=? and ftcc.fin_tr_det_id=? and ftcc.company_id=? and ftcc.location_id=? and ftcc.year_id=?";
	
	public static String GET_COST_CENTER_FOR_EDIT="select ftcc.costcenter_id,cc.costcenter_name,ftcc.debit,ftcc.credit,ftcc.auto_id "
							+ " from fin_tr_cc ftcc"
							+ " inner join costcenter cc with(nolock) on ftcc.costcenter_id=cc.costcenter_id"
							+ " where ftcc.fin_tr_id=? and ftcc.fin_tr_det_id=? and ftcc.company_id=? and ftcc.location_id=? and ftcc.year_id=? order by ftcc.fin_tr_det_id,ftcc.auto_id";
	
	public static String GET_INVOICE_COST_CENTER_COUNT_FOR_EDIT="select count(*) "
			+ " from sales_invoice_cc icc"
			+ " inner join costcenter cc with(nolock) on icc.costcenter_id=cc.costcenter_id"
			+ " where icc.invoice_id=? and icc.invoice_tag=? and icc.company_id=? and icc.location_id=? and icc.year_id=?";

	public static String GET_INVOICE_COST_CENTER_FOR_EDIT="select icc.costcenter_id,cc.costcenter_name,icc.amount,icc.auto_id"
			+ " from sales_invoice_cc icc"
			+ " inner join costcenter cc with(nolock) on icc.costcenter_id=cc.costcenter_id"
			+ " where icc.invoice_id=? and icc.invoice_tag=? and icc.company_id=? and icc.location_id=? and icc.year_id=? order by auto_id";
	
	public static String GET_PURCHASE_INVOICE_COST_CENTER_COUNT_FOR_EDIT="select count(*) "
			+ " from purchase_invoice_cc icc"
			+ " inner join costcenter cc with(nolock) on icc.costcenter_id=cc.costcenter_id"
			+ " where icc.invoice_id=? and icc.invoice_tag=? and icc.company_id=? and icc.location_id=? and icc.year_id=?";

	public static String GET_PURCHASE_INVOICE_COST_CENTER_FOR_EDIT="select icc.costcenter_id,cc.costcenter_name,icc.amount,icc.auto_id"
			+ " from purchase_invoice_cc icc"
			+ " inner join costcenter cc with(nolock) on icc.costcenter_id=cc.costcenter_id"
			+ " where icc.invoice_id=? and icc.invoice_tag=? and icc.company_id=? and icc.location_id=? and icc.year_id=? order by auto_id";
	
	public static String GET_BANK_RECEIPT_DEDUCTIONS_FOR_PRINT="select ftd.fin_tr_id,ftd.fin_tr_det_id,ftd.account_id,acc.account_name,round(ftd.debit_fcy,2) as debit,ftd.remarks "
			+ " From fin_tr_details ftd "
			+ " inner join account acc with(nolock) on ftd.account_id=acc.account_id"
			+ " where ftd.account_id not in(?,?) and ftd.fin_tr_id=? ";

	public static String GET_BANK_PAYMENT_DEDUCTIONS_FOR_PRINT="select ftd.fin_tr_id,ftd.fin_tr_det_id,ftd.account_id,acc.account_name,round(ftd.debit_fcy,2) as debit,ftd.remarks "
			+ " From fin_tr_details ftd "
			+ " inner join account acc with(nolock) on ftd.account_id=acc.account_id"
			+ " where ftd.account_id not in(?,?) and ftd.fin_tr_id=? ";

	public static String GET_BANK_RECEIPT_AMT_RECEIVED_AND_DED=" select sum(credit_fcy)-sum(debit_fcy),sum(debit_fcy) from fin_tr_details where fin_tr_id =? and account_id <>?";
	public static String GET_BANK_PAYMENT_AMT_PAID_AND_DED="  select sum(debit_fcy)-sum(credit_fcy),sum(credit_fcy) from fin_tr_details where fin_tr_id =? and account_id <>?";
	public static String GET_BANK_RECEIPT_EXCESS_CREDIT="select round(isnull(sum(debit_fcy),0),2) "
			+ " from fin_tr_bills "
			+ " where bill_reference=? and bill_reference_tr_type='rec' "
			+ " and account_id=? and company_id=? and location_id=? and adjustment_mode='AGAINST'";

	public static String GET_BANK_PAYMENT_EXCESS_CREDIT="  select round(isnull(sum(credit_fcy),0),2) "
			+ " from fin_tr_bills "
			+ " where bill_reference=? and bill_reference_tr_type='PAY' "
			+ " and account_id=? and company_id=? and location_id=? and adjustment_mode='AGAINST'";

	public static String GET_BANK_RECEIPT_RADIO_TYPE="select distinct prefix1,prefix2,prefix3,autono from set_autono_bank_receipt  where  company_id=? and location_id=? and year_id=? ";
	public static String GET_BANK_PAYMENT_RADIO_TYPE="select distinct prefix1,prefix2,prefix3,autono from set_autono_bank_receipt  where  company_id=? and location_id=? and year_id=?"; 
	public static String GET_BANK_RECEIPT_BILLS="select b.bill_reference,b.bill_reference_tr_type,isnull(convert(nvarchar,bn.due_date,105),'') as due_date,round(bn.debit_fcy,2) as original_amt,round(sum(b.debit_fcy)-sum(b.credit_fcy),2) as balance_amt"
			+ " from fin_tr_bills b"
			+ " inner join fin_tr_bills bn on b.bill_reference=bn.bill_reference and b.bill_reference_tr_type=bn.bill_reference_tr_type and b.account_id=bn.account_id and b.account_id=bn.account_id and b.company_id=bn.company_id and b.location_id=bn.location_id"
			+ " and (bn.adjustment_mode='NEW' or bn.adjustment_mode='EXCESS' )"
			+ " where b.account_id=? and b.company_id=? and b.location_id=?"
			+ " group by b.bill_reference,b.bill_reference_tr_type,bn.due_date,bn.debit_fcy having round(sum(b.debit_fcy)-sum(b.credit_fcy),2)<>0 ";

	public static String GET_BANK_PAYMENT_BILLS="select b.bill_reference,b.bill_reference_tr_type,isnull(convert(nvarchar,bn.due_date,105),'') as due_date,round(bn.credit_fcy,2) as original_amt,round(sum(b.credit_fcy)-sum(b.debit_fcy),2) as balance_amt"
			+ " from fin_tr_bills b"
			+ " inner join fin_tr_bills bn on b.bill_reference=bn.bill_reference and b.bill_reference_tr_type=bn.bill_reference_tr_type and b.account_id=bn.account_id and b.account_id=bn.account_id and b.company_id=bn.company_id and b.location_id=bn.location_id"
			+ " and (bn.adjustment_mode='NEW' or bn.adjustment_mode='EXCESS' )"
			+ " where b.account_id=? and b.company_id=? and b.location_id=?"
			+ " group by b.bill_reference,b.bill_reference_tr_type,bn.due_date,bn.credit_fcy having round(sum(b.credit_fcy)-sum(b.debit_fcy),2)<>0 ";

	public static String GET_ACCOUNT_CUSTOMER_EMAIL="select party_id,contact_email from party where account_id =? and party_tag='Customer'";

	//Journal Voucher 
	public static String GET_JOURNAL_VOUCHER_HEADER="select distinct ft.fin_tr_type,ft.fin_tr_id,ft.prefix,ft.fin_tr_no,convert(nvarchar,ft.fin_tr_date,105) as fin_tr_date,cur.currency_id,cur.currency_name,"
			+ " ft.exchange_rate,ft.cheq_no,convert(nvarchar,ft.cheq_date,105) as cheq_date,ft.remarks,ft.internal_memo"
			+ " from fin_tr ft"
			+ " inner join fin_tr_details ftd on ft.fin_tr_id=ftd.fin_tr_id "
			+ " inner join currency_master cur on ft.currency_id=cur.currency_id"
			+ " where ft.fin_tr_id=?";
	public static String GET_JOURNAL_VOUCHER_CNT_FOR_EDIT=" select count(*)"
			+ " from "
			+ " fin_tr ft "
			+ " inner join fin_tr_details ftd on ft.fin_tr_id=ftd.fin_tr_id "
			+ " inner join account acc on ftd.account_id=acc.account_id"
			+ " inner join account tacc on ftd.towards_account_id=tacc.account_id"
			+ " inner join account_group ag on acc.group_id=ag.group_id"
			+ " inner join account_group_type agt on ag.group_type=agt.group_type"
			+ " where ft.fin_tr_id=? ";
	public static String GET_JOURNAL_VOUCHER_DATA_FOR_EDIT="select ftd.fin_tr_det_id,ftd.account_id ,acc.account_name account_name,ftd.debit_fcy,ftd.credit_fcy ,ftd.towards_account_id,tacc.account_name towards_account_name,ftd.remarks,ag.group_type,agt.category  "
			+ " from "
			+ " fin_tr ft "
			+ " inner join fin_tr_details ftd on ft.fin_tr_id=ftd.fin_tr_id "
			+ " inner join account acc on ftd.account_id=acc.account_id"
			+ " inner join account tacc on ftd.towards_account_id=tacc.account_id"
			+ " inner join account_group ag on acc.group_id=ag.group_id"
			+ " inner join account_group_type agt on ag.group_type=agt.group_type"
			+ " where ft.fin_tr_id=? order by ftd.fin_tr_det_id";
	public static String GET_JOURNAL_VOUCHER_TOTAL_FOR_EDIT="select isnull(sum(ftd.debit_fcy),0),isnull(sum(ftd.credit_fcy),0)"
			+ " from "
			+ " fin_tr ft "
			+ " inner join fin_tr_details ftd on ft.fin_tr_id=ftd.fin_tr_id "
			+ " inner join account acc on ftd.account_id=acc.account_id"
			+ " inner join account tacc on ftd.towards_account_id=tacc.account_id"
			+ " where ft.fin_tr_id=?";
	public static String GET_AUTO_NO_TYPE=" select top 1 case when (autono=1) then "
			+ " case when(common_slno=1) "
			+ " then 'common' "
			+ " else 'seperate' "
			+ " end "
			+ " else 'manual' end from ?autno_table WHERE company_id=? and location_id=? and year_id=?";
	//	public static String GET_SALES_INVOICE_PREFIX_AND_LASTNO_FOR_EDIT_AUTO="select top 1 prefix,(last_no-1) from set_autono_sales_invoice where prefix+convert(nvarchar,(last_no-1))=(select invoice_no from sales_invoice where invoice_id=?) and company_id=? and location_id=? and year_id=?";

	//	public static String GET_SALES_INVOICE_PREFIX_AND_LASTNO_FOR_EDIT_MANUAL="select distinct p.prefix,REPLACE(s.invoice_no, p.prefix, '')"
	//			+ " from("
	//			+ " select invoice_no from sales_invoice si where invoice_id=? and si.company_id=? and si.location_id=? and si.year_id=?"
	//			+ " ) s"
	//			+ " join set_autono_sales_invoice p on  s.invoice_no like p.prefix+'%' where p.company_id=? and p.location_id=? and p.year_id=?";

	//	public static String GET_SALES_INVOICE_PREFIX_AND_LASTNO_FOR_NEW="select top 1 prefix,last_no "
	//			+ " from set_autono_sales_invoice "
	//			+ " where prefix+convert(nvarchar,(last_no-1))=(select top 1 invoice_no from sales_invoice where invoice_id=(select max(invoice_id) from sales_invoice))"
	//			+ " and company_id=? and location_id=? and year_id=?";

	public static String GET_SALES_INVOICE_PREFIX_AND_LASTNO_FOR_NEW="select isnull(prefix,'') as prefix,(invoice_no+1) as invoice_no "
			+ " from sales_invoice "
			+ " where invoice_id=(select max(invoice_id) from sales_invoice where company_id=? and location_id=? and year_id=?)"
			+ " and company_id=? and location_id=? and year_id=?";
	
	public static String GET_PURCHASE_INVOICE_PREFIX_AND_LASTNO_FOR_NEW="select isnull(prefix,'') as prefix,(invoice_no+1) as invoice_no "
			+ " from purchase_invoice "
			+ " where invoice_id=(select max(invoice_id) from purchase_invoice where company_id=? and location_id=? and year_id=?)"
			+ " and company_id=? and location_id=? and year_id=?";

	public static String GET_SALES_QUOTEATION_PREFIX_AND_LASTNO_FOR_NEW="select isnull(prefix,'') as prefix,(quote_no+1) as quote_no "
			+ " from sales_quotation"
			+ " where quote_id=(select max(quote_id) from sales_quotation where company_id=? and location_id=? and year_id=?)"
			+ " and company_id=? and location_id=? and year_id=?";

	public static String GET_SALES_ORDER_PREFIX_AND_LASTNO_FOR_NEW="select isnull(prefix,'') as prefix,(so_no+1) as so_no "
			+ " from sales_order "
			+ " where so_id=(select max(so_id) from sales_order where company_id=? and location_id=? and year_id=?)"
			+ " and company_id=? and location_id=? and year_id=?";

	public static String GET_SALES_ENQUIRY_PREFIX_AND_LASTNO_FOR_NEW="select isnull(prefix,'') as prefix,(enq_no+1) as enq_no "
			+ " from sales_enquiry "
			+ " where enq_id=(select max(enq_id) from sales_enquiry where company_id=? and location_id=? and year_id=?)"
			+ " and company_id=? and location_id=? and year_id=?";

	public static String GET_PURCHASE_ORDER_PREFIX_AND_LASTNO_FOR_NEW="select isnull(prefix,'') as prefix,(po_no+1) as po_no "
			+ " from po "
			+ " where po_id=(select max(po_id) from po where company_id=? and location_id=? and year_id=?)"
			+ " and company_id=? and location_id=? and year_id=?";

	public static String GET_BANK_RECEIPT_PREFIX_AND_LASTNO_FOR_NEW="select isnull(prefix,'') as prefix,(fin_tr_no+1) as fin_tr_no "
			+ " from fin_tr "
			+ " where fin_tr_type='REC' and fin_tr_id=(select max(fin_tr_id) from fin_tr where fin_tr_type='REC' and company_id=? and location_id=? and year_id=?)"
			+ " and company_id=? and location_id=? and year_id=?";
	
	public static String GET_JOURNAL_VOUCHER_PREFIX_AND_LASTNO_FOR_NEW="select isnull(prefix,'') as prefix,(fin_tr_no+1) as fin_tr_no "
			+ " from fin_tr "
			+ " where fin_tr_type='JV' and fin_tr_id=(select max(fin_tr_id) from fin_tr where fin_tr_type='JV' and company_id=? and location_id=? and year_id=?)"
			+ " and company_id=? and location_id=? and year_id=?";
	
	public static String GET_BANK_PAYMENT_PREFIX_AND_LASTNO_FOR_NEW="select isnull(prefix,'') as prefix,(fin_tr_no+1) as fin_tr_no "
			+ " from fin_tr "
			+ " where fin_tr_type='PAY' and fin_tr_id=(select max(fin_tr_id) from fin_tr where fin_tr_type='PAY' and company_id=? and location_id=? and year_id=?)"
			+ " and company_id=? and location_id=? and year_id=?";

	public static String GET_PREFIX_COUNT="SELECT DISTINCT count(*) FROM ?autno_table  WHERE company_id=? AND year_id=?  AND location_id=?";
	
	

	//	public static String GET_SI_TRANSACTION_CREATED_PREFIX="select distinct p.prefix"
	//						+ " from("
	//						+ " select invoice_no from sales_invoice si where si.company_id=? and si.location_id=? and si.year_id=?"
	//						+ " ) s"
	//						+ " join set_autono_sales_invoice p on  s.invoice_no like p.prefix+'%' where p.company_id=? and p.location_id=? and p.year_id=? ";

	public static String GET_SI_TRANSACTION_CREATED_PREFIX="select distinct prefix from ?header_table where company_id=? and location_id=? and year_id=? ";

	public static String GET_OPENINGBAL_LIST_WITH_BILLS="select distinct ft.fin_tr_id,ftd.Debit_FCY,ftd.Credit_FCY,cur.currency_id,cur.currency_name,ft.exchange_rate,ftd.remarks,ftd.Fin_Tr_det_id "+
			" from fin_tr ft "+
			" inner join fin_tr_details ftd on ft.fin_tr_id=ftd.fin_tr_id "+
			" inner join currency_master cur on ft.currency_id=cur.currency_id "+
			" inner join fin_tr_bills ftb on  ft.fin_tr_id=ftb.fin_tr_id and ftd.Fin_Tr_det_id=ftb.Fin_Tr_det_id "+
			" where ft.fin_tr_type='OPBAL' and ftd.Account_id=? and ftd.Towards_Account_id<>?";

	public static String GET_OPENINGBAL_LIST_WITHOUT_BILLS="select distinct ft.fin_tr_id,ftd.Debit_FCY,ftd.Credit_FCY,cur.currency_id,cur.currency_name,ft.exchange_rate,ftd.remarks,ftd.Fin_Tr_det_id "+
			" from fin_tr ft "+
			" inner join fin_tr_details ftd on ft.fin_tr_id=ftd.fin_tr_id "+
			" inner join currency_master cur on ft.currency_id=cur.currency_id "+
			/*" inner join fin_tr_bills ftb on  ft.fin_tr_id=ftb.fin_tr_id and ftd.Fin_Tr_det_id=ftb.Fin_Tr_det_id "+*/
			" where ft.fin_tr_type='OPBAL' and ftd.Account_id=? and ftd.Towards_Account_id<>?";


	public static String GET_BILL_DATA_FOR_EDIT="select ftb.Fin_Tr_id,ftb.Fin_Tr_det_id,ftb.Bill_Reference,convert(nvarchar,ftb.Fin_Tr_Date,105),convert(nvarchar,ftb.Due_Date,105),ftb.Debit_fcy,ftb.Credit_Fcy "+
			"	FROM fin_tr_bills ftb "+ 
			"	INNER JOIN fin_tr ft on ft.fin_tr_id=ftb.fin_tr_id "+ 
			"	INNER JOIN fin_tr_details ftd on  ft.fin_tr_id=ftd.fin_tr_id and ftd.Fin_Tr_det_id=ftb.Fin_Tr_det_id where ftb.Fin_Tr_id=? and ftb.account_id=? ";

	public static String GET_BILL_DATA_FOR_EDIT_CNT="select distinct count(*) "+
			"	FROM fin_tr_bills ftb "+ 
			"	INNER JOIN fin_tr ft on ft.fin_tr_id=ftb.fin_tr_id "+ 
			"	INNER JOIN fin_tr_details ftd on  ft.fin_tr_id=ftd.fin_tr_id and ftd.Fin_Tr_det_id=ftb.Fin_Tr_det_id where ftb.Fin_Tr_id=? and ftb.account_id=? ";

	public static String GET_BILL_DATA_FOR_TOTAL="select isnull(sum(ftb.debit_fcy),0),isnull(sum(ftb.credit_fcy),0)"+
			"	FROM fin_tr_bills ftb "+ 
			"	INNER JOIN fin_tr ft on ft.fin_tr_id=ftb.fin_tr_id "+ 
			"	INNER JOIN fin_tr_details ftd on  ft.fin_tr_id=ftd.fin_tr_id and ftd.Fin_Tr_det_id=ftb.Fin_Tr_det_id where ftb.Fin_Tr_id=? and ftb.account_id=? ";

	public static String GET_OPENING_BAL_TOTAL="select isnull(sum(ftd.debit_fcy),0),isnull(sum(ftd.credit_fcy),0)"+
			"	FROM fin_tr_details ftd "+ 
			"	INNER JOIN fin_tr ft on ft.fin_tr_id=ftd.fin_tr_id "+ 
			"	WHERE  ftd.Fin_Tr_type='OPBAL' and ftd.Towards_Account_id=? ";


	public static String GET_STARTRING_NO=" select top 1 starting_no,last_no from ?autno_table WHERE company_id=? and location_id=? and year_id=?";
	public static String DELETE_AUTONO_ROW="delete from ?autno_table where prefix=? and company_id=? and location_id=? and year_id=?";
	public static String GET_LAST_NO_FOR_PREFIX=" select top 1 last_no,autono from ?autno_table where prefix=? and company_id=? and location_id=? and year_id=?";

	public static String GET_SALES_ACCOUNT_CONFIG_OPTION="select config_option from set_location_config where location_id=? and config_code='SalesAccName'";
	
	public static String GET_PURCHASE_ACCOUNT_CONFIG_OPTION="select config_option from set_location_config where location_id=? and config_code='PurAccName'";
	
/*	public static String GET_SALES_REGISTER=" select ft.Fin_Tr_id,acc.account_id, acc.account_name,ag.group_type,ft.Fin_Tr_No,convert(nvarchar,ft.Fin_Tr_Date,105) as Fin_Tr_Date,ft.fin_tr_type,isnull(ft.Reference,'') Reference,ftd.credit_fcy as basic,ftd.credit_fcy as other,ftd.debit_fcy from "+ 
										"	fin_tr ft inner join fin_tr_details ftd on ft.Fin_Tr_id=ftd.Fin_Tr_id "+
										"	inner join account acc on acc.account_id=ftd.account_id "+
										"	inner join account_group ag on ag.group_id=acc.group_id "+
										"	 where group_type  in ('Sales','Income','Sundry Debtors','Sundry Creditors') and ft.Fin_Tr_id=395 order by ftd.fin_tr_det_id  ";*/
	
	public static String GET_SALES_REGISTER=" select t1.Fin_Tr_id,t1.customer_account,t1.sales_account,t1.Fin_Tr_No,t1.Fin_Tr_Date,t1.Fin_Tr_type,t1.Reference,t1.basic,t1.total, ?1   "
			+ " from temp_sales_register t1 inner join tax_account_table t2 on t1.Fin_Tr_id=t2.Fin_Tr_id  ";
	
	
	public static String GET_SALES_REGISTER_TAX_ACCOUNT=" select distinct acc.account_name from fin_tr_details ftd "+
														"	inner join fin_tr ft on ft.Fin_Tr_id=ftd.Fin_Tr_id "+
														"	inner join account acc on acc.account_id=ftd.account_id "+
														"	inner join account_group ag on ag.group_id=acc.group_id "+
														"	where group_type not like '%Sale%' and group_type not like '%Income%' and group_type not like '%Sundry Debtor%' and group_type not like '%Sundry Creditor%'";
	
	public static String  GET_COMPANY_CONFIGURATION_INFO="select company_id,company_name,street,location,city,state,zip,country,phone_no,fax_no,email_id,currency_id,pan_no,tax_reg_no1 from company  where client_id=? ";
	
	public static String GET_CONTRA_HEADER="select distinct ft.fin_tr_type,ft.fin_tr_id,ft.prefix,ft.fin_tr_no,convert(nvarchar,ft.fin_tr_date,105) as fin_tr_date,cur.currency_id,cur.currency_name,"
			+ " ft.exchange_rate,ft.cheq_no,convert(nvarchar,ft.cheq_date,105) as cheq_date,ft.remarks,ft.internal_memo,ftd.debit_fcy,ft.debit_to_account_id,dacc.account_name,ft.credit_to_account_id,cacc.account_name"
			+ " from fin_tr ft"
			+ " inner join fin_tr_details ftd on ft.fin_tr_id=ftd.fin_tr_id "
			+ " inner join currency_master cur on ft.currency_id=cur.currency_id"
			+ " inner join account dacc on ft.debit_to_account_id=dacc.account_id"
			+ " inner join account cacc on ft.credit_to_account_id=cacc.account_id"
			+ " where ft.fin_tr_id=? and ft.debit_to_account_id=ftd.account_id";
	
	public static String DELETE_SALES_INVOICE_MORE="delete from sales_invoice_more where company_id=? and location_id=? and year_id=? and invoice_id=?";
	public static String DELETE_PURCHASE_INVOICE_MORE="delete from purchase_invoice_more where company_id=? and location_id=? and year_id=? and invoice_id=?";
	
	public static String CHECK_SE_AUTO_NUMBER_EXISTENCE="select count(*) from sales_enquiry where enq_no=? and prefix=? and company_id=? and location_id=? and year_id=?";
	public static String CHECK_SQ_AUTO_NUMBER_EXISTENCE="select count(*) from sales_quotation where quote_no=? and prefix=? and company_id=? and location_id=? and year_id=?";
	public static String CHECK_SO_AUTO_NUMBER_EXISTENCE="select count(*) from sales_order where so_no=? and prefix=? and company_id=? and location_id=? and year_id=?";
	public static String CHECK_SI_AUTO_NUMBER_EXISTENCE="select count(*) from sales_invoice where invoice_no=? and prefix=? and company_id=? and location_id=? and year_id=?";
	public static String CHECK_SR_AUTO_NUMBER_EXISTENCE="select count(*) from sales_invoice where invoice_no=? and prefix=? and company_id=? and location_id=? and year_id=?";
	
	
	public static String CHECK_PI_AUTO_NUMBER_EXISTENCE="select count(*) from purchase_invoice where invoice_no=? and prefix=? and company_id=? and location_id=? and year_id=?";
	public static String CHECK_PR_AUTO_NUMBER_EXISTENCE="select count(*) from purchase_invoice where invoice_no=? and prefix=? and company_id=? and location_id=? and year_id=?";
	
	public static String CHECK_FIN_AUTO_NUMBER_EXISTENCE="select count(*) from fin_tr where fin_tr_no=? and prefix=? and company_id=? and location_id=? and year_id=?";

	public static final String SELECT_LOCACTION_CONFIG = "select heading,config_name,config_code,config_option,isnull(remark,'') as remark,auto_id from set_location_config where location_id=? and heading=? order by heading";
	
	
	/* Specification SEtting */
	public static String SELECT_SPECIFICATION_SETTING = "SELECT ss.grouptype_id,gt.group_type,ss.spec1,ss.spec1_len,ss.spec2,ss.spec2_len,ss.spec3,ss.spec3_len,ss.spec4,ss.spec4_len, "
		+ " ss.spec5,ss.spec5_len,ss.spec6,ss.spec6_len,ss.spec7,ss.spec7_len FROM spec_setting ss,group_type gt WHERE ss.grouptype_id=gt.group_type_id";
	
	public static String SPECIFICATION_SETTING_COUNT = "SELECT count(*) FROM spec_setting ";
	
	public static String INSERT_SPECIFICATION_SETTING = "INSERT INTO spec_setting(grouptype_id,spec1,spec1_len,spec2,spec2_len,spec3,spec3_len, "
		+ " spec4,spec4_len,spec5,spec5_len,spec6,spec6_len,spec7,spec7_len,is_active,Companyid,location_id,entered_by,entered_date,ip_address,host_name) "
		+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,GETDATE(),?,?)";

	public static String DELETE_SPECIFICATION_SETTING = "delete from spec_setting where grouptype_id=? and spec1=? and spec1_len=? and spec2=? and spec2_len=? and spec3=? and spec3_len=? and spec4=? and spec4_len=? and spec5=? and spec5_len=? and spec6=? and spec6_len=? and  spec7=? and spec7_len=?";

	public static String UPDATE_SPECMASTER_SETTING = " update spec_setting set spec1=?,spec1_len=?,spec2=?,spec2_len=?,spec3=?,spec3_len=?,spec4=?,spec4_len=?,spec5=?,spec5_len=?,spec6=?,spec6_len=?,spec7=?,spec7_len=?,updated_by=?,updated_date=getdate() where  grouptype_id=? and spec1=? and spec1_len=? and spec2=? and spec2_len=? and spec3=? and spec3_len=? and spec4=? and spec4_len=? and spec5=? and spec5_len=? and spec6=? and spec6_len=? and spec7=? and spec7_len=?";

	public static String SPECSETTING_INFO = " select ss.grouptype_id,gt.group_type,ss.spec1,ss.spec1_len,ss.spec2,ss.spec2_len,ss.spec3,ss.spec3_len,ss.spec4,ss.spec4_len,ss.spec5,ss.spec5_len,ss.spec6,ss.spec6_len,ss.spec7,ss.spec7_len from group_type as gt,spec_setting as ss where gt.group_type_id=ss.grouptype_id and ss.grouptype_id=? and ss.spec1=? and ss.spec1_len=? and ss.spec2=? and ss.spec2_len=? and ss.spec3=? and ss.spec3_len=? and ss.spec4=? and ss.spec4_len=? and ss.spec5=? and ss.spec5_len=? and ss.spec6=? and ss.spec6_len=? and  ss.spec7=? and ss.spec7_len=?";
	/* Specification Master */
	public static String SELECT_SPECIFICATION = "SELECT sm.grouptype_id,gt.group_type,sm.spec,sm.spec_value,sm.spec_code,sm.status,sm.Companyid, "
		+ " sm.location_id FROM spec_master sm,group_type gt WHERE sm.grouptype_id=gt.grouptype_id";
	public static String SPECIFICATION_COUNT = "SELECT count(*) FROM spec_master ";
	public static String INSERT_SPECIFICATION = "INSERT INTO spec_master(grouptype_id,spec,spec_value,spec_code,status,Companyid,location_id,entered_by,entered_on) VALUES(?,?,?,?,0,?,?,?,GETDATE())";
	public static String DELETE_SPECIFICATION = "delete from spec_master where grouptype_id=? and spec=? and spec_value=? and spec_code=?";

	public static String GET_SPECMASTER_INFO = "select sm.grouptype_id ,gt.group_type,sm.spec,sm.spec_value,sm.spec_code from group_type as gt ,spec_master as sm where gt.grouptype_id=sm.grouptype_id and sm.grouptype_id=? and sm.spec=? and sm.spec_value=? and sm.spec_code=?";

	public static String UPDATE_SPECMASTER = "update spec_master set spec_value=?,spec_code=? where grouptype_id=? AND spec=?";

	public static String IS_SPECIFICATION_EXIST = "  select count(*) from spec_master where spec_value=? and spec_code=?";
	
	
	//grouptype formPage
		public static String IS_GROUPTYPE_EXIST = " select count(*) from group_type where (group_type=? or sl_no=? ) ";
		public static String INSERT_INTO_GROUPTYPE = "insert into group_type(group_type,parent_group_type_id,sl_no)values(?,?,?)";

	//warehouse Master
		public static String WAREHOUSE_MASTER_PAGE_COUNT="select count(*) from store_master";
		public static String GET_WAREHOUSE_MASTER_INFO=" select store_id,store_code,store_name,is_active from store_master where store_id=? ";
		public static String DEACTIVE_WAREHOUSE="update store_master set is_active=0 where store_id=?";
		public static String WAREHOUSE_CODE_NAME_EXIST="select count(*) from store_master where store_name=? ";
		
		// ItemGroups Dynamic
		public static String SELECT_ITEMGROUPS_DYNAMIC_BY_ID = " Select mdf.* "+
				" FROM  "+
				" groups m (NOLOCK)  "+
				" Left Join groups_dynamic mdf on m.group_id = mdf.group_id "+ 
				" Where m.group_id=? "; 
		
	//Approved Price List
		public static String SELECT_SIZE_FOR_SIZE_RANGE_ID="select sm.size_id,sm.size_name "
				+ " from Size_Master sm"
				+ " inner join Size_Range_det sr on sm.Size_Id=sr.Size_Id"
				+ " where sr.Size_Range_Id=?";
		
		public static String GET_APPROVEDPRICE_DETAIL_LIST="select distinct pr.purchase_price_id,pr.Supplier_Id,p.party_name,pr.item_Id,i.item_Name,pr.Variant_Id,c.Variant_Code,pr.Rate,pr.UOM,pr.Currency_Name,pr.Lead_Time_Days,"
				+ " pr.Supplier_Desc,pr.MOQ,pr.Size_Range_Id,sr.Size_Range"
				+ " from Purchase_Price pr"
				+ " inner join party p with(nolock) on pr.supplier_id=p.party_id"
				+ " inner join item i with(nolock) on pr.item_Id=i.item_Id"
				+ " left join variant_master c with(nolock) on pr.Variant_Id=c.Variant_Id"
				+ " left join Size_Range sr with(nolock) on pr.Size_Range_Id=sr.Size_Range_Id"
				+ " where pr.supplier_id=? ";
		
		public static String SELECT_APPROVED_PRICELIST_DETAIL_DYNAMIC = " Select aplddf.* "
				+ " FROM  "
				+ " Purchase_Price pr (NOLOCK) "
				+ " Left Join Purchase_Price_dynamic aplddf on pr.purchase_price_id = aplddf.purchase_price_id "
				+ " Where pr.supplier_id=? ";
		
		public static String GET_APPROVED_PRICE_LIST="select pr.purchase_price_id,pr.Supplier_Id,p.party_name,pr.item_Id,i.item_Name,pr.Variant_Id,c.Variant_Code,pr.Rate,pr.UOM,pr.Currency_Name,pr.Lead_Time_Days,"
				+ " pr.Supplier_Desc,pr.MOQ,pr.Size_Range_Id,(select distinct Size_Range from  Size_Range where Size_Range_Id=pr.Size_Range_Id) as Size_Range"
				+ " from Purchase_Price pr"
				+ " inner join party p with(nolock) on pr.supplier_id=p.party_id"
				+ " inner join item i with(nolock) on pr.item_Id=i.item_Id"
				+ " left join variant_master c with(nolock) on pr.Variant_Id=c.Variant_Id";
				/*+ " inner join Size_Range sr with(nolock) on pr.Size_Range_Id=sr.Size_Range_Id ";*/
			
		public static String GET_APPROVED_PIRCE_LIST_COUNT="select count(*) "
				+ " from Purchase_Price pr"
				+ " inner join party p with(nolock) on pr.supplier_id=p.party_id"
				+ " inner join item i with(nolock) on pr.item_Id=i.item_Id"
				+ " left join variant_master c with(nolock) on pr.Variant_Id=c.Variant_Id";
//				+ " inner join Size_Range sr with(nolock) on pr.Size_Range_Id=sr.Size_Range_Id ";
		
		public static String GET_APPROVEDPRICE_HEAD="select distinct pr.Supplier_Id,p.party_name"
				+ " from Purchase_Price pr"
				+ " inner join party p with(nolock) on pr.supplier_id=p.party_id"
				+ " where pr.supplier_id=? ";
		
		public static String CHECK_APPROVED_PRICE_EXISTENCE="select count(*) from purchase_price where Supplier_id=? ";
		public static String CHECK_APPROVED_PRICE_MAT_EXISTENCE="select count(*) from purchase_price where Supplier_id=? and mat_id=? ";
		
		
		//BOM
		
		
/*		public static String GET_PRODUCTION_BOM = " select b.bom_no,b.bom_id,b.product_id,prod.Mat_Name product,isnull(b.version_no,0) version_no,b.customer_id,p.party_name customer,pb.bom_no parent_bom_no,b.remarks,convert(nvarchar,b.bom_date,105) bom_date,b.Status, "+ 
										"	bd.bom_detail_id,bd.Mat_Id,m.Mat_Name,bd.variant_id,v.Variant_Name,bd.UOM,bd.operation_id,op.operation ,bd.required_qty,bd.wastage_per,bd.costing_per,bd.purchase_per,bd.issue_per, "+ 
										"	bd.supplier_id,supp.party_name supplier,bd.usage_area,mc.Mat_Name as component_name,isnull(b.lock_status,0) lock_status,m.group_id,g.group_name  from bom b "+ 
										"	inner join bom_details bd on bd.bom_id = b.bom_id "+
										"	left join party p on p.party_id = b.customer_id "+
										"	left join party supp on supp.party_id = bd.supplier_id "+
										"	inner join item m on m.Mat_Id = bd.Mat_Id "+
										"	inner join groups g on g.group_Id = m.group_Id "+
										"	left join item prod on b.product_id = prod.Mat_Id "+
										"	left join Variant_Master v on v.variant_id = bd.variant_id "+
										"	left join operation op on op.operation_id = bd.operation_id "+
										"	left join item mc on mc.Mat_Id=bd.component_id "+
										"	left join bom pb on pb.bom_id =b.parent_bom_id "+
										"	Left Join bom_dynamic sedf on b.bom_id = sedf.bom_id "+ 
										"	Left Join bom_details_dynamic seddf on bd.bom_detail_id = seddf.bom_detail_id "; */
		
		
		public static String GET_PRODUCTION_BOM = " select b.bom_no,b.bom_id,b.product_id,prod.item_Name product,isnull(b.version_no,0) version_no,b.customer_id,p.party_name customer,pb.bom_no parent_bom_no,b.remarks,convert(nvarchar,b.bom_date,105) bom_date,b.Status, "+  
										"	sum(isnull(bd.required_qty,0)) required_qty,isnull(b.lock_status,0) lock_status,count(b.bom_id) as count "+
										"	from bom b  "+
										"	inner join bom_details bd on bd.bom_id = b.bom_id "+
										"	left join party p on p.party_id = b.customer_id "+
										"	left join party supp on supp.party_id = bd.supplier_id "+ 
										"	inner join item m on m.item_id = bd.item_id "+
										"	inner join groups g on g.group_Id = m.group_Id "+
										"	left join item prod on b.product_id = prod.item_id "+
										"	left join Variant_Master v on v.variant_id = bd.variant_id "+
										"	left join operation op on op.operation_id = bd.operation_id "+ 
										"	left join item mc on mc.item_id=bd.component_id "+
										"	left join bom pb on pb.bom_id =b.parent_bom_id "+
										"	Left Join bom_dynamic sedf on b.bom_id = sedf.bom_id  "+
										"	Left Join bom_details_dynamic seddf on bd.bom_detail_id = seddf.bom_detail_id ";
		
		
		
		
		public static String GET_PARENT_BOM_NO_LIST = " select b.bom_id,b.bom_no,prod.Mat_Name product,p.party_name customer,m.Mat_Name,v.Variant_Name, "+
						"			bd.UOM,op.operation ,supp.party_name supplier,mc.Mat_Name as component_name,round(ISNULL(bd.required_qty,0),2) required_qty,b.customer_id "+
						"			from bom b  "+
						"			inner join bom_details bd on bd.bom_id = b.bom_id  "+
						"			left join party p on p.party_id = b.customer_id "+
						"			left join party supp on supp.party_id = bd.supplier_id "+ 
						"			inner join item m on m.Mat_Id = bd.Mat_Id  "+
						"			left join item prod on b.product_id = prod.Mat_Id  "+
						"			left join Variant_Master v on v.variant_id = bd.variant_id  "+
						"			left join operation op on op.operation_id = bd.operation_id "+ 
						"			left join item mc on mc.Mat_Id=bd.component_id  "; 
		
	/*	
		public static String SELECT_BOM_DYNAMIC_BY_ID = " Select b.* "+
				" FROM  "+
				" bom b (NOLOCK)  "+
				" Left Join bom_dynamic bd on b.bom_id = bd.bom_id "+ 
				" Where b.bom_id=? ";*/
		public static String SELECT_BOM_DETAIL_DYNAMIC_BY_ID = " Select sdf.*, sedf.* "
				+ " FROM  "
				+ " bom se (NOLOCK) "
				+ " Join bom_details sed on se.bom_id = sed.bom_id "
				+ " Left Join bom_dynamic sdf on se.bom_id = sdf.bom_id "
				+ " Left Join bom_details_dynamic sedf on sed.bom_detail_id = sedf.bom_detail_id "
				+ " Where se.bom_id=? ";
		

		  
		
		public static String BOM_PAGE_COUNT = " select distinct count(*) from bom b  "+
									"	inner join bom_details bd on bd.bom_id = b.bom_id "+
									"	left join party p on p.party_id = b.customer_id "+
									"	left join party supp on supp.party_id = bd.supplier_id "+
									"	inner join item m on m.item_id = bd.item_id "+
									"	left join item prod on b.product_id = prod.item_id "+
									"	left join Variant_Master v on v.variant_id = bd.variant_id "+
									"	left join operation op on op.operation_id = bd.operation_id "+
									"	left join bom pb on pb.parent_bom_id =b.bom_id  "+
									"	Left Join bom_dynamic sedf on b.bom_id = sedf.bom_id "+ 
									"	Left Join bom_details_dynamic seddf on bd.bom_detail_id = seddf.bom_detail_id  ";  

		public static String GET_ALT_MAT_COUNT="select  count(*) "+
				"	FROM bom_alt_details bd where bom_detail_id=? "; 
		
		
		public static String GET_ALT_MAT_DATA=" select bd.bom_detail_id,bd.item_id,m.item_name,bd.alt_per "+
				                            "	FROM bom_alt_details bd inner join item m on m.item_id=bd.item_id where bom_detail_id=? "; 
		
		
		public static String GET_BOM_COM_COUNT="select  count(*) "+
				"	FROM bom_comp_details bd where bom_detail_id=? "; 
		
		
		public static String GET_BOM_COMP_DATA=" select bom_detail_id,component_name,comp_length,comp_width,No_of_parts,Reqd_qty,Meas_uom,bom_comp_id from bom_comp_details where bom_detail_id=? "; 
		
		
		
		public static String GET_BOM_HEADER=" SELECT distinct b.bom_id,b.bom_no,convert(nvarchar,b.bom_date,105) as bomDate,b.product_id,m.item_name,b.customer_id,c.party_name,b.parent_bom_id, "+
								"			pb.bom_no,b.remarks  "+
								"			FROM bom b left join item m on m.item_id=b.product_id "+
								"			left join party c on c.party_id=b.customer_id "+
								"	        left join bom pb on pb.bom_id =b.parent_bom_id "+
								"			where b.bom_id=? ";
		
		
		public static String GET_BOM_HEADER_IN_PRINT="SELECT  b.bom_id,b.bom_no,b.customer_id,c.party_name,b.remarks  "+
						"			FROM bom b inner join item m on m.item_id=b.product_id "+
						"			inner join party c on c.party_id=b.customer_id "+
						"			where b.bom_id=?  ";
		
		
		public static String SELECT_BOM_ATTACHMENT=" SELECT bom_id,File_type,isnull(URL,''),reverse(substring(reverse(URL), 1,charindex('/', reverse(URL))-1)) from bom_URL where bom_id=? ";
		
		public static String SELECT_BOM_TOP_ATTACHMENT=" SELECT top 1 bom_id,File_type,isnull(URL,''),reverse(substring(reverse(URL), 1,charindex('/', reverse(URL))-1)) from bom_URL where bom_id=? ";
		
		
		
		public static String SELECT_BOM_HEADER_DYNAMIC = " Select sedf.* "
				+ " FROM  "
				+ " bom se (NOLOCK) "
				+ " Left Join bom_dynamic sedf on se.bom_id = sedf.bom_id "
				+ " Where se.bom_id=? ";
		
		
		public static String GET_BOM_DETAIL_LIST=" SELECT  b.bom_id,b.bom_detail_id,b.item_id,m.item_name,b.variant_id,v.Variant_Name,b.UOM,u.uom_id,b.operation_id,o.operation, "+
						"	b.required_qty,b.wastage_per,b.costing_per,b.purchase_per,b.issue_per,b.supplier_id,p.party_name,b.usage_area ,b.component_id,mc.item_name,"
						+ " b.size_schedule_id,sze.size_sched_ref  ,isnull(m.Standard_Cost,0) Standard_Cost, (isnull(b.required_qty,0)*isnull(m.Standard_Cost,0)) val ,m.group_id,g.group_name  "+
						"	FROM bom_details b inner join item m on m.item_id=b.item_id "+
						"	inner join groups g on g.group_id=m.group_id "+
						"	left join Variant_Master v on v.Variant_Id=b.Variant_Id "+
						"	inner join Uom_Master u on u.UOM=b.UOM "+
						"	left join party p on p.party_id=b.supplier_id "+
						"	left join operation o on b.operation_id=o.operation_id "+
						"	left join item mc on mc.item_id=b.component_id "+
						"	left join size_schedule sze on sze.size_schedule_id=b.size_schedule_id "+
						"	where b.bom_id=?  order by b.bom_detail_id  ";
		
		
		

		
		public static String GET_BOM_COMPONENT_LIST=" select distinct sc.component_name,it.item_name from Set_Comp_GroupLink sc "+
									"		inner join item it on it.group_id=sc.Group_id "+
									"		inner join bom_details bd on bd.item_id=it.item_id "+
									"		where bd.bom_id=?  ";
		
		public static String GET_BOM_DETAIL_PRINT_LIST=" SELECT  isnull(bddyn.heading,'') heading,g.group_name,it.item_Name,p.party_name,round(isnull(bd.required_qty,0),3),bd.UOM ,bddyn.part "+
					"			FROM bom b inner join bom_details bd on b.bom_id=bd.bom_id "+
					"			inner join item it on it.item_id=bd.item_id "+
					"			inner join groups g on g.group_Id=it.group_Id "+
					"			left join party p on p.party_id=bd.supplier_id "+
					"			inner join Uom_Master u on u.UOM=bd.UOM "+
					"			Left Join bom_dynamic sedf on b.bom_id = sedf.bom_id "+
					"			Left Join Bom_Details_dynamic bddyn on bd.bom_detail_id = bddyn.bom_detail_id "+
					"			where b.bom_id=? "+
					"			group by isnull(bddyn.heading,''),g.group_name,it.item_Name,p.party_name,bd.required_qty,bd.UOM ,bddyn.part  ";
		

		public static String SELECT_BOM_DETAIL_DYNAMIC = " Select seddf.* "
				+ " FROM  "
				+ " bom se (NOLOCK) "
				+ " Left Join bom_details_dynamic seddf on se.bom_id = seddf.bom_id "
				+ " Where se.bom_id=? ";
		

		
		
		public static String INSERT_BOM_ATTACHMENT=" INSERT INTO bom_URL(bom_id,File_type,URL) VALUES(?,?,?) ";
		
		public static String DELETE_BOM_ATTACH =" DELETE FROM bom_URL WHERE bom_id=? and url=? "; 

		public static String INSERT_INTO_BOM_COMPONENTS=" INSERT INTO bom_comp_details(bom_detail_id,component_name,comp_length,comp_width,No_of_parts,Reqd_qty,Meas_uom) "+
				                                        " VALUES(?,?,?,?,?,?,?) ";
		
		public static String UPDATE_BOM_COMPONENTS=" update bom_comp_details set component_name=?,comp_length=?,comp_width=?,No_of_parts=?,Reqd_qty=?,Meas_uom=? where bom_detail_id=? and bom_comp_id=? ";
		
		
		//COLOR 
//		public static String GET_COLOR_MASTER="select Color_Code,Color_Id,Color_Desc,Remarks,Is_Active from Colors ";
//		public static String COLOR_MASTER_PAGE_COUNT="select count(*) from Colors";
//		public static String GET_COLOR_MASTER_INFO=" select Color_Code,Color_Id,Color_Desc,Remarks,Is_Active from Colors where Color_Id=? ";
//		public static String COLOR_CODE_EXIST="select count(*) from Colors where Color_Code=? ";
//		public static String COLOR_DESC_EXIST="select count(*) from Colors where Color_Desc=? ";
//		public static String VIEW_COLOR_INFO= "SELECT Color_Code,Color_Id,Color_Desc,Remarks,Is_Active from Colors order by Color_Id DESC";
//		public static String DELETE_COLOR="delete from Colors where Color_Id=?";
//		public static String SELECT_COLOR_HEADER_DYNAMIC=" Select cd.* "
//			+ " FROM  "
//			+ " colors c (NOLOCK) "
//			+ " Left Join Colors_Dynamic cd on c. "
//			+ " color_id = cd.color_id "
//			+ " Where c.color_id=? ";
//		public static String SELECT_COLOR="select Color_Code,Color_Id,Color_Desc,Remarks,case when Is_Active=1 then 'Active' else case when Is_Active=0 then 'Inactive' else '' end end as Is_Active from Colors where Color_Id=? ";
//		public static String SELECT_COLOR_DYNAMIC_BY_ID="Select cd.* "
//			+ " FROM  "
//			+ " colors c (NOLOCK) "
//			+ " Left Join Colors_Dynamic cd on c."
//			+ "color_id = cd.color_id "
//			+ " Where c.color_id=? ";
		
		//Size Master
		public static String DELETE_SIZE_RANGE_ROW="DELETE FROM Size_range WHERE size_range_id=?";
		public static String SIZE_RANGE_PAGE_COUNT="select count(*) FROM Size_Range si"
				+ " inner join size_master sm on si.size_id=sm.size_id";
		public static String GET_ALL_SIZE_RANGE= "SELECT si.Size_Range_Id,si.Size_Range,si.Applicable_To,si.Is_Active,si.Sl_No,si.Size_Id,si.Is_Active_Det"+
				" FROM Size_Range si where si.Size_Range_Id=? ";
		public static String GET_SIZE_RANGE_INFO= "SELECT si.Size_Range_Id,si.Size_Range,si.Applicable_To,si.Is_Active,si.Sl_No,si.Size_Id,sm.size_name,si.Is_Active_Det "
				+ " FROM Size_Range si "
				+ " inner join size_master sm on si.size_id=sm.size_id"
				+ " where si.Size_Range_Id=? ";
		public static String SIZE_RANGE_EXIST="select count(*) from Size_range where Size_Range=? ";
		
		//Season
		public static String SEASON_EXIST=" select count(*) from Season where Season_Name=? ";
		public static String GET_SEASON_MASTER_INFO=" select Season_Id,Season_Name,CONVERT(nvarchar,From_Date,105),CONVERT(nvarchar,To_Date,105),Is_Active from Season  where Season_Id=? ";
		public static String SEASON_MASTER_PAGE_COUNT=" select count(*) from Season ";
		public static String DELETE_SEASON = " delete from Season where Season_Id=? ";
		
		//Currency
		
		public static String CURRENCY_MASTER_PAGE_COUNT="select count(*) from Currency_Master";
		//public static String DEACTIVE_COLOR="update Colors set Is_Active=0 where Color_Id=?";
		public static String GET_CURRENCY_MASTER_INFO=" select Currency_Id,Currency_Name,Coin_Name,Symbol,Is_Active from  Currency_Master where Currency_Id=? ";
		public static String CURRENCY_NAME_EXIST="select count(*) from Currency_Master where Currency_Name=? ";
		public static String CURRENCY_COIN_EXIST="select count(*) from Currency_Master where Coin_Name=? ";
//		public static String VIEW_CURRENCY_INFO= "SELECT Currency_Id,Currency_Name,Coin_Name,Symbol,Is_Active from Currency_Master order by Currency_Id DESC";
		public static String DELETE_CURRENCY="delete from Currency_Master where Currency_Id=?";
		
		//Operation or Stage
		public static String OPERATION_STAGE_CODE_EXIST="select count(*) from Operation where Operation=? ";
		public static String GET_OPERATON_MASTER_INFO=" select Operation_id,Operation,Seqno,Operation_Description,Display_Order,Inspection_Required,Is_Production_op,Main_group,Sub_group,Is_Active from Operation where Operation_id=? ";
		public static String GET_OPERATION_MASTER_LIST="select Operation_id,Operation,Seqno,Operation_Description,Display_Order,Inspection_Required,Is_Production_op,Main_group,Sub_group,Is_Active from Operation";
		public static String OPERATION_MASTER_PAGE_COUNT="select count(*) from Operation";
		public static String DELETE_OPERATION_MASTER="delete from Operation where Operation_id=?";
		public static String DEACTIVE_OPREATION="update operation set Is_Active=0 where Operation_id=?";
		
		//Size Mapping 
		public static String GET_SIZEMAPPING_HEAD="select size_schedule_id,size_sched_ref,remarks,is_active from size_schedule where size_schedule_id=?";
		public static String GET_SIZEMAPPING_DETAIL_LIST="select ssd.size_schedule_id,ssd.size_sched_det_id,ssd.prod_size_range_id,ssd.prod_size_id,ssd.rm_size_range_id,ssd.rm_size_id,"
				+ " (select distinct Size_Range from  Size_Range where Size_Range_Id=ssd.prod_size_range_id) as prod_Size_Range "
				+ " ,psm.Size_Name,(select distinct Size_Range from  Size_Range where Size_Range_Id=ssd.rm_size_range_id) as rm_Size_Range,rmsm.Size_Name"
				+ " from size_schedule_det ssd"
				+ " inner join size_master psm with(nolock) on psm.Size_Id=ssd.prod_size_id"
				+ " inner join Size_Master rmsm with(nolock) on rmsm.Size_Id=ssd.rm_size_id"
				+ " where ssd.size_schedule_id=?";
		
		
		
		public static String GET_SIZEMAPPING_LIST="select ss.size_schedule_id,ssd.size_sched_det_id,ss.size_sched_ref,ss.remarks,case when ss.is_active=1 then 'Active' else case when ss.is_active=0 then 'Inactive' else '' end end as is_active, "
				+ " ssd.prod_size_range_id,ssd.prod_size_id,ssd.rm_size_range_id,ssd.rm_size_id,"
				+ " (select distinct Size_Range from  Size_Range where Size_Range_Id=ssd.prod_size_range_id) as prod_Size_Range "
				+ " ,psm.Size_Name as prod_size_name,(select distinct Size_Range from  Size_Range where Size_Range_Id=ssd.rm_size_range_id) as rm_Size_Range,rmsm.Size_Name as rm_size_name"
				+ " from size_schedule ss "
				+ " inner join size_schedule_det ssd with(nolock) on  ss.size_schedule_id=ssd.size_schedule_id"
				+ " inner join size_master psm with(nolock) on psm.Size_Id=ssd.prod_size_id"
				+ " inner join Size_Master rmsm with(nolock) on rmsm.Size_Id=ssd.rm_size_id ";
		
		public static String GET_SIZEMAPPING_LIST_COUNT="select count(*)"
				+ " from size_schedule ss "
				+ " inner join size_schedule_det ssd with(nolock) on  ss.size_schedule_id=ssd.size_schedule_id"
				+ " inner join size_master psm with(nolock) on psm.Size_Id=ssd.prod_size_id"
				+ " inner join Size_Master rmsm with(nolock) on rmsm.Size_Id=ssd.rm_size_id ";
		
		public static String GET_SIZEMAPPING_DETAIL="select distinct size_schedule_id,size_sched_det_id,prod_size_range_id,(select distinct Size_Range from  Size_Range where Size_Range_Id=prod_size_range_id) as prod_Size_Range"
				+ " from size_schedule_det where size_schedule_id=? and size_sched_det_id=?";
		
		//VARIANT 
		public static String VARIANT_MASTER_PAGE_COUNT="select count(*) from Variant_Master";
		public static String GET_VARIANT_MASTER_INFO=" select Variant_Code,Variant_Id,Variant_Name,Remarks,Status from Variant_Master where Variant_Id=? ";
		public static String VARIANT_CODE_EXIST="select count(*) from variant_master where variant_Code=? ";
		public static String VARIANT_DESC_EXIST="select count(*) from variant_Master where Variant_Name=? ";
		public static String VIEW_VARIANT_INFO= "SELECT Variant_Code,Variant_Id,Variant_Name,Remarks,Status from Variant_Master order by Variant_Id DESC";
		public static String DELETE_VARIANT="delete from Variant_Master where Variant_Id=?";
		public static String SELECT_VARIANT_HEADER_DYNAMIC=" Select cd.* "
			+ " FROM  "
			+ " Variant_Master c (NOLOCK) "
			+ " Left Join Variant_Dynamic cd on c. "
			+ " variant_id = cd.variant_id "
			+ " Where c.variant_id=? ";
		public static String SELECT_VARIANT="select Variant_Code,Variant_Id,Variant_Name,Remarks,case when Status=1 then 'Active' else case when Status=0 then 'Inactive' else '' end end as Status from Variant_Master where Variant_Id=? ";
		public static String SELECT_VARIANT_DYNAMIC_BY_ID="Select cd.* "
			+ " FROM  "
			+ " Variant_Master c (NOLOCK) "
			+ " Left Join Variant_Dynamic cd on c."
			+ " variant_id = cd.variant_id "
			+ " Where c.variant_id=? ";		

		//SIZE_MASTER

		public static String SIZE_CODE_EXIST=" select count(*) from size_master where size_name=? ";
		public static String INSERT_SIZE_MASTER=" INSERT INTO size_master(Size_Name,Is_Active) VALUES(?,?)";
		//Item master
		public static String GET_ITEMGROUP_DATA="SELECT g1.group_id,gt.group_type,gd.Item_Tracking_Applicable,gd.Expiry_Applicable,gd.Allow_Negative_Stock,gd.Issue_With_IO,gd.Tax_Category,"
				+ " gd.Size_applicable,gd.Color_Applicable,gd.Reservation_Applicable,gd.Inspection_Required,gd.Barcode_Required "
				+ " FROM groups g1  "
				+ " inner join group_type gt on g1.group_type_id=gt.group_type_id "
				+ " left join group_details gd on g1.group_id=gd.group_id "
				+ " WHERE g1.group_id =? ";
		
		//Approved Price List
		public static String GET_ITEM_DATA="select Standard_Cost_currency,Purchase_Uom,Standard_Cost from item where item_Id=?";
		
		/* TAX GROUP */

		public static String GET_TAX_GROUP_INFO = " SELECT tg.tax_group_id,tg.tax_group,tg.Tax_id,tg.Tax_Percentage,tm.Tax_Name,isnull(tg.status,0) as status "
				+ " FROM tax_group tg "
				+ " left JOIN  tax_master tm ON tg.tax_id=tm.tax_id  WHERE tg.tax_group_id=?"
				+ " order by tg.display_order";

		public static String GET_ALL_TAX_GROUP=" SELECT tg.tax_group_id,tg.tax_group,tg.Tax_id,tg.Tax_Percentage,tm.Tax_display_Name,tg.Status  FROM tax_group tg LEFT JOIN  tax_master tm ON tg.tax_id=tm.tax_id ";

		public static String GET_TAX_GROUP_COUNT = " SELECT DISTINCT COUNT(*)  FROM tax_group tg LEFT JOIN  tax_master tm ON tg.tax_id=tm.tax_id ";

		public static String DELETE_TAX_GROUP="DELETE FROM tax_group WHERE tax_group_id=? ";
		public static String DELETE_TAX_GROUP_ROW="DELETE FROM tax_group WHERE tax_group_id=? and tax_id=? ";

		public static String GET_RECEIVABLE_AGE_DURATION=" select DISTINCT to_day1,to_day2,to_day3,to_day4,to_day1_name,to_day2_name,to_day3_name,to_day4_name from set_receivables";

		public static String GET_PAYABLE_AGE_DURATION=" select DISTINCT to_day1,to_day2,to_day3,to_day4,to_day1_name,to_day2_name,to_day3_name,to_day4_name from set_payables";

		/*public static String GET_ACCOUNT_BOOK=" select a.account_id,a.Account_name,v.Fin_Tr_no,convert(nvarchar,v.fin_tr_date,105) as fin_tr_date,t.towards_Account_id,P.Account_Name Particulars, "+ 
				" v.cheq_no ,convert(nvarchar,V.cheq_date,105) as cheq_date,t.Debit,T.credit, V.Reference "+ 
				" from account a, Fin_TR_details t, Fin_Tr V,Account p ";*/

		public static String  GET_BILL_FOR_FIN_TR_DET="  select acc.account_id,acc.Account_name,ftb.bill_reference as Fin_Tr_no,convert(nvarchar,ft.fin_tr_date,105) as fin_tr_date,ftb.fin_tr_type,ftb.Debit,ftb.credit,isnull(ft.Reference,'') Reference,ft.Remarks,ftb.fin_tr_det_id,ft.fin_tr_id,ftb.adjustment_mode "+  
				"	from  Fin_TR ft  "+
				"   inner join Fin_TR_bills ftb on ft.fin_tr_type  = ftb.fin_tr_type  and ft.fin_tr_id = ftb.fin_tr_id and ft.company_id = ftb.company_id   and ft.location_id = ftb.location_id and ft.year_id = ftb.year_id "+
				"	inner join account acc on acc.account_id = ftb.Account_Id  where  ftb.company_Id=? and ftb.location_id=? and ftb.year_id=? and ftb.fin_tr_det_id=?  order by ft.Fin_Tr_id desc,ftb.Fin_Tr_det_id ";

		public static String  GET_COSTCENTER_FOR_FIN_TR_DET="  select cc.costcenter_id,cc.costcenter_name,ft.prefix+convert(nvarchar,ft.Fin_Tr_no) as Fin_Tr_no,convert(nvarchar,ft.fin_tr_date,105) as fin_tr_date,ft.fin_tr_type,ftc.Debit,ftc.credit,isnull(ft.Reference,'') Reference,ft.Remarks,ftc.fin_tr_det_id ,ft.fin_tr_id"+  
				"	from  Fin_TR ft  "+
				"   inner join Fin_TR_CC ftc on ft.fin_tr_type  = ftc.fin_tr_type  and ft.fin_tr_id = ftc.fin_tr_id and ft.company_id = ftc.company_id   and ft.location_id = ftc.location_id and ft.year_id = ftc.year_id "+
				"	inner join costcenter cc on cc.costcenter_id = ftc.costcenter_id  where  ftc.company_Id=? and ftc.location_id=? and ftc.year_id=? and ftc.fin_tr_det_id=?  order by ft.Fin_Tr_id desc,ftc.Fin_Tr_det_id ";


		public static String GET_SALES_REGISTER_COUNT=" select count(*) from temp_sales_register where user_id=?";

		public static String GET_PURCHASE_REGISTER_COUNT=" select count(*) from temp_purchase_register where user_id=?";
		//ed

		public static String GET_ED_TAX_IF_APPLICABLES="select tax_id,tax_display_name from tax_master where "
				+ " (tax_name like '%ED%' or tax_name like '%ECHESS%' or tax_name like '%SH Ecess%' ) order by display_order ";

		//VAT 
		public static String GET_VAT_TAX_IF_APPLICABLES=" select tax_id,tax_display_name from tax_master where  " 
				+ " (tax_name like '%VAT%' ) order by display_order ";

		//CST
		public static String GET_CST_TAX_IF_APPLICABLES=" select tax_id,tax_display_name from tax_master where tax_name like '%CST%' order by display_order ";

		//GST
		public static String GET_GST_TAX_IF_APPLICABLES=" select tax_id,tax_display_name from tax_master where tax_name like '%GST%' order by display_order ";

		//Service Tax
		public static String GET_SERVICE_TAX_IF_APPLICABLES=" select tax_id,tax_display_name from tax_master where tax_name like '%Service Tax%' order by display_order ";
		
		//ORDER
		
		//BUYER_ORDER
		public static String INSERT_BO_ATTACHMENT=" INSERT INTO sales_order_attach(company_id,location_id,year_id,so_id,Attach_Type,Attach_Path) VALUES(?,?,?,?,?,?) ";
		public static String BUYER_ORDER_HEADER_INFO="select so.so_id,so.so_no,so.prefix,convert(nvarchar,so.so_date,105) as so_date,so.party_id,p.party_name,isnull(so.buyer_po,'') as buyer_po,so.currency_id,"
				+ " cm.currency_name,so.ex_rate,so.season_id,isnull(s.season_name,'') as season_name,so.agent_id,isnull(ag.party_name,'') as agent_name,"
				+ " isnull(so.customer_plan_no,'') as customer_plan_no,isnull(so.payment_terms,'') as payment_terms,isnull(so.insurance_terms,'') as insurance_terms,"
				+ " isnull(so.delivery_terms,'') as delivery_terms,isnull(so.special_instruction,'') as special_instruction,isnull(so.packing_labeling,'') as packing_labeling "
				+ " from sales_order so with(nolock)"
				+ " inner join party p with(nolock) on so.party_id=p.party_id and p.party_tag='Customer'"
				+ " inner join currency_master cm with(nolock) on so.currency_id=cm.currency_id"
				+ " left join season s with(nolock) on so.season_id=s.season_id"
				+ " left join party ag with(nolock) on so.agent_id=ag.party_id and p.party_tag='Agent'"
				+ " where so.so_id=? and so.company_id=? and so.location_id=? and so.year_id=? ";

		public static String BUYER_ORDER_HEADER_INFO_DYNAMIC = " Select sodf.* "
				+ " FROM  "
				+ " sales_order so (NOLOCK) "
				+ " Left Join sales_order_dynamic sodf on so.so_id = sodf.so_id "
				+ " Where so.so_id=? ";
		
		public static String BUYER_ORDER_DETAIL_LIST="select distinct sod.so_id,sod.so_det_id,sod.line_no,sod.item_id,it.item_name,sod.variant_id,isnull(vm.variant_name,'') as variant_name,sod.size_range_id,"
				+ " sr.size_range,isnull(sod.qty,0) as qty,isnull(sod.shipped_qty,0) as shipped_qty,isnull(sod.rate_fcy,0) as rate_fcy,convert(nvarchar,sod.required_date,105) as  required_date,convert(nvarchar,sod.possible_date,105) as possible_date,isnull(sod.mrp,'') as mrp,isnull(sod.barcode,'') as barcode,isnull(sod.remark,'') as remark"
				+ " from sales_order so with(nolock) "
				+ " inner join sales_order_details sod with(nolock) on so.so_id=sod.so_id"
				+ " inner join item it with(nolock) on sod.item_id=it.item_id"
				+ " left join variant_master vm with(nolock) on sod.variant_id=vm.variant_id "
				+ " inner join size_range sr with(nolock) on sod.size_range_id=sr.size_range_id"
				+ " where sod.so_id=? and so.company_id=? and so.location_id=? and so.year_id=? ";
		
		public static String BUYER_ORDER_DETAIL_LIST_DYNAMIC="Select soddf.* "
				+ " FROM  "
				+ " sales_order so (NOLOCK) "
				+ " Left Join Sales_Order_Details_Dynamic soddf on so.so_id= soddf.so_id"
				+ " Where so.so_id=? ";
		
		public static String BUYER_ORDER_ATTACHMENT="SELECT so_id,attach_type,isnull(attach_path,''),reverse(substring(reverse(attach_path), 1,charindex('/', reverse(attach_path))-1)) as file_name"
				+ " from sales_order_attach where so_id=?";
		
		public static String CHECK_BO_AUTO_NUMBER_EXISTENCE="select count(*) from sales_order where so_no=? and prefix=? and company_id=? and location_id=? and year_id=?";
		
//		public static String   GET_BQ_SAVED_FOLLOWERS="  select t.tpcs_user_id,t.tpcs_user_first_name,t.tpcs_user_last_name, "+ 
//				"	tc.image_url,t.tpcs_user_login_name ,reverse(substring(reverse(image_url), 1,charindex('/', reverse(image_url))-1)) "+
//				"	from Sales_Quotation_Followers sqf , "+
//				"	tpcs_user t WITH(NOLOCK)  "+
//				"	left join tpcs_contact tc on t.tpcs_user_id = tc.user_id "+ 
//				"	WHERE sqf.user_id=t.tpcs_user_id "+
//				"	and sqf.quote_id=? and t.tpcs_user_id<>? ";
		
		public static String BUYER_ORDER_COUNT="select count(*) "
				+ " from sales_order so with(nolock) "
				+ " inner join sales_order_details sod with(nolock) on so.so_id=sod.so_id"
				+ " inner join item it with(nolock) on sod.item_id=it.item_id"
				+ " left join variant_master vm with(nolock) on sod.variant_id=vm.variant_id "
				+ " inner join size_range sr with(nolock) on sod.size_range_id=sr.size_range_id"
				+ " where so.company_id=? and so.location_id=? and so.year_id=? ";
		
		public static String BUYER_ORDER_LIST="select sod.so_id,sod.so_det_id,so.prefix,so.so_no,convert(nvarchar,so.so_date,105) as so_date,isnull(p.party_name,'') as buyer_name,isnull(so.buyer_po,'') as buyer_po,"
				+ " isnull(cm.currency_name,'') as currency_name,so.ex_rate,isnull(s.season_name,'') as season_name,isnull(a.party_name,'') as agent_name,isnull(so.customer_plan_no,'') as customer_plan_no,"
				+ " isnull(so.payment_terms,'') as payment_terms,isnull(so.insurance_terms,'') as insurance_terms,"
				+ " isnull(so.delivery_terms,'') as delivery_terms,isnull(so.special_instruction,'') as special_instruction,isnull(so.packing_labeling,'') as packing_labeling,"
				+ " sod.line_no,isnull(it.item_name,'') as item_name,isnull(vm.variant_name,'') as variant_name,"
				+ " sr.size_range,isnull(sod.qty,0) as qty,isnull(sod.shipped_qty,0) as shipped_qty,isnull(sod.rate_fcy,0) as rate_fcy,"
				+ " convert(nvarchar,sod.required_date,105) as  required_date,convert(nvarchar,sod.possible_date,105) as possible_date,"
				+ " isnull(sod.mrp,'') as mrp,isnull(sod.barcode,'') as barcode,isnull(sod.remark,'') as remark,isnull(Cancel_Tag,0) as cancel_tag,isnull(Close_Tag,0) as close_tag"
				+ " from sales_order so with(nolock) "
				+ " inner join sales_order_details sod with(nolock) on so.so_id=sod.so_id"
				+ " inner join party p with(nolock) on so.party_id=p.party_id"
				+ " left join currency_master cm with(nolock) on so.currency_id=cm.currency_id"
				+ " left join season s with(nolock) on so.season_id=s.season_id"
				+ " left join party a with(nolock) on so.agent_id=a.party_id"
				+ " inner join item it with(nolock) on sod.item_id=it.item_id"
				+ " left join variant_master vm with(nolock) on sod.variant_id=vm.variant_id "
				+ " inner join size_range sr with(nolock) on sod.size_range_id=sr.size_range_id " ;
		
		public static String SELECT_BUYER_ORDER_ATTACHMENT=" SELECT so_id,attach_type,attach_path from sales_order_attach where so_id=? ";
		
		public static String SELECT_BUYER_ORDER_DETAIL_DYNAMIC_BY_ID = " Select sdf.*, sodf.* "
				+ " FROM  "
				+ " sales_order so (NOLOCK) "
				+ " Join sales_order_details sod on so.so_id = sod.so_id "
				+ " Left Join sales_order_dynamic sdf on so.so_id = sdf.so_id "
				+ " Left Join sales_order_Details_Dynamic sodf on sod.so_det_id = sodf.so_det_id "
				+ " Where so.so_id=? AND sod.so_det_id=? ";
		
		public static String SELECT_BO_TC="select Sl_No,Terms,Particulars from Sales_Order_Terms where SO_Id=? order by Auto_Id";
		public static String DETAIL_VISIBLE_FIELDS_COUNT="select count(active) from dynamicfields df with(nolock)"
				+ " inner join dynamicrefscreenfields drsf with(nolock) on df.dynamicfieldid=drsf.dynamicfieldid"
				+ " where drsf.tableid=? and drsf.active=1";
		
		//PURCHASE_ORDER

		public static String GET_ALL_PREFIX="select prefix,case when(autono=1) then last_no else 0 end,starting_no from ?autno_table where company_id=? and location_id=? and year_id=? order by auto_id";
		
		public static String PURCHASE_ORDER_HEADER_INFO="select po.po_id,po.po_no,po.po_prefix,convert(nvarchar,po.po_date,105) as po_date,po.party_id,p.party_name,po.currency_id,"
				+ " cm.currency_name,po.ex_rate,isnull(po.other_ref,'') as other_ref,isnull(po.remark,'') as remark,"
				+ " isnull(po.purchase_type,'') as purchase_type,isnull(pom.Payment_Terms,'') as Payment_Terms,isnull(pom.Delivery_Terms,'') as Delivery_Terms,"
				+ " isnull(pom.Ship_To_Address,'') as Ship_To_Address,isnull(pom.Internal_Memo,'') as Internal_Memo,convert(nvarchar,pom.po_valid_till,105) as po_valid_till,isnull(pom.season_id,0) as season_id,isnull(s.season_name,'') as season_name "
				+ " from po po with(nolock)"
				+ " inner join po_more pom with(nolock) on po.po_id=pom.po_id"
				+ " inner join party p with(nolock) on po.party_id=p.party_id and p.party_tag='Customer'"
				+ " inner join currency_master cm with(nolock) on po.currency_id=cm.currency_id"
				+ " left join season s with(nolock) on pom.season_id=s.season_id"
				+ " where po.po_Id=? and po.company_id=? and po.location_id=? and po.year_id=? ";

		public static String PURCHASE_ORDER_HEADER_INFO_DYNAMIC="select podf.* "
				+ " from po po with(nolock) "
				+ " inner join po_dynamic podf with(nolock)  on po.po_id = podf.po_id "
				+ " where po.po_id=?";

		public static String PURCHASE_ORDER_LIST="select pod.po_id,pod.po_det_id,po.po_prefix,po.po_no,convert(nvarchar,po.po_date,105) as po_date,isnull(p.party_name,'') as buyer_name, "
				+ " isnull(cm.currency_name,'') as currency_name,po.ex_rate,po.purchase_type,isnull(po.other_ref,'') as other_ref,isnull(po.remark,'') as hremark,"
				+ " convert(nvarchar,pom.po_valid_till,105) as po_valid_till,isnull(pom.payment_terms,'') as payment_terms,isnull(pom.delivery_terms,'') as delivery_terms,"
				+ " isnull(pom.ship_to_address,'') as ship_to_address,isnull(pom.internal_memo,'') as internal_memo,"
				+ " isnull(it.item_name,'') as item_name, isnull(vm.variant_name,'') as variant_name, "
				+ " sr.size_range,isnull(pod.other_spec,'') as other_spec,  isnull(pod.uom,0) as uom, isnull(pod.qty,0) as qty, isnull(pod.qty_uom,'') as qty_uom,"
				+ " isnull(pod.price_fcy,0) as price_fcy, convert(nvarchar,pod.required_date,105) as  required_date,"
				+ " isnull(pod.remark,'') as remark, isnull(pod.remark1,'') as remark1, isnull(pod.remark2,'') as remark2"
				+ " , isnull(pod.remark3,'') as remark3,isnull(po.cancel_tag,0) as cancel_tag, isnull(po.close_tag,0) as close_tag"
				+ " from po po with(nolock)  "
				+ " inner join po_more pom with(nolock) on po.po_id=pom.po_id"
				+ " inner join po_details pod with(nolock) on po.po_id=pod.po_id "
				+ " inner join party p with(nolock) on po.party_id=p.party_id  "
				+ " inner join currency_master cm with(nolock) on po.currency_id=cm.currency_id "
				+ " inner join item it with(nolock) on pod.item_id=it.item_id "
				+ " left join variant_master vm with(nolock) on pod.variant_id=vm.variant_id  "
				+ " left join size_range sr with(nolock) on pod.size_range_id=sr.size_range_id" ;

		public static String INSERT_PO_ATTACHMENT=" INSERT INTO po_url(company_id,location_id,year_id,po_id,Attach_Type,Attach_Path) VALUES(?,?,?,?,?,?) ";

		public static String PURCHASE_ORDER_DETAIL_LIST="select pod.po_id,pod.po_det_id,pod.item_id,it.item_name,pod.variant_id,isnull(vm.variant_name,'') as variant_name, "
				+ " pod.size_range_id,sr.size_range, pod.other_spec, isnull(pod.uom,0) as uom,isnull(pod.qty,0) as qty, isnull(pod.qty_uom,0) as qty_uom, "
				+ " isnull(pod.price_fcy,0) as price_fcy,isnull(pod.remark1,'') as remark1,isnull(pod.remark2,'') as remark2, isnull(pod.remark3,'') as remark3, "
				+ " convert(nvarchar,pod.required_date,105) as  required_date, isnull(pod.remark,'') as remark,isnull(pod.tax_group_id,0) as  tax_group_id"
				+ " ,isnull(pod.discount_percent,0) as  discount_percent,(isnull(pod.qty,0)*isnull(pod.price_fcy,0)) value_before_discount_fcy,isnull(value_before_tax_fcy,0) as value_after_discount_fcy"
				+ " from po po with(nolock) "
				+ " inner join po_details pod with(nolock) on po.po_id=pod.po_id  "
				+ " inner join item it with(nolock) on pod.item_id=it.item_id "
				+ " inner join size_range sr with(nolock) on pod.size_range_id=sr.size_range_id  "
				+ " left join variant_master vm with(nolock) on pod.variant_id=vm.variant_id  "
				+ " where pod.po_id=? and po.company_id=? and po.location_id=? and po.year_id=? ";

		public static String PURCHASE_ORDER_DETAIL_LIST_DYNAMIC="select poddf.* "
				+ " from  "
				+ " po po (nolock) "
				+ " left join po_details_dynamic poddf on po.po_id= poddf.po_id"
				+ " where po.po_id=? ";

//		public static String PURCHASE_ORDER_LIST="select pod.po_id,pod.po_det_id,po.po_prefix,po.po_no,convert(nvarchar,po.po_date,105) as po_date,isnull(p.party_name,'') as buyer_name, "
//				+ " isnull(cm.currency_name,'') as currency_name,po.ex_rate,isnull(pom.Payment_Terms,'') as Payment_Terms,"
//				+ " isnull(pom.Delivery_Terms,'') as Delivery_Terms,isnull(pom.Ship_To_Address,'') as Ship_To_Address,isnull(pom.Internal_Memo,'') as Internal_Memo,"
//				+ " pod.slno, isnull(it.item_name,'') as item_name, isnull(vm.variant_name,'') as variant_name, "
//				+ " sr.size_range,  isnull(pod.uom,0) as uom, isnull(pod.qty,0) as qty, isnull(pod.price,0) as price, isnull(pod.qty_uom,'') as qty_uom,"
//				+ " isnull(pod.price_fcy,0) as price_fcy, convert(nvarchar,pod.required_date,105) as  required_date, isnull(po.cancel_tag,0) as cancel_tag "
//				+ " from po po with(nolock)  "
//				+ " inner join po_more pom with(nolock) on po.po_id=pom.po_id"
//				+ " inner join po_details pod with(nolock) on po.po_id=pod.po_id "
//				+ " inner join party p with(nolock) on po.party_id=p.party_id  "
//				+ " inner join currency_master cm with(nolock) on po.currency_id=cm.currency_id "
//				+ " inner join item it with(nolock) on pod.item_id=it.item_id "
//				+ " left join variant_master vm with(nolock) on pod.variant_id=vm.variant_id  "
//				+ " left join size_range sr with(nolock) on pod.size_range_id=sr.size_range_id";

		public static String SELECT_PURCHASE_ORDER_DETAIL_DYNAMIC_BY_ID = " select pdf.*, podf.*  "
				+ " from  "
				+ " po po (nolock)  "
				+ " join po_details pod on po.po_id = pod.po_id "
				+ " left join po_dynamic pdf on po.po_id = pdf.po_id  "
				+ " left join po_details_dynamic podf on pod.po_det_id = podf.po_det_id  "
				+ " where po.po_id=? and pod.po_det_id=? " ;

		public static String PURCHASE_ORDER_COUNT="select count(*)  "
				+ " from po po with(nolock)  "
				+ " inner join po_more pom with(nolock) on po.po_id=pom.po_id"
				+ " inner join po_details pod with(nolock) on po.po_id=pod.po_id "
				+ " inner join party p with(nolock) on po.party_id=p.party_id  "
				+ " inner join currency_master cm with(nolock) on po.currency_id=cm.currency_id "
				+ " inner join item it with(nolock) on pod.item_id=it.item_id "
				+ " left join variant_master vm with(nolock) on pod.variant_id=vm.variant_id  "
				+ " left join size_range sr with(nolock) on pod.size_range_id=sr.size_range_id";

		public static String PURCHASE_ORDER_ATTACHMENT="SELECT po_id,attach_type,isnull(attach_path,''),reverse(substring(reverse(attach_path), 1,charindex('/', reverse(attach_path))-1)) as file_name "
				+"  from po_url where po_id=?" ;

		public static String SELECT_PO_TC="select SlNo,Terms,Particulars from PO_Tc where PO_Id=? order by Auto_Id";
		
		//INDENT
		
		
		public static String INDENT_HEADER_INFO="SELECT ind.Indent_Id,ind.Indent_No,ind.Prefix,convert(nvarchar,ind.Indent_Date,105) as Indent_Date, "
				+ " isnull(ind.Indent_Type,'') as Indent_Type,isnull(ind.department,'') as Department, "
				+ " isnull(ind.Remarks,'') as Remarks,isnull(ind.other_ref,'') as other_ref "
				+ " from indent ind with(nolock)"
				+ " where ind.indent_id=? and ind.company_id=? and ind.location_id=? and ind.year_id=? "; 
				  
		
		public static String INDENT_HEADER_INFO_DYNAMIC="Select indf.* " 
				+ " FROM "
				+ " Indent ind (NOLOCK) "
				+ " Left Join Indent_Dynamic indf on ind.indent_id = indf.indent_id " 
				+ " Where ind.indent_id=? ";
		
		
		public static String INDENT_DETAIL_LIST="select distinct inde.Indent_Id,inde.Indent_Det_Id,inde.item_id,it.item_name,"
				+ " inde.variant_id,isnull(vm.variant_name,'') as variant_name,inde.size_range_id, sr.size_range,isnull(inde.indent_qty,0) as Indent_Qty,"
				+ " inde.uom as UOM,isnull(inde.party_id,0) as Buyer_Id,isnull(p.party_name,0) as Buyer_Name, convert(nvarchar,inde.required_date,105) as required_date,"
				+ " isnull(inde.remark,'') as remark,um.uom_id ,isnull(inde.so_det_id,0) so_det_id, it.group_id, "
				+ " isnull(g.group_name,'') as group_name "
				+ " from Indent ind with(nolock) "
				+ " inner join Indent_details inde with(nolock) on ind.indent_id=inde.indent_id "
				+ " left join party p with(nolock) on inde.party_id=p.party_id "
				+ " inner join item it with(nolock) on inde.item_id=it.item_id "
				+ " inner join Groups g with(nolock) on g.group_id=it.group_id "
				+ " inner join Uom_Master um with(nolock) on inde.uom=um.uom "
				+ " left join variant_master vm with(nolock) on inde.variant_id=vm.variant_id "
				+ " inner join size_range sr with(nolock) on inde.size_range_id=sr.size_range_id "
				+ " where inde.indent_id=? and ind.company_id=? and ind.location_id=? and ind.year_id=? ";


		public static String INDENT_DETAIL_LIST_DYNAMIC="Select indf.* "
				+ " FROM  "
				+ " Indent ind (NOLOCK) "
				+ " Left Join Indent_Details_Dynamic indf on ind.indent_id= indf.indent_id"
				+ " Where ind.indent_id=? ";
		
	
		public static String INDENT_LIST=" select indd.indent_id,indd.indent_det_id,ind.prefix + convert(nvarchar,ind.Indent_No) as prefix,ind.indent_no,"
				+ " convert(nvarchar,ind.indent_date,105) as indent_date, isnull(ind.indent_type,'') as indent_type, isnull(ind.department,'') as department, "
				+ " isnull(ind.remarks,'') as remarks, isnull(ind.other_ref,'') as other_ref, indd.indent_line_no, isnull(it.item_name,'') as item_name, "
				+ " isnull(vm.variant_name,'') as variant_name, sr.size_range,  isnull(indd.indent_qty,0) as indent_qty, indd.uom as UOM, "
				+ " isnull(p.party_name,'') as buyer_name, convert(nvarchar,indd.required_date,105) as required_date,  isnull(indd.remark,'') as remark,"
				+ " isnull(ind.Cancel_Tag,0) as cancel_tag,isnull(ind.Close_Tag,0) as close_tag,isnull(g.group_name,'') as group_name "
				+ " from indent ind with(nolock) "
				+ " inner join indent_details indd with(nolock) on ind.indent_id=indd.indent_id"
				+ " left join party p with(nolock) on indd.party_id=p.party_id "
				+ " inner join item it with(nolock) on indd.item_id=it.item_id "
				+ " inner join Groups g with(nolock) on g.group_id=it.group_id "
				+ " left join variant_master vm with(nolock) on indd.variant_id=vm.variant_id "
				+ " inner join size_range sr with(nolock) on indd.size_range_id=sr.size_range_id " ;
		
		
		public static String SELECT_INDENT_DETAIL_DYNAMIC_BY_ID = " Select indf.*, inddf.* "
				+ " FROM  "
				+ " Indent ind (NOLOCK) "
				+ " Join Indent_details indd on ind.Indent_id = indd.Indent_id "
				+ " Left Join Indent_dynamic indf on ind.Indent_id = indf.Indent_id "
				+ " Left Join Indent_Details_Dynamic inddf on indd.Indent_det_id = inddf.Indent_det_id "
				+ " Where ind.Indent_id=? AND indd.Indent_det_id=? ";
		
		
		//Terms and Conditions
		public static String SELECT_INDENT_TC="select Sl_No,Terms,Particulars from Indent_Terms where Indent_Id=? order by Auto_Id";
		
			
		public static String INDENT_COUNT="select count(*) "
				+ " from indent ind with(nolock) "
				+ " inner join indent_details indd with(nolock) on ind.indent_id=indd.indent_id"
				+ " inner join item it with(nolock) on indd.item_id=it.item_id"
				+ " left join variant_master vm with(nolock) on indd.variant_id=vm.variant_id "
				+ " inner join size_range sr with(nolock) on indd.size_range_id=sr.size_range_id"
				+ " inner join party p on indd.party_id=p.party_id"
				+ " where ind.company_id=? and ind.location_id=? and ind.year_id=? ";
		
		
		public static String INSERT_IND_ATTACHMENT=" INSERT INTO indent_attach(company_id,location_id,year_id,indent_id,Attach_Type,Attach_Path) VALUES(?,?,?,?,?,?) ";
		
		
		public static String INDENT_ATTACHMENT="SELECT indent_id,attach_type,isnull(attach_path,''), "
				+ " reverse(substring(reverse(attach_path), 1,charindex('/', reverse(attach_path))-1)) as file_name "
				+ " from indent_attach where indent_id=?";

		
		public static String SELECT_INDENT_ATTACHMENT=" SELECT indent_id,attach_type,attach_path from Indent_Attach where indent_id=? ";
		
		
		public static String CHECK_IND_AUTO_NUMBER_EXISTENCE="select count(*) from indent where indent_no=? and prefix=? and company_id=? "
				+ " and location_id=? and year_id=?";
		
		
		public static String GET_INDENT_PRINT_HEADER=" SELECT distinct ind.indent_id,ind.indent_no as indent_no,"
				+ " convert(nvarchar,ind.indent_date,105) as indent_date , isnull(ind.indent_type,'') as indent_type, isnull(ind.department,'') as department, "
				+ " isnull(ind.remark,'') as remark, isnull(ind.other_ref,'') as other_ref " 
				+ " FROM  indent ind " 
				+ " where  ind.company_Id=? and ind.year_id=? and ind.location_id=? and  ind.indent_id=? ";
		
	

		
}
