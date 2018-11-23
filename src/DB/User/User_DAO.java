package DB.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DB.Res.Res_DTO;
import dbUtil.DBConnection;

public class User_DAO {
	
	private Connection con;
	private PreparedStatement pst;
	private Statement st;
	
	User_DTO udto;
	
	private static User_DAO instance = new User_DAO();
	
	public static User_DAO getInstance( ) {
		return instance;
	}
	
	
	public User_DAO() {
		
	}
	
	
	
	public int insertUser(User_DTO insertUser) {
		
		try {
			con = DBConnection.getCon();
			con.setAutoCommit(false);

			String sql = "INSERT INTO THRMS_USER VALUES (THRMS_USER_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?)";
			pst = con.prepareStatement(sql);
			
			pst.setString(1, insertUser.getId());
			pst.setString(2, insertUser.getPw());
			pst.setString(3, insertUser.getName());
			pst.setString(4, insertUser.getGen());
			pst.setString(5, insertUser.getBirthDate());
			pst.setString(6, insertUser.getPhone());
			
			int n = pst.executeUpdate();
			
			con.commit();
			
			return n;
		} catch (SQLException e) {	// pstmt = con.prepareStatement(sql);
			try {
				con.rollback();
			} catch (SQLException e1) {	// con.rollback();
				System.out.println(e1.getMessage());
			}
			System.out.println(e.getMessage());
			return -1;
		} finally {
				try {
					if(pst != null)	pst.close();
					
				} catch (SQLException e) {	// close()
					
					System.out.println(e.getMessage());
				}
		}
		
	}
	
