package test;

import java.awt.Event;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


import javax.swing.JOptionPane;

class Ticket extends Frame{
	Vector<String> lst1 = new Vector<String>(); 
	Vector<String> lst2 = new Vector<String>(); 
	Connection con = makeConnection();
	ResultSet rs = null;
	PreparedStatement psmt = null;
	String sql = null;
	JButton check = setButton("예매\n하기",375,150,85,50);
	JButton back = setButton("뒤로\n가기",375,200,85,50);
	JComboBox jBox1 = null;
	JComboBox jBox2 = new JComboBox();
	String name;
	String date;
	JLabel lbl1= null;
	JLabel lbl2=null;
	
	
	Ticket(String lbl) throws SQLException{
		super(lbl);

		exit.setBounds(375, 300, 85, 40);
		comboBox_init();
		lbl1 = setLabel("영화 제목" , 50,150,150,30);
		lbl2 = setLabel("예매 시간", 200,150,150,30);
		lbl1.setFont(getFont().deriveFont(30.0f));
		lbl2.setFont(getFont().deriveFont(30.0f));
		getContentPane().add(lbl1);
		getContentPane().add(lbl2);
		add(lbl1);
		add(lbl2);
		// 예매하기 버튼 이벤트
		check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				name = jBox1.getSelectedItem().toString();
				date = jBox2.getSelectedItem().toString();
				int result = JOptionPane.showConfirmDialog(null, date + "\n"+ name,"예매 확인",JOptionPane.YES_NO_OPTION);
		        if (result == 0) {
		        	new Ticket_m("예매 확인");
		        	setVisible(false);
		        }
			}
		});
		
		// box1 설정 이벤트
		jBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lst2.clear();
				jBox2.removeAllItems();
				try {
		    		name= jBox1.getSelectedItem().toString();
		        	sql = "select * from movie where M_NAME='" + name +"'";
		        	psmt = con.prepareStatement(sql);	
		        	rs = psmt.executeQuery();
		    		
		    		String date= null;
		    		while(rs.next()) {
		    			date = rs.getString("TIME").substring(5,7) +"월 "+
		    				   rs.getString("TIME").substring(8,10) + "일 "+
		    				   rs.getString("TIME").substring(11,13) + "시 "+
		    				   rs.getString("TIME").substring(14,16) + "분";
		    			jBox2.addItem(date);
		    		}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void comboBox_init() throws SQLException {
		sql = "Select * from movie_Table";
		psmt = con.prepareStatement(sql);
		rs = psmt.executeQuery();
		
		while(rs.next()) {
			lst1.add(rs.getString("M_NAME"));
		}
		jBox1 = new JComboBox(lst1);
		jBox1.setBounds(50,200,150,30);
		jBox1.setEditable(true);
		jBox2.setEditable(true);
		jBox2.setBounds(200,200,150,30);
		this.add(jBox1);
		this.add(jBox2);
		
		psmt.close();
		rs.close();
	}
}
	
