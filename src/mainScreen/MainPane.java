package mainScreen;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import board.Nboard;
import board.Qboard;
import myPageAdmin.MyPageAdmin;
import myPageMember.MyPageMember;
import reservation.ReservationPane;
import thrms.MainFrame;
import java.awt.Font;

public class MainPane extends JPanel implements ActionListener {

	private MainFrame mainFrame;
	private JPanel mainPane;
	//공지사항
	private JPanel noticeMPane;
	private JPanel noticePane;
	private JLabel Ntitletxt;
	
	//문의 게시판
	private JPanel qnaMPane;
	private JPanel qnaPane;
	private JLabel Qtitletxt;

	//예약현황
	private JPanel resMPane;	// 달력 들어갈 패널

	//버튼
	private JButton resBT;
	private JButton mypBT;
	private JButton HinfBT;
	
	private JButton more1;
	private JButton	more2;

	private JTextPane resjtf;


	//가시작 frame
	private JFrame fr ;
	private JPanel jp ;
	//-------------

	protected Color clr = new Color(50, 205, 50);	// 테두리 색

	

	public MainPane() {
		setBounds(0,0,1500,732);
		setBackground(Color.WHITE);

		Color color = new Color(29, 219, 22);	// 테두리 색
		LineBorder lb = new LineBorder(color, 5 , false); // 테두리 커스텀
		setBorder(lb);	// 테두리 설정
		setLayout(null);

		initNoticePane();
		initQnaPane();
		initResPane();

		initresBT();
		initmypBT();
		initHinfBT();

	}
	

	private void initNoticePane() {
		noticeMPane = new JPanel();
		noticeMPane.setLayout(null);
		noticeMPane.setBounds(199,100,500,180);
		noticeMPane.setBackground(Color.gray);

		NoticePane noticePane_1 = new NoticePane();
		noticeMPane.add(noticePane_1);
		noticePane = new JPanel();
		noticePane.setBounds(0, 0, 500, 40);
		noticePane_1.add(noticePane);
		noticePane.setBackground(clr);
		noticePane.setLayout(null);
		
		Ntitletxt = new JLabel("공지 사항");
		Ntitletxt.setForeground(Color.WHITE);
		Ntitletxt.setBounds(10,4,180,40);
		Ntitletxt.setFont(new Font("나눔고딕", Font.PLAIN, 30));
				
		more1 = new JButton("+더보기");
		more1.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		more1.setBounds(380,5,100,30);
						
		more1.addActionListener(this);
		
		noticePane.add(more1);
		noticePane.add(Ntitletxt);
		
		initNoticetitle();
				
		add(noticeMPane);
	}
	
	private void initNoticetitle() {
	}




	private void initQnaPane() {
		qnaMPane = new JPanel();
		qnaMPane.setLayout(null);
		qnaMPane.setBounds(199, 285, 500, 180);
		qnaMPane.setBackground(Color.gray);

		initqnatitle();
		
		QnaPane qnaPane_1 = new QnaPane();
		qnaMPane.add(qnaPane_1);
		qnaPane = new JPanel();
		qnaPane.setBounds(0, 0, 500, 40);
		qnaPane_1.add(qnaPane);
		qnaPane.setBackground(clr);
		qnaPane.setLayout(null);
		
				Qtitletxt = new JLabel("문의 사항");
				Qtitletxt.setForeground(Color.WHITE);
				Qtitletxt.setBounds(10,4,245,40);
				Qtitletxt.setFont(new Font("나눔고딕", Font.PLAIN, 30));
				more2 = new JButton("+더보기");
				more2.setFont(new Font("나눔고딕", Font.PLAIN, 15));
				more2.setBounds(380,5,100,30);
				
						more2.addActionListener(this);
						
								qnaPane.add(Qtitletxt);
								qnaPane.add(more2);
		add(qnaMPane);

	}
	private void initqnatitle() {
	}


	private void initResPane() {
		resMPane = new JPanel();
		resMPane.setLayout(null);
		resMPane.setBounds(713,100,600,365);
		resMPane.setBackground(Color.GRAY);

		resMPane.add(new ResPane());
		add(resMPane);

	}

	private void initresBT() {
		resBT = new JButton();
		resBT.setBounds(199, 506,200 ,100);
		resBT.setBackground(clr);
		resBT.setFont(new Font("나눔고딕", Font.PLAIN, 35));

		resBT.setText("예  약");
		resBT.setForeground(Color.WHITE);
		resBT.addActionListener(this);

		add(resBT);

	}



