package myPageMember;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.management.InstanceNotFoundException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import mainScreen.MainPane;
import thrms.MainFrame;
import java.awt.Color;
import java.awt.Font;

public class PWConfirmDialog2 extends JDialog implements ActionListener {

	private MainFrame mainFrame;
	
	private JPasswordField pwtxt;
	private JButton okbtn, cancelbtn;
	private JFrame jf;
	
	
	public PWConfirmDialog2(MainFrame mainFrmae) {
		
		this.mainFrame = mainFrmae;
		
		setBounds(1000,300,500,700);
		setSize(500, 700);
		getContentPane().setLayout(null);
		
		initPane();
	}
	
	private void initPane() {
		JLabel titlelbl = new JLabel("���� Ȯ��");
		titlelbl.setFont(new Font("�������", Font.BOLD, 30));
		titlelbl.setHorizontalAlignment(SwingConstants.CENTER);
		titlelbl.setBounds(151, 161, 177, 35);
		getContentPane().add(titlelbl, BorderLayout.NORTH);
		
		JLabel label = new JLabel("���� Ȯ���� ���� ��й�ȣ�� ��Ȯ�� �մϴ�.");
		label.setFont(new Font("�������", Font.PLAIN, 15));
		label.setBounds(100, 228, 301, 20);
		getContentPane().add(label);
		
		JLabel pwlbl = new JLabel("PW");
		pwlbl.setFont(new Font("�������", Font.BOLD, 15));
		pwlbl.setBounds(129, 292, 43, 42);
		getContentPane().add(pwlbl);
		
		pwtxt = new JPasswordField();
		pwtxt.setFont(new Font("�������", Font.PLAIN, 15));
		pwtxt.setBounds(182, 292, 184, 42);
		pwtxt.setColumns(10);
		getContentPane().add(pwtxt);
		
		okbtn = new JButton("Ȯ��");
		okbtn.setFont(new Font("�������", Font.BOLD, 15));
		okbtn.setBounds(140, 361, 104, 55);
		okbtn.addActionListener(this);
		getContentPane().add(okbtn);
		
		cancelbtn = new JButton("���");
		cancelbtn.setFont(new Font("�������", Font.BOLD, 15));
		cancelbtn.setBounds(250, 361, 104, 55);
		cancelbtn.addActionListener(this);
		getContentPane().add(cancelbtn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == okbtn) {
			String password = new String(pwtxt.getPassword());
			if(password.equals(MainFrame.pw)) {
				MemberInfo j  = new MemberInfo(mainFrame);
				j.setVisible(true);
	
				dispose();
			} else if(password.equals("")) {
				JDialog dialog = new ErrorPWConfirm_okbtnDialog("���� ���̾�α�", true, "none");
				
				dialog.setVisible(true);
			}
			
			else {
				JDialog dialog = new ErrorPWConfirm_okbtnDialog("���� ���̾�α�", true, "x");
				
				dialog.setVisible(true);
			} 
		}
		
		if(e.getSource() == cancelbtn) {
			dispose();
		}
	}
}
