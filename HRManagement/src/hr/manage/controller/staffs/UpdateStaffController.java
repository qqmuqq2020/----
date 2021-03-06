package hr.manage.controller.staffs;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import hr.manage.entity.Jurisdiction;
import hr.manage.entity.Staffs;
import hr.manage.service.jurisdiction.JurisdictionService;
import hr.manage.service.staffs.StaffsLoginService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping(value = "/hrm/staff")
public class UpdateStaffController {

	@Resource
	private StaffsLoginService ss; // staffs service
	@Resource
	private JurisdictionService jurs;// jur service

	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(String fileName, // �ϴ���ͼƬ���
			String staffname, // Ա�����
//			Integer positionid, // ְλID
			String birthday, // Ա������
			String staffpassword, // ����
			String nowaddress, // ��סַ
			String contactway, // ��ϵ��ʽ
			Integer sectionid, // ����ID
			String idcardno, // ���֤����
			String functionid, String gid,
//			String ready2,
			String staffid,
			// ����ID
			Integer id,
			/* HttpServletRequest request, */
			HttpServletResponse response) throws ParseException, IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter pWriter = response.getWriter(); // ������
		JSONObject js = new JSONObject();// ����json����

		// ����Ա�����
		/*String staffid = positionid + "-" + sectionid + "-"
				+ (ss.selectID() + 1);*/
		// ת����������
		if(birthday!=null&&birthday!=""){
			Date birthday1 = sf.parse(birthday);
		}

		Staffs update = new Staffs();

		update.setStaffName(staffname);
		update.setStaffPassword(staffpassword);
//		update.setPositionID(positionid);
		update.setBirthday(birthday);
		update.setPicture(fileName);
		update.setNowAddress(nowaddress);
		update.setIdCardNo(idcardno);
		update.setContactWay(contactway);
		update.setSectionID(sectionid);
//		update.setReady2(ready2);
		update.setId(id);
		int iret = ss.updateStaffs(update);
//		if (functionid!="") {
//			jurs.deleteJur(gid);
//			Integer f;
//			String fff[] = functionid.split(",");
//			for (int i = 0; i < fff.length; i++) {
//				f = Integer.valueOf(fff[i]);
//				jurs.saveJur(new Jurisdiction(staffid, f, gid));
//			}
//		}
		pWriter.print(iret);
		pWriter.close();
	}
}
