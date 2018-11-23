package myPageMember;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DB.User.User_DAO;
import DB.User.User_DTO;
import login.LoginFrame;
import thrms.MainFrame;

public class MemberInfo  extends JFrame implements ActionListener, KeyListener{
	
	private MainFrame mainFrame;
	
	private Container root;
	JPanel btnPane; // 버튼패널
	JPanel btnPane2; // 버튼패널2
	JPanel infoPane; // 회원정보패널
	JPanel infoPaneget; // 회원정보패널2
	JPanel infoUp; // 회원정보 수정 패널
	JPanel basicPane;  //베이직패인
	JPanel basicPane2;  //갈아엎을패널
	
	//회원정보 고정
	JLabel IDtxt; // ID 레이블
	JLabel PWtxt; // PW 레이블
	JLabel Nametxt; // 성명 레이블
	JLabel Birthtxt; // 생년월일 레이블
	JLabel Gendertxt; // 성별 레이블
	JLabel Phonenumtxt; // 성별 레이블
	
	//회원정보 -db연동
	JLabel IDget; // ID 레이블
	JLabel PWget; // PW 레이블
	JLabel Nameget; // 성명 레이블
	JLabel Birthget; // 생년월일 레이블
	JLabel Genderget; // 성별 레이블
	JLabel Phonenumget; // 성별 레이블
	
	//회원정보 수정
	JTextField PWinput; // PW 레이블
	JTextField Phonenuminput; //전화번호
	JPasswordField Phonenuminputt;//
	String p = "패스워드";
	String n = "전화번호";
	private int k;
	private int k2;

	JButton Upbtn; // 업데이트 버튼
	JButton Okbtn; // 확인
	JButton Wdbtn; // 탈퇴버튼
	
	JButton Cancel; // 캔슬버튼
	JButton Okbtn2; // 정보수정 확인버튼
	
	User_DTO udto = new User_DTO();
	User_DAO udao = User_DAO.getInstance();

	
	public MemberInfo(MainFrame mainFrame) {
		super("회원 정보");
		
		this.mainFrame = mainFrame;
		
		//루트
		root = getContentPane();
		setBounds(1000,300,500,700);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		root.setLayout(new BorderLayout());
		
		initbtn();   //루트에 추가
		
		Basic();   //
		
	}
	
	private void Basic() {
		basicPane = new JPanel();
		basicPane.setSize(3000,3000);
		basicPane.setLayout(null);
		initinfo(basicPane);  // basicPane과 basicPane2에 추가
		initinfoget();  //  basicPane에 추가 
		root.add(basicPane);
	}
	

	private void initinfo(JPanel J) {
		infoPane = new JPanel();
		infoPane.setBounds(50, 30, 200, 400);
		infoPane.setLayout(new GridLayout(6, 0));
		
		Nametxt = new JLabel("성      명 ");
		Gendertxt = new JLabel("성      별");
		Gendertxt.setFont(new Font("나눔고딕", Font.BOLD, 20));
		Birthtxt = new JLabel("생년월일");
		Birthtxt.setFont(new Font("나눔고딕", Font.BOLD, 20));
		
		IDtxt = new JLabel("아  이  디");
		IDtxt.setFont(new Font("나눔고딕", Font.BOLD, 20));
		PWtxt = new JLabel("패스워드");
		PWtxt.setFont(new Font("나눔고딕", Font.BOLD, 20));
		Phonenumtxt = new JLabel("전화번호");
		Phonenumtxt.setFont(new Font("나눔고딕", Font.BOLD, 20));
		
		Font(Nametxt);
		Font(Gendertxt);
		Font(Birthtxt);
		Font(IDtxt);
		Font(PWtxt);
		Font(Phonenumtxt);
		
		infoPane.add(Nametxt);
		infoPane.add(Gendertxt);
		infoPane.add(Birthtxt);
		infoPane.add(IDtxt);
		infoPane.add(PWtxt);
		infoPane.add(Phonenumtxt);
		
		J.add(infoPane);
	}

	
	private void initinfoget() {  //회원정보 db가져오기
		infoPaneget = new JPanel();
		infoPaneget.setBounds(250, 30, 200, 400);
		infoPaneget.setLayout(new GridLayout(6, 0));
	
		udto = udao.getUserList(MainFrame.id);
		
		Nameget = new JLabel(udto.getName());
		Nameget.setFont(new Font("나눔고딕", Font.BOLD, 20));
		Genderget = new JLabel(udto.getGen());
		Genderget.setFont(new Font("나눔고딕", Font.BOLD, 20));
		Birthget = new JLabel(udto.getBirthDate());
		Birthget.setFont(new Font("나눔고딕", Font.BOLD, 20));
		
		IDget = new JLabel(udto.getId());
		IDget.setFont(new Font("나눔고딕", Font.BOLD, 20));
		PWget = new JLabel(udto.getPw());
		PWget.setFont(new Font("나눔고딕", Font.BOLD, 20));
		
		Phonenumget = new JLabel(udto.getPhone());
		Phonenumget.setFont(new Font("나눔고딕", Font.BOLD, 20));
		
		Font(Nameget);
		Font(Genderget);
		Font(Birthget);
		Font(IDget);
		Font(PWget);
		Font(Phonenumget);
		
		infoPaneget.add(Nameget);
		infoPaneget.add(Genderget);
		infoPaneget.add(Birthget);
		infoPaneget.add(IDget);
		infoPaneget.add(PWget);
		infoPaneget.add(Phonenumget);
		
		basicPane.add(infoPaneget);
	}
	
