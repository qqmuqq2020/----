package hr.manage.entity;

import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;


public class CwgonRecord {
		//主键id
		private Integer id;
		//图书外键id
		private Integer bid;
		//客户外键id，引用客户表id
		private Integer uid;
		//创建人
		private Integer createBy;
		
		//创建人
		@Excel(name = "备案人",orderNum = "4")
		private String staffName;
		//创建时间
		
		private Date creationDate;
		//审核人
		private String checkBy;
		//备案编号
		@Excel(name = "备案编号",orderNum = "5")
		private String recordNumber;
		//订单编号
		@Excel(name = "订单编号",orderNum = "6")
		private String orderNumber;
		//状态，引用状态表最大id
		private Integer statusById;
		//时间，引用时间表最大id
		private Integer remainingById;
		//备用字段1
		private String reserve;
		//备用字段2
		private String spare;
		//备用字段3
		private String standby;
		//图书
		//编号
		@Excel(name = "编号",orderNum = "0")
		private String number;
		//书名
		@Excel(name = "书名",orderNum = "1")
		private String name;
		//价格
		@Excel(name = "定价",orderNum = "2")
		private double price;
		
		private CwgBookManager cwgBookManager;
		//客户
		//客户姓名
		@Excel(name = "客户",orderNum = "3")
		private String userName;
		private CwgClient cwgClient;
		//状态
		//审核状态，1.创建。2.审核通过。3.驳回。4.终止。5.完成
		@Excel(name = "状态",orderNum = "7")
		private String status;
		private Integer checkstatus;
		private CwgonRecoreStatus cwgonRecoreStatus;
		//时间
		//剩余时间
		
		private Integer remainingTime;
		private CwgonRecoreTimes cwgonRecoreTimes;
		//时间表最大id创建时间
		private Date timeCreationDate;
		//剩余次数
		private Integer residueNumber;
		//权限id
		private Integer psid;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		public Integer getPsid() {
			return psid;
		}
		public void setPsid(Integer psid) {
			this.psid = psid;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public Integer getResidueNumber() {
			return residueNumber;
		}
		public void setResidueNumber(Integer residueNumber) {
			this.residueNumber = residueNumber;
		}
		public String getStaffName() {
			return staffName;
		}
		public void setStaffName(String staffName) {
			this.staffName = staffName;
		}
		public String getNumber() {
			return number;
		}
		public void setNumber(String number) {
			this.number = number;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public Integer getCheckstatus() {
			return checkstatus;
		}
		public void setCheckstatus(Integer checkstatus) {
			this.checkstatus = checkstatus;
		}
		public Integer getRemainingTime() {
			return remainingTime;
		}
		public void setRemainingTime(Integer remainingTime) {
			this.remainingTime = remainingTime;
		}
		public CwgBookManager getCwgBookManager() {
			return cwgBookManager;
		}
		public void setCwgBookManager(CwgBookManager cwgBookManager) {
			this.cwgBookManager = cwgBookManager;
		}
		public CwgClient getCwgClient() {
			return cwgClient;
		}
		public void setCwgClient(CwgClient cwgClient) {
			this.cwgClient = cwgClient;
		}
		public CwgonRecoreStatus getCwgonRecoreStatus() {
			return cwgonRecoreStatus;
		}
		public void setCwgonRecoreStatus(CwgonRecoreStatus cwgonRecoreStatus) {
			this.cwgonRecoreStatus = cwgonRecoreStatus;
		}
		public CwgonRecoreTimes getCwgonRecoreTimes() {
			return cwgonRecoreTimes;
		}
		public void setCwgonRecoreTimes(CwgonRecoreTimes cwgonRecoreTimes) {
			this.cwgonRecoreTimes = cwgonRecoreTimes;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getBid() {
			return bid;
		}
		public void setBid(Integer bid) {
			this.bid = bid;
		}
		public Integer getUid() {
			return uid;
		}
		public void setUid(Integer uid) {
			this.uid = uid;
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
		public String getCheckBy() {
			return checkBy;
		}
		public void setCheckBy(String checkBy) {
			this.checkBy = checkBy;
		}
		public String getRecordNumber() {
			return recordNumber;
		}
		public void setRecordNumber(String recordNumber) {
			this.recordNumber = recordNumber;
		}
		public String getOrderNumber() {
			return orderNumber;
		}
		public void setOrderNumber(String orderNumber) {
			this.orderNumber = orderNumber;
		}
		public Integer getStatusById() {
			return statusById;
		}
		public void setStatusById(Integer statusById) {
			this.statusById = statusById;
		}
		public Integer getRemainingById() {
			return remainingById;
		}
		public void setRemainingById(Integer remainingById) {
			this.remainingById = remainingById;
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
