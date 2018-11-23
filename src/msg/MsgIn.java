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
	
	
	private JTextArea ta= new JTextArea(); // 쪽지 발송 관련
	private JPanel contentPane;
	private JTextField textField; 
	private JTextArea txtMsgSb; //쪽지 제목
	private JTextArea  txtMsgIn; //쪽지 내용
	private JButton btnSend;  //보내기 버튼
	private JButton btnCancel; //취소 버튼
	
	Res_DTO user;
	
	Msg_DAO mdao = Msg_DAO.getInstance();
	Msg_DTO mdto = new Msg_DTO();
	

	public MsgIn(Res_DTO user) {
		super("쪽지함");
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
		txtMsgSb.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		txtMsgSb.setText("제목을 입력해주세요");
		txtMsgSb.setForeground(Color.gray);
		txtMsgSb.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(txtMsgSb.getText().isEmpty()) {
					txtMsgSb.setText("제목을 입력해주세요");
					txtMsgSb.setForeground(Color.gray);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if(txtMsgSb.getText().equals("제목을 입력해주세요")) {
					txtMsgSb.setText("");
					txtMsgSb.setForeground(Color.black);
				}
			}
		});
		
		//쪽지 제목
		txtMsgSb.setBounds(45, 30, 290, 30);
		txtMsgSb.setColumns(10);
		panel.add(txtMsgSb);
		
		//쪽지내용 입력
		txtMsgIn = new JTextArea();
		txtMsgIn.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		txtMsgIn.setText("내용을 입력해주세요");
		txtMsgIn.setForeground(Color.gray);
		txtMsgIn.setBounds(45, 70, 290, 300);
		txtMsgIn.setColumns(10);
		txtMsgIn.setLineWrap(true);
		
		txtMsgIn.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(txtMsgIn.getText().isEmpty()) {
					txtMsgIn.setText("내용을 입력해주세요");
					txtMsgIn.setForeground(Color.gray);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if(txtMsgIn.getText().equals("내용을 입력해주세요")) {
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
		
		//보내기 버튼
		btnSend = new JButton("보내기");
		btnSend.setFont(new Font("나눔고딕", Font.BOLD, 15));
		btnSend.setBounds(50, 380, 80, 30);
		btnSend.addActionListener(this);
		panel.add(btnSend);
		
		
		//취소 버튼
		btnCancel = new JButton("취소");
		btnCancel.setFont(new Font("나눔고딕", Font.BOLD, 15));
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
					JDialog dialog = new MsgOkBtn_Dialog("쪽지 보내기", false, "o");
					dialog.setVisible(true);
					System.out.println("전송완료");
					dispose();
				} else {
					JDialog dialog = new MsgOkBtn_Dialog("쪽지 보내기", false, "x");
					dialog.setVisible(true);
					System.out.println("전송실패");
					dispose();
				}
				
				
			}
		}
	}
}
