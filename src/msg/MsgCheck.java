package msg;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DB.Msg.Msg_DAO;
import DB.Msg.Msg_DTO;

public class MsgCheck extends JFrame {

	private MsgList msgList;
	private JTextArea ta; 
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea txtMsgSb;
	private JTextArea txtMsgIn;
	private JButton btnCheck, btnDel;
	
	Msg_DTO user;
	Msg_DAO mdao = Msg_DAO.getInstance();

	public MsgCheck(MsgList msgList, Msg_DTO user) {
		
		super("쪽지함 확인");
		this.msgList = msgList;
		this.user = user;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 500);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 350, 500);
		contentPane.add(panel);
		panel.setLayout(null);

		
		//쪽지 제목 부분
		txtMsgSb = new JTextArea(user.getTitle());
		txtMsgSb.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		txtMsgSb.setBounds(45, 30, 290, 30);
		panel.add(txtMsgSb);
		txtMsgSb.setColumns(10);
		txtMsgSb.setEditable(false);//수정불가
		
		
		//쪽지 내용 부분
		txtMsgIn = new JTextArea(user.getText());
		txtMsgIn.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		txtMsgIn.setBounds(45, 70, 290, 300);
		panel.add(txtMsgIn);
		txtMsgIn.setColumns(10);
		txtMsgIn.setLineWrap(true);
		txtMsgIn.setEditable(false);// 수정불가
		
		
		//쪽지 확인 시 확인 누르면 닫힘
		btnCheck = new JButton("확인");
		btnCheck.setFont(new Font("나눔고딕", Font.BOLD, 15));
		btnCheck.setBounds(103, 380, 80, 30);
		btnCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panel.add(btnCheck);
		
		btnDel = new JButton("삭제");
		btnDel.setFont(new Font("나눔고딕", Font.BOLD, 15));
	      btnDel.setBounds(190,380,80,30);
	      btnDel.addActionListener(new ActionListener() {
	         
	         @Override
	         public void actionPerformed(ActionEvent e) {
	            int n = mdao.deleteMsg(user.getIdx());
	            if(n>0) {
	               JOptionPane.showMessageDialog(null, "삭제가 완료되었습니다.");
	               
	               msgList.getContentPane().removeAll();
	               msgList.initMsgtable();
	               
	               msgList.validate();
	               msgList.repaint();
	               
	               dispose();
	            } else {
	               JOptionPane.showMessageDialog(null, "삭제가 실패하였습니다.");
	            }
	            
	         }
	      });
	      
	      panel.add(btnDel);
	}
	
	
}


