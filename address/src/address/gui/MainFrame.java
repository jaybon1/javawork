package address.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import address.model.GroupType;
import address.model.Member;
import address.utils.MyStringParser;

public class MainFrame extends JFrame {
	
	
	private JFrame mainFrame = this;
	
	private Container backgroundPanel;
	
	private JPanel topPanel, menuPanel, listPanel;
	
	private JButton homeButton, frButton, coButton, scButton, faButton, addButton;
	
	private JList<Member> userList;
	
	private DefaultListModel<Member> listModel; // Jlist에 이것을 넣어야 한다.
	
	private JScrollPane jsPane;
	
	public MainFrame() {
		initObject();
		initData();
		initDesign();
		initListener();
		setVisible(true);
	}
	
	// new
	private void initObject() {
		backgroundPanel = getContentPane();
		
		topPanel = new JPanel();
		menuPanel = new JPanel();
		listPanel = new JPanel();
		
		homeButton = new JButton("주소록 전체");
		frButton = new JButton("친구");
		coButton = new JButton("회사");
		scButton = new JButton("학교");
		faButton = new JButton("가족");
		addButton = new JButton("추가");
		
		listModel = new DefaultListModel<>();
		userList = new JList<>(listModel);
		
		jsPane = new JScrollPane(userList); // 뒤의 패널색깔을 유지하려면 그냥 ScrollPane을 이용하자
	}
	
	// 데이터 초기화
	private void initData() {
		for (int i = 1; i < 31; i++) {
			listModel.addElement(new Member(i, "홍길동", "0102222", "부산시", GroupType.친구));
		}
	}
	
	// 디자인
	private void initDesign() {
		// 1. 기본세팅
		setTitle("주소록 메인");
		setSize(400, 500);
		setLocationRelativeTo(null); // 모니터 중앙에 배치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 2. 패널세팅
		backgroundPanel.setLayout(new BorderLayout());
		topPanel.setLayout(new GridLayout(2,1));
		menuPanel.setLayout(new GridLayout(1,4));
		listPanel.setLayout(new BorderLayout());
		
		// 3. 디자인
		userList.setFixedCellHeight(50); // 리스트각각의 높이
		topPanel.setPreferredSize(new Dimension(0, 100)); // 그리드레이아웃이라 가로를 0으로해도 자동으로 꽉차게된다.
		
		// 4. 패널에 컴포넌트 추가
		menuPanel.add(frButton);
		menuPanel.add(coButton);
		menuPanel.add(scButton);
		menuPanel.add(faButton);
		
		listPanel.add(jsPane);
		
		topPanel.add(homeButton);
		topPanel.add(menuPanel);
		
		backgroundPanel.add(topPanel, BorderLayout.NORTH);
		backgroundPanel.add(listPanel, BorderLayout.CENTER);
		backgroundPanel.add(addButton, BorderLayout.SOUTH);
	}
	
	// 리스너 등록
	private void initListener() {
		userList.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
//				System.out.println(userList.getSelectedIndex());
//				System.out.println(userList.getSelectedValue());
				int memberId = MyStringParser.getId(userList.getSelectedValue().toString());
				new DetailFrame(mainFrame, memberId);
			}
			
		});
		
		addButton.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddFrame(mainFrame);
			}
		});
	}
}
