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

	// DML은 retrun값이 int다. 리턴되는 값은 변경된 행의 개수다.

	// db연결은 했다가 바로 끊어야 부하가 적다. 스택으로 관리하자
	public int 추가(Member member) {
		final String SQL = "INSERT INTO member(id, name, phone, address, groupType) VALUES(member_seq.NEXTVAL,?,?,?,?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. 스트림 연결
			conn = DBConnection.getConnection();

			// 2. 버퍼달기
			pstmt = conn.prepareStatement(SQL);

			// 3. 물음표 완성
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getAddress());
			pstmt.setString(4, member.getGroupType().toString());

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
		final String SQL = "SELECT * FROM member ORDER BY id";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Member> members = new ArrayList<>();

		try {
			// 1. 스트림 연결
			conn = DBConnection.getConnection();

			// 2. 버퍼달기
			pstmt = conn.prepareStatement(SQL);

			// 4. 쿼리전송(flush+rs받기)
			rs = pstmt.executeQuery();

			while (rs.next()) { // rs.next()는 리턴값이 참 거짓
				
				// .id()등 의 함수는 자기자신을 리턴하기때문에 뒤에 계속 .을 붙일 수 있다
				// 싱글톤이 아니며 new 되는 것이다
				// 순서 상관없이 생성자에 값을 넣을 수 있어서 실수가 없다
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
			// e.getMessage()해당 오류만 보여줌
			// e.getStackTrace() 해당 오류로 일어나는 모든 오류를 보여줌
			System.out.println(TAG + "전체목록 오류 : " + e.getMessage());

		} finally { // 무조건 실행
			DBUtils.close(conn, pstmt, rs);
		}

		return null;
	}

	public List<Member> 그룹목록(GroupType group) {
		return null;
	}

}
