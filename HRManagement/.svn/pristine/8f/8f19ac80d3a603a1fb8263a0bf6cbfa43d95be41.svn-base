package hr.manage.dao.sections;

import hr.manage.entity.Sections;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionsDao {
	//查询全部部门
	@Select("select * from sections where sectionID>0")
	public List<Sections> findSections();

	//部门分页模糊查询
	public Map<String, Object> getPage(int pageIndex,
			 int pageSizes,
			 String sectionname)throws SQLException ;

	// 部门添加
	@Insert("INSERT INTO sections(sectionName) VALUES(#{sectionname})")
	public int add(@Param("sectionname")String sectionname) ;

	// 部门修改
	@Update("update Sections set sectionName = #{sectionname} where sectionId= #{sectionid}")
	public int update(@Param("sectionname") String sectioname,
			@Param("sectionid") Integer sectionid);

	// 部门删除
	@Delete("delete from Sections where sectionId = #{sectionid}")
	public int del(@Param("sectionid") Integer sectionid);

	// 根据sectionid查询单个部门
	@Select("select * from Sections where sectionId=#{sectionid}")
	public List<Sections> getByID(@Param("sectionid") Integer sectionid);
	
	//修改部门时 查询所属部门
	@Select("select * from Sections where sectionId=#{sid}")
	public Sections getSectionName(@Param("sid") String sid);
	
	//删除部门时把该部门的员工分到未分配部门下
	@Update("UPDATE staffs SET sectionID=-1 WHERE sectionID=#{sectionid}")
	public int updateStaffsSectionID(@Param("sectionid")Integer sectionid);
}