	private void initmypBT() {
		mypBT = new JButton("마이 페이지");
		mypBT.setBounds(645, 506,220 ,100);
		mypBT.setBackground(clr);
		mypBT.setFont(new Font("나눔고딕", Font.PLAIN, 35));

		mypBT.setForeground(Color.WHITE);

		mypBT.addActionListener(this);

		add(mypBT);

	}



	private void initHinfBT() {
		HinfBT = new JButton();
		HinfBT.setBounds(1113, 506,200 ,100);
		HinfBT.setBackground(clr);
		HinfBT.setFont(new Font("나눔고딕", Font.PLAIN, 35));

		HinfBT.setText("병원 정보");
		HinfBT.setForeground(Color.WHITE);
		HinfBT.addActionListener(this);

		add(HinfBT);

	}
	
	public void getMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	   @Override
	   public void actionPerformed(ActionEvent e) {
	      if(e.getSource() == mypBT) {
	         if(MainFrame.id.equals("admin")) {
	            MainFrame.basicPane.removeAll();
	            MainFrame.basicPane.add(new MyPageAdmin());
	            
	         } else {
	            MainFrame.basicPane.removeAll();
	            MainFrame.basicPane.add(new MyPageMember(mainFrame));
	         }
	         
	         MainFrame.NmenuBoard.setForeground(Color.white);
	         MainFrame.QmenuBoard.setForeground(Color.white);
	         MainFrame.menuRSV.setForeground(Color.white);
	         MainFrame.menuMyPage.setForeground(Color.DARK_GRAY);
	         MainFrame.menuHosInfo.setForeground(Color.white);
	             
	         MainFrame.basicPane.validate();
	         MainFrame.basicPane.repaint();
	         
	      }else if (e.getSource() == resBT) {
	         if(MainFrame.id.equals("admin")) {
	            JOptionPane.showMessageDialog(null, "관리자 계정에서는 사용할 수 없는 메뉴입니다.");
	         } else {
	            MainFrame.basicPane.removeAll();
	            ReservationPane resPane = new ReservationPane();
	            resPane.setUserInfo();               //   *
	            MainFrame.basicPane.add(resPane);
	            
	            MainFrame.NmenuBoard.setForeground(Color.white);
		        MainFrame.QmenuBoard.setForeground(Color.white);
	            MainFrame.menuRSV.setForeground(Color.DARK_GRAY);
	            MainFrame.menuMyPage.setForeground(Color.white);
	            MainFrame.menuHosInfo.setForeground(Color.white);
	            
	            MainFrame.basicPane.validate();
	            MainFrame.basicPane.repaint();
	         }         

	         
	      }else if (e.getSource() == HinfBT) {
	         
	         MainFrame.basicPane.removeAll();
	         MainFrame.basicPane.add(new login.HospitalInfo());//new 병원정보 입력 -버튼 입력
	         
	         MainFrame.NmenuBoard.setForeground(Color.white);
	         MainFrame.QmenuBoard.setForeground(Color.white);
	         MainFrame.menuRSV.setForeground(Color.white);
	         MainFrame.menuMyPage.setForeground(Color.white);
	         MainFrame.menuHosInfo.setForeground(Color.DARK_GRAY);

	         MainFrame.basicPane.validate();
	         MainFrame.basicPane.repaint();
	      }
	      else if (e.getSource() == more1) {

	         MainFrame.basicPane.removeAll();
	         MainFrame.basicPane.add(new Nboard());
	         
	         MainFrame.NmenuBoard.setForeground(Color.DARK_GRAY);
	         MainFrame.QmenuBoard.setForeground(Color.white);
	         MainFrame.menuRSV.setForeground(Color.white);
	         MainFrame.menuMyPage.setForeground(Color.white);
	         MainFrame.menuHosInfo.setForeground(Color.white);

	         MainFrame.basicPane.validate();
	         MainFrame.basicPane.repaint();
	         
	      }else if (e.getSource() == more2) {

	         MainFrame.basicPane.removeAll();
	         MainFrame.basicPane.add(new Qboard());
	         
	         MainFrame.NmenuBoard.setForeground(Color.white);
	         MainFrame.QmenuBoard.setForeground(Color.DARK_GRAY);
	         MainFrame.menuRSV.setForeground(Color.white);
	         MainFrame.menuMyPage.setForeground(Color.white);
	         MainFrame.menuHosInfo.setForeground(Color.white);

	         MainFrame.basicPane.validate();
	         MainFrame.basicPane.repaint();
	      }

	   }
}
