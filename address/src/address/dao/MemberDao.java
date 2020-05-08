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
	
	
	// DML�� retrun���� int��. ���ϵǴ� ���� ����� ���� ������.
	
	// db������ �ߴٰ� �ٷ� ����� ���ϰ� ����. �������� ��������
	public int �߰�(Member m) {
		final String SQL = "INSERT INTO member(id, name, phone, address, group) VALUES(member_seq.NEXTVAL,?,?,?,?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1. ��Ʈ�� ����
			conn = DBConnection.getConnection();
			
			// 2. ���۴ޱ�
			pstmt = conn.prepareStatement(SQL);
			
			// 3. ����ǥ �ϼ�
			pstmt.setString(1, m.getName());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getAddress());
			pstmt.setString(4, m.getGroup().toString());
			
			// 4. ��������
			int result = pstmt.executeUpdate();
			return result;
			
			
		} catch (Exception e) {
			// e.getMessage()�ش� ������ ������
			// e.getStackTrace() �ش� ������ �Ͼ�� ��� ������ ������
			System.out.println("�߰� ���� : " + e.getMessage());
			
		} finally { // ������ ����
			DBUtils.close(conn, pstmt);
		}
		
		
		return -1;
	}
	
	public int ����(int id) {
		return -1;
	}
	
	public int ����(Member m) {
		return -1;
	}
	
	
	// DQL�� retrun���� ResultSEt == Cursor
	public Member �󼼺���(int id) {
		return null;
	}
	
	public List<Member> ��ü���() {
		return null;
	}
	
	public List<Member> �׷���(GroupType group) {
		return null;
	}
	
}


