package DB.Doc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbUtil.DBConnection;

public class Doc_DAO {
	
	private Connection con = DBConnection.getCon();
	private PreparedStatement pstmt;
	
	public Doc_DAO() {
		
	}
	
	public ArrayList<Doc_DTO> getDocListAll() {				// 모든 의사 리스트 받아오기
		ResultSet rs = null;
		
		ArrayList<Doc_DTO> list = new ArrayList<Doc_DTO>();
		
		
		try {
			String sql = "SELECT * FROM THRMS_DOC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String dname = rs.getString("dname");
				String part = rs.getString("part");
				
				Doc_DTO dto = new Doc_DTO(dname, part);
				
				list.add(dto);
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

}
