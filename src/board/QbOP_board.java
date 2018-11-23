package board;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DB.Board.NBoard_DTO;
import DB.Board.QBoard_DAO;
import DB.Board.QBoard_DTO;
import thrms.MainFrame;
import java.awt.Font;

public class QbOP_board extends JTable implements MouseListener{

	ArrayList<QBoard_DTO> list ;
	QBoard_DTO qdto = new QBoard_DTO();
	QBoard_DAO qdao = QBoard_DAO.getInstance();
	JPanel basicPane ;
	MainFrame mainFrame;
	JPanel insertp;
	
	protected Color clr = new Color(50, 205, 50);	// Å×µÎ¸® »ö
	private JTable jtb;
	String[][] contents = null;
	String[] contentsin= null;
	String[] ID=null;

	private String[] header = {"No","Á¦¸ñ","±Û¾´ÀÌ"};

	private JScrollPane scrollPane;
	
	public void getinsertp(JPanel insertp) {
		this.insertp = insertp;
	}
	public QbOP_board(JPanel insertp ) {
		this.basicPane = basicPane;
		this.mainFrame = mainFrame;
		this.insertp = insertp;
		
		setLayout(null);
		setBackground(Color.white);
		setBounds(0,0,1500,732);


		initNTable() ;

	}

	private void initNTable() {
				
		ArrayList<QBoard_DTO> list = qdao.getQBoardListAll();
		
		contents = new String[list.size()][3];
		contentsin = new String[list.size()];
		ID = new String[list.size()];
		
		for(int i =0; i < list.size() ; i++) {

			qdto = list.get(i);
			contents[i][0] = qdto.getIdx()+"";
			contents[i][1] = qdto.getTitle();
			contents[i][2] = qdto.getName();
			
			contentsin[i] = qdto.getText();
			
			ID[i]=qdto.getId();
				
				
			
		}
		DefaultTableModel model = new DefaultTableModel(contents, header){
			public boolean isCellEditable(int row, int column) {return false;}
		};
		
		jtb = new JTable(model);
		jtb.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 15));
		
		jtb.setBounds(0,0,1500,732);
		jtb.setBackground(Color.white);
		scrollPane = new JScrollPane(jtb);
		scrollPane.setBounds(0,0,1500,732);
		scrollPane.setBackground(Color.white);
		jtb.addMouseListener(this);
		add(scrollPane);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()== jtb) {
//			System.out.println("Å¬¸¯µÊ");
			 if(e.getClickCount()==2) {
//				 System.out.println("´õºíÅ¬¸¯µÊ");
				 String in;
				 String title;
				 String Sid;
				 String Sidx;
				 int row = jtb.getSelectedRow();
				 ArrayList<QBoard_DTO> list = qdao.getQBoardListAll();
				 				 
				 System.out.println(row);
				 Sidx = contents[row][0];
				 title = contents[row][1];
				 in=contentsin[row];
				 Sid=ID[row];
				 
			
				 insertp.removeAll();
				 insertp.add(new Qboard_deepinfo(title , in , basicPane , mainFrame , insertp , Sid , Sidx));
				 System.out.println("ddddd");
				 insertp.validate();
				 insertp.repaint();
				 
				 
			 }
			
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
