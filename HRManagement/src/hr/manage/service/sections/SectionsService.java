package hr.manage.service.sections;

import hr.manage.entity.Sections;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface SectionsService {

	// 分页模糊查询部门
	public Map<String, Object> getPage(int pageIndex, int pageSizes,
			String sectionname) throws SQLException;

	// 添加部门
	public int Add(String sectionname);

	// 修改部门
	public int update(String nationName, Integer id);

	// 删除部门
	public int delete(Integer id);

	// 根据sectionid查询
	public List<Sections> getById(Integer sectionid);

	// 根据sid所属部门查询所属部门的名称
	public Sections getSectionName(String sid);

	// 查询所有部门
	public List<Sections> findSections();

	// 删除部门时把该部门的员工分到未分配部门下
	public int updateStaffsSectionID(Integer sectionid);
}
