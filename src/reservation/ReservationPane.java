// 예약하기 화면

package reservation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import DB.Doc.Doc_DAO;
import DB.Doc.Doc_DTO;
import DB.Res.Res_DAO;
import DB.Res.Res_DTO;
import DB.User.User_DAO;
import DB.User.User_DTO;
import thrms.MainFrame;

public class ReservationPane extends JPanel implements ActionListener, MouseListener {
	
	private JPanel resPane, selectPane, departmentsPane, doctorPane, // 선택+텍스트 영역, 선택 영역, 진료과목 영역, 의사 영역
					datePane, tmpCalPane, tmpCalPane2, textPane; // 날짜/시간 영역, 달력 영역, 달력상단부 영역, 확인용 텍스트 영역
	private ResCalendar calPane;	// 달력
	private JScrollPane departmentsScroll, doctorScroll1, doctorScroll2, doctorScroll3, timeScroll;	// 진료과목 리스트 스크롤, 담당의 리스트 스크롤
	private JList<String> departmentsList, doctorList1, doctorList2, doctorList3; 	// 진료과목 리스트, 담당의 리스트, doctorList : n개
	private String[] departments, doctor1, doctor2, doctor3, availableTimes;		// 진료과목 리스트 내용, 담당의 리스트 내용, doctor : n개
	private JComboBox<Object> timecmb;	// 예약시간 콤보박스
	private JLabel[] textlbl;		// 레이블 배열
	private JLabel textNum;
	private JTextArea symptomstxt;	// 증상 입력 텍스트에리아
	private JButton completebtn;	// 완료 버튼
	private String selectedDList;
	
	private Map<String, ImageIcon> docImageMap;	// 이미지 리스트 맵
	
	User_DAO user_dao = User_DAO.getInstance();
	Res_DAO res_dao = Res_DAO.getInstance();
	Doc_DAO doc_dao = new Doc_DAO();
	Res_DTO rdto;
	
	
	public ReservationPane() {	// 크기, 위치, 레이아웃
		setBounds(0,0,1500,755);	// 크기, 위치 설정
		setBackground(Color.WHITE);
		
		Color color = new Color(50, 205, 50);	// 테두리 색
		LineBorder lb = new LineBorder(color, 5 , false); // 테두리 커스텀
		setBorder(lb);	// 테두리 설정
		
		setLayout(null);
		
		initPane();
	}

	private void initPane() {	// 판넬 추가, 배치, 컴포넌트 생성, 추가
		JPanel btnPane = new JPanel();
		btnPane.setLocation(5, 681);
		btnPane.setSize(new Dimension(1490, 41));
		btnPane.setLayout(null);
		btnPane.setBackground(Color.WHITE);
		
		completebtn = new JButton("예약완료");
		completebtn.setFont(new Font("나눔고딕", Font.BOLD, 15));
		completebtn.setLocation(680, 10);
		completebtn.setSize(166, 31);
		completebtn.addActionListener(this);
		
		btnPane.add(completebtn);
		
		initResPane();
		
		add(resPane);
		add(btnPane);
	}
	
	private void initResPane() {	// 선택+텍스트 영역
		resPane = new JPanel();
		resPane.setLocation(5, 5);
		resPane.setSize(1490, 681);
		resPane.setLayout(null);
		resPane.setBackground(Color.WHITE);
		
		initSelectPane();
		initTextPane();
		
		resPane.add(selectPane);
		resPane.add(textPane);
	}
	
	private void initSelectPane() {	// 선택 영역
		selectPane = new JPanel();
		selectPane.setLayout(new GridLayout(0, 3));
		selectPane.setBounds(0, 20, 1500, 334);
		selectPane.setBackground(Color.WHITE);
		
		initDepartmentsPane();
		initDoctorPane();
		initDatePane();
		
		selectPane.add(departmentsPane);
		selectPane.add(doctorPane);
		selectPane.add(datePane);
	}
	
	private void initDepartmentsPane() {	// 진료과목 영역
		departmentsPane = new JPanel();
		departmentsPane.setBackground(Color.WHITE);
		
		departments = new String[3];
		
		ArrayList<Doc_DTO> list = doc_dao.getDocListAll();
		
		for(Doc_DTO dto : list) {
			if(departments[0] == null) {
				departments[0] = dto.getPart();			
			} else if (departments[0] != null) {
				if(!dto.getPart().equals(departments[0]) && departments[1] == null) {
					departments[1] = dto.getPart();
				}
				if(!dto.getPart().equals(departments[0]) && !dto.getPart().equals(departments[1])) {
					departments[2] = dto.getPart();
				}
			}
			if(departments[2] != null) {
				break;
			}
		}
		
		
		departmentsList = new JList<>(departments);
		departmentsList.setFont(new Font("나눔고딕", Font.BOLD, 20));
		departmentsList.addMouseListener(this);
		
		departmentsScroll = new JScrollPane(departmentsList);
		departmentsScroll.setPreferredSize(new Dimension(300, 300));
		
		departmentsPane.add(departmentsScroll);
	}
	
