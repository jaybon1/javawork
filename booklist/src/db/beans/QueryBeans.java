package db.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class QueryBeans {
	Connection conn;
	Statement stmt;
	ResultSet rs;
	
	public QueryBeans() {
		conn = null;
		stmt = null;
		rs = null;
	}
	
	public void getConnection() {
		try {
			conn = DBConnection.getConnection();			
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			stmt = conn.createStatement();		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void closeConnection() {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	public ArrayList getUserInfo() throws Exception{ // ²©¼è ¾ÆÁ÷ ¾ÈÀûÀ½
		StringBuffer sb = new StringBuffer();
		
		sb.append(" SELECT ");
		sb.append(" U_ID, U_NAME, U_PHONE, U_GRADE, WRITE_TIME ");
		sb.append(" FROM ");
		sb.append(" USER_INFO_SAMPLE ");
		sb.append(" ORDER BY ");
		sb.append(" WRITE_TIME ");
		
		rs = stmt.executeQuery(sb.toString());
		
		ArrayList<String> res = new ArrayList<>();
		while (rs.next()) {
			res.add(rs.getString(1));
			res.add(rs.getString(2));
			res.add(rs.getString(3));
			res.add(rs.getString(4));
			res.add(rs.getString(5));
		}
		System.out.println(sb.toString());
		return res;
	}
}
