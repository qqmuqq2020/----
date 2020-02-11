package hr.manage.dao.book;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import hr.manage.entity.CwgBookManager;

import org.springframework.stereotype.Component;

import util.Page;

import com.mysql.jdbc.Connection;
import hr.manage.filter.JDBC;

@Component
public class PageBookDao {

	JDBC jdbc = new JDBC();
	//创建连接接Y
	public Page<CwgBookManager> getCwgBookManagerlistPage(Integer index,String like,String clike,String pub,String snnumber,Integer psid){
		Connection connection = jdbc.getConnection();

		Page<CwgBookManager> page = new Page<CwgBookManager>();
		List<CwgBookManager> list = new ArrayList<CwgBookManager>();
		int count=0;

		try {
			CallableStatement st= connection.prepareCall("{CALL sp_page(?,?,?,?,'order by id desc')}");
			st.setObject(1, index);
			st.setObject(2, 30);
			st.setObject(3, "*");
			/*if(clike==""){
			  st.setObject(4, "view_book where name like '%"+like+"%' and author like '%"+pub+"%'");
			}
			if(pub==""){
				st.setObject(4, "view_book where name like '%"+like+"%' and publisher like '%"+clike+"%'");
				}
			if(like==""){
				st.setObject(4, "view_book where publisher like '%"+clike+"%' and author like '%"+pub+"%'");
				}
			else{
				st.setObject(4, "view_book where publisher like '%"+clike+"%' and author like '%"+pub+"%' and name like '%"+like+"%'");
			}*/
			st.setObject(4, "view_book where publisher like '%"+clike+"%' and author like '%"+pub+"%' and name like '%"+like+"%' and serialNumber like '%"+snnumber+"%'");
			ResultSet rst =st.executeQuery();

			while(rst.next()){

				CwgBookManager record = new CwgBookManager();
				record.setId(rst.getInt("id"));
				record.setNumber(rst.getString("number"));
				record.setName(rst.getString("name"));
				record.setSerialNumber(rst.getString("serialNumber"));
				record.setPublisher(rst.getString("publisher"));
				record.setAuthor(rst.getString("author"));
				record.setCreateBy(rst.getInt("createBy"));
				record.setCreationDate(rst.getDate("creationDate"));
				record.setTotal(rst.getInt("total"));
				record.setPrice(rst.getDouble("price"));
				record.setDetail(rst.getString("detail"));
				record.setStatus(rst.getInt("status"));
				record.setSname(rst.getString("sname"));
				record.setPsid(psid);
				list.add(record);
			}
			if(st.getMoreResults()==true){
				rst=st.getResultSet();
				if(rst.next()){
					count = rst.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		page.setGetlist(list);
		page.setCount(count);
		page.setCurrentPage(index);
		page.setPageCount(count, 30);
		page.setLike(like);
		page.setClike(clike);
		page.setPub(pub);
		page.setSnnumber(snnumber);
		
		
		jdbc.Close();
		
		return page;
	}

	//根据状态查看对应备案信息[模糊查询方法]
//	public Page<CwgBookManager> getRecordBystatus(int index,String text){
//		
//		Page<CwgBookManager> page = new Page<CwgBookManager>();
//		List<CwgBookManager> list = new ArrayList<CwgBookManager>();
//		int count=0;
//		try {
//			CallableStatement st= connection.prepareCall("{CALL sp_page(?,?,?,?,'order by id')}");
//			st.setObject(1, index);
//			st.setObject(2, 5);
//			st.setObject(3, "*");
//			st.setObject(4, "view_book where name like '%"+text+"%'");
//			
//			ResultSet rst =st.executeQuery();
//			
//			while(rst.next()){
//				CwgBookManager  record = new CwgBookManager();
//				record.setId(rst.getInt("id"));
//				record.setNumber(rst.getString("number"));
//				record.setName(rst.getString("name"));
//				record.setSerialNumber(rst.getString("serialNumber"));
//				record.setPublisher(rst.getString("publisher"));
//				record.setAuthor(rst.getString("author"));
//				record.setCreateBy(rst.getInt("createBy"));
//				record.setCreationDate(rst.getDate("creationDate"));
//				record.setTotal(rst.getInt("total"));
//				record.setPrice(rst.getDouble("price"));
//				record.setDetail(rst.getString("detail"));
//				record.setStatus(rst.getInt("status"));
//				record.setSname(rst.getString("sname"));
//				list.add(record);
//			}
//
//			if(st.getMoreResults()==true){
//				rst=st.getResultSet();
//				if(rst.next()) {
//					count = rst.getInt(1);
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		page.setGetlist(list);
//		page.setCount(count);
//		page.setCurrentPage(index);
//		page.setPageCount(count, 5);
//		return page;
//	}
}
