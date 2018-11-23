package msg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import DB.Msg.Msg_DAO;
import DB.Msg.Msg_DTO;
import thrms.MainFrame;
import java.awt.Font;

public class MsgList extends JFrame	 {
	
	private MsgList msgList = this;

	private String[] header = {"No","제목","보낸이","날짜"};
	
	Calendar cal = Calendar.getInstance(); 
	
	int year = cal.get(Calendar.YEAR); 
	int month = cal.get(Calendar.MONTH)+1; 
	int day = cal.get(Calendar.DATE); 
	
	JTable resTable;
	JScrollPane resTablePane;
	Container root;
	
	protected Color color = new Color(50, 205, 50);	// 테두리 색
	protected LineBorder lb = new LineBorder(color, 5 , false);
	
	Msg_DAO mdao = Msg_DAO.getInstance();
	Msg_DTO mdto = new Msg_DTO();
	
	
	public MsgList() {
		getContentPane().setFont(new Font("나눔고딕", Font.PLAIN, 15));
		
		setBounds(100,100,500,700);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		root = getContentPane();
		root.setLayout(new BorderLayout());
		
		initMsgtable();
		
		setVisible(true);
	}

	public void initMsgtable() {
		ArrayList<Msg_DTO> list = mdao.getMsgUser(MainFrame.id);
		 String[][] contents = null;
		 
		 contents = new String[list.size()][4];
		 
		 for (int i = 0; i < list.size(); i++) {
		        String mon = String.valueOf(month);
		        String d = String.valueOf(day);
		        if(mon.length()<2) {
		           mon = "0" + mon;
		        }
		        if(d.length()<2) {
		           d = "0" + d;
		        }
	
	        mdto = list.get(i);
	        contents[i][0] = mdto.getIdx() + "";
	        contents[i][1] = mdto.getTitle();
	        contents[i][2] = "관리자";
	        contents[i][3] = year+"-"+mon+"-"+d;
	        
	     }
		 
		 DefaultTableModel model = new DefaultTableModel(contents,header){
				public boolean isCellEditable(int row, int column) {return false;}
			};// 테이블 수정금지
		 
		resTable = new JTable(model);
		resTable.setFont(new Font("나눔고딕", Font.PLAIN, 15));
	
		resTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// 단일 선택
		resTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					new MsgCheck(msgList, list.get(resTable.getSelectedRow()));
				}
			}
		});
	
		resTablePane = new JScrollPane(resTable);
	
		resTablePane.setBounds(50,50,770,500);
		resTablePane.setBorder(lb);
		root.add(resTablePane, BorderLayout.CENTER);
 	}


}

