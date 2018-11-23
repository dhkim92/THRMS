package myPageAdmin;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import DB.Res.Res_DTO;

public class ResAll extends JFrame implements MouseListener{

	private static final long serialVersionUID = 1L;
	JPanel restxtPane; // 예약레이블 패널
	JScrollPane resTablePane; // 예약테이블 패널
	Container root;
	
	JLabel restxt; // 예약레이블
	JTable resTable; // 예약테이블
	
	JButton Upbtn; //수정 버튼
	JButton Delbtn; // 삭제 버튼
	
	private String[] header = {"No","성명","진료과목","담당의","예약 날짜","예약 시간","증상","진료 여부"};
	
	protected Color color = new Color(29, 219, 22);
	protected LineBorder lb = new LineBorder(color, 5 , false); // 테두리 커스텀
	
	
	Res_DTO rdto = new Res_DTO();
	private ArrayList<Res_DTO> list;
	
	
	public ResAll(ArrayList<Res_DTO> list) {
		super("THRMS - 예약 정보");
		this.list = list;
		
		setBounds(700,200,900,650);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		root = getContentPane();
		root.setLayout(null);

		
		initrestxt();
		initresTable();
		
		setVisible(true);
	}
	
	
	private void initresTable() {
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
     	}; // 테이블 수정 금지
     	
		resTable = new JTable(model);
		
		resTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 단일선택
		resTable.addMouseListener(this);
		
		resTablePane = new JScrollPane(resTable);
		resTablePane.setBounds(50,50,770,500);
		resTablePane.setBorder(lb);
		root.add(resTablePane);
	}


	private void initrestxt() {
		
		restxtPane = new JPanel();
		restxtPane.setBounds(50,20,220,30);
		restxtPane.setBackground(color);
		
		restxt = new JLabel( "  예약 정보");
		setFonttxt(restxt);
		
		restxtPane.add(restxt);
		root.add(restxtPane);
	}


	private void setFonttxt(JComponent c) {
		
		c.setFont(c.getFont().deriveFont(15.0f)); // 글자 크기
		c.setForeground(Color.white);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == resTable ) {
			int row = resTable.getSelectedRow();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
