package hr.manage.service.impl.staffs;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hr.manage.dao.staffs.StaffsLoginDao;
import hr.manage.entity.Staffs;
import hr.manage.filter.JDBC;
import hr.manage.service.staffs.StaffsLoginService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mysql.jdbc.Connection;

@Service
public class StaffsLoginServiceImpl implements StaffsLoginService {
	
	JDBC Jdbc = new JDBC();
	@Resource
	private StaffsLoginDao sdao;
	
	// 登录
	public Staffs userLogin(String name) {
		// TODO Auto-generated method stub
		return sdao.userLogin(name);
	}

	//基于分页的 模糊,多条件,跳转页面的查询
	public Map<String, Object> getPage(int pageIndex, int pageSizes, String staffName,String positionID,String sectionID,Integer pid,String name) throws SQLException {
		// TODO Auto-generated method stub
		int count = 0;
		Connection connection =Jdbc.getConnection();
		Map<String, Object> map=new HashMap<String, Object>();
		String sql="";
		if(pid==0){
		sql = "view_staff where staffName like '" + staffName + "' and positionID like '"+ positionID+"'";
		}else if(pid==2){
			sql = "view_staff where staffNameID = '"+name+"' and staffName like '" + staffName + "' and positionID like '"+ positionID+"' ";
		}
		List<Staffs> list = new ArrayList<Staffs>();
		try {
			CallableStatement call=connection.prepareCall("CALL sp_page(?,?,'*',?,' order by id desc ')");
			// 调用存储过程
			call.setInt(1, pageIndex);//页码
			call.setInt(2, pageSizes);//每页显示数量
			call.setString(3, sql);//表明及条件
			//占位符传值
			ResultSet rs=call.executeQuery();
			while (rs.next()) {
				Staffs staffs = new Staffs();
				staffs.setBirthday(rs.getString("birthday"));
				staffs.setContactWay(rs.getString("contactWay"));
				staffs.setIcID(rs.getString("icID"));
				staffs.setId(rs.getInt("id"));
				staffs.setIdCardNo(rs.getString("idCardNo"));
				staffs.setNowAddress(rs.getString("nowAddress"));
				staffs.setPicture(rs.getString("picture"));
				staffs.setPositionID(rs.getInt("positionID"));			
				staffs.setPositionName(rs.getString("positionName"));
				staffs.setStaffID(rs.getString("staffID"));
				staffs.setStaffName(rs.getString("staffName"));
				staffs.setStaffPassword(rs.getString("staffPassword"));
				staffs.setReady2(rs.getString("staffNameUp"));
				list.add(staffs);
			}
			if (call.getMoreResults()) {
				rs=call.getResultSet();
				while (rs.next()) {
					count=rs.getInt(1);
				}
			}
			map.put("staffs", list);
			map.put("total", count);

		} finally{
			Jdbc.Close();
		}
		
		return map;
	}
	
	
	
	// MAXid
	public int selectID() {

		return sdao.selectID();
	}

	// 保存
	public int saveStaff(Staffs staffs) {
		// TODO Auto-generated method stub
		return sdao.saveStaff(staffs);
	}

	// getbyid for view
	public Staffs getStaffsById(Integer id) {
		// TODO Auto-generated method stub
		return sdao.getStaffsById(id);
	}

	// 删除
	public int delStaffsById(int id) {
		// TODO Auto-generated method stub
		return sdao.delStaffsById(id);
	}

	// get by id
	public List<Staffs> getbyid(Integer id) {
		// TODO Auto-generated method stub
		return sdao.getbyid(id);
	}

	// 修改
	public int updateStaffs(Staffs staffs) {
		// TODO Auto-generated method stub
		return sdao.updateStaffs(staffs);
	}

	//人员详细信息
	public List<Staffs> selectStaffs(int id) {
		// TODO Auto-generated method stub
		return sdao.selectStaffs(id);
	}
	
	//判断身份证重复
	public List<Staffs> getbyCard(String card) {
		// TODO Auto-generated method stub
		return sdao.getbyCard(card);
	}
	
	//判断电话号重复
	public List<Staffs> getbycontactWay(String contactWay) {
		// TODO Auto-generated method stub
		return sdao.getbycontactWay(contactWay);
	}
		
	//发卡
	@Override
	public int  updateIC(String icID, int id) {
		// TODO Auto-generated method stub
		return sdao.updateIC(icID, id);
	}

	@Override
	public List<Staffs> getbyICid(String icID) {
		// TODO Auto-generated method stub
		return sdao.getbyicID(icID);
	}

	public String getdel(int id){
		return sdao.getdel(id);
	}

}