	private void initDoctorPane() {	// 의사 영역
		doctorPane = new JPanel();
		doctorPane.setBackground(Color.WHITE);
		
		doctor1 = new String[5];
		doctor2 = new String[4];
		doctor3 = new String[5];
		
		ArrayList<Doc_DTO> list = doc_dao.getDocListAll();
		
		for(Doc_DTO dto : list) {
			if(dto.getPart().equals(departments[0])) {
				if(doctor1[0] == null)	doctor1[0] = dto.getDname();
				else if(doctor1[1] == null)	doctor1[1] = dto.getDname();
				else if(doctor1[2] == null)	doctor1[2] = dto.getDname();
				else if(doctor1[3] == null)	doctor1[3] = dto.getDname();
				else if(doctor1[4] == null)	doctor1[4] = dto.getDname();
			}
			if(dto.getPart().equals(departments[1])) {
				if(doctor2[0] == null)	doctor2[0] = dto.getDname();
				else if(doctor2[1] == null)	doctor2[1] = dto.getDname();
				else if(doctor2[2] == null)	doctor2[2] = dto.getDname();
				else if(doctor2[3] == null)	doctor2[3] = dto.getDname();
			}
			if(dto.getPart().equals(departments[2])) {
				if(doctor3[0] == null)	doctor3[0] = dto.getDname();
				else if(doctor3[1] == null)	doctor3[1] = dto.getDname();
				else if(doctor3[2] == null)	doctor3[2] = dto.getDname();
				else if(doctor3[3] == null)	doctor3[3] = dto.getDname();
				else if(doctor3[4] == null)	doctor3[4] = dto.getDname();
			}
		}
		
		doctorList1 = new JList<>(doctor1);
		doctorList1.setCellRenderer(new DoctorListRenderer());
		
		doctorList2 = new JList<>(doctor2);
		doctorList2.setCellRenderer(new DoctorListRenderer());
		
		doctorList3 = new JList<>(doctor3);
		doctorList3.setCellRenderer(new DoctorListRenderer());
		
		doctorList1.setFont(new Font("나눔고딕", Font.BOLD, 20));
		doctorList2.setFont(new Font("나눔고딕", Font.BOLD, 20));
		doctorList3.setFont(new Font("나눔고딕", Font.BOLD, 20));
		
		doctorList1.addMouseListener(this);
		doctorList2.addMouseListener(this);
		doctorList3.addMouseListener(this);
		
		doctorScroll1 = new JScrollPane(doctorList1);
		doctorScroll1.setPreferredSize(new Dimension(300, 300));
		doctorScroll2 = new JScrollPane(doctorList2);
		doctorScroll2.setPreferredSize(new Dimension(300, 300));
		doctorScroll3 = new JScrollPane(doctorList3);
		doctorScroll3.setPreferredSize(new Dimension(300, 300));
		
		JPanel tmpPane = new JPanel();
		tmpPane.setLayout(new BorderLayout());
		tmpPane.setPreferredSize(new Dimension(300, 300));
		tmpPane.setBackground(Color.WHITE);
		tmpPane.setBorder(new LineBorder(Color.gray, 1));
		
		JLabel tmplbl = new JLabel("진료과목을 선택해주세요.");
		tmplbl.setFont(new Font("나눔고딕", Font.PLAIN, 13));
		tmplbl.setHorizontalAlignment(JLabel.CENTER);
		tmpPane.add(tmplbl, "Center");
		
		doctorPane.add(tmpPane);
	}
	
	// 사진 + 텍스트 리스트 만들기
	public class DoctorListRenderer extends DefaultListCellRenderer {
		
		Font font = new Font("나눔고딕", Font.BOLD, 25);
		
		@Override
		public Component getListCellRendererComponent(
				JList list, Object value, int index,
				boolean isSelected, boolean cellHasFocus) {
			
			JLabel label = (JLabel) super.getListCellRendererComponent(
					list, value, index, isSelected, cellHasFocus);
			label.setIcon(docImageMap.get((String) value));
			label.setHorizontalTextPosition(JLabel.RIGHT);
			label.setFont(font);
			return label;
		}
	}
	
