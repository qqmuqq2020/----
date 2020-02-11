package hr.manage.service.impl.onclockrecords;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hr.manage.filter.JDBC;
import com.mysql.jdbc.Connection;

import hr.manage.dao.onclockrecords.OnClockRecordsDao;
import hr.manage.entity.Manage;
import hr.manage.service.onclockrecords.OnClockRecordsService;

@Service
@Transactional
public class OnClockRecordsServiceImpl implements OnClockRecordsService {
	//通过JDBC连接池实现分页查询数据
	JDBC Jdbc = new JDBC();

	//员工报表接口
	@Autowired
	private OnClockRecordsDao oDao;
	//图形报表模糊分页实现
	public List<Manage> getPhoto(String sectionid, String startTime,
			String endTime) {
		// TODO Auto-generated method stub
		return oDao.getPhoto(sectionid, startTime, endTime);
	}
	//表格报表模糊分页实现 start
	@Override
	public Map<String, Object> findP(int pageIndex, int pageSizes,
			String staffname, String sectionid, String startTime,
			String endTime) throws SQLException {
		// TODO Auto-generated method stub
		int count = 0;//总页数
		Connection connection = Jdbc.getConnection();//jdbc连接
		Map<String, Object> map = new HashMap<String, Object>();//Map集合接收
		//拼装查询字符串
		final String fen = "view_records where staffName like '" + staffname+ "' and sectionID like '"+sectionid+"' and colckTime BETWEEN '"+startTime+"' AND '"+endTime+"'";
		//List集合存报表数据
		List<Manage> list = new ArrayList<Manage>();
		try {
			CallableStatement call = connection.prepareCall("CALL sp_page(?,?,'*',?,' order by sectionid asc')");
			call.setInt(1, pageIndex);
			call.setInt(2, pageSizes);
			call.setString(3, fen);
			ResultSet rs = call.executeQuery();
			while (rs.next()) {
				Manage manage=new Manage();
				manage.setStaffid(rs.getString("staffid"));
				manage.setStaffname(rs.getString("staffname"));
				manage.setSectionname(rs.getString("sectionname"));
				manage.setColcktime(rs.getString("colcktime"));
				manage.setColcktype(rs.getInt("colcktype"));
				list.add(manage);
			}
			if (call.getMoreResults() == true) {
				rs = call.getResultSet();
				while (rs.next()) {
					//获取数据库查询的第二个结果 总条数
					count = rs.getInt(1);
				}
			}
			//数据存入Map集合中
			map.put("manage", list);
			map.put("total", count);
			return map;
		} finally {
			//关闭连接
			Jdbc.Close();
		}
	}
	//表格报表模糊分页实现 end

	//导出表格报表实现
	@Override
	public List<Manage> getExcel(String minTime, String maxTime,
			String sectionName, String staffName) {
		// TODO Auto-generated method stub
		return oDao.getExcel(minTime, maxTime, sectionName, staffName);
	}
}
