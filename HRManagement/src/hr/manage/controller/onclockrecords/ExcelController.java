package hr.manage.controller.onclockrecords;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import hr.manage.entity.Manage;
import hr.manage.service.onclockrecords.OnClockRecordsService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/hrm/excel")
public class ExcelController {
@Resource
OnClockRecordsService service;
@SuppressWarnings("deprecation")
//导出Excel
@RequestMapping(value="excel.do")
public void derivation(HttpServletResponse response,String startTime, String endTime,
		String sectionid, String staffName)throws IOException{
	response.setCharacterEncoding("UTF-8");//设置编码格式
	OutputStream outputStream=null;
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	response.reset();
	response.setContentType("application/x-excel");
	response.setHeader("Content-disposition", "attachment; filename=getExcel.xls");
	response.setContentType("application/x-download");
	try {
		outputStream=response.getOutputStream();
        Calendar cal = Calendar.getInstance();//定义Calendar时间格式
        cal.setTime(new Date());//获取当天时间
        cal.add(Calendar.MONTH, 0);//获取当前月 -1为上一月 0为当月 1为下一月
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));//获取当月的月初
        Calendar last = Calendar.getInstance();//设置当月的最后一天
        last.set(Calendar.DAY_OF_MONTH, last.getActualMaximum(Calendar.DAY_OF_MONTH)); 
		//模糊查询 如果部门ID为空的话 默认查询当月全部
		if(sectionid.equals("0")){
			sectionid="";
		}
		List<Manage> manages=service.getExcel(startTime, endTime, sectionid, staffName);
	if(manages.size()>0){  
		 // 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("打卡报表");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        //创建行
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("员工编号");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 1);  
        cell.setCellValue("员工姓名");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 2);  
        cell.setCellValue("部门名称");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 3);  
        cell.setCellValue("打卡时间");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 4);  
        cell.setCellValue("打卡类型");
        cell.setCellStyle(style);  
        //循环manage集合里的数据
        for (int i = 0; i < manages.size(); i++) {
			row=sheet.createRow((int)i+1);
			//通过实体获取manage集合数据
			Manage manage=manages.get(i);
			//判断实体里打卡类型,1是正常,2是迟到,3是早退
			if (manage.getColcktype()==1) {
				row.createCell((short)0).setCellValue(manage.getStaffid());
				row.createCell((short)1).setCellValue(manage.getStaffname());
				row.createCell((short)2).setCellValue(manage.getSectionname());
				row.createCell((short)3).setCellValue(manage.getColcktime());
				row.createCell((short)4).setCellValue("正常");
			}
			if(manage.getColcktype()==2){
				row.createCell((short)0).setCellValue(manage.getStaffid());
				row.createCell((short)1).setCellValue(manage.getStaffname());
				row.createCell((short)2).setCellValue(manage.getSectionname());
				row.createCell((short)3).setCellValue(manage.getColcktime());
				row.createCell((short)4).setCellValue("迟到");
			}
			if (manage.getColcktype()==3) {
				row.createCell((short)0).setCellValue(manage.getStaffid());
				row.createCell((short)1).setCellValue(manage.getStaffname());
				row.createCell((short)2).setCellValue(manage.getSectionname());
				row.createCell((short)3).setCellValue(manage.getColcktime());
				row.createCell((short)4).setCellValue("早退");
			}
		}
        //设置导出路径和文件名
        //创建文件
response.setHeader("Content-Disposition", "attachment; filename=getExcel"+sdf.format(new Date())+".xls");
        wb.write(outputStream);
        //关闭
	}
	} catch (Exception e) {
		// TODO: handle exception
	
		e.printStackTrace();
	}
}
}
