package hr.manage.entity;

public class Sections {
    private Integer sectionid;

    private String sectionname;
    
    private Integer sid;

	public Integer getSectionid() {
		return sectionid;
	}

	public Sections() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sections(String sectionname, Integer sid) {
		super();
		this.sectionname = sectionname;
		this.sid = sid;
	}

	public Sections(Integer sectionid, String sectionname, Integer sid) {
		super();
		this.sectionid = sectionid;
		this.sectionname = sectionname;
		this.sid = sid;
	}

	public void setSectionid(Integer sectionid) {
		this.sectionid = sectionid;
	}

	public String getSectionname() {
		return sectionname;
	}

	public void setSectionname(String sectionname) {
		this.sectionname = sectionname;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}
}