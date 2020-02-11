package hr.manage.dao.view_Client;

import hr.manage.entity.View_Client;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface View_ClientDao {

	
	
	
	@Select("select * from view_client where userName LIKE #{like}")
	public List<View_Client> getAll(@Param("like") String like);
	
	
	/**
	 * 获取所有创建人的客户
	 * @param createBy
	 * @return
	 */
	@Select("select * from view_client where createBy=#{createBy}")
	public List<View_Client> getListbyCreateBy(@Param("createBy") Integer createBy);
	
	/**
	 * 获取所有公司发行员下的客户
	 * @param createBy
	 * @return
	 */
	@Select("select * from view_client where positionID=#{positionID} and reserve=#{reserve} ")
	public List<View_Client> getListbypositionID(@Param("positionID") Integer positionID,@Param("reserve") String reserve);
	

}
