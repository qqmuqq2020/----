package hr.manage.entity;

public class Jurisdiction {
	private Integer jurisdictionID;
	private String staffID;
	private Integer functionID;
	private String gid;
	private Integer ready2;
	public Integer getJurisdictionID() {
		return jurisdictionID;
	}
	public Integer getReady2() {
		return ready2;
	}
	public void setReady2(Integer ready2) {
		this.ready2 = ready2;
	}
	public void setJurisdictionID(Integer jurisdictionID) {
		this.jurisdictionID = jurisdictionID;
	}
	public String getStaffID() {
		return staffID;
	}
	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}
	public Integer getFunctionID() {
		return functionID;
	}
	public void setFunctionID(Integer functionID) {
		this.functionID = functionID;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public Jurisdiction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Jurisdiction(String staffID, Integer functionID, String gid) {
		super();
		this.staffID = staffID;
		this.functionID = functionID;
		this.gid = gid;
	}
	
}