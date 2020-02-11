package hr.manage.controller.staffs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hr.manage.entity.Staffs;
import hr.manage.service.staffs.StaffsLoginService;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class StaffsLoginController {

	@Resource
	private StaffsLoginService staffsLoginService;
	
	JSONObject json = new JSONObject();
	
	/**
	 * 人员登录
	 */
	@RequestMapping(value = "/login.do")
	public void userLogin(HttpServletResponse response, String name,
			String pwd, HttpSession session,HttpServletRequest req) throws IOException {
		
		PrintWriter pWriter = response.getWriter();
		Staffs ulogin = staffsLoginService.userLogin(name);//根据账号查询
		
		if (ulogin == null) {
			pWriter.print(0); //0代表 无账号
			pWriter.close(); 
		} else {
			if (ulogin.getStaffPassword().equals(pwd)) {//登录成功为拦截器存 session
				session.setAttribute("uname", ulogin.getStaffName());//人员名字
				session.setAttribute("name", name);//账号
				session.setAttribute("gid", ulogin.getGid());//权限
				session.setAttribute("uid", ulogin.getId());
				session.setAttribute("psid", ulogin.getPositionID());//职位ID
				Cookie cooki; 
				cooki=new Cookie("keypid",ulogin.getPositionID().toString());//用户ID 
				cooki.setMaxAge(60*60*24*365);//cookie时间 
				cooki.setPath("/"); //根据个人的不用，在不同功能的路径下创建 
				response.addCookie(cooki);
				Cookie cooki1; 
				cooki1=new Cookie("keyuid",ulogin.getId().toString());//用户ID 
				cooki1.setMaxAge(60*60*24*365);//cookie时间 
				cooki1.setPath("/"); //根据个人的不用，在不同功能的路径下创建 
				response.addCookie(cooki1); 
				pWriter.print(json.toString());
			} else {
				pWriter.print(-1); // -1 密码错误
				pWriter.close();
			}
		}
		pWriter.close();
	}
	
	/**
	 * 获取人员姓名给top框架
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/getUname")
	public void getUname(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String uName = (String) request.getSession().getAttribute("uname");//获取session
		json.put("uname", uName);//打包输出
		PrintWriter pWriter = response.getWriter();
		pWriter.print(json.toString());		
		pWriter.close();
	}
	
	/**
	 * 人员退出
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "exit")
	private void exit(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute("name");
		request.getSession().removeAttribute("gid");//清空session
		PrintWriter pWriter = response.getWriter();
		pWriter.print("yes");
	}
}
