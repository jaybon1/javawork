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
	
	private DefaultListModel<Member> listModel; // Jlist�� �̰��� �־�� �Ѵ�.
	
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
		
		homeButton = new JButton("�ּҷ� ��ü");
		frButton = new JButton("ģ��");
		coButton = new JButton("ȸ��");
		scButton = new JButton("�б�");
		faButton = new JButton("����");
		addButton = new JButton("�߰�");
		
		listModel = new DefaultListModel<>();
		userList = new JList<>(listModel);
		
		jsPane = new JScrollPane(userList); // ���� �гλ����� �����Ϸ��� �׳� ScrollPane�� �̿�����
	}
	
	// ������ �ʱ�ȭ
	private void initData() {
		for (int i = 1; i < 31; i++) {
			listModel.addElement(new Member(i, "ȫ�浿", "0102222", "�λ��", GroupType.ģ��));
		}
	}
	
	// ������
	private void initDesign() {
		// 1. �⺻����
		setTitle("�ּҷ� ����");
		setSize(400, 500);
		setLocationRelativeTo(null); // ����� �߾ӿ� ��ġ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 2. �гμ���
		backgroundPanel.setLayout(new BorderLayout());
		topPanel.setLayout(new GridLayout(2,1));
		menuPanel.setLayout(new GridLayout(1,4));
		listPanel.setLayout(new BorderLayout());
		
		// 3. ������
		userList.setFixedCellHeight(50); // ����Ʈ������ ����
		topPanel.setPreferredSize(new Dimension(0, 100)); // �׸��巹�̾ƿ��̶� ���θ� 0�����ص� �ڵ����� �����Եȴ�.
		
		// 4. �гο� ������Ʈ �߰�
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
	
	// ������ ���
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
