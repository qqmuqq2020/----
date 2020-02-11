package hr.manage.service.staffs;

import hr.manage.entity.Staffs;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface StaffsLoginService {

	
	//登录
	public Staffs userLogin(String name);
	
	//基于分页的 跳转页面 上一页下一页 模糊查询及多条件查询
	public Map<String, Object> getPage(int pageIndex,int pageSize,String staffname,String positionID,String sectionID,Integer pid,String name) throws SQLException;
	

	
	//保存
	public int saveStaff(Staffs staffs);
	
	//MAX id
	public int selectID();

	//get by id
	public List<Staffs> getbyid(Integer id);
	
	//get by id for view
	public Staffs getStaffsById(Integer id);
	
	//删除
	public int delStaffsById(int id);
	
	//修改
	public int updateStaffs(Staffs staffs);
	
	//人员详细信息
	public List<Staffs> selectStaffs(@Param("id")int id);
	
	//判断身份证重复
	public List<Staffs> getbyCard(String card);
	
	//判断电话号重复
	public List<Staffs> getbycontactWay(String contactWay);
	
	//发卡
	public int updateIC(String icID,int id);
	
	//
	public List<Staffs> getbyICid(String icID);
	//获取是否有备案或客户
	public String getdel(int id);
}
