package mainScreen;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResPane extends JPanel {
	
	private JPanel resPane;
	private JLabel Rtitletxt;
	protected Color clr = new Color(50, 205, 50);	// �׵θ� ��
	
	public ResPane() {
		setBounds(0,0,600,365);
		setBackground(clr);
		setLayout(null);
		
		resTitle();
		resCalendar();
	}
	
	private void resTitle() {
		resPane = new JPanel();
		resPane.setBounds(0,0,600,40);
		resPane.setBackground(clr);
		resPane.setLayout(null);
		
		Rtitletxt = new JLabel("���� ��Ȳ");
		Rtitletxt.setForeground(Color.WHITE);
		Rtitletxt.setBounds(10,4,245,40);
		Rtitletxt.setFont(new Font("�������", Font.PLAIN, 30));
		
		resPane.add(Rtitletxt);
		add(resPane);
		
	}

	private void resCalendar() {
		add(new ResCalendar());
	}
	
}
