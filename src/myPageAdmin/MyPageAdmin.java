package myPageAdmin;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import DB.Res.Res_DAO;
import DB.Res.Res_DTO;
import msg.MsgIn;

public class MyPageAdmin extends JPanel{

	private static final long serialVersionUID = 1L;

	JPanel basicPane;
	
	Calendar cal = Calendar.getInstance(); 

	int year = cal.get(Calendar.YEAR); 
	int month = cal.get(Calendar.MONTH)+1; 
	int day = cal.get(Calendar.DATE); 

	//====��====
	JPanel txtPane; // �� �г�
	JPanel MAmenuPane; // ������ �޴� �г�
	JPanel MAallResPane; // �� ������Ȳ
	JPanel MAtodayResPane; // �ش� ��¥ ������Ȳ
	//====�Ʒ�====
	JScrollPane scrollPane; // �Ʒ� �г�
	
	//====��====
	JLabel MAcalendartxt; // ���� �޷� ���̺�
	JLabel MAmembertxt; // ȸ�� ���� ���̺�
	JLabel MAmsgtxt; // ���������� ���̺�
	JLabel MAtodayRestxt; // ���� ������Ȳ ���̺�
	JLabel MAallRestxt; // ��ü ������Ȳ ���̺�
	JLabel MATnum; // ���� ���� ��Ȳ �Ǽ�
	JLabel MAAnum; // ��ü ���� ��Ȳ �Ǽ�
	//====�Ʒ�====
	JTable MAtodayTable; // ���� ������Ȳ ���̺�
	
	
	Res_DAO rdao = Res_DAO.getInstance();
	Res_DTO rdto = new Res_DTO();
	
	Res_DTO tt;
	
	protected Color color = new Color(29, 219, 22);
	
	ArrayList<Res_DTO> list;
	
