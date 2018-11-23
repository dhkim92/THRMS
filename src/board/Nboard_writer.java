package board;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import DB.Board.NBoard_DAO;
import DB.Board.NBoard_DTO;
import DB.Board.QBoard_DTO;
import thrms.MainFrame;
import java.awt.Font;

public class Nboard_writer extends JPanel implements ActionListener {

	JPanel basicPane;
	
	
	private JTextArea writefield;
	private JTextField titlefield;
	private JButton savebtn;
	
	NBoard_DTO ndto = new NBoard_DTO();
	NBoard_DAO ndao = NBoard_DAO.getInstance();
	
	public Nboard_writer() {
		this.basicPane = MainFrame.basicPane;
		
		setBounds(0,0,1500,732);
		setBackground(Color.white);
		setLayout(null);
		initinsert();
				
	}
	
	
	private void initinsert() {
		titlefield = new JTextField();
		titlefield.setFont(new Font("�������", Font.PLAIN, 15));
		titlefield.setText("������ �Է��Ͻÿ�");
		titlefield.setForeground(Color.gray);
		titlefield.setBounds(5,5,1460,100);
		titlefield.setBackground(Color.LIGHT_GRAY);
		
		titlefield.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(titlefield.getText().isEmpty()) {
					titlefield.setText("������ �Է��Ͻÿ�");
					titlefield.setForeground(Color.gray);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if(titlefield.getText().equals("������ �Է��Ͻÿ�")) {
					titlefield.setText("");
					titlefield.setForeground(Color.black);
				}
			}
		});
		
		writefield = new JTextArea();
		writefield.setFont(new Font("�������", Font.PLAIN, 15));
		writefield.setText("������ �Է��Ͻÿ�");
		writefield.setForeground(Color.gray);
		writefield.setBounds(5,110,1460,490);
		writefield.setBackground(Color.LIGHT_GRAY);
		
		writefield.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(writefield.getText().isEmpty()) {
					writefield.setText("������ �Է��Ͻÿ�");
					writefield.setForeground(Color.gray);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if(writefield.getText().equals("������ �Է��Ͻÿ�")) {
					writefield.setText("");
					writefield.setForeground(Color.black);
				}
			}
			
		});
		
		writefield.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					writefield.append(System.lineSeparator());
				}
				
			}
		});
		
		
		savebtn = new JButton();
		savebtn.setFont(new Font("�������", Font.BOLD, 15));
		savebtn.setBounds(1385,612,80,30);
		savebtn.setText("����");
		
		savebtn.addActionListener(this);
		
		add(titlefield);
		add(writefield);
		add(savebtn);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==savebtn) {
			if(titlefield!=null) {
				
				ndao.insertnboard(new NBoard_DTO(MainFrame.id, titlefield.getText(), writefield.getText()));
				
				basicPane.removeAll();
				basicPane.add(new Nboard());
				
				basicPane.validate();
				basicPane.repaint();
			}
					
		}
		
		
		
	}
	

}
