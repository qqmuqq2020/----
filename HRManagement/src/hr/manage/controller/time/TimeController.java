package hr.manage.controller.time;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import hr.manage.entity.Time;
import hr.manage.service.time.TimeService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/hrm/time")
public class TimeController {
 
	@Resource
	private TimeService time;
	//修改
	@RequestMapping(value="update")
	public void timeDate(HttpServletResponse response,String onTime,String underTime,String afternoontimetop,String afternoontimebelow)throws IOException{
		PrintWriter pw=null;
		response.setCharacterEncoding("UTF-8");
		try {
			pw=response.getWriter();
			int result=time.timeUpdate(onTime, underTime, afternoontimetop, afternoontimebelow);
			if (result>0) {
				pw.print(1);
			}else {
				pw.print(0);
			}
			} catch (Exception e) {
			// TODO: handle exception
				e.printStackTrace();
		}finally{
			pw.close();
		}
	}
	//查询所有并传到前台
	@RequestMapping(value="list")
	public void timeList(HttpServletResponse response)throws IOException{
		PrintWriter pw=null;
		response.setCharacterEncoding("UTF-8");
		JSONObject json=new JSONObject();
		try {
			pw=response.getWriter();
			List<Time> times=time.time();
			json.put("time", times);
			pw.print(json.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			pw.close();
		}
	}
	
}
