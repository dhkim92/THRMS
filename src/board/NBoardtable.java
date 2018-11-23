package board;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.xml.crypto.Data;

import mainScreen.NoticePane;
import thrms.MainFrame;
import java.awt.Font;

public class NBoardtable extends JPanel implements ActionListener {

//	private JPanel tablePane;
	private JPanel insertp;
	private JLabel NBtitle;
	private JButton btnW;
	
	protected Color clr = new Color(50, 205, 50);	// Å×µÎ¸® »ö
	
	
	public NBoardtable() {
		
		
		setBounds(0,0,1500,732);
		setBackground(clr);
		setLayout(null);
		
		initinsert();
		inittitle();
	}
	

	private void inittitle() {
		NBtitle = new JLabel();
		NBtitle.setText("°øÁö»çÇ× °Ô½ÃÆÇ");
		NBtitle.setForeground(Color.WHITE);
		NBtitle.setBounds(10, 0,1490 ,71);
		NBtitle.setBackground(clr);
		NBtitle.setFont(new Font("³ª´®°íµñ", Font.BOLD, 35));
		
		if(MainFrame.id.equals("admin")) {
		btnW = new JButton();
		btnW.setFont(new Font("³ª´®°íµñ", Font.BOLD, 15));
		btnW.setText("±Û¾²±â");
		btnW.setBounds(1358, 8, 120, 50);
						
		NBtitle.add(btnW);
		btnW.addActionListener(this);
		};
		
		
		
		add(NBtitle);
		
		
	}


	private void initinsert() {
		insertp = new JPanel();
		insertp.setBounds(10,70,1476,650);
		insertp.setBackground(Color.white);
		insertp.setLayout(null);
		
		NoticePane ntp = new NoticePane();
		ntp.setinsertp(insertp);
		
		NbOP_board nbop = new NbOP_board(insertp);
		nbop.setBounds(0, 0, 1476, 650);
		nbop.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 15));
		
		insertp.add(nbop);
		insertp.validate();
		insertp.repaint();

		add(insertp);
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnW) {
			Nboard_writer nbdw = new Nboard_writer();
			
			NBtitle.removeAll();
			insertp.removeAll();
			insertp.add(nbdw);
			
			NBtitle.validate();
			NBtitle.repaint();
			insertp.validate();
			insertp.repaint();
			
		}
		
		
		
	}

}
