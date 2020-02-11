package hr.manage.entity;

import java.util.Date;

public class CwgonRecoreTimes {

	//主键id
	private Integer id;
	//状态
	private Integer status;
	//备案号id
	private Integer recoreId;
	//创建人
	private Integer createBy;
	//创建时间
	private Date creationDate;
	//延期次数
	private Integer residueNumber;
	//剩余时间
	private Integer remainingTime;
	//备用字段1
	private String reserve;
	//备用字段2
	private String spare;
	//备用字段3
	private String standby;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getRecoreId() {
		return recoreId;
	}
	public void setRecoreId(Integer recoreId) {
		this.recoreId = recoreId;
	}
	public Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getResidueNumber() {
		return residueNumber;
	}
	public void setResidueNumber(Integer residueNumber) {
		this.residueNumber = residueNumber;
	}
	public Integer getRemainingTime() {
		return remainingTime;
	}
	public void setRemainingTime(Integer remainingTime) {
		this.remainingTime = remainingTime;
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
