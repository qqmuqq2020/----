package hr.manage.entity;

import java.util.Date;

public class CwgonRecoreStatus {
	//主键id
	private Integer id;
	//备案编号
	private Integer recoreId;
	//创建时间
	private Date creationDate;
	//创建人
	private Integer createBy;
	//审核状态，1.创建。2.审核通过。3.驳回。4.终止。5.完成
	private Integer checkstatus;
	//备注
	private String detail;
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
	public Integer getRecoreId() {
		return recoreId;
	}
	public void setRecoreId(Integer recoreId) {
		this.recoreId = recoreId;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	public Integer getCheckstatus() {
		return checkstatus;
	}
	public void setCheckstatus(Integer checkstatus) {
		this.checkstatus = checkstatus;
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
