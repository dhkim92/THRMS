package login;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class LoginFrame extends JFrame {

	private JPanel contentPane;
	
	
	public LoginFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		initPane();
		
		setVisible(true);
	}
	
	public void initPane() {
		
		LoginPane loginPane = new LoginPane();
		loginPane.setBounds(0, 0, 482, 653);
		loginPane.getBasicPane(this, contentPane);
		
		contentPane.add(loginPane);
	}

	public static void main(String[] args) {
		new LoginFrame();
	}
}


