package DB.Board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbUtil.DBConnection;

public class QBoard_DAO {
	
	private Connection con = DBConnection.getCon();
	private PreparedStatement pstmt;
	
	private static QBoard_DAO instance = new QBoard_DAO();
	
	public static QBoard_DAO getInstance( ) {
		return instance;
	}
	public QBoard_DAO() {
		
	}
	
	QBoard_DTO qdto = null;
	
	public int insertqboard(QBoard_DTO dto) {	//문의사항 등록 글쓰기
		
		try {
			
			String sql = "INSERT INTO THRMS_QBOARD VALUES ( THRMS_QBOARD_SEQ.NEXTVAL,?,?,?,TO_CHAR(TRUNC(SYSDATE),'YYYY-MM-DD' ) , "
					+ " TO_CHAR((SYSDATE),'HH24:MI:SS' ) )";
			
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
	
	public ArrayList<QBoard_DTO> getQBoardListAll(){	//모든 문의사항 받아오기
		
		ResultSet rs = null;
		ArrayList<QBoard_DTO> list = new ArrayList<>();
		
		try {
			String sql = "SELECT (SELECT U.NAME FROM THRMS_USER U WHERE U.ID = Q.ID) NAME, Q.*"
					+" FROM THRMS_QBOARD Q, THRMS_USER U"
					+" WHERE Q.ID = U.ID ORDER BY Q.IDX DESC";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				qdto = new QBoard_DTO();
				qdto.setName(rs.getString("name"));
				qdto.setIdx(rs.getInt("idx"));
				qdto.setId(rs.getString("id"));
				qdto.setTitle(rs.getString("title"));
				qdto.setText(rs.getString("text"));
				qdto.setWdate(rs.getString("wdate"));
				qdto.setWtime(rs.getString("wtime"));
				
				list.add(qdto);
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
	
	public int deleteQTable(String idx) {
		
		try {
			con = DBConnection.getCon();
			con.setAutoCommit(false);
			
			String sql = "DELETE FROM THRMS_QBOARD WHERE idx=?";
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
