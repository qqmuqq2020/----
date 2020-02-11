package hr.manage.controller.cwg_client;

import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hr.manage.dao.CwgonRecord.CwgonRecordMapper;
import hr.manage.dao.cwg_client.Cwg_clientDao;
import hr.manage.dao.jurisdiction.JurisdictionDao;
import hr.manage.dao.staffs.StaffsLoginDao;
import hr.manage.dao.view_Client.View_ClientDao;
import hr.manage.entity.CwgBookManager;
import hr.manage.entity.CwgClient;
import hr.manage.entity.Staffs;
import hr.manage.entity.View_Client;
import hr.manage.filter.JDBC;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import util.BaseController;
import util.FileUtil;
import util.Page;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.Connection;

@Controller
@RequestMapping(value = "/hrm/cwg_client")
public class Cwg_clientInfoController extends BaseController {
	
	
	@Resource
	Cwg_clientDao dao;
	@Resource
	View_ClientDao cdao;
	@Resource
	StaffsLoginDao sdao;
	@Resource
	JurisdictionDao jdao;
	
	JDBC jdbc = new JDBC();
	
	
	
	/**
	 * 获取配管商人员客户信息
	 * @param createBy
	 * @return
	 */
	
	@RequestMapping(value="/getlist",produces="application/json;charset=utf-8",method=RequestMethod.POST)
	@ResponseBody
	public String getlistbycreateBy(HttpSession session,int index,String like){
		if(like==null){
			like = "";
		}
		Integer uid = Integer.parseInt(session.getAttribute("uid").toString());
		String name = session.getAttribute("name").toString();
		Connection connection = jdbc.getConnection();
		Page<View_Client> page = new Page<View_Client>();
		Integer pid = Integer.parseInt(session.getAttribute("psid").toString());
		if(pid==0){
			try{
				CallableStatement st= connection.prepareCall("{CALL sp_page(?,?,?,?,'order by id desc')}");
				st.setObject(1, index);
				st.setObject(2, 30);
				st.setObject(3, "*");
				st.setObject(4, "view_client where userName LIKE '%"+like+"%'");
				ResultSet rst =st.executeQuery();
				List<View_Client> list = new ArrayList<View_Client>();
				int count=0;
				while(rst.next()){
					View_Client  view_Client = new View_Client();
					view_Client.setId(rst.getInt(1));
					view_Client.setUserName(rst.getString(2));
					view_Client.setCreateBy(rst.getInt(3));
					view_Client.setCreationDate(rst.getTimestamp(4));
					view_Client.setDetail(rst.getString(5));
					view_Client.setReserve(rst.getString(6));
					view_Client.setSpare(rst.getString(7));
					view_Client.setStandby(rst.getString(8));
					view_Client.setPositionID(rst.getInt(9));
					view_Client.setStaffName(rst.getString(10));
					view_Client.setUpname(rst.getString(11));
					list.add(view_Client);
				}
				if(st.getMoreResults()==true){
					rst=st.getResultSet();
					if(rst.next()){
						count = rst.getInt(1);
					}
					System.out.println(count);
					
				}
				page.setGetlist(list);
				page.setCount(count);
				page.setCurrentPage(index);
				page.setPageCount(count, 30);
				
			}catch (Exception e) {
				e.getMessage();
			}
		}else{
			if(pid==2){
				try{
					CallableStatement st= connection.prepareCall("{CALL sp_page(?,?,?,?,'order by id desc')}");
					st.setObject(1, index);
					st.setObject(2, 30);
					st.setObject(3, "*");
					st.setObject(4, "view_client where (reserve= '"+name+"' and userName LIKE '%"+like+"%') or (createBy= '"+uid+"' and userName LIKE '%"+like+"%')");
					ResultSet rst =st.executeQuery();
					List<View_Client> list = new ArrayList<View_Client>();
					int count=0;
					while(rst.next()){
						View_Client  view_Client = new View_Client();
						view_Client.setId(rst.getInt(1));
						view_Client.setUserName(rst.getString(2));
						view_Client.setCreateBy(rst.getInt(3));
						view_Client.setCreationDate(rst.getTimestamp(4));
						view_Client.setDetail(rst.getString(5));
						view_Client.setReserve(rst.getString(6));
						view_Client.setSpare(rst.getString(7));
						view_Client.setStandby(rst.getString(8));
						view_Client.setPositionID(rst.getInt(9));
						view_Client.setStaffName(rst.getString(10));
						view_Client.setUpname(rst.getString(11));
						list.add(view_Client);
					}
					if(st.getMoreResults()==true){
						rst=st.getResultSet();
						if(rst.next()){
							count = rst.getInt(1);
						}
						System.out.println(count);
						
					}
					page.setGetlist(list);
					page.setCount(count);
					page.setCurrentPage(index);
					page.setPageCount(count, 30);
				}catch (Exception e) {
					e.getMessage();
				}
				}else if(pid==3){
					try{
						CallableStatement st= connection.prepareCall("{CALL sp_page(?,?,?,?,'order by id desc')}");
						st.setObject(1, index);
						st.setObject(2, 30);
						st.setObject(3, "*");
						st.setObject(4, "view_client where createBy = "+"'"+uid+"' and userName LIKE '%"+like+"%'");
						ResultSet rst =st.executeQuery();
						List<View_Client> list = new ArrayList<View_Client>();
						int count=0;
						while(rst.next()){
							View_Client  view_Client = new View_Client();
							view_Client.setId(rst.getInt(1));
							view_Client.setUserName(rst.getString(2));
							view_Client.setCreateBy(rst.getInt(3));
							view_Client.setCreationDate(rst.getTimestamp(4));
							view_Client.setDetail(rst.getString(5));
							view_Client.setReserve(rst.getString(6));
							view_Client.setSpare(rst.getString(7));
							view_Client.setStandby(rst.getString(8));
							view_Client.setPositionID(rst.getInt(9));
							view_Client.setStaffName(rst.getString(10));
							view_Client.setUpname(rst.getString(11));
							list.add(view_Client);
						}
						if(st.getMoreResults()==true){
							rst=st.getResultSet();
							if(rst.next()){
								count = rst.getInt(1);
							}
							System.out.println(count);
						}
						
						page.setGetlist(list);
						page.setCount(count);
						page.setCurrentPage(index);
						page.setPageCount(count, 30);
						
					}catch (Exception e) {
						e.getMessage();
					}
				}
			
		}
		page.setLike(like);
		
		jdbc.Close();
		return JSONArray.toJSONString(page);
		
		}
		
	
	
/*	private Integer id;
	//客户姓名
	private String userName;
	//创建人
	private int createBy;
	//创建时间
	private Date creationDate;
	//备注，详情
	private String detail;
	//所属公司发行员ID
	private String reserve;
	//备用字段2
	private String spare;
	//备用字段3
	private String standby;*/
	 
