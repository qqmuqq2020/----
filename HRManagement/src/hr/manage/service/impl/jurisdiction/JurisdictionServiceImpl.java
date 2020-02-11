package hr.manage.service.impl.jurisdiction;

import java.util.List;

import javax.annotation.Resource;

import hr.manage.dao.jurisdiction.JurisdictionDao;
import hr.manage.entity.Jurisdiction;
import hr.manage.service.jurisdiction.JurisdictionService;

import org.springframework.stereotype.Service;

@Service
public class JurisdictionServiceImpl implements JurisdictionService {
	@Resource
	private JurisdictionDao juDao;

	
	public int saveJur(Jurisdiction jurisdiction) {

		return juDao.saveJur(jurisdiction);
	}

	public List<Jurisdiction> get(String id) {
		// TODO Auto-generated method stub
		return juDao.getJurisdictions(id);
	}

	public int deleteJur(String gid) {
		// TODO Auto-generated method stub
		return juDao.deleteJur(gid);
	}

}
