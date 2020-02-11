package hr.manage.controller.financial;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import hr.manage.dao.CwgonRecord.CwgonRecordDao;
import hr.manage.dao.CwgonRecord.CwgonRecordMapper;
import hr.manage.dao.financial.financialDao;
import hr.manage.dao.financial.financialEXDao;
import hr.manage.entity.CwgonRecord;
import hr.manage.entity.View_Client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import util.FileUtil;
import util.Page;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

@Controller
@RequestMapping("/hrm/financial")
public class financialController {
	
	@Resource
	private financialDao dao ;
	
	@Resource
	private financialEXDao fdao;
	
	@RequestMapping(value="/financialList",method=RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public Object getRecordList( @RequestParam(value = "index",required = false) 
									String index,
								@RequestParam(value = "bookname",required = false) 
										String bookname,
								@RequestParam(value = "checkstatus",required = false)  
	String checkstatus,String clike,String like){
		 if(index==null||index==""){
	            index="1";
	        }
		
		Page<CwgonRecord> list=dao.getRecordlistPage(Integer.parseInt(index),clike,like);
		
		return JSONArray.toJSONString(list);
	}
	@RequestMapping(value="/outEX")
	public void outEX(HttpServletResponse response,String clike,String like){
		
		List<CwgonRecord> list  = new ArrayList<>();
		like ="%"+like+"%";
		try{
		if(clike==null||clike==""){
			 list = fdao.outEXByall(like);
		}else{
			Integer l = Integer.parseInt(clike);
			
			 list = fdao.outEXByClike(l, like);
		}
		}catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		FileUtil.exportExcel(list, "财务信息", "财务信息", CwgonRecord.class, "财务信息.xls", response);
		
	}
	
	@RequestMapping(value="/Complete",method=RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public String Complete(int id){
		int i =-1;
		try {
			i= fdao.Complete(id);
			if(i!=-1){
				String orderNumber = "";
				Integer num = fdao.getNum()+1;
				
				if(num.toString().length()<=8){
					orderNumber = String.format("c%08d",num);
				}else{
					orderNumber="c"+num;
				}
				fdao.setorderNum(id, orderNumber);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
			return JSONArray.toJSONString(i);
		}
		return JSONArray.toJSONString(i);
	}
	
}
