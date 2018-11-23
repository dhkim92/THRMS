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

	//====위====
	JPanel txtPane; // 윗 패널
	JPanel MAmenuPane; // 관리자 메뉴 패널
	JPanel MAallResPane; // 총 예약현황
	JPanel MAtodayResPane; // 해당 날짜 예약현황
	//====아래====
	JScrollPane scrollPane; // 아래 패널
	
	//====위====
	JLabel MAcalendartxt; // 예약 달력 레이블
	JLabel MAmembertxt; // 회원 관리 레이블
	JLabel MAmsgtxt; // 쪽지보내기 레이블
	JLabel MAtodayRestxt; // 오늘 예약현황 레이블
	JLabel MAallRestxt; // 전체 예약현황 레이블
	JLabel MATnum; // 오늘 예약 현황 건수
	JLabel MAAnum; // 전체 예약 현황 건수
	//====아래====
	JTable MAtodayTable; // 오늘 예약현황 테이블
	
	
	Res_DAO rdao = Res_DAO.getInstance();
	Res_DTO rdto = new Res_DTO();
	
	Res_DTO tt;
	
	protected Color color = new Color(29, 219, 22);
	
	ArrayList<Res_DTO> list;
	
	String[] header = {"No","성명","진료과목","담당의","예약 날짜","예약 시간","증상","진료 여부"};

	
	public MyPageAdmin() {
		
		setBounds(0,0,1500,732);	// 크기, 위치 설정
		
		Color color = new Color(29, 219, 22);	// 테두리 색
		LineBorder lb = new LineBorder(color, 5 , false); // 테두리 커스텀
		setBorder(lb);	// 테두리 설정
		setLayout(null);
	
		initMAmenu();
		initMAtodayTable();
	}
	
// 윗패널
	private void initMAmenu() {
	
		txtPane = new JPanel();
		txtPane.setLayout(null);
		txtPane.setBounds(145, 30, 1200, 280);
		txtPane.setBackground(Color.LIGHT_GRAY);
		
		//-----------------메뉴 적는 곳---------------------
		MAmenuPane = new JPanel(); // 메뉴 적는 곳
		MAmenuPane.setBounds(20, 20, 380, 240);
		MAmenuPane.setBackground(Color.LIGHT_GRAY);
		MAmenuPane.setLayout(new GridLayout(3,0));
		
		MAcalendartxt = new JLabel("총 예약 현황"); // 예약 달력 레이블
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
		MAmembertxt = new JLabel("회 원 관 리"); // 회원 관리 레이블
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
		
		MAmsgtxt = new JLabel("쪽지 보내기"); // 쪽지보내기 레이블
		MAmsgtxt.setHorizontalAlignment(JLabel.CENTER);
		MAmsgtxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tt == null) {
					JDialog dialog = new ErrorMSGConfirm_okbtnDialog("쪽지보내기", false, "o");
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
		
		//------------------- 당일 예약 현황 -----------------------
		
		
		MAtodayResPane = new JPanel(); // 당일 예약현황
		MAtodayResPane.setBounds(410, 20 ,380, 240);
		MAtodayResPane.setBackground(Color.LIGHT_GRAY);
		MAtodayResPane.setLayout(null);
		
		MAtodayRestxt = new JLabel("오늘 진료 예약"); // 오늘 예약현황 레이블
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
		MATnum = new JLabel(((Integer)rdao.getResDate(year+"-"+mon+"-"+day).size()).toString()); // 연습중인 부분
		}
		
		MATnum.setHorizontalAlignment(JLabel.CENTER);
		MATnum.setBounds(40, 90, 300, 100);
		
		MAtodayResPane.add(MAtodayRestxt);
		MAtodayResPane.add(MATnum);
		
		menulb2(MAtodayRestxt);
		menulb3(MATnum);
		
		//------------------ 총 예약 현황 ------------------------
		MAallResPane = new JPanel(); // 총 예약현황
		MAallResPane.setBounds(800, 20, 380, 240);
		MAallResPane.setBackground(Color.LIGHT_GRAY);
		MAallResPane.setLayout(null);
		
		MAallRestxt = new JLabel("총 진료 예약"); // 총 예약현황
		MAallRestxt.setHorizontalAlignment(JLabel.CENTER);
		MAallRestxt.setBounds(90, 40, 200, 30);
		
		MAAnum = new JLabel(((Integer)rdao.getResListAll().size()).toString());
		
		MAAnum.setHorizontalAlignment(JLabel.CENTER);
		MAAnum.setBounds(40, 90, 300, 100);
		
		MAallResPane.add(MAallRestxt);
		MAallResPane.add(MAAnum);
		
		menulb2(MAallRestxt);
		menulb3(MAAnum);
		
		//------------------- 윗 패널에 올리기-----------------------
		txtPane.add(MAmenuPane);
		txtPane.add(MAtodayResPane);
		txtPane.add(MAallResPane);
		
		txtPane.validate();
		txtPane.repaint();
		
		add(txtPane);
	}
//=================================윗 패널 끝===========================================		
		
// 아래 패널
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

//=================================아래 패널 끝==========================================
	
	private void menulb(JComponent c) {
		
		c.setFont(c.getFont().deriveFont(25.0f)); // 글자 크기
		c.setForeground(Color.black);
	}
	
	private void menulb2(JComponent c) {
		
		c.setFont(c.getFont().deriveFont(18.0f)); // 글자 크기
		c.setForeground(Color.black);
	}
	
	private void menulb3(JComponent c) {
		
		c.setFont(c.getFont().deriveFont(75.0f)); // 글자 크기
		c.setForeground(Color.black);
	}
}
