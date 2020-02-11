package hr.manage.dao.CwgonRecord;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import hr.manage.entity.CwgonRecord;

public interface CwgonRecordMapper {
	
	//根据成交状态查看对应的备案信息
	public CwgonRecord getCwgonRecordByStatus(@Param("statusById") Integer satusById);
	//添加备案
	public Integer addRecord(CwgonRecord CwgonRecord);
	//删除备案
	public Integer delRecord(@Param("id") Integer id);
	public Integer delRecordstatus(@Param("id") Integer id);
	public Integer delRecordTime(@Param("id") Integer id);
	public Integer seleRecord(@Param("id") Integer id);
	//查询总记录数
	public Integer seleCount();
	//延期备案
	public Integer residueRecord(@Param("id") Integer id,@Param("residueNumber") Integer residueNumber,@Param("uid") Integer uid);
	public Integer residueRecord2(@Param("id") Integer id,@Param("residueNumber") Integer residueNumber,@Param("uid") Integer uid);
	//手动中止备案
	public Integer outRecord(@Param("id") Integer id,@Param("uid") Integer uid);
	public Integer outTime(@Param("id") Integer id,@Param("uid") Integer uid);
	//同意审核	
	public Integer consentRecord(@Param("id") Integer id,@Param("uid") Integer uid);
	public Integer consentTime(@Param("id") Integer id,@Param("uid") Integer uid);
	public Integer updateOnRecord(@Param("id") Integer id,@Param("baid") String baid);
	//拒绝审核
	public Integer refustRecord(@Param("id") Integer id,@Param("uid") Integer uid);
	//查询所有备案
	public List<CwgonRecord> getlist1(@Param("book") String book,@Param("client") String client);
	public List<CwgonRecord> getlist(@Param("book") String book,@Param("client") String client,@Param("status") String status);
	public List<CwgonRecord> getlistByfx(@Param("uid")String uid);
	//查询所有备案9klklolllk
	public String getstaffslist(@Param("sid") String sid);
	
	
}
