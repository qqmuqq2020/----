package hr.manage.dao.time;

import hr.manage.entity.Time;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
@Repository
public interface TimeDao {
 //修改时间
	@Update("UPDATE time SET onTime='${onTime}',underTime='${underTime}',afternoontimetop='${afternoontimetop}',afternoontimebelow='${afternoontimebelow}'  WHERE timeID=1 ")
	public int timeUpdate(@Param("onTime")String onTime,@Param("underTime")String underTime,@Param("afternoontimetop")String afternoontimetop,@Param("afternoontimebelow")String afternoontimebelow);
	//查询时间
	@Select("select * from time")
	public List<Time> time();
	
	

}
