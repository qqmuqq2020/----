package hr.manage.controller.CwgonRecord;

import java.io.IOException;
import java.util.List;

import hr.manage.dao.CwgonRecord.CwgonRecordMapper;
import hr.manage.entity.CwgonRecord;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import util.FileUtil;
import util.Page;

@Controller
@RequestMapping("/hrm/CwgonRecord")
public class Ex {

	@Resource
	private CwgonRecordMapper mapper;
	
	@RequestMapping("/outs1")
    public void execl(@RequestParam(value = "book",required = false) String book,
    		@RequestParam(value = "status",required = false) String status,
    		@RequestParam(value = "client",required = false) String client,
    		HttpServletResponse response,HttpSession session) throws IOException {
		
		Integer psid=(Integer) session.getAttribute("psid");
		List<CwgonRecord> list=null;
		
		if(book==null||book==""){
			book="";
		}
		if(client==null||client==""){
			client="";
		}
		if(psid==0){
			if(status==null||status.equals("")){
				list=mapper.getlist1("%"+book+"%","%"+client+"%");
			}else{
				list=mapper.getlist("%"+book+"%","%"+client+"%",status);
			}
				
		}else if(psid==2){
			String name=(String)session.getAttribute("name");
			Integer uid=(Integer)session.getAttribute("uid");
			String uids=mapper.getstaffslist(name);
			String asd=uid+"";
			if(uids==null||uid.equals("")){
				uids+=asd;
			}else{
				uids+=","+asd;
			}	
			
			
			System.out.println(uids);
			
			 list=mapper.getlistByfx(uids);
		
		}else if(psid==3){
			 Integer uid=(Integer)session.getAttribute("uid");
			 String uuids=uid+"";
			 System.out.println(uuids);
			 list=mapper.getlistByfx(uuids);
		 }
		 
        FileUtil.exportExcel(list,"备案信息","备案",CwgonRecord.class,"备案.xls",response);
       
    }
	
	@RequestMapping("/selepsid")
	@ResponseBody
    public Object execl(HttpSession session)  {
		Integer psid=(Integer) session.getAttribute("psid");
		return JSONArray.toJSONString(psid);
	}
	@RequestMapping(value="/selecount",method=RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public Object selecount(){
		Integer count=mapper.seleCount();
		
		return JSONArray.toJSONString(count);
	}
	
}
