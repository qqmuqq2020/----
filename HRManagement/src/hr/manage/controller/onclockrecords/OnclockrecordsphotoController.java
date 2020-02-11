package hr.manage.controller.onclockrecords;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;

import hr.manage.entity.Manage;
import hr.manage.service.onclockrecords.OnClockRecordsService;

@Controller
@RequestMapping(value="/hrm/onclockrecords")
public class OnclockrecordsphotoController {
	
	//图形报表接口
	@Resource
	public OnClockRecordsService ms;
	
	
	int cdd;//迟到的百分比
	int zcd;//正常的百分比
	int ztd;//早退的百分比
	
	//图形报表的控制层
	@RequestMapping(value="getphoto", method = RequestMethod.POST)
	public void getview(HttpServletResponse response,String sectionid,String startTime,String endTime)throws IOException{
		response.setCharacterEncoding("utf-8");
		PrintWriter pWriter = response.getWriter();
		//模糊查询 先判断部门id是否为空 为空默认查询当月全部
		if (sectionid==null||sectionid.equals("")) {
			sectionid="";
		}
		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());//获取当前时间
        cal.add(Calendar.MONTH, 0);//获取当前月份
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));//获取当月第一天
        Calendar last = Calendar.getInstance();//定义当月最后一天
        last.set(Calendar.DAY_OF_MONTH, last.getActualMaximum(Calendar.DAY_OF_MONTH)); //设置当月最后一天
        if (startTime==null||startTime.equals("本月月初")||endTime.equals("")) {
			startTime=sdf.format(cal.getTime());
		}
        if (endTime==null||endTime.equals("本月月末")||endTime.equals("")) {
			endTime=sdf.format(last.getTime());
		}
        //List接收获取的数据
		List<Manage> list=ms.getPhoto(sectionid,startTime,endTime);
		int cd=0;//迟到的数量
		int zc=0;//正常的数量
		int zt=0;//早退的数量
		//循环设置各类型的数量
		for (Manage m : list) {
			if (m.getColcktype()==1) {
				zc+=1;
			}else if(m.getColcktype()==2){
				cd+=1;
			}else{
				zt+=1;
			}
		}
		int error=0;
		//各个类型的百分比
		try {
			int total=cd+zt+zc;
			cdd=(cd*100)/total;
			ztd=(zt*100)/total;
			zcd=(zc*100)/total;
		} catch (ArithmeticException e) {
			// TODO: handle exception
			System.out.println("/by zero");
			error=1;
		}
		JSONObject js = new JSONObject();
		js.put("cdd", cdd);
		js.put("ztd", ztd);
		js.put("zcd", zcd);
		js.put("startTime", startTime);
		js.put("endTime", endTime);
		//如何没有当月数据 前台接收错误
		js.put("error", error);
		pWriter.print(js.toString());
		pWriter.close();
	}
}
