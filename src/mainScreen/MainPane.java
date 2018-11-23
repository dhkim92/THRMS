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
	//��������
	private JPanel noticeMPane;
	private JPanel noticePane;
	private JLabel Ntitletxt;
	
	//���� �Խ���
	private JPanel qnaMPane;
	private JPanel qnaPane;
	private JLabel Qtitletxt;

	//������Ȳ
	private JPanel resMPane;	// �޷� �� �г�

	//��ư
	private JButton resBT;
	private JButton mypBT;
	private JButton HinfBT;
	
	private JButton more1;
	private JButton	more2;

	private JTextPane resjtf;


	//������ frame
	private JFrame fr ;
	private JPanel jp ;
	//-------------

	protected Color clr = new Color(50, 205, 50);	// �׵θ� ��

	

	public MainPane() {
		setBounds(0,0,1500,732);
		setBackground(Color.WHITE);

		Color color = new Color(29, 219, 22);	// �׵θ� ��
		LineBorder lb = new LineBorder(color, 5 , false); // �׵θ� Ŀ����
		setBorder(lb);	// �׵θ� ����
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
		
		Ntitletxt = new JLabel("���� ����");
		Ntitletxt.setForeground(Color.WHITE);
		Ntitletxt.setBounds(10,4,180,40);
		Ntitletxt.setFont(new Font("�������", Font.PLAIN, 30));
				
		more1 = new JButton("+������");
		more1.setFont(new Font("�������", Font.PLAIN, 15));
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
		
				Qtitletxt = new JLabel("���� ����");
				Qtitletxt.setForeground(Color.WHITE);
				Qtitletxt.setBounds(10,4,245,40);
				Qtitletxt.setFont(new Font("�������", Font.PLAIN, 30));
				more2 = new JButton("+������");
				more2.setFont(new Font("�������", Font.PLAIN, 15));
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
		resBT.setFont(new Font("�������", Font.PLAIN, 35));

		resBT.setText("��  ��");
		resBT.setForeground(Color.WHITE);
		resBT.addActionListener(this);

		add(resBT);

	}



	private void initmypBT() {
		mypBT = new JButton("���� ������");
		mypBT.setBounds(645, 506,220 ,100);
		mypBT.setBackground(clr);
		mypBT.setFont(new Font("�������", Font.PLAIN, 35));

		mypBT.setForeground(Color.WHITE);

		mypBT.addActionListener(this);

		add(mypBT);

	}



	private void initHinfBT() {
		HinfBT = new JButton();
		HinfBT.setBounds(1113, 506,200 ,100);
		HinfBT.setBackground(clr);
		HinfBT.setFont(new Font("�������", Font.PLAIN, 35));

		HinfBT.setText("���� ����");
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
	            JOptionPane.showMessageDialog(null, "������ ���������� ����� �� ���� �޴��Դϴ�.");
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
	         MainFrame.basicPane.add(new login.HospitalInfo());//new �������� �Է� -��ư �Է�
	         
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