	public ArrayList<User_DTO> getUserListAll() {
	
		ResultSet rs = null;
		
		ArrayList<User_DTO> list = new ArrayList<>();
		
		try {
			con = DBConnection.getCon();
			
			String sql = "SELECT * FROM THRMS_USER";
			
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				udto = new User_DTO();
				
				udto.setNo(rs.getInt("no"));
				udto.setId(rs.getString("id"));
				udto.setPw(rs.getString("pw"));
				udto.setName(rs.getString("name"));
				udto.setGen(rs.getString("gen"));
				udto.setBirthDate(rs.getString("birthDate"));
				udto.setPhone(rs.getString("phone"));
				
				list.add(udto);
			}
			
			return list;
			
		} catch (SQLException e) {	// pstmt = con.prepareStatement(sql);
			System.out.println(e.getMessage());
			return null;
		} finally {
			try {
				if(rs != null)		rs.close();
				if(st != null)		st.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public boolean checkId(String id) {		// 아이디 중복확인
		boolean flag = false;
		ResultSet rs = null;
		
		try {
			con = DBConnection.getCon();
			
			String sql = "SELECT COUNT(*) COUNT FROM THRMS_USER WHERE ID = ?";
			
			pst = con.prepareStatement(sql);
			pst.setString(1, id);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {

				if(rs.getInt("count") == 1) {
					flag = false;
				} else if(rs.getInt("count") == 0) {
					flag = true;
				}
			}
			
			return flag;
			
		} catch (SQLException e) {	// pstmt = con.prepareStatement(sql);
			System.out.println(e.getMessage());
			return false;
		} finally {
			try {
				if(rs != null)		rs.close();
				if(pst != null)		pst.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public User_DTO getUserInfo(String name, String birthDate, String phone) {	// 아이디/비밀번호 찾기
		ResultSet rs = null;
		User_DTO dto = null;
		try {
			
			con = DBConnection.getCon();
			
			String sql = "SELECT * FROM THRMS_USER WHERE NAME=? AND BIRTHDATE=? AND PHONE=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, birthDate);
			pst.setString(3, phone);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				
				dto = new User_DTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setGen(rs.getString("gen"));
				dto.setBirthDate(rs.getString("birthDate"));
				dto.setPhone(rs.getString("phone"));
				
			} else {
				return null;
			}
		} catch (SQLException e) {	// pstmt = con.prepareStatement(sql);
			System.out.println(e.getMessage());
			return null;
		} finally {
			try {
				if(rs != null)		rs.close();
				if(pst != null)	pst.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return dto;
	}
	
	public ArrayList<User_DTO> getUseridpw(String id, String pw) {	
		ResultSet rs = null;
		ArrayList<User_DTO> list = new ArrayList<>();
		
		try {
			
			con = DBConnection.getCon();
			
			String sql = "SELECT ID, PW FROM THRMS_USER WHERE ID=? AND PW=?";
			pst = con.prepareStatement(sql);
			
			pst.setString(1, id);
			pst.setString(2, pw);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				udto = new User_DTO();
				
				udto.setId(rs.getString("id"));
				udto.setPw(rs.getString("pw"));
				udto.setName(rs.getString("name"));
				
				list.add(udto);
				
			} else {
				return null;
			}
			
		} catch (SQLException e) {	// pstmt = con.prepareStatement(sql);
			System.out.println(e.getMessage());
			return null;
		} finally {
			try {
				if(rs != null)		rs.close();
				if(pst != null)	pst.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return list;
	}
	
	public int deleteUser(String id) {
		
		try {
			con = DBConnection.getCon();
			con.setAutoCommit(false);
			
			String sql = "DELETE FROM THRMS_USER WHERE ID=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, id);
			
			int n = pst.executeUpdate();
			
			con.commit();
			
			return n;			
		} catch (SQLException e) {	// pstmt = con.prepareStatement(sql);
			try {
				con.rollback();
			} catch (SQLException e1) {	// con.rollback();
				System.out.println(e1.getMessage());
			}
			System.out.println(e.getMessage());
			return -1;
		} finally {
			try {
				if(pst != null)	pst.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public int updateUser(User_DTO dto) {
		
		try {
			con = DBConnection.getCon();
			con.setAutoCommit(false);
			
			String sql = "UPDATE THRMS_USER SET PHONE=? WHERE ID=?";
			pst = con.prepareStatement(sql);
	
			pst.setString(1, dto.getPhone());
			pst.setString(2, dto.getId());
			
			int n = pst.executeUpdate();
			
			con.commit();
			
			return n;
			
		} catch (SQLException e) {	// pstmt = con.prepareStatement(sql);
			try {
				con.rollback();
			} catch (SQLException e1) {	// con.rollback();
				System.out.println(e1.getMessage());
			}
			System.out.println(e.getMessage());
			return -1;
		} finally {
			try {
				if(pst != null)	pst.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		
	}
	
	public User_DTO getUserList(String id) {				// id에 해당하는 값 가져오기
		
		ArrayList<User_DTO> list = new ArrayList<>();
		ResultSet rs = null;
		
		try {
			String sql ="SELECT * FROM THRMS_USER WHERE ID = ?";
			
			pst = con.prepareStatement(sql);

			pst.setString(1, id);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				
				udto.setNo(rs.getInt("no"));
				udto.setId(rs.getString("id"));
				udto.setPw(rs.getString("pw"));
				udto.setName(rs.getString("name"));
				udto.setGen(rs.getString("gen"));
				udto.setBirthDate(rs.getString("birthDate"));
				udto.setPhone(rs.getString("phone"));
				
			}
			
			return udto;
		} catch (SQLException e) {	// pstmt = con.prepareStatement(sql);
			System.out.println(e.getMessage());
			return null;
		} finally {
			try {
				if(rs != null)		rs.close();
				if(pst != null)		pst.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public int updateUserinfo(String id, String pw, String phone) {
			
			try {
				con = DBConnection.getCon();
				con.setAutoCommit(false);
				
				String sql = "UPDATE THRMS_USER SET PHONE=?, PW =? WHERE ID = ?";
				pst = con.prepareStatement(sql);
		
				pst.setString(1, phone);
				pst.setString(2, pw);
				pst.setString(3, id);
				
				int n = pst.executeUpdate();
				
				con.commit();
				
				return n;
				
			} catch (SQLException e) {	// pstmt = con.prepareStatement(sql);
				try {
					con.rollback();
				} catch (SQLException e1) {	// con.rollback();
					System.out.println(e1.getMessage());
				}
				System.out.println(e.getMessage());
				return -1;
			} finally {
				try {
					if(pst != null)	pst.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			
	}
}
