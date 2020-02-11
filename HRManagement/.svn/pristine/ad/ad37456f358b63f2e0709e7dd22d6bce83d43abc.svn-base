package hr.manage.controller.positions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import hr.manage.entity.Positions;
import hr.manage.service.positions.PositionService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/hrm/positions")
public class PositionsController {
	
	private List<Positions> list;
	@Resource
	private PositionService pService;
	
	//��ѯȫ��
	@RequestMapping(value="/findPositions",method=RequestMethod.POST)
	public void findPositions(HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		PrintWriter pWriter = response.getWriter();	 //������
		list = pService.findAll();
		JSONObject js=new JSONObject();//����json����
		js.put("list", list);//json������list
		pWriter.print(js.toString());//�����
		pWriter.close();		//close
	}
}
