package hr.manage.entity;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
public class CwgBookManager{
	public CwgBookManager() {
	}

	public CwgBookManager(String name, String serialNumber, String publisher, String author, Date creationDate, int total, double price, String detail) {
		this.name = name;
		this.serialNumber = serialNumber;
		this.publisher = publisher;
		this.author = author;
		this.creationDate = creationDate;
		this.total = total;
		this.price = price;
		this.detail = detail;
	}

	//主键id
	private Integer id;
	//编号
	@Excel(name = "编号",orderNum = "0")
	private String number;
	//书名
	@Excel(name = "书名",orderNum = "1")
	private String name;
	//图书号
	@Excel(name = "图书编号",orderNum = "2")
	private String serialNumber;
	//出版社
	@Excel(name = "出版社",orderNum = "3")
	private String publisher;
	//作者
	@Excel(name = "作者",orderNum = "4")
	private String author;
	//创建人
	/*@Excel(name = "创建人",orderNum = "5")*/
	private int createBy;
	//创建时间
	@Excel(name = "创建时间",exportFormat ="yyyy-MM-dd", orderNum = "5")
	@JSONField(format = "yyyy-MM-dd")
	private Date creationDate;
	//册数，数量
	@Excel(name = "册数",orderNum = "6")
	private int total;
	//价格
	@Excel(name = "价格",orderNum = "7")
	private double price;
	//备注，详情
	@Excel(name = "备注",orderNum = "8")
	private String detail;
	//psid
	private Integer psid;
//	状态0：无备案，1，有备案
	private int status;
	//备用字段1
	private String reserve;
	//备用字段2
	private String spare;
	//备用字段3
	private String standby;

	//关联表
	private String sname;

	
	
	
	public Integer getPsid() {
		return psid;
	}

	public void setPsid(Integer psid) {
		this.psid = psid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String staffName) {
		this.sname = staffName;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
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
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
