package myPageMember;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import DB.Res.Res_DAO;
import DB.Res.Res_DTO;
import DB.User.User_DAO;
import DB.User.User_DTO;
import thrms.MainFrame;
import java.awt.Font;

public class Info  extends JFrame {
	
	private MainFrame mainFrame;
	
	protected Color color = new Color(50, 205, 50);	// 테두리 색
	protected LineBorder lb = new LineBorder(Color.gray, 1 , false);
	
	private JPanel root;
	
	private JPanel BasicPane;  //베이직패널
	private JPanel BasicPane2; //베이직위에올릴거
	private  JButton ok;  //확인버튼
	private JButton cancel;  //취소버튼
	private String a;
	private int row;
	User_DTO udto = new User_DTO();
	User_DAO udao = User_DAO.getInstance();
	Res_DAO rdao = Res_DAO.getInstance();
	Res_DTO rdto = new Res_DTO();
	//정보
	private JLabel idx;
	private JLabel id;
	private JLabel part;
	private JLabel dname;
	private JLabel resDate;
	private JLabel resTime;
	private JLabel symptoms;
	private JLabel status;
	private int res_idx;

	
	//생성자
	public Info(MainFrame mainFrame, String id,int row) {
		super("예약내역");
		
		this.mainFrame = mainFrame;
		this.row =row;
		setBounds(1000,300,500,700);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		intiroot();
		Basic();
		setVisible(true);
	}
	
	private void intiroot() {
		
		root = new JPanel();
		setContentPane(root);
		root.setLayout(null);
	}
	
	private void Basic() {
		
	BasicPane = new JPanel();
	BasicPane.setBounds(20,20,440,620);
	BasicPane.setBorder(lb);
//	BasicPane.setBackground(Color.lightGray);
	BasicPane.setLayout(null);
	Basic2();
	
	root.add(BasicPane);

	//버튼추가
	ok = new JButton("확인");
	ok.setFont(new Font("나눔고딕", Font.BOLD, 15));
	ok.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			 dispose();
		}
	});
	
	cancel = new JButton("예약 취소 ");
	cancel.setFont(new Font("나눔고딕", Font.BOLD, 15));
	cancel.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			
			new PWConfirmDialog(mainFrame, res_idx).setVisible(true);
		}
	});
	
	ok.setBounds(110, 570, 100, 30);	 
	cancel.setBounds(220, 570, 100, 30);
	BasicPane.add(ok);
	BasicPane.add(cancel);
	}

	
	private void Basic2() {
		BasicPane2 = new JPanel();
		BasicPane2.setBounds(0,0,440,550);
		BasicPane2.setBorder(lb);
		BasicPane2.setLayout(new GridLayout(8, 0));
		
		idx=new JLabel();
		idx.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		id=new JLabel();
		id.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		part=new JLabel();
		part.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		dname=new JLabel();
		dname.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		resDate=new JLabel();
		resDate.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		resTime=new JLabel();
		resTime.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		symptoms=new JLabel();
		symptoms.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		status=new JLabel();
		status.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		
		//db 연동
		
		String[][] contents = null;
		ArrayList<Res_DTO> list = rdao.getResList(MainFrame.id);
		
		for(Res_DTO dto : list) {
			int r = row + 1;
			if(dto.getRownum() == r ) {
				res_idx = dto.getIdx();
 				
					id.setText("회원 아이디 :  "+dto.getId());
					dname.setText("담당의  :  "+dto.getDname());
					part.setText("진료 과목 :  "+dto.getPart());
					resDate.setText("진료 일자 :  "+dto.getResDate());
					resTime.setText("진료 시간 :  "+dto.getResTime());
					symptoms.setText("증   상 :  "+dto.getSymptoms());
					status.setText("진료 상황 :  "+dto.getStatus()); 
			}
		}

		BasicPane2.add(id);
		BasicPane2.add(part);
		BasicPane2.add(dname);
		BasicPane2.add(resDate);
		BasicPane2.add(resTime);
		BasicPane2.add(symptoms);
		BasicPane2.add(status);
		
		BasicPane.add(BasicPane2);
		
	}
}
