package hr.manage.dao.cwg_client;

import hr.manage.entity.CwgClient;
import hr.manage.entity.Staffs;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;



public interface Cwg_clientDao {
	
	
	
	/**
	 * 获取所有客户信息
	 * 
	 * @return
	 */
	@Select("select * from cwg_client ORDER BY id DESC")
	public List<CwgClient> getlist();
	
	/**
	 * 
	 * 获取当前个创建客户数量
	 * @return
	 */
	@Select("select count(createBy) from cwg_client where createBy = #{createBy}")
	public int getCreatMAX(@Param("createBy") int createBy);
	
	/**
	 * 获取所有创建人的客户
	 * @param createBy
	 * @return
	 */
	@Select("select * from cwg_client where createBy=#{createBy} ORDER BY id DESC")
	public List<CwgClient> getListbyCreate(@Param("createBy") Integer createBy);
	
	/**
	 * 
	 * 获取指定客户
	 * @return
	 */
	@Select("select * from cwg_client where id=#{id}")
	public CwgClient getCwgClient(@Param("id") Integer id);
	
	
	/**
	 * 
	 * 添加客户
	 * @param client
	 * @return
	 */
	@Insert("insert into cwg_client(userName,createBy,creationDate,detail,reserve) values(#{userName},#{createBy},#{creationDate},#{detail},#{reserve})")
	public int insertCwgClient(CwgClient client);
	
	
	/**
	 * 
	 * 修改客户信息
	 * @param client
	 * @return
	 */
	@Update("update cwg_client set userName=#{userName},createBy=#{createBy},creationDate=#{creationDate},detail=#{detail} where id=#{id}")
	public int updataCwgClient(CwgClient client);
	
	
	
	/**
	 * 
	 * 获取公司发行员下的所有客户
	 * 
	 * @param reserve
	 * @return
	 */
	@Select("select * from cwg_client where reserve=#{reserve} ORDER BY id DESC")
	public List<CwgClient> getListbyreserve(@Param("reserve") String reserve);
	
	/**
	 * 删除客户
	 * @param id
	 * @return
	 */
	@Delete("delete from cwg_client where id=#{id}")
	
	public int delCwgClient(@Param("id") Integer id);
	
	@Insert("insert into cwg_client(userName,createBy,creationDate,detail,reserve) values(#{userName},#{createBy},#{creationDate},#{detail},#{reserve})")
	@Options(useGeneratedKeys = true,keyProperty = "id",keyColumn="id")
	public int insert111(CwgClient cwgClient);

	@Select("SELECT uid,COUNT(id) as id FROM cwg_onrecord WHERE uid= #{id}")
	@ResultType(Map.class)
	@MapKey("uid")
	public Map<Integer, Map<String, Long>> getClentDel(@Param("id") int id);
	
	@Select("select * from cwg_client where createBy=#{createBy} and userName = #{userName}")
	public CwgClient ifname(@Param("createBy") int createBy,@Param("userName") String userName);
	
}
