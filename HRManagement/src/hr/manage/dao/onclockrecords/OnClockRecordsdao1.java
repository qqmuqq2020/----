package hr.manage.dao.onclockrecords;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface OnClockRecordsdao1 {
    
	@Select("select count(id) from view_onrecord where createBy=#{id} and checkstatus!=6")
	public int getcountnotsex(@Param("id")Integer id);
	
	
	@Select("select count(id) from view_onrecord where createBy=#{id} and checkstatus=6")
	public int getcounsex(@Param("id")Integer id);
	
	@Select("select count(id) from view_onrecord where createBy=#{id}")
	public int getCountByEmp(@Param("id")Integer id);
}
