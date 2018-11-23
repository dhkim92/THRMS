package DB.Board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbUtil.DBConnection;

public class NBoard_DAO {
	
	private Connection con = DBConnection.getCon();
	private PreparedStatement pstmt;
	
	private static NBoard_DAO instance = new NBoard_DAO();
	
	public static NBoard_DAO getInstance( ) {
		return instance;
	}
	public NBoard_DAO() {
		
	}
	
	NBoard_DTO ndto = null;
	
	public int insertnboard(NBoard_DTO dto) {	//공지사항 등록 글쓰기
		
		try {
			String sql = "INSERT INTO THRMS_NBOARD VALUES (THRMS_NBOARD_SEQ.NEXTVAL,?,?,?,TO_CHAR( TRUNC(SYSDATE),'YYYY-MM-DD' ) , "
					+ " TO_CHAR((SYSDATE) , 'HH24:MI:SS' ) )";
			
			pstmt = con.prepareStatement(sql);
		
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getText());
			
			int n = pstmt.executeUpdate();
			
			con.commit();
			
			return n;
			
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
				
			}
			System.out.println(e.getMessage());
			return -1 ;
			
		}finally {
			if(pstmt != null)
				try {
					pstmt.close();
					
				} catch (SQLException e) {
				System.out.println(e.getMessage());
				}
			
		}
	}
	
	public ArrayList<NBoard_DTO> getNBoardListAll(){	//모든 문의사항 받아오기
		
		ResultSet rs = null;
		ArrayList<NBoard_DTO> list = new ArrayList<>();
		
		try {
			String sql = "SELECT (SELECT U.NAME FROM THRMS_USER U WHERE U.ID = N.ID) NAME, N.*"
					+" FROM THRMS_NBOARD N, THRMS_USER U"
					+" WHERE N.ID = U.ID ORDER BY N.IDX DESC";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("aaaa");

			while(rs.next()) {
			
				ndto = new NBoard_DTO();
				ndto.setName(rs.getString("name"));
				ndto.setIdx(rs.getInt("idx"));
				ndto.setId(rs.getString("id"));
				ndto.setTitle(rs.getString("title"));
				ndto.setText(rs.getString("text"));
				ndto.setWdate(rs.getString("wdate"));
				ndto.setWtime(rs.getString("wtime"));
				
				list.add(ndto);
			}
			
			return list;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
			
		}finally {
				try {
					if(rs!= null) rs.close();
					if(pstmt != null) pstmt.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		}
		
		
	}
	
	public int deleteNTable(String idx) {
		
		try {
			con = DBConnection.getCon();
			con.setAutoCommit(false);
			
			String sql = "DELETE FROM THRMS_NBOARD WHERE idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, idx);
			
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
}
