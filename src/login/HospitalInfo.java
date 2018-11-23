package login;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
 
public class HospitalInfo extends JPanel {
	
	JPanel basicPane;
    	
	public HospitalInfo() {        
		
		setBackground(new Color(255, 255, 255));        
		
		setBounds(0,0,1500,732);	// 크기, 위치 설정
		
		Color color = new Color(50, 205, 50);	// 테두리 색
		LineBorder lb = new LineBorder(color, 5 , false); // 테두리 커스텀
		setBorder(lb);	// 테두리 설정
		
        this.setLayout(null);
        
        ImageIcon hsImage = new ImageIcon("./src/login/hs.jpg");
        
        
        JLabel hsImagelbl = new JLabel(hsImage);
        hsImagelbl.setLocation(281, 54);
        hsImagelbl.setSize(464, 297);
        
        add(hsImagelbl);
        
        JLabel hslbl = new JLabel("병원소개");
        hslbl.setForeground(new Color(0, 128, 0));
        hslbl.setFont(new Font("나눔고딕", Font.BOLD, 18));
        hslbl.setBounds(823, 54, 464, 46);
        add(hslbl);
        
        JLabel hslbl2 = new JLabel("<html><body>KH 병원은 환자 중심의 병원으로 거듭나기 위하여 특성화된 진료를 포함한 양질의 진료, "
        		+ "심신이 편안함을 느끼는 쾌적한 환경, 가슴에 와 닿는 고객감동의 세 가지 사항을 충족시키는데 주력하겠습니다.<br>"
        		+ "그리하여 환자와 보호자 분들께는 친절과 봉사로써, 동료 간에는 신뢰와 협력, 업무에는 자율과 책임으로 맑고 밝고 부드러운 병원을 만들어 "
        		+ "다시 찾고 싶은 병원이 되도록 노력하겠습니다.<br><br>"
        		+ "한 분, 한 분의 마음의 소리까지 듣겠습니다.<br><br>"
        		+ "감사합니다.</body></html>");
        hslbl2.setFont(new Font("나눔고딕", Font.BOLD, 15));
        hslbl2.setBounds(823, 112, 464, 186);
        add(hslbl2);
        
        ImageIcon hsImage2 = new ImageIcon("./src/login/hsinfo1.jpg");
        
        JLabel hsImagelbl2 = new JLabel(hsImage2);
        hsImagelbl2.setBounds(281, 373, 464, 297);
        add(hsImagelbl2);
        
        JLabel hslbl3 = new JLabel("찾아오시는 길");
        hslbl3.setForeground(new Color(0, 128, 0));
        hslbl3.setFont(new Font("나눔고딕", Font.BOLD, 18));
        hslbl3.setBounds(823, 373, 464, 46);
        add(hslbl3);
        
        JLabel hslbl4 = new JLabel("<html><body>주소 : 서울특별시 강남구 테헤란로 14길 6<br>     남도빌딩 2F, 3F, 4F, 5F<br>"
        		+ "     (T: 1544-9970 / F: 070-8290-2889)<br><br>"
        		+ "버스<br>"
        		+ "     역삼역 포스코P&S타워 정류장<br>"
        		+ "     146 / 740 / 341 / 360<br>"
        		+ "     1100 / 1700 / 2000 / 7007 / 8001<br><br>"
        		+ "지하철  2호선 역삼역 3번출구 100m</body></html>");
        hslbl4.setFont(new Font("나눔고딕", Font.BOLD, 15));
        hslbl4.setBounds(823, 431, 464, 186);
        add(hslbl4);

    }
}
