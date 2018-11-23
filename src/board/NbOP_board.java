package board;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import DB.Board.NBoard_DAO;
import DB.Board.NBoard_DTO;
import DB.Board.QBoard_DAO;
import DB.Board.QBoard_DTO;
import thrms.MainFrame;

public class NbOP_board extends JTable implements MouseListener{
	
	ArrayList<NBoard_DTO> list ;
	NBoard_DTO ndto = new NBoard_DTO();
	NBoard_DAO ndao = NBoard_DAO.getInstance();
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
	
	
	public NbOP_board( JPanel insertp) {
		this.insertp = insertp;
		
		setLayout(null);
		setBackground(Color.white);
		setBounds(0,0,1500,732);
		
		
		initNTable() ;
		
	}

	private void initNTable() {

		ArrayList<NBoard_DTO> list = ndao.getNBoardListAll();
		
		contents = new String[list.size()][3];
		contentsin = new String[list.size()];
		ID = new String[list.size()];
		for(int i =0; i < list.size() ; i++) {

			ndto = list.get(i);
			contents[i][0] = ndto.getIdx()+"";
			contents[i][1] = ndto.getTitle();
			contents[i][2] = ndto.getName();
			
			contentsin[i] = ndto.getText();
			
			ID[i]=ndto.getId();
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
		jtb.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
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
				 ArrayList<NBoard_DTO> list = ndao.getNBoardListAll();
				 				 
				 System.out.println(row);
				 
				 Sidx = contents[row][0];
				 title = contents[row][1];
				 in=contentsin[row];
				 
				 Sid = ID[row];
				
				 insertp.removeAll();
				 insertp.add(new Nboard_deepinfo(title , in , basicPane , mainFrame , insertp , Sid ,Sidx));
				 
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
