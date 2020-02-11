package hr.manage.controller.staffs;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hr.manage.dao.staffs.StaffsLoginDao;
import hr.manage.entity.Jurisdiction;
import hr.manage.entity.Staffs;

import hr.manage.service.jurisdiction.JurisdictionService;
import hr.manage.service.staffs.StaffsLoginService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping(value = "/hrm/staff")
public class AddStaffController {
	@Resource
	private StaffsLoginService sLoginService; // 用户
	@Resource
	private JurisdictionService jService; // 权限
	@Resource
	private StaffsLoginDao dao;
	JSONObject js = new JSONObject(); // Json
	Integer IsOK = null; // 判断是否重复的条件


	/**
	 * 身份证重复判断
	 * @param idcardno
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/ifCard", method = RequestMethod.POST)
	public void ifCard(String idcardno, HttpServletResponse response)
			throws IOException {
		List<Staffs> list = sLoginService.getbyCard(idcardno);
		if (list.size() > 0) {
			IsOK = 1;
		} else {
			IsOK = 0;
		}
		PrintWriter pWriter = response.getWriter();
		js.put("card", IsOK);
		pWriter.print(js.toString());
		pWriter.close();
	}
	
	/**
	 * 身份证重复判断(修改)
	 * @param idcardno
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/ifCard2", method = RequestMethod.POST)
	public void ifCard2(String idcardno, HttpServletResponse response,HttpSession session)
			throws IOException {
		List<Staffs> list = sLoginService.getbyCard(idcardno);
		Integer id = (Integer) session.getAttribute("updateId");
		if (list.size() > 0) {
			if (list.get(0).getId().equals(id)) {
				IsOK = 0;
			}else {
				IsOK = 1;
			}
		} else {
			IsOK = 0;
		}
		PrintWriter pWriter = response.getWriter();
		js.put("card", IsOK);
		pWriter.print(js.toString());
		pWriter.close();
	}
	
	/**
	 * 电话号重复判断
	 * @param contactway
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/ifPhone", method = RequestMethod.POST)
	public void ifPhone(String contactway, HttpServletResponse response)
			throws IOException {
		List<Staffs> list = sLoginService.getbycontactWay(contactway);
		if (list.size() > 0) {
			IsOK = 1;
		} else {
			IsOK = 0;
		}
		PrintWriter pWriter = response.getWriter();
		js.put("phone", IsOK);
		pWriter.print(js.toString());
		pWriter.close();
	}
	
	/**
	 * 电话号重复判断(修改)
	 * @param contactway
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/ifPhone2", method = RequestMethod.POST)
	public void ifPhone2(String contactway, HttpServletResponse response,HttpSession session)
			throws IOException {
		List<Staffs> list = sLoginService.getbycontactWay(contactway);
		Integer id = (Integer) session.getAttribute("updateId");
		if (list.size() > 0) {
			if (list.get(0).getId().equals(id)) {
				IsOK = 0;
			}else {
				IsOK = 1;
			}		
		} else {
			IsOK = 0;
		}
		PrintWriter pWriter = response.getWriter();
		js.put("phone", IsOK);
		pWriter.print(js.toString());
		pWriter.close();
	}
	/**
	 * 人员添加
	 */
	@RequestMapping(value = "/addStaff", method = RequestMethod.POST)
	public void saveStaff(String fileName, // 图片名称
			String staffname, // 人员姓名
			Integer positionid, // 职位ID
			String birthday, // 出生日期
			String staffpassword, // 人员密码
			String nowaddress, // 住址
			String contactway, // 联系方式
			Integer sectionid, // 部门id
			String idcardno, // 身份证号
			String functionid, // 权限id
			String ready2,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		
		//自动生成员工账号
		String staffid = positionid + "-"+ (sLoginService.selectID() + 1);
		
		Staffs staffs = new Staffs();//容器
		
		staffs.setStaffID(staffid);
		staffs.setStaffName(staffname);
		staffs.setStaffPassword(staffpassword);
		staffs.setPositionID(positionid);
		staffs.setBirthday(birthday);
		staffs.setPicture(fileName);
		staffs.setNowAddress(nowaddress);
		staffs.setIdCardNo(idcardno);
		staffs.setContactWay(contactway);
		staffs.setSectionID(sectionid);
		staffs.setReady2(ready2);
		String gid = java.util.UUID.randomUUID().toString();//利用UUID进行权限标识赋值
		staffs.setGid(gid);
		
		int row = sLoginService.saveStaff(staffs);//执行人员表保存
		
		System.out.println(row);
		PrintWriter pWriter = response.getWriter();
		if (functionid!="") {
			jService.deleteJur(gid);
			Integer f;
			String fff[] = functionid.split(",");
			for (int i = 0; i < fff.length; i++) {
				f = Integer.valueOf(fff[i]);
				jService.saveJur(new Jurisdiction(staffid, f, gid));
			}
		}
		pWriter.print(row);
		pWriter.close(); // close
	}
	@RequestMapping(value=("/getStaffsByid"),method = RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String gatStaffbyid(){
		List<Staffs> list= dao.getStaffsBypositionID(2);
		return JSONArray.toJSONString(list);
	}
}
