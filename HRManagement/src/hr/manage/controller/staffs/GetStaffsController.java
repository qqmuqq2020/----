package hr.manage.controller.staffs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import hr.manage.entity.Jurisdiction;
import hr.manage.entity.Staffs;
import hr.manage.service.jurisdiction.JurisdictionService;
import hr.manage.service.staffs.StaffsLoginService;

@Controller
@RequestMapping(value = "/hrm/staffs")
public class GetStaffsController {

	public List<Staffs> list; // 容器
	private int total; // 总条数
	private int pageSize = 30; // 每页显示数量

	@Resource
	private StaffsLoginService staffsLoginService; // 人员service
	@Resource
	private JurisdictionService jurisdictionService;// 权限service

	/**
	 * 基于分页的 模糊查询 上一页下一页 跳转 及多条件查询
	 */
	@RequestMapping(value = "getpage", method = RequestMethod.POST)
	public void getPage(HttpServletResponse response, int pageIndex, String staffname, String positionID,
			String sectionID,HttpSession session) throws Exception {

		
		Integer pid = Integer.parseInt(session.getAttribute("psid").toString());
		String name = session.getAttribute("name").toString();
		response.setCharacterEncoding("UTF-8");
		PrintWriter pWriter = response.getWriter();
		
		if (staffname != null && !staffname.equals("")) {
			staffname = "%" + staffname + "%";
		} else {
			staffname = "%%";
		}
		if (positionID != null && !positionID.equals("0")) {
			positionID = "%" + positionID + "%";
		} else {
			positionID = "%%";
		}
		if (sectionID != null && !sectionID.equals("0")) {
			sectionID = "%" + sectionID + "%";
		} else {
			sectionID = "%%";
		}
		Map<String, Object> map = staffsLoginService.getPage(pageIndex, pageSize, staffname, positionID, sectionID,pid,name);// 执行查询
		total = (Integer) map.get("total");// 接收总条数
		list = (List<Staffs>) map.get("staffs");// 接收数据

		JSONObject js = new JSONObject();
		js.put("total", total);
		js.put("list", list);
		pWriter.print(js.toString());// js打包输出
		pWriter.close();
	}

	/**
	 * 基于视图查询人员信息
	 * 
	 * @param response
	 * @param Id
	 * @throws Exception
	 */
	@RequestMapping(value = "getstaffs", method = RequestMethod.POST)
	public void service(HttpServletResponse response, Integer Id) throws Exception {
		response.setCharacterEncoding("UTF-8");
		PrintWriter pWriter = response.getWriter();
		JSONObject js = new JSONObject();
		Staffs staffs = staffsLoginService.getStaffsById(Id);// 执行查询
		js.put("staffs", staffs);
		pWriter.print(js.toString());// js打包输出
		pWriter.close(); // close

	}

	/**
	 * 基于table查询人员信息
	 * 
	 * @param response
	 * @param Id
	 * @throws IOException
	 */
	@RequestMapping(value = "getbyid", method = RequestMethod.POST)
	public void getbyid(HttpServletResponse response, Integer Id, HttpSession session) throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter pWriter = response.getWriter();
		JSONObject js = new JSONObject();
		List<Staffs> list = staffsLoginService.getbyid(Id); // 执行人员查询
		String gid = list.get(0).getGid();
		List<Jurisdiction> jlist = jurisdictionService.get(gid);// 执行权限查询
		session.setAttribute("updateId", Id);
		System.out.println(Id);
		js.put("list", list);
		js.put("jlist", jlist);
		pWriter.print(js.toString());// js打包输出
		pWriter.close();

	}

	/**
	 * 查询人员详细信息
	 * 
	 * @param response
	 * @param id
	 * @throws IOException
	 */
	@RequestMapping(value = "selectStaffs")
	public void selectStaffs(HttpServletResponse response, int id) throws IOException {
		PrintWriter pw = null;
		JSONObject json = new JSONObject();
		response.setCharacterEncoding("UTF-8");
		try {
			pw = response.getWriter();
			List<Staffs> staffs = staffsLoginService.selectStaffs(id);// 执行查询
			json.put("selectStaffs", staffs);
			pw.print(json.toString());// js打包输出
		} finally {
			pw.close();
		}

	}

	
}