	private void initinfoUp() {
		
		infoUp = new JPanel();
		infoUp.setBounds(250, 30, 200, 400);
		infoUp.setLayout(new GridLayout(6, 0));
		
		Nameget = new JLabel(udto.getName());
		Genderget = new JLabel(udto.getGen());
		Birthget = new JLabel(udto.getBirthDate());
		
		IDget = new JLabel(udto.getId());
		
		PWinput = new JTextField();
		PWinput.setColumns(12);
		
		PWinput.setText("새로운 비밀번호를 입력하세요"); 
		PWinput.setForeground(Color.LIGHT_GRAY);
		PWinput.addKeyListener(this);

		PWinput.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(PWinput.getText().equals("")) {
				PWinput.setText("새로운 비밀번호를 입력하세요");
				PWinput.setForeground(Color.lightGray);}
				
				else if(!PWinput.getText().equals("")) {PWinput.setForeground(Color.black);}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if(PWinput.getText().equals("새로운 비밀번호를 입력하세요")) {
				PWinput.setForeground(Color.black);
				PWinput.setText("");}
	
			}
		});
		
		Phonenuminput = new JTextField();
		
		Phonenuminput.setText("새로운 전화번호를 입력하세요");
		Phonenuminput.setColumns(12);
		Phonenuminput.setForeground(Color.LIGHT_GRAY);
		Phonenuminput.addKeyListener(this);

		Phonenuminput.addFocusListener(new FocusListener() {
		
			@Override
			public void focusLost(FocusEvent e) {
				if(Phonenuminput.getText().equals("")) {
				Phonenuminput.setText("새로운 전화번호를 입력하세요");
				Phonenuminput.setForeground(Color.lightGray);}
				
				else if(!PWinput.getText().equals("")) {PWinput.setForeground(Color.black);}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if(Phonenuminput.getText().equals("새로운 전화번호를 입력하세요")) {
				Phonenuminput.setForeground(Color.black);
				Phonenuminput.setText("");}
			}
		});
	
		Font(Nameget);
		Font(Genderget);
		Font(Birthget);
		Font(IDget);
		
		infoUp.add(Nameget);
		infoUp.add(Genderget);
		infoUp.add(Birthget);
		infoUp.add(IDget);
		infoUp.add(PWinput);
		infoUp.add(Phonenuminput);
		
		basicPane2.add(infoUp);
	}
	
	
	private void initbtn() {
		btnPane = new JPanel();
		btnPane.setBounds(30, 530, 420, 50);
		btnPane.setLayout(new FlowLayout(1, 10, 10));
		Okbtn = new JButton("확인");
		Upbtn = new JButton("수정"); // 업데이트 버튼
		Upbtn.setFont(new Font("나눔고딕", Font.BOLD, 15));
		Wdbtn = new JButton("회원 탈퇴");
		Wdbtn.setFont(new Font("나눔고딕", Font.BOLD, 15));
		Okbtn.addActionListener(this);
		Upbtn.addActionListener(this);
		Wdbtn.addActionListener(this);
		
		btnFont(Okbtn);
		btnFont(Upbtn);
		btnFont(Wdbtn);
		
		btnPane.add(Okbtn);
		btnPane.add(Upbtn);
		btnPane.add(Wdbtn);
		
		root.add(btnPane, BorderLayout.SOUTH);
	}
	
	private void initbtn2() {
		
		btnPane2 = new JPanel();
		btnPane2.setBounds(30, 530, 420, 50);
		btnPane2.setLayout(new FlowLayout(1, 10, 10));
		Okbtn2 = new JButton("수정 확인");
		Cancel = new JButton("취소"); // 업데이트 버튼
	
		Okbtn2.setEnabled(false);
		Okbtn2.addActionListener(this);
		Cancel.addActionListener(this);
		
		btnFont(Okbtn2);
		btnFont(Cancel);
	
		btnPane2.add(Okbtn2);
		btnPane2.add(Cancel);
		
		root.add(btnPane2, BorderLayout.SOUTH);
	}
	
	private void Font(Component c) {
		c.setFont(new Font("나눔고딕", Font.BOLD, 20)); // 글자 크기
		c.setForeground(Color.black);
		c.setBackground(Color.white);
	}
	
	private void btnFont(Component c) {
		c.setFont(new Font("나눔고딕", Font.BOLD, 15)); // 글자 크기
		c.setForeground(Color.black);
		c.setBackground(Color.white);
	}

	private void Basic2() {
		basicPane2 = new JPanel();
		basicPane2.setSize(3000,3000);
		basicPane2.setLayout(null);
		initinfo(basicPane2);
		initinfoUp();
		root.add(basicPane2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==Okbtn) {
			dispose();
		}
		else if(e.getSource()==Cancel) {
			dispose(); 
		}
		else if(e.getSource()==Okbtn2) {
			
			int i = udao.updateUserinfo(MainFrame.id, PWinput.getText(), Phonenuminput.getText());
			
			if(i>0) {
				JDialog dialog = new InfoUpdateOk_Dialog("확인", false, "o");
				
				PWget.setText(PWinput.getText());
				Phonenumget.setText(Phonenuminput.getText());
				
				MainFrame.pw = PWinput.getText();
				
				dialog.setVisible(true);
			} else {
				JDialog dialog = new InfoUpdateOk_Dialog("확인", false, "x");
				dialog.setVisible(true);
			}
			
	if(!PWinput.getText().equals("") && !PWinput.getText().equals("새로운 비밀번호를 입력하세요"))		
	{p = PWinput.getText();}  												
	if(!Phonenuminput.getText().equals("") && !Phonenuminput.getText().equals("새로운 전화번호를 입력하세요"))	
	{n = Phonenuminput.getText();}
			
		root.removeAll();
		Basic();
		root.add(basicPane);
		initbtn();
	
			root.validate();
			root.repaint();
		}
		
		else if(e.getSource()==Upbtn) {
			
			root.removeAll();
			
			Basic2();
			root.add(basicPane2);
		
			initbtn2();
			
			root.validate();
			root.repaint();
			
		} else if(e.getSource() == Wdbtn) {
			int i = udao.deleteUser(MainFrame.id);
			
			if(i>0) {
				JOptionPane.showMessageDialog(null, "탈퇴되셨습니다.");
				
				MainFrame.id = null;
				MainFrame.pw = null;
				MainFrame.name = null;
				
				new LoginFrame();
				dispose();
				mainFrame.dispose();
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getSource()==Phonenuminput) {k++;}
		else if (e.getSource()==PWinput) {k2++;}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if((Phonenuminput.getText().equals("새로운 전화번호를 입력하세요")
			|| Phonenuminput.getText().equals("") || Phonenuminput.getText().length()<10 )
						
				|| (PWinput.getText().equals("새로운 비밀번호를 입력하세요")
							|| PWinput.getText().equals("") || PWinput.getText().length()<6 ))	{
					Okbtn2.setEnabled(false);	
		}	

		if((!Phonenuminput.getText().equals("새로운 전화번호를 입력하세요")
			&& !Phonenuminput.getText().equals("") && Phonenuminput.getText().length()>10 )
				
			|| 
			
			(!PWinput.getText().equals("새로운 비밀번호를 입력하세요")
					&& !PWinput.getText().equals("") && PWinput.getText().length()>6 ))	{
			Okbtn2.setEnabled(true);}
	}
}

