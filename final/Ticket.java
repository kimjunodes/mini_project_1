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
	JButton check = setButton("����Ȯ��",375,150,85,50);
	JButton back = setButton("�޴�����",375,200,85,50);
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM�� dd�� HH�� mm��");
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
		lbl1 = setLabel("��ȭ ����" , 50,150,150,30);
		lbl2 = setLabel("���� �ð�", 200,150,150,30);
		lbl1.setFont(getFont().deriveFont(30.0f));
		lbl2.setFont(getFont().deriveFont(30.0f));
		getContentPane().add(lbl1);
		getContentPane().add(lbl2);
		
		// �����ϱ� ��ư �̺�Ʈ
		check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (date.isBlank()) {
					JOptionPane.showMessageDialog(null, "�ð��� �������ּ���.");
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
		    			JOptionPane.showMessageDialog(null, "���Ű� �� ��ȭ�Դϴ�.");
		    		}else {
		    			int result = JOptionPane.showConfirmDialog(null,"��ȭ �ð� : " + date + "\n"
								+"��ȭ�� : "+ name,"���� Ȯ��",JOptionPane.YES_NO_OPTION);
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
		
		// �ڷΰ��� ��ư �̺�Ʈ
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new UserFrame(con,"User Menu",id);
				
			}
		});
		// box1 ���� �̺�Ʈ
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
	
