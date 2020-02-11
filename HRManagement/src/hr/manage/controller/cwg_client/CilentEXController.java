package hr.manage.controller.cwg_client;

import hr.manage.dao.view_Client.View_ClientDao;
import hr.manage.entity.View_Client;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import util.FileUtil;
@Controller
@RequestMapping(value="/hrm/EX")
public class CilentEXController {
	@Resource
	View_ClientDao cao;
	
	
	@RequestMapping("/outClient")
	@ResponseBody
	public void out(HttpServletResponse response,HttpSession session,String l){		
			Integer id = Integer.parseInt(session.getAttribute("uid").toString());
			Integer pid = Integer.parseInt(session.getAttribute("psid").toString());
			if(pid==0){
				if(l!=null&&l!=""){
					List<View_Client> list = cao.getAll("%"+l+"%");
					FileUtil.exportExcel(list, "客户信息", "客户信息", View_Client.class, "客户信息.xls", response);
				}else{
					List<View_Client> list = new ArrayList<>();
					FileUtil.exportExcel(list, "超级管理员导出客户必须要有一个条件", "超级管理员导出客户必须要有一个条件", View_Client.class, "客户信息.xls", response);
				}
				}else if(pid==2||pid==3){
				List<View_Client> list = cao.getListbyCreateBy(id);
				FileUtil.exportExcel(list, "客户信息", "客户信息", View_Client.class, "客户信息.xls", response);
			}
}
}