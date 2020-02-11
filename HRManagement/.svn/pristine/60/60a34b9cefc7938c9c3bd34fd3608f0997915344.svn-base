package util;

import java.util.List;

public class Page <T> {
	private int currentPage;
	private int count;
	private int pageCount;
	private List<T> getlist;
	private String like;
	
	//状态
	private int status;
	//图书
	private String book;
	//消息
	private String message;
	private String clike;
	private String pub;
	private String snnumber;
	
	//psid
	private Integer psid;
	
	
	
	
	
	public String getSnnumber() {
		return snnumber;
	}
	public void setSnnumber(String snnumber) {
		this.snnumber = snnumber;
	}
	public String getPub() {
		return pub;
	}
	public void setPub(String pub) {
		this.pub = pub;
	}
	public Integer getPsid() {
		return psid;
	}
	public void setPsid(Integer psid) {
		this.psid = psid;
	}
	public String getClike() {
		return clike;
	}
	public void setClike(String clike) {
		this.clike = clike;
	}
	
	
	
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public String getLike() {
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int count,int size) {
		this.pageCount = count%size==0?count/size:(count/size+1);
	}
	public List<T> getGetlist() {
		return getlist;
	}
	public void setGetlist(List<T> getlist) {
		this.getlist = getlist;
	}
	
	
	
}
