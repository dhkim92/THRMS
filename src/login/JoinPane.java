package login;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import DB.User.User_DAO;
import DB.User.User_DTO;

public class JoinPane extends JPanel implements ActionListener {
	
	private LoginFrame loginFrame;
	private Container contentPane;
    
	private JLabel lblId, lblPwd, lblName, lblGen, lblAge, lblHp;
	private JFormattedTextField txtId, txtName, txtAge, txtAge2, txtAge3, txtHp1, txtHp2, txtHp3;
	private JPasswordField txtPwd;
	private JCheckBox cbFeMale, cbMale;
    private JButton idCheckbtn, btnSubmit, btnCancel;
     
    private String gen;
    private boolean idCheckbtnFlag = false;
    private boolean idCheckFlag = false;
    private User_DAO dao = User_DAO.getInstance();
    
    
    public JoinPane() {
         
          setLayout(null);
          setBounds(0, 0, 482, 653);
          
          Color color = new Color(50, 205, 50);	// �׵θ� ��
  		  LineBorder lb = new LineBorder(color, 5 , false); // �׵θ� Ŀ����
  		  setBorder(new LineBorder(color, 5));	// �׵θ� ����
  		  
  		  // Ÿ��Ʋ
  		 JLabel lblJoin = new JLabel("ȸ������");
         lblJoin.setFont(new Font("�������", Font.BOLD, 25));
         lblJoin.setBounds(191, 70, 119, 35);
  		  
          // ���̺�
          lblId = new JLabel("���̵�");
          lblPwd = new JLabel("��й�ȣ");
          lblName = new JLabel("����");
          lblGen = new JLabel("����");
          lblAge = new JLabel("�������");
          lblHp = new JLabel("����ó");

          // ��Ʈ ����
          Font font = new Font("�������", Font.BOLD, 20);
          Font font2 = new Font("�������", Font.PLAIN, 15);

          lblId.setFont(font);
          lblPwd.setFont(font);
          lblName.setFont(font);
          lblGen.setFont(font);
          lblAge.setFont(font);
          lblHp.setFont(font);
          
          // ���̺� ũ��, ��ġ ����
          lblId.setBounds(69, 163, 100, 20);
          lblPwd.setBounds(69, 215, 100, 20);
          lblName.setBounds(77, 269, 100, 20);
          lblGen.setBounds(77, 324, 100, 20);
          lblAge.setBounds(58, 432, 100, 20);
          lblHp.setBounds(69, 380, 100 , 20);
          
          // �ߺ�Ȯ�� ��ư
          idCheckbtn = new JButton("�ߺ�Ȯ��");
          idCheckbtn.addActionListener(this);
          idCheckbtn.setFont(new Font("�������", Font.PLAIN, 13));
          idCheckbtn.setBounds(341, 162, 93, 27);

         // �ؽ�Ʈ ����
          MaskFormatter format1 = null;
          MaskFormatter format2 = null;
          MaskFormatter format3 = null;
          
          try {
        	  format1 = new MaskFormatter("###");	// ���� 3��
        	  format2 = new MaskFormatter("####");	// ���� 4��
        	  format3 = new MaskFormatter("##");
          } catch (ParseException e1) {
        	  e1.printStackTrace();
          }
          
          // �ؽ�Ʈ �ʵ�
          txtId = new JFormattedTextField();
          txtId.addKeyListener(new KeyAdapter() {
        	  @Override
        	public void keyPressed(KeyEvent e) {
        		  idCheckFlag = true;
        		  idCheckbtnFlag = false;
        	  }
		});
          txtPwd = new JPasswordField(15);
          txtName= new JFormattedTextField();
          txtAge = new JFormattedTextField(format2);
          JLabel lblhipen3 = new JLabel("-");
          txtAge2 = new JFormattedTextField(format3);
          JLabel lblhipen4 = new JLabel("-");
          txtAge3 = new JFormattedTextField(format3);
          
          txtId.setFont(font2);
          txtPwd.setFont(font2);
          txtName.setFont(font2);
          txtAge.setFont(font2);
          txtAge2.setFont(font2);
          txtAge3.setFont(font2);

          txtId.setToolTipText("����,���� / �ִ� 10��");
          txtPwd.setToolTipText("����,���� / �ִ� 15��");
          
          txtId.setColumns(10);
          txtPwd.setColumns(15);
                    
          txtId.setBounds(177, 160, 150, 30);
          txtPwd.setBounds(177, 212, 150, 30);
          txtName.setBounds(177, 266, 150, 30);
          txtAge.setBounds(183, 429, 60, 30);
          lblhipen3.setBounds(254, 435, 10, 20);
          txtAge2.setBounds(273, 429, 60, 30);
          lblhipen4.setBounds(343, 435, 10, 20);
          txtAge3.setBounds(363, 429, 60, 30);
          

          // ����
          JPanel panGen = new JPanel(new FlowLayout());  
          panGen.setSize(133, 40);
          panGen.setLocation(177, 317);
          
          cbFeMale = new JCheckBox("����");
          cbMale = new JCheckBox("����");

          cbFeMale.setFont(font2);
          cbMale.setFont(font2);
          
          ButtonGroup group = new ButtonGroup();
          
          group.add(cbFeMale);
          group.add(cbMale);
          
          // üũ�ڽ� ���� �� ���� ����
          cbFeMale.addItemListener(new ItemListener() {
        	  
        	  @Override
        	  public void itemStateChanged(ItemEvent e) {
        		  if(e.getStateChange() == ItemEvent.SELECTED) {
        			  gen = cbFeMale.getText();
        		  }
        	  }
          });
          
          cbMale.addItemListener(new ItemListener() {
        	  
        	  @Override
        	  public void itemStateChanged(ItemEvent e) {
        		  if(e.getStateChange() == ItemEvent.SELECTED) {
        			  gen = cbMale.getText();
        		  }
        	  }
          });
          
          panGen.add(cbFeMale);
          panGen.add(cbMale);
          
          // ����ó
          txtHp1 = new JFormattedTextField(format1);
          JLabel lblhipen1 = new JLabel("-");
          txtHp2 = new JFormattedTextField(format2);
          JLabel lblhipen2 = new JLabel("-");
          txtHp3 = new JFormattedTextField(format2);

          txtHp1.setText("010");
          txtHp1.setEditable(false);
          
          txtHp1.setFont(font2);
          txtHp2.setFont(font2);
          txtHp3.setFont(font2);
          
          txtHp1.setBounds(183, 374, 60, 30);
          lblhipen1.setBounds(254, 378, 10, 20);
          txtHp2.setBounds(273, 374, 60, 30);
          lblhipen2.setBounds(343, 378, 10, 20);
          txtHp3.setBounds(363, 374, 60, 30);
         
         // ��ư
          btnSubmit = new JButton("���ԿϷ�");
          btnCancel = new JButton("���");       

          btnSubmit.setFont(font2);
          btnCancel.setFont(font2);
          
          btnSubmit.addActionListener(this);
          btnCancel.addActionListener(this);
         
                  
          JPanel paButton = new JPanel();
          paButton.setBounds(75, 508, 324, 62);

          paButton.add(btnSubmit);
          paButton.add(btnCancel);

          
          // ȭ�鿡 �߰�
          add(lblJoin);
          
          add(lblId);
          add(lblPwd);
          add(lblName);
          add(lblGen);
          add(lblAge);
          add(lblHp);

          add(txtId);
          add(txtPwd);
          add(txtName);
          add(lblGen);
          add(txtAge);
          add(lblhipen3);
          add(txtAge2);
          add(lblhipen4);
          add(txtAge3);
          add(txtHp1);
          add(lblhipen1);
          add(txtHp2);
          add(lblhipen2);
          add(txtHp3);
          
          add(idCheckbtn);

          add(panGen);
          add(paButton);
     }
    
