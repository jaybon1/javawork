package address.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import address.db.DBConnection;
import address.db.DBUtils;
import address.model.GroupType;
import address.model.Member;

public class MemberDao {

	private final static String TAG = "MemberDao : ";

	private MemberDao() {
	}

	private static MemberDao instance = new MemberDao();

	public static MemberDao getInstance() {
		return instance;
	}

	// DML�� retrun���� int��. ���ϵǴ� ���� ����� ���� ������.

	// db������ �ߴٰ� �ٷ� ����� ���ϰ� ����. �������� ��������
	public int �߰�(Member member) {
		final String SQL = "INSERT INTO member(id, name, phone, address, groupType) VALUES(member_seq.NEXTVAL,?,?,?,?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. ��Ʈ�� ����
			conn = DBConnection.getConnection();

			// 2. ���۴ޱ�
			pstmt = conn.prepareStatement(SQL);

			// 3. ����ǥ �ϼ�
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getAddress());
			pstmt.setString(4, member.getGroupType().toString());

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

	public int ����(int memberId) {
		final String SQL = "DELETE FROM member WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. ��Ʈ�� ����
			conn = DBConnection.getConnection();

			// 2. ���۴ޱ�
			pstmt = conn.prepareStatement(SQL);

			// 3. ����ǥ �ϼ�
			pstmt.setInt(1, memberId);

			// 4. ��������
			int result = pstmt.executeUpdate();
			return result;

		} catch (Exception e) {
			// e.getMessage()�ش� ������ ������
			// e.getStackTrace() �ش� ������ �Ͼ�� ��� ������ ������
			System.out.println("���� ���� : " + e.getMessage());

		} finally { // ������ ����
			DBUtils.close(conn, pstmt);
		}

		return -1;
	}

	public int ����(Member member) {
		final String SQL = "UPDATE member SET name = ? , phone = ?, address = ?, grouptype = ? WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. ��Ʈ�� ����
			conn = DBConnection.getConnection();

			// 2. ���۴ޱ�
			pstmt = conn.prepareStatement(SQL);

			// 3. ����ǥ �ϼ�
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getAddress());
			pstmt.setString(4, member.getGroupType().toString());
			pstmt.setInt(5, member.getId());

			// 4. ��������
			int result = pstmt.executeUpdate();
			return result;

		} catch (Exception e) {
			// e.getMessage()�ش� ������ ������
			// e.getStackTrace() �ش� ������ �Ͼ�� ��� ������ ������
			System.out.println("���� ���� : " + e.getMessage());

		} finally { // ������ ����
			DBUtils.close(conn, pstmt);
		}

		return -1;
	}

	// DQL�� retrun���� ResultSEt == Cursor
	public Member �󼼺���(int memberId) {
		final String SQL = "SELECT * FROM member WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = null;

		try {
			// 1. ��Ʈ�� ����
			conn = DBConnection.getConnection();

			// 2. ���۴ޱ�
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, memberId);

			// 4. ��������(flush+rs�ޱ�)
			rs = pstmt.executeQuery();

			if (rs.next()) { // rs.next()�� ���ϰ��� �� ����
				member = Member.builder()
						.id(rs.getInt("id"))
						.name(rs.getString("name"))
						.phone(rs.getString("phone"))
						.address(rs.getString("address"))
						.groupType(GroupType.valueOf(rs.getString("groupType")))
						.build();
			}

			return member;

		} catch (Exception e) {
			// e.getMessage()�ش� ������ ������
			// e.getStackTrace() �ش� ������ �Ͼ�� ��� ������ ������
			System.out.println(TAG + "��ü��� ���� : " + e.getMessage());

		} finally { // ������ ����
			DBUtils.close(conn, pstmt, rs);
		}

		return null;
	}

	public List<Member> ��ü���() {
		final String SQL = "SELECT * FROM member ORDER BY id";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Member> members = new ArrayList<>();

		try {
			// 1. ��Ʈ�� ����
			conn = DBConnection.getConnection();

			// 2. ���۴ޱ�
			pstmt = conn.prepareStatement(SQL);

			// 4. ��������(flush+rs�ޱ�)
			rs = pstmt.executeQuery();

			while (rs.next()) { // rs.next()�� ���ϰ��� �� ����
				
				// .id()�� �� �Լ��� �ڱ��ڽ��� �����ϱ⶧���� �ڿ� ��� .�� ���� �� �ִ�
				// �̱����� �ƴϸ� new �Ǵ� ���̴�
				// ���� ������� �����ڿ� ���� ���� �� �־ �Ǽ��� ����
				members.add(Member.builder()
						.id(rs.getInt("id"))
						.name(rs.getString("name"))
						.phone(rs.getString("phone"))
						.address(rs.getString("address"))
						.groupType(GroupType.valueOf(rs.getString("groupType")))
						.build());
			}

			return members;

		} catch (Exception e) {
			// e.getMessage()�ش� ������ ������
			// e.getStackTrace() �ش� ������ �Ͼ�� ��� ������ ������
			System.out.println(TAG + "��ü��� ���� : " + e.getMessage());

		} finally { // ������ ����
			DBUtils.close(conn, pstmt, rs);
		}

		return null;
	}

	public List<Member> �׷���(GroupType groupType) {
		final String SQL = "SELECT * FROM member WHERE grouptype = ? ORDER BY id";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Member> members = new ArrayList<>();

		try {
			// 1. ��Ʈ�� ����
			conn = DBConnection.getConnection();

			// 2. ���۴ޱ�
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, groupType.toString());

			// 4. ��������(flush+rs�ޱ�)
			rs = pstmt.executeQuery();

			while (rs.next()) { // rs.next()�� ���ϰ��� �� ����
				
				// .id()�� �� �Լ��� �ڱ��ڽ��� �����ϱ⶧���� �ڿ� ��� .�� ���� �� �ִ�
				// �̱����� �ƴϸ� new �Ǵ� ���̴�
				// ���� ������� �����ڿ� ���� ���� �� �־ �Ǽ��� ����
				members.add(Member.builder()
						.id(rs.getInt("id"))
						.name(rs.getString("name"))
						.phone(rs.getString("phone"))
						.address(rs.getString("address"))
						.groupType(GroupType.valueOf(rs.getString("groupType")))
						.build());
			}

			return members;

		} catch (Exception e) {
			// e.getMessage()�ش� ������ ������
			// e.getStackTrace() �ش� ������ �Ͼ�� ��� ������ ������
			System.out.println(TAG + "�׷��� ���� : " + e.getMessage());

		} finally { // ������ ����
			DBUtils.close(conn, pstmt, rs);
		}

		return null;
	}
}
