package address.service;

import java.util.List;

import address.dao.MemberDao;
import address.model.GroupType;
import address.model.Member;

public class MemberService {
	
	
	private MemberService() {}
	
	private static MemberService instance = new MemberService();
	
	public static MemberService getInstance() {
		return instance;
	}
	
	private MemberDao memberDao = MemberDao.getInstance();
	
	public int 주소록추가(Member member) {
		
		// 3 DAO에 접근해서 추가 함수호출(Member)
		
		return memberDao.추가(member);
	}
	
	public int 주소록삭제(int memberId) {
		return memberDao.삭제(memberId);
	}
	
	public int 주소록수정(Member member) {
		return memberDao.수정(member);
	}
	
	
	public List<Member> 전체목록(){ // 트랜잭션 단위가 길어지면 이렇게 만드는 게 좋다
		return memberDao.전체목록();
	}
	
	public List<Member> 그룹목록(GroupType groupType){ // 트랜잭션 단위가 길어지면 이렇게 만드는 게 좋다
		return memberDao.그룹목록(groupType);
	}
	
	public Member 상세보기(int memberId) {
		return memberDao.상세보기(memberId);
	}
}