	/**
	 * 添加
	 * @param prosonId
	 * @param userName
	 * @param creationDate
	 * @param detail
	 * @return
	 */
	@RequestMapping(value="addClient",produces="application/json;charset=utf-8",method=RequestMethod.POST)
	@ResponseBody
	public String addClient(HttpSession session,String userName,String detail,String max){
		Integer id = Integer.parseInt(session.getAttribute("uid").toString());
		Staffs staffs = sdao.getStaffByIdT(id);
		Integer Max = Integer.parseInt(max);
		int i = -1;
		if(dao.getCreatMAX(staffs.getId())<=Max){
		CwgClient client = new CwgClient();
		Date date = new Date();
		client.setCreationDate(date);
		client.setUserName(userName.replaceAll(" ", ""));
		client.setCreateBy(staffs.getId());
		client.setReserve(staffs.getReady2());
		client.setDetail(detail);
		i = dao.insertCwgClient(client);
		return JSONArray.toJSONString(i);
		}else{
			
			return JSONArray.toJSONString(i);
		}
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delClient",produces="application/json;charset=utf-8",method=RequestMethod.POST)
	@ResponseBody
	
	public String delClient(int id){
		int i =-1;
		Map<Integer, Map<String, Long>> map = dao.getClentDel(id);
		Map<String, Long> a =map.get(id);
		if(a==null){
		i= dao.delCwgClient(id);
		}
		return  JSONArray.toJSONString(i);
	}
	
	
	@RequestMapping(value ="/editClient",produces="application/json;charset=utf-8")
	@ResponseBody
	public String getClient(int id){
		CwgClient client = dao.getCwgClient(id);
		return JSONArray.toJSONString(client);
	}
	
	@RequestMapping(value ="/ifname",produces="application/json;charset=utf-8")
	@ResponseBody
	public String ifname(String userName,HttpSession session) throws UnsupportedEncodingException{
		
		String nnname=new String(userName.getBytes("iso-8859-1"),"utf-8");
		
		String ddString=nnname.replaceAll(" ", "");
		int i = -1;
		try {
			Integer createBy = Integer.parseInt(session.getAttribute("uid").toString());
		
			System.out.println(ddString+":"+createBy);
			CwgClient custerm = dao.ifname(createBy, ddString);
			if(custerm!=null){
				i=1;
			}else{
				i=0;
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return JSONArray.toJSONString(i);
	}
	
}
