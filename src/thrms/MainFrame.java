// 메인 프레임

package thrms;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import board.Nboard;
import board.Qboard;
import login.HospitalInfo;
import login.LoginFrame;
import mainScreen.MainPane;
import msg.MsgList;
import myPageAdmin.MyPageAdmin;
import myPageMember.MyPageMember;
import reservation.ReservationPane;

public class MainFrame extends JFrame implements MouseListener {

	private Container root; // 루트 패널

	public static JPanel basicPane; // 기초 패널
	private JPanel menuPane; // 메뉴 패널

	private JPanel brandPane; // 브랜드 로고 패널
	private JPanel logoutPane; // 로그아웃 패널
	private JLabel logoutlbl;

	public static JLabel menuRSV; // 예약 레이블
	public static JLabel NmenuBoard; // N게시판 레이블
	public static JLabel QmenuBoard; // Q게시판 레이블
	public static JLabel menuMyPage; // 마이페이지 레이블
	public static JLabel menuHosInfo; // 병원 정보

	private JLabel member; // 회원
	private JLabel memberx; // 님 환영합니다

	private ImageIcon img;	// 아이콘

	protected Color color = new Color(50, 205, 50);	// 테두리 색
	protected LineBorder lb = new LineBorder(color, 5 , false); // 테두리 커스텀

	public static String id;		//	*
	public static String name;		//	*
	public static String pw;		//	*


	public MainFrame() {
		super("THRMS(트름) - Trillion Hospital Reservation Management System");
		setBounds(200,50,1518,900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		intiroot();

		setVisible(true);
	}

	public void setIdPwName(String id, String name, String pw) {		//	*
		this.id = id;
		this.name = name;
		this.pw = pw;

		member.setText(name);
	}

	private void intiroot() {
		root = getContentPane();
		root.setLayout(null);
		root.setBackground(color);

		initMenuPane();
		initBasicPane();

		root.add(menuPane);
		root.add(basicPane);
	}

	private void initMenuPane() {
		menuPane = new JPanel();
		menuPane.setBounds(0,0,1518,120);
		menuPane.setBackground(color);
		menuPane.setLayout(null);

		initMenuLable();
		initBrandLogo();
		initMemberLable();
		initMemberx();
		initLogout();

		menuPane.add(brandPane); // 브랜드 로고 패널
		menuPane.add(logoutPane); // 로그아웃 패널

		menuPane.add(menuRSV);
		menuPane.add(NmenuBoard);
		menuPane.add(QmenuBoard);
		menuPane.add(menuMyPage);
		menuPane.add(menuHosInfo);

		menuPane.add(member);

		menuPane.add(memberx);
	}

	private void initLogout() {
		logoutPane = new JPanel();

		logoutPane.setBounds(1380, 55, 80, 30);
		logoutPane.setBackground(color);
		logoutlbl = new JLabel("로그아웃");
		logoutlbl.setFont(new Font("나눔고딕", Font.BOLD, 15));
		logoutlbl.addMouseListener(this);

		logoutPane.add(logoutlbl);
	}

	private void initMemberx() {
		memberx = new JLabel("님 환영합니다");

		memberx.setBounds(1261,44,130,50);
		memberx.setFont(new Font("나눔고딕", Font.PLAIN, 15)); // 글자 크기
	}

	private void initMemberLable() {
		member = new JLabel("");			//	*

		member.setBounds(1188, 40, 99, 50);
		member.setFont(new Font("나눔고딕", Font.PLAIN, 20)); // 글자 크기
		member.addMouseListener(this);
	}

	private void initBrandLogo() {
		img = new ImageIcon("./src/image/bi.png");
		brandPane = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {

				g.drawImage(img.getImage(), 0, 0, getSize().width, getSize().height, null);

				setOpaque(false);

				super.paintComponent(g);
			}
		};

		brandPane.setBounds(15, 10, 130, 100);
		brandPane.addMouseListener(this);
	}

