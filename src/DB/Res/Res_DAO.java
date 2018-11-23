package DB.Res;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbUtil.DBConnection;
public class Res_DAO {
	
	private Connection con = DBConnection.getCon();
	private PreparedStatement pstmt;

	private static Res_DAO instance = new Res_DAO();
	
	public static Res_DAO getInstance( ) {
		return instance;
	}
	
	private Res_DAO() {
		
	}
	
	Res_DTO rdto = null;
	
	
	public int insertRes(Res_DTO dto) {					// 예약하기
		
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);

			String sql = "INSERT INTO THRMS_RES VALUES (THRMS_RES_SEQ.NEXTVAL,?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPart());
			pstmt.setString(3, dto.getDname());
			pstmt.setString(4, dto.getResDate());
			pstmt.setString(5, dto.getResTime());
			pstmt.setString(6, dto.getSymptoms());
			pstmt.setString(7, dto.getStatus());
			
			int n = pstmt.executeUpdate();
			
			int idx = 0;
			
			if(n>0) {
				pstmt = con.prepareStatement("SELECT THRMS_RES_SEQ.CURRVAL FROM DUAL");
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					idx = rs.getInt("currval");
				}
			}
			
			con.commit();
			
			return idx;
			
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
					if(pstmt != null)	pstmt.close();
				} catch (SQLException e) {	// close()
					System.out.println(e.getMessage());
				}
		}
	}
	
	public ArrayList<Res_DTO> getResListAll() {				// 모든 예약 리스트 받아오기
		
		ResultSet rs = null;
		ArrayList<Res_DTO> list = new ArrayList<>();
		
		
		try {
			String sql = "SELECT U.NAME, R.* " + 
					" FROM THRMS_RES R, THRMS_USER U" + 
					" WHERE R.ID = U.ID";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){

				rdto = new Res_DTO();
				
				rdto.setIdx(rs.getInt("idx"));
				rdto.setId(rs.getString("id"));
				rdto.setName(rs.getString("name"));
				rdto.setPart(rs.getString("part"));
				rdto.setDname(rs.getString("dname"));
				rdto.setResDate(rs.getString("resdate"));
				rdto.setResTime(rs.getString("restime"));
				rdto.setSymptoms(rs.getString("symptoms"));
				rdto.setStatus(rs.getString("status"));
				
				list.add(rdto);
			}
			
			return list;
		} catch (SQLException e) {	// pstmt = con.prepareStatement(sql);
			System.out.println(e.getMessage());
			return null;
		} finally {
			try {
				if(rs != null)		rs.close();
				if(pstmt != null)	pstmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public int deleteRes(int idx) {						// 예약 취소
		
		try {
			con.setAutoCommit(false);
			
			String sql = "DELETE FROM THRMS_RES WHERE IDX=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			
			int n = pstmt.executeUpdate();
			
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
				if(pstmt != null)	pstmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public ArrayList<Res_DTO> getResDate(String date) {				// 모든 예약 리스트 받아오기
		
		ArrayList<Res_DTO> list = new ArrayList<>();
		ResultSet rs = null;
		
		try {
			String sql ="SELECT (SELECT U.NAME FROM THRMS_USER U WHERE U.ID = R.ID) NAME,"
					+ " R.* FROM THRMS_RES R WHERE RESDATE = ?";
			
			System.out.println(date);
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, date);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				rdto = new Res_DTO();
				
				rdto.setName(rs.getString("name"));
				rdto.setIdx(rs.getInt("idx"));
				rdto.setPart(rs.getString("part"));
				rdto.setDname(rs.getString("dname"));
				rdto.setResDate(rs.getString("resDate"));
				rdto.setResTime(rs.getString("resTime"));
				rdto.setSymptoms(rs.getString("symptoms"));
				rdto.setStatus(rs.getString("status"));
				
				list.add(rdto);
				
			}
			
			return list;
		} catch (SQLException e) {	// pstmt = con.prepareStatement(sql);
			System.out.println(e.getMessage());
			return null;
		} finally {
			try {
				if(rs != null)		rs.close();
				if(pstmt != null)	pstmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	
public ArrayList<Res_DTO> getResDateId(String date, String id) {				// 모든 예약 리스트 받아오기
		
		ArrayList<Res_DTO> list = new ArrayList<>();
		ResultSet rs = null;
		
		try {
			String sql ="SELECT (SELECT U.NAME FROM THRMS_USER U WHERE U.ID = R.ID) NAME,"
					+ " R.* FROM THRMS_RES R WHERE RESDATE = ? and R.ID = ?";
			
			System.out.println(date);
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, date);
			pstmt.setString(2, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				rdto = new Res_DTO();
				
				rdto.setName(rs.getString("name"));
				rdto.setIdx(rs.getInt("idx"));
				rdto.setPart(rs.getString("part"));
				rdto.setDname(rs.getString("dname"));
				rdto.setResDate(rs.getString("resDate"));
				rdto.setResTime(rs.getString("resTime"));
				rdto.setSymptoms(rs.getString("symptoms"));
				rdto.setStatus(rs.getString("status"));
				
				list.add(rdto);
				
			}
			
			return list;
		} catch (SQLException e) {	// pstmt = con.prepareStatement(sql);
			System.out.println(e.getMessage());
			return null;
		} finally {
			try {
				if(rs != null)		rs.close();
				if(pstmt != null)	pstmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public ArrayList<Res_DTO> getResList(String id) {            //한 명
	    ResultSet rs = null;
	    ArrayList<Res_DTO> list = new ArrayList<>();
	    
	    
	    try {
	       String sql = "SELECT ROWNUM, U.NAME, R.*" + 
	             " FROM THRMS_RES R, THRMS_USER U" + 
	             " WHERE R.ID = U.ID AND R.ID=?";
	       pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, id);
	       rs = pstmt.executeQuery();
	       
	       while(rs.next()){
	
	          rdto = new Res_DTO();
	          
	          rdto.setRownum(rs.getInt("rownum"));
	          rdto.setIdx(rs.getInt("idx"));
	          rdto.setId(rs.getString("id"));
	          rdto.setName(rs.getString("name"));
	          rdto.setPart(rs.getString("part"));
	          rdto.setDname(rs.getString("dname"));
	          rdto.setResDate(rs.getString("resDate"));
	          rdto.setResTime(rs.getString("resTime"));
	          rdto.setSymptoms(rs.getString("symptoms"));
	          rdto.setStatus(rs.getString("status"));
	          
	          list.add(rdto);
	       }
	       
	       return list;
	    } catch (SQLException e) {   // pstmt = con.prepareStatement(sql);
	       System.out.println(e.getMessage());
	       return null;
	    } finally {
	       try {
	          if(rs != null)      rs.close();
	          if(pstmt != null)   pstmt.close();
	       } catch (SQLException e) {
	          System.out.println(e.getMessage());
	       }
	    }
	 }

}
