package hr.manage.controller.CwgonRecord;

import java.io.IOException;
import java.rmi.server.UID;
import java.text.NumberFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.sun.xml.internal.bind.v2.model.core.ID;

import util.BaseController;
import util.FileUtil;
import util.Page;

import hr.manage.dao.CwgonRecord.CwgonRecordDao;
import hr.manage.dao.CwgonRecord.CwgonRecordMapper;
import hr.manage.dao.onclockrecords.OnClockRecordsdao1;
import hr.manage.entity.CwgBookManager;
import hr.manage.entity.CwgonRecord;


@Controller
@RequestMapping("/hrm/CwgonRecord")
public class CwgonRecordController extends BaseController {
	
	@Resource
	private CwgonRecordMapper mapper;
	@Resource
	private CwgonRecordDao dao ;
	
	//查询所有备案信息
	@RequestMapping(value="/onRecordList",method=RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public Object getRecordList( @RequestParam(value = "index",required = false) 
									Integer index,
								@RequestParam(value = "like",required = false) 
										String like,
								@RequestParam(value = "clike",required = false)  
									String clike,
									@RequestParam(value = "pub",required = false)  
									String pub,
									@RequestParam(value = "snnumber",required = false)  
									String snnumber,
									HttpSession session){
		 
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
		 if(snnumber==null||snnumber==""){
			 snnumber="1";
		 }
			/*list= dao.getRecordBystatus(index, clike, like,0);*/
		 
			try{
				Integer psid=(Integer) session.getAttribute("psid");
				if(psid==0){
					
					Page<CwgonRecord> list=dao.getRecordBystatus(index, clike, like,pub,0,psid);
				 return JSONArray.toJSONString(list);
				}else if(psid==2){
					String name=(String)session.getAttribute("name");
					Integer uid=(Integer)session.getAttribute("uid");
					String uids=mapper.getstaffslist(name);
					/*StringBuilder uuids=new StringBuilder(uids);
					if(uids==null||uid.equals("")){
						uuids.append(uid);
					}else{
						uuids.append(","+uid);
					}*/	
					Page<CwgonRecord> list=null;
					if(snnumber.equals("1")){
						 list=dao.getRecordlistPageByfxy(index, clike, like,pub, uid+"",snnumber,psid);
					}else if(snnumber.equals("2")){
						 list=dao.getRecordlistPageByfxy(index, clike, like,pub, uids,snnumber,psid);
					}
					
					  return JSONArray.toJSONString(list);
				}else if(psid==3){
					 Integer uid=(Integer)session.getAttribute("uid");
					 Page<CwgonRecord> list=dao.getRecordBystatus(index, clike, like,pub,uid,psid);
					 return JSONArray.toJSONString(list);
				 }
			}catch (Exception e) {
				System.err.println(e.getMessage());
				// TODO: handle exception
			}
			
		
		
		return JSONArray.toJSONString(null);
	}
	
	@Resource
	OnClockRecordsdao1 dao5;
	
	@RequestMapping(value="/onRecordListNew",method=RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public Object getRecordListNew( @RequestParam(value = "index",required = false) 
									Integer index,
								@RequestParam(value = "like",required = false) 
										String like,
								@RequestParam(value = "clike",required = false)  
									String clike,
									@RequestParam(value = "pub",required = false)  
									String pub,
									@RequestParam(value = "snnumber",required = false)  
									String snnumber,
									HttpSession session){
		 
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
		 if(snnumber==null||snnumber==""){
			 snnumber="1";
		 }
			/*list= dao.getRecordBystatus(index, clike, like,0);*/
		 
	
				Integer psid=(Integer) session.getAttribute("psid");
				
				//备案总目统计成功率
				 Integer uid = (Integer)session.getAttribute("uid");
				
				int a=dao5.getcounsex(uid);
				int b=dao5.getcountnotsex(uid);
					Page<CwgonRecord> list=dao.getRecordBystatusNew(index, clike, like,pub,0,psid);
					
					if(b!=0)
					{
						        NumberFormat numberFormat = NumberFormat.getInstance();  
						         
						              // 设置精确到小数点后2位  
						        
						             numberFormat.setMaximumFractionDigits(2);  
						        
						              String result = numberFormat.format((float) a / (float) b * 100);  
						        
						              System.out.println("num1和num2的百分比为:" + result + "%");  
						System.out.println(a/b);
					list.setMessage("当前用户成功率为:【"+result+"%】【未完成"+b+"】 【成功"+a+"】");
					}else {
						list.setMessage("成功率为:【0.00%】 没有备案数据");
					}
					
				 return JSONArray.toJSONString(list);
				
		
		
	}
	
	//中止
	@RequestMapping(value="/out",method=RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public Object outonRecord(@RequestParam(value = "id") int id,HttpSession session){
		Integer uid=(Integer)session.getAttribute("uid");
		int i=mapper.outRecord(id,uid);
		
		 return JSONArray.toJSONString(i);
	}
	//同意审核
	@RequestMapping(value="/consent",method=RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public Object consentonRecord(@RequestParam(value = "id") int id,HttpSession session){
		Integer uid=(Integer)session.getAttribute("uid");
		int i=mapper.consentRecord(id,uid);
		int ca=0;
		if(i>0){
			
			int j=mapper.consentTime(id,uid);
			String baid="b"+System.currentTimeMillis();
			int uidd=RandomUtils.nextInt(1000, 9999);		
			String cuidS=baid.substring(0, 9)+uidd;
			
			ca=mapper.updateOnRecord(id, cuidS);
		}
		
		 return JSONArray.toJSONString(ca);
	}
	//拒绝审核
	@RequestMapping(value="/refust",method=RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public Object refustonRecord(@RequestParam(value = "id") int id,HttpSession session){
		Integer uid=(Integer)session.getAttribute("uid");
		int i=mapper.refustRecord(id,uid);
		
		 return JSONArray.toJSONString(i);
	}
	//延期
	@RequestMapping(value="/residue",method=RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public Object residueRecord(@RequestParam(value = "id") Integer id,
			@RequestParam(value = "residueNumber")Integer residueNumber,HttpSession session){
		
		Integer uid=(Integer)session.getAttribute("uid");
		int i=0;
		if(residueNumber==2){
			Integer residueNumber1 =residueNumber-1;
			i=mapper.residueRecord(id,residueNumber1,uid);
		}
		if(residueNumber==1){
			Integer residueNumber1 =residueNumber-1;
			 i=mapper.residueRecord2(id,residueNumber1,uid);
		}
		
		
		
		 return JSONArray.toJSONString(i);
	}
	
	
	//删除
	@RequestMapping(value="/del",method=RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public Object delonRecord( Integer id){
			
		int i=mapper.seleRecord(id);
		if(i==0){
			int j=mapper.delRecord(id);
			if(j>0){
				mapper.delRecordstatus(id);
				mapper.delRecordTime(id);
				return JSONArray.toJSONString(j);
			}
		}
		return JSONArray.toJSONString(0);
		
		
		 
	}
	
	
	
}
