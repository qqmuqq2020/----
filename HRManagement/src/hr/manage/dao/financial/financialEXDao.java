package hr.manage.dao.financial;

import hr.manage.entity.CwgonRecord;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface financialEXDao {
	
	@Select("SELECT *,CASE checkstatus WHEN 3 THEN '待收尾款' WHEN 6 THEN '完成' END status from view_onrecord WHERE (userName LIKE #{like} AND checkstatus = 3) or (userName LIKE #{like} and checkstatus = 6)")
	public List<CwgonRecord> outEXByall(@Param("like")String like);
	
	@Select("SELECT *,CASE checkstatus WHEN 3 THEN '待收尾款' WHEN 6 THEN '完成' END status  from view_onrecord WHERE userName LIKE #{like} AND checkstatus = #{Clike} ")
	public List<CwgonRecord> outEXByClike(@Param("Clike")int Clike,@Param("like")String like);

	@Update("UPDATE cwg_onrecore_status set checkstatus = 6 WHERE recoreId = #{id}")
	public int Complete(@Param("id") int id);
	
	@Update("UPDATE cwg_onrecord set orderNumber = #{orderNumber}  WHERE id = #{id}")
	public int setorderNum(@Param("id") int id,@Param("orderNumber") String orderNumber);
	@Select("SELECT count(id) from view_onrecord where checkstatus = 6")
	public int getNum();

	
//	<insert id="consentTime">
//	INSERT INTO cwg_onrecore_times(recoreId,creationDate,residueNumber,remainingTime) 
//	VALUES(#{id},NOW(),2,120) 
//</insert>
}
