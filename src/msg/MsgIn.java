package msg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DB.Msg.Msg_DAO;
import DB.Msg.Msg_DTO;
import DB.Res.Res_DTO;

import java.awt.Color;
import java.awt.Font;


public class MsgIn extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	
	private JTextArea ta= new JTextArea(); // ���� �߼� ����
	private JPanel contentPane;
	private JTextField textField; 
	private JTextArea txtMsgSb; //���� ����
	private JTextArea  txtMsgIn; //���� ����
	private JButton btnSend;  //������ ��ư
	private JButton btnCancel; //��� ��ư
	
	Res_DTO user;
	
	Msg_DAO mdao = Msg_DAO.getInstance();
	Msg_DTO mdto = new Msg_DTO();
	

	public MsgIn(Res_DTO user) {
		super("������");
		this.user = user;
		
		mdto = mdao.getMsg(user.getId());
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 350, 500);
		contentPane.add(panel);
		panel.setLayout(null);

		
		txtMsgSb = new JTextArea();
		txtMsgSb.setFont(new Font("�������", Font.PLAIN, 15));
		txtMsgSb.setText("������ �Է����ּ���");
		txtMsgSb.setForeground(Color.gray);
		txtMsgSb.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(txtMsgSb.getText().isEmpty()) {
					txtMsgSb.setText("������ �Է����ּ���");
					txtMsgSb.setForeground(Color.gray);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if(txtMsgSb.getText().equals("������ �Է����ּ���")) {
					txtMsgSb.setText("");
					txtMsgSb.setForeground(Color.black);
				}
			}
		});
		
		//���� ����
		txtMsgSb.setBounds(45, 30, 290, 30);
		txtMsgSb.setColumns(10);
		panel.add(txtMsgSb);
		
		//�������� �Է�
		txtMsgIn = new JTextArea();
		txtMsgIn.setFont(new Font("�������", Font.PLAIN, 15));
		txtMsgIn.setText("������ �Է����ּ���");
		txtMsgIn.setForeground(Color.gray);
		txtMsgIn.setBounds(45, 70, 290, 300);
		txtMsgIn.setColumns(10);
		txtMsgIn.setLineWrap(true);
		
		txtMsgIn.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(txtMsgIn.getText().isEmpty()) {
					txtMsgIn.setText("������ �Է����ּ���");
					txtMsgIn.setForeground(Color.gray);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if(txtMsgIn.getText().equals("������ �Է����ּ���")) {
					txtMsgIn.setText("");
					txtMsgIn.setForeground(Color.black);
				}
			}
		});
		
		txtMsgIn.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
			public void keyTyped(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtMsgIn.append(System.lineSeparator());
				}
			}
		});
		
		panel.add(txtMsgIn);
		
		//������ ��ư
		btnSend = new JButton("������");
		btnSend.setFont(new Font("�������", Font.BOLD, 15));
		btnSend.setBounds(50, 380, 80, 30);
		btnSend.addActionListener(this);
		panel.add(btnSend);
		
		
		//��� ��ư
		btnCancel = new JButton("���");
		btnCancel.setFont(new Font("�������", Font.BOLD, 15));
		btnCancel.setBounds(255, 380, 80, 30);
		btnCancel.addActionListener(this);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panel.add(btnCancel);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnSend) {
			if(txtMsgSb!=null) {
				
				mdto.setRecipient(user.getId());
				mdto.setTitle(txtMsgSb.getText());
				mdto.setText(txtMsgIn.getText());
				
				int i = mdao.insertMsg(mdto);
				if(i>0) {
					JDialog dialog = new MsgOkBtn_Dialog("���� ������", false, "o");
					dialog.setVisible(true);
					System.out.println("���ۿϷ�");
					dispose();
				} else {
					JDialog dialog = new MsgOkBtn_Dialog("���� ������", false, "x");
					dialog.setVisible(true);
					System.out.println("���۽���");
					dispose();
				}
				
				
			}
		}
	}
}
