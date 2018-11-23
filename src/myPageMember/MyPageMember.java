package myPageMember;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import DB.Msg.Msg_DAO;
import DB.Msg.Msg_DTO;
import DB.Res.Res_DAO;
import DB.Res.Res_DTO;
import msg.MsgList;
import thrms.MainFrame;

public class MyPageMember extends JPanel {

	//--------------------상단
	private MainFrame mainFrame;
	
	private JPanel MMres;  //상단기본패널
	private JPanel MMTopL;  //상단 추가패널
	private JPanel MMTopR;  //상단 추가패널2
	
	private JLabel MMcpL; //프로필 이름
	private JButton MMcpB; //프로필 버튼
	private ImageIcon profil;
	private String name;
	
	private JLabel MMcrL; // 진료 예약
	private JLabel MMcrL2; // 진료 예약 건수
	private int n;  //건수
	
	private JLabel MMcmL; // 쪽지함
	private JLabel MMcmL2; // 쪽지함 건수
	private int nn;  //쪽지함건수
	 //--------------------상단 끝
	
	//--------------------하단
	private DefaultTableModel dtm;
	private JTable jtable;
	private int row;   //받아올컬럼
	private JScrollPane jsp;
	private int Tablecount;
	
	Res_DAO rdao = Res_DAO.getInstance();
	Res_DTO rdto = new Res_DTO();
	Msg_DAO mdao = Msg_DAO.getInstance();
	Msg_DTO mdto = new Msg_DTO();
	
	String header[] = {"예약 날짜", "예약 시간", "진료 과목", "담당의", "예약 내역"};
	//--------------------하단 끝
	
	//기타
	private LineBorder lb;  // 라인보더 

	
	//생성자 --기본 백그라운드
	public MyPageMember(MainFrame mainFrame) {
		
		this.mainFrame = mainFrame;
	
		lb = new LineBorder(Color.BLACK, 1 , false);
		setBounds(0,0,1500,732);
		setBorder(lb);
		setBackground(Color.white);
		setLayout(null);
		MMT(); //상단 패널
		MMB();
	}
	
	public void MMB() { //하단
		
		String[][] contents = null;
		 
		ArrayList<Res_DTO> list = rdao.getResList(MainFrame.id);
		
		contents = new String[list.size()][5];
		Tablecount = 0;
		for(Res_DTO dto : list) {
			
		            contents[Tablecount][0] = dto.getResDate();
		            contents[Tablecount][1] = dto.getResTime();
		            contents[Tablecount][2] = dto.getPart();
		            contents[Tablecount][3] = dto.getDname();
		            contents[Tablecount][4] = "예약내역 확인하기";
		            
		            Tablecount++;
			}
	
		DefaultTableModel model = new DefaultTableModel(contents, header) {
		public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			} 	// 수정 불가
		};
		
		//테이블생성
		jtable = new JTable(model);
		jtable.getColumnModel().setColumnSelectionAllowed(true);	 //하나만 선택
		jtable.getTableHeader().setReorderingAllowed(false); //컬럼 이동불가
		jtable.getTableHeader().setResizingAllowed(false); //컬럼 사이즈 고정
		jtable.setFont(new Font("나눔고딕", Font.PLAIN, 13)); // 글자 크기
		jtable.setForeground(Color.black); //색상
		jtable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(jtable.getSelectedColumn()==4) {
					new Info(mainFrame, MainFrame.id, jtable.getSelectedRow());
				}
			};
		});
		
		MMcrL2.setText(jtable.getRowCount()+"건");  //예약 건수
		
		row = jtable.getSelectedRow();
		
		jsp = new JScrollPane(jtable);
		jsp.setBounds(145, 320, 1200, 370);
		
		add(jsp);
	}
	
	
	public void MMT() {  //상단 
	
		MMres = new JPanel();
		MMres.setBounds(145, 30, 1200, 280);
		MMres.setBackground(Color.LIGHT_GRAY);
		MMres.setLayout(null);
		MMTop();
		MMpro();
		add(MMres);
	}
	
	
	public void MMTop() {  //상단 추가패널
	
		MMTopL = new JPanel();
		MMTopL.setBounds(410, 20 ,380, 240);
		MMTopL.setBackground(Color.LIGHT_GRAY);
		MMTopL.setLayout(null);
		
		MMTopR = new JPanel();
		MMTopR.setBounds(800, 20, 380, 240);
		MMTopR.setBackground(Color.LIGHT_GRAY);
		MMTopR.setLayout(null);
		
		initMMres();
		initMMmsg();
		
		MMres.add(MMTopL);
		MMres.add(MMTopR);
	}
	
	
	public void initMMres() {   //상단  진료예약
		
		MMcrL = new JLabel("진료 예약");
		MMcrL.setBounds(130, 40, 200, 30);
		
		MMcrL2 = new JLabel();  //DB연동
		MMcrL2.setBounds(100, 90, 300, 100);
		
		menulb2(MMcrL);
		menulb3(MMcrL2);
		
		MMTopL.add(MMcrL);
		MMTopL.add(MMcrL2);
		
	}
	
	
	public void initMMmsg() {   //상단 쪽지함
		
		MMcmL = new JLabel("쪽지함");
		MMcmL.setFont(new Font("나눔고딕", Font.PLAIN, 18));
		MMcmL.setBounds(90, 40, 200, 30);
		
		ArrayList<Msg_DTO> list = mdao.getMsgUser(MainFrame.id);
			
		nn=list.size();
		MMcmL2 = new JLabel(nn+"건");  //DB연동
		MMcmL2.setFont(new Font("나눔고딕", Font.PLAIN, 75));
		
		MMcmL2.addMouseListener(new MouseAdapter() {
			@Override
		    public void mouseClicked(MouseEvent e) {
				new MsgList();
		    }
		});
		
		MMcmL2.setBounds(40, 90, 300, 100);
		
		menulb2(MMcmL);
		menulb3(MMcmL2);
		
		MMTopR.add(MMcmL);
		MMTopR.add(MMcmL2);
	}
	
	public void MMpro() {
		//아이콘	
		profil= new ImageIcon ("./src/myPageMember/pro.png");	
		MMcpB = new JButton(profil)	;
		MMcpB.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		MMcpB.setBounds(110,65,110,110);
		MMcpB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
				JDialog dialog = new PWConfirmDialog2(mainFrame);
				dialog.setVisible(true);
			}
		});
		
		//버튼 투명처리
		MMcpB.setBorderPainted(false);
		MMcpB.setFocusPainted(false);
		MMcpB.setContentAreaFilled(false);
		
		MMres.add(MMcpB);
		
		name = MainFrame.name;
		MMcpL = new JLabel(name);
		MMcpL.setFont(new Font("나눔고딕", Font.BOLD, 15));
		MMcpL.setBounds(140, 180, 200, 30);
		
		menulb2(MMcpL);
		MMres.add(MMcpL);
	}
	
	
	private void menulb(JComponent c) {
		c.setFont(c.getFont().deriveFont(25.0f)); // 글자 크기
		c.setForeground(Color.black);
	}
	
	private void menulb2(JComponent c) {
		c.setFont(new Font("나눔고딕", Font.PLAIN, 18)); // 글자 크기
		c.setForeground(Color.black);
	}
	
	private void menulb3(JComponent c) {
		c.setFont(new Font("나눔고딕", Font.PLAIN, 75)); // 글자 크기
		c.setForeground(Color.black);
	}
	
	private void menulb4(JComponent c) {
		c.setFont(c.getFont().deriveFont(10.0f)); // 글자 크기
		c.setForeground(Color.blue);
	}
}