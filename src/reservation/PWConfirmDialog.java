package reservation;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import DB.Res.Res_DAO;
import mainScreen.MainPane;
import thrms.MainFrame;
import java.awt.Font;

public class PWConfirmDialog extends JDialog implements ActionListener {

	private int idx = 0;
	private String pw;
	private JPasswordField pwtxt;
	private JButton okbtn, cancelbtn;
	
	Res_DAO dao = Res_DAO.getInstance();
	
	public PWConfirmDialog(int idx) {
		this.idx = idx;
		
		setSize(500, 700);
		setLocation(200, 300);
		getContentPane().setLayout(null);
		
		initPane();
	}
	
	public PWConfirmDialog() {
		
		setSize(500, 700);
		getContentPane().setLayout(null);
		
		initPane();
	}
	
	private void initPane() {
		JLabel titlelbl = new JLabel("본인 확인");
		titlelbl.setFont(new Font("나눔고딕", Font.BOLD, 30));
		titlelbl.setHorizontalAlignment(SwingConstants.CENTER);
		titlelbl.setBounds(152, 192, 177, 35);
		getContentPane().add(titlelbl, BorderLayout.NORTH);
		
		JLabel label = new JLabel("본인 확인을 위해 비밀번호를 재확인 합니다.");
		label.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		label.setBounds(105, 239, 296, 20);
		getContentPane().add(label);
		
		JLabel pwlbl = new JLabel("PW");
		pwlbl.setFont(new Font("나눔고딕", Font.BOLD, 15));
		pwlbl.setBounds(129, 287, 43, 42);
		getContentPane().add(pwlbl);
		
		pwtxt = new JPasswordField();
		pwtxt.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		pwtxt.setBounds(182, 287, 184, 42);
		pwtxt.setColumns(10);
		getContentPane().add(pwtxt);
		
		okbtn = new JButton("확인");
		okbtn.setFont(new Font("나눔고딕", Font.BOLD, 15));
		okbtn.setBounds(139, 350, 104, 55);
		okbtn.addActionListener(this);
		getContentPane().add(okbtn);
		
		cancelbtn = new JButton("취소");
		cancelbtn.setFont(new Font("나눔고딕", Font.BOLD, 15));
		cancelbtn.setBounds(249, 350, 104, 55);
		cancelbtn.addActionListener(this);
		getContentPane().add(cancelbtn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == okbtn) {
			String password = new String(pwtxt.getPassword());
			if(password.equals(MainFrame.pw)) {
				JDialog dialog = new ErrorPWConfirm_okbtnDialog("확인 다이얼로그", true, "o");
				
				dialog.setVisible(true);
				
				if(idx != 0) {		// idx값을 받아오는 생성자로 호출이 되면 실행되는 코드
					int n = dao.deleteRes(idx);
					
					if(n > 0) {
						MainFrame.basicPane.removeAll();
						
						MainFrame.basicPane.add(new MainPane());
						
						MainFrame.basicPane.validate();
						MainFrame.basicPane.repaint();
						
						dispose();
					}
				}
			} else if(password.equals("")) {
				JDialog dialog = new ErrorPWConfirm_okbtnDialog("오류 다이얼로그", true, "none");
				
				dialog.setVisible(true);
			} else {
				JDialog dialog = new ErrorPWConfirm_okbtnDialog("오류 다이얼로그", true, "x");
				
				dialog.setVisible(true);
			}
		}
		
		if(e.getSource() == cancelbtn) {
			dispose();
		}
	}
}
