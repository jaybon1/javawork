package address.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import address.db.DBConnection;
import address.db.DBUtils;
import address.model.GroupType;
import address.model.Member;

public class MemberDao {
	
	private MemberDao() {}
	
	private static MemberDao instance = new MemberDao();
	
	public static MemberDao getInstance() {
		return instance;
	}
	
	
	// DML은 retrun값이 int다. 리턴되는 값은 변경된 행의 개수다.
	
	// db연결은 했다가 바로 끊어야 부하가 적다. 스택으로 관리하자
	public int 추가(Member m) {
		final String SQL = "INSERT INTO member(id, name, phone, address, group) VALUES(member_seq.NEXTVAL,?,?,?,?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1. 스트림 연결
			conn = DBConnection.getConnection();
			
			// 2. 버퍼달기
			pstmt = conn.prepareStatement(SQL);
			
			// 3. 물음표 완성
			pstmt.setString(1, m.getName());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getAddress());
			pstmt.setString(4, m.getGroup().toString());
			
			// 4. 쿼리전송
			int result = pstmt.executeUpdate();
			return result;
			
			
		} catch (Exception e) {
			// e.getMessage()해당 오류만 보여줌
			// e.getStackTrace() 해당 오류로 일어나는 모든 오류를 보여줌
			System.out.println("추가 오류 : " + e.getMessage());
			
		} finally { // 무조건 실행
			DBUtils.close(conn, pstmt);
		}
		
		
		return -1;
	}
	
	public int 삭제(int id) {
		return -1;
	}
	
	public int 수정(Member m) {
		return -1;
	}
	
	
	// DQL은 retrun값이 ResultSEt == Cursor
	public Member 상세보기(int id) {
		return null;
	}
	
	public List<Member> 전체목록() {
		return null;
	}
	
	public List<Member> 그룹목록(GroupType group) {
		return null;
	}
	
}


