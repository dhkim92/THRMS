package board;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import DB.Board.QBoard_DAO;
import DB.Board.QBoard_DTO;
import thrms.MainFrame;
import java.awt.Font;

public class QBoardtable extends JPanel implements ActionListener {

	//	private JPanel tablePane;
	private JPanel insertp;
	private JLabel QBtitle;
	private JButton btnW;
	

	protected Color clr = new Color(50, 205, 50);	// Å×µÎ¸® »ö
	
	public QBoardtable() {
				
		setBounds(0,0,1500,732);
		setBackground(clr);
		setLayout(null);

		initinsert();
		inittitle();
	}


	private void inittitle() {
		QBtitle = new JLabel();
		QBtitle.setText("¹®ÀÇ»çÇ× °Ô½ÃÆÇ");
		QBtitle.setForeground(Color.WHITE);
		QBtitle.setBounds(10, 0,1490 ,72);
		QBtitle.setBackground(clr);
		QBtitle.setFont(new Font("³ª´®°íµñ", Font.BOLD, 35));

		add(QBtitle);
		
				btnW = new JButton();
				add(btnW);
				btnW.setFont(new Font("³ª´®°íµñ", Font.BOLD, 15));
				btnW.setText("±Û¾²±â");
				btnW.setBounds(1366, 8, 120, 50);
				btnW.addActionListener(this);


	}


	private void initinsert() {
		insertp = new JPanel();
		insertp.setBounds(10,70,1476,650);
		insertp.setBackground(Color.white);
		insertp.setLayout(null);
		QbOP_board qbop = new QbOP_board(insertp);
		qbop.setBounds(0, 0, 1476, 650);
		qbop.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 15));
		qbop.getinsertp(insertp);
		insertp.add(qbop);
		
		insertp.validate();
		insertp.repaint();
		add(insertp);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnW) {
			Qboard_writer qbdw = new Qboard_writer();
			
			QBtitle.removeAll();
			insertp.removeAll();
			insertp.add(qbdw);

			QBtitle.validate();
			QBtitle.repaint();
			insertp.validate();
			insertp.repaint();

		}

	}
}
