package hr.manage.controller.book;
import hr.manage.dao.book.BookDao;
import hr.manage.entity.CwgBookManager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import util.FileUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
/**
 * 导出Execl
 */
@Controller
@RequestMapping("/hrm/book")
public class ExcelBookController {

    @Resource
    private BookDao book;

    @RequestMapping("/demo")
    public void execl(HttpServletResponse response,HttpServletRequest request) throws IOException {
    	
    	List<CwgBookManager> list = book.findBookList();
    	
    	System.out.println(list.size());
//        Integer positionID = (Integer) request.getSession().getAttribute("psid");
        FileUtil.exportExcel(list,"图书信息","图书",CwgBookManager.class,"图书.xls",response);
    }
    
    
}
