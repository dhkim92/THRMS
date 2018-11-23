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
		
		setBounds(0,0,1500,732);	// ũ��, ��ġ ����
		
		Color color = new Color(50, 205, 50);	// �׵θ� ��
		LineBorder lb = new LineBorder(color, 5 , false); // �׵θ� Ŀ����
		setBorder(lb);	// �׵θ� ����
		
        this.setLayout(null);
        
        ImageIcon hsImage = new ImageIcon("./src/login/hs.jpg");
        
        
        JLabel hsImagelbl = new JLabel(hsImage);
        hsImagelbl.setLocation(281, 54);
        hsImagelbl.setSize(464, 297);
        
        add(hsImagelbl);
        
        JLabel hslbl = new JLabel("�����Ұ�");
        hslbl.setForeground(new Color(0, 128, 0));
        hslbl.setFont(new Font("�������", Font.BOLD, 18));
        hslbl.setBounds(823, 54, 464, 46);
        add(hslbl);
        
        JLabel hslbl2 = new JLabel("<html><body>KH ������ ȯ�� �߽��� �������� �ŵ쳪�� ���Ͽ� Ư��ȭ�� ���Ḧ ������ ������ ����, "
        		+ "�ɽ��� ������� ������ ������ ȯ��, ������ �� ��� �������� �� ���� ������ ������Ű�µ� �ַ��ϰڽ��ϴ�.<br>"
        		+ "�׸��Ͽ� ȯ�ڿ� ��ȣ�� �е鲲�� ģ���� ����ν�, ���� ������ �ŷڿ� ����, �������� ������ å������ ���� ��� �ε巯�� ������ ����� "
        		+ "�ٽ� ã�� ���� ������ �ǵ��� ����ϰڽ��ϴ�.<br><br>"
        		+ "�� ��, �� ���� ������ �Ҹ����� ��ڽ��ϴ�.<br><br>"
        		+ "�����մϴ�.</body></html>");
        hslbl2.setFont(new Font("�������", Font.BOLD, 15));
        hslbl2.setBounds(823, 112, 464, 186);
        add(hslbl2);
        
        ImageIcon hsImage2 = new ImageIcon("./src/login/hsinfo1.jpg");
        
        JLabel hsImagelbl2 = new JLabel(hsImage2);
        hsImagelbl2.setBounds(281, 373, 464, 297);
        add(hsImagelbl2);
        
        JLabel hslbl3 = new JLabel("ã�ƿ��ô� ��");
        hslbl3.setForeground(new Color(0, 128, 0));
        hslbl3.setFont(new Font("�������", Font.BOLD, 18));
        hslbl3.setBounds(823, 373, 464, 46);
        add(hslbl3);
        
        JLabel hslbl4 = new JLabel("<html><body>�ּ� : ����Ư���� ������ ������� 14�� 6<br>     �������� 2F, 3F, 4F, 5F<br>"
        		+ "     (T: 1544-9970 / F: 070-8290-2889)<br><br>"
        		+ "����<br>"
        		+ "     ���￪ ������P&SŸ�� ������<br>"
        		+ "     146 / 740 / 341 / 360<br>"
        		+ "     1100 / 1700 / 2000 / 7007 / 8001<br><br>"
        		+ "����ö  2ȣ�� ���￪ 3���ⱸ 100m</body></html>");
        hslbl4.setFont(new Font("�������", Font.BOLD, 15));
        hslbl4.setBounds(823, 431, 464, 186);
        add(hslbl4);

    }
}
