package hr.manage.controller.functions;

import hr.manage.entity.Functions;
import hr.manage.service.functions.FunctionsService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/hrm/functions")
public class FunctionsController {
	
	private List<Functions> list;
	@Resource
	private FunctionsService fService;
	//��ѯ���й���
	@RequestMapping(value="/findFunctions",method=RequestMethod.POST)
	public void findFunctions(HttpServletResponse response)throws IOException{
		response.setCharacterEncoding("utf-8");
		PrintWriter pWriter = response.getWriter();	 //������
		list = fService.findFunctions();
		System.out.println(list.size());
		JSONObject js=new JSONObject();//����json����
		js.put("list", list);//json������list
		pWriter.print(js.toString());//�����
		pWriter.close();		//close
	}
	
}
