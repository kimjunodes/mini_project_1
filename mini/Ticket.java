package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;



class Ticket extends Frame{
	Vector<String> lst1 = new Vector<String>(); 
	Vector<String> lst2 = new Vector<String>(); 
	Connection con = makeConnection();
	ResultSet rs = null;
	PreparedStatement psmt = null;
	String sql = null;
	JComboBox jBox1 = null;
	JComboBox jBox2 = new JComboBox();
	
	Ticket(String lbl) throws SQLException{
		super(lbl);
		comboBox_init();

		jBox1.addItemListener(new ItemListener() {
		    public void itemStateChanged(ItemEvent arg0) {
		    	try {
		    		String a = jBox1.getSelectedItem().toString();
		    		jBox2.removeAllItems();
		        	sql = "select * from movie where M_NAME='" + a+"'";
		    		psmt = con.prepareStatement(sql);	
		    		rs = psmt.executeQuery();
		    		String date= null;
		    		while(rs.next()) {
		    			date = rs.getString("TIME").substring(5,7) +"월 "+
		    				   rs.getString("TIME").substring(8,10) + "일 "+
		    				   rs.getString("TIME").substring(11,13) + "시 "+
		    				   rs.getString("TIME").substring(14,16) + "분";
		    			lst2.add(date);

		    		}
		    		
		    		for (int j = 0; j < lst2.size(); j++) {
		    			jBox2.addItem(lst2.remove(j));
		    		}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
		jBox1.setBounds(50,150,150,30);
		jBox1.setEditable(false);
		jBox2.setEditable(false);
		jBox2.setBounds(200,150,150,30);
		this.add(jBox1);
		this.add(jBox2);
		
		psmt.close();
		rs.close();
	}
}
	
