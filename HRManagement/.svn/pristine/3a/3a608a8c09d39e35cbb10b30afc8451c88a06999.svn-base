package hr.manage.service.impl.time;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hr.manage.dao.time.TimeDao;
import hr.manage.entity.Time;
import hr.manage.service.time.TimeService;

@Service
public class TimeServiceImpl implements TimeService {

	@Resource
	private TimeDao dao;
	//修改时间
	public int timeUpdate(String onTime, String underTime,
			String afternoontimetop, String afternoontimebelow) {
		// TODO Auto-generated method stub
		return dao.timeUpdate(onTime, underTime, afternoontimetop,
				afternoontimebelow);
	}
//查询时间
	public List<Time> time() {
		// TODO Auto-generated method stub
		return dao.time();
	}

}
