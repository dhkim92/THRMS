package myPageAdmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DB.User.User_DAO;
import DB.User.User_DTO;


public class MemberManage extends JPanel{

	private static final long serialVersionUID = 1L;

	private JPanel memMngPane;
	
	private JScrollPane memMngTablePane; // 회원관리 테이블패널
	private JPanel memMngtxtPane; // 회원관리 레이블패널
	private JPanel memInfotxtPane; // 회원정보 레이블패널
	
	
	private JLabel memMngtxt; // 회원관리레이블
	private JTable memMngTable; // 회원관리테이블 레이블
	
	private JLabel memInfotxt;
	
	private String[] header = {"No","성명","성별","생년월일","휴대전화"};
	protected Color color = new Color(29, 219, 22);
	
	User_DTO udto = new User_DTO();
	User_DAO udao = User_DAO.getInstance();
	
	ArrayList<User_DTO> list = udao.getUserListAll();

	
	public MemberManage() {
		
		setBounds(0,0,1486,726);
		setBackground(Color.white);
		setLayout(null);

		memMngPane = new JPanel();
		memMngPane.setBounds(250,100,1000,560);
		memMngPane.setLayout(new BorderLayout());
		 
		initmemMngtxt();
		initmemMngTable();
		
		initmemInfotxt();
		
		add(memMngPane);
	}
	
	
	private void initmemInfotxt() {
		memInfotxtPane = new JPanel();
		memInfotxtPane.setBackground(Color.LIGHT_GRAY);
		
		memInfotxt = new JLabel("회 원 정 보");
		
		memInfotxtPane.add(memInfotxt);
		memInfotxt.setFont(memMngtxt.getFont().deriveFont(18.0f));
		
		memMngPane.add(memInfotxtPane, BorderLayout.NORTH);
	}



	private void initmemMngTable() {
		
		String[][] contents = null;
		
		 contents = new String[list.size()][5];
		 
        for (int i = 0; i < list.size(); i++) {
           udto = list.get(i);
           contents[i][0] = udto.getNo() + "";
           contents[i][1] = udto.getName();
           contents[i][2] = udto.getGen();
           contents[i][3] = udto.getBirthDate();
           contents[i][4] = udto.getPhone();
        }

		DefaultTableModel model = new DefaultTableModel(contents, header) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {return false;}
		}; // 테이블 수정 금지
		
		memMngTable = new JTable(model);
		
		memMngTable.getTableHeader().setReorderingAllowed(false);
		
		memMngTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					
					MemberInfo mi = new MemberInfo(list.get(memMngTable.getSelectedRow()));
					
					if(list.get(memMngTable.getSelectedRow()).getId().equals("admin")) {
						JOptionPane.showMessageDialog(null, "관리자 계정입니다");
					} else {
					mi.setVisible(true);
					}
				}
			}
		});
		
		memMngTablePane = new JScrollPane(memMngTable);
		memMngPane.add(memMngTablePane, BorderLayout.CENTER);
	}


	private void initmemMngtxt() {
		memMngtxtPane = new JPanel();
		memMngtxtPane.setBackground(color);
		
		memMngtxt = new JLabel("회원관리");
		memMngtxt.setFont(memMngtxt.getFont().deriveFont(20.0f)); // 글자 크기
		memMngtxt.setForeground(Color.white);
		
		memMngtxtPane.add(memMngtxt);
		memMngtxtPane.setBounds(250, 60, 200, 40);
		
		add(memMngtxtPane);
	}
}
