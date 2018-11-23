package login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import DB.User.User_DAO;
import DB.User.User_DTO;
import thrms.MainFrame;

public class LoginPane extends JPanel implements ActionListener {
	
	private Container contentPane;
	private LoginFrame loginFrame;
	
	private JPanel brandLogoPane;
	private JPanel panel;
	private JPanel brandPane;
	
	private JTextField txtID;
	
	private JPasswordField pw;
	
	private JButton btnLoginButton;
	private JButton btnJoin;
	private JButton btnIdPwFind;
	
	private JLabel idlbl;
	private JLabel pwlbl;
	
	private ImageIcon img;
	
	private User_DAO dao = User_DAO.getInstance();

	public LoginPane() {
		
		setLayout(null);
		setBounds(0, 0, 482, 653);
		
		Color color = new Color(50, 205, 50);	// �׵θ� ��
		LineBorder lb = new LineBorder(color, 5 , false); // �׵θ� Ŀ����
		setBorder(lb);	// �׵θ� ����

		
		brandLogoPane = new JPanel();
		brandLogoPane.setBounds(20,20,400,250);
		brandLogoPane.setLayout(new BorderLayout());
		
		img = new ImageIcon("./src/image/bi.png");
		brandPane = new JPanel() {
			
			@Override
			protected void paintComponent(Graphics g) {
				
				g.drawImage(img.getImage(), 0, 0, getSize().width, getSize().height, null);
				
				setOpaque(false);
				
				super.paintComponent(g);
			}
		};
		brandLogoPane.add(brandPane, BorderLayout.CENTER);
		
		add(brandLogoPane);
		
		panel = new JPanel();
		panel.setBounds(6, 280, 470, 365);
		panel.setLayout(null);

		add(panel);

		idlbl = new JLabel("ID");
		idlbl.setFont(new Font("�������", Font.BOLD, 18));
		idlbl.setForeground(color);
		idlbl.setBounds(70,60,50,40);
		
		panel.add(idlbl);
		
		
		pwlbl = new JLabel("PW");
		pwlbl.setFont(new Font("�������", Font.BOLD, 18));
		pwlbl.setForeground(color);
		pwlbl.setBounds(70,112,50,40);
		
		panel.add(pwlbl);
		
		txtID = new JTextField();
		txtID.setFont(new Font("�������", Font.PLAIN, 15));
		txtID.setBounds(110, 60, 185, 40);//+130
		panel.add(txtID);
		txtID.setColumns(10);


		pw = new JPasswordField();
		pw.setFont(new Font("�������", Font.PLAIN, 15));
		pw.setBounds(110, 112, 185, 40);
		pw.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(txtID.equals("") || new String(pw.getPassword()).equals("")) {
						JOptionPane.showMessageDialog(null, "���̵�� ��й�ȣ ��� �ԷµǾ�� �մϴ�.");
					} else {
						boolean flag = true;	// ���̺� id�� ���ٸ� false
						
						String idstr = txtID.getText();
						String pwstr = new String(pw.getPassword());
						
						ArrayList<User_DTO> list = dao.getUserListAll();
						
						for(User_DTO user : list) {
							if(user.getId().equals(idstr) && user.getPw().equals(pwstr)) {	// �α��� ����
								
								MainFrame mainFrame = new MainFrame();					//	*
								mainFrame.setIdPwName(user.getId(), user.getName(), user.getPw());		//	*
								
								loginFrame.dispose();
								
								flag = true;
								break;
							} else if(user.getId().equals(idstr) && !user.getPw().equals(pwstr)) {	// ���̵� ����, ��й�ȣ Ʋ��
								JDialog dialog = new ErrorLogin("Ȯ�� ���̾�α�", true, "x");
								
								dialog.setVisible(true);
								
								flag = true;		// ���̺� id ������					
								break;
							} else {				// ȸ������ ���� x
								flag = false;
							}
						}
						
						if(flag == false) {				// ȸ������ ���� x
							JDialog dialog = new ErrorLogin("Ȯ�� ���̾�α�", true, "none");
							
							dialog.setVisible(true);
						}
					}
					
				}
			}
		});
		panel.add(pw);
		

		btnLoginButton = new JButton("�α���");
		btnLoginButton.setFont(new Font("�������", Font.BOLD, 15));
		btnLoginButton.setBounds(320, 60, 95, 90);
		btnLoginButton.addActionListener(this);
		
		panel.add(btnLoginButton);

		btnJoin = new JButton("ȸ�� ����");
		btnJoin.setFont(new Font("�������", Font.BOLD, 15));
		btnJoin.setBounds(80, 181, 320, 40);
		btnJoin.addActionListener(this);
		
		panel.add(btnJoin);

		btnIdPwFind = new JButton("��й�ȣ ã��");
		btnIdPwFind.setFont(new Font("�������", Font.BOLD, 15));
		btnIdPwFind.setBounds(80, 225, 320, 40);
		btnIdPwFind.addActionListener(this);
		
		panel.add(btnIdPwFind);
		
	}
	
	public void getBasicPane(LoginFrame loginFrame, Container contentPane) {	//	*
		this.loginFrame = loginFrame;	//	*
		this.contentPane = contentPane;
 	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLoginButton) {
			boolean flag = true;	// ���̺� id�� ���ٸ� false
			
			String idstr = txtID.getText();
			String pwstr = new String(pw.getPassword());
			
			ArrayList<User_DTO> list = dao.getUserListAll();
			
			for(User_DTO user : list) {
				if(user.getId().equals(idstr) && user.getPw().equals(pwstr)) {	// �α��� ����
					
					MainFrame mainFrame = new MainFrame();					//	*
					mainFrame.setIdPwName(user.getId(), user.getName(), user.getPw());		//	*
					
					loginFrame.dispose();
					
					flag = true;
					break;
				} else if(user.getId().equals(idstr) && !user.getPw().equals(pwstr)) {	// ���̵� ����, ��й�ȣ Ʋ��
					JDialog dialog = new ErrorLogin("Ȯ�� ���̾�α�", true, "x");
					
					dialog.setVisible(true);
					
					flag = true;		// ���̺� id ������					
					break;
				} else {				// ȸ������ ���� x
					flag = false;
				}
			}
			
			if(flag == false) {				// ȸ������ ���� x
				JDialog dialog = new ErrorLogin("Ȯ�� ���̾�α�", true, "none");
				
				dialog.setVisible(true);
			}
			
		} else if(e.getSource() == btnJoin) {
			
			contentPane.removeAll();
			
			JoinPane joinPane = new JoinPane();
			joinPane.getBasicPane(loginFrame);
			
			contentPane.add(joinPane);
			
			contentPane.validate();
			contentPane.repaint();
			
			
		} else if(e.getSource() == btnIdPwFind) {
			
			contentPane.removeAll();
			
			IdpwFind idpwFindPane = new IdpwFind();
			idpwFindPane.getBasicPane(loginFrame);
			
			contentPane.add(idpwFindPane);
			
			contentPane.validate();
			contentPane.repaint();
		}
		
	}

}
