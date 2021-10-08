package movie;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.text.SimpleDateFormat;
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
	Connection con;
	String id;
	ResultSet rs = null;
	PreparedStatement psmt = null;
	String sql = null;
	JButton check = setButton("예약확인",375,150,85,50);
	JButton back = setButton("메뉴보기",375,200,85,50);
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM월 dd일 HH시 mm분");
	JComboBox jBox1 = null;
	JComboBox jBox2 = new JComboBox();
	String name="";
	String date="";
	int M_id;
	Timestamp da;
	JLabel lbl1= null;
	JLabel lbl2=null;
	
	
	Ticket(Connection con, String lbl, String id){
		super(con,lbl);
		this.con = con;
		this.id = id;
		
		exit.setBounds(375, 300, 85, 40);
		sql = "Select * from movie_Table";
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				lst1.add(rs.getString("M_NAME"));
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		jBox1 = new JComboBox(lst1);
		jBox1.setBounds(50,200,150,30);
		jBox1.setEditable(true);
		jBox2.setEditable(true);
		jBox2.setBounds(200,200,150,30);
		this.add(jBox1);
		this.add(jBox2);
		lbl1 = setLabel("영화 제목" , 50,150,150,30);
		lbl2 = setLabel("예매 시간", 200,150,150,30);
		lbl1.setFont(getFont().deriveFont(30.0f));
		lbl2.setFont(getFont().deriveFont(30.0f));
		getContentPane().add(lbl1);
		getContentPane().add(lbl2);
		
		// 예매하기 버튼 이벤트
		check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (date.isBlank()) {
					JOptionPane.showMessageDialog(null, "시간을 선택해주세요.");
				}else {
				try {
					name = jBox1.getSelectedItem().toString();
					date = jBox2.getSelectedItem().toString();
		        	sql = "select * from movie where M_NAME='" + name +"'";
		        	psmt = con.prepareStatement(sql);	
		        	rs = psmt.executeQuery();
		    		while(rs.next()) {
		    			if(date.equals(simpleDateFormat.format(rs.getTimestamp("M_TIME")))) {
		    				M_id = rs.getInt("M_ID");
		    				break;
		    			}
		    		}
		    		
		    		boolean bool =true;
		    		sql = "select * from ticket";
		        	psmt = con.prepareStatement(sql);	
		        	rs = psmt.executeQuery();
		    		while(rs.next()) {
		    			if(rs.getInt("M_ID") == M_id) {
		    				bool = false;
		    				break;
		    			}
		    		}
		    		if (!bool) {
		    			JOptionPane.showMessageDialog(null, "예매가 된 영화입니다.");
		    		}else {
		    			int result = JOptionPane.showConfirmDialog(null,"영화 시간 : " + date + "\n"
								+"영화명 : "+ name,"예매 확인",JOptionPane.YES_NO_OPTION);
		    			if (result == 0) {
		    				new Ticket_m(con,id ,M_id);
		    				dispose();
		    			}
		    		}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				}
			}
		});
		
		// 뒤로가기 버튼 이벤트
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new UserFrame(con,"User Menu",id);
				
			}
		});
		// box1 설정 이벤트
		jBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jBox2.removeAllItems();
				try {
		    		name= jBox1.getSelectedItem().toString();
		        	sql = "select * from movie where M_NAME='" + name +"'";
		        	psmt = con.prepareStatement(sql);	
		        	rs = psmt.executeQuery();
		    		String date= null;
		    		while(rs.next()) {
		    			da = rs.getTimestamp("M_TIME");
		    			date = simpleDateFormat.format(da);
		    			jBox2.addItem(date);
		    			
		    		}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				name = jBox1.getSelectedItem().toString();
				date = jBox2.getSelectedItem().toString();
			}
			
		});
	}
	
	private void comboBox_init() throws SQLException {
		
		
		psmt.close();
		rs.close();
	}
}
	
