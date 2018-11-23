package myPageAdmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DB.User.User_DAO;
import DB.User.User_DTO;
import myPageMember.InfoUpdateOk_Dialog;
import thrms.MainFrame;

public class MemberInfo  extends JFrame implements ActionListener, KeyListener{

	private static final long serialVersionUID = 1L;
	private Container root;
	JPanel btnPane; // ��ư�г�
	JPanel btnPane2; // ��ư�г�2
	JPanel infoPane; // ȸ�������г�
	JPanel infoPaneget; // ȸ�������г�2
	JPanel infoUp; // ȸ������ ���� �г�
	JPanel basicPane;  //����������
	JPanel basicPane2;  //���ƾ����г�
	
	//ȸ������ ����
	JLabel IDtxt; // ID ���̺�
	JLabel PWtxt; // PW ���̺�
	JLabel Nametxt; // ���� ���̺�
	JLabel Birthtxt; // ������� ���̺�
	JLabel Gendertxt; // ���� ���̺�
	JLabel Phonenumtxt; // ���� ���̺�
	
	
	//ȸ������ -db����
	JLabel IDget; // ID ���̺�
	JLabel PWget; // PW ���̺�
	JLabel Nameget; // ���� ���̺�
	JLabel Birthget; // ������� ���̺�
	JLabel Genderget; // ���� ���̺�
	JLabel Phonenumget; // ���� ���̺�
	
	int k;
	int k2;
	
	//ȸ������ ����
	
	JTextField PWinput; // PW ���̺�
	JTextField Phonenuminput; //��ȭ��ȣ
	JPasswordField Phonenuminputt;//
	String p = "�н�����";
	String n = "��ȭ��ȣ";

	JButton Upbtn; // ������Ʈ ��ư
	JButton Okbtn; // Ȯ��
	JButton Wdbtn; // Ż���ư
	
	JButton Cancel; // ĵ����ư
	JButton Okbtn2; // �������� Ȯ�ι�ư
	
	User_DTO udto = new User_DTO();
	User_DAO udao = User_DAO.getInstance();
	
	public MemberInfo(User_DTO user) {
		super("ȸ�� ����");
		this.udto = user;
		
		//��Ʈ
		root = getContentPane();
		setBounds(1000,300,500,700);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		root.setLayout(new BorderLayout());
		
		initbtn();   //��Ʈ�� �߰�
		
		Basic();   //
		
	}
	
	private void Basic() {
		basicPane = new JPanel();
		basicPane.setSize(3000,3000);
		basicPane.setLayout(null);
		initinfo(basicPane);  // basicPane�� basicPane2�� �߰�
		initinfoget();  //  basicPane�� �߰� 
		root.add(basicPane);
	}


	private void initinfo(JPanel J) {
		infoPane = new JPanel();
		infoPane.setBounds(50, 30, 200, 400);
		infoPane.setLayout(new GridLayout(6, 0));
		
		Nametxt = new JLabel("��      �� ");
		Gendertxt = new JLabel("��      ��");
		Birthtxt = new JLabel("�������");
		
		IDtxt = new JLabel("��  ��  ��");
		PWtxt = new JLabel("�н�����");
		Phonenumtxt = new JLabel("��ȭ��ȣ");
		
		
		Font(Nametxt);
		Font(Gendertxt);
		Font(Birthtxt);
		Font(IDtxt);
		Font(PWtxt);
		Font(Phonenumtxt);
		
		infoPane.add(Nametxt);
		infoPane.add(Gendertxt);
		infoPane.add(Birthtxt);
		infoPane.add(IDtxt);
		infoPane.add(PWtxt);
		infoPane.add(Phonenumtxt);
		
		J.add(infoPane);
	}

	
	private void initinfoget() {  //ȸ������ db��������
		
		infoPaneget = new JPanel();
		infoPaneget.setBounds(250, 30, 200, 400);
		infoPaneget.setLayout(new GridLayout(6, 0));

		Nameget = new JLabel(udto.getName());
		Genderget = new JLabel(udto.getGen());
		Birthget = new JLabel(udto.getBirthDate());
		
		IDget = new JLabel(udto.getId());
		PWget = new JLabel(udto.getPw());
		
		Phonenumget = new JLabel(udto.getPhone());
		
		
		Font(Nameget);
		Font(Genderget);
		Font(Birthget);
		Font(IDget);
		Font(PWget);
		Font(Phonenumget);
		
		infoPaneget.add(Nameget);
		infoPaneget.add(Genderget);
		infoPaneget.add(Birthget);
		infoPaneget.add(IDget);
		infoPaneget.add(PWget);
		infoPaneget.add(Phonenumget);
		
		basicPane.add(infoPaneget);
	}
	
