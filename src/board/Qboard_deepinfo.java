package board;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DB.Board.NBoard_DTO;
import DB.Board.QBoard_DAO;
import DB.Board.QBoard_DTO;
import thrms.MainFrame;
import java.awt.Font;

public class Qboard_deepinfo extends JPanel implements ActionListener {
	
	JPanel basicPane ;
	MainFrame mainFrame;
	JPanel insertp;
	String ID =null;
	String IDX = null;
	
	ArrayList<QBoard_DTO> list ;
	QBoard_DTO qdto = new QBoard_DTO();
	QBoard_DAO qdao = QBoard_DAO.getInstance();
	
	String title;
	String in;
	
	private JTextField writefield;
	private JTextField titlefield;
	private JButton delete;
	
	private JButton reboardbtn;
	
	protected Color clr = new Color(50, 205, 50);	// Å×µÎ¸® »ö
	
	
	public Qboard_deepinfo(String title , String in , JPanel basicPane , MainFrame mainFrame,JPanel insertp,String ID ,String IDX) {
		this.title = title;
		this.in = in;
		this.basicPane = basicPane;
		this.mainFrame = mainFrame;
		this.insertp = insertp;
		this.ID = ID;
		this.IDX = IDX;
		
		setBounds(0,0,1500,707);
		setBackground(Color.white);
		setLayout(null);
		
		
		
		initcon();
		
	}
	
	
	private void initcon() {
		ArrayList<QBoard_DTO> list = qdao.getQBoardListAll();
		
		titlefield = new JTextField();
		titlefield.setEditable(false);
		titlefield.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 15));
		titlefield.setBounds(5,5,1460,100);
		titlefield.setText(title);
		
//		titlefield.setEnabled(false);
		titlefield.setBackground(Color.white);
		
		writefield = new JTextField();
		writefield.setEditable(false);
		writefield.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 15));
		writefield.setBounds(5,110,1460,490);
		writefield.setText(in);
//		writefield.setEnabled(false);
		writefield.setBackground(Color.white);
			
		reboardbtn = new JButton();
		reboardbtn.setFont(new Font("³ª´®°íµñ", Font.BOLD, 15));
		reboardbtn.setBounds(1365,612,100,30);
		reboardbtn.setText("µ¹¾Æ°¡±â");
		
		if(mainFrame.id.equals("admin")||mainFrame.id.equals(ID)) {
			
			delete = new JButton("»èÁ¦");
			delete.setFont(new Font("³ª´®°íµñ", Font.BOLD, 15));
			delete.setBounds(1260,612,100,30);
			
			delete.addActionListener(this);
			
			add(delete);
		}
		
		reboardbtn.addActionListener(this);
		
		add(titlefield);
		add(writefield);
		add(reboardbtn);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== reboardbtn) {
			 insertp.removeAll();
			 insertp.add(new QbOP_board(insertp));
			 
			 insertp.validate();
			 insertp.repaint();
		}
		if(e.getSource()== delete) {
			qdao.deleteQTable(IDX);
			
			 insertp.removeAll();
			 insertp.add(new QbOP_board(insertp));
			 
			 insertp.validate();
			 insertp.repaint();
		}
		
	}

}
