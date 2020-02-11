package hr.manage.service.impl.sections;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hr.manage.dao.sections.SectionsDao;
import hr.manage.entity.Sections;
import hr.manage.filter.JDBC;
import hr.manage.service.sections.SectionsService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mysql.jdbc.Connection;

@Service
public class SectionsServiceImpl implements SectionsService {
	JDBC Jdbc=new JDBC();
	@Resource
	private SectionsDao sd;
	
	/*
	 * (修改)
	 * @see hr.manage.service.sections.SectionsService#update(java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public int update(String sectionName, Integer sectionid) {
		// TODO Auto-generated method stub
		return sd.update(sectionName, sectionid);
	}
	/*
	 * (删除)
	 * @see hr.manage.service.sections.SectionsService#delete(java.lang.Integer)
	 */
	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return sd.del(id);
	}
	/*
	 * (根据sectionid查询单个)
	 * @see hr.manage.service.sections.SectionsService#getById(java.lang.Integer)
	 */
	@Override
	public List<Sections> getById(Integer id) {
		// TODO Auto-generated method stub
		return sd.getByID(id);
	}
	/*
	 * (根据sid查询所属部门的名称)
	 * @see hr.manage.service.sections.SectionsService#getSectionName(java.lang.String)
	 */
	@Override
	public Sections getSectionName(String sid) {
		// TODO Auto-generated method stub
		return sd.getSectionName(sid);
	}
	/*
	 * (查询所有部门)
	 * @see hr.manage.service.sections.SectionsService#findSections()
	 */
	@Override
	public List<Sections> findSections() {
		// TODO Auto-generated method stub
		return sd.findSections();
	}
	/*
	 * (分页模糊查询所有部门)
	 * @see hr.manage.service.sections.SectionsService#getPage(int, int, java.lang.String)
	 */
	@Override
	public Map<String, Object> getPage(int pageIndex, int pageSizes, String sectionname) throws SQLException {
		// TODO Auto-generated method stub
		int count = 0;//获取所有数据的个数
		Connection connection=Jdbc.getConnection() ;
		Map<String, Object> map=new HashMap<String, Object>();
		//拼接字符串
		final String fen = "sections where sectionname like '" + sectionname + "' and sectionID>0";
		List<Sections> list = new ArrayList<Sections>();
		try {
			CallableStatement call=connection.prepareCall("CALL sp_page(?,?,'*',?,' order by sectionid asc')");
			call.setInt(1, pageIndex);
			call.setInt(2, pageSizes);
			call.setString(3, fen);
			ResultSet rs=call.executeQuery();
			while (rs.next()) {
				Sections sections=new Sections();
				sections.setSectionid(rs.getInt("sectionID"));
				sections.setSectionname(rs.getString("sectionName"));
				sections.setSid(rs.getInt("sid"));
				list.add(sections);
			}
			//获取返回结果中的总条数
			if (call.getMoreResults()==true) {
				rs=call.getResultSet();
				while (rs.next()) {
					count=rs.getInt(1);
				}
			}
			map.put("sections", list);
			map.put("total", count);
			return map;
		}finally{
			Jdbc.Close();
		}
		
	
	}
	/*
	 * (添加)
	 * @see hr.manage.service.sections.SectionsService#Add(java.lang.String, int)
	 */
	@Override
	public int Add(String sectionname) {
		// TODO Auto-generated method stub
		return sd.add(sectionname);
	}
	/*
	 * (删除部门时修改该部门下的员工的部门状态)
	 * @see hr.manage.service.sections.SectionsService#updateStaffsSectionID(java.lang.Integer)
	 */
	@Override
	public int updateStaffsSectionID(Integer sectionid) {
		// TODO Auto-generated method stub
		return sd.updateStaffsSectionID(sectionid);
	}


}
