package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static Connection con = null;
	
	public static Connection getCon() {
		
		if(con == null) {	// ���α׷��� ���ۺ��� ������ �ϳ��� ���� ��ü�� ����ϱ� ���� ó��
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				String url = "jdbc:oracle:thin:@localhost:1521:XE";
				
				con = DriverManager.getConnection(url, "scott", "tiger");
				
				System.out.println("DB Connected!!");
			} catch (ClassNotFoundException e) {	// Class.forName
				System.out.println(e.getMessage());
			} catch (SQLException e) {	// DriverManager.getConnection
				System.out.println(e.getMessage());
			}
		}
			return con;
	}

}
