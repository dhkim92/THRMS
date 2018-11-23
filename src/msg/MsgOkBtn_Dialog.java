package msg;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;

public class MsgOkBtn_Dialog extends JDialog implements ActionListener {
	
	private String title, match;
	private boolean modal;
	
	private JPanel pane;
	private JLabel textlbl;
	private JButton okbtn;
	
	public MsgOkBtn_Dialog(String title, boolean modal, String match) {
		getContentPane().setFont(new Font("�������", Font.PLAIN, 15));
		this.title = title;
		this.modal = modal;
		this.match = match;
		
		setLocation(750, 400);
		setSize(400, 180);
		setTitle(title);
		setModal(modal);
		
		getContentPane().setLayout(new GridLayout(2, 0));
		
		initText();
		
		pane = new JPanel();

		okbtn = new JButton("Ȯ��");
		okbtn.setFont(new Font("�������", Font.BOLD, 15));
		
		pane.add(okbtn);
		
		getContentPane().add(pane);
		
		okbtn.addActionListener(this);
	}
	
	private void initText() {
		if(match.equals("o")) {
			textlbl = new JLabel("������ �Ϸ�Ǿ����ϴ�.", JLabel.CENTER);
			
		} else if(match.equals("x")) {
			textlbl = new JLabel("������ �����Ͽ����ϴ�.", JLabel.CENTER);
			
		}
		textlbl.setFont(textlbl.getFont().deriveFont(15.0f));
		getContentPane().add(textlbl);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}
	
}
