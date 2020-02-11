package hr.manage.controller.person;

import hr.manage.dao.staffs.StaffsLoginDao;
import hr.manage.entity.Jurisdiction;
import hr.manage.entity.Staffs;
import hr.manage.service.jurisdiction.JurisdictionService;
import hr.manage.service.staffs.StaffsLoginService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/hrm/person")
public class PersonController {
	@Resource
	private StaffsLoginService staffsLoginService; // 人员service
	@Resource
	private JurisdictionService jurisdictionService;// 权限service
	
	/**
	 * 基于table查询人员信息
	 * 
	 * @param response
	 * @param Id
	 * @throws IOException
	 */
	@RequestMapping(value = "getbyid", method = RequestMethod.POST)
	public void getbyid(HttpServletResponse response, HttpSession session) throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter pWriter = response.getWriter();
		JSONObject js = new JSONObject();
		Integer id = Integer.parseInt(session.getAttribute("uid").toString());
		List<Staffs> list = staffsLoginService.getbyid(id); // 执行人员查询
		String gid = list.get(0).getGid();
		List<Jurisdiction> jlist = jurisdictionService.get(gid);// 执行权限查询
		session.setAttribute("updateId", id);
		System.out.println(id);
		js.put("list", list);
		pWriter.print(js.toString());// js打包输出
		pWriter.close();

	}
}
