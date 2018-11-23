package myPageMember;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import DB.Msg.Msg_DAO;
import DB.Msg.Msg_DTO;
import DB.Res.Res_DAO;
import DB.Res.Res_DTO;
import msg.MsgList;
import thrms.MainFrame;

public class MyPageMember extends JPanel {

	//--------------------���
	private MainFrame mainFrame;
	
	private JPanel MMres;  //��ܱ⺻�г�
	private JPanel MMTopL;  //��� �߰��г�
	private JPanel MMTopR;  //��� �߰��г�2
	
	private JLabel MMcpL; //������ �̸�
	private JButton MMcpB; //������ ��ư
	private ImageIcon profil;
	private String name;
	
	private JLabel MMcrL; // ���� ����
	private JLabel MMcrL2; // ���� ���� �Ǽ�
	private int n;  //�Ǽ�
	
	private JLabel MMcmL; // ������
	private JLabel MMcmL2; // ������ �Ǽ�
	private int nn;  //�����԰Ǽ�
	 //--------------------��� ��
	
	//--------------------�ϴ�
	private DefaultTableModel dtm;
	private JTable jtable;
	private int row;   //�޾ƿ��÷�
	private JScrollPane jsp;
	private int Tablecount;
	
	Res_DAO rdao = Res_DAO.getInstance();
	Res_DTO rdto = new Res_DTO();
	Msg_DAO mdao = Msg_DAO.getInstance();
	Msg_DTO mdto = new Msg_DTO();
	
	String header[] = {"���� ��¥", "���� �ð�", "���� ����", "�����", "���� ����"};
	//--------------------�ϴ� ��
	
	//��Ÿ
	private LineBorder lb;  // ���κ��� 

	
	//������ --�⺻ ��׶���
	public MyPageMember(MainFrame mainFrame) {
		
		this.mainFrame = mainFrame;
	
		lb = new LineBorder(Color.BLACK, 1 , false);
		setBounds(0,0,1500,732);
		setBorder(lb);
		setBackground(Color.white);
		setLayout(null);
		MMT(); //��� �г�
		MMB();
	}
	
	public void MMB() { //�ϴ�
		
		String[][] contents = null;
		 
		ArrayList<Res_DTO> list = rdao.getResList(MainFrame.id);
		
		contents = new String[list.size()][5];
		Tablecount = 0;
		for(Res_DTO dto : list) {
			
		            contents[Tablecount][0] = dto.getResDate();
		            contents[Tablecount][1] = dto.getResTime();
		            contents[Tablecount][2] = dto.getPart();
		            contents[Tablecount][3] = dto.getDname();
		            contents[Tablecount][4] = "���೻�� Ȯ���ϱ�";
		            
		            Tablecount++;
			}
	
		DefaultTableModel model = new DefaultTableModel(contents, header) {
		public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			} 	// ���� �Ұ�
		};
		
		//���̺����
		jtable = new JTable(model);
		jtable.getColumnModel().setColumnSelectionAllowed(true);	 //�ϳ��� ����
		jtable.getTableHeader().setReorderingAllowed(false); //�÷� �̵��Ұ�
		jtable.getTableHeader().setResizingAllowed(false); //�÷� ������ ����
		jtable.setFont(new Font("�������", Font.PLAIN, 13)); // ���� ũ��
		jtable.setForeground(Color.black); //����
		jtable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(jtable.getSelectedColumn()==4) {
					new Info(mainFrame, MainFrame.id, jtable.getSelectedRow());
				}
			};
		});
		
		MMcrL2.setText(jtable.getRowCount()+"��");  //���� �Ǽ�
		
		row = jtable.getSelectedRow();
		
		jsp = new JScrollPane(jtable);
		jsp.setBounds(145, 320, 1200, 370);
		
		add(jsp);
	}
	
	
	public void MMT() {  //��� 
	
		MMres = new JPanel();
		MMres.setBounds(145, 30, 1200, 280);
		MMres.setBackground(Color.LIGHT_GRAY);
		MMres.setLayout(null);
		MMTop();
		MMpro();
		add(MMres);
	}
	
	
	public void MMTop() {  //��� �߰��г�
	
		MMTopL = new JPanel();
		MMTopL.setBounds(410, 20 ,380, 240);
		MMTopL.setBackground(Color.LIGHT_GRAY);
		MMTopL.setLayout(null);
		
		MMTopR = new JPanel();
		MMTopR.setBounds(800, 20, 380, 240);
		MMTopR.setBackground(Color.LIGHT_GRAY);
		MMTopR.setLayout(null);
		
		initMMres();
		initMMmsg();
		
		MMres.add(MMTopL);
		MMres.add(MMTopR);
	}
	
	
	public void initMMres() {   //���  ���Ό��
		
		MMcrL = new JLabel("���� ����");
		MMcrL.setBounds(130, 40, 200, 30);
		
		MMcrL2 = new JLabel();  //DB����
		MMcrL2.setBounds(100, 90, 300, 100);
		
		menulb2(MMcrL);
		menulb3(MMcrL2);
		
		MMTopL.add(MMcrL);
		MMTopL.add(MMcrL2);
		
	}
	
	
	public void initMMmsg() {   //��� ������
		
		MMcmL = new JLabel("������");
		MMcmL.setFont(new Font("�������", Font.PLAIN, 18));
		MMcmL.setBounds(90, 40, 200, 30);
		
		ArrayList<Msg_DTO> list = mdao.getMsgUser(MainFrame.id);
			
		nn=list.size();
		MMcmL2 = new JLabel(nn+"��");  //DB����
		MMcmL2.setFont(new Font("�������", Font.PLAIN, 75));
		
		MMcmL2.addMouseListener(new MouseAdapter() {
			@Override
		    public void mouseClicked(MouseEvent e) {
				new MsgList();
		    }
		});
		
		MMcmL2.setBounds(40, 90, 300, 100);
		
		menulb2(MMcmL);
		menulb3(MMcmL2);
		
		MMTopR.add(MMcmL);
		MMTopR.add(MMcmL2);
	}
	
	public void MMpro() {
		//������	
		profil= new ImageIcon ("./src/myPageMember/pro.png");	
		MMcpB = new JButton(profil)	;
		MMcpB.setFont(new Font("�������", Font.PLAIN, 15));
		MMcpB.setBounds(110,65,110,110);
		MMcpB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
				JDialog dialog = new PWConfirmDialog2(mainFrame);
				dialog.setVisible(true);
			}
		});
		
		//��ư ����ó��
		MMcpB.setBorderPainted(false);
		MMcpB.setFocusPainted(false);
		MMcpB.setContentAreaFilled(false);
		
		MMres.add(MMcpB);
		
		name = MainFrame.name;
		MMcpL = new JLabel(name);
		MMcpL.setFont(new Font("�������", Font.BOLD, 15));
		MMcpL.setBounds(140, 180, 200, 30);
		
		menulb2(MMcpL);
		MMres.add(MMcpL);
	}
	
	
	private void menulb(JComponent c) {
		c.setFont(c.getFont().deriveFont(25.0f)); // ���� ũ��
		c.setForeground(Color.black);
	}
	
	private void menulb2(JComponent c) {
		c.setFont(new Font("�������", Font.PLAIN, 18)); // ���� ũ��
		c.setForeground(Color.black);
	}
	
	private void menulb3(JComponent c) {
		c.setFont(new Font("�������", Font.PLAIN, 75)); // ���� ũ��
		c.setForeground(Color.black);
	}
	
	private void menulb4(JComponent c) {
		c.setFont(c.getFont().deriveFont(10.0f)); // ���� ũ��
		c.setForeground(Color.blue);
	}
}