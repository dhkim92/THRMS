package DB.Msg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dbUtil.DBConnection;

public class Msg_DAO {
	
	private Connection con = DBConnection.getCon(); 
	private PreparedStatement pst = null;
	private Statement st = null;
	
	private static Msg_DAO instance = new Msg_DAO();
	
	private Msg_DAO() {	}
	
	public static Msg_DAO getInstance() {
		return instance;
	}
	
	
	public ArrayList<Msg_DTO> getMsgList() { // 쪽지함 전부 가져오기
		
		ResultSet rs = null;
		ArrayList<Msg_DTO> list = new ArrayList<>();
		
		String sql = "Select * from thrms_msg order by idx desc";
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				Msg_DTO mdto = new Msg_DTO();
				mdto.setIdx(rs.getInt("idx"));
				mdto.setRecipient(rs.getString("recipient"));
				mdto.setTitle(rs.getString("title"));
				mdto.setText(rs.getString("text"));
				mdto.setWdate(rs.getString("wdate"));
				mdto.setWtime(rs.getString("wtime"));
				mdto.setSender(rs.getString("sender"));
				
				list.add(mdto);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
				try {
					if(rs != null)rs.close();
					if(st != null)st.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		}
		return list;
	}
	
	public ArrayList<Msg_DTO> getMsgUser(String id) { // 쪽지함 전부 가져오기
			
			ResultSet rs = null;
			ArrayList<Msg_DTO> list = new ArrayList<>();
			
			String sql = "Select M.* from thrms_msg M, thrms_user U where M.RECIPIENT = U.id and RECIPIENT = ? order by M.idx desc";
			
			try {
				pst = con.prepareStatement(sql);
				pst.setString(1, id);
				
				rs = pst.executeQuery();
				
				while(rs.next()) {
					
					Msg_DTO mdto = new Msg_DTO();
					mdto.setIdx(rs.getInt("IDX"));
					mdto.setRecipient(rs.getString("RECIPIENT"));
					mdto.setTitle(rs.getString("TITLE"));
					mdto.setText(rs.getString("TEXT"));
					mdto.setWdate(rs.getString("WDATE"));
					mdto.setWtime(rs.getString("WTIME"));
					mdto.setSender(rs.getString("SENDER"));
					
					list.add(mdto);
					
				}
				
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
					try {
						if(rs != null)rs.close();
						if(st != null)st.close();
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
			}
			return list;
		}
	
	public Msg_DTO getMsg(String id) { // 쪽지함 전부 가져오기
		
		Msg_DTO mdto = new Msg_DTO();;
		ResultSet rs = null;
		
		String sql = "Select M.* from thrms_msg M, thrms_user U where M.RECIPIENT = U.id and RECIPIENT = ? order by M.idx desc";
		
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, id);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				
				mdto.setIdx(rs.getInt("IDX"));
				mdto.setRecipient(rs.getString("RECIPIENT"));
				mdto.setTitle(rs.getString("TITLE"));
				mdto.setText(rs.getString("TEXT"));
				mdto.setWdate(rs.getString("WDATE"));
				mdto.setWtime(rs.getString("WTIME"));
				mdto.setSender(rs.getString("SENDER"));
				
			}
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
				try {
					if(rs != null)rs.close();
					if(st != null)st.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		}
		return mdto;
	}

	public int insertMsg(Msg_DTO mdto) {
		
		String sql = "insert into thrms_msg values (thrms_msg_seq.nextval, ?, ?, ?, ?, ?, ?)";
		ResultSet rs = null;
		
		try {
			pst = con.prepareStatement(sql);
			
			pst.setString(1, mdto.getRecipient());
			pst.setString(2, mdto.getTitle());
			pst.setString(3, mdto.getText());
			pst.setString(4, mdto.getWdate());
			pst.setString(5, mdto.getWtime());
			pst.setString(6, mdto.getSender());
			
			int n = pst.executeUpdate();
			
			int idx = 0;
			if(n>0) {
				pst = con.prepareStatement("SELECT THRMS_MSG_SEQ.CURRVAL FROM DUAL");
				rs = pst.executeQuery();
				
				while(rs.next()) {
					idx = rs.getInt("currval");
				}
			}
			return idx;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println(e.getMessage());
			}
			return -1;
		} finally {
			
				try {
					if(rs != null) rs.close();
					if(pst != null) pst.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		}
		
	}
	
	public int deleteMsg(int idx) {                  // 예약 취소
	      
	      try {
	         con.setAutoCommit(false);
	         
	         String sql = "DELETE FROM THRMS_MSG WHERE IDX=?";
	         pst = con.prepareStatement(sql);
	         pst.setInt(1, idx);
	         
	         int n = pst.executeUpdate();
	         
	         con.commit();
	         
	         return n;         
	      } catch (SQLException e) {   // pstmt = con.prepareStatement(sql);
	         try {
	            con.rollback();
	         } catch (SQLException e1) {   // con.rollback();
	            System.out.println(e1.getMessage());
	         }
	         System.out.println(e.getMessage());
	         return -1;
	      } finally {
	         try {
	            if(pst != null)   pst.close();
	         } catch (SQLException e) {
	            System.out.println(e.getMessage());
	         }
	      }
	   }
	
}