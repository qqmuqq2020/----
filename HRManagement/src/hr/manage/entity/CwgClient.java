package hr.manage.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class CwgClient {
		//主键id
		private Integer id;
		//客户姓名
		private String userName;
		//创建人
		private int createBy;
		//创建时间
		@JSONField(format="yyyy-MM-dd HH:mm:ss")
		private Date creationDate;
		//备注，详情
		private String detail;
		//所属公司发行员ID
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
