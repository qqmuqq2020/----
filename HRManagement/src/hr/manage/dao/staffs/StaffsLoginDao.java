package hr.manage.dao.staffs;

import hr.manage.entity.Staffs;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffsLoginDao {
	
	
	//登录
	@Select("select id,staffID,staffPassword,gid,staffName,positionID from Staffs where staffID =#{staid} ")
	public Staffs userLogin(@Param("staid") String name);

	
	
	//添加
	@Insert("insert into staffs(staffID,staffName,staffPassword,positionID,birthday,picture,nowAddress,idcardno,contactway,sectionid,gid,ready2)"
			+ " values(#{staffID},#{staffName},#{staffPassword},#{positionID},#{birthday},#{picture},#{nowAddress},#{idCardNo},#{contactWay},#{sectionID},#{gid},#{ready2})")
	public int saveStaff(Staffs staffs);
	// 查询最大id
	@Select("select max(id) from staffs")
	public int selectID();

	// 查询单个员工信息
	@Select("select * from view_staff where id=#{id}")
	public Staffs getStaffsById(@Param("id") Integer id);
	
	
	@Select("select * from staffs where id=#{id}")
	public Staffs getStaffByIdT(@Param("id") Integer id);
	// 查询部门人员
	@Select("select * from view_staff where positionID=#{positionID}")
	public List<Staffs> getStaffsBypositionID(@Param("positionID") Integer positionID);
	//根据id查询
	@Select("select a.*,b.staffName 'staffnameup' from staffs a  left join staffs b on a.ready2 = b.staffID WHERE a.id = #{id}")
	public List<Staffs> getbyid(@Param("id") Integer id);

	// 删除
	@Delete("delete from Staffs where id=#{id}")
	public int delStaffsById(@Param("id") int id);
	
	//修改
	@Update("update staffs set staffName=#{staffName},staffPassword=#{staffPassword},birthday=#{birthday},picture=#{picture},nowAddress=#{nowAddress},idCardNo=#{idCardNo},contactWay=#{contactWay},sectionID=#{sectionID} where id=#{id}")
	public int updateStaffs(Staffs staffs);
	
	//详细信息
	@Select("select s.staffID,s.picture,s.staffName,s.icID,s.idCardNo,s.birthday,s.staffPassword,s.nowAddress,s.contactWay,p.positionName from staffs s,positions p WHERE s.positionID=p.positionID and s.id = #{id}")
	public List<Staffs> selectStaffs(@Param("id")int id);
	
	//判断电话号重复
	@Select("select * from staffs where idCardNo = #{card}")
	public List<Staffs> getbyCard(@Param("card") String card);
	
	//判断身份证重复
	@Select("select * from staffs where contactWay = #{contactWay}")
	public List<Staffs> getbycontactWay(@Param("contactWay")String contactWay);
	
	//添加发卡功能
	@Update("update staffs set icID='${icID}' where id = #{id}")
	public int updateIC(@Param("icID") String icID,@Param("id")int id);
	
	//
	@Select("select * from view_staff where icID=#{icID}")
	public List<Staffs> getbyicID(@Param("icID") String icID);
	
	//获取是否有备案或客户
	@Select("SELECT createBy FROM `cwg_client` WHERE createBy = #{id} GROUP BY id Union SELECT createBy FROM cwg_onrecord WHERE createBy= #{id}  GROUP BY id")
	public String getdel(@Param("id") int id);
}
