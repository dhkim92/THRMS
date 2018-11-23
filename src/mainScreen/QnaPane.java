package mainScreen;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import DB.Board.QBoard_DAO;
import DB.Board.QBoard_DTO;

public class QnaPane extends JPanel {
	
	String[][] contents = null;
	String[] contentsin= null;
	ArrayList<QBoard_DTO> list ;
	QBoard_DTO qdto = new QBoard_DTO();
	QBoard_DAO qdao = QBoard_DAO.getInstance();
	
	private JTable jtb;
	private String[] header = {"No","제목","글쓴이"};
	
	private JScrollPane scrollPane;
	
	protected Color clr = new Color(50, 205, 50);	// 테두리 색

	public QnaPane() {
		
		setBounds(0,0,500,180);
		setBackground(clr);
		setLayout(null);

		initQTable();
	}

	private void initQTable() {

		ArrayList<QBoard_DTO> list = qdao.getQBoardListAll();
		
		contents = new String[list.size()][3];
		contentsin = new String[list.size()];
		for(int i =0; i < list.size() ; i++) {

			qdto = list.get(i);
			contents[i][0] = qdto.getIdx()+"";
			contents[i][1] = qdto.getTitle();
			contents[i][2] = qdto.getName();
			
			contentsin[i] = qdto.getText();
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
