package hr.manage.dao.financial;

import hr.manage.entity.CwgonRecord;
import hr.manage.filter.JDBC;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import util.Page;

import com.mysql.jdbc.Connection;
//CALL sp_page(1,5,'*',"view_onrecord WHERE checkstatus = 3
//or checkstatus = 6",'order by id')

@Component
public class financialDao {
	JDBC jdbc = new JDBC();
	//创建连接接

	public Page<CwgonRecord> getRecordlistPage(int index,String Clike,String like){
		Connection connection = jdbc.getConnection();
		String sql ="";
		if(like == null){
			like="";
		}
	   if(Clike ==null||Clike==""){

			 sql = "view_onrecord WHERE (userName LIKE '%"+like+"%' AND checkstatus = 3) or (userName LIKE '%"+like+"%' and checkstatus = 6)  ";
	   }else{
		
		 sql = "view_onrecord WHERE checkstatus = "+Clike+
				"  AND userName LIKE '%"+like+"%' ";
	   }
		
		Page<CwgonRecord> page = new Page<CwgonRecord>();
		List<CwgonRecord> list = new ArrayList<CwgonRecord>();
		int count=0;	    
		try {
			CallableStatement st= connection.prepareCall("{CALL sp_page(?,?,?,?,'order by id desc')}");
			st.setObject(1, index);
			st.setObject(2, 30);
			st.setObject(3, "*");
			st.setObject(4, sql);
			
			ResultSet rst =st.executeQuery();
			
			while(rst.next()){
				CwgonRecord  record = new CwgonRecord();
				record.setId(rst.getInt("id"));
				record.setBid(rst.getInt("bid"));
				record.setUid(rst.getInt("uid"));
				record.setCreateBy(rst.getInt("createBy"));
				
				record.setCreationDate(rst.getDate("creationDate"));
				record.setRecordNumber(rst.getString("recordNumber"));
				record.setOrderNumber(rst.getString("orderNumber"));
		
				//关联子表字段
				record.setNumber(rst.getString("number"));
				record.setName(rst.getString("name"));
				record.setPrice(rst.getDouble("price"));
				record.setUserName(rst.getString("userName"));
				record.setStaffName(rst.getString("staffName"));
				record.setCheckstatus(rst.getInt("checkstatus"));
				record.setRemainingTime(rst.getInt("remainingTime"));
				record.setResidueNumber(rst.getInt("residueNumber"));
				
				list.add(record);
			}
			System.out.println(list.size());
			
			if(st.getMoreResults()==true){
				rst=st.getResultSet();
				if(rst.next()){
					count = rst.getInt(1);
				}
				System.out.println(count);
				
			}
			rst.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setClike(Clike);
		page.setLike(like);
		page.setGetlist(list);
		page.setCount(count);
		page.setCurrentPage(index);
		page.setPageCount(count, 30);
		
		jdbc.Close();
		
		return page;
	}
	
	
	
}
