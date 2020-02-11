package hr.manage.dao.onclockrecords;

import hr.manage.entity.Manage;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface OnClockRecordsDao {
	// 图形报表模糊查询加分页
	@Select("select * from view_records where sectionid like '%${sectionid}%' and colckTime BETWEEN #{startTime} AND #{endTime}")
	public List<Manage> getPhoto(@Param("sectionid") String sectionid,
			@Param("startTime") String startTime,
			@Param("endTime") String endTime);
	//表格报表模糊查询加分页
	public Map<String, Object> findP(int pageIndex, int pageSizes,
			String staffname, String sectionid, String startTime, String endTime)
			throws SQLException;

	// 导出Excel表格
	@Select("select s.staffID,s.staffName,ss.sectionName,o.colckTime,o.colckType from staffs s,sections ss,onclockrecords o where s.sectionID=ss.sectionID and o.staffID=s.staffID and o.colckTime BETWEEN \"${minTime}\" and \"${maxTime}\" and ss.sectionID=\"${sectionName}\" and s.staffName LIKE '%${staffName}%'")
	public List<Manage> getExcel(@Param("minTime") String minTime,
			@Param("maxTime") String maxTime,
			@Param("sectionName") String sectionName,
			@Param("staffName") String staffName);
}