     public void getBasicPane(LoginFrame loginFrame) {
 		this.loginFrame = loginFrame;
 		
 		loginFrame.setTitle("ȸ������");
 		
 		contentPane = loginFrame.getContentPane();
  	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String id = txtId.getText();
		String pw = new String(txtPwd.getPassword());
		String name = txtName.getText();
		// gen�� ������ �޾ƿ�
		
		String age2 = txtAge2.getText();
		String age3 = txtAge3.getText();
		
		if(age2.length()<2) {
			age2 = "0" + age2;
		}
		if(age3.length()<2) {
			age3 = "0" + age3;
		}
		
		String birthDate = txtAge.getText() + "-" + age2 + "-" + age3;
		String phone = txtHp1.getText() + txtHp2.getText() + txtHp3.getText();
		
		
		if(e.getSource() == btnSubmit) {
			
			// �Է����� ���� �׸��� �ִٸ�
			if(id.equals("") || pw.equals("") || name.equals("") || txtAge.getText().equals("")
					|| age2.equals("") || age3.equals("") || txtHp2.getText().equals("") || txtHp3.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "��� �׸��� �ԷµǾ�� �մϴ�.");
			} else if(Integer.valueOf(age2)<1 || Integer.valueOf(age2)>12 
					|| Integer.valueOf(age3)<1 || Integer.valueOf(age3)>31) {
				JOptionPane.showMessageDialog(this, "������Ͽ� �ùٸ� ���� �Է����ּ���.");
			} else if(idCheckbtnFlag == false) {
				JOptionPane.showMessageDialog(this, "���̵� �ߺ�Ȯ���� ���ּ���.");
			} else if(idCheckFlag == false) {
				JOptionPane.showMessageDialog(this, "�̹� �����ϴ� ���̵��Դϴ�.");
			} else {
				
				User_DTO dto = new User_DTO();
				
				dto.setId(id);
				dto.setPw(pw);
				dto.setName(name);
				dto.setBirthDate(birthDate);
				dto.setGen(gen);
				dto.setPhone(phone);
				
				int n = dao.insertUser(dto);	// ȸ�����̺� �߰�
				
				if(n>0) {	// �߰� ����
					JOptionPane.showMessageDialog(this, "ȸ������ ����");
					
					contentPane.removeAll();
					
					LoginPane loginPane = new LoginPane();
					loginPane.getBasicPane(loginFrame, contentPane);
					
					contentPane.add(loginPane);
					
					contentPane.validate();
					contentPane.repaint();
					
				} else {	// �߰� ����
					JOptionPane.showMessageDialog(this, "ȸ������ ����");
				}
			}
			
		} else if (e.getSource() == btnCancel) {
			contentPane.removeAll();
			
			LoginPane loginPane = new LoginPane();
			loginPane.getBasicPane(loginFrame, contentPane);
			
			contentPane.add(loginPane);
			
			contentPane.validate();
			contentPane.repaint();
		}
		
		if(e.getSource() == idCheckbtn) {
			idCheckbtnFlag = true;
			boolean flag = dao.checkId(id);
			
			if(flag == true) {
				idCheckFlag = true;
				JOptionPane.showMessageDialog(this, "��밡���� ���̵��Դϴ�.");
			} else {
				idCheckFlag = false;
				JOptionPane.showMessageDialog(this, "�̹� �����ϴ� ���̵��Դϴ�.");
			}
		}
		
	}

}