	private void initMenuLable() {
		menuRSV = new JLabel("예약하기");
		NmenuBoard = new JLabel("공지 게시판");
		QmenuBoard = new JLabel("문의 게시판");
		menuMyPage = new JLabel("마이페이지");
		menuHosInfo = new JLabel("병원정보");

		menuRSV.setBounds(197, 40, 150, 50);
		NmenuBoard.setBounds(350, 40, 190, 50);
		QmenuBoard.setBounds(550, 40, 190, 50);
		menuMyPage.setBounds(750, 40, 190, 50);
		menuHosInfo.setBounds(950,40, 150, 50);

		menuRSV.addMouseListener(this);
		NmenuBoard.addMouseListener(this);
		QmenuBoard.addMouseListener(this);
		menuMyPage.addMouseListener(this);
		menuHosInfo.addMouseListener(this);

		menulb(menuRSV);
		menulb(NmenuBoard);
		menulb(QmenuBoard);
		menulb(menuMyPage);
		menulb(menuHosInfo);
	}

	private void menulb(JComponent c) {
		c.setFont(new Font("나눔고딕", Font.BOLD, 25)); // 글자 크기
		c.setForeground(Color.white);
	}

	private void initBasicPane() {

		basicPane = new JPanel();
		basicPane.setBounds(0,120,1500,732);
		basicPane.setLayout(null);
		basicPane.setBackground(Color.black);
		basicPane.setBorder(lb);

		MainPane mainPane = new MainPane();
		mainPane.getMainFrame(this);
		basicPane.add(mainPane);
	}

	public static void main(String[] args) {
		new MainFrame();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == brandPane) {
			basicPane.removeAll();
			basicPane.add(new MainPane());

			menuRSV.setForeground(color.white);
			NmenuBoard.setForeground(color.white);
			QmenuBoard.setForeground(color.white);
			menuMyPage.setForeground(color.white);
			menuHosInfo.setForeground(color.white);

			basicPane.validate();
			basicPane.repaint();
		}

		if(e.getSource() == member) {
			if(id.equals("admin")) {

			} else {
				new MsgList();
			}
		}

		if(e.getSource() == menuRSV) {
			if(id.equals("admin")) {
				JOptionPane.showMessageDialog(null, "관리자 계정에서는 사용할 수 없는 메뉴입니다.");
			} else {
				basicPane.removeAll();
				ReservationPane resPane = new ReservationPane();
				resPane.setUserInfo();               //   *
				basicPane.add(resPane);

				menuRSV.setForeground(color.DARK_GRAY);
				NmenuBoard.setForeground(color.white);
				QmenuBoard.setForeground(color.white);
				menuMyPage.setForeground(color.white);
				menuHosInfo.setForeground(color.white);

				basicPane.validate();
				basicPane.repaint();
			}
			
		} else if(e.getSource() == menuMyPage ) {
			if(id.equals("admin")) {
				basicPane.removeAll();
				basicPane.add(new MyPageAdmin());

			} else {
				basicPane.removeAll();
				basicPane.add(new MyPageMember(this));
			}

			menuRSV.setForeground(color.white);
			NmenuBoard.setForeground(color.white);
			QmenuBoard.setForeground(color.white);
			menuMyPage.setForeground(color.DARK_GRAY);
			menuHosInfo.setForeground(color.white);

			basicPane.validate();
			basicPane.repaint();
		} else if(e.getSource() == menuHosInfo) {
			// 병원 정보
			basicPane.removeAll();

			basicPane.add(new HospitalInfo());

			menuRSV.setForeground(color.white);
	        NmenuBoard.setForeground(color.white);
	        QmenuBoard.setForeground(color.white);
	        menuMyPage.setForeground(color.white);
	        menuHosInfo.setForeground(color.DARK_GRAY);

			basicPane.validate();
			basicPane.repaint();

		} else if(e.getSource() == NmenuBoard) {// 공지사항 게시판
			basicPane.removeAll();
			basicPane.add(new Nboard());

			menuRSV.setForeground(color.white);
	        NmenuBoard.setForeground(color.DARK_GRAY);
	        QmenuBoard.setForeground(color.white);
	        menuMyPage.setForeground(color.white);
	        menuHosInfo.setForeground(color.white);
			
			basicPane.validate();
			basicPane.repaint();

		}else if(e.getSource() == QmenuBoard) {// 문의사항 게시판
			basicPane.removeAll();
			basicPane.add(new Qboard());

			menuRSV.setForeground(color.white);
	        NmenuBoard.setForeground(color.white);
	        QmenuBoard.setForeground(color.DARK_GRAY);
	        menuMyPage.setForeground(color.white);
	        menuHosInfo.setForeground(color.white);
			
			basicPane.validate();
			basicPane.repaint();       	        
		}

		if(e.getSource() == logoutlbl) {
			this.id = null;
			this.pw = null;
			this.name = null;

			new LoginFrame();
			dispose();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
