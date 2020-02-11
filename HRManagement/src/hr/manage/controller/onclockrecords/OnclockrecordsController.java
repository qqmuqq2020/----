package hr.manage.controller.onclockrecords;

import hr.manage.entity.Manage;
import hr.manage.service.onclockrecords.OnClockRecordsService;

import java.io 

.IOException;
import java.io 

.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping(value="/hrm/onclockrecords")
public class OnclockrecordsController {
	//页面每页显示15条数据
	private int pageSize = 15;
	//接收总条数
	private int total;
	//员工表接口
	@Resource
	private OnClockRecordsService onService;
	//表格报表控制层
	@RequestMapping(value="findtable",method=RequestMethod.POST)
	public void findtable(HttpServletResponse response,int pageIndex, String staffname,String sectionid,String startTime,String endTime) throws IOException, SQLException {
		response.setCharacterEncoding("UTF-8");//设置编码格式
		PrintWriter pWriter=response.getWriter();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        Calendar cal = Calendar.getInstance();//定义Calendar时间格式
        cal.setTime(new Date());//获取当天时间
        cal.add(Calendar.MONTH, 0);//获取当前月 -1为上一月 0为当月 1为下一月
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));//获取当月的月初
        Calendar last = Calendar.getInstance();//设置当月的最后一天
        last.set(Calendar.DAY_OF_MONTH, last.getActualMaximum(Calendar.DAY_OF_MONTH)); 
        //如何前台传的起始时间为空的话 设置为当月月初
        if (startTime==null||startTime.equals("本月月初")||endTime.equals("")) {
			startTime=sdf.format(cal.getTime());
		}
        //如何前台传的最终时间为空的话 设置为当月月末
        if (endTime==null||endTime.equals("本月月末")||endTime.equals("")) {
			endTime=sdf.format(last.getTime());
		}
        //模糊查询 如果员工姓名为空的话 默认查询当月全部
		if(staffname!=null&&!staffname.equals("")){
			staffname="%"+staffname+"%";
		}else{
			staffname="%%";
		}
		//模糊查询 如果部门ID为空的话 默认查询当月全部
		if(!sectionid.equals("")&&!sectionid.equals("0")){
			sectionid="%"+sectionid+"%";
		}else{
			sectionid="%%";
		}
		//Map集合接收
		Map<String, Object> list = onService.findP(pageIndex, pageSize, staffname,sectionid,startTime,endTime);
		//获取总条数
		total = (Integer) list.get("total");
		//使用list获取Map集合中的数据
		List<Manage> manages = (List<Manage>) list.get("manage");
		//使用JSONObject传值到前台
		JSONObject js=new JSONObject();
		js.put("total", total);
		js.put("list",manages);
		pWriter.print(js.toString());
		pWriter.close();
		
	}
}