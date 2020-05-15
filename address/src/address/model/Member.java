package address.model;

import lombok.Builder;
import lombok.Data;

@Data
public class Member {
	
	private int id; //pk
	private String name; // 이름
	private String phone; // 전화번호
	private String address; // 주소
	
	
	// 그룹 : 친구 회사 학교 가족
	private GroupType groupType; // 도메인 안의 값만 넣을 수 있다.
	
	
	@Builder
	public Member(int id, String name, String phone, String address, GroupType groupType) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.groupType = groupType;
	}

	
	@Override
	public String toString() {
		return id +". " + name;
	}
}
