package DB.Doc;

public class Doc_DTO {
	
	private String dname;
	private String part;
	
	public Doc_DTO(String dname, String part) {
		this.dname = dname;
		this.part = part;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}
	
	@Override
	public String toString() {
		return "THRMS_DOC : " + dname + ", " + part;
	}
}
