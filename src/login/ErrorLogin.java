package login;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ErrorLogin extends JDialog implements ActionListener {
		
		private String title, match;
		private boolean modal;
		
		private JPanel pane;
		private JLabel textlbl;
		private JButton okbtn, cancelbtn;
		
		public ErrorLogin(String title, boolean modal, String match) {
			this.title = title;
			this.modal = modal;
			this.match = match;
			
			setLocation(750, 400);
			setSize(400, 180);
			setTitle(title);
			setModal(modal);
			
			setLayout(new GridLayout(2, 0));
			
			initText();
			
			pane = new JPanel();

			okbtn = new JButton("Ȯ��");
			
			pane.add(okbtn);
			
			add(pane);
			
			okbtn.addActionListener(this);
		}
		
		private void initText() {
			if(match.equals("none")) {
				textlbl = new JLabel("ȸ�������� �������� �ʽ��ϴ�", JLabel.CENTER);
			} else if(match.equals("x")) {
				textlbl = new JLabel("��й�ȣ�� ��ġ���� �ʽ��ϴ�.", JLabel.CENTER);
			} 
			textlbl.setFont(textlbl.getFont().deriveFont(15.0f));
			add(textlbl);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
		
	}

