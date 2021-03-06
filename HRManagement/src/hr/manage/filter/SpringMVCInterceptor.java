package hr.manage.filter;

import java.util.List;

import hr.manage.dao.verify.PermissionsDao;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SpringMVCInterceptor implements HandlerInterceptor{
	@Resource
	PermissionsDao dao;
	//鏁翠釜椤甸潰娓叉煋鍚庢墽琛�
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	//杩斿洖閫昏緫瑙嗗浘鍓嶆墽琛�
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
			
		String aString="";
	}

	//访问时
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		
		System.out.println("------进入到拦截器");
		// TODO Auto-generated method stub
		//获取页面id
		String fid=arg0.getParameter("fid");
		//获取用户Session
		String loingString=(String)arg0.getSession().getAttribute("name");
		//设置返回值
		boolean trues=false;
		//获取用户唯一识别码
		String gid=(String) arg0.getSession().getAttribute("gid");
		//根据识别码查询用户权限
		List<Integer> result=dao.Permission_verification(gid);
		//所有权限添加备案总目
		result.add(13);
//判断用户是否登录
		if(loingString!=null)
		{  
			
			
			//设置返回值为true
			trues=true;
			//判断返回值
			if(trues==true){
				
				//判断该页面是否加上权限W
				if (fid!=null) {
					//判断用户权限是否为空
 					 if (result.size()>0) {
						//循环用户权限
						for (int i : result) {
							trues=true;
							//循环判断用户权限
 							if (new Integer(fid)==i) {
								//返回结果
								return trues;
							}
						}
						//返回结果
						trues=false;
						//重定向到权限不足页面
						arg1.sendRedirect("/HRManagement/nothing.html");

						//返回结果
						return trues;
					}
					trues=false;
					//重定向到权限不足页面
					arg1.sendRedirect("/HRManagement/nothing.html");
					//返回结果
					return trues;
				}else {
					trues=false;
					//重定向到权限不足页面
					arg1.sendRedirect("/HRManagement/nothing.html");
					//返回结果
					return trues;
				}
			}
			trues=false;
			//重定向到错误页面
			arg1.sendRedirect("/HRManagement/error.html");
			//返回结果
			return trues;
		}
		//重定向到错误页面
		arg1.sendRedirect("/HRManagement/error.html");
		//返回结果
		return trues;		
	}
}