	private Map<String, ImageIcon> createImageMap(String[] list) {
		Map<String, ImageIcon> map = new HashMap<>();
		for (String s : list) {
				map.put(s, new ImageIcon(".\\src\\image\\" + s + ".png"));
		}
		return map;
	}
	
	private void initDatePane() {	// 날짜/시간 영역
		datePane = new JPanel();
		datePane.setLayout(new BorderLayout(25, 0));
		datePane.setBackground(Color.WHITE);
		
		JPanel dateLayoutPane = new JPanel();
		dateLayoutPane.setLayout(new BorderLayout());
		dateLayoutPane.setBackground(Color.WHITE);
		
		initCalPane();
		
		JPanel timePane = new JPanel();
		timePane.setLayout(new FlowLayout());
		timePane.setBackground(Color.WHITE);
		
		String[] Times = {"9:00", "9:30", "10:00", "10:30", "11:00", "11:30", 
						"12:00", "12:30", "13:00", "13:30", "14:00", "14:30",
						"15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00"};
		
		availableTimes = Times;
		
		timecmb = new JComboBox<>(availableTimes);
		timecmb.setFont(new Font("나눔고딕", Font.BOLD, 18));
		timecmb.setPreferredSize(new Dimension(400, 40));
		timecmb.setEnabled(false);	// 처음엔 비활성화
		timecmb.setBackground(Color.WHITE);
		timecmb.addActionListener(this);

		timeScroll = new JScrollPane(timecmb);
		
		timePane.add(timeScroll);
		
		dateLayoutPane.add(tmpCalPane, "Center");
		dateLayoutPane.add(timePane, "South");

		JPanel tmpPane1 = new JPanel();
		JPanel tmpPane2 = new JPanel();
		JPanel tmpPane3 = new JPanel();
		
		tmpPane1.setBackground(Color.WHITE);
		tmpPane2.setBackground(Color.WHITE);
		tmpPane3.setBackground(Color.WHITE);
		
		datePane.add(tmpPane1, "East");
		datePane.add(tmpPane2, "West");
		datePane.add(dateLayoutPane, "Center");
		datePane.add(tmpPane3, "South");
	}
	
	private void initCalPane() {	// 달력 영역
		tmpCalPane = new JPanel();
		tmpCalPane.setBackground(Color.WHITE);
		calPane = new ResCalendar(this);
		calPane.setBackground(Color.WHITE);
		 
		tmpCalPane2 = new JPanel();
		tmpCalPane2.setLayout(new BorderLayout());
		tmpCalPane2.setPreferredSize(new Dimension(400, 300));
		tmpCalPane2.setBackground(Color.WHITE);
		tmpCalPane2.setBorder(new LineBorder(Color.gray, 1));
		
		JLabel tmplbl = new JLabel("담당의를 선택해주세요.");
		tmplbl.setFont(new Font("나눔고딕", Font.PLAIN, 13));
		tmplbl.setHorizontalAlignment(JLabel.CENTER);
		tmpCalPane2.add(tmplbl, "Center");
		
		tmpCalPane.add(tmpCalPane2);
	}
	
