package DB.User;

import java.sql.Date;

public class User_DTO {
	
	private int no = 0;
	private String id = null;
	private String pw = null;
	private String name = null;
	private String gen = null;
	private String birthDate = null;
	private String phone = null;
	
//	public Member_DTO(String id, String pw, String name, String gen, Date birthDate, String phone) {
//		this.id = id;
//		this.pw = pw;
//		this.name = name;
//		this.gen = gen;
//		this.birthDate = birthDate;
//		this.phone = phone;
//	}
	//public Member_DTO() {
//		setNo(0);
//		setId("-");
//		setPw("-");
//		setName("-");
//		setGen("-");
//		setBirthDate(null);
//		setPhone("-");
		
	//}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGen() {
		return gen;
	}

	public void setGen(String gen) {
		this.gen = gen;
	}
	
	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
}
