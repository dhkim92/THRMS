package myPageAdmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DB.Res.Res_DAO;
import DB.Res.Res_DTO;

public class AllResCalendar extends JPanel {
	
	private static final long serialVersionUID = 1L;
	JPanel caltxtPane; // 예약현황 레이블이 들어갈 패널
	 JPanel calPane;
	 JPanel ARcalPane; // 달력 패널
	 JPanel ARcalPane2;
	 protected Color color = new Color(29, 219, 22);
	 
	 int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
	 int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);;
	 JLabel l = new JLabel("", JLabel.CENTER);
	 String day = "";
	 String date="";
	 JButton[] button = new JButton[49];
	 
	 JLabel caltxt; // 예약현황 레이블
	 
	 Res_DAO rdao = Res_DAO.getInstance();
	 Res_DTO rdto = new Res_DTO();
	 
	 
	 public AllResCalendar() {
		 setBounds(6,3,1488,726);
		 setBackground(Color.white);
		 setLayout(null);
		 
		 caltxtPane = new JPanel();
		 caltxtPane.setBackground(color);
		 caltxt = new JLabel("예약 현황 확인");
		 dayFont(caltxt);
		 caltxt.setForeground(Color.WHITE);
		 caltxtPane.setBounds(250, 60, 200, 40);
		 
		 caltxtPane.add(caltxt);
		 
		 calPane = new JPanel();
		 calPane.setBounds(250,100,1000,560);
		 calPane.setLayout(new BorderLayout());
		 calPane.setBackground(Color.white);
		 
	       String[] header = { "일", "월", "화", "수", "목", "금", "토" };
	       JPanel ARcalPane = new JPanel(new GridLayout(7, 7));

	       for (int x = 0; x < button.length; x++) {
	               final int selection = x;
	               button[x] = new JButton();
	               button[x].setFocusPainted(false);
	               button[x].setBackground(Color.white);
	               if (x > 6)
	                       button[x].addActionListener(new ActionListener() {
	                               public void actionPerformed(ActionEvent ae) {
	                                      day = button[selection].getActionCommand();
	                                      String mon = String.valueOf(month + 1);
	                                      if(mon.length()<2) {
	                                         mon = "0" + mon;
	                                      }
	                                      if(day.length()<2) {
	                                         day = "0" + day;
	                                      }
	                                      date = year + "-" + mon + "-" + day;
	                                      
	                                 
	                                      ArrayList<Res_DTO> list = rdao.getResDate(date);
	                                      if(list!= null) {     
	                                    	  new ResAll(list);
	                                    	  
	                                      }
	                               }
	                       });
	               			dayFont(button[x]);
	               if (x < 7) {
	                       button[x].setText(header[x]);
	                       headerFont(button[x]);
	                       button[0].setForeground(Color.red);
	               }
	               if (x == 6)
	            	   button[6].setForeground(Color.blue);
	               
	               ARcalPane.add(button[x]);
	               
	       }
	       JPanel ARcalPane2 = new JPanel(new GridLayout(1, 3));
	       JButton previous = new JButton("<< Previous");
	       previous.addActionListener(new ActionListener() {
	               public void actionPerformed(ActionEvent ae) {
	                       month--;
	                       displayDate();
	               }
	       });
	       ARcalPane2.add(previous);
	       ARcalPane2.add(l);
	       JButton next = new JButton("Next >>");
	       next.addActionListener(new ActionListener() {
	               public void actionPerformed(ActionEvent ae) {
	                       month++;
	                       displayDate();
	               }
	       });
	       ARcalPane2.add(next);
	       
	       calPane.add(ARcalPane, BorderLayout.CENTER);
	       calPane.add(ARcalPane2, BorderLayout.SOUTH);
	       add(caltxtPane);
	       add(calPane);
	       displayDate();
	       
	 }

	 
	 public void displayDate() {
	       for (int x = 7; x < button.length; x++)
	               button[x].setText("");
	       java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
	                       "MMMM yyyy");
	       java.util.Calendar cal = java.util.Calendar.getInstance();
	       cal.set(year, month, 1);
	       int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
	       int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
	       for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++)
	               button[x].setText("" + day);
	       l.setText(sdf.format(cal.getTime()));
	 }

	 public String setPickedDate() {
	       if (day.equals(""))
	               return day;
	       java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
	                       "dd-MM-yyyy");
	       java.util.Calendar cal = java.util.Calendar.getInstance();
	       cal.set(year, month, Integer.parseInt(day));
	       return sdf.format(cal.getTime());
	 	}
	 
	 	private void dayFont(JComponent c) {
			
			c.setFont(c.getFont().deriveFont(20.0f)); // 글자 크기
			c.setForeground(Color.black);
			
		}
	 	private void headerFont(JComponent c) {
			
			c.setFont(c.getFont().deriveFont(23.0f)); // 글자 크기
			c.setForeground(Color.black);
			
		}
	 }

