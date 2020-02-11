package hr.manage.dao.cwg_onrecord;

import hr.manage.entity.CwgonRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Cwg_onrecordDao {

    //模糊查询是否存在
    @Select("SELECT * FROM `view_addRecord` WHERE 1=1 and ${str} ")
    public List<CwgonRecord> findIfhave(@Param("str") String str);


    //添加数据
    //1.备份表
    @Insert("INSERT INTO `cwg_onrecord`(bid,uid,createBy,creationDate,statusById)VALUES (#{bid},#{uid},#{createBy},NOW(),1)")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    public int addRecord(CwgonRecord cwgonRecord);

    //2.状态表
    @Insert("INSERT INTO `cwg_onrecore_status`(recoreId,creationDate,checkstatus,createBy)VALUES (${str1})")
    public int addRecore_status(@Param("str1") String str1);
    
    //名字查询是否存在
    @Select("SELECT * FROM `view_onrecord` WHERE 1=1 and ${str} ")
    public List<CwgonRecord> findIfhaveByname(@Param("str") String str);

}
