package hr.manage.dao.jurisdiction;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import hr.manage.entity.Jurisdiction;
@Repository
public interface JurisdictionDao {
	
	//���Ȩ��
	@Insert("insert into jurisdiction(staffID,functionID,gid,ready2) values(#{staffID},#{functionID},#{gid},#{ready2})")
	public int saveJur(Jurisdiction jurisdiction);
	
	

	@Select("select * from jurisdiction where gid = #{gid}")
	public List<Jurisdiction> getJurisdictions(@Param ("gid") String gid);
	
	@Delete("delete from jurisdiction where gid = #{gid}")
	public int deleteJur(@Param ("gid") String gid);
	
	@Select("select count(staffID) from jurisdiction where staffID = #{staffID}")
	public int getLevel(@Param("staffID") String staffID);
}