	private void initTextPane() {	// 확인용 텍스트 영역
		textPane = new JPanel();
		textPane.setSize(1490, 334);
		textPane.setLocation(0, 345);
		textPane.setLayout(null);
		textPane.setBackground(Color.WHITE);
		
		Font font = new Font("나눔고딕", Font.BOLD, 18);
		
		textlbl = new JLabel[15];
		
		for(int i=0; i<textlbl.length; i++) {
			textlbl[i] = new JLabel("");
			textlbl[i].setFont(font);
			
			if(i%2 == 0) {
				textlbl[i].setHorizontalAlignment(SwingConstants.RIGHT);
			}
		}
		
		textlbl[0].setText("이름     ");
		textlbl[1].setText("이름 자동입력");
		textlbl[2].setText("생년월일     ");
		textlbl[3].setText("생년월일 자동입력");
		textlbl[4].setText("연락처     ");
		textlbl[5].setText("연락처 자동입력");
		textlbl[6].setText("진료과목     ");
		textlbl[7].setText("과목 자동입력");
		textlbl[8].setText("담당의     ");
		textlbl[9].setText("담당의 자동입력");
		textlbl[10].setText("예약날짜     ");
		textlbl[11].setText("날짜 자동입력");
		textlbl[12].setText("예약시간     ");
		textlbl[13].setText("시간 자동입력");
		textlbl[14].setText("증상     ");
		
		textlbl[0].setBounds(418, 10, 150, 25);
		textlbl[1].setBounds(562, 10, 150, 25);
		textlbl[2].setBounds(418, 70, 150, 25);
		textlbl[3].setBounds(562, 70, 150, 25);
		textlbl[4].setBounds(418, 133, 150, 25);
		textlbl[5].setBounds(562, 133, 150, 25);
		textlbl[6].setBounds(787, 10, 150, 25);
		textlbl[7].setBounds(950, 10, 150, 25);
		textlbl[8].setBounds(787, 70, 150, 25);
		textlbl[9].setBounds(950, 70, 150, 25);
		textlbl[10].setBounds(787, 133, 150, 25);
		textlbl[11].setBounds(950, 133, 150, 25);
		textlbl[12].setBounds(787, 196, 150, 25);
		textlbl[13].setBounds(950, 196, 150, 25);
		textlbl[14].setBounds(787, 250, 150, 25);
		
		textNum = new JLabel("(0 / 300)");
		textNum.setFont(new Font("나눔고딕", Font.PLAIN, 13));
		textNum.setBounds(1270, 295, 100, 15);
		
		symptomstxt = new JTextArea();
		symptomstxt.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		symptomstxt.setLineWrap(true);	// 자동 줄바꿈
		symptomstxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(symptomstxt.getText().equals("")) {
					textNum.setText("(0 / 300)");
				}
				textNum.setText("(" + symptomstxt.getText().length() + " / 300)");
				
				if(symptomstxt.getText().length() >= 300) {
					// 옵션다이얼로그
					JOptionPane.showMessageDialog(null, "글자 수가 초과되었습니다.");
				}
			}
		});
		
		JScrollPane symptomsScroll = new JScrollPane(symptomstxt);
		symptomsScroll.setLocation(940, 250);
		symptomsScroll.setSize(300, 70);
		symptomsScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		symptomsScroll.setPreferredSize(new Dimension(500, 200));
		
		for(int i=0; i<textlbl.length; i++) {
			textPane.add(textlbl[i]);
		}
		
		textPane.add(symptomsScroll);
		textPane.add(textNum);		
	}
	
	public void setUserInfo() {
		ArrayList<User_DTO> list = user_dao.getUserListAll();
		
		for(User_DTO dto : list) {
			if(dto.getId().equals(MainFrame.id)) {
				textlbl[1].setText(dto.getName());
				textlbl[3].setText(dto.getBirthDate());
				textlbl[5].setText(dto.getPhone());
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {	// 리스너
		if(e.getSource() == completebtn) {
			String symptoms = symptomstxt.getText();
			
			if(textlbl[7].getText().equals("과목 자동입력") || textlbl[9].getText().equals("담당의 자동입력")
					|| textlbl[11].getText().equals("날짜 자동입력") || textlbl[13].getText().equals("시간 자동입력")
					|| symptoms.equals("")) {
				
				JDialog dialog = new ErrorRes_completebtnDialog("오류 다이얼로그", true, "");
				
				dialog.setVisible(true);
			} else {
				
				rdto = new Res_DTO();
				
				rdto.setId(MainFrame.id);
				rdto.setPart(textlbl[7].getText());
				rdto.setDname(textlbl[9].getText());
				rdto.setResDate(textlbl[11].getText());
				rdto.setResTime(textlbl[13].getText());
				rdto.setSymptoms(symptoms);
				rdto.setStatus("진료 전");
				
				int idx = res_dao.insertRes(rdto);
				
				if(idx > 0) {
					MainFrame.basicPane.removeAll();
					
					ResCompletePane resComPane = new ResCompletePane(idx, textlbl, symptoms);
					MainFrame.basicPane.add(resComPane);
					
					MainFrame.basicPane.validate();
					MainFrame.basicPane.repaint();
				}
			}
		}
		// 콤보박스 선택하면 시간 자동입력
		if(e.getSource() == timecmb) {
			textlbl[13].setText((String)timecmb.getSelectedItem());
			
			textPane.validate();
			textPane.repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// 진료 과목 선택 시
		if(e.getSource() == departmentsList) {
			doctorPane.removeAll();
			
			tmpCalPane.removeAll();
			tmpCalPane.add(tmpCalPane2);
			
			timecmb.setEnabled(false);
			timecmb.setSelectedItem(0);
			
			textlbl[7].setText(departmentsList.getSelectedValue());
			textlbl[9].setText("담당의 자동입력");
			textlbl[11].setText("날짜 자동입력");
			textlbl[13].setText("시간 자동입력");
			
			if(departmentsList.getSelectedValue().equals("호흡기 내과")) {
				docImageMap = createImageMap(doctor1);
				doctorPane.add(doctorScroll1);
			} else if(departmentsList.getSelectedValue().equals("소화기 내과")) {
				docImageMap = createImageMap(doctor2);
				doctorPane.add(doctorScroll2);
			} else if(departmentsList.getSelectedValue().equals("내분비대사 내과")) {
				docImageMap = createImageMap(doctor3);
				doctorPane.add(doctorScroll3);
			}
			
			doctorPane.validate();
			doctorPane.repaint();
			tmpCalPane.validate();
			tmpCalPane.repaint();
			textPane.validate();
			textPane.repaint();
		}
		
		// 의사 선택 시
		if(e.getSource() == doctorList1 || e.getSource() == doctorList2 || e.getSource() == doctorList3) {
			
			tmpCalPane.removeAll();
			tmpCalPane.add(calPane);
			
			timecmb.setEnabled(false);
			timecmb.setSelectedItem(0);
			
			textlbl[11].setText("날짜 자동입력");
			textlbl[13].setText("시간 자동입력");

			if (e.getSource() == doctorList1) {
				textlbl[9].setText(doctorList1.getSelectedValue());
				selectedDList = "doctorList1";
			} else if (e.getSource() == doctorList2) {
				textlbl[9].setText(doctorList2.getSelectedValue());
				selectedDList = "doctorList2";
			} else if (e.getSource() == doctorList3) {
				textlbl[9].setText(doctorList3.getSelectedValue());
				selectedDList = "doctorList3";
			}
			
			tmpCalPane.validate();
			tmpCalPane.repaint();
			textPane.validate();
			textPane.repaint();
		}
	}

	public void getCalDate(String date) {		
		
		textlbl[11].setText(date);
		
		timecmb.removeAllItems();
		
		for(int i=0; i<availableTimes.length; i++) {
			timecmb.addItem(availableTimes[i]);
		}
		
		ArrayList<Res_DTO> list = res_dao.getResListAll();
		
		for(Res_DTO dto : list) {
			String time = "";
			if(selectedDList.equals("doctorList1")) {
				if(dto.getPart().equals(departmentsList.getSelectedValue()) 
						&& dto.getDname().equals(doctorList1.getSelectedValue())
						&& dto.getResDate().equals(date)) {
					time = dto.getResTime();
				}				
			} else if(selectedDList.equals("doctorList2")) {
				if(dto.getPart().equals(departmentsList.getSelectedValue()) 
						&& dto.getDname().equals(doctorList2.getSelectedValue())
						&& dto.getResDate().equals(date)) {
					time = dto.getResTime();
				}
			} else if(selectedDList.equals("doctorList3")) {
				if(dto.getPart().equals(departmentsList.getSelectedValue()) 
						&& dto.getDname().equals(doctorList3.getSelectedValue())
						&& dto.getResDate().equals(date)) {
					time = dto.getResTime();
				}
			}
			
			if(time.equals("9:00"))				timecmb.removeItem("9:00");
			else if(time.equals("9:30"))		timecmb.removeItem("9:30");
			else if(time.equals("10:00"))		timecmb.removeItem("10:00");
			else if(time.equals("10:30"))		timecmb.removeItem("10:30");
			else if(time.equals("11:00"))		timecmb.removeItem("11:00");
			else if(time.equals("11:30"))		timecmb.removeItem("11:30");
			else if(time.equals("12:00"))		timecmb.removeItem("12:00");
			else if(time.equals("12:30"))		timecmb.removeItem("12:30");
			else if(time.equals("13:00"))		timecmb.removeItem("13:00");
			else if(time.equals("13:30"))		timecmb.removeItem("13:30");
			else if(time.equals("14:00"))		timecmb.removeItem("14:00");
			else if(time.equals("14:30"))		timecmb.removeItem("14:30");
			else if(time.equals("15:00"))		timecmb.removeItem("15:00");
			else if(time.equals("15:30"))		timecmb.removeItem("15:30");
			else if(time.equals("16:00"))		timecmb.removeItem("16:00");
			else if(time.equals("16:30"))		timecmb.removeItem("16:30");
			else if(time.equals("17:00"))		timecmb.removeItem("17:00");
			else if(time.equals("17:30"))		timecmb.removeItem("17:30");
			else if(time.equals("18:00"))		timecmb.removeItem("18:00");
			
		}
		
		timecmb.setEnabled(true);
		
		textlbl[13].setText("시간 자동입력");
		
		selectPane.validate();
		selectPane.repaint();
		textPane.validate();
		textPane.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}