package address.gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import address.dao.MemberDao;
import address.model.GroupType;
import address.model.Member;
import address.service.MemberService;

public class DetailFrame extends JFrame {

	private DetailFrame detailFrame = this;
	private	MainFrame mainFrame;
	private int memberId; // mainFrame에서 넘어온 member의 id 값
	private Container backgroundPanel;
	private JLabel laName, laPhone, laAddress, laGroup;
	private JTextField tfName, tfPhone, tfAddress;
	private JComboBox<GroupType> cbGroup;
	private JButton updateButton, deleteButton;
	
	private MemberService memberService = MemberService.getInstance();

	public DetailFrame(MainFrame mainFrame, int memberId) {
		this.mainFrame = mainFrame;
		this.memberId = memberId;
		initObject();
		initData();
		initDesign();
		initListener();
		setVisible(true);
	}

	private void initObject() {
		backgroundPanel = getContentPane();
		laName = new JLabel("이름");
		laPhone = new JLabel("전화번호");
		laAddress = new JLabel("주소");
		laGroup = new JLabel("그룹");
		tfName = new JTextField(20);
		tfPhone = new JTextField(20);
		tfAddress = new JTextField(20);
		cbGroup = new JComboBox<>(GroupType.values()); // GroupType.values()은 배열이고 콤보박스에 들어가게 된다.
		updateButton = new JButton("수정하기");
		deleteButton = new JButton("삭제하기");
	}

	private void initData() { // 프레임들은 서비스에 접근해야한다. 단계별로 만들어야 협업이 가능하며 서비스가 있어야 트랜잭션을 만들 수 있다
		// DetailFrame -> MemberService의 상세보기 -> MemberDao의 상세보기 -> DB
		Member member = memberService.상세보기(memberId);
		tfName.setText(member.getName()); // setText는 repaint()를 들고 있음
		tfPhone.setText(member.getPhone());
		tfAddress.setText(member.getAddress());
		cbGroup.setSelectedItem(member.getGroupType());
	}

	private void initDesign() {
		setTitle("주소록 상세보기");
		setSize(300, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		backgroundPanel.setLayout(new GridLayout(5,2));
		
		backgroundPanel.add(laName);
		backgroundPanel.add(tfName);
		backgroundPanel.add(laPhone);
		backgroundPanel.add(tfPhone);
		backgroundPanel.add(laAddress);
		backgroundPanel.add(tfAddress);
		backgroundPanel.add(laGroup);
		backgroundPanel.add(cbGroup);
		backgroundPanel.add(updateButton);
		backgroundPanel.add(deleteButton);
	}

	private void initListener() {
		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				detailFrame.dispose();
			}
		});
		
		updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 서비스 연결 -수정
				Member member = Member.builder()
						.id(memberId)
						.name(tfName.getText())
						.phone(tfPhone.getText())
						.address(tfAddress.getText())
						.groupType(GroupType.valueOf(cbGroup.getSelectedItem().toString()))
						.build();
				
				int result = memberService.주소록수정(member);
				
				// result == 1이면 아래 로직 실행, 1이 아니면 다이얼로그 박스
				if(result == 1) {
					mainFrame.notifyUserList();
					detailFrame.dispose();
					mainFrame.setVisible(true);					
				} else {
					JOptionPane.showMessageDialog(null, "수정에 실패하였습니다");
				}
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 서비스 연결 -삭제
				int result = memberService.주소록삭제(memberId);
				
				// result == 1이면 아래 로직 실행, 1이 아니면 다이얼로그 박스
				if(result == 1) {
					mainFrame.notifyUserList();
					detailFrame.dispose();
					mainFrame.setVisible(true);					
				} else {
					JOptionPane.showMessageDialog(null, "삭제에 실패하였습니다");
				}
			}
		});
		
		detailFrame.addWindowListener(new WindowAdapter() { // 창의 이벤트를 담당 (최소화 최대화 종료버튼)
			@Override
			public void windowClosed(WindowEvent e) {
				mainFrame.setVisible(true); // 메인프레임을 다시 보이게한다
			}
		});
		
	}
}