	String[] header = {"No","����","�������","�����","���� ��¥","���� �ð�","����","���� ����"};

	
	public MyPageAdmin() {
		
		setBounds(0,0,1500,732);	// ũ��, ��ġ ����
		
		Color color = new Color(29, 219, 22);	// �׵θ� ��
		LineBorder lb = new LineBorder(color, 5 , false); // �׵θ� Ŀ����
		setBorder(lb);	// �׵θ� ����
		setLayout(null);
	
		initMAmenu();
		initMAtodayTable();
	}
	
// ���г�
	private void initMAmenu() {
	
		txtPane = new JPanel();
		txtPane.setLayout(null);
		txtPane.setBounds(145, 30, 1200, 280);
		txtPane.setBackground(Color.LIGHT_GRAY);
		
		//-----------------�޴� ���� ��---------------------
		MAmenuPane = new JPanel(); // �޴� ���� ��
		MAmenuPane.setBounds(20, 20, 380, 240);
		MAmenuPane.setBackground(Color.LIGHT_GRAY);
		MAmenuPane.setLayout(new GridLayout(3,0));
		
		MAcalendartxt = new JLabel("�� ���� ��Ȳ"); // ���� �޷� ���̺�
		MAcalendartxt.setHorizontalAlignment(JLabel.CENTER);
		
		MAcalendartxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeAll();
				add(new AllResCalendar());
				validate();
				repaint();
			}
		});
		MAmembertxt = new JLabel("ȸ �� �� ��"); // ȸ�� ���� ���̺�
		MAmembertxt.setHorizontalAlignment(JLabel.CENTER);
		
		MAmembertxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeAll();
				add(new MemberManage());
				validate();
				repaint();
			}
		});
		
		MAmsgtxt = new JLabel("���� ������"); // ���������� ���̺�
		MAmsgtxt.setHorizontalAlignment(JLabel.CENTER);
		MAmsgtxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tt == null) {
					JDialog dialog = new ErrorMSGConfirm_okbtnDialog("����������", false, "o");
					dialog.setVisible(true);
				} else {
				System.out.println(tt.getId());
				MsgIn mi = new MsgIn(tt);
				mi.setVisible(true);
				}
			}
		});
		
		MAmenuPane.add(MAcalendartxt);
		MAmenuPane.add(MAmembertxt);
		MAmenuPane.add(MAmsgtxt);
		
		menulb(MAcalendartxt);
		menulb(MAmembertxt);
		menulb(MAmsgtxt);
		
		//------------------- ���� ���� ��Ȳ -----------------------
		
		
		MAtodayResPane = new JPanel(); // ���� ������Ȳ
		MAtodayResPane.setBounds(410, 20 ,380, 240);
		MAtodayResPane.setBackground(Color.LIGHT_GRAY);
		MAtodayResPane.setLayout(null);
		
		MAtodayRestxt = new JLabel("���� ���� ����"); // ���� ������Ȳ ���̺�
		MAtodayRestxt.setHorizontalTextPosition(JLabel.CENTER);
		MAtodayRestxt.setBounds(130, 40, 200, 30);
		
		list = rdao.getResListAll();
		
		for (int i = 0; i < list.size(); i++) {
	        String mon = String.valueOf(month);
	        String d = String.valueOf(day);
	        if(mon.length()<2) {
	           mon = "0" + mon;
	        }
	        if(d.length()<2) {
	           d = "0" + d;
	        }
		MATnum = new JLabel(((Integer)rdao.getResDate(year+"-"+mon+"-"+day).size()).toString()); // �������� �κ�
		}
		
		MATnum.setHorizontalAlignment(JLabel.CENTER);
		MATnum.setBounds(40, 90, 300, 100);
		
		MAtodayResPane.add(MAtodayRestxt);
		MAtodayResPane.add(MATnum);
		
		menulb2(MAtodayRestxt);
		menulb3(MATnum);
		
		//------------------ �� ���� ��Ȳ ------------------------
		MAallResPane = new JPanel(); // �� ������Ȳ
		MAallResPane.setBounds(800, 20, 380, 240);
		MAallResPane.setBackground(Color.LIGHT_GRAY);
		MAallResPane.setLayout(null);
		
		MAallRestxt = new JLabel("�� ���� ����"); // �� ������Ȳ
		MAallRestxt.setHorizontalAlignment(JLabel.CENTER);
		MAallRestxt.setBounds(90, 40, 200, 30);
		
		MAAnum = new JLabel(((Integer)rdao.getResListAll().size()).toString());
		
		MAAnum.setHorizontalAlignment(JLabel.CENTER);
		MAAnum.setBounds(40, 90, 300, 100);
		
		MAallResPane.add(MAallRestxt);
		MAallResPane.add(MAAnum);
		
		menulb2(MAallRestxt);
		menulb3(MAAnum);
		
		//------------------- �� �гο� �ø���-----------------------
		txtPane.add(MAmenuPane);
		txtPane.add(MAtodayResPane);
		txtPane.add(MAallResPane);
		
		txtPane.validate();
		txtPane.repaint();
		
		add(txtPane);
	}
//=================================�� �г� ��===========================================		
		
// �Ʒ� �г�
	private void initMAtodayTable() {
		
		list = rdao.getResListAll();
		
		String[][] contents = null;
		
		contents = new String[list.size()][8];

        for (int i = 0; i < list.size(); i++) {

           rdto = list.get(i);
           contents[i][0] = rdto.getIdx() + "";
           contents[i][1] = rdto.getName();
           contents[i][2] = rdto.getPart();
           contents[i][3] = rdto.getDname();
           contents[i][4] = rdto.getResDate();
           contents[i][5] = rdto.getResTime();
           contents[i][6] = rdto.getSymptoms();
           contents[i][7] = rdto.getStatus();
        }
        
        DefaultTableModel model = new DefaultTableModel(contents, header) {

        	private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int row, int column) {return false;}
        };
        
		MAtodayTable = new JTable(model);
		MAtodayTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tt = list.get(MAtodayTable.getSelectedRow());
			}
		});
		scrollPane = new JScrollPane(MAtodayTable);

		scrollPane.setBounds(145, 320, 1200, 370);

		add(scrollPane);
	}

//=================================�Ʒ� �г� ��==========================================
	
	private void menulb(JComponent c) {
		
		c.setFont(c.getFont().deriveFont(25.0f)); // ���� ũ��
		c.setForeground(Color.black);
	}
	
	private void menulb2(JComponent c) {
		
		c.setFont(c.getFont().deriveFont(18.0f)); // ���� ũ��
		c.setForeground(Color.black);
	}
	
	private void menulb3(JComponent c) {
		
		c.setFont(c.getFont().deriveFont(75.0f)); // ���� ũ��
		c.setForeground(Color.black);
	}
}
