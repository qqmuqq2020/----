package hr.manage.controller.book;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import hr.manage.dao.book.BookDao;
import hr.manage.dao.book.PageBookDao;
import hr.manage.entity.CwgBookManager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import util.Page;

import com.alibaba.fastjson.JSONArray;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 图书管理
 * @author 64826
 */
@Controller
@RequestMapping("/hrm/book")
public class BookController extends BaseController {

    @Resource
    private BookDao bookDao;
    @Resource
    private PageBookDao pagedao;
    
    
    
    
    @RequestMapping("/selepsid")
	@ResponseBody
    public Object execl(HttpSession session)  {
		Integer psid=(Integer) session.getAttribute("psid");
		return JSONArray.toJSONString(psid);
	}
    
    
    
    
    /**
     * 查询方法
     *
     * @param index
     * @return
     */
	@RequestMapping(value = "/find", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object getBookAll(@RequestParam(value = "index", required = false) Integer index ,@RequestParam(value = "like", required = false)String like,
    		@RequestParam(value = "clike", required = false)String clike,
    		@RequestParam(value = "pub", required = false)String pub,
    		@RequestParam(value = "snnumber", required = false)String snnumber,
    		HttpSession session) {
    	 if(index==null||index.equals("")){
	            index=1;
	        }
		 if(like==null){
			 like="";
		 }
		 if(clike==null){
			 clike="";
		 }
		 if(pub==null){
			 pub="";
		 }
		 if(snnumber==null){
			 snnumber="";
		 }
		 Integer psid=(Integer) session.getAttribute("psid");
    	Page<CwgBookManager> bookList = pagedao.getCwgBookManagerlistPage(index,like,clike,pub,snnumber,psid);
        return JSONArray.toJSONString(bookList);
    }

    /**
     * 添加数据方法
     * @param
     */
    @RequestMapping(value = "/addbook", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object bookAdd(String name, String serialNumber, String publisher, String author,
                          Date creationDate, String total, String price, String detail, HttpSession session) {
        CwgBookManager cwgBookManager = new CwgBookManager();
        String fileName = UUID.randomUUID().toString().substring(0, 4);
        cwgBookManager.setNumber(fileName);
        cwgBookManager.setName(name);
        cwgBookManager.setSerialNumber(serialNumber);
        cwgBookManager.setPublisher(publisher);
        cwgBookManager.setAuthor(author);
        Integer uid = (Integer) session.getAttribute("uid");
        cwgBookManager.setCreateBy(uid);
        cwgBookManager.setCreationDate(creationDate);
        cwgBookManager.setTotal(Integer.parseInt(total));
        cwgBookManager.setPrice(Double.valueOf(price));
        cwgBookManager.setDetail(detail);
        int add = bookDao.findBookAdd(cwgBookManager);

        return JSONArray.toJSONString(add);
    }

    /**
     * 删除方法
     * @param id
     */
    @RequestMapping(value = "/deletebook",method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object  deleteBook(Integer id) {
        int i = bookDao.deletecwgRecord(id);

        if (i == 0){
           Integer  d= bookDao.deleteBook(id);
            return JSONArray.toJSONString(d);
        }
        return JSONArray.toJSONString(0);
    }

    /**
     * 根据ID修改图书信息
     * @return
     */
    @RequestMapping(value = "/updateBook",method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object updateBook(String name, String serialNumber, String publisher, String author,
                             String total, String price, String detail,Integer id) {
        CwgBookManager cwgBookManager = new CwgBookManager();
        cwgBookManager.setName(name);
        cwgBookManager.setSerialNumber(serialNumber);
        cwgBookManager.setPublisher(publisher);
        cwgBookManager.setAuthor(author);
        cwgBookManager.setTotal(Integer.parseInt(total));
        cwgBookManager.setPrice(Double.valueOf(price));
        cwgBookManager.setDetail(detail);
        cwgBookManager.setId(id);
        int book = bookDao.updateBook(cwgBookManager);
        System.out.println(book);
        return JSONArray.toJSONString(book);
    }

    /**
     * 根据ID查询图书
     */
    @RequestMapping(value = "/findById",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object getfindById(Integer id) {
        List<CwgBookManager> book = bookDao.findBookById(id);
        return JSONArray.toJSONString(book.get(0));
    }

}
