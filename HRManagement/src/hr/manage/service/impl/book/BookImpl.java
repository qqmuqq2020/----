package hr.manage.service.impl.book;
import java.util.List;
import hr.manage.entity.CwgBookManager;
import org.springframework.stereotype.Service;

import hr.manage.dao.book.BookDao;

import javax.annotation.Resource;

@Service
public class BookImpl {

	@Resource
	private BookDao bookDao;
	/**
	 * 查询方法
	 * @return
	 */
	public List<CwgBookManager> findBookList(){
		List<CwgBookManager> findBookList = bookDao.findBookList();
		if(findBookList != null) {
		return findBookList;
		}else {
			return null;
		}
	}

	/**
	 * 添加方法
	 */
	public void findBookAdd(CwgBookManager bookManager){
		 bookDao.findBookAdd(bookManager);
	}


}
