package hr.manage.dao.positions;

import hr.manage.entity.Positions;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
@Repository
public interface PositionsDao {
	//��ѯ����ְλ
	@Select("select positionID,positionName from positions")
	public List<Positions> findAll();
	
}
