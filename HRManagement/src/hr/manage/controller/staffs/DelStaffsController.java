package hr.manage.controller.staffs;




import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import hr.manage.entity.Staffs;
import hr.manage.service.jurisdiction.JurisdictionService;
import hr.manage.service.staffs.StaffsLoginService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value="/hrm/staffs")
public class DelStaffsController {
	
	@Resource
	private StaffsLoginService ss;//人员service
	@Resource
	private JurisdictionService js;//权限service
	
	
	/**
	 * 人员删除方法
	 * @param response
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value="delstaffs", method = RequestMethod.POST)
	public void service(HttpServletResponse response,int id) throws Exception{
		
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter pWriter=response.getWriter();
		String sde = ss.getdel(id);
		if(ss.getdel(id)==null){
			List<Staffs> delgid = ss.getbyid(id);	//查询
			String gid = delgid.get(0).getGid();	//获取id
			String photo = delgid.get(0).getPicture();//获取图片路径
			
			File f = new File("C:\\Program Files (x86)\\apache-tomcat-7.0.79\\webapps\\HRManagement\\uploads\\"+photo);
		    deleteFile(f);//IO流删除图片
		    
			int iret=ss.delStaffsById(id);	//执行人员表删除
			int iiret = 0 ;
			
			if (iret == 1) {
				iiret = js.deleteJur(gid);//成功后对权限表进行删除
			}
			
			pWriter.print(iret);//打包输出受影响行数4
			pWriter.close();
		}else{
		pWriter.print(-1);
		pWriter.close();
		}
	}
	//io流删除图片
	private static void deleteFile(File f){
			f.delete();	
	}
}
