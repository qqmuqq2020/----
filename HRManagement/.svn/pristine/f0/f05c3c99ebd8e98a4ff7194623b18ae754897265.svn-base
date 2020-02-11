package hr.manage.dao.verify;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionsDao {
	//查询权限
	@Select("select j.functionID from functions f ,jurisdiction j where f.functionID=j.functionID and j.gid=#{gid}")
	public List<Integer> Permission_verification(@Param("gid")String gid);
	@Select("select j.ready2 from functions f ,jurisdiction j where f.functionID=j.functionID and j.gid=#{gid}")
	public List<Integer> Permission_controller(@Param("gid")String gid);
	@Select("select count(functionID) from functions")
	public Integer Permission_count();
}
