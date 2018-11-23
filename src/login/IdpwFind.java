package login;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import DB.User.User_DAO;
import DB.User.User_DTO;

public class IdpwFind extends JPanel implements ActionListener {
	
	private LoginFrame loginFrame;
	private Container contentPane;
	
	protected Color color = new Color(50, 205, 50);	// Å×µÎ¸® »ö
	protected LineBorder lb = new LineBorder(color, 5 , false);
	
	JLabel namelbl, birthlbl, phonelbl;
	JFormattedTextField phonetxt;
	JTextField nametxt;
	JComboBox<String> birthcb1, birthcb2, birthcb3;
	JButton findbtn, cancelbtn;
	private JLabel titlelbl;
	
	private String name;
	private String birthDate;
	private String phone;
	
	User_DAO dao = User_DAO.getInstance();
	
	public IdpwFind() {
		setLayout(null);
		setBounds(0, 0, 482, 653);
		
		LineBorder lb = new LineBorder(color, 5 , false); // Å×µÎ¸® Ä¿½ºÅÒ
		setBorder(lb);	// Å×µÎ¸® ¼³Á¤
		
		initTxt();
	}
	
	public void getBasicPane(LoginFrame loginFrame) {
 		this.loginFrame = loginFrame;
 		
 		loginFrame.setTitle("¾ÆÀÌµð/ºñ¹Ð¹øÈ£ Ã£±â");
 		
 		contentPane = loginFrame.getContentPane();
  	}

	private void initTxt() {
		
		titlelbl = new JLabel("¾ÆÀÌµð/ºñ¹Ð¹øÈ£ Ã£±â");
		titlelbl.setFont(new Font("³ª´®°íµñ", Font.BOLD, 25));
		
		namelbl = new JLabel("¼º¸í");
		birthlbl = new JLabel("»ý³â¿ùÀÏ");
		phonelbl = new JLabel("ÈÞ´ëÀüÈ­");
		
		Font font = new Font("Gulim", Font.BOLD, 20);
		
		namelbl.setFont(new Font("³ª´®°íµñ", Font.BOLD, 20));
		birthlbl.setFont(new Font("³ª´®°íµñ", Font.BOLD, 20));
		phonelbl.setFont(new Font("³ª´®°íµñ", Font.BOLD, 20));
		
		birthcb1 = new JComboBox<String>();
		birthcb1.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 15));
		birthcb2 = new JComboBox<String>();
		birthcb2.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 15));
		birthcb3 = new JComboBox<String>();
		birthcb3.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 15));
		
		for(int i=1970; i<2000; i++) {
			birthcb1.addItem(String.valueOf(i));
		}
		
		for(int i=1; i<13; i++) {
			birthcb2.addItem(String.valueOf(i));
		}
		
		for(int i=1; i<32; i++) {
			birthcb3.addItem(String.valueOf(i));
		}
		
		JLabel yearlbl = new JLabel("³â");
		yearlbl.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 15));
		JLabel monthlbl = new JLabel("¿ù");
		monthlbl.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 15));
		JLabel daylbl = new JLabel("ÀÏ");	
		daylbl.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 15));
		
		MaskFormatter format = null;
		
		try {
			format = new MaskFormatter("010########");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		phonetxt = new JFormattedTextField(format);
		phonetxt.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 20));
		nametxt = new JTextField();
		nametxt.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 20));
		
		findbtn = new JButton("È®ÀÎ");
		findbtn.setFont(new Font("³ª´®°íµñ", Font.BOLD, 15));
		cancelbtn = new JButton("Ãë¼Ò");
		cancelbtn.setFont(new Font("³ª´®°íµñ", Font.BOLD, 15));
		
		findbtn.addActionListener(this);
		cancelbtn.addActionListener(this);
		
		titlelbl.setBounds(114, 117, 252, 82);		
		
		namelbl.setBounds(57, 246, 100, 50);
		birthlbl.setBounds(54,296,100,50);
		phonelbl.setBounds(54,346,100,50);
		
		nametxt.setBounds(154, 251, 265, 40);
		birthcb1.setBounds(154, 301, 81, 40);
		yearlbl.setBounds(238, 314, 21, 18);
		birthcb2.setBounds(263, 303, 54, 40);
		monthlbl.setBounds(322, 314, 28, 18);
		birthcb3.setBounds(344, 303, 54, 40);
		daylbl.setBounds(403, 314, 21, 18);
		phonetxt.setBounds(154, 351, 265, 40);
		findbtn.setBounds(92, 431, 152, 50);
		cancelbtn.setBounds(246, 431, 152, 50);
		
		add(titlelbl);
		add(namelbl);
		add(birthlbl);
		add(phonelbl);	
		
		add(nametxt);
		add(birthcb1);
		add(yearlbl);
		add(birthcb2);
		add(monthlbl);
		add(birthcb3);
		add(daylbl);
		add(phonetxt);
		add(findbtn);
		add(cancelbtn);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String month = (String)birthcb2.getSelectedItem();
		String day = (String)birthcb3.getSelectedItem();
		
		name = nametxt.getText();
		
		if(month.length()<2) {
			month= "0" + month;
		}
		if(day.length()<2) {
			day= "0" + day;
		}
		
		birthDate = (String)birthcb1.getSelectedItem() + "-" + month + "-" + day;
		phone = phonetxt.getText();
		
		System.out.println(name + ", " + birthDate + ", " + phone);
		
		if(e.getSource() == findbtn) {
			boolean flag = true;	// Å×ÀÌºí¿¡ ÀÏÄ¡ÇÏ´Â Á¤º¸°¡ ¾ø´Ù¸é false
			
			ArrayList<User_DTO> list = dao.getUserListAll();
			
			for(User_DTO user : list) {
				if(user.getName().equals(name) && user.getBirthDate().equals(birthDate) && user.getPhone().equals(phone)) {					
					JOptionPane.showMessageDialog(this, "ID : " + user.getId() + "\nPW : " + user.getPw());
					
					contentPane.removeAll();
					
					LoginPane loginPane = new LoginPane();
					loginPane.getBasicPane(loginFrame, contentPane);
					
					contentPane.add(loginPane);
					
					contentPane.validate();
					contentPane.repaint();
					
					flag = true;
					break;
				} else {
					flag = false;
				}
			}
			
			if(flag == false) {
				JOptionPane.showMessageDialog(this, "ÀÏÄ¡ÇÏ´Â Á¤º¸°¡ ¾ø½À´Ï´Ù.\nÀÔ·ÂÇÏ½Å Á¤º¸¸¦ ´Ù½Ã È®ÀÎÇØÁÖ¼¼¿ä.");
			}
			
		} else if(e.getSource() == cancelbtn) {
			
			contentPane.removeAll();
			
			LoginPane loginPane = new LoginPane();
			loginPane.getBasicPane(loginFrame, contentPane);
			
			contentPane.add(loginPane);
			
			contentPane.validate();
			contentPane.repaint();
			
		}
	}
}
