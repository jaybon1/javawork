package address.gui;

import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.JTextField;

import address.model.GroupType;
import address.model.Member;
import address.service.MemberService;

public class AddFrame extends JFrame {
	
	private final static String TAG = "AddFrame : ";

	private AddFrame addFrame = this;
	private MainFrame mainFrame;
	private Container backGroundPanel;
	private JPanel addPanel;
	private JLabel laName, laPhone, laAddress, laGroup;
	private JTextField tfName, tfPhone, tfAddress;
	private JComboBox<GroupType> cbGroup;
	private JButton addButton;
	
	// 싱글톤은 전역공간에서 생성해도된다.
	private MemberService memberService = MemberService.getInstance(); 

	public AddFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		initObject();
		initDesign();
		initListener();
		setVisible(true);
	}

	private void initObject() {
		backGroundPanel = getContentPane();
		laName = new JLabel("이름");
		laPhone = new JLabel("전화번호");
		laAddress = new JLabel("주소");
		laGroup = new JLabel("그룹");
		tfName = new JTextField(20);
		tfPhone = new JTextField(20);
		tfAddress = new JTextField(20);
		cbGroup = new JComboBox<>(GroupType.values()); // GroupType.values()은 배열이고 콤보박스에 들어가게 된다.
		addButton = new JButton("추가하기");
	}

	private void initDesign() {
		setTitle("주소록 추가하기");
		setSize(300, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		backGroundPanel.setLayout(new BorderLayout());
		addPanel = new JPanel();
		addPanel.setLayout(new GridLayout(4,2));
		
		
		addPanel.add(laName);
		addPanel.add(tfName);
		addPanel.add(laPhone);
		addPanel.add(tfPhone);
		addPanel.add(laAddress);
		addPanel.add(tfAddress);
		addPanel.add(laGroup);
		addPanel.add(cbGroup);
		addPanel.add(addButton);
		
		backGroundPanel.add(addPanel, BorderLayout.CENTER);
		backGroundPanel.add(addButton, BorderLayout.SOUTH);

	}

	private void initListener() {
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 1 텍스트필드에 있는 값을 가져와야한다. // 2 값을 멤버에 담음
				Member member = Member.builder()
						.name(tfName.getText())
						.phone(tfPhone.getText())
						.address(tfAddress.getText())
						.groupType(GroupType.valueOf(cbGroup.getSelectedItem().toString()))
						.build();
			
				int result = memberService.주소록추가(member);
				
				if(result == 1) {
					// 성공 = mainFrame에 값을 변경
					mainFrame.notifyUserList(); // Ui를 동기화 해주는 메서드(만들어야함)
					addFrame.dispose(); // 추가하기 버튼을 누르면 자신을 끈다
					mainFrame.setVisible(true); // 메인프레임을 다시 보이게한다
				}else {
					JOptionPane.showMessageDialog(null, "주소록 추가에 실패하였습니다");
				}
	

			}
		});
		
		addFrame.addWindowListener(new WindowAdapter() { // 창의 이벤트를 담당 (최소화 최대화 종료버튼)
			@Override
			public void windowClosed(WindowEvent e) {
				mainFrame.setVisible(true); // 메인프레임을 다시 보이게한다
			}
		});

	}
}
