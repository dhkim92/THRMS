package mainScreen;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import DB.Board.NBoard_DAO;
import DB.Board.NBoard_DTO;
import board.NbOP_board;
import board.Nboard;
import board.Nboard_deepinfo;
import thrms.MainFrame;
import java.awt.Font;

public class NoticePane extends JPanel{
	
	String[][] contents = null;
	String[] contentsin= null;
	ArrayList<NBoard_DTO> list ;
	NBoard_DTO ndto = new NBoard_DTO();
	NBoard_DAO ndao = NBoard_DAO.getInstance();
	JPanel insertp;
	
	private JTable jtb;
	private String[] header = {"No","제목","글쓴이"};
	
	private JScrollPane scrollPane;
	
	
	protected Color clr = new Color(50, 205, 50);	// 테두리 색
	public void setinsertp(JPanel insertp) {
		this.insertp = insertp;
	}
	
	public NoticePane() {
		
		setBounds(0,0,500,180);
		setBackground(clr);
		setLayout(null);

		
		initNTable();
	}
	
	
	private void initNTable() {

		ArrayList<NBoard_DTO> list = ndao.getNBoardListAll();
		
		contents = new String[list.size()][3];
		contentsin = new String[list.size()];
		for(int i =0; i < list.size() ; i++) {

			ndto = list.get(i);
			contents[i][0] = ndto.getIdx()+"";
			contents[i][1] = ndto.getTitle();
			contents[i][2] = ndto.getName();
			
			contentsin[i] = ndto.getText();
		}
		DefaultTableModel model = new DefaultTableModel(contents, header){
			public boolean isCellEditable(int row, int column) {return false;}
		};
		
		jtb = new JTable(model);
		jtb.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		
		jtb.setBounds(0,0,490,135);
		jtb.setBackground(Color.white);
		scrollPane = new JScrollPane(jtb);
		scrollPane.setBounds(5,40,490,135);
		scrollPane.setBackground(Color.white);
		jtb.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		
		add(scrollPane);
	}

}
