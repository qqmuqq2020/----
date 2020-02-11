package hr.manage.controller.sections;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.DuplicateFormatFlagsException;
import java.util.List;
import java.util.Map;

import hr.manage.entity.Sections;
import hr.manage.service.sections.SectionsService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping(value = "/hrm/sections")
public class SectionsController {

	// 定义list结合集合接收
	public List<Sections> list;
	// 总数
	private int total;
	// 每页显示的数量
	private int pageSize = 4;
	// 部门接口层
	int iret;
	@Resource
	private SectionsService sService;

	// 部门表分页模糊查询
	@RequestMapping(value = "getAll", method = RequestMethod.POST)
	public void getAll(HttpServletResponse response, int pageIndex,
			String sectionname) throws IOException, SQLException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter pWriter = response.getWriter();
		// 前台传值为空时默认查询全部
		if (sectionname != null && !sectionname.equals("")) {
			sectionname = "%" + sectionname + "%";
		} else {
			sectionname = "%%";
		}
		// map接收后台数据
		Map<String, Object> list = sService.getPage(pageIndex, pageSize,
				sectionname);
		// 获取map集合中第二个结果总数
		total = (Integer) list.get("total");
		// 把Map集合中的数据放到list集合中
		List<Sections> sections = (List<Sections>) list.get("sections");
		JSONObject js = new JSONObject();
		js.put("total", total);// 总数
		js.put("list", sections);// 每页的数据
		pWriter.print(js.toString());
		pWriter.close();
	}

	// 添加sections人员
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public void Add(HttpServletResponse response, String sectionname)
			throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter pWriter = response.getWriter();
		list = sService.findSections();
		boolean add = true;
		for (Sections s : list) {
			if (!s.getSectionname().equals(sectionname)) {
				add = true;
			} else {
				iret=-1;
				add = false;
				break;
			}
		}
		if (add) {
			iret = sService.Add(sectionname);// 返回int前台判断
		}
		pWriter.print(iret);
		pWriter.close();
	}

	// 修改sections人员
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public void Update(String sectionname, Integer sectionid,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter pWriter = response.getWriter();
		list = sService.findSections();
		boolean update = true;
		for (Sections s : list) {
			if (!s.getSectionname().equals(sectionname)&&!sectionname.equals("")) {
				update = true;
			} else {
				iret = 0;
				update = false;
				break;
			}
		}
		if (update) {
			iret = sService.update(sectionname, sectionid);// 返回int前台判断
		}
		pWriter.print(iret);
		pWriter.close();
	}

	// 删除sections部门
	@RequestMapping(value = "del", method = RequestMethod.POST)
	public void Del(Integer id, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter pWriter = response.getWriter();
		sService.updateStaffsSectionID(id);// 在删除部门时 把该部门下所有员工转移到未分配部门下
		iret = sService.delete(id);
		pWriter.print(iret);
		pWriter.close();
	}

	/*
	 * 根据id查询sections人员 查看时使用 当所属部门未分配时 前台显示未分配人员
	 */
	@RequestMapping(value = "byid", method = RequestMethod.POST)
	public void byId(Integer sectionid, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter pWriter = response.getWriter();
		Sections sections = new Sections();
		// 当sid所属部门id为空或者为零时
		list = sService.getById(sectionid);
		JSONObject js = new JSONObject();
		js.put("list", list);
		pWriter.print(js.toString());
		pWriter.close();
	} // yes

	/*
	 * 查询全部sections部门 部门管理功能 添加修改时使用 人员管理功能 添加修改模糊查询时使用 报表管理（图形报表，表格报表）模糊查询时使用
	 */
	@RequestMapping(value = "findSections", method = RequestMethod.POST)
	public void findSections(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter pWriter = response.getWriter();
		// 获取所有部门
		list = sService.findSections();
		JSONObject js = new JSONObject();
		js.put("list", list);
		pWriter.print(js.toString());
		pWriter.close();// close
	}

}
