package DB.Res;

public class Res_DTO {
	
	private int idx = 0;
	private int Rownum = 0;
	private String id = null;
	private String name = null;
	private String part = null;
	private String dname = null;
	private String resDate = null;
	private String resTime = null;
	private String Symptoms = null;
	
	private String status = null;

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getResDate() {
		return resDate;
	}

	public void setResDate(String resDate) {
		this.resDate = resDate;
	}

	public String getResTime() {
		return resTime;
	}

	public void setResTime(String resTime) {
		this.resTime = resTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	

	public int getRownum() {
		return Rownum;
	}

	public void setRownum(int rownum) {
		Rownum = rownum;
	}

	public String getSymptoms() {
		return Symptoms;
	}

	public void setSymptoms(String symptoms) {
		Symptoms = symptoms;
	}


	@Override
	public String toString() {
		return "THRMS_RES : " + idx + ", " + id + ", "+name+"," + part + ", " + dname 
				+ ", " + resDate + ", " + resTime + ", " + status;
	}
	


}
