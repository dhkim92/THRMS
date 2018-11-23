package board;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import thrms.MainFrame;

public class Qboard extends JPanel implements ActionListener{
	
	JPanel basicPane;
	MainFrame mainFrame;
	
	private JPanel Qboard;
	protected Color clr = new Color(50, 205, 50);	// 테두리 색
		
	public Qboard() {
		this.basicPane=mainFrame.basicPane;
		
		
		setBounds(0,0,1500,732);
		setBackground(Color.white);
		setLayout(null);
		
		initlogPane();
		initQbdPane();
		
	}
	
	private void initlogPane() {
		
	}
	private void initQbdPane() {
		QBoardtable qbdt = new QBoardtable();
				
		add(qbdt);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
