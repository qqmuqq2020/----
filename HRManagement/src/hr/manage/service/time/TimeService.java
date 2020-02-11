package hr.manage.service.time;

import hr.manage.entity.Time;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TimeService {

	//修改时间
		public int timeUpdate(@Param("onTime")String onTime,@Param("underTime")String underTime,@Param("afternoontimetop")String afternoontimetop,@Param("afternoontimebelow")String afternoontimebelow);
		//查询所有时间
		public List<Time> time();
}
