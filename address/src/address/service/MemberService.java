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
	
	public int �ּҷ��߰�(Member member) {
		
		// 3 DAO�� �����ؼ� �߰� �Լ�ȣ��(Member)
		
		return memberDao.�߰�(member);
	}
	
	public int �ּҷϻ���(int memberId) {
		return memberDao.����(memberId);
	}
	
	public int �ּҷϼ���(Member member) {
		return memberDao.����(member);
	}
	
	
	public List<Member> ��ü���(){ // Ʈ����� ������ ������� �̷��� ����� �� ����
		return memberDao.��ü���();
	}
	
	public List<Member> �׷���(GroupType groupType){ // Ʈ����� ������ ������� �̷��� ����� �� ����
		return memberDao.�׷���(groupType);
	}
	
	public Member �󼼺���(int memberId) {
		return memberDao.�󼼺���(memberId);
	}
}
