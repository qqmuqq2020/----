package hr.manage.service.jurisdiction;

import java.util.List;

import hr.manage.entity.Jurisdiction;

public interface JurisdictionService {
	
	//���Ȩ��
	public int saveJur(Jurisdiction jurisdiction);
	
	//���gid��ѯȨ��
	public List<Jurisdiction> get(String id);
	
	public int deleteJur(String gid);
}
