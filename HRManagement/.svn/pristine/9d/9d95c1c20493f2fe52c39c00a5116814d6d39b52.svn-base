package hr.manage.dao.System_examine;

import hr.manage.entity.System_Review;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Stm_examineDao {

    //查询到期备案ID
    @Select("SELECT view_onrecord.`id` AS recoreId,\r\n" + 
    		"view_onrecord.`creationDate`AS creationDate ,\r\n" + 
    		"checkstatus,\r\n" + 
    		"view_onrecord.`remainingTime`AS Out_Time\r\n" + 
    		"FROM view_onrecord \r\n" + 
    		"INNER JOIN \r\n" + 
    		"(SELECT CASE residueNumber WHEN 2 THEN 120 WHEN 1 THEN 240 \r\n" + 
    		"ELSE 360 END AS time1,recoreId FROM cwg_onrecore_times)AS bb\r\n" + 
    		"ON view_onrecord.id=bb.recoreId\r\n" + 
    		"WHERE checkstatus=3 AND ABS(remainingTime)>=time1")
    public List<System_Review> findRecoreId();
    //修改状态表中的状态
    @Update("UPDATE cwg_onrecore_status SET\n" +
            "checkstatus=7,timeout=NOW()\n" +
            "WHERE recoreId IN(${IDS})\n" +
            "AND checkstatus IN(\n" +
            "SELECT check_status FROM \n" +
            "(SELECT MAX(checkstatus) AS check_status FROM cwg_onrecore_status GROUP BY recoreId HAVING recoreId IN(${IDS}) ) cc\n" +
            ")")
    public int UpdateState(@Param("IDS") String IDS);

}
