package hr.manage.entity;

import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;

import com.alibaba.fastjson.annotation.JSONField;

public class View_Client {

	@Excel(name="编号",orderNum="0")
	private int id;
	@Excel(name="客户名",orderNum="1")
	private String userName;
	private int createBy;
	@Excel(name="创建时间",orderNum="3")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date creationDate;
	@Excel(name="备注",orderNum="4")
	private String detail;

	private String reserve;
	private String spare;
	private String standby;
	private int positionID;
	@Excel(name="创建人",orderNum="2")
	private String staffName;
	@Excel(name="上级人姓名",orderNum="5")
	private String upname;
	
	
	public String getUpname() {
		return upname;
	}
	public void setUpname(String upname) {
		this.upname = upname;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public int getId() {
		return id;
	}
	public int getPositionID() {
		return positionID;
	}
	public void setPositionID(int i) {
		this.positionID = i;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getCreateBy() {
		return createBy;
	}
	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getReserve() {
		return reserve;
	}
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	public String getSpare() {
		return spare;
	}
	public void setSpare(String spare) {
		this.spare = spare;
	}
	public String getStandby() {
		return standby;
	}
	public void setStandby(String standby) {
		this.standby = standby;
	}
	
	
	
	
}
