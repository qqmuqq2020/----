package hr.manage.dao.book;

import java.util.List;

import hr.manage.entity.CwgBookManager;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
@Repository
public interface BookDao {

	/**
	 * 查询book表数据
	 * @return
	 */
	@Select("SELECT * FROM cwg_bookmanager")
	public List<CwgBookManager> findBookList();
	
	@Select("SELECT * FROM cwg_bookmanager where name like '%${name}%'")
	public List<CwgBookManager> findBookListByName(@Param("name")String name);
	
	//查看是否备案
	@Select("SELECT c.`id`,w.`bid` FROM cwg_bookmanager c,cwg_onrecord w WHERE c.`id` = w.`bid`")
	public Integer deleteList(Integer id);
	/**
	 * 添加book表
	 */
	@Insert("INSERT INTO cwg_bookmanager(number,name,serialNumber,publisher,createBy,author,creationDate,total,price,detail)values(#{number},#{name},#{serialNumber},#{publisher},#{createBy},#{author},NOW(),#{total},#{price},#{detail})")
	public int findBookAdd(CwgBookManager bookManager);

	/**
	 * 删除图书
	 */
	@Delete("delete from cwg_bookmanager where id = #{id}")
	public int deleteBook(@Param("id")Integer id);
	
	//查看备案id是否和传入的id相同
	@Select("SELECT COUNT(1) FROM cwg_onrecord WHERE bid = #{id}")
	public int deletecwgRecord(@Param("id")Integer id);
	
	//修改图书
	@Update("update cwg_bookmanager set name=#{name},serialNumber=#{serialNumber},publisher=#{publisher},author=#{author},total=#{total},price=#{price},detail=#{detail} where id = #{id}")
	public int updateBook(CwgBookManager book);
	
	//根据id查询图书
	@Select("SELECT * FROM cwg_bookmanager WHERE id=#{id}")
	public List<CwgBookManager> findBookById(@Param("id")Integer id);
	
	//模糊查询
	@Select("SELECT * FROM cwg_bookmanager WHERE NAME LIKE '%${str}%' ")
	public List<CwgBookManager> findBookListByString(@Param("str") String str);

}
