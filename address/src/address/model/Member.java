package address.model;

public class Member {
	
	private int id; //pk
	private String name; // �̸�
	private String phone; // ��ȭ��ȣ
	private String address; // �ּ�
	
	// �׷� : ģ�� ȸ�� �б� ����
	private GroupType group; // ������ ���� ���� ���� �� �ִ�.

	
	// ����� ������
	public Member(String name, String phone, String address, GroupType group) {
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.group = group;
	}
	
	
	// ���̵����� ������
	public Member(int id, String name, String phone, String address, GroupType group) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.group = group;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public GroupType getGroup() {
		return group;
	}

	public void setGroup(GroupType group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return id +". " + name;
	}
	
}