	private void initinfoUp() {
		
		infoUp = new JPanel();
		infoUp.setBounds(250, 30, 200, 400);
		infoUp.setLayout(new GridLayout(6, 0));
		
		Nameget = new JLabel(udto.getName());
		Genderget = new JLabel(udto.getGen());
		Birthget = new JLabel(udto.getBirthDate());
		
		IDget = new JLabel(udto.getId());

		PWinput = new JTextField();
		PWinput.setColumns(12);
		
		PWinput.setText("���ο� ��й�ȣ�� �Է��ϼ���"); 
		PWinput.setForeground(Color.LIGHT_GRAY);
		
		PWinput.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(PWinput.getText().equals("")) {
				PWinput.setText("���ο� ��й�ȣ�� �Է��ϼ���");
				PWinput.setForeground(Color.lightGray);}
				
				else if(!PWinput.getText().equals("")) {PWinput.setForeground(Color.black);}
			
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if(PWinput.getText().equals("���ο� ��й�ȣ�� �Է��ϼ���")) {
				PWinput.setForeground(Color.black);
				PWinput.setText("");}
	
			}
		});
		
		Phonenuminput = new JTextField();
		
		Phonenuminput.setText("���ο� ��ȭ��ȣ�� �Է��ϼ���");
		Phonenuminput.setColumns(12);
		Phonenuminput.setForeground(Color.LIGHT_GRAY);
		Phonenuminput.addKeyListener(this);
		
		Phonenuminput.addFocusListener(new FocusListener() {
		
			@Override
			public void focusLost(FocusEvent e) {
				if(Phonenuminput.getText().equals("")) {
				Phonenuminput.setText("���ο� ��ȭ��ȣ�� �Է��ϼ���");
				Phonenuminput.setForeground(Color.lightGray);}
				
				else if(!PWinput.getText().equals("")) {
					PWinput.setForeground(Color.black);
					}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if(Phonenuminput.getText().equals("���ο� ��ȭ��ȣ�� �Է��ϼ���")) {
				Phonenuminput.setForeground(Color.black);
				Phonenuminput.setText("");}
			}
			
		});
	
		
		Font(Nameget);
		Font(Genderget);
		Font(Birthget);
		Font(IDget);
		
		infoUp.add(Nameget);
		infoUp.add(Genderget);
		infoUp.add(Birthget);
		infoUp.add(IDget);
		infoUp.add(PWinput);
		infoUp.add(Phonenuminput);
		
	
		basicPane2.add(infoUp);
		
	}
	
	
	private void initbtn() {
		btnPane = new JPanel();
		btnPane.setBounds(30, 530, 420, 50);
		btnPane.setLayout(new FlowLayout(1, 10, 10));
		Okbtn = new JButton("Ȯ��");
		Upbtn = new JButton("����"); // ������Ʈ ��ư
		Wdbtn = new JButton("ȸ�� Ż��");
		Okbtn.addActionListener(this);
		Upbtn.addActionListener(this);
		Wdbtn.addActionListener(this);
		
		
		btnFont(Okbtn);
		btnFont(Upbtn);
		btnFont(Wdbtn);
		
		btnPane.add(Okbtn);
		btnPane.add(Upbtn);
		btnPane.add(Wdbtn);
		
		root.add(btnPane, BorderLayout.SOUTH);
	}
	
	private void initbtn2() {
		
		btnPane2 = new JPanel();
		btnPane2.setBounds(30, 530, 420, 50);
		btnPane2.setLayout(new FlowLayout(1, 10, 10));
		Okbtn2 = new JButton("���� Ȯ��");
		
		Cancel = new JButton("���"); // ������Ʈ ��ư
		
		Okbtn2.setEnabled(false);
		Okbtn2.addActionListener(this);
		Cancel.addActionListener(this);
		
		btnFont(Okbtn2);
		btnFont(Cancel);
		
		btnPane2.add(Okbtn2);
		btnPane2.add(Cancel);
		
		root.add(btnPane2, BorderLayout.SOUTH);
	}
	
	private void Font(Component c) {
		c.setFont(c.getFont().deriveFont(20.0f)); // ���� ũ��
		c.setForeground(Color.black);
		c.setBackground(Color.white);
	}
	
	private void btnFont(Component c) {
		c.setFont(c.getFont().deriveFont(15.0f)); // ���� ũ��
		c.setForeground(Color.black);
		c.setBackground(Color.white);
	}

	private void Basic2() {
		basicPane2 = new JPanel();
		basicPane2.setSize(3000,3000);
		basicPane2.setLayout(null);
		initinfo(basicPane2);
		initinfoUp();
		root.add(basicPane2);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==Okbtn) {
			dispose();
		}
		else if(e.getSource()==Cancel) {
			dispose(); 
		}
		else if(e.getSource()==Okbtn2) {
			
			int i = udao.updateUserinfo(udto.getId(), PWinput.getText(), Phonenuminput.getText());
				
			if(i>0) {
				JDialog dialog = new InfoUpdateOk_Dialog("Ȯ��", false, "o");
				
				udto.setPw(PWinput.getText());
				PWget.setText(udto.getPw());
				
				udto.setPhone(Phonenuminput.getText());
				Phonenumget.setText(udto.getPhone());
				
				dialog.setVisible(true);
			} else {
				JDialog dialog = new InfoUpdateOk_Dialog("Ȯ��", false, "x");
				dialog.setVisible(true);
			} 
			
			if(!PWinput.getText().equals("") && !PWinput.getText().equals("���ο� ��й�ȣ�� �Է��ϼ���")) {
				p = PWinput.getText();
			}
			if(!Phonenuminput.getText().equals("") && !Phonenuminput.getText().equals("���ο� ��ȭ��ȣ�� �Է��ϼ���")) {
				n = Phonenuminput.getText();
			}
			
			root.removeAll();
			Basic();
			root.add(basicPane);
			initbtn();
	
			root.validate();
			root.repaint();
		}
		else if(e.getSource()==Upbtn) {
			root.removeAll();
			
			Basic2();
			root.add(basicPane2);
		
			initbtn2();
			
			root.validate();
			root.repaint();
			
		}
		
		if(e.getSource() == Wdbtn) {
			int i = udao.deleteUser(udto.getId());
			
			if(i>0) {
				JOptionPane.showMessageDialog(null, "Ż�� �Ϸ�Ǿ����ϴ�");
				
				udto.setId(null);
				udto.setPw(null);
				udto.setName(null);
				
				MainFrame.basicPane.removeAll();
				MainFrame.basicPane.add(new MyPageAdmin());
				MainFrame.basicPane.validate();
				MainFrame.basicPane.repaint();
				
				dispose();
				
			}
		}
	}
	
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	
		if(e.getSource()==Phonenuminput) {k++;}
		else if (e.getSource()==PWinput) {k2++;}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		if((Phonenuminput.getText().equals("���ο� ��ȭ��ȣ�� �Է��ϼ���")
			|| Phonenuminput.getText().equals("") || Phonenuminput.getText().length()<10 )
						
				|| (PWinput.getText().equals("���ο� ��й�ȣ�� �Է��ϼ���")
							|| PWinput.getText().equals("") || PWinput.getText().length()<6 ))	{
					Okbtn2.setEnabled(false);	
		}	

		if((!Phonenuminput.getText().equals("���ο� ��ȭ��ȣ�� �Է��ϼ���")
			&& !Phonenuminput.getText().equals("") && Phonenuminput.getText().length()>10 )
				
			|| 
			
			(!PWinput.getText().equals("���ο� ��й�ȣ�� �Է��ϼ���")
					&& !PWinput.getText().equals("") && PWinput.getText().length()>6 ))	{
			Okbtn2.setEnabled(true);}
	}
}