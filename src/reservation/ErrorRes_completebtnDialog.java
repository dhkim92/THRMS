package reservation;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ErrorRes_completebtnDialog extends JDialog implements ActionListener {

	private String title;
	private boolean modal;
	private String wrongThing;
	
	private JPanel pane;
	private JLabel textlbl;
	private JButton okbtn;
	
	public ErrorRes_completebtnDialog(String title, boolean modal, String wrongThing) {
		this.title = title;
		this.modal = modal;
		this.wrongThing = wrongThing;
		
		setLocation(750, 400);
		setSize(400, 180);
		setTitle(title);
		setModal(modal);
		
		setLayout(new GridLayout(2, 0));
		
		textlbl = new JLabel("모든 항목이 입력되어야 합니다.", JLabel.CENTER);
		textlbl.setFont(textlbl.getFont().deriveFont(15.0f));
		add(textlbl);
		
		pane = new JPanel();

		okbtn = new JButton("확인");
		
		pane.add(okbtn);
		
		add(pane);
		
		okbtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();		// 다이얼로그 닫기
	}
}
