// 예약 완료 화면

package reservation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import mainScreen.MainPane;
import thrms.MainFrame;

public class ResCompletePane extends JPanel implements ActionListener {
	
	private JPanel titlePane, textPane, btnLayoutPane;
	private int idx;
	private JLabel[] textlbl;
	private JTextArea symptomstxt;
	private JButton okbtn, cancelResbtn;	// 확인 버튼, 예약취소 버튼
	
	public ResCompletePane(int idx, JLabel[] textlbl, String symptoms) {
		setBackground(new Color(255, 255, 255));	// 크기, 위치, 레이아웃
		this.idx = idx;
		this.textlbl = textlbl;
		
		symptomstxt = new JTextArea(symptoms);
		symptomstxt.setFont(new Font("나눔고딕", Font.PLAIN, 18));
		
		setBounds(0,0,1500,732);	// 크기, 위치 설정
		
		Color color = new Color(50, 205, 50);	// 테두리 색
		LineBorder lb = new LineBorder(color, 5 , false); // 테두리 커스텀
		setBorder(lb);	// 테두리 설정
		
		initPane();
	}
	
	private void initPane() {	// 컴포넌트 생성, 추가
		setLayout(null);
		
		titlePane = new JPanel();
		titlePane.setBackground(new Color(255, 255, 255));
		titlePane.setLayout(new FlowLayout());
		titlePane.setBounds(588, 12, 300, 50);
		
		JLabel title = new JLabel("예약 완료");
		title.setFont(new Font("나눔고딕", Font.BOLD, 30));
		
		titlePane.add(title);
		
		textPane = new JPanel();
		textPane.setBackground(new Color(255, 255, 255));
		textPane.setLayout(null);
		textPane.setBounds(102, 70, 1300, 604);
		
		initTextPane();
		initBtnPane();
		
		add(titlePane);
		add(textPane);
		add(btnLayoutPane);
	}

	private void initTextPane() {
		Font font = new Font("나눔고딕", Font.BOLD, 20);
		
		for(int i=0; i<textlbl.length; i++) {
			textlbl[i].setFont(font);
		}
		
		textlbl[0].setBounds(192, 67, 150, 25);
		textlbl[1].setBounds(394, 67, 150, 25);
		textlbl[2].setBounds(192, 149, 150, 25);
		textlbl[3].setBounds(394, 149, 150, 25);
		textlbl[4].setBounds(192, 238, 150, 25);
		textlbl[5].setBounds(394, 238, 150, 25);
		textlbl[6].setBounds(719, 67, 150, 25);
		textlbl[7].setBounds(939, 67, 150, 25);
		textlbl[8].setBounds(719, 149, 150, 25);
		textlbl[9].setBounds(939, 149, 150, 25);
		textlbl[10].setBounds(719, 238, 150, 25);
		textlbl[11].setBounds(939, 238, 150, 25);
		textlbl[12].setBounds(719, 331, 150, 25);
		textlbl[13].setBounds(939, 331, 150, 25);
		textlbl[14].setBounds(719, 426, 150, 25);
				
		symptomstxt.setBounds(929, 426, 315, 149);
		symptomstxt.setEnabled(false);
		symptomstxt.setLineWrap(true);	// 자동 줄바꿈
		
		JScrollPane symptomsScroll = new JScrollPane(symptomstxt);
		symptomsScroll.setBounds(929, 426, 315, 149);
		symptomsScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		for(int i=0; i<textlbl.length; i++) {
			textPane.add(textlbl[i]);
		}
		
		textPane.add(symptomsScroll);
	}

	private void initBtnPane() {
		okbtn = new JButton("메인으로 이동");
		okbtn.setFont(new Font("나눔고딕", Font.BOLD, 15));
		okbtn.addActionListener(this);
		
		cancelResbtn = new JButton("예약취소");
		cancelResbtn.setFont(new Font("나눔고딕", Font.BOLD, 15));
		cancelResbtn.addActionListener(this);
		
		btnLayoutPane = new JPanel();
		btnLayoutPane.setBackground(new Color(255, 255, 255));
		btnLayoutPane.setBounds(506, 673, 500, 49);
		btnLayoutPane.setLayout(new FlowLayout());
		
		btnLayoutPane.add(okbtn);
		btnLayoutPane.add(cancelResbtn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {	// 리스너
		if(e.getSource() == okbtn) {
			MainFrame.menuRSV.setForeground(Color.white);
			MainFrame.NmenuBoard.setForeground(Color.white);
			MainFrame.QmenuBoard.setForeground(Color.white);
			MainFrame.menuMyPage.setForeground(Color.white);
			MainFrame.menuHosInfo.setForeground(Color.white);
			
			MainFrame.basicPane.removeAll();
			
			MainFrame.basicPane.add(new MainPane());
			
			MainFrame.basicPane.validate();
			MainFrame.basicPane.repaint();
		} else if(e.getSource() == cancelResbtn) {
			PWConfirmDialog pwConfirmDialog = new PWConfirmDialog(idx);
			pwConfirmDialog.setVisible(true);
		}
	}
}
