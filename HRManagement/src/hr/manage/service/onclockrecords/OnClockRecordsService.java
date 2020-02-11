package hr.manage.service.onclockrecords;

import hr.manage.entity.Manage;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface OnClockRecordsService {
	//图形报表模糊查询加分页接口
	public List<Manage> getPhoto(String sectionid, String startTime,
			String endTime);
	//表格报表模糊查询加分页接口
	public Map<String, Object> findP(int pageIndex, int pageSizes,
			String staffname, String sectionid, String startTime, String endTime)
			throws SQLException;
	//导出表格报表

//条件查询并导出Excel
	public List<Manage> getExcel(String minTime, String maxTime,
			String sectionName, String staffName);
}
