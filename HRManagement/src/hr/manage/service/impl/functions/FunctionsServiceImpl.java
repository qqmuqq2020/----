package hr.manage.service.impl.functions;

import java.util.List;

import javax.annotation.Resource;

import hr.manage.dao.functions.FunctionsDao;
import hr.manage.entity.Functions;
import hr.manage.service.functions.FunctionsService;

import org.springframework.stereotype.Service;

@Service
public class FunctionsServiceImpl implements FunctionsService {

	@Resource
	private FunctionsDao fDao;

	public List<Functions> findFunctions() {
		// TODO Auto-generated method stub
		return fDao.findFunctions();
	}

}